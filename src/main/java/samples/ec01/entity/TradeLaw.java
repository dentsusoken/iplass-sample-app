/*
 * Copyright (C) 2018 INFORMATION SERVICES INTERNATIONAL - DENTSU, LTD. All Rights Reserved.
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

import org.iplass.mtp.entity.GenericEntity;
import org.iplass.mtp.entity.SelectValue;

/**
 * 特定商取引法 Entity。
 */
public class TradeLaw extends GenericEntity {

	private static final long serialVersionUID = 1L;

	/** Entity Definition Name */
	public static final String DEFINITION_NAME = "samples.ec01.basic.TradeLaw";

	/** 販売業者 */
	public static final String DEALER = "dealer";
	/** 運営責任者 */
	public static final String ADMINISTRATOR = "administrator";
	/** 郵便番号 */
	public static final String POST_NO = "postNo";
	/** 所在地 */
	public static final String ADDRESS = "address";
	/** 電話番号 */
	public static final String TEL = "tel";
	/** FAX番号 */
	public static final String FAX = "fax";
	/** メールアドレス */
	public static final String MAIL = "mail";
	/** URL */
	public static final String URL = "url";
	/** 商品代金以外の必要料金 */
	public static final String FEE_REQUIRED = "feeRequired";
	/** 注文方法 */
	public static final String HOW_TO_ORDER = "howToOrder";
	/** 支払方法 */
	public static final String HOW_TO_PAYMENT = "howToPayment";
	/** 支払期限 */
	public static final String PAYMENT_DEADLINE = "paymentDeadline";
	/** 引き渡し時期 */
	public static final String DELIVERY_TIME = "deliveryTime";
	/** 返品・交換について */
	public static final String EXCHANGED = "exchanged";
	/** 言語 */
	public static final String LANGUAGE = "language";

	public TradeLaw() {
		setDefinitionName(DEFINITION_NAME);
	}

	/**
	 * 販売業者を返します。
	 * 
	 * @return 販売業者
	 */
	public String getDealer() {
		return getValue(DEALER);
	}

	/**
	 * 販売業者を設定します。
	 * 
	 * @param dealer 販売業者
	 */
	public void setDealer(String dealer) {
		setValue(DEALER, dealer);
	}

	/**
	 * 運営責任者を返します。
	 * 
	 * @return 運営責任者
	 */
	public String getAdministrator() {
		return getValue(ADMINISTRATOR);
	}

	/**
	 * 運営責任者を設定します。
	 * 
	 * @param administrator 運営責任者
	 */
	public void setAdministrator(String administrator) {
		setValue(ADMINISTRATOR, administrator);
	}

	/**
	 * 郵便番号を返します。
	 * 
	 * @return 郵便番号
	 */
	public String[] getPostNo() {
		return getValue(POST_NO);
	}

	/**
	 * 郵便番号を設定します。
	 * 
	 * @param postNo 郵便番号
	 */
	public void setPostNo(String[] postNo) {
		setValue(POST_NO, postNo);
	}

	/**
	 * 所在地を返します。
	 * 
	 * @return 所在地
	 */
	public String[] getAddress() {
		return getValue(ADDRESS);
	}

	/**
	 * 所在地を設定します。
	 * 
	 * @param address 所在地
	 */
	public void setAddress(String[] address) {
		setValue(ADDRESS, address);
	}

	/**
	 * 電話番号を返します。
	 * 
	 * @return 電話番号
	 */
	public String[] getTel() {
		return getValue(TEL);
	}

	/**
	 * 電話番号を設定します。
	 * 
	 * @param tel 電話番号
	 */
	public void setTel(String[] tel) {
		setValue(TEL, tel);
	}

	/**
	 * FAX番号を返します。
	 * 
	 * @return FAX番号
	 */
	public String[] getFax() {
		return getValue(FAX);
	}

	/**
	 * FAX番号を設定します。
	 * 
	 * @param fax FAX番号
	 */
	public void setFax(String[] fax) {
		setValue(FAX, fax);
	}

	/**
	 * メールアドレスを返します。
	 * 
	 * @return メールアドレス
	 */
	public String getMail() {
		return getValue(MAIL);
	}

	/**
	 * メールアドレスを設定します。
	 * 
	 * @param mail メールアドレス
	 */
	public void setMail(String mail) {
		setValue(MAIL, mail);
	}

	/**
	 * URLを返します。
	 * 
	 * @return URL
	 */
	public String getUrl() {
		return getValue(URL);
	}

	/**
	 * URLを設定します。
	 * 
	 * @param url URL
	 */
	public void setUrl(String url) {
		setValue(URL, url);
	}

	/**
	 * 商品代金以外の必要料金を返します。
	 * 
	 * @return 商品代金以外の必要料金
	 */
	public String getFeeRequired() {
		return getValue(FEE_REQUIRED);
	}

	/**
	 * 商品代金以外の必要料金を設定します。
	 * 
	 * @param feeRequired 商品代金以外の必要料金
	 */
	public void setFeeRequired(String feeRequired) {
		setValue(FEE_REQUIRED, feeRequired);
	}

	/**
	 * 注文方法を返します。
	 * 
	 * @return 注文方法
	 */
	public String getHowToOrder() {
		return getValue(HOW_TO_ORDER);
	}

	/**
	 * 注文方法を設定します。
	 * 
	 * @param howToOrder 注文方法
	 */
	public void setHowToOrder(String howToOrder) {
		setValue(HOW_TO_ORDER, howToOrder);
	}

	/**
	 * 支払方法を返します。
	 * 
	 * @return 支払方法
	 */
	public String getHowToPayment() {
		return getValue(HOW_TO_PAYMENT);
	}

	/**
	 * 支払方法を設定します。
	 * 
	 * @param howToPayment 支払方法
	 */
	public void setHowToPayment(String howToPayment) {
		setValue(HOW_TO_PAYMENT, howToPayment);
	}

	/**
	 * 支払期限を返します。
	 * 
	 * @return 支払期限
	 */
	public String getPaymentDeadline() {
		return getValue(PAYMENT_DEADLINE);
	}

	/**
	 * 支払期限を設定します。
	 * 
	 * @param paymentDeadline 支払期限
	 */
	public void setPaymentDeadline(String paymentDeadline) {
		setValue(PAYMENT_DEADLINE, paymentDeadline);
	}

	/**
	 * 引き渡し時期を返します。
	 * 
	 * @return 引き渡し時期
	 */
	public String getDeliveryTime() {
		return getValue(DELIVERY_TIME);
	}

	/**
	 * 引き渡し時期を設定します。
	 * 
	 * @param deliveryTime 引き渡し時期
	 */
	public void setDeliveryTime(String deliveryTime) {
		setValue(DELIVERY_TIME, deliveryTime);
	}

	/**
	 * 返品・交換についてを返します。
	 * 
	 * @return 返品・交換について
	 */
	public String getExchanged() {
		return getValue(EXCHANGED);
	}

	/**
	 * 返品・交換についてを設定します。
	 * 
	 * @param exchanged 返品・交換について
	 */
	public void setExchanged(String exchanged) {
		setValue(EXCHANGED, exchanged);
	}

	/**
	 * 言語を返します。
	 * 
	 * @return 言語
	 */
	public SelectValue getLanguage() {
		return getValue(LANGUAGE);
	}

	/**
	 * 言語を設定します。
	 * 
	 * @param language 言語
	 */
	public void setLanguage(SelectValue language) {
		setValue(LANGUAGE, language);
	}

}
