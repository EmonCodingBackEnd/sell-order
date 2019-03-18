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
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService {
    @Autowired OrderDetailRepository orderDetailRepository;
    @Autowired OrderMasterRepository orderMasterRepository;

    @Transactional
    @Override
    public OrderResponse create(OrderRequest request) {
        OrderResponse response = new OrderResponse();
        OrderMaster orderMaster = new OrderMaster();
        BeanUtils.copyProperties(request, orderMaster);

        orderMaster.setOrderAmount(BigDecimal.ZERO);
        orderMaster.setOrderStatus(DictDefinition.OrderStatus.NEW.getValue());
        orderMaster.setPayStatus(DictDefinition.PayStatus.WAIT.getValue());
        return response;
    }
}
