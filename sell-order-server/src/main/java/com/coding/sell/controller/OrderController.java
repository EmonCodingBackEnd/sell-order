/*
 * 文件名称：OrderController.java
 * 系统名称：[系统名称]
 * 模块名称：[模块名称]
 * 软件版权：Copyright (c) 2011-2019, liming20110711@163.com All Rights Reserved.
 * 功能说明：[请在此处输入功能说明]
 * 开发人员：Rushing0711
 * 创建日期：20190318 23:16
 * 修改记录：
 * <Version>        <DateSerial>        <Author>        <Description>
 * 1.0.0            20190318-01         Rushing0711     M201903182316 新建文件
 ********************************************************************************/
package com.coding.sell.controller;

import com.coding.sell.service.api.OrderService;
import com.coding.sell.service.req.OrderRequest;
import com.coding.sell.service.res.OrderResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired OrderService orderService;

    @PostMapping("/create")
    public OrderResponse create(@RequestBody OrderRequest request) {
        return orderService.create(request);
    }
}
