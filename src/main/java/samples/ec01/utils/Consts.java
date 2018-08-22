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

package samples.ec01.utils;

public class Consts {

	public static final String LANGUAGE_JA = "ja";
	public static final String LANGUAGE_EN = "en";
	// Command path
	public static final String LOGIN_COMMAND_PATH = "gem/auth/LoginCommand";
	// Login redirect path
	public static final String LOGIN_REDIRECT_PATH_ATTRIBUTE = "samples.ec01.login.redirect";
	// 登録会員用のグループ
	public static final String PUB_GROUP_CODE = "samples/ec01/Pub001Group";

	// Error message
	public static final String BLANK_MES = "{samples.ec01.bean.validation.isBlank}";
	public static final String NUM_MES = "{samples.ec01.bean.validation.notNum}";
	public static final String NUM_OUT_MES = "{samples.ec01.bean.validation.numOutOfRange}";
	public static final String MAIL_MES = "{samples.ec01.bean.validation.notMail}";
	public static final String KANA_MES = "{samples.ec01.bean.validation.notKana}";
	public static final String USERID_MES1 = "{samples.ec01.bean.validation.UserId.outOfLength}";
	public static final String USERID_MES2 = "{samples.ec01.bean.validation.UserId.invalidChar}";
	public static final String TELL_MES = "{samples.ec01.bean.validation.Tel.invalidChar}";

	// Value format
	public static final int USER_ID_MIN_LENGTH = 4;
	public static final int USER_ID_MAX_LENGTH = 128;
	public static final String USER_ID_FORMART = "^[0-9a-zA-Z\\-@\\_.]+$";
	public static final String TEL_FORMAT = "^[0-9\\-]+$";
	public static final String KANA_FORMAT = "^[ァ-ン]+$";

	// Session scope key
	public static final String CART_BEAN_SESSION_KEY = "cartBean";
	public static final String USER_BEAN_SESSION_KEY = "userBean";
	public static final String SHIPPING_BEAN_SESSION_KEY = "shippingBean";

	// ページでの最大表示件数
	public static final int PAGE_SHOWN_LIMIT = 9;
	public static final String PAGINATION_REQUEST_KEY = "pagination";
	
	// 画像バイナリタイプ
	public static final String BIN_TYPE_SHOP_IMG = "shopImg";
	public static final String BIN_TYPE_PRODUCT_IMG = "productImg";
}
