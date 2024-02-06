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

package samples.ec01.command.backoffice.report;

import java.util.List;

import org.iplass.gem.command.Constants;
import org.iplass.mtp.command.Command;
import org.iplass.mtp.command.RequestContext;
import org.iplass.mtp.command.annotation.CommandClass;
import org.iplass.mtp.command.annotation.action.ActionMapping;
import org.iplass.mtp.command.annotation.action.Result;
import org.iplass.mtp.command.annotation.action.Result.Type;
import org.iplass.mtp.entity.query.Query;
import org.iplass.mtp.util.StringUtil;

import samples.ec01.dao.EntityDaoHelper;

@ActionMapping(
		name = "samples/ec01/backoffice/report/settlement",
		displayName = "請求書",
		privilaged = false,
		result = @Result(
				type = Type.TEMPLATE,
				value = "samples/ec01/backoffice/report/settlement"))
@CommandClass(
		name = "samples/ec01/backoffice/report/SettlementViewCommand",
		displayName = "請求書コマンド")
public class SettlementViewCommand implements Command {

	private final String PARAM_OID = "oid";
	private final String RESULT_CUSTOMER = "customer";
	private final String RESULT_PRODUCT = "product_";
	private final String RESULT_QUANTITY = "quantity_";
	private final String RESULT_ITEM_PRICE = "itemPrice_";
	private final String RESULT_PRICE = "price_";
	private final String RESULT_TOTAL_PRICE = "totalPrice";
	private final int MAX_CNT = 8;

	@Override
	public String execute(RequestContext request) {
		String oid = request.getParam(PARAM_OID);
		// 汎用的な検索
		Query query = Query.newQuery("select oid, order.customer, order.orderItem.product.name,"
				+ " order.orderItem.quantity, order.orderItem.product.price, order.orderItem.price "
				+ " from samples.ec01.order.Settlement where oid = '" + StringUtil.escapeEql(oid) + "'");
		List<Object[]> result = EntityDaoHelper.search(query);
		// 初期化
		for (int cnt = 0; cnt < MAX_CNT; cnt++) {
			request.setAttribute(RESULT_PRODUCT + cnt, "");
			request.setAttribute(RESULT_QUANTITY + cnt, 0L);
			request.setAttribute(RESULT_ITEM_PRICE + cnt, 0L);
			request.setAttribute(RESULT_PRICE + cnt, 0L);
		}
		int cnt = 0;
		Long totalPrice = 0L;
		for (Object[] obj : result) {
			String customer = (String) obj[1];
			String product = (String) obj[2];
			Long quantity = (Long) obj[3];
			Long itemPrice = (Long) obj[4];
			Long price = (Long) obj[5];

			request.setAttribute(RESULT_CUSTOMER, customer);
			request.setAttribute(RESULT_PRODUCT + cnt, product);
			request.setAttribute(RESULT_QUANTITY + cnt, quantity);
			request.setAttribute(RESULT_ITEM_PRICE + cnt, itemPrice);
			request.setAttribute(RESULT_PRICE + cnt, price);
			totalPrice = totalPrice + price;
			cnt++;
		}
		request.setAttribute(RESULT_TOTAL_PRICE, totalPrice);

		return Constants.CMD_EXEC_SUCCESS;
	}
}
