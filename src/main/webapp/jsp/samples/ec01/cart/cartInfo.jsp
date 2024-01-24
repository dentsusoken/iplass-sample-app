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

<%@ page language="java" contentType="text/html; charset=utf-8"	pageEncoding="utf-8"%>
<%@ page import="samples.ec01.utils.URLHelper"%>
<%@ page import="samples.ec01.utils.Consts" %>
<%@ page import="samples.ec01.entity.Product"%>
<%@ page import="samples.ec01.bean.CartBean" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="m" uri="http://iplass.org/tags/mtp"%>

<div class="row">
    <div class="col-12">
        <div class="border-top"></div>
        <nav class="breadcrumb all-breadcrumb">
            <a class="breadcrumb-item text-primary" href="${m:tcPath()}/samples/ec01/top">
            	${m:rs('iplass-wtp-messages', 'samples.ec01.all.breadcrumb.home')}
            </a>
            <span class="breadcrumb-item active">
            	${m:rs('iplass-wtp-messages', 'samples.ec01.cart.info.title')}
            </span>
        </nav>
    </div>
</div>
<%
	CartBean cartBean = (CartBean)request.getSession().getAttribute(Consts.CART_BEAN_SESSION_KEY);
	if( cartBean == null || cartBean.getCartItems().size() == 0) {
%>
<div class="row cart-table">
	<div class="col-12 text-left">
		<p>
			${m:rs('iplass-wtp-messages', 'samples.ec01.cart.info.emptyMsg.p1')}
		</p>
		<p>
			${m:rs('iplass-wtp-messages', 'samples.ec01.cart.info.emptyMsg.p2')}
			<a class="text-dark" href="${m:tcPath()}/samples/ec01/top">
				${m:rs('iplass-wtp-messages', 'samples.ec01.cart.info.emptyMsg.p3')}
			</a>
		</p>
	</div>
</div>
<% } else { %>
<form id="cartInfoForm" action="${m:tcPath()}/samples/ec01/cart/updateCartInfo" method="post">
	<div class="row cart-table">
	    <div class="col-12 d-none d-md-block">
	        <div class="row">
	            <div class="col-md-3 font-weight-bold text-center">${m:rs('iplass-wtp-messages', 'samples.ec01.cart.info.product')}</div>
	            <div class="col-md-3 font-weight-bold"></div>
	            <div class="col-md-2 font-weight-bold">${m:rs('iplass-wtp-messages', 'samples.ec01.cart.info.price')}</div>
	            <div class="col-md-2 font-weight-bold">${m:rs('iplass-wtp-messages', 'samples.ec01.cart.info.quantity')}</div>
	            <div class="col-md-2 font-weight-bold">${m:rs('iplass-wtp-messages', 'samples.ec01.cart.info.subtotal')}</div>
	        </div>
	    </div>
	    <div class="col-12">
	        <!--cart item info start-->
	        <m:bind bean="${cartBean}" >
		        <c:forEach var="it" items="${cartBean.cartItems}" varStatus="stat">
			        <c:set var="product" value="${productMap[it.productId]}" scope="page" />
			        <div class="row mb-4">
			            <div class="col-12 col-md-3 text-center text-md-left cart-table-item">
			                <div class="w-100">
			                    <a class="img-fluid" href="${URLHelper.getProductDetailPath(it.productId)}">
			                        <img src="${URLHelper.getProductImageResource(product, Product.PRODUCT_IMG)}" alt="" class="img-thumbnail w-100">
			                    </a>
			                </div>
			            </div>
			            <div class="col-12 col-md-3 text-center text-md-left cart-table-item">
			                <div class="w-100">
			                    <h6>${m:esc(product.name)}</h6>
			                    <p>
			                        <a class="text-dark" href="${URLHelper.getDeleteCartInfoPath(it.productId)}" role="button">
			                        	${m:rs('iplass-wtp-messages', 'samples.ec01.cart.info.remove')}
			                        </a>
			                    </p>
			                </div>
			            </div>
			            <div class="col-3 d-block d-md-none cart-table-item">
			                <p class="w-100 font-weight-bold">${m:rs('iplass-wtp-messages', 'samples.ec01.cart.info.price')}</p>
			            </div>
			            <div class="col-8 col-md-2 cart-table-item">
			                <p class="w-100">${m:esc(product.price)}${m:rs('iplass-wtp-messages', 'samples.ec01.all.yen')}</p>
			            </div>
			            <div class="col-3 d-block d-md-none cart-table-item">
			                <p class="w-100 font-weight-bold">${m:rs('iplass-wtp-messages', 'samples.ec01.cart.info.quantity')}</p>
			            </div>
			            <div class="col-8 col-md-2 cart-table-item clearfix">
			            	<m:bind prop="cartItems[${stat.index}].value">
				                <div class="w-100">
				                    <input type="text" class="form-control float-right" name="${name}" value="${value}" cart-data="value">
				                </div>
			                </m:bind>
			                <m:bind prop="cartItems[${stat.index}].productId">
				            	<input type="hidden" name="${name}" value="${value}" cart-data="productId">
			                </m:bind>
			            </div>
			            <div class="col-3 d-block d-md-none cart-table-item">
			                <p class="w-100 font-weight-bold">${m:rs('iplass-wtp-messages', 'samples.ec01.cart.info.total')}</p>
			            </div>
			            <div class="col-8 col-md-2 cart-table-item">
			                <p class="w-100">${m:esc(product.price * it.value)}${m:rs('iplass-wtp-messages', 'samples.ec01.all.yen')}</p>
			            </div>
			        </div>
			        <hr class="mb-3">
		        </c:forEach>
	        </m:bind>
	    </div>
	</div>
	<div class="row mt-3">
	    <div class="col-md-12 text-center text-md-right">
	        <p class="font-weight-bold">
	        	${m:rs('iplass-wtp-messages', 'samples.ec01.cart.info.total')}：&nbsp;
	        	${m:esc(cartBean.totalPrice)}${m:rs('iplass-wtp-messages', 'samples.ec01.all.yen')}
	        </p>
	        <button type="button" class="btn btn-outline-dark" onclick="updateCart();return false;">
	            <span class="oi oi-reload" title="${m:rs('iplass-wtp-messages', 'samples.ec01.cart.info.refresh')}" aria-hidden="true" />
	        </button>
	        <a class="btn btn-outline-dark" href="${m:tcPath()}/samples/ec01/top" role="button">
	        	${m:rs('iplass-wtp-messages', 'samples.ec01.cart.info.return')}
	        </a>
	    </div>
	</div>
</form>
<div class="row mt-3">
    <div class="col-md-12 text-center text-md-right">
        <a class="btn btn-dark btn-lg" href="${m:tcPath()}/samples/ec01/member/login" role="button">
        	${m:rs('iplass-wtp-messages', 'samples.ec01.cart.info.checkOut')}
        </a>
    </div>
</div>
<script type="text/javascript">
function updateCart() {
	var err = false;
    $("input[type=text][cart-data=value]").each(function(){
        var str = this.value;
        if(isNaN(str) || !/^[1-9]\d*|0$/.test(str)) {
        	err = true;
        }
    });
    if(err){
        alert("${m:rs('iplass-wtp-messages', 'samples.ec01.cart.info.notNum')}");
    	return false;
    }
    var obj = document.getElementById("cartInfoForm")
    obj.submit();
}
</script>
<% } %>
