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
<%@ taglib prefix="m" uri="http://iplass.org/tags/mtp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<div class="row">
    <div class="col-12 d-none d-md-block">
        <span class="h4"> </span>
        <nav class="breadcrumb all-breadcrumb">
            <a class="breadcrumb-item text-primary" href="${m:tcPath()}/samples/ec01/top"><c:out value="${m:rs('iplass-wtp-messages', 'samples.ec01.all.breadcrumb.home')}" /></a>
            <span class="breadcrumb-item active" >
            	<c:out value="${m:rs('iplass-wtp-messages', 'samples.ec01.shipping.input.title')}"/>
            </span>
            <span class="breadcrumb-item active" >
            	<c:out value="${m:rs('iplass-wtp-messages', 'samples.ec01.shipping.confirm.title')}"/>
            </span>
            <span class="breadcrumb-item active" >
            	<c:out value="${m:rs('iplass-wtp-messages', 'samples.ec01.shipping.success.title')}"/>
            </span>
        </nav>
    </div>
    <div class="col-12 col-md-12">
        <span class="h4">
        	<c:out value="${m:rs('iplass-wtp-messages', 'samples.ec01.shipping.success.title')}"/>
        </span>
        <div class="alert alert-success mt-3" role="alert">
            <p class="mb-0">
                <strong>
                	<c:out value="${m:rs('iplass-wtp-messages', 'samples.ec01.shipping.success.msg.p1')}"/>
                </strong>
            </p>
            <p class="mb-0">
                <strong>
                	<c:out value="${m:rs('iplass-wtp-messages', 'samples.ec01.shipping.success.msg.p2')}"/>
                </strong>
            </p>
        </div>
    </div>
    <div class="col-12 mt-4 text-center clearfix">
        <div class="float-md-right">
            <a class="btn btn-dark" href="${m:tcPath()}/samples/ec01/top">
            	<c:out value="${m:rs('iplass-wtp-messages', 'samples.ec01.shipping.success.return')}"/>
            </a>
        </div>
    </div>
</div>