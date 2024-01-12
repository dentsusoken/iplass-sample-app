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
<%@ page import="org.iplass.mtp.web.template.TemplateUtil"%>
<%@ page import="samples.ec01.utils.URLHelper" %>
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
	<!-- Optional meta tags -->
	<meta name="keywords" content="キーワードその1,キーワードその2,キーワードその3,キーワードその4,キーワードその5" />
	<meta name="description" content="ご自身のWebサイトの説明を記載します" />
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="${m:esc(staticContentPath)}/styles/bootstrap.min.css?cv=<%=TemplateUtil.getAPIVersion()%>">
    <link rel="stylesheet" href="${m:esc(staticContentPath)}/styles/open-iconic-bootstrap.min.css?cv=<%=TemplateUtil.getAPIVersion()%>">
    <link rel="stylesheet" href="${m:esc(staticContentPath)}/styles/bookstore.css?cv=<%=TemplateUtil.getAPIVersion()%>">
</head>

<body>
	 <div class="container">
        <div class="row">
            <div class="col-12">
                <nav class="navbar navbar-expand-md navbar-light layout-navbar">
                    <!-- 大きい画面で表示 start-->
                    <div class="d-none d-md-block">
                        <form class="form-inline" action="${m:tcPath()}/samples/ec01/search/doSearch"  method="post">
                            <button class="btn  bg-transparent btn-lg" type="submit">
                                <span class="oi oi-magnifying-glass" title="search" aria-hidden="true"></span>
                            </button>
                            <input type="text" class="form-control mr-md-2 layout-navbar-text border-0" name="productName" placeholder="Search">
                        </form>
                    </div>
                    <!-- 大きい画面で表示 end-->
                    <!-- 小さい画面で表示  start-->
                    <div class="d-block d-md-none">
	                    <a class="btn bg-transparent btn-lg text-dark" href="${m:tcPath()}/samples/ec01/search/search">
	                        <span class="oi oi-magnifying-glass" title="search" aria-hidden="true"></span>
	                    </a>
                    </div>
                    <!-- 小さい画面で表示  end-->
                    <!-- 大きい画面で表示 start-->
                    <div class="d-none d-md-block">
                        <ul class="navbar-nav mr-auto">
                            <li class="nav-item">
                                <a class="btn btn-lg text-dark" href="${m:tcPath()}/samples/ec01/cart/cartInfo">
                                    <span class="oi oi-cart"></span>
                                        ${m:rs('iplass-wtp-messages', 'samples.ec01.layout.defaultLayout.cart')}
                                    <span cart-data="totalAmount">(${m:esc(totalAmount)})</span>
                                </a>
                            </li>
                            <li class="nav-item">
                                <a class="btn btn-lg text-dark" href="${m:tcPath()}/samples/ec01/member/login">
                                    ${m:rs('iplass-wtp-messages', 'samples.ec01.layout.defaultLayout.checkOut')}
                                </a>
                            </li>
                        </ul>
                    </div>
                    <!-- 大きい画面で表示 end-->
                    <!-- 小さい画面で表示  start-->
                    <div class="d-block d-md-none">
                        <ul class="navbar-nav mr-auto">
                            <li class="nav-item">
                                <a class="btn btn-lg text-dark" href="${m:tcPath()}/samples/ec01/cart/cartInfo">
                                    <span class="oi oi-cart"></span><span cart-data="totalAmount">(${m:esc(totalAmount)})</span>
                                </a>
                            </li>
                        </ul>
                    </div>
                    <!-- 小さい画面で表示  end-->
                </nav>
            </div>
        </div>
        <div class="row">
            <div class="col-12 border-top text-center text-md-left">
                <p class="h1 font-weight-bold my-3">iPLAss.</p>
            </div>
        </div>
        <!-- 小さい画面で表示  start-->
        <div class="row">
            <div class="col-12 d-block d-md-none">
                <div class="navbar-light border-top text-center py-2">
                    <button class="navbar-toggler hidden-md-up border-0" type="button" data-toggle="collapse" data-target="#collapsibleNavId"
                        aria-controls="collapsibleNavId" aria-expanded="false" aria-label="Toggle navigation">
                        <span class="navbar-toggler-icon"></span>
                    </button>
                    <div class="collapse navbar-collapse" id="collapsibleNavId">
                        <ul class="list-group mt-2 d-block d-md-none">
                            <li class="list-group-item border-0">
                                <a class="nav-link text-dark font-weight-bold" href="${m:tcPath()}/samples/ec01/top">${m:rs('iplass-wtp-messages', 'samples.ec01.layout.defaultLayout.home')}</a>
                            </li>
                            <li class="list-group-item border-0">
                            	<a class="nav-link text-dark" href="${URLHelper.getCategoryPath('all')}">${m:rs('iplass-wtp-messages', 'samples.ec01.product.category.all')}</a>
                            </li>
							<c:forEach var="category" items="${categoryList}">
								<li class="list-group-item border-0">
									<a class="nav-link text-dark" href="${URLHelper.getCategoryPath(category.oid)}">${category.name}</a>
								</li>
							</c:forEach>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
        <!-- 小さい画面で表示 end-->
        <div class="row layout-container">
            <div class="col-md-3 d-none d-md-block">
                <div class="row">
                    <div class="col-12">
                        <div class="list-group list-group-item-dark list-group-flush">
                            <a href="${m:tcPath()}/samples/ec01/top" class="list-group-item list-group-item-action font-weight-bold border-top">${m:rs('iplass-wtp-messages', 'samples.ec01.layout.defaultLayout.home')}</a>
							<c:forEach var="category" items="${categoryList}">
								<a href="${URLHelper.getCategoryPath(category.oid)}" class="list-group-item list-group-item-action">${category.name}</a>
							</c:forEach>
                        </div>
                    </div>
                </div>
            </div>
            <div class="col-sm-12 col-md-9">
                <m:renderContent />
            </div>
        </div>
        <hr>
        <div class="row">
            <div class="col-12">
                <ul class="list-group">
                    <li class="list-group-item border-0 font-weight-bold">Links</li>
                    <li class="list-group-item border-0">
                        <a href="${m:tcPath()}/samples/ec01/news/newInfo" class="text-dark">${m:rs('iplass-wtp-messages','samples.ec01.layout.defaultLayout.news')}</a>
                    </li>
                    <li class="list-group-item border-0">
                        <a href="${m:tcPath()}/samples/ec01/search/search" class="text-dark">${m:rs('iplass-wtp-messages','samples.ec01.layout.defaultLayout.search')}</a>
                    </li>
                    <li class="list-group-item border-0">
                        <a href="${m:tcPath()}/samples/ec01/inquiry/inquiry" class="text-dark">${m:rs('iplass-wtp-messages','samples.ec01.layout.defaultLayout.inquiry')}</a>
                    </li>
                    <li class="list-group-item border-0">
                        <a href="${m:tcPath()}/samples/ec01/shop/tradeLaw" class="text-dark">${m:rs('iplass-wtp-messages','samples.ec01.layout.defaultLayout.SCTAInfo')}</a>
                    </li>
                </ul>
            </div>
        </div>
        <div class="row layout-footer bg-dark">
            <div class="col-md-12 text-light text-left">Copyright（c）2018 MTP Demo Store all rights reserved.</div>
        </div>
    </div>
    <!-- jQuery first, then Tether, then Bootstrap JS. -->
    <script type="text/javascript" src="${m:esc(staticContentPath)}/scripts/jquery-3.3.1.min.js?cv=<%=TemplateUtil.getAPIVersion()%>"></script>
    <script type="text/javascript" src="${m:esc(staticContentPath)}/scripts/popper.min.js?cv=<%=TemplateUtil.getAPIVersion()%>"></script>
    <script type="text/javascript" src="${m:esc(staticContentPath)}/scripts/bootstrap.min.js?cv=<%=TemplateUtil.getAPIVersion()%>"></script>
    <script>
    	// カートに入れた商品件数の更新
    	function refreshCartTotalAmount(totalAmount){
    		$("span[cart-data='totalAmount']").html("("+totalAmount+")");
    	}
    </script>
</body>
</html>