/*
 * 文件名称：ListForOrderResponse.java
 * 系统名称：[系统名称]
 * 模块名称：[模块名称]
 * 软件版权：Copyright (c) 2011-2019, liming20110711@163.com All Rights Reserved.
 * 功能说明：[请在此处输入功能说明]
 * 开发人员：Rushing0711
 * 创建日期：20190331 19:37
 * 修改记录：
 * <Version>        <DateSerial>        <Author>        <Description>
 * 1.0.0            20190331-01         Rushing0711     M201903311937 新建文件
 ********************************************************************************/
package com.coding.sell.client;

import com.coding.helpers.tool.cmp.api.AppResponse;
import com.coding.sell.common.JsonCustomSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
public class ListForOrderResponse extends AppResponse<List<ListForOrderResponse.ItemVO>> {

    @Data
    public static class ItemVO implements Serializable {

        private static final long serialVersionUID = -7780375263109327785L;

        @JsonSerialize(using = JsonCustomSerializer.Long2StringSerializer.class)
        private Long id;

        /** 商品类型名称. */
        private String categoryName;

        /** 商品类型值. */
        private Integer categoryType;

        private String productName;

        @JsonSerialize(using = JsonCustomSerializer.BigDecimal2SimpleStringSerializer.class)
        private BigDecimal productPrice;

        private String productDescription;

        private String productIcon;
    }
}
