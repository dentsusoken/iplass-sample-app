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
<%@ page import="samples.ec01.utils.URLHelper"%>
<%@ page import="samples.ec01.entity.Product"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="m" uri="http://iplass.org/tags/mtp"%>

<!-- カテゴリ選択されてない場合 -->
<c:if test="${categoryName eq 'all'}">
	<c:set var="categoryName" value="${m:rs('iplass-wtp-messages', 'samples.ec01.product.category.all')}" scope="page" />
</c:if>
<div class="row">
	<div class="col-12">
		<div class="border-top"></div>
		<nav class="breadcrumb all-breadcrumb">
			<a class="breadcrumb-item text-primary" href="${m:tcPath()}/samples/ec01/top">
				<c:out value="${m:rs('iplass-wtp-messages', 'samples.ec01.all.breadcrumb.home')}" />
			</a>
			<span class="breadcrumb-item active">
				<c:out value="${categoryName}"/>
			</span>
		</nav>
	</div>
</div>
<div class="row d-none d-md-block">
	<div class="col-12 clearfix">
		<span class="h4"><c:out value="${categoryName}"/></span>
		<div class="dropdown float-right">
			<button class="btn btn-outline-dark dropdown-toggle" type="button" id="categoryList" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
				<c:out value="${m:rs('iplass-wtp-messages', 'samples.ec01.product.category.title') }"/>
			</button>
			<div class="dropdown-menu" aria-labelledby="categoryList">
				<a class="dropdown-item" href="${URLHelper.getCategoryPath('all')}">
					<c:out value="${m:rs('iplass-wtp-messages', 'samples.ec01.product.category.all')}" />
				</a>
				<div class="dropdown-divider"></div>
				<c:forEach var="c" items="${categoryList}">
					<a class="dropdown-item" href="${URLHelper.getCategoryPath(c.oid)}"><c:out value="${c.name}"/></a>
				</c:forEach>
			</div>
		</div>
	</div>
</div>
<div class="row mt-4">
<c:forEach var="p" items="${productList}">
	<div class="col-12 col-md-4">
		<div class="card border-light border-0">
			<a href="${URLHelper.getProductDetailPath(p.oid)}" class="h-100">
				<img class="card-img-top img-thumbnail img-fluid all-product-img" src="${URLHelper.getProductImageResource(p, Product.PRODUCT_IMG)}" alt="${p.name}">
			</a>
			<div class="card-body pt-md-1 text-center">
				<div>
					<a href="${URLHelper.getProductDetailPath(p.oid)}" class="card-link text-dark"><c:out value="${p.name}"/></a>
				</div>
				<div class="all-price">
					<c:out value="${p.price}"/><c:out value="${m:rs('iplass-wtp-messages', 'samples.ec01.all.yen')}" />
				</div>
			</div>
		</div>
	</div>
</c:forEach>
</div>
<div class="row">
	<div class="col-12 all-pagination-list" >
		<!-- ページング -->
		<c:set var="paginationUrl" value="${URLHelper.getCategoryRawPath(categoryId)}" scope="request"/>
		<jsp:include page="../ui/pagination.jsp" flush="false"/>
	</div>
</div>