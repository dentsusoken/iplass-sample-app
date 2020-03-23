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
<%@ page import="org.iplass.mtp.web.template.TemplateUtil"%>
<%@ page import="samples.ec01.utils.URLHelper"%>
<%@ page import="samples.ec01.entity.Product" %>
<%@ taglib prefix="m" uri="http://iplass.org/tags/mtp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html lang="<%=TemplateUtil.getLanguage() %>">
<%
	request.setAttribute("staticContentPath", TemplateUtil.getStaticContentPath());
%>

<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <!-- For Compatible -->
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="${m:esc(staticContentPath)}/styles/bootstrap.min.css?cv=<%=TemplateUtil.getAPIVersion()%>">
    <link rel="stylesheet" href="${m:esc(staticContentPath)}/styles/open-iconic-bootstrap.min.css?cv=<%=TemplateUtil.getAPIVersion()%>">
    <link rel="stylesheet" href="${m:esc(staticContentPath)}/styles/bookstore.css?cv=<%=TemplateUtil.getAPIVersion()%>">
</head>
<body>
    <div class="container">
        <div class="row">
            <div class="col-12 d-block d-md-none pt-5">
                <p class="h4">${m:esc(m:rs('iplass-wtp-messages', 'samples.ec01.layout.shipping.title'))} </p>
            </div>
            <div class="col-12 d-block d-md-none bg-light pt-3 pr-4 clearfix">
                <button id="toggle-btn" class="border-0 bg-transparent float-left" type="button" data-toggle="collapse" data-target="#cartTab"
                    aria-controls="cartTab" aria-expanded="false" aria-label="Toggle">
                    <span class="oi oi-cart"></span>
                    ${m:esc(m:rs('iplass-wtp-messages', 'samples.ec01.layout.shipping.viewCart'))}
                    <span class="oi oi-caret-bottom"></span>
                </button>
                <p class="float-right">
                	${m:esc(cartBean.totalPrice)}	${m:esc(m:rs('iplass-wtp-messages', 'samples.ec01.all.yen'))}
                </p>
            </div>
            <div class="col-12 col-md-6 bg-light collapse d-md-block offset-md-6 pt-md-5 custom-form-right-container" id="cartTab">
                <div>
                    <table class="table custom-cart-table">
                        <tbody>
                        <c:forEach var="it" items="${cartBean.cartItems}">
                            <c:set var="product" value="${productMap[it.productId]}" scope="page" />
                            <tr>
                                <td scope="row">
                                    <div style="width:80px; position:relative">
                                        <img src="${URLHelper.getProductImageResource(product, Product.PRODUCT_IMG)}" class="rounded" alt="${product.name}" />
                                        <span class="badge badge-dark badge-pill">${m:esc(it.value)} </span>
                                    </div>
                                </td>
                                <td>
                                    <p>${m:esc(product.name)} </p>
                                    <p class="cate">${m:esc(product.category.name)} </p>
                                </td>
                                <td class="w-25 text-right">
                                	${m:esc(product.price*it.value)}${m:esc(m:rs('iplass-wtp-messages', 'samples.ec01.all.yen'))}
                                </td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                </div>
                <div>
                    <table class="table custom-total-table">
                        <tbody>
                        	<!-- 商品合計 -->
                            <tr>
                                <td scope="row" class="w-25 text-left">
                                	${m:esc(m:rs('iplass-wtp-messages', 'samples.ec01.layout.shipping.subtotal'))}
                                </td>
                                <td class="w-75">
                                    ${m:esc(cartBean.totalPrice)}${m:esc(m:rs('iplass-wtp-messages', 'samples.ec01.all.yen'))}
                                </td>
                            </tr>
                            <tr>
                                <td scope="row" class="w-25 text-left border-top-0">
                                	${m:esc(m:rs('iplass-wtp-messages', 'samples.ec01.layout.shipping.fee'))}
                                </td>
                                <td class="w-75 border-top-0">--</td>
                            </tr>
                            <tr>
                                <td scope="row" class="w-25 text-left h4">
                                	${m:esc(m:rs('iplass-wtp-messages', 'samples.ec01.layout.shipping.total'))}
                                </td>
                                <td class="w-75 font-weight-bold h4">
                                    ${m:esc(cartBean.totalPrice)}${m:esc(m:rs('iplass-wtp-messages', 'samples.ec01.all.yen'))}
                                </td>
                            </tr>
                        </tbody>
                    </table>
                </div>
            </div>
            <div class="col-12 col-md-6 pt-4 custom-form-left-container">
                <m:renderContent/>
                <div class="row layout-footer shipping-layout-footer">
                    <div class="col-12 text-light text-left bg-dark">Copyright（c）2018 MTP Demo Store all rights reserved.</div>
                </div>
            </div>
        </div>
    </div>
    <!-- jQuery first, then Tether, then Bootstrap JS. -->
    <script src="${m:esc(staticContentPath)}/scripts/jquery-3.3.1.min.js?cv=<%=TemplateUtil.getAPIVersion()%>" type="text/javascript"></script>
    <script src="${m:esc(staticContentPath)}/scripts/popper.min.js?cv=<%=TemplateUtil.getAPIVersion()%>" type="text/javascript"></script>
    <script src="${m:esc(staticContentPath)}/scripts/bootstrap.min.js?cv=<%=TemplateUtil.getAPIVersion()%>" type="text/javascript"></script>
    <script type="text/javascript">
		$("#toggle-btn").click(function() {
		    var s = $(this).children("span").eq(1);
		    s.toggleClass("oi-caret-bottom").toggleClass("oi-caret-top");
		});
    </script>
</body>

</html>