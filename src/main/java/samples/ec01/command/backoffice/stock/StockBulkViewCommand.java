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

package samples.ec01.command.backoffice.stock;

import java.util.List;

import org.iplass.gem.command.Constants;
import org.iplass.mtp.command.Command;
import org.iplass.mtp.command.RequestContext;
import org.iplass.mtp.command.annotation.CommandClass;
import org.iplass.mtp.command.annotation.action.ActionMapping;
import org.iplass.mtp.command.annotation.action.Result;
import org.iplass.mtp.command.annotation.action.Result.Type;

import samples.ec01.dao.EntityDaoHelper;
import samples.ec01.entity.Product;

@ActionMapping(
		name = "samples/ec01/backoffice/stock/stockUpdate", 
		displayName = "在庫一括表示", 
		privileged = false, 
		result = @Result(
				type = Type.TEMPLATE, 
				value = "samples/ec01/backoffice/stock/stockUpdate"))
@CommandClass(
		name = "samples/ec01/backoffice/stock/StockBulkViewCommand", 
		displayName = "在庫一括表示コマンド")
public class StockBulkViewCommand implements Command {

	private final String RESULT_PRODUCT_LIST = "productList";

	@Override
	public String execute(RequestContext request) {
		String[] properties = new String[] { Product.OID, Product.NAME, Product.STOCK_CNT };
		List<Product> productList = EntityDaoHelper.searchDistinctEntity(Product.DEFINITION_NAME, null, properties);
		request.setAttribute(RESULT_PRODUCT_LIST, productList);
		
		return Constants.CMD_EXEC_SUCCESS;
	}
}
