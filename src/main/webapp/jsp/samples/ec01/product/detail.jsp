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
<%@ page import="java.text.DecimalFormat" %>
<%@ page import="org.iplass.mtp.util.StringUtil" %>
<%@ page import="org.iplass.mtp.entity.Entity" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="m" uri="http://iplass.org/tags/mtp"%>

<%
	Product productInfo = (Product)request.getAttribute("productInfo");
	String regInfo = productInfo.getRegInfo();
	if (StringUtil.isNotEmpty(regInfo)) {
	    regInfo = regInfo.replaceAll("\\r\\n", "<BR>").replaceAll("\\n", "<BR>").replaceAll("\\r", "<BR>").replaceAll(" ", "&nbsp;");
	}
%>

<div class="row">
	<div class="col-12">
		<div class="border-top"></div>
		<nav class="breadcrumb all-breadcrumb">
			<a class="breadcrumb-item text-primary" href="${m:tcPath()}/samples/ec01/top">
				${m:esc(m:rs('iplass-wtp-messages', 'samples.ec01.all.breadcrumb.home'))}
			</a>
			<span class="breadcrumb-item active">
				${m:esc(productInfo.name)}
			</span>
		</nav>
	</div>
</div>
<div class="row text-center text-md-left">
	<div class="col-sm-12 col-md-5">
		<img src="${URLHelper.getProductImageResource(productInfo, Product.PRODUCT_IMG)}" class="img-thumbnail w-100">
	</div>
	<div class="col-sm-12 col-md-7">
		<h4>${m:esc(productInfo.name)} </h4>
		<p class="font-weight-bold">
			${m:esc(m:rs('iplass-wtp-messages', 'samples.ec01.product.detail.price'))} ：
			<span class="all-price">
				${m:esc(productInfo.price)}${m:esc(m:rs('iplass-wtp-messages', 'samples.ec01.all.yen'))}
			</span>
			（${m:esc(m:rs('iplass-wtp-messages', 'samples.ec01.product.detail.priceWithoutTax'))}
			${m:esc(m:fmt(productInfo.getPriceWithoutTax(), '###0'))}${m:esc(m:rs('iplass-wtp-messages', 'samples.ec01.all.yen'))} ）
		</p>
		<p>
 			<%= regInfo %>
		</p>
		<!-- 商品サブ情報 -->
		<p class="h5">
			<c:forEach var="sub" items="${subInfoList}">
				<span class="badge badge-info" title="${sub.description}">${m:esc(sub.name)} </span>
			</c:forEach>
		</p>
		<div cart-data="addToCart">
			<button type="button" class="btn btn-outline-dark my-2" onclick="inputCartInfo('${productInfo.oid}');">
				${m:esc(m:rs('iplass-wtp-messages', 'samples.ec01.product.detail.addToCart'))}
			</button>
		</div>
		<div cart-data="viewCart" class="d-none">
			<a class="btn btn-dark my-2" href="${m:tcPath()}/samples/ec01/cart/cartInfo">
				${m:esc(m:rs('iplass-wtp-messages', 'samples.ec01.product.detail.viewCart'))}
			</a>
			<a class="btn btn-outline-dark my-2" href="${m:tcPath()}/samples/ec01/top">
				${m:esc(m:rs('iplass-wtp-messages', 'samples.ec01.product.detail.continueShopping'))}
			</a>
		</div>
	</div>
</div>
<div class="row mt-5 border-bottom">
	<div class="col-12 detail-description">
		<p>${productInfo.description}</p>
	</div>
</div>
<!-- おすすめ -->
<div class="row">
	<div class="col-12 mt-4">
		<h4>${m:rs('iplass-wtp-messages', 'samples.ec01.product.detail.recommend')}</h4>
		<div class="row">
		<c:forEach var="p" items="${productList}">
			<div class="col-12 col-md-4">
				<div class="card border-light border-0">
					<a href="${URLHelper.getProductDetailPath(p.oid)}" class="h-100">
						<img class="card-img-top img-thumbnail img-fluid all-product-img" src="${URLHelper.getProductImageResource(p, Product.PRODUCT_IMG)}" alt="${p.name}">
					</a>
					<div class="card-body pt-md-1 text-center">
						<div>
							<a href="${URLHelper.getProductDetailPath(p.oid)}" class="card-link text-dark">${m:esc(p.name)} </a>
						</div>
						<div class="all-price">
							${m:esc(p.price)}${m:esc(m:rs('iplass-wtp-messages', 'samples.ec01.all.yen'))}
						</div>
					</div>
				</div>
			</div>
		</c:forEach>
		</div>
	</div>
</div>
<script type="text/javascript">
function inputCartInfo(productId) {
    var param = "{\"productId\":\"" + productId + "\"}";
    $.ajax({
        type: "POST",
        contentType: "application/json",
		url:"${m:tcPath()}/api/samples/ec01/cart/inputCartInfo",
		dataType: "json",
		data: param,
		success: function(commandResult){
    		if (commandResult.exceptionType != null) {
				alert("${m:rs('iplass-wtp-messages', 'samples.ec01.product.detail.jsError')}" + commandResult.exceptionType + "\\n" + commandResult.exceptionMessage);
				return;
			}
    		if(commandResult.status == "SUCCESS"){
				var totalAmount = commandResult.totalAmount;
				// カートに入れた商品件数の更新
				refreshCartTotalAmount(totalAmount);
				// カートの中を見るなどのボタンの表示
				$("div[cart-data='addToCart']").addClass("d-none");
				$("div[cart-data='viewCart']").removeClass("d-none");
    		}
		}
	});
}
</script>