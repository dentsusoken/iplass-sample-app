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

import java.math.BigDecimal;
import org.iplass.mtp.entity.BinaryReference;
import org.iplass.mtp.entity.GenericEntity;
import org.iplass.mtp.entity.SelectValue;
import samples.ec01.entity.Category;
import samples.ec01.entity.ProductSubInfo;

/**
 * 商品 Entity。
 */
public class Product extends GenericEntity {

	private static final long serialVersionUID = 1L;

	/** Entity Definition Name */
	public static final String DEFINITION_NAME = "samples.ec01.products.Product";

	/** カテゴリ */
	public static final String CATEGORY = "category";
	/** 販売ステータス */
	public static final String SALE_STATUS = "saleStatus";
	/** 商品画像 */
	public static final String PRODUCT_IMG = "productImg";
	/** 商品価格 */
	public static final String PRICE = "price";
	/** 税抜き価格 */
	public static final String PRICE_WITHOUT_TAX = "priceWithoutTax";
	/** 商品原価 */
	public static final String COST = "cost";
	/** 登録情報 */
	public static final String REG_INFO = "regInfo";
	/** 在庫数 */
	public static final String STOCK_CNT = "stockCnt";
	/** サブ情報 */
	public static final String SUB_INFO = "subInfo";
	/** オススメ */
	public static final String RECOMMEND = "recommend";
	/** 略称 */
	public static final String ABBREVIATION = "abbreviation";
	/** 言語 */
	public static final String LANGUAGE = "language";

	public Product() {
		setDefinitionName(DEFINITION_NAME);
	}

	/**
	 * カテゴリを返します。
	 * 
	 * @return カテゴリ
	 */
	public Category getCategory() {
		return getValue(CATEGORY);
	}

	/**
	 * カテゴリを設定します。
	 * 
	 * @param category カテゴリ
	 */
	public void setCategory(Category category) {
		setValue(CATEGORY, category);
	}

	/**
	 * 販売ステータスを返します。
	 * 
	 * @return 販売ステータス
	 */
	public SelectValue getSaleStatus() {
		return getValue(SALE_STATUS);
	}

	/**
	 * 販売ステータスを設定します。
	 * 
	 * @param saleStatus 販売ステータス
	 */
	public void setSaleStatus(SelectValue saleStatus) {
		setValue(SALE_STATUS, saleStatus);
	}

	/**
	 * 商品画像を返します。
	 * 
	 * @return 商品画像
	 */
	public BinaryReference getProductImg() {
		return getValue(PRODUCT_IMG);
	}

	/**
	 * 商品画像を設定します。
	 * 
	 * @param productImg 商品画像
	 */
	public void setProductImg(BinaryReference productImg) {
		setValue(PRODUCT_IMG, productImg);
	}

	/**
	 * 商品価格を返します。
	 * 
	 * @return 商品価格
	 */
	public Long getPrice() {
		return getValue(PRICE);
	}

	/**
	 * 商品価格を設定します。
	 * 
	 * @param price 商品価格
	 */
	public void setPrice(Long price) {
		setValue(PRICE, price);
	}

	/**
	 * 税抜き価格を返します。
	 * 
	 * @return 税抜き価格
	 */
	public BigDecimal getPriceWithoutTax() {
		return getValue(PRICE_WITHOUT_TAX);
	}

	/**
	 * 税抜き価格を設定します。
	 * 
	 * @param priceWithoutTax 税抜き価格
	 */
	public void setPriceWithoutTax(BigDecimal priceWithoutTax) {
		setValue(PRICE_WITHOUT_TAX, priceWithoutTax);
	}

	/**
	 * 商品原価を返します。
	 * 
	 * @return 商品原価
	 */
	public Long getCost() {
		return getValue(COST);
	}

	/**
	 * 商品原価を設定します。
	 * 
	 * @param cost 商品原価
	 */
	public void setCost(Long cost) {
		setValue(COST, cost);
	}

	/**
	 * 登録情報を返します。
	 * 
	 * @return 登録情報
	 */
	public String getRegInfo() {
		return getValue(REG_INFO);
	}

	/**
	 * 登録情報を設定します。
	 * 
	 * @param regInfo 登録情報
	 */
	public void setRegInfo(String regInfo) {
		setValue(REG_INFO, regInfo);
	}

	/**
	 * 在庫数を返します。
	 * 
	 * @return 在庫数
	 */
	public Long getStockCnt() {
		return getValue(STOCK_CNT);
	}

	/**
	 * 在庫数を設定します。
	 * 
	 * @param stockCnt 在庫数
	 */
	public void setStockCnt(Long stockCnt) {
		setValue(STOCK_CNT, stockCnt);
	}

	/**
	 * サブ情報を返します。
	 * 
	 * @return サブ情報
	 */
	public ProductSubInfo[] getSubInfo() {
		Object value = getValue(SUB_INFO);
		if (value instanceof ProductSubInfo) {
			return new ProductSubInfo[]{(ProductSubInfo)value};	//for search
		} else {
			return (ProductSubInfo[])value;	//for load
		}
	}

	/**
	 * サブ情報を設定します。
	 * 
	 * @param subInfo サブ情報
	 */
	public void setSubInfo(ProductSubInfo[] subInfo) {
		setValue(SUB_INFO, subInfo);
	}

	/**
	 * オススメを返します。
	 * 
	 * @return オススメ
	 */
	public Boolean getRecommend() {
		return getValue(RECOMMEND);
	}

	/**
	 * オススメを返します。
	 * 値がnullの場合はfalseを返します。
	 * 
	 * @return オススメ
	 */
	public boolean isRecommend() {
		Boolean value = getValue(RECOMMEND);
		if (value == null) {
			return false;
		}
		return value.booleanValue();
	}

	/**
	 * オススメを設定します。
	 * 
	 * @param recommend オススメ
	 */
	public void setRecommend(Boolean recommend) {
		setValue(RECOMMEND, recommend);
	}

	/**
	 * 略称を返します。
	 * 
	 * @return 略称
	 */
	public String getAbbreviation() {
		return getValue(ABBREVIATION);
	}

	/**
	 * 略称を設定します。
	 * 
	 * @param abbreviation 略称
	 */
	public void setAbbreviation(String abbreviation) {
		setValue(ABBREVIATION, abbreviation);
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
