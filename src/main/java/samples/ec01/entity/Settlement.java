/*
 * Copyright (C) 2018 DENTSU SOKEN INC. All Rights Reserved.
 * 
 * Unless you have purchased a commercial license,
 * the following license terms apply:
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 * 
 * You should have received a copy of the GNU Affero General Public License
 * along with this program. If not, see <https://www.gnu.org/licenses/>.
 */

package samples.ec01.entity;

import java.sql.Date;
import org.iplass.mtp.entity.GenericEntity;
import org.iplass.mtp.entity.SelectValue;
import samples.ec01.entity.Order;

/**
 * 請求 Entity。
 */
public class Settlement extends GenericEntity {

	private static final long serialVersionUID = 1L;

	/** Entity Definition Name */
	public static final String DEFINITION_NAME = "samples.ec01.order.Settlement";

	/** 請求番号 */
	public static final String SETTLEMENT_NO = "settlementNo";
	/** 注文 */
	public static final String ORDER = "order";
	/** 請求状況 */
	public static final String SETTLEMENT_STATUS = "settlementStatus";
	/** 納品予定日 */
	public static final String DELIVER_GOODS_DATE = "deliverGoodsDate";

	public Settlement() {
		setDefinitionName(DEFINITION_NAME);
	}

	/**
	 * 請求番号を返します。
	 * 
	 * @return 請求番号
	 */
	public String getSettlementNo() {
		return getValue(SETTLEMENT_NO);
	}

	/**
	 * 請求番号を設定します。
	 * 
	 * @param settlementNo 請求番号
	 */
	public void setSettlementNo(String settlementNo) {
		setValue(SETTLEMENT_NO, settlementNo);
	}

	/**
	 * 注文を返します。
	 * 
	 * @return 注文
	 */
	public Order getOrder() {
		return getValue(ORDER);
	}

	/**
	 * 注文を設定します。
	 * 
	 * @param order 注文
	 */
	public void setOrder(Order order) {
		setValue(ORDER, order);
	}

	/**
	 * 請求状況を返します。
	 * 
	 * @return 請求状況
	 */
	public SelectValue getSettlementStatus() {
		return getValue(SETTLEMENT_STATUS);
	}

	/**
	 * 請求状況を設定します。
	 * 
	 * @param settlementStatus 請求状況
	 */
	public void setSettlementStatus(SelectValue settlementStatus) {
		setValue(SETTLEMENT_STATUS, settlementStatus);
	}

	/**
	 * 納品予定日を返します。
	 * 
	 * @return 納品予定日
	 */
	public Date getDeliverGoodsDate() {
		return getValue(DELIVER_GOODS_DATE);
	}

	/**
	 * 納品予定日を設定します。
	 * 
	 * @param deliverGoodsDate 納品予定日
	 */
	public void setDeliverGoodsDate(Date deliverGoodsDate) {
		setValue(DELIVER_GOODS_DATE, deliverGoodsDate);
	}

}
