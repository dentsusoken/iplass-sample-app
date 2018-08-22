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

package samples.ec01.command.metadata;

import org.iplass.mtp.command.annotation.MetaDataSeeAlso;

@MetaDataSeeAlso({ 
	samples.ec01.command.resource.DownloadBinaryCommand.class,
	samples.ec01.command.TopCommand.class,
	samples.ec01.command.product.DetailCommand.class,
	samples.ec01.command.product.CategoryCommand.class,
	samples.ec01.command.cart.CartInfoCommand.class,
	samples.ec01.command.cart.UpdateCartInfoCommand.class,
	samples.ec01.command.cart.DeleteCartInfoCommand.class,
	samples.ec01.command.member.DoLoginCommand.class,
	samples.ec01.command.member.InputMemberInfoCommand.class,
	samples.ec01.command.member.ConfirmMemberInfoCommand.class,
	samples.ec01.command.member.RegistMemberInfoCommand.class,
	samples.ec01.command.shipping.InputShippingInfoCommand.class,
	samples.ec01.command.shipping.InputShippingInfoNoMemberCommand.class,
	samples.ec01.command.shipping.ConfirmShippingInfoCommand.class,
	samples.ec01.command.shipping.OrderCommand.class,
	samples.ec01.command.search.KeywordSearchCommand.class,
	samples.ec01.command.inquiry.RegistInquiryCommand.class,
	samples.ec01.command.news.NewInfoCommand.class,
	samples.ec01.command.shop.TradeLawCommand.class,
	// Web Api
	samples.ec01.command.cart.InputCartInfoCommand.class,
	samples.ec01.command.search.FulltextSearchCommand.class,
	// Backoffice
	samples.ec01.command.backoffice.stock.StockBulkViewCommand.class,
	samples.ec01.command.backoffice.stock.StockBulkUpdateCommand.class,
	samples.ec01.command.backoffice.report.OrderRetrieveCommand.class,
	samples.ec01.command.backoffice.report.SettlementViewCommand.class,
	// レイアウト
	samples.ec01.command.layout.IndexCommand.class,
	samples.ec01.command.layout.ShippingIndexCommand.class,
})
public class CommandList {
	
}
