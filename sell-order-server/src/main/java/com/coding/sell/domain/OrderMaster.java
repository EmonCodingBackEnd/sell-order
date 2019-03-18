/*
 * 文件名称：OrderMaster.java
 * 系统名称：[系统名称]
 * 模块名称：[模块名称]
 * 软件版权：Copyright (c) 2011-2019, liming20110711@163.com All Rights Reserved.
 * 功能说明：[请在此处输入功能说明]
 * 开发人员：Rushing0711
 * 创建日期：20190318 07:40
 * 修改记录：
 * <Version>        <DateSerial>        <Author>        <Description>
 * 1.0.0            20190318-01         Rushing0711     M201903180740 新建文件
 ********************************************************************************/
package com.coding.sell.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * 订单主表.
 *
 * <p>创建时间: <font style="color:#00FFFF">20190318 07:41</font><br>
 * [请在此输入功能详述]
 *
 * @author Rushing0711
 * @version 1.0.0
 * @since 1.0.0
 */
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "order_master")
@Data
public class OrderMaster extends BaseEntity {

    private static final long serialVersionUID = -8356651156873907423L;

    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "idGenerator")
    @GenericGenerator(
        name = "idGenerator",
        strategy = "com.coding.helpers.tool.cmp.generator.SnowFlakeIdStrategy"
    )
    private Long id;

    /** 买家姓名. */
    @Column(name = "buyer_name")
    private String buyerName;

    /** 买家电话. */
    @Column(name = "buyer_phone")
    private String buyerPhone;

    /** 买家地址. */
    @Column(name = "buyer_address")
    private String buyerAddress;

    /** 买家微信openid. */
    @Column(name = "buyer_openid")
    private String buyerOpenid;

    /** 订单状态. */
    @Column(name = "order_amount")
    private BigDecimal orderAmount;

    /** 订单状态. */
    @Column(name = "order_status")
    private Integer orderStatus;

    /** 支付状态. */
    @Column(name = "pay_status")
    private Integer payStatus;
}
