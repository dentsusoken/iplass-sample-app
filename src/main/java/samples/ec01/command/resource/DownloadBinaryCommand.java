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

package samples.ec01.command.resource;

import java.util.HashMap;
import java.util.Map;

import org.iplass.gem.command.Constants;
import org.iplass.mtp.command.Command;
import org.iplass.mtp.command.RequestContext;
import org.iplass.mtp.command.annotation.CommandClass;
import org.iplass.mtp.command.annotation.action.ActionMapping;
import org.iplass.mtp.command.annotation.action.Result;
import org.iplass.mtp.command.annotation.action.Result.Type;
import org.iplass.mtp.entity.BinaryReference;

import samples.ec01.dao.EntityDaoHelper;
import samples.ec01.entity.Product;
import samples.ec01.entity.Shop;
import samples.ec01.utils.Consts;

@ActionMapping(
		name = "samples/ec01/resource/bin", 
		displayName = "バイナリデータダウロード",
		privilaged = true,
		result = @Result(
				type = Type.STREAM, 
				value = DownloadBinaryCommand.RESULT_STREAM))
@CommandClass(
		name = "samples/ec01/resource/DownloadBinaryCommand", 
		displayName = "バイナリデータDLコマンド")
public class DownloadBinaryCommand implements Command {

	private final String PARAM_ID = "id";
	private final String PARAM_TYPE = "type";
	public static final String RESULT_STREAM = "resultStream";
	@SuppressWarnings("serial")
	private final Map<String, DefinitionPropName> defPropNameMap = new HashMap<String, DefinitionPropName>() {
		{
			// ショップ情報と店舗画像
			put(Consts.BIN_TYPE_SHOP_IMG, new DefinitionPropName(Shop.DEFINITION_NAME, Shop.SHOP_IMG));
			// 商品と商品画像
			put(Consts.BIN_TYPE_PRODUCT_IMG, new DefinitionPropName(Product.DEFINITION_NAME, Product.PRODUCT_IMG));
		}
	};

	@Override
	public String execute(RequestContext request) {
		Long lobId = request.getParamAsLong(PARAM_ID);
		String type = request.getParam(PARAM_TYPE);

		DefinitionPropName defPropName = defPropNameMap.get(type);
		if (defPropName == null) {
			return Constants.CMD_EXEC_SUCCESS;
		}
		String defName = defPropName.getDefinitionName();
		String propName = defPropName.getPropertyName();
		BinaryReference br = EntityDaoHelper.loadBinaryReference(lobId);
		if (br != null && defName.equals(br.getDefinitionName()) && propName.equals(br.getPropertyName())) {
			request.setAttribute(RESULT_STREAM, br);
		} else {
			request.setAttribute(RESULT_STREAM, request.getSession().loadFromTemporary(lobId));
		}

		return Constants.CMD_EXEC_SUCCESS;
	}
	
	private class DefinitionPropName {
		private String definitionName;
		private String PropertyName;

		public DefinitionPropName(String definitionName, String propertyName) {
			this.definitionName = definitionName;
			this.PropertyName = propertyName;
		}

		public String getDefinitionName() {
			return definitionName;
		}

		public String getPropertyName() {
			return PropertyName;
		}
	}
}
