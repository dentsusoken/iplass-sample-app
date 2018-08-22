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

import org.iplass.mtp.entity.BinaryReference;
import org.iplass.mtp.entity.GenericEntity;
import org.iplass.mtp.entity.SelectValue;

/**
 * ショップ情報 Entity。
 */
public class Shop extends GenericEntity {

	private static final long serialVersionUID = 1L;

	/** Entity Definition Name */
	public static final String DEFINITION_NAME = "samples.ec01.basic.Shop";

	/** 会社名 */
	public static final String COMPANY_NAME = "companyName";
	/** 会社名（カナ） */
	public static final String COMPANY_NAME_KANA = "companyNameKana";
	/** 店名 */
	public static final String SHOP_NAME = "shopName";
	/** 店名（カナ） */
	public static final String SHOP_NAME_KANA = "shopNameKana";
	/** 店名（英語） */
	public static final String SHOP_NAME_EN = "shopName_en";
	/** 店名補足 */
	public static final String SHOP_NAME_SUB = "shopNameSub";
	/** 郵便番号 */
	public static final String POST_NO = "postNo";
	/** 住所 */
	public static final String SHOP_ADDR = "shopAddr";
	/** 店舗紹介 */
	public static final String SHOP_INTRODUCTION = "shopIntroduction";
	/** 店舗画像 */
	public static final String SHOP_IMG = "shopImg";
	/** 言語 */
	public static final String LANGUAGE = "language";

	public Shop() {
		setDefinitionName(DEFINITION_NAME);
	}

	/**
	 * 会社名を返します。
	 * 
	 * @return 会社名
	 */
	public String getCompanyName() {
		return getValue(COMPANY_NAME);
	}

	/**
	 * 会社名を設定します。
	 * 
	 * @param companyName 会社名
	 */
	public void setCompanyName(String companyName) {
		setValue(COMPANY_NAME, companyName);
	}

	/**
	 * 会社名（カナ）を返します。
	 * 
	 * @return 会社名（カナ）
	 */
	public String getCompanyNameKana() {
		return getValue(COMPANY_NAME_KANA);
	}

	/**
	 * 会社名（カナ）を設定します。
	 * 
	 * @param companyNameKana 会社名（カナ）
	 */
	public void setCompanyNameKana(String companyNameKana) {
		setValue(COMPANY_NAME_KANA, companyNameKana);
	}

	/**
	 * 店名を返します。
	 * 
	 * @return 店名
	 */
	public String getShopName() {
		return getValue(SHOP_NAME);
	}

	/**
	 * 店名を設定します。
	 * 
	 * @param shopName 店名
	 */
	public void setShopName(String shopName) {
		setValue(SHOP_NAME, shopName);
	}

	/**
	 * 店名（カナ）を返します。
	 * 
	 * @return 店名（カナ）
	 */
	public String getShopNameKana() {
		return getValue(SHOP_NAME_KANA);
	}

	/**
	 * 店名（カナ）を設定します。
	 * 
	 * @param shopNameKana 店名（カナ）
	 */
	public void setShopNameKana(String shopNameKana) {
		setValue(SHOP_NAME_KANA, shopNameKana);
	}

	/**
	 * 店名（英語）を返します。
	 * 
	 * @return 店名（英語）
	 */
	public String getShopNameEn() {
		return getValue(SHOP_NAME_EN);
	}

	/**
	 * 店名（英語）を設定します。
	 * 
	 * @param shopName_en 店名（英語）
	 */
	public void setShopNameEn(String shopName_en) {
		setValue(SHOP_NAME_EN, shopName_en);
	}

	/**
	 * 店名補足を返します。
	 * 
	 * @return 店名補足
	 */
	public String getShopNameSub() {
		return getValue(SHOP_NAME_SUB);
	}

	/**
	 * 店名補足を設定します。
	 * 
	 * @param shopNameSub 店名補足
	 */
	public void setShopNameSub(String shopNameSub) {
		setValue(SHOP_NAME_SUB, shopNameSub);
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
	 * 住所を返します。
	 * 
	 * @return 住所
	 */
	public String[] getShopAddr() {
		return getValue(SHOP_ADDR);
	}

	/**
	 * 住所を設定します。
	 * 
	 * @param shopAddr 住所
	 */
	public void setShopAddr(String[] shopAddr) {
		setValue(SHOP_ADDR, shopAddr);
	}

	/**
	 * 店舗紹介を返します。
	 * 
	 * @return 店舗紹介
	 */
	public String getShopIntroduction() {
		return getValue(SHOP_INTRODUCTION);
	}

	/**
	 * 店舗紹介を設定します。
	 * 
	 * @param shopIntroduction 店舗紹介
	 */
	public void setShopIntroduction(String shopIntroduction) {
		setValue(SHOP_INTRODUCTION, shopIntroduction);
	}

	/**
	 * 店舗画像を返します。
	 * 
	 * @return 店舗画像
	 */
	public BinaryReference getShopImg() {
		return getValue(SHOP_IMG);
	}

	/**
	 * 店舗画像を設定します。
	 * 
	 * @param shopImg 店舗画像
	 */
	public void setShopImg(BinaryReference shopImg) {
		setValue(SHOP_IMG, shopImg);
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
