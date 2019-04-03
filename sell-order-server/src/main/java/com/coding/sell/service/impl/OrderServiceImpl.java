/*
 * 文件名称：OrderServiceImpl.java
 * 系统名称：[系统名称]
 * 模块名称：[模块名称]
 * 软件版权：Copyright (c) 2011-2019, liming20110711@163.com All Rights Reserved.
 * 功能说明：[请在此处输入功能说明]
 * 开发人员：Rushing0711
 * 创建日期：20190318 22:40
 * 修改记录：
 * <Version>        <DateSerial>        <Author>        <Description>
 * 1.0.0            20190318-01         Rushing0711     M201903182240 新建文件
 ********************************************************************************/
package com.coding.sell.service.impl;

import com.coding.helpers.tool.cmp.generator.SnowFlakeIdGenerator;
import com.coding.sell.client.product.ProductClient;
import com.coding.sell.common.DictDefinition;
import com.coding.sell.domain.OrderDetail;
import com.coding.sell.domain.OrderMaster;
import com.coding.sell.order.service.req.OrderRequest;
import com.coding.sell.order.service.res.OrderResponse;
import com.coding.sell.product.service.req.DecreaseStockRequest;
import com.coding.sell.product.service.req.ListForOrderRequest;
import com.coding.sell.product.service.res.DecreaseStockResponse;
import com.coding.sell.product.service.res.ListForOrderResponse;
import com.coding.sell.repository.OrderDetailRepository;
import com.coding.sell.repository.OrderMasterRepository;
import com.coding.sell.service.api.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    @Autowired OrderMasterRepository orderMasterRepository;

    @Autowired OrderDetailRepository orderDetailRepository;

    @Autowired ProductClient productClient;

    @Transactional
    @Override
    public OrderResponse create(OrderRequest request) {
        OrderResponse response = new OrderResponse();

        OrderMaster orderMaster = new OrderMaster();
        Long orderId = SnowFlakeIdGenerator.getInstance().nextId();

        // 查询商品信息（调用商品服务）
        List<Long> productIdList =
                request.getOrderDetailDTOList()
                        .stream()
                        .map(OrderRequest.OrderDetailDTO::getProductId)
                        .collect(Collectors.toList());
        ListForOrderRequest forOrderRequest = new ListForOrderRequest();
        forOrderRequest.setProductIdList(productIdList);
        ListForOrderResponse forOrderResponse = productClient.listForOrder(forOrderRequest);
        List<ListForOrderResponse.ItemVO> itemVOList = forOrderResponse.getData();

        // 计算总价
        BigDecimal orderAmount = BigDecimal.ZERO;
        for (OrderRequest.OrderDetailDTO orderDetailDTO : request.getOrderDetailDTOList()) {
            OrderDetail orderDetail = new OrderDetail();
            for (ListForOrderResponse.ItemVO itemVO : itemVOList) {
                if (itemVO.getId().equals(orderDetailDTO.getProductId())) {
                    // 单价*数量
                    orderAmount =
                            itemVO.getProductPrice()
                                    .multiply(new BigDecimal(orderDetailDTO.getProductQuantity()))
                                    .add(orderAmount);
                    BeanUtils.copyProperties(itemVO, orderDetail);
                    orderDetail.setProductId(itemVO.getId());
                    orderDetail.setProductQuantity(orderDetailDTO.getProductQuantity());
                    orderDetail.setOrderId(orderId);

                    // 订单详情入库
                    orderDetailRepository.save(orderDetail);
                }
            }
        }

        // 扣库存（调用商品服务）
        DecreaseStockRequest decreaseStockRequest = new DecreaseStockRequest();
        List<DecreaseStockRequest.CartDTO> cartDTOList =
                request.getOrderDetailDTOList()
                        .stream()
                        .map(
                                e -> {
                                    DecreaseStockRequest.CartDTO cartDTO =
                                            new DecreaseStockRequest.CartDTO();
                                    cartDTO.setProductId(e.getProductId());
                                    cartDTO.setProductQuantity(e.getProductQuantity());
                                    return cartDTO;
                                })
                        .collect(Collectors.toList());
        decreaseStockRequest.setCartDTOList(cartDTOList);
        DecreaseStockResponse decreaseStockResponse =
                productClient.decreaseStock(decreaseStockRequest);

        // 订单入库
        BeanUtils.copyProperties(request, orderMaster);
        orderMaster.setOrderAmount(orderAmount);
        orderMaster.setOrderStatus(DictDefinition.OrderStatus.NEW.getValue());
        orderMaster.setPayStatus(DictDefinition.PayStatus.WAIT.getValue());
        orderMaster.setId(orderId);

        orderMasterRepository.save(orderMaster);

        return response;
    }
}
