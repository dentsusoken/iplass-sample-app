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
import org.iplass.mtp.command.beanmapper.BeanParamMapper;
import org.iplass.mtp.command.beanmapper.MappingException;
import org.iplass.mtp.entity.query.condition.predicate.In;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import samples.ec01.bean.CartBean;
import samples.ec01.dao.EntityDaoHelper;
import samples.ec01.entity.Product;
import samples.ec01.utils.Consts;

@ActionMapping(
		name = "samples/ec01/cart/updateCartInfo", 
		displayName = "カート情報更新アクション", 
		privileged = true,
		synchronizeOnSession = true,
		result = @Result(
				type = Type.REDIRECT, 
				value = UpdateCartInfoCommand.RESULT_REDIRECT_URL))
@CommandClass(
		name = "samples/ec01/cart/UpdateCartInfoCommand", 
		displayName = "カート情報更新コマンド")
public class UpdateCartInfoCommand implements Command {
	
	private static final Logger logger = LoggerFactory.getLogger(UpdateCartInfoCommand.class);
	public static final String RESULT_REDIRECT_URL = "redirectURL";
	private final BeanParamMapper mapper = new BeanParamMapper().withValidation().enableAutoGrow()
			.whitelistPropertyNameRegex("cartItems\\[\\d*\\]\\.productId|cartItems\\[\\d*\\].value");

	@Override
	public String execute(RequestContext request) {
		CartBean cartBean = new CartBean();
		try {
			mapper.populate(cartBean, request.getParamMap());
			// 最新の商品価格を取得する
			Object[] productIds = cartBean.getProductIds().toArray();
			In in = new In(Product.OID, productIds);
			String[] properties = new String[] { Product.OID, Product.PRICE };
			List<Product> products = EntityDaoHelper.searchDistinctEntity(Product.DEFINITION_NAME, in, properties);
			// Mapに変換
			Map<String, Long> prices = products.stream().collect(Collectors.toMap(Product::getOid, Product::getPrice));
			// カートの総額値を再計算する
			cartBean.recaculate(prices);
			// セッションでのカート情報を更新する
			request.getSession().setAttribute(Consts.CART_BEAN_SESSION_KEY, cartBean);
		} catch (MappingException e) {
			logger.warn(e.getMessage());
		}
		request.setAttribute(RESULT_REDIRECT_URL, "cartInfo");

		return Constants.CMD_EXEC_SUCCESS;
	}
}
