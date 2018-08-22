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

package samples.ec01.command.shipping;

import org.iplass.gem.command.Constants;
import org.iplass.mtp.auth.AuthContext;
import org.iplass.mtp.auth.User;
import org.iplass.mtp.command.Command;
import org.iplass.mtp.command.RequestContext;
import org.iplass.mtp.command.annotation.CommandClass;
import org.iplass.mtp.command.annotation.action.ActionMapping;
import org.iplass.mtp.command.annotation.action.Result;
import org.iplass.mtp.command.annotation.action.Result.Type;
import org.iplass.mtp.web.template.TemplateUtil;

import samples.ec01.bean.CartBean;
import samples.ec01.bean.ShippingBean;
import samples.ec01.utils.Consts;

@ActionMapping(
		name = "samples/ec01/shipping/editShippingInfo", 
		displayName = "配送情報編集画面表示アクション", 
		privilaged = true, 
		result = @Result( 
					type = Type.TEMPLATE, 
					value = "samples/ec01/shipping/inputShippingInfo"),
		command = {})
@ActionMapping(
		name = "samples/ec01/shipping/inputShippingInfo", 
		displayName = "配送情報入力画面表示アクション", 
		privilaged = true, 
		result = { 
			@Result(
					status = Constants.CMD_EXEC_ERROR, 
					type = Type.REDIRECT, 
					value = InputShippingInfoCommand.RESULT_REDIRECT_URL),
			@Result(
					status = Constants.CMD_EXEC_SUCCESS, 
					type = Type.TEMPLATE, 
					value = "samples/ec01/shipping/inputShippingInfo") })
@CommandClass(
		name = "samples/ec01/shipping/InputShippingInfoCommand", 
		displayName = "配送情報入力コマンド")
public class InputShippingInfoCommand implements Command {

	public static final String RESULT_REDIRECT_URL = "redirectURL";
	private final String RESULT_SHIPPING_BEAN = "shippingBean";
	
	@Override
	public String execute(RequestContext request) {
		// セッションからカート情報を取得する
		CartBean cart = (CartBean) request.getSession().getAttribute(Consts.CART_BEAN_SESSION_KEY);
		if (cart == null || cart.getCartSize() == 0) {
			request.setAttribute(InputShippingInfoCommand.RESULT_REDIRECT_URL, "../cart/cartInfo");
			return Constants.CMD_EXEC_ERROR;
		}
		// ユーザ情報の取得
		User user = AuthContext.getCurrentContext().getUser();
		ShippingBean shippingBean = new ShippingBean();
		shippingBean.setFirstName(user.getFirstName());
		shippingBean.setFamilyName(user.getLastName());
		// // 日本語専用"name_kana"取得フォーム
		if (Consts.LANGUAGE_JA.equals(TemplateUtil.getLanguage()) || TemplateUtil.getLanguage() == null) {
			shippingBean.setFirstNameKana(user.getFirstNameKana());
			shippingBean.setFamilyNameKana(user.getLastNameKana());
		}
		shippingBean.setMail(user.getMail());
		request.setAttribute(RESULT_SHIPPING_BEAN, shippingBean);

		return Constants.CMD_EXEC_SUCCESS;
	}
}
