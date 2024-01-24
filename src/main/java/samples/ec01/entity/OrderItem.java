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
import samples.ec01.entity.Order;
import samples.ec01.entity.Product;

/**
 * 注文明細 Entity。
 */
public class OrderItem extends GenericEntity {

	private static final long serialVersionUID = 1L;

	/** Entity Definition Name */
	public static final String DEFINITION_NAME = "samples.ec01.order.OrderItem";

	/** 商品 */
	public static final String PRODUCT = "product";
	/** 注文数 */
	public static final String QUANTITY = "quantity";
	/** 金額 */
	public static final String PRICE = "price";
	/** 注文 */
	public static final String ORDER = "order";
	/** 注文日 */
	public static final String ORDER_DATE = "orderDate";

	public OrderItem() {
		setDefinitionName(DEFINITION_NAME);
	}

	/**
	 * 商品を返します。
	 * 
	 * @return 商品
	 */
	public Product getProduct() {
		return getValue(PRODUCT);
	}

	/**
	 * 商品を設定します。
	 * 
	 * @param product 商品
	 */
	public void setProduct(Product product) {
		setValue(PRODUCT, product);
	}

	/**
	 * 注文数を返します。
	 * 
	 * @return 注文数
	 */
	public Long getQuantity() {
		return getValue(QUANTITY);
	}

	/**
	 * 注文数を設定します。
	 * 
	 * @param quantity 注文数
	 */
	public void setQuantity(Long quantity) {
		setValue(QUANTITY, quantity);
	}

	/**
	 * 金額を返します。
	 * 
	 * @return 金額
	 */
	public Long getPrice() {
		return getValue(PRICE);
	}

	/**
	 * 金額を設定します。
	 * 
	 * @param price 金額
	 */
	public void setPrice(Long price) {
		setValue(PRICE, price);
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

}
