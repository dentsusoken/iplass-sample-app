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

package samples.ec01.command.member;

import java.util.List;

import org.iplass.gem.command.Constants;
import org.iplass.mtp.auth.Group;
import org.iplass.mtp.auth.User;
import org.iplass.mtp.command.Command;
import org.iplass.mtp.command.RequestContext;
import org.iplass.mtp.command.annotation.CommandClass;
import org.iplass.mtp.command.annotation.action.ActionMapping;
import org.iplass.mtp.command.annotation.action.Result;
import org.iplass.mtp.command.annotation.action.Result.Type;
import org.iplass.mtp.command.annotation.action.TokenCheck;
import org.iplass.mtp.entity.query.condition.predicate.Equals;
import org.iplass.mtp.impl.metadata.MetaDataIllegalStateException;

import samples.ec01.bean.UserBean;
import samples.ec01.command.exception.SessionValueNotFoundException;
import samples.ec01.dao.EntityDaoHelper;
import samples.ec01.utils.Consts;

@ActionMapping(
		name = "samples/ec01/member/registMemberInfo", 
		displayName = "会員情報登録処理", 
		privilaged = true, 
		tokenCheck = @TokenCheck(
				executeCheck = true, 
				consume = true, 
				exceptionRollback = true), 
		result = @Result(
				type = Type.TEMPLATE, 
				value = "samples/ec01/member/registMemberSuccess"))
@CommandClass(
		name = "samples/ec01/member/RegistMemberInfoCommand", 
		displayName = "会員情報登録コマンド")
public class RegistMemberInfoCommand implements Command {
	
	@Override
	public String execute(RequestContext request) {
		// PubGroupのOID取得(GroupCodeから)
		Equals equals = new Equals(Group.CODE, Consts.PUB_GROUP_CODE);
		List<Group> groupsList = EntityDaoHelper.searchDistinctEntity(Group.DEFINITION_NAME, equals, Group.OID, Group.NAME);
		if (groupsList.size() <= 0) {
			throw new MetaDataIllegalStateException("can't find meta data by using group code: " + Consts.PUB_GROUP_CODE);
		}
		Group[] groups = new Group[1];
		groupsList.toArray(groups);
		
		UserBean userBean = (UserBean) request.getSession().getAttribute(Consts.USER_BEAN_SESSION_KEY);
		if (userBean == null) {
			throw new SessionValueNotFoundException();
		}
		// 利用ユーザの登録
		User user = userBean.toEntity();
		user.setGroups(groups);
		EntityDaoHelper.insert(user);
		// ユーザBeanをクリアする
		request.getSession().setAttribute(Consts.USER_BEAN_SESSION_KEY, null);

		return Constants.CMD_EXEC_SUCCESS;
	}
}
