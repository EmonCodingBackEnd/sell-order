/*
 * 文件名称：ClientController.java
 * 系统名称：[系统名称]
 * 模块名称：[模块名称]
 * 软件版权：Copyright (c) 2011-2019, liming20110711@163.com All Rights Reserved.
 * 功能说明：[请在此处输入功能说明]
 * 开发人员：Rushing0711
 * 创建日期：20190328 23:20
 * 修改记录：
 * <Version>        <DateSerial>        <Author>        <Description>
 * 1.0.0            20190328-01         Rushing0711     M201903282320 新建文件
 ********************************************************************************/
package com.coding.sell.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/order")
@Slf4j
public class ClientController {

    @Autowired private LoadBalancerClient loadBalancerClient;

    // 第一种方式：直接使用restTemplate，url写死
    @GetMapping("/getProductMsg")
    public String getProductMsg() {
        RestTemplate restTemplate = new RestTemplate();
        String response =
                restTemplate.getForObject("http://localhost:8751/product/msg", String.class);
        log.info("response={}", response);
        return response;
    }

    // 第二种方式：利用loadBalancerClient通过应用名获取url
    @GetMapping("/getProductMsg2")
    public String getProductMsg2() {
        ServiceInstance serviceInstance = loadBalancerClient.choose("SELL-PRODUCT-PROVIDER");
        String url =
                String.format("http://%s:%s", serviceInstance.getHost(), serviceInstance.getPort());

        RestTemplate restTemplate = new RestTemplate();
        String response = restTemplate.getForObject(url + "/product/msg", String.class);
        log.info("response={}", response);
        return response;
    }

    // 第三种方式：利用注解@LoadBalanced，可以在restTemplate里使用应用的服务注册名字
    @Autowired
    @Qualifier("newRestTemplate")
    RestTemplate restTemplate;

    @GetMapping("/getProductMsg3")
    public String getProductMsg3() {
        String response =
                restTemplate.getForObject("http://SELL-PRODUCT-PROVIDER/product/msg", String.class);
        log.info("response={}", response);
        return response;
    }
}
