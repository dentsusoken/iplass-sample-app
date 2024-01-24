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

import org.iplass.gem.command.Constants;
import org.iplass.mtp.command.Command;
import org.iplass.mtp.command.RequestContext;
import org.iplass.mtp.command.annotation.CommandClass;
import org.iplass.mtp.command.annotation.action.ActionMapping;
import org.iplass.mtp.command.annotation.action.Result;
import org.iplass.mtp.command.annotation.action.Result.Type;

@ActionMapping(
		name = "samples/ec01/backoffice/report/order", 
		displayName = "注文情報", 
		privilaged = false, 
		result = @Result(
				type = Type.TEMPLATE, 
				value = "samples/ec01/backoffice/report/order"))
@CommandClass(
		name = "samples/ec01/backoffice/report/OrderRetrieveCommand", 
		displayName = "注文情報コマンド")
public class OrderRetrieveCommand implements Command {

	private final String PARAM_OID = "oid";
	private final String RESULT_OID = "oid";

	@Override
	public String execute(RequestContext request) {
		String oid = request.getParam(PARAM_OID);
		request.setAttribute(RESULT_OID, oid);
		return Constants.CMD_EXEC_SUCCESS;
	}
}
