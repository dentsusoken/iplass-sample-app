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

package samples.ec01.command.product;

import java.util.List;

import org.iplass.gem.command.Constants;
import org.iplass.mtp.command.Command;
import org.iplass.mtp.command.RequestContext;
import org.iplass.mtp.command.annotation.CommandClass;
import org.iplass.mtp.command.annotation.action.ActionMapping;
import org.iplass.mtp.command.annotation.action.Result;
import org.iplass.mtp.command.annotation.action.Result.Type;
import org.iplass.mtp.entity.query.Limit;
import org.iplass.mtp.entity.query.condition.predicate.Equals;

import samples.ec01.bean.ui.Pagination;
import samples.ec01.command.exception.EntityDataNotFoundException;
import samples.ec01.dao.EntityDaoHelper;
import samples.ec01.entity.Category;
import samples.ec01.entity.Product;
import samples.ec01.utils.Consts;

@ActionMapping(
		name = "samples/ec01/product/category", 
		displayName = "カテゴリ表示用アクション", 
		privileged = true, 
		result = @Result(
				type = Type.TEMPLATE, 
				value = "samples/ec01/product/category"))
@CommandClass(
		name = "samples/ec01/product/CategoryCommand", 
		displayName = "カテゴリコマンド")
public class CategoryCommand implements Command {

	private final String PARAM_CATEGORY_ID = "categoryId";
	private final String PARAM_PAGE_INDEX = "page";
	private final String RESULT_PRODUCT_LIST = "productList";
	private final String RESULT_CATEGROY_ID = "categoryId";
	private final String RESULT_CATEGORY_NAME = "categoryName";
	private final String PRODUCT_CATEGORY_OID = Product.CATEGORY + "." + Category.OID;
	private final String CATEGORY_ALL = "all";

	@Override
	public String execute(RequestContext request) {
		String categoryId = request.getParam(PARAM_CATEGORY_ID);
		int pageIndex = 0;
		try {
			pageIndex = request.getParamAsInt(PARAM_PAGE_INDEX);
		} catch (RuntimeException e) {
		}
		Equals equals = null;
		if (!CATEGORY_ALL.equals(categoryId)) {
			// 対象カテゴリ名の検索
			Category category = EntityDaoHelper.load(Category.DEFINITION_NAME, categoryId);
			if (category == null) {
				throw new EntityDataNotFoundException(PARAM_CATEGORY_ID + ": " + categoryId);
			}
			equals = new Equals(PRODUCT_CATEGORY_OID, categoryId);
			request.setAttribute(RESULT_CATEGORY_NAME, category.getName());
		} else {
			request.setAttribute(RESULT_CATEGORY_NAME, CATEGORY_ALL);
		}
		// 対象カテゴリの商品一覧の検索
		int offset = pageIndex * Consts.PAGE_SHOWN_LIMIT;
		Limit limit = new Limit(Consts.PAGE_SHOWN_LIMIT, offset);
		String[] properties = new String[] { Product.OID, Product.NAME, Product.PRICE, Product.PRODUCT_IMG };
		List<Product> productList = EntityDaoHelper.searchDistinctEntity(Product.DEFINITION_NAME, equals, limit,
				properties);
		if (productList.size() > 0) {
			request.setAttribute(RESULT_PRODUCT_LIST, productList);
		}
		// 対象カテゴリの商品の件数
		int totalCount = EntityDaoHelper.countDistinct(Product.DEFINITION_NAME, equals);
		if (totalCount > 0) {
			Pagination pagination = new Pagination(pageIndex, totalCount);
			request.setAttribute(Consts.PAGINATION_REQUEST_KEY, pagination);
		}
		request.setAttribute(RESULT_CATEGROY_ID, categoryId);

		return Constants.CMD_EXEC_SUCCESS;
	}
}
