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

<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page import="samples.ec01.utils.URLHelper" %>
<%@ page import="samples.ec01.entity.Product" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="m" uri="http://iplass.org/tags/mtp"%>

<div class="border-top mb-3"></div>
<div class="row">
<c:forEach var="product" items="${productList}">
	<div class="col-12 col-md-4">
	    <div class="card border-light border-0">
	        <a href="${URLHelper.getProductDetailPath(product.oid)}" class="h-100">
	            <img class="card-img-top img-thumbnail img-fluid all-product-img" src="${URLHelper.getProductImageResource(product, Product.PRODUCT_IMG)}" alt="${product.name}">
	        </a>
	        <div class="card-body pt-md-1 text-center">
	            <div>
	                <a href="${URLHelper.getProductDetailPath(product.oid)}" class="card-link text-dark">${m:esc(product.name)} </a>
	            </div>
	            <div class="all-price">
	                ${m:esc(product.price)}${m:esc(m:rs('iplass-wtp-messages', 'samples.ec01.all.yen'))}
	            </div>
	        </div>
	    </div>
	</div>
</c:forEach>
</div>