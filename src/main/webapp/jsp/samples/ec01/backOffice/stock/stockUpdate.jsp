<%--
 Copyright (C) 2018 INFORMATION SERVICES INTERNATIONAL - DENTSU, LTD. All Rights Reserved.

 Unless you have purchased a commercial license,
 the following license terms apply:

 This program is free software: you can redistribute it and/or modify
 it under the terms of the GNU Affero General Public License as
 published by the Free Software Foundation, either version 3 of the
 License, or (at your option) any later version.

 This program is distributed in the hope that it will be useful,
 but WITHOUT ANY WARRANTY; without even the implied warranty of
 MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 GNU Affero General Public License for more details.

 You should have received a copy of the GNU Affero General Public License
 along with this program. If not, see <https://www.gnu.org/licenses/>.
 --%>

<%@ page language="java" contentType="text/html; charset=utf-8"	pageEncoding="utf-8"%>
<%@ page import="samples.ec01.entity.Product"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="m" uri="http://iplass.org/tags/mtp"%>

<script type="text/javascript">
function button_onclick(action) {
    var form = $("#bulkForm");
	form.attr("action", action).submit();
}
</script>

<h2 class="hgroup-01">
	<c:out value="${m:rs('iplass-wtp-messages', 'samples.ec01.backoffice.stockUpdate.title')}" />
</h2>
<form id="bulkForm" method="post" action="">
<div class="operation-bar operation-bar_top">
	<ul class="list_operation edit-bar">
		<li class="btn update-btn">
			<input type="button" class="gr-btn" value="${m:rs('iplass-wtp-messages', 'samples.ec01.backoffice.stockUpdate.doUpdate')}" 
					onclick="button_onclick('${m:tcPath()}/samples/ec01/backoffice/stock/doStockUpdate')" />
		</li>
	</ul>
</div><!--lyt-edit-01-->

<div>
	<table class="tbl-reference" cellspacing="0" width="100%">
	<thead>
	<tr class="">
		<th>${m:rs('iplass-wtp-messages', 'samples.ec01.backoffice.stockUpdate.product')}</th>
		<th>${m:rs('iplass-wtp-messages', 'samples.ec01.backoffice.stockUpdate.stock')}</th>
	</tr>
	</thead>
	<tbody>
	<c:forEach var="product" items="${productList}">
		<tr style="border:1px solid #cccccc;">
			<td>${product.name}</td>
			<td>
				<c:set var="stock" value="${product.stockCnt}" />
				<c:choose>
					<c:when test="${empty stock}">
						<input type="text" value="0" name="${product.oid}" size="3">
					</c:when>
					<c:otherwise>
						<input type="text" value="${stock}" name="${product.oid}" size="3">
					</c:otherwise>
				</c:choose>
			</td>
		</tr>
	</c:forEach>
	</tbody>
	</table>
</div>

</form>