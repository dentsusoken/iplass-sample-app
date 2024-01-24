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

package samples.ec01.command.search;

import java.util.List;

import org.iplass.gem.command.Constants;
import org.iplass.mtp.command.Command;
import org.iplass.mtp.command.RequestContext;
import org.iplass.mtp.command.annotation.CommandClass;
import org.iplass.mtp.command.annotation.webapi.RestJson;
import org.iplass.mtp.command.annotation.webapi.WebApi;
import org.iplass.mtp.entity.query.condition.Condition;
import org.iplass.mtp.entity.query.condition.expr.And;
import org.iplass.mtp.entity.query.condition.predicate.Equals;
import org.iplass.mtp.entity.query.condition.predicate.Like;
import org.iplass.mtp.entity.query.condition.predicate.Like.MatchPattern;
import org.iplass.mtp.webapi.definition.MethodType;
import org.iplass.mtp.webapi.definition.RequestType;

import samples.ec01.dao.EntityDaoHelper;
import samples.ec01.entity.Category;
import samples.ec01.entity.Product;


@WebApi(
		name = "samples/ec01/search/fulltextSearch", 
		displayName = "全文検索用コマンド", 
		accepts = RequestType.REST_JSON, 
		methods = MethodType.POST, 
		privilaged = true, 
		restJson = @RestJson(parameterName = "param"), 
		results = FulltextSearchCommand.RESULT_DEFAULT_RESULT)
@CommandClass(
		name = "samples/ec01/search/FulltextSearchCommand", 
		displayName = "全文検索用コマンド")
public class FulltextSearchCommand implements Command {

	private final String PARAM_PRODUCT_NAME = "productName";
	private final String PARAM_CATEGORY_OID = "categoryOid";
	private final String PRODUCT_CATEGORY_OID = Product.CATEGORY + "." + Category.OID;
	private final String ALL = "all";
	public static final String RESULT_DEFAULT_RESULT = "defaultResult";
	
	@Override
	public String execute(RequestContext request) {
		String productName = request.getParam(PARAM_PRODUCT_NAME);
		String categoryOid = request.getParam(PARAM_CATEGORY_OID);

		List<Product> productList = null;
		Condition cond = null;
		String[] properties = new String[] { Product.OID, Product.NAME, Product.PRODUCT_IMG, Product.PRICE };
		if (ALL.equals(categoryOid) == false) {
			// ※ Containsを使いたい場合は、全文検索機能を設定し、有効化する必要があります。
			// cond = new And(new Contains(productName),
			// 				  new Equals(PRODUCT_CATEGORY_OID, categoryOid));
			cond = new And(new Like(Product.NAME, productName, MatchPattern.PARTIAL),
						   new Equals(PRODUCT_CATEGORY_OID, categoryOid));
		} else {
			// ※ Containsを使いたい場合は、全文検索機能を設定し、有効化する必要があります。
			// cond = new Contains(productName);
			cond = new Like(Product.NAME, productName, MatchPattern.PARTIAL);
		}
		productList = EntityDaoHelper.searchDistinctEntity(Product.DEFINITION_NAME, cond, properties);
		if (productList.size() > 0) {
			// 実行結果をdefaultResultとしてセットする
			request.setAttribute(RESULT_DEFAULT_RESULT, productList);
		}

		return Constants.CMD_EXEC_SUCCESS;
	}
}

