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

package samples.ec01.command.news;

import java.util.List;

import org.iplass.gem.command.Constants;
import org.iplass.mtp.command.Command;
import org.iplass.mtp.command.RequestContext;
import org.iplass.mtp.command.annotation.CommandClass;
import org.iplass.mtp.command.annotation.action.ActionMapping;
import org.iplass.mtp.command.annotation.action.Result;
import org.iplass.mtp.command.annotation.action.Result.Type;
import org.iplass.mtp.entity.query.Limit;

import samples.ec01.bean.ui.Pagination;
import samples.ec01.dao.EntityDaoHelper;
import samples.ec01.entity.NewInfo;
import samples.ec01.utils.Consts;

@ActionMapping(
		name = "samples/ec01/news/newInfo", 
		displayName = "新着情報一覧", 
		privilaged = true, 
		result = @Result(
				type = Type.TEMPLATE, 
				value = "samples/ec01/news/newInfo"))
@CommandClass(
		name = "samples/ec01/news/NewInfoCommand", 
		displayName = "新着情報一覧コマンド")
public class NewInfoCommand implements Command {

	private final String RESULT_NEW_INFO_LIST = "newsInfoList";
	private final String PARAM_PAGE_INDEX = "page";

	@Override
	public String execute(RequestContext request) {
		int pageIndex = 0;
		try {
			pageIndex = request.getParamAsInt(PARAM_PAGE_INDEX);
		} catch (RuntimeException e) {
		}
		// 新着情報の総件数
		int totalCount = EntityDaoHelper.countDistinct(NewInfo.DEFINITION_NAME, null);
		if (totalCount > 0) {
			Pagination pagination = new Pagination(pageIndex, totalCount);
			request.setAttribute(Consts.PAGINATION_REQUEST_KEY, pagination);
			// 新着情報の検索
			String[] properties = new String[] { NewInfo.SHOW_DATE, NewInfo.CONTENT };
			int offset = pageIndex * Consts.PAGE_SHOWN_LIMIT;
			Limit limit = new Limit(Consts.PAGE_SHOWN_LIMIT, offset);
			List<NewInfo> newInfoList = EntityDaoHelper.searchEntity(NewInfo.DEFINITION_NAME, null, limit, properties);
			request.setAttribute(RESULT_NEW_INFO_LIST, newInfoList);
		}

		return Constants.CMD_EXEC_SUCCESS;
	}
}
