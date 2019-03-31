/*
 * 文件名称：ProductClient.java
 * 系统名称：[系统名称]
 * 模块名称：[模块名称]
 * 软件版权：Copyright (c) 2011-2019, liming20110711@163.com All Rights Reserved.
 * 功能说明：[请在此处输入功能说明]
 * 开发人员：Rushing0711
 * 创建日期：20190331 12:10
 * 修改记录：
 * <Version>        <DateSerial>        <Author>        <Description>
 * 1.0.0            20190331-01         Rushing0711     M201903311210 新建文件
 ********************************************************************************/
package com.coding.sell.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "SELL-PRODUCT-PROVIDER")
public interface ProductClient {

    @GetMapping("/product/msg")
    String productMsg();
}
