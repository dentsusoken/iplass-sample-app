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

package samples.ec01.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;
import javax.validation.constraints.Min;

import org.hibernate.validator.constraints.NotBlank;

import samples.ec01.utils.Consts;

public class CartBean implements Serializable {
	private static final long serialVersionUID = 8770008117231095046L;

	@Valid
	private List<CartItem> cartItems = new ArrayList<CartItem>();
	private long totalPrice = 0;

	// コンストラクタ
	public CartBean() {
	}

	// カートに商品を追加
	synchronized public void addCartItem(String productId, long price) {
		for (CartItem item : this.cartItems) {

			// 商品が既に追加されていたらValueを１増加
			if (item.getProductId().equals(productId)) {
				item.setValue(item.getValue() + 1);
				totalPrice += price;
				return;
			}
		}

		// 商品が無かった場合には初期値１が入ったCartItemを追加
		this.cartItems.add(new CartItem(productId));
		totalPrice += price;
	}

	// カートの商品を削除
	synchronized public void removeCartItem(String productId, long price) {
		for (int index = 0; index < this.cartItems.size(); index++) {
			CartItem item = this.cartItems.get(index);
			if (item.getProductId().equals(productId)) {
				this.cartItems.remove(index);
				totalPrice = totalPrice - price * item.getValue();
				return;
			}
		}
	}

	// カートの総額値段を再計算
	synchronized public void recaculate(Map<String, Long> prices) {
		totalPrice = 0;
		for (int index = 0; index < this.cartItems.size(); index++) {
			CartItem cartItem = this.cartItems.get(index);
			Long price = prices.get(cartItem.productId);
			long value = cartItem.getValue();
			// 個数が0だったら削除
			if (price == null || value == 0) {
				cartItems.remove(index);
				index--;
			} else {
				totalPrice += price * value;
			}
		}
	}

	// カートの商品数を返却
	public int getCartSize() {
		return this.cartItems.size();
	}

	public List<CartItem> getCartItems() {
		return this.cartItems;
	}

	// カートの総額値段を返却
	public long getTotalPrice() {
		return totalPrice;
	}

	// カートの総個数を返却
	public long getTotalAmount() {
		long totalAmount = 0;
		for (CartItem item : this.cartItems) {
			totalAmount = totalAmount + item.getValue();
		}
		return totalAmount;
	}
	
	// カートに入れた商品のoidリストを返却
	public List<String> getProductIds() {
		List<String> productIds = this.cartItems.stream()
				.map(item -> item.productId)
				.collect(Collectors.toList());
		return productIds;
	}
	
	public static class CartItem implements Serializable {
		private static final long serialVersionUID = 2730845929449301628L;

		@NotBlank(message = Consts.BLANK_MES)
		private String productId;
		@Min(value = 0, message = Consts.NUM_MES)
		private long value;

		public CartItem() {
		}
		
		public CartItem(String productId) {
			this(productId, 1);
		}

		public CartItem(String productId, int value) {
			this.productId = productId;
			this.value = value;
		}

		public String getProductId() {
			return this.productId;
		}

		public void setProductId(String productId) {
			this.productId = productId;
		}

		public long getValue() {
			return this.value;
		}

		public void setValue(long value) {
			this.value = value;
		}
	}
}
