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

package samples.ec01.bean.ui;

import samples.ec01.utils.Consts;

public class Pagination {

	private int currentPage;
	private int totalPage;

	public Pagination(int currentPage, int totalCount) {
		this.currentPage = currentPage;
		this.totalPage = totalCount % Consts.PAGE_SHOWN_LIMIT == 0 
						? totalCount / Consts.PAGE_SHOWN_LIMIT
						: totalCount / Consts.PAGE_SHOWN_LIMIT + 1;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public String format(String paginationUrl, int pageIndex) {
		return String.format(paginationUrl, pageIndex);
	}
}
