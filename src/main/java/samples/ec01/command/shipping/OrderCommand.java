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

package samples.ec01.command.shipping;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.iplass.gem.command.Constants;
import org.iplass.mtp.command.Command;
import org.iplass.mtp.command.RequestContext;
import org.iplass.mtp.command.annotation.CommandClass;
import org.iplass.mtp.command.annotation.action.ActionMapping;
import org.iplass.mtp.command.annotation.action.Result;
import org.iplass.mtp.command.annotation.action.Result.Type;
import org.iplass.mtp.command.annotation.action.TokenCheck;
import org.iplass.mtp.entity.SelectValue;
import org.iplass.mtp.entity.UpdateOption;
import org.iplass.mtp.util.DateUtil;

import samples.ec01.bean.CartBean;
import samples.ec01.bean.CartBean.CartItem;
import samples.ec01.bean.ShippingBean;
import samples.ec01.command.exception.SessionValueNotFoundException;
import samples.ec01.dao.EntityDaoHelper;
import samples.ec01.entity.Order;
import samples.ec01.entity.OrderItem;
import samples.ec01.entity.Product;
import samples.ec01.entity.Settlement;
import samples.ec01.enums.SettlementStatus;
import samples.ec01.utils.Consts;

@ActionMapping(
		name = "samples/ec01/shipping/doOrder", 
		displayName = "注文処理", 
		privilaged = true, 
		synchronizeOnSession = true, 
		tokenCheck = @TokenCheck(
				executeCheck = true, 
				consume = true, 
				exceptionRollback = true), 
		result = @Result(
				type = Type.TEMPLATE, 
				value = "samples/ec01/shipping/orderSuccess"))
@CommandClass(
		name = "samples/ec01/shipping/OrderCommand", 
		displayName = "注文コマンド")
public class OrderCommand implements Command {
	
	@Override
	public String execute(RequestContext request) {
		//マッピング処理
		ShippingBean shippingBean = (ShippingBean) request.getSession().getAttribute(Consts.SHIPPING_BEAN_SESSION_KEY);
		if (shippingBean == null) {
			throw new SessionValueNotFoundException();
		}
		
		Long time = DateUtil.getCurrentDate().getTime();
		Date sysDate = new Date(time);
		// 注文の登録
		Order order = shippingBean.toEntity();
		order.setOrderDate(sysDate);
		String oid = EntityDaoHelper.insert(order);
		// 注文Noの抽出
		order = EntityDaoHelper.load(Order.DEFINITION_NAME, oid);
		String orderNo = order.getOrderNo();
		// セッションから注文内容を取得
		CartBean cart = (CartBean) request.getSession().getAttribute(Consts.CART_BEAN_SESSION_KEY);
		if (cart == null) {
			throw new SessionValueNotFoundException();
		}
		// 注文明細の登録
		List<String> orderItemOidList = new ArrayList<String>();
		for (int i = 0; i < cart.getCartSize(); i++) {
			CartItem cartItem = cart.getCartItems().get(i);
			String productId = cartItem.getProductId();
			Product product = EntityDaoHelper.load(Product.DEFINITION_NAME, productId);
			String _orderNo = orderNo + "-" + (i + 1);
			OrderItem orderItem = new OrderItem();
			orderItem.setDefinitionName(OrderItem.DEFINITION_NAME);
			orderItem.setName(_orderNo);
			orderItem.setQuantity(cartItem.getValue());
			orderItem.setProduct(product);
			orderItem.setOrderDate(sysDate);
			String oderItemOid = EntityDaoHelper.insert(orderItem);
			orderItemOidList.add(oderItemOid);
		}
		// 注文の更新（注文明細を注文にセット）
		OrderItem[] orderItems = new OrderItem[orderItemOidList.size()];
		for (int i = 0; i < orderItemOidList.size(); i++) {
			String orderItemOid = orderItemOidList.get(i);
			OrderItem orderItem = EntityDaoHelper.load(OrderItem.DEFINITION_NAME, orderItemOid);
			orderItems[i] = orderItem;
		}
		order.setOrderItem(orderItems);

		UpdateOption option = new UpdateOption(false);
		option.setUpdateProperties(Order.ORDER_ITEM);
		EntityDaoHelper.update(order, option);
		// 請求の登録
		// 0: 入金前
		// 1: 入金済納品待
		// 2: 納品済
		// 3: キャンセル
		Settlement settlement = new Settlement();
		settlement.setOrder(order);
		settlement.setSettlementStatus(new SelectValue(SettlementStatus.PRE_SETTLEMENT.getValue()));
		EntityDaoHelper.insert(settlement);
		// セッションから配送先情報をクリアする
		request.getSession().setAttribute(Consts.CART_BEAN_SESSION_KEY, null);
		request.getSession().setAttribute(Consts.SHIPPING_BEAN_SESSION_KEY, null);
		
		return Constants.CMD_EXEC_SUCCESS;
	}
}
