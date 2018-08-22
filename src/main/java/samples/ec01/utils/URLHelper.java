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

package samples.ec01.utils;

import org.iplass.mtp.entity.BinaryReference;
import org.iplass.mtp.entity.Entity;
import org.iplass.mtp.web.template.TemplateUtil;

public class URLHelper {

	private final static String PACKAGE_URL_ROOT = "/samples/ec01/";
	private final static String PAGINATION_SUFFIX = "page=%s";
	private final static String DELETE_CART_INFO_URL_PATH = PACKAGE_URL_ROOT + "cart/deleteCartInfo?deleteId=%s";
	private final static String PRODUCT_DETAIL_URL_PATH = PACKAGE_URL_ROOT + "product/detail?productId=%s";
	private final static String CATEGORY_PAGE_URL_PATH = PACKAGE_URL_ROOT + "product/category?categoryId=%s&";
	private final static String CATEGORY_PAGE_INDEX_URL_PATH = CATEGORY_PAGE_URL_PATH + PAGINATION_SUFFIX;
	private final static String NEWS_PAGE_INDEX_URL_PATH = PACKAGE_URL_ROOT + "news/newInfo?" + PAGINATION_SUFFIX;
	private final static String BINARY_RESOUCE_URL_PATH = PACKAGE_URL_ROOT + "resource/bin?id=%d&type=%s";

	public static String getCategoryPath(String categoryId) {
		return TemplateUtil.getTenantContextPath() + String.format(CATEGORY_PAGE_URL_PATH, categoryId);
	}

	public static String getCategoryPath(String categoryId, String page) {
		return TemplateUtil.getTenantContextPath() + String.format(CATEGORY_PAGE_INDEX_URL_PATH, categoryId, page);
	}

	public static String getCategoryRawPath(String categoryId) {
		return getCategoryPath(categoryId) + PAGINATION_SUFFIX;
	}

	public static String getNewInfoRawPath() {
		return TemplateUtil.getTenantContextPath() + NEWS_PAGE_INDEX_URL_PATH;
	}

	public static String getDeleteCartInfoPath(String deleteId) {
		return TemplateUtil.getTenantContextPath() + String.format(DELETE_CART_INFO_URL_PATH, deleteId);
	}

	public static String getProductDetailPath(String productId) {
		return TemplateUtil.getTenantContextPath() + String.format(PRODUCT_DETAIL_URL_PATH, productId);
	}

	public static String getProductImageResource(Entity product, String propertyName) {
		BinaryReference br = product.getValue(propertyName);
		return TemplateUtil.getTenantContextPath() + getBinaryResoucePath(br.getLobId(), Consts.BIN_TYPE_PRODUCT_IMG);
	}

	private static String getBinaryResoucePath(long lobId, String type) {
		return String.format(BINARY_RESOUCE_URL_PATH, lobId, type);
	}
}
