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

package samples.ec01.command.inquiry;

import jakarta.validation.groups.Default;

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
import org.iplass.mtp.entity.SelectValue;
import org.iplass.mtp.web.template.TemplateUtil;

import samples.ec01.bean.InquiryBean;
import samples.ec01.bean.validator.group.JapaneseChecks;
import samples.ec01.dao.EntityDaoHelper;
import samples.ec01.entity.Inquiry;
import samples.ec01.enums.InquiryStatus;
import samples.ec01.utils.Consts;


@ActionMapping(
		name = "samples/ec01/inquiry/inquiry", 
		displayName = "お問合せ画面表示", 
		privilaged = true, 
		result = @Result(
				type = Type.TEMPLATE, 
				value = "samples/ec01/inquiry/inquiry"),
		command = {})
@ActionMapping(
		name = "samples/ec01/inquiry/doInquiry", 
		displayName = "お問合せ登録", 
		privilaged = true, 
		tokenCheck = @TokenCheck(
				executeCheck = true, 
				consume = true, 
				exceptionRollback = true), 
		result = { 
				@Result(
						exception = MappingException.class, 
						type = Type.TEMPLATE, 
						value = "samples/ec01/inquiry/inquiry"),
				@Result(
						status = Constants.CMD_EXEC_SUCCESS, 
						type = Type.TEMPLATE, 
						value = "samples/ec01/inquiry/inquirySuccess")})
@CommandClass(
		name = "samples/ec01/inquiry/RegistInquiryCommand", 
		displayName = "お問合せ登録コマンド")
public class RegistInquiryCommand implements Command {

	private final BeanParamMapper mapper = new BeanParamMapper().withValidation()
			.whitelistPropertyNameRegex("^(mail|content|familyName(Kana)?|firstName(Kana)?)$");
	public static final String RESULT_INQUIRY_BEAN = "inquiryBean";


	@Override
	public String execute(RequestContext request) {
		// 入力チェック
		InquiryBean inquiryBean = new InquiryBean();
		request.setAttribute(RESULT_INQUIRY_BEAN, inquiryBean);
		// 日本語専用"name_kana"取得フォーム
		if (Consts.LANGUAGE_JA.equals(TemplateUtil.getLanguage()) || TemplateUtil.getLanguage() == null) {
			mapper.populate(inquiryBean, request.getParamMap(), Default.class, JapaneseChecks.class);
		} else {
			mapper.populate(inquiryBean, request.getParamMap(), Default.class);
		}

		Inquiry inquiry = inquiryBean.toEntity();
		// 問い合わせステータス
		// 1 : 未対応
		// 2 : 対応中
		// 3 : 対応完了
		// 4 : 終了
		SelectValue inquiryStatus = new SelectValue(InquiryStatus.NOT_DEAL.getValue());
		inquiry.setInquiryStatus(inquiryStatus);
		// 請求の登録
		EntityDaoHelper.insert(inquiry);

		return Constants.CMD_EXEC_SUCCESS;
	}
}
