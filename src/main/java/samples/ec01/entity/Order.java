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
import samples.ec01.entity.OrderItem;

/**
 * 注文 Entity。
 */
public class Order extends GenericEntity {

	private static final long serialVersionUID = 1L;

	/** Entity Definition Name */
	public static final String DEFINITION_NAME = "samples.ec01.order.Order";

	/** 注文No */
	public static final String ORDER_NO = "orderNo";
	/** 注文日 */
	public static final String ORDER_DATE = "orderDate";
	/** 顧客名 */
	public static final String CUSTOMER = "customer";
	/** 顧客住所 */
	public static final String ADDRESS = "address";
	/** 顧客メールアドレス */
	public static final String MAIL = "mail";
	/** 顧客電話番号 */
	public static final String TEL = "tel";
	/** 注文明細 */
	public static final String ORDER_ITEM = "orderItem";
	/** 合計金額 */
	public static final String TOTAL_PRICE = "totalPrice";

	public Order() {
		setDefinitionName(DEFINITION_NAME);
	}

	/**
	 * 注文Noを返します。
	 * 
	 * @return 注文No
	 */
	public String getOrderNo() {
		return getValue(ORDER_NO);
	}

	/**
	 * 注文Noを設定します。
	 * 
	 * @param orderNo 注文No
	 */
	public void setOrderNo(String orderNo) {
		setValue(ORDER_NO, orderNo);
	}

	/**
	 * 注文日を返します。
	 * 
	 * @return 注文日
	 */
	public Date getOrderDate() {
		return getValue(ORDER_DATE);
	}

	/**
	 * 注文日を設定します。
	 * 
	 * @param orderDate 注文日
	 */
	public void setOrderDate(Date orderDate) {
		setValue(ORDER_DATE, orderDate);
	}

	/**
	 * 顧客名を返します。
	 * 
	 * @return 顧客名
	 */
	public String getCustomer() {
		return getValue(CUSTOMER);
	}

	/**
	 * 顧客名を設定します。
	 * 
	 * @param customer 顧客名
	 */
	public void setCustomer(String customer) {
		setValue(CUSTOMER, customer);
	}

	/**
	 * 顧客住所を返します。
	 * 
	 * @return 顧客住所
	 */
	public String getAddress() {
		return getValue(ADDRESS);
	}

	/**
	 * 顧客住所を設定します。
	 * 
	 * @param address 顧客住所
	 */
	public void setAddress(String address) {
		setValue(ADDRESS, address);
	}

	/**
	 * 顧客メールアドレスを返します。
	 * 
	 * @return 顧客メールアドレス
	 */
	public String getMail() {
		return getValue(MAIL);
	}

	/**
	 * 顧客メールアドレスを設定します。
	 * 
	 * @param mail 顧客メールアドレス
	 */
	public void setMail(String mail) {
		setValue(MAIL, mail);
	}

	/**
	 * 顧客電話番号を返します。
	 * 
	 * @return 顧客電話番号
	 */
	public String getTel() {
		return getValue(TEL);
	}

	/**
	 * 顧客電話番号を設定します。
	 * 
	 * @param tel 顧客電話番号
	 */
	public void setTel(String tel) {
		setValue(TEL, tel);
	}

	/**
	 * 注文明細を返します。
	 * 
	 * @return 注文明細
	 */
	public OrderItem[] getOrderItem() {
		Object value = getValue(ORDER_ITEM);
		if (value instanceof OrderItem) {
			return new OrderItem[]{(OrderItem)value};	//for search
		} else {
			return (OrderItem[])value;	//for load
		}
	}

	/**
	 * 注文明細を設定します。
	 * 
	 * @param orderItem 注文明細
	 */
	public void setOrderItem(OrderItem[] orderItem) {
		setValue(ORDER_ITEM, orderItem);
	}

	/**
	 * 合計金額を返します。
	 * 
	 * @return 合計金額
	 */
	public Long getTotalPrice() {
		return getValue(TOTAL_PRICE);
	}

	/**
	 * 合計金額を設定します。
	 * 
	 * @param totalPrice 合計金額
	 */
	public void setTotalPrice(Long totalPrice) {
		setValue(TOTAL_PRICE, totalPrice);
	}

}
