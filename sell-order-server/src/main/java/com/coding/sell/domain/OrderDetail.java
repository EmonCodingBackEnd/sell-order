/*
 * 文件名称：OrderDetail.java
 * 系统名称：[系统名称]
 * 模块名称：[模块名称]
 * 软件版权：Copyright (c) 2011-2019, liming20110711@163.com All Rights Reserved.
 * 功能说明：[请在此处输入功能说明]
 * 开发人员：Rushing0711
 * 创建日期：20190318 07:45
 * 修改记录：
 * <Version>        <DateSerial>        <Author>        <Description>
 * 1.0.0            20190318-01         Rushing0711     M201903180745 新建文件
 ********************************************************************************/
package com.coding.sell.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * 订单商品.
 *
 * <p>创建时间: <font style="color:#00FFFF">20190318 07:45</font><br>
 * [请在此输入功能详述]
 *
 * @author Rushing0711
 * @version 1.0.0
 * @since 1.0.0
 */
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "order_detail")
@Data
public class OrderDetail extends BaseEntity {

    private static final long serialVersionUID = 7916542646380288017L;

    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "idGenerator")
    @GenericGenerator(
        name = "idGenerator",
        strategy = "com.coding.helpers.tool.cmp.generator.SnowFlakeIdStrategy"
    )
    private Long id;

    /** 订单ID. */
    @Column(name = "order_id")
    private Long orderId;

    /** 商品ID. */
    @Column(name = "product_id")
    private Long productId;

    /** 商品名称. */
    @Column(name = "product_name")
    private String productName;

    /** 商品价格. */
    @Column(name = "product_price")
    private BigDecimal productPrice;

    /** 商品数量. */
    @Column(name = "product_quantity")
    private Integer productQuantity;

    /** 商品小图. */
    @Column(name = "product_icon")
    private String productIcon;
}
