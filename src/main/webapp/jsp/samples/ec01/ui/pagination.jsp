<%--
 Copyright (C) 2018 DENTSU SOKEN INC. All Rights Reserved.

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

<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page import="samples.ec01.bean.ui.Pagination" %>
<%@ taglib prefix="m" uri="http://iplass.org/tags/mtp"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<% 
	Pagination pagination = (Pagination)request.getAttribute("pagination");
	if (pagination != null) {
%> 
<nav aria-label="Page navigation">
 <ul class="pagination pagination-md justify-content-center">
 	<!-- 前へ -->
	<li class="page-item">
	<c:choose>
		<c:when test="${ pagination.currentPage - 1 lt 0 }">
			<a class="page-link border-0 text-dark disabled"  aria-label="Previous">
		</c:when>
		<c:otherwise>
			<a class="page-link border-0 text-dark" href="${pagination.format(paginationUrl, pagination.currentPage - 1)}" aria-label="Previous">
		</c:otherwise>
	</c:choose> 
				<span aria-hidden="true">&laquo;${m:rs('iplass-wtp-messages', 'samples.ec01.all.pagination.prev')}</span>
				<span class="visually-hidden">Previous</span>
			</a>
	</li>
<!-- ページ番号 -->
<c:forEach var="i" begin="0" end="${pagination.totalPage - 1}">
	<!-- 最大表示ページ数 -->
	<c:set var="maxPageShown" scope="page" value="3" />
	<c:choose>
		<c:when test="${ i eq pagination.currentPage}">
			<li class="page-item disabled">
				<a class="page-link border-0 text-dark bg-light">${i + 1}</a>
			</li>	
		</c:when>
		<c:when test="${ i lt maxPageShown || i gt pagination.totalPage - maxPageShown }">
			<li class="page-item">
				<a class="page-link border-0 text-dark" href="${pagination.format(paginationUrl, i)}">${i + 1}</a>
			</li>
		</c:when>
		<c:when test="${ i eq maxPageShown }">
			<li class="page-item">
				<a class="page-link border-0 text-dark">......</a>
			</li>
		</c:when>
	</c:choose>	
</c:forEach>
	<!-- 次へ -->
	<li class="page-item">
	<c:choose>
		<c:when test="${ pagination.currentPage + 1 ge pagination.totalPage }">
			<a class="page-link border-0 text-dark disabled" aria-label="Next">
		</c:when>
		<c:otherwise>
			<a class="page-link border-0 text-dark" href="${pagination.format(paginationUrl, pagination.currentPage + 1)}" aria-label="Next">
		</c:otherwise>
	</c:choose>
				<span aria-hidden="true">&laquo;${m:rs('iplass-wtp-messages', 'samples.ec01.all.pagination.next')}</span>
				<span class="visually-hidden">Next</span>
			</a>
	</li>
 </ul>
</nav>
<% } %>