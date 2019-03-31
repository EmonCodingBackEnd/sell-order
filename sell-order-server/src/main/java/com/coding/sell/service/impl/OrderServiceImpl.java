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
import com.coding.sell.client.ListForOrderRequest;
import com.coding.sell.client.ListForOrderResponse;
import com.coding.sell.client.ProductClient;
import com.coding.sell.common.DictDefinition;
import com.coding.sell.domain.OrderMaster;
import com.coding.sell.repository.OrderDetailRepository;
import com.coding.sell.repository.OrderMasterRepository;
import com.coding.sell.service.api.OrderService;
import com.coding.sell.service.req.OrderRequest;
import com.coding.sell.service.res.OrderResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.AnnotationUtils;
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

        List<Long> productIdList =
                request.getItemDTOList()
                        .stream()
                        .map(OrderRequest.ItemDTO::getProductId)
                        .collect(Collectors.toList());
        ListForOrderRequest forOrderRequest = new ListForOrderRequest();
        forOrderRequest.setProductIdList(productIdList);
        ListForOrderResponse forOrderResponse = productClient.listForOrder(forOrderRequest);

        BigDecimal orderAmount;
        for (OrderRequest.ItemDTO itemDTO : request.getItemDTOList()) {
            for (Product)
        }

        // TODO: 2019/3/28 参数校验
        // TODO: 2019/3/28 查询商品（调用商品服务）
        // TODO: 2019/3/28 计算总价
        // TODO: 2019/3/28 扣库存（调用商品服务）
        // TODO: 2019/3/28 订单入库

        OrderMaster orderMaster = new OrderMaster();
        Long orderId = SnowFlakeIdGenerator.getInstance().nextId();

        BeanUtils.copyProperties(request, orderMaster);
        orderMaster.setOrderAmount(BigDecimal.ZERO);
        orderMaster.setOrderStatus(DictDefinition.OrderStatus.NEW.getValue());
        orderMaster.setPayStatus(DictDefinition.PayStatus.WAIT.getValue());
        orderMaster.setId(orderId);
        orderMasterRepository.save(orderMaster);

        for (OrderRequest.ItemDTO itemDTO : request.getItemDTOList()) {}

        return response;
    }
}
