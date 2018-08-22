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

package samples.ec01.command.cart;

import org.iplass.gem.command.Constants;
import org.iplass.mtp.command.Command;
import org.iplass.mtp.command.RequestContext;
import org.iplass.mtp.command.annotation.CommandClass;
import org.iplass.mtp.command.annotation.webapi.RestJson;
import org.iplass.mtp.command.annotation.webapi.WebApi;
import org.iplass.mtp.webapi.definition.MethodType;
import org.iplass.mtp.webapi.definition.RequestType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import samples.ec01.bean.CartBean;
import samples.ec01.command.exception.EntityDataNotFoundException;
import samples.ec01.dao.EntityDaoHelper;
import samples.ec01.entity.Product;
import samples.ec01.utils.Consts;

@WebApi(
		name = "samples/ec01/cart/inputCartInfo", 
		displayName = "カートに入れるWebAPI", 
		accepts = RequestType.REST_JSON, 
		methods = MethodType.POST, 
		privilaged = true, 
		synchronizeOnSession = true,
		restJson = @RestJson(parameterName = "param"), 
		results = InputCartInfoCommand.RESULT_TOTAL_AMOUNT)
@CommandClass(
		name = "samples/ec01/cart/InputCartInfoCommand", 
		displayName = "カートに入れるコマンド")
public class InputCartInfoCommand implements Command {

	private static final Logger logger = LoggerFactory.getLogger(InputCartInfoCommand.class);
	private final String PARAM_PRODUCT_ID = "productId";
	public final static String RESULT_TOTAL_AMOUNT = "totalAmount";

	public String execute(RequestContext request) {
		String productId = request.getParam(PARAM_PRODUCT_ID);
		CartBean cartBean = (CartBean) request.getSession().getAttribute(Consts.CART_BEAN_SESSION_KEY);
		if (cartBean == null) {
			if (logger.isDebugEnabled()) {
				logger.debug("セッションにカートなし");
				logger.debug("セッションに新しいカート作成");
			}
			cartBean = new CartBean();
			request.getSession().setAttribute(Consts.CART_BEAN_SESSION_KEY, cartBean);
		} else {
			if (logger.isDebugEnabled()) {
				logger.debug("セッションにカートあり");
				logger.debug("：総計：" + cartBean.getTotalPrice());
			}
		}
		Product product = EntityDaoHelper.load(Product.DEFINITION_NAME, productId);
		if (product == null) {
			throw new EntityDataNotFoundException(PARAM_PRODUCT_ID + ": " + productId);
		}
		Long price = product.getPrice();
		cartBean.addCartItem(productId, price);
		if (logger.isDebugEnabled()) {
			logger.debug("：総計2：" + cartBean.getTotalPrice());
		}
		request.setAttribute(RESULT_TOTAL_AMOUNT, cartBean.getTotalAmount());

		return Constants.CMD_EXEC_SUCCESS;
	}
}
