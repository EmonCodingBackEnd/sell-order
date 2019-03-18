/*
 * 文件名称：OrderRequest.java
 * 系统名称：[系统名称]
 * 模块名称：[模块名称]
 * 软件版权：Copyright (c) 2011-2019, liming20110711@163.com All Rights Reserved.
 * 功能说明：[请在此处输入功能说明]
 * 开发人员：Rushing0711
 * 创建日期：20190318 22:19
 * 修改记录：
 * <Version>        <DateSerial>        <Author>        <Description>
 * 1.0.0            20190318-01         Rushing0711     M201903182219 新建文件
 ********************************************************************************/
package com.coding.sell.service.req;

import com.coding.helpers.tool.cmp.api.AppRequest;
import com.coding.sell.service.res.OrderResponse;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class OrderRequest extends AppRequest<OrderResponse> {
    private static final long serialVersionUID = 5969721550113922291L;

    @NotBlank(message = "姓名必填")
    private String name;

    @NotBlank(message = "手机号必填")
    private String phone;

    @NotBlank(message = "地址必填")
    private String address;

    @NotBlank(message = "openid必填")
    private String openid;

    @NotEmpty(message = "购买商品列表不允许为空")
    private List<ProductVO> productVOList;

    @Data
    public static class ProductVO implements Serializable {

        private static final long serialVersionUID = -1720615722275129764L;

        private Long productId;

        private Integer productQuantity;
    }

    /** 订单ID. */
    private Long orderId;

    /** 买家姓名. */
    private String buyerName;

    /** 买家电话. */
    private String buyerPhone;

    /** 买家地址. */
    private String buyerAddress;

    /** 买家微信openid. */
    private String buyerOpenid;

    /** 订单状态. */
    private BigDecimal orderAmount;

    /** 订单状态. */
    private Integer orderStatus;

    /** 支付状态. */
    private Integer payStatus;
}
