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

package samples.ec01.command.backoffice.stock;

import java.util.Iterator;
import java.util.List;

import org.iplass.gem.command.Constants;
import org.iplass.mtp.command.Command;
import org.iplass.mtp.command.RequestContext;
import org.iplass.mtp.command.annotation.CommandClass;
import org.iplass.mtp.command.annotation.action.ActionMapping;
import org.iplass.mtp.command.annotation.action.Result;
import org.iplass.mtp.command.annotation.action.Result.Type;
import org.iplass.mtp.entity.UpdateOption;

import samples.ec01.command.exception.EntityDataNotFoundException;
import samples.ec01.dao.EntityDaoHelper;
import samples.ec01.entity.Product;

@ActionMapping(
		name = "samples/ec01/backoffice/stock/doStockUpdate", 
		displayName = "在庫一括更新", 
		privilaged = false, 
		result = @Result(
				type = Type.TEMPLATE, 
				value = "samples/ec01/backoffice/stock/stockUpdate"))
@CommandClass(
		name = "samples/ec01/backoffice/stock/StockBulkUpdateCommand", 
		displayName = "在庫一括更新コマンド")
public class StockBulkUpdateCommand implements Command {

	private final String RESULT_PRODUCT_LIST = "productList";

	@Override
	public String execute(RequestContext request) {
		// 更新処理
		Iterator<String> ite = request.getParamNames();
		while (ite.hasNext()) {
			String productId = ite.next();
			Long stockCnt = request.getParamAsLong(productId);
			// 在庫数の更新
			Product product = EntityDaoHelper.load(Product.DEFINITION_NAME, productId);
			if (product == null) {
				throw new EntityDataNotFoundException("productId: " + productId);
			}
			product.setStockCnt(stockCnt);
			// 更新項目を指定
			UpdateOption option = new UpdateOption();
			option.setUpdateProperties(Product.STOCK_CNT);
			EntityDaoHelper.update(product, option);
		}
		// 更新後の値を取得
		String[] properties = new String[] { Product.OID, Product.NAME, Product.STOCK_CNT };
		List<Product> productList = EntityDaoHelper.searchDistinctEntity(Product.DEFINITION_NAME, null, properties);
		request.setAttribute(RESULT_PRODUCT_LIST, productList);
		return Constants.CMD_EXEC_SUCCESS;
	}
}
