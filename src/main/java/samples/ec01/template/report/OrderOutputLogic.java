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

package samples.ec01.template.report;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.iplass.mtp.command.RequestContext;
import org.iplass.mtp.web.template.TemplateUtil;
import org.iplass.mtp.web.template.report.PoiReportOutputLogic;

import samples.ec01.command.exception.EntityDataNotFoundException;
import samples.ec01.dao.EntityDaoHelper;
import samples.ec01.entity.Order;
import samples.ec01.entity.OrderItem;
import samples.ec01.entity.Product;
import samples.ec01.utils.Consts;

public class OrderOutputLogic implements PoiReportOutputLogic {

	private final String PARAM_OID = "oid";
	private final String SHEET_NAME = "注文書";
	private final String SHEET_NAME_EN = "Order";
	private final int SHEET_ORDER_ROW_NO = 2;
	private final int SHEET_ORDER_COL_NO = 5;
	private final int SHEET_CUSTOMER_ROW_NO = 5;
	private final int SHEET_CUSTOMER_COL_NO = 2;
	private final int SHEET_TABLE_START_ROW_NO = 15;
	private final int SHEET_PRODUCT_NAME_COL_NO = 1;
	private final int SHEET_QUANTITY_COL_NO = 3;
	private final int SHEET_PRICE_COL_NO = 4;
	private final int SHEET_SUBTOTAL_COL_NO = 5;
	private final int SHEET_TOTAL_ROW_NO = 12;
	private final int SHEET_TOTAL_COL_NO = 2;

	@Override
	public void reportOutput(RequestContext context, Workbook book) {
		String oid = (String) context.getAttribute(PARAM_OID);
		Order entity = EntityDaoHelper.load(Order.DEFINITION_NAME, oid);
		if (entity == null) {
			throw new EntityDataNotFoundException(PARAM_OID + ": " + oid);
		}
		Sheet sheet = null;
		if (Consts.LANGUAGE_EN.equals(TemplateUtil.getLanguage())) {
			sheet = book.getSheet(SHEET_NAME_EN);
		} else {
			sheet = book.getSheet(SHEET_NAME);
		}
		// 注文番号
		Row orderNoRow = sheet.getRow(SHEET_ORDER_ROW_NO);
		String orderNo = (String) entity.getOrderNo();
		orderNoRow.getCell(SHEET_ORDER_COL_NO).setCellValue(orderNo);

		// 顧客名
		Row customerRow = sheet.getRow(SHEET_CUSTOMER_ROW_NO);
		String customer = (String) entity.getCustomer();
		customerRow.getCell(SHEET_CUSTOMER_COL_NO).setCellValue(customer);

		OrderItem[] details = (OrderItem[]) entity.getOrderItem();

		int cnt = SHEET_TABLE_START_ROW_NO;
		long totalPrice = 0;
		for (OrderItem detail : details) {

			OrderItem detailEntity = (OrderItem) EntityDaoHelper.load(OrderItem.DEFINITION_NAME, detail.getOid());
			Long quantity = detailEntity.getQuantity();
			Long price = detailEntity.getPrice();

			Row productsRow = sheet.getRow(cnt);
			Product product = (Product) detailEntity.getProduct();
			productsRow.getCell(SHEET_PRODUCT_NAME_COL_NO).setCellValue(product.getName());
			productsRow.getCell(SHEET_QUANTITY_COL_NO).setCellValue(quantity);
			productsRow.getCell(SHEET_PRICE_COL_NO).setCellValue(price / quantity);
			productsRow.getCell(SHEET_SUBTOTAL_COL_NO).setCellValue(price);

			totalPrice = totalPrice + price;
			cnt++;
		}

		// 合計額
		Row totalRow = sheet.getRow(SHEET_TOTAL_ROW_NO);
		totalRow.getCell(SHEET_TOTAL_COL_NO).setCellValue(totalPrice);
	}
}
