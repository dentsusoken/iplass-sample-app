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
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="m" uri="http://iplass.org/tags/mtp"%>

<div class="row">
    <div class="col-12">
        <div class="border-top"></div>
        <nav class="breadcrumb all-breadcrumb">
            <a class="breadcrumb-item text-primary" href="${m:tcPath()}/samples/ec01/top">
            	${m:esc(m:rs('iplass-wtp-messages', 'samples.ec01.all.breadcrumb.home'))}
            </a>
            <span class="breadcrumb-item active" >
            	${m:esc(m:rs('iplass-wtp-messages', 'samples.ec01.member.regist.title'))}
            </span>
            <span class="breadcrumb-item active" >
            	${m:esc(m:rs('iplass-wtp-messages', 'samples.ec01.member.registConfirm.title'))}
            </span>
            <span class="breadcrumb-item active" >
            	${m:esc(m:rs('iplass-wtp-messages', 'samples.ec01.member.registSuccess.title'))}
            </span>
        </nav>
    </div>
    <div class="col-12">
        <span class="h4">
        	${m:esc(m:rs('iplass-wtp-messages', 'samples.ec01.member.registSuccess.title'))}
        </span>
        <div class="mt-3">
            <div class="alert alert-success" role="alert">
                <p class="mb-0">
                    <strong>
						${m:esc(m:rs('iplass-wtp-messages', 'samples.ec01.member.registSuccess.successMsg.p1'))}
                    </strong>
                </p>
                <p class="mb-0">
                    <strong>
 						${m:esc(m:rs('iplass-wtp-messages', 'samples.ec01.member.registSuccess.successMsg.p2'))}
                    </strong>
                </p>
            </div>
        </div>
        <div class="col-12 mt-5 text-center">
            <a class="btn btn-dark" href="${m:tcPath()}/samples/ec01/top">${m:esc(m:rs('iplass-wtp-messages', 'samples.ec01.member.registSuccess.return'))} </a>
        </div>
    </div>
</div>