/*
 * 文件名称：OrderService.java
 * 系统名称：[系统名称]
 * 模块名称：[模块名称]
 * 软件版权：Copyright (c) 2011-2019, liming20110711@163.com All Rights Reserved.
 * 功能说明：[请在此处输入功能说明]
 * 开发人员：Rushing0711
 * 创建日期：20190318 22:18
 * 修改记录：
 * <Version>        <DateSerial>        <Author>        <Description>
 * 1.0.0            20190318-01         Rushing0711     M201903182218 新建文件
 ********************************************************************************/
package com.coding.sell.service.api;

import com.coding.sell.order.service.req.OrderRequest;
import com.coding.sell.order.service.res.OrderResponse;

public interface OrderService {

    OrderResponse create(OrderRequest request);
}
