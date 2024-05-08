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

package samples.ec01.command.cart;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.iplass.gem.command.Constants;
import org.iplass.mtp.command.Command;
import org.iplass.mtp.command.RequestContext;
import org.iplass.mtp.command.annotation.CommandClass;
import org.iplass.mtp.command.annotation.action.ActionMapping;
import org.iplass.mtp.command.annotation.action.Result;
import org.iplass.mtp.command.annotation.action.Result.Type;
import org.iplass.mtp.entity.query.condition.predicate.In;

import samples.ec01.bean.CartBean;
import samples.ec01.dao.EntityDaoHelper;
import samples.ec01.entity.Product;
import samples.ec01.utils.Consts;

@ActionMapping(
		name = "samples/ec01/cart/cartInfo", 
		displayName = "カート情報アクション", 
		privileged = true, 
		result = @Result(
				type = Type.TEMPLATE, 
				value = "samples/ec01/cart/cartInfo"))
@CommandClass(
		name = "samples/ec01/cart/CartInfoCommand", 
		displayName = "カート情報取得コマンド")
public class CartInfoCommand implements Command {

	private final String RESULT_PRODUCT_MAP = "productMap";

	@Override
	public String execute(RequestContext request) {
		// セッションからカート情報を取得する
		CartBean cartBean = (CartBean) request.getSession().getAttribute(Consts.CART_BEAN_SESSION_KEY);
		if (cartBean != null && cartBean.getCartSize() > 0) {
			// 商品情報の取得
			Object[] productIds = cartBean.getProductIds().toArray();
			In in = new In(Product.OID, productIds);
			String[] properties = new String[] { Product.OID, Product.NAME, Product.PRICE, Product.PRODUCT_IMG };
			List<Product> products = EntityDaoHelper.searchDistinctEntity(Product.DEFINITION_NAME, in, properties);
			Map<String, Product> productMap = products.stream()
					.collect(Collectors.toMap(Product::getOid, product -> product));
			request.setAttribute(RESULT_PRODUCT_MAP, productMap);
		}
		return Constants.CMD_EXEC_SUCCESS;
	}
}
