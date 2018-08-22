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
import samples.ec01.entity.Category;
import samples.ec01.entity.Product;

/**
 * カテゴリ Entity。
 */
public class Category extends GenericEntity {

	private static final long serialVersionUID = 1L;

	/** Entity Definition Name */
	public static final String DEFINITION_NAME = "samples.ec01.products.Category";

	/** 名前(日本語） */
	public static final String NAME_JA = "name_ja";
	/** 名前(英語） */
	public static final String NAME_EN = "name_en";
	/** 親カテゴリ */
	public static final String PARENT_CATEGORY = "parentCategory";
	/** 子カテゴリ */
	public static final String CHILD_CATEGORY = "childCategory";
	/** 商品 */
	public static final String PRODUCTS = "products";

	public Category() {
		setDefinitionName(DEFINITION_NAME);
	}

	/**
	 * 名前(日本語）を返します。
	 * 
	 * @return 名前(日本語）
	 */
	public String getNameJa() {
		return getValue(NAME_JA);
	}

	/**
	 * 名前(日本語）を設定します。
	 * 
	 * @param name_ja 名前(日本語）
	 */
	public void setNameJa(String name_ja) {
		setValue(NAME_JA, name_ja);
	}

	/**
	 * 名前(英語）を返します。
	 * 
	 * @return 名前(英語）
	 */
	public String getNameEn() {
		return getValue(NAME_EN);
	}

	/**
	 * 名前(英語）を設定します。
	 * 
	 * @param name_en 名前(英語）
	 */
	public void setNameEn(String name_en) {
		setValue(NAME_EN, name_en);
	}

	/**
	 * 親カテゴリを返します。
	 * 
	 * @return 親カテゴリ
	 */
	public Category getParentCategory() {
		return getValue(PARENT_CATEGORY);
	}

	/**
	 * 親カテゴリを設定します。
	 * 
	 * @param parentCategory 親カテゴリ
	 */
	public void setParentCategory(Category parentCategory) {
		setValue(PARENT_CATEGORY, parentCategory);
	}

	/**
	 * 子カテゴリを返します。
	 * 
	 * @return 子カテゴリ
	 */
	public Category[] getChildCategory() {
		Object value = getValue(CHILD_CATEGORY);
		if (value instanceof Category) {
			return new Category[]{(Category)value};	//for search
		} else {
			return (Category[])value;	//for load
		}
	}

	/**
	 * 子カテゴリを設定します。
	 * 
	 * @param childCategory 子カテゴリ
	 */
	public void setChildCategory(Category[] childCategory) {
		setValue(CHILD_CATEGORY, childCategory);
	}

	/**
	 * 商品を返します。
	 * 
	 * @return 商品
	 */
	public Product[] getProducts() {
		Object value = getValue(PRODUCTS);
		if (value instanceof Product) {
			return new Product[]{(Product)value};	//for search
		} else {
			return (Product[])value;	//for load
		}
	}

	/**
	 * 商品を設定します。
	 * 
	 * @param products 商品
	 */
	public void setProducts(Product[] products) {
		setValue(PRODUCTS, products);
	}

}
