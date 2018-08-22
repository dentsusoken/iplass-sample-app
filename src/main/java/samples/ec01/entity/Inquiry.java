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

package samples.ec01.entity;

import org.iplass.mtp.entity.GenericEntity;
import org.iplass.mtp.entity.SelectValue;

/**
 * お問合せ Entity。
 */
public class Inquiry extends GenericEntity {

	private static final long serialVersionUID = 1L;

	/** Entity Definition Name */
	public static final String DEFINITION_NAME = "samples.ec01.Inquiry";

	/** お問合せ番号 */
	public static final String INQUIRY_NO = "inquiryNo";
	/** 姓 */
	public static final String FAMILY_NAME = "familyName";
	/** 名 */
	public static final String FIRST_NAME = "firstName";
	/** セイ */
	public static final String FAMILY_NAME_KANA = "familyNameKana";
	/** メイ */
	public static final String FIRST_NAME_KANA = "firstNameKana";
	/** メール */
	public static final String MAIL = "mail";
	/** 内容 */
	public static final String CONTENT = "content";
	/** 種別 */
	public static final String KIND = "kind";
	/** 問い合わせステータス */
	public static final String INQUIRY_STATUS = "inquiryStatus";

	public Inquiry() {
		setDefinitionName(DEFINITION_NAME);
	}

	/**
	 * お問合せ番号を返します。
	 * 
	 * @return お問合せ番号
	 */
	public String getInquiryNo() {
		return getValue(INQUIRY_NO);
	}

	/**
	 * お問合せ番号を設定します。
	 * 
	 * @param inquiryNo お問合せ番号
	 */
	public void setInquiryNo(String inquiryNo) {
		setValue(INQUIRY_NO, inquiryNo);
	}

	/**
	 * 姓を返します。
	 * 
	 * @return 姓
	 */
	public String getFamilyName() {
		return getValue(FAMILY_NAME);
	}

	/**
	 * 姓を設定します。
	 * 
	 * @param familyName 姓
	 */
	public void setFamilyName(String familyName) {
		setValue(FAMILY_NAME, familyName);
	}

	/**
	 * 名を返します。
	 * 
	 * @return 名
	 */
	public String getFirstName() {
		return getValue(FIRST_NAME);
	}

	/**
	 * 名を設定します。
	 * 
	 * @param firstName 名
	 */
	public void setFirstName(String firstName) {
		setValue(FIRST_NAME, firstName);
	}

	/**
	 * セイを返します。
	 * 
	 * @return セイ
	 */
	public String getFamilyNameKana() {
		return getValue(FAMILY_NAME_KANA);
	}

	/**
	 * セイを設定します。
	 * 
	 * @param familyNameKana セイ
	 */
	public void setFamilyNameKana(String familyNameKana) {
		setValue(FAMILY_NAME_KANA, familyNameKana);
	}

	/**
	 * メイを返します。
	 * 
	 * @return メイ
	 */
	public String getFirstNameKana() {
		return getValue(FIRST_NAME_KANA);
	}

	/**
	 * メイを設定します。
	 * 
	 * @param firstNameKana メイ
	 */
	public void setFirstNameKana(String firstNameKana) {
		setValue(FIRST_NAME_KANA, firstNameKana);
	}

	/**
	 * メールを返します。
	 * 
	 * @return メール
	 */
	public String getMail() {
		return getValue(MAIL);
	}

	/**
	 * メールを設定します。
	 * 
	 * @param mail メール
	 */
	public void setMail(String mail) {
		setValue(MAIL, mail);
	}

	/**
	 * 内容を返します。
	 * 
	 * @return 内容
	 */
	public String getContent() {
		return getValue(CONTENT);
	}

	/**
	 * 内容を設定します。
	 * 
	 * @param content 内容
	 */
	public void setContent(String content) {
		setValue(CONTENT, content);
	}

	/**
	 * 種別を返します。
	 * 
	 * @return 種別
	 */
	public SelectValue getKind() {
		return getValue(KIND);
	}

	/**
	 * 種別を設定します。
	 * 
	 * @param kind 種別
	 */
	public void setKind(SelectValue kind) {
		setValue(KIND, kind);
	}

	/**
	 * 問い合わせステータスを返します。
	 * 
	 * @return 問い合わせステータス
	 */
	public SelectValue getInquiryStatus() {
		return getValue(INQUIRY_STATUS);
	}

	/**
	 * 問い合わせステータスを設定します。
	 * 
	 * @param inquiryStatus 問い合わせステータス
	 */
	public void setInquiryStatus(SelectValue inquiryStatus) {
		setValue(INQUIRY_STATUS, inquiryStatus);
	}

}
