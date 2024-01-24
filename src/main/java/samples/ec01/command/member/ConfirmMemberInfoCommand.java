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

package samples.ec01.command.member;

import java.util.List;

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
import org.iplass.mtp.command.beanmapper.MappingResult;
import org.iplass.mtp.web.template.TemplateUtil;

import samples.ec01.bean.UserBean;
import samples.ec01.bean.validator.group.JapaneseChecks;
import samples.ec01.dao.EntityDaoHelper;
import samples.ec01.entity.MemberAgree;
import samples.ec01.utils.Consts;

@ActionMapping(
		name = "samples/ec01/member/confirmMemberInfo", 
		displayName = "会員情報確認アクション", 
		privilaged = true, 
		tokenCheck = @TokenCheck(
				executeCheck = true, 
				consume = true, 
				exceptionRollback = true), 
		result = {
				@Result(
						status = Constants.CMD_EXEC_ERROR, 
						type = Type.TEMPLATE, 
						value = "samples/ec01/member/inputMemberInfo"),
				@Result(
						status = Constants.CMD_EXEC_SUCCESS, 
						type = Type.TEMPLATE, 
						value = "samples/ec01/member/confirmMemberInfo") })
@CommandClass(
		name = "samples/ec01/member/ConfirmMemberInfoCommand", 
		displayName = "会員情報確認コマンド")
public class ConfirmMemberInfoCommand implements Command {

	private final BeanParamMapper mapper = new BeanParamMapper().withValidation()
			.whitelistPropertyNameRegex("^(userId|mail|familyName(Kana)?|firstName(Kana)?)$");
	private final String RESULT_MEMBER_AGREE = "memberAgree";
	private final String RESULT_MAPPING_RESULT = "result";
	private final String RESULT_USER_BEAN = "userBean";

	@Override
	public String execute(RequestContext request) {
		String result = Constants.CMD_EXEC_SUCCESS;
		// 入力チェック
		try {
			request.getSession().setAttribute(Consts.USER_BEAN_SESSION_KEY, null);
			UserBean userBean = new UserBean();
			request.setAttribute(RESULT_USER_BEAN, userBean);
			// 日本語専用"name_kana"取得フォーム
			if (Consts.LANGUAGE_JA.equals(TemplateUtil.getLanguage()) || TemplateUtil.getLanguage() == null) {
				mapper.populate(userBean, request.getParamMap(), Default.class, JapaneseChecks.class);
			} else {
				mapper.populate(userBean, request.getParamMap(), Default.class);
			}
			request.getSession().setAttribute(Consts.USER_BEAN_SESSION_KEY, userBean);
		} catch (MappingException e) {
			MappingResult res = e.getResult();
			request.setAttribute(RESULT_MAPPING_RESULT, res);
			result = Constants.CMD_EXEC_ERROR;
		}
		if (Constants.CMD_EXEC_ERROR.equals(result)) {
			// 会員の規約の呼び出し
			String[] properties = new String[] { MemberAgree.CONTENT };
			List<MemberAgree> memberAgree = EntityDaoHelper.searchEntity(MemberAgree.DEFINITION_NAME, null, properties);
			if (memberAgree.size() > 0) {
				request.setAttribute(RESULT_MEMBER_AGREE, memberAgree.get(0));
			}
		}

		return result;
	}
}
