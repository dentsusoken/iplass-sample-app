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
 * 会員規約 Entity。
 */
public class MemberAgree extends GenericEntity {

	private static final long serialVersionUID = 1L;

	/** Entity Definition Name */
	public static final String DEFINITION_NAME = "samples.ec01.basic.MemberAgree";

	/** 規約内容 */
	public static final String CONTENT = "content";
	/** 言語 */
	public static final String LANGUAGE = "language";

	public MemberAgree() {
		setDefinitionName(DEFINITION_NAME);
	}

	/**
	 * 規約内容を返します。
	 * 
	 * @return 規約内容
	 */
	public String getContent() {
		return getValue(CONTENT);
	}

	/**
	 * 規約内容を設定します。
	 * 
	 * @param content 規約内容
	 */
	public void setContent(String content) {
		setValue(CONTENT, content);
	}

	/**
	 * 言語を返します。
	 * 
	 * @return 言語
	 */
	public SelectValue getLanguage() {
		return getValue(LANGUAGE);
	}

	/**
	 * 言語を設定します。
	 * 
	 * @param language 言語
	 */
	public void setLanguage(SelectValue language) {
		setValue(LANGUAGE, language);
	}

}
