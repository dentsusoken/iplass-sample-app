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

package samples.ec01.web;

import org.iplass.gem.GemErrorUrlSelector;
import org.iplass.mtp.auth.UserExistsException;
import org.iplass.mtp.command.RequestContext;
import org.iplass.mtp.entity.EntityValidationException;
import org.iplass.mtp.web.WebRequestConstants;
import org.iplass.mtp.web.actionmapping.TokenValidationException;

import samples.ec01.command.exception.SessionValueNotFoundException;

public class EcErrorUrlSelector extends GemErrorUrlSelector {

	private final String ROOT_PATH = "samples/ec01";
	private final String TEMPURL_TOKEN_ERROR = ROOT_PATH + "/error/tokenError";
	private final String TEMPURL_ENTITY_ERROR = ROOT_PATH + "/error/entityError";
	private final String TEMPURL_USER_EXISTS_ERROR = ROOT_PATH + "/error/userExistsError";
	private final String TEMPURL_SESSION_VALUE_NOT_FOUND_ERROR = ROOT_PATH + "/error/sessionValueNotFoundError";
	private final String TEMPURL_SYSTEM_ERROR = ROOT_PATH + "/error/systemError";

	@Override
	public String getErrorTemplateName(Throwable exception, RequestContext request, String path) {
		Exception error = (Exception) request.getAttribute(WebRequestConstants.EXCEPTION);
		// ECサイト用
		if (path != null && path.startsWith(ROOT_PATH)) {
			// TokenValidationException用
			if (error instanceof TokenValidationException) {
				return TEMPURL_TOKEN_ERROR;
				// EntityValidationException用
			} else if (error instanceof EntityValidationException) {
				return TEMPURL_ENTITY_ERROR;
				// UserExistsException
			} else if (error instanceof UserExistsException) {
				return TEMPURL_USER_EXISTS_ERROR;
				// SessionValueNotFoundException
			} else if (error instanceof SessionValueNotFoundException) {
				return TEMPURL_SESSION_VALUE_NOT_FOUND_ERROR;
				// その他のエラー用
			} else {
				return TEMPURL_SYSTEM_ERROR;
			}
		}
		return super.getErrorTemplateName(exception, request, path);
	}
}
