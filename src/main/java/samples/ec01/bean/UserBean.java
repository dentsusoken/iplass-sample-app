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

package samples.ec01.bean;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.iplass.mtp.auth.User;
import org.iplass.mtp.web.template.TemplateUtil;

import samples.ec01.bean.annotation.Kana;
import samples.ec01.bean.annotation.UserId;
import samples.ec01.bean.validator.group.JapaneseChecks;
import samples.ec01.utils.Consts;

public class UserBean implements Serializable {

	private static final long serialVersionUID = -2265215179819556717L;

	@NotBlank(message = Consts.BLANK_MES)
	@Size(min = Consts.USER_ID_MIN_LENGTH,
			max = Consts.USER_ID_MAX_LENGTH,
			message = Consts.USERID_MES1)
	@UserId(message = Consts.USERID_MES2)
	private String userId;

	@NotBlank(message = Consts.BLANK_MES)
	private String familyName;

	@NotBlank(message = Consts.BLANK_MES)
	private String firstName;

	@NotBlank(message = Consts.BLANK_MES,
			groups = JapaneseChecks.class)
	@Kana(message = Consts.KANA_MES,
			groups = JapaneseChecks.class)
	private String familyNameKana;

	@NotBlank(message = Consts.BLANK_MES,
			groups = JapaneseChecks.class)
	@Kana(message = Consts.KANA_MES,
			groups = JapaneseChecks.class)
	private String firstNameKana;

	@NotBlank(message = Consts.BLANK_MES)
	@Email(message = Consts.MAIL_MES)
	private String mail;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getFamilyName() {
		return familyName;
	}

	public void setFamilyName(String familyName) {
		this.familyName = familyName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getFamilyNameKana() {
		return familyNameKana;
	}

	public void setFamilyNameKana(String familyNameKana) {
		this.familyNameKana = familyNameKana;
	}

	public String getFirstNameKana() {
		return firstNameKana;
	}

	public void setFirstNameKana(String firstNameKana) {
		this.firstNameKana = firstNameKana;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public User toEntity() {
		// Entityクラスに変換する
		User user = new User();
		user.setAccountId(this.userId);
		user.setLastName(this.familyName);
		user.setFirstName(this.firstName);
		// 日本語専用"name_kana"取得フォーム
		if (Consts.LANGUAGE_JA.equals(TemplateUtil.getLanguage()) || TemplateUtil.getLanguage() == null) {
			user.setLastNameKana(this.familyNameKana);
			user.setFirstNameKana(this.firstNameKana);
		}
		user.setMail(this.mail);

		return user;
	}
}
