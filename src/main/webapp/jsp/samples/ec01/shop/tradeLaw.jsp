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
<%@ page import="org.iplass.mtp.web.template.TemplateUtil"%>
<%@ page import="samples.ec01.entity.Shop"%>
<%@ page import="samples.ec01.entity.TradeLaw"%>
<%@ taglib prefix="m" uri="http://iplass.org/tags/mtp"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<div class="row">
    <div class="col-12">
        <div class="border-top"></div>
        <nav class="breadcrumb all-breadcrumb">
            <a class="breadcrumb-item text-primary" href="${m:tcPath()}/samples/ec01/top">
            	${m:rs('iplass-wtp-messages', 'samples.ec01.all.breadcrumb.home')}
            </a>
            <span class="breadcrumb-item active">
            	${m:rs('iplass-wtp-messages', 'samples.ec01.layout.defaultLayout.SCTAInfo')}
            </span>
        </nav>
    </div>
    <div class="col-12">
        <span class="h4">
        	${m:rs('iplass-wtp-messages', 'samples.ec01.layout.defaultLayout.SCTAInfo')}
        </span>
        <div class="form-group mt-3">
            <div class="alert alert-info w-100" role="alert">
                <p class="mb-0">
                    <strong>
                        ${m:esc(shopInfo.shopName)}
                    </strong>
                </p>
                <p class="mb-0">
                    <strong>
                        ${m:esc(shopInfo.shopNameSub)}
                    </strong>
                </p>
            </div>
            <div class="card col-12 bg-light">
                <div class="card-body">
                    <div class="row mt-3 border-bottom">
                        <div class="col-12 col-md-4 fw-bold">
                        	<span class="text-muted">
                        		${m:rs('iplass-wtp-messages', 'samples.ec01.shop.tradeLaw.dealer')}
                        	</span>
                        </div>
                        <div class="col-12 col-md-8">
                        	${m:esc(tradeLaw.dealer)}
                        </div>
                    </div>
                    <div class="row mt-3 border-bottom">
                        <div class="col-12 col-md-4 fw-bold">
                        	<span class="text-muted">
                        		${m:rs('iplass-wtp-messages', 'samples.ec01.shop.tradeLaw.administrator')}
                        	</span>
                        </div>
                        <div class="col-12 col-md-8">
                        	${m:esc(tradeLaw.administrator)}
                        </div>
                    </div>
                    <div class="row mt-3 border-bottom">
                        <div class="col-12 col-md-4 fw-bold">
                        	<span class="text-muted">
                        		${m:rs('iplass-wtp-messages', 'samples.ec01.shop.tradeLaw.postNo')}
                        	</span>
                        </div>
                        <div class="col-12 col-md-8">
                        	<c:forEach var="s" items="${tradeLaw.postNo}">
                        		${m:esc(s)}&nbsp;
                        	</c:forEach>
                        </div>
                    </div>
                    <div class="row mt-3 border-bottom">
                        <div class="col-12 col-md-4 fw-bold">
                        	<span class="text-muted">
                        		${m:rs('iplass-wtp-messages', 'samples.ec01.shop.tradeLaw.address')}
                        	</span>
                        </div>
                        <div class="col-12 col-md-8">
                            <c:forEach var="s" items="${tradeLaw.address}">
                        		${m:esc(s)}&nbsp;
                        	</c:forEach>
                        </div>
                    </div>
                    <div class="row mt-3 border-bottom">
                        <div class="col-12 col-md-4 fw-bold">
                        	<span class="text-muted">
                        		${m:rs('iplass-wtp-messages', 'samples.ec01.shop.tradeLaw.tel')}
                        	</span>
                        </div>
                        <div class="col-12 col-md-8">
                            <c:forEach var="s" items="${tradeLaw.tel}">
                        		${m:esc(s)}&nbsp;
                        	</c:forEach>
                        </div>
                    </div>
                    <div class="row mt-3 border-bottom">
                        <div class="col-12 col-md-4 fw-bold">
                        	<span class="text-muted">
                        		${m:rs('iplass-wtp-messages', 'samples.ec01.shop.tradeLaw.fax')}
                        	</span>
                        </div>
                        <div class="col-12 col-md-8">
                            <c:forEach var="s" items="${tradeLaw.fax}">
                        		${m:esc(s)}&nbsp;
                        	</c:forEach>
                        </div>
                    </div>
                    <div class="row mt-3 border-bottom">
                        <div class="col-12 col-md-4 fw-bold">
                        	<span class="text-muted">
                        		${m:rs('iplass-wtp-messages', 'samples.ec01.shop.tradeLaw.mail')}
                        	</span>
                        </div>
                        <div class="col-12 col-md-8">
                        	${m:esc(tradeLaw.mail)}
                        </div>
                    </div>
                    <div class="row mt-3 border-bottom">
                        <div class="col-12 col-md-4 fw-bold"><span class="text-muted">URL</span></div>
                        <div class="col-12 col-md-8">
                        	${m:esc(tradeLaw.url)}
                        </div>
                    </div>
                    <div class="row mt-3 border-bottom">
                        <div class="col-12 col-md-4 fw-bold">
                        	<span class="text-muted">
                        		${m:rs('iplass-wtp-messages', 'samples.ec01.shop.tradeLaw.feeRequired')}
                        	</span>
                        </div>
                        <div class="col-12 col-md-8">${m:esc(tradeLaw.feeRequired)}</div>
                    </div>
                    <div class="row mt-3 border-bottom">
                        <div class="col-12 col-md-4 fw-bold">
                        	<span class="text-muted">
                        		${m:rs('iplass-wtp-messages', 'samples.ec01.shop.tradeLaw.orderingMethod')}
                        	</span>
                        </div>
                        <div class="col-12 col-md-8">
                        	${m:esc(tradeLaw.howToOrder)}
                        </div>
                    </div>
                    <div class="row mt-3 border-bottom">
                        <div class="col-12 col-md-4 fw-bold">
                        	<span class="text-muted">
                        		${m:rs('iplass-wtp-messages', 'samples.ec01.shop.tradeLaw.paymentMethod')}
                        	</span>
                        </div>
                        <div class="col-12 col-md-8">${m:esc(tradeLaw.howToPayment)}</div>
                    </div>
                    <div class="row mt-3 border-bottom">
                        <div class="col-12 col-md-4 fw-bold">
                        	<span class="text-muted">
                        		${m:rs('iplass-wtp-messages', 'samples.ec01.shop.tradeLaw.paymentDate')}
                        	</span>
                        </div>
                        <div class="col-12 col-md-8">${m:esc(tradeLaw.paymentDeadline)}</div>
                    </div>
                    <div class="row mt-3 border-bottom">
                        <div class="col-12 col-md-4 fw-bold">
                        	<span class="text-muted">
                        		${m:rs('iplass-wtp-messages', 'samples.ec01.shop.tradeLaw.deliveryTime')}
                        	</span>
                        </div>
                        <div class="col-12 col-md-8">${m:esc(tradeLaw.deliveryTime)}</div>
                    </div>
                    <div class="row mt-3 border-bottom">
                        <div class="col-12 col-md-4 fw-bold">
                        	<span class="text-muted">
                        		${m:rs('iplass-wtp-messages', 'samples.ec01.shop.tradeLaw.exchanged')}
                        	</span>
                        </div>
                        <div class="col-12 col-md-8">
                        	${m:esc(tradeLaw.exchanged)}
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="mt-5">
	      <div class="col-12 mt-5 text-center">
	        <a class="btn btn-dark" href="${m:tcPath()}/samples/ec01/top">${m:rs('iplass-wtp-messages', 'samples.ec01.shop.tradeLaw.back')}</a>
	      </div>
        </div>
    </div>
</div>