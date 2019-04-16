/*
 * 文件名称：ProductInfoReceiver.java
 * 系统名称：[系统名称]
 * 模块名称：[模块名称]
 * 软件版权：Copyright (c) 2011-2019, liming20110711@163.com All Rights Reserved.
 * 功能说明：[请在此处输入功能说明]
 * 开发人员：Rushing0711
 * 创建日期：20190415 22:51
 * 修改记录：
 * <Version>        <DateSerial>        <Author>        <Description>
 * 1.0.0            20190415-01         Rushing0711     M201904152251 新建文件
 ********************************************************************************/
package com.coding.sell.message.mq;

import com.coding.sell.common.JsonConverter;
import com.coding.sell.product.message.DecreaseStockMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
public class ProductInfoReceiver {

    @RabbitListener(queuesToDeclare = @Queue("productInfo"))
    public void process(String message) {
        List<DecreaseStockMessage> decreaseStockMessageList =
                JsonConverter.toDecreaseStockMessage(message);
        log.info("【库存扣减队列消息】从队列【{}】接收到消息：{}", "productInfo", decreaseStockMessageList);
    }
}
