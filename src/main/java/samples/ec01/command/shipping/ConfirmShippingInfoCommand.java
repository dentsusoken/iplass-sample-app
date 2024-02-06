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

package samples.ec01.command.shipping;

import javax.validation.groups.Default;

import org.iplass.gem.command.Constants;
import org.iplass.mtp.command.Command;
import org.iplass.mtp.command.RequestContext;
import org.iplass.mtp.command.annotation.CommandClass;
import org.iplass.mtp.command.annotation.action.ActionMapping;
import org.iplass.mtp.command.annotation.action.Result;
import org.iplass.mtp.command.annotation.action.Result.Type;
import org.iplass.mtp.command.annotation.action.TokenCheck;
import org.iplass.mtp.command.beanmapper.BeanParamMapper;
import org.iplass.mtp.command.beanmapper.MappingException;
import org.iplass.mtp.web.template.TemplateUtil;

import samples.ec01.bean.ShippingBean;
import samples.ec01.bean.validator.group.JapaneseChecks;
import samples.ec01.utils.Consts;

@ActionMapping(
		name = "samples/ec01/shipping/confirmShippingInfo", 
		displayName = "配送情報確認画面表示アクション", 
		privilaged = true, 
		tokenCheck = @TokenCheck(
				executeCheck = true, 
				consume = true, 
				exceptionRollback = true), 
		result = {
			@Result(
					exception = MappingException.class, 
					type = Type.TEMPLATE, 
					value = "samples/ec01/shipping/inputShippingInfo"),
			@Result(
					status=Constants.CMD_EXEC_SUCCESS, 
					type = Type.TEMPLATE, 
					value = "samples/ec01/shipping/confirmShippingInfo") })
@CommandClass(
		name = "samples/ec01/shipping/ConfirmShippingInfoCommand", 
		displayName = "配送情報確認コマンド")
public class ConfirmShippingInfoCommand implements Command {

	private final BeanParamMapper mapper = new BeanParamMapper().withValidation()
			.whitelistPropertyNameRegex("^(userId|mail|address|tel|familyName(Kana)?|firstName(Kana)?)$");
	private final String RESULT_SHIPPING_BEAN = "shippingBean";

	@Override
	public String execute(RequestContext request) {
		request.getSession().setAttribute(Consts.SHIPPING_BEAN_SESSION_KEY, null);
		ShippingBean shippingBean = new ShippingBean();
		request.setAttribute(RESULT_SHIPPING_BEAN, shippingBean);
		// 日本語専用"name_kana"取得フォーム
		if (Consts.LANGUAGE_JA.equals(TemplateUtil.getLanguage()) || TemplateUtil.getLanguage() == null) {
			mapper.populate(shippingBean, request.getParamMap(), Default.class, JapaneseChecks.class);
		} else {
			mapper.populate(shippingBean, request.getParamMap(), Default.class);
		}
		request.getSession().setAttribute(Consts.SHIPPING_BEAN_SESSION_KEY, shippingBean);

		return Constants.CMD_EXEC_SUCCESS;
	}
}
