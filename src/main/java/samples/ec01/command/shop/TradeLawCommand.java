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

package samples.ec01.command.shop;

import java.util.List;

import org.iplass.gem.command.Constants;
import org.iplass.mtp.command.Command;
import org.iplass.mtp.command.RequestContext;
import org.iplass.mtp.command.annotation.CommandClass;
import org.iplass.mtp.command.annotation.action.ActionMapping;
import org.iplass.mtp.command.annotation.action.Result;
import org.iplass.mtp.command.annotation.action.Result.Type;

import samples.ec01.dao.EntityDaoHelper;
import samples.ec01.entity.Shop;
import samples.ec01.entity.TradeLaw;

@ActionMapping(
		name = "samples/ec01/shop/tradeLaw", 
		displayName = "特定商取引画面表示アクション", 
		privilaged = true, 
		result = @Result(
				type = Type.TEMPLATE, 
				value = "samples/ec01/shop/tradeLaw"))
@CommandClass(
		name = "samples/ec01/shop/TradeLawCommand", 
		displayName = "特定商取引画面表示コマンド")
public class TradeLawCommand implements Command {

	private final String RESULT_TRADE_LAW = "tradeLaw";
	private final String RESULT_SHOP_INFO = "shopInfo";

	@Override
	public String execute(RequestContext request) {	
		// サイト概要の検索
		String[] properties = new String[] { Shop.SHOP_NAME, Shop.SHOP_NAME_SUB, Shop.SHOP_INTRODUCTION };
		List<Shop> shopInfo = EntityDaoHelper.searchEntity(Shop.DEFINITION_NAME, null, properties);
		if (shopInfo.size() > 0) {
			request.setAttribute(RESULT_SHOP_INFO, shopInfo.get(0));
		}
		// 特定商取引の検索
		List<TradeLaw> tradeLaw = EntityDaoHelper.searchEntity(TradeLaw.DEFINITION_NAME, null, (String[]) null);
		if (tradeLaw.size() > 0) {
			request.setAttribute(RESULT_TRADE_LAW, tradeLaw.get(0));
		}
		return Constants.CMD_EXEC_SUCCESS;
	}
}
