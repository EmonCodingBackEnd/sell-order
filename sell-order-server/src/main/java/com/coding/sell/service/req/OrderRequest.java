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
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class OrderRequest extends AppRequest<OrderResponse> {
    private static final long serialVersionUID = 5969721550113922291L;

    /** 买家姓名. */
    @NotBlank(message = "姓名必填")
    @JsonProperty("name")
    private String buyerName;

    /** 买家手机号. */
    @NotBlank(message = "手机号必填")
    @JsonProperty("phone")
    private String buyerPhone;

    /** 买家地址. */
    @NotBlank(message = "地址必填")
    @JsonProperty("address")
    private String buyerAddress;

    /** 买家微信Openid. */
    @NotBlank(message = "openid必填")
    @JsonProperty("openid")
    private String buyerOpenid;

    /** 订单详情. */
    @NotEmpty(message = "购物车不允许为空")
    @JsonProperty("details")
    private List<OrderDetailDTO> orderDetailDTOList;

    @Data
    public static class OrderDetailDTO implements Serializable {

        private static final long serialVersionUID = -1720615722275129764L;

        private Long productId;

        private Integer productQuantity;
    }
}
