/*
 * 文件名称：OrderMasterRepository.java
 * 系统名称：[系统名称]
 * 模块名称：[模块名称]
 * 软件版权：Copyright (c) 2011-2019, liming20110711@163.com All Rights Reserved.
 * 功能说明：[请在此处输入功能说明]
 * 开发人员：Rushing0711
 * 创建日期：20190318 21:55
 * 修改记录：
 * <Version>        <DateSerial>        <Author>        <Description>
 * 1.0.0            20190318-01         Rushing0711     M201903182155 新建文件
 ********************************************************************************/
package com.coding.sell.repository;

import com.coding.sell.domain.OrderMaster;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderMasterRepository extends JpaRepository<OrderMaster, Long> {

}
