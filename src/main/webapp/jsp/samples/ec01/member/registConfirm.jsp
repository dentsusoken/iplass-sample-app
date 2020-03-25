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
<%@ page import="org.iplass.mtp.web.template.TemplateUtil"%>
<%@ page import="samples.ec01.utils.Consts" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="m" uri="http://iplass.org/tags/mtp"%>

<div class="row">
    <div class="col-12">
        <div class="border-top"></div>
        <nav class="breadcrumb all-breadcrumb">
            <a class="breadcrumb-item text-primary" href="${m:tcPath()}/samples/ec01/top">
            	${m:rs('iplass-wtp-messages', 'samples.ec01.all.breadcrumb.home')}
            </a>
            <a class="breadcrumb-item text-primary" href="${m:tcPath()}/samples/ec01/member/inputMemberInfo">
            	${m:rs('iplass-wtp-messages', 'samples.ec01.member.regist.title')}
            </a>
            <span class="breadcrumb-item active">
            	${m:rs('iplass-wtp-messages', 'samples.ec01.member.registConfirm.title')}
            </span>
        </nav>
    </div>
    <div class="col-12">
        <span class="h4">
        	${m:rs('iplass-wtp-messages', 'samples.ec01.confirmRegist.title')}
        </span>
        <form class="custome-confirm-form mt-3" action="${m:tcPath()}/samples/ec01/member/registMemberInfo" method="post">
        <input type="hidden" name="_t" value="${m:token()}">
            <div class="form-group">
                <div class="alert alert-info w-100" role="alert">
                    <p class="mb-0">
                        <strong>
                            ${m:rs('iplass-wtp-messages', 'samples.ec01.member.registConfirm.registMsg.p1')}
                        </strong>
                    </p>
                    <p class="mb-0">
                        <strong>
                            ${m:rs('iplass-wtp-messages', 'samples.ec01.member.registConfirm.registMsg.p2')}
                        </strong>
                    </p>
                </div>
                <div class="card col-12 bg-light">
                    <div class="card-body">
                        <div class="row mt-3 border-bottom">
                            <div class="col-12 col-md-4">
                            	<span class="text-muted font-weight-bold">${m:rs('iplass-wtp-messages', 'samples.ec01.member.regist.userId')}</span>
                            </div>
                            <div class="col-12 col-md-8">${m:esc(userBean.userId)}</div>
                        </div>
                        <div class="row mt-3 border-bottom">
                            <div class="col-12 col-md-4">
                            	<span class="text-muted  font-weight-bold">${m:rs('iplass-wtp-messages', 'samples.ec01.member.registConfirm.fullName')}</span>
                            </div>
                            <div class="col-12 col-md-3">
                            	<span class="text-muted  font-weight-bold">${m:rs('iplass-wtp-messages', 'samples.ec01.member.regist.familyName')}</span>
                            	&nbsp;${m:esc(userBean.familyName)}
                            </div>
                            <div class="col-12 col-md-3">
                            	<span class="text-muted  font-weight-bold">${m:rs('iplass-wtp-messages', 'samples.ec01.member.regist.firstName')}</span>
                            	&nbsp;${m:esc(userBean.firstName)}
                            </div>
                        </div>
                        <% if (Consts.LANGUAGE_JA.equals(TemplateUtil.getLanguage()) || TemplateUtil.getLanguage() == null) { %>
                        <div class="row mt-3 border-bottom">
                            <div class="col-12 col-md-4">
                            	<span class="text-muted  font-weight-bold">${m:rs('iplass-wtp-messages', 'samples.ec01.member.registConfirm.fullNameKana')}</span>
                            </div>
                            <div class="col-12 col-md-3">
                            	<span class="text-muted  font-weight-bold">${m:rs('iplass-wtp-messages', 'samples.ec01.member.regist.familyNameKana')}</span>
                            	&nbsp;${m:esc(userBean.familyNameKana)}
                            </div>
                            <div class="col-12 col-md-3">
                            	<span class="text-muted  font-weight-bold">${m:rs('iplass-wtp-messages', 'samples.ec01.member.regist.firstNameKana')}</span>
                            	&nbsp;${m:esc(userBean.firstNameKana)}
                            </div>
                        </div>
                        <% } %>
                        <div class="row mt-3 border-bottom">
                            <div class="col-12 col-md-4">
                            	<span class="text-muted  font-weight-bold">${m:rs('iplass-wtp-messages', 'samples.ec01.member.regist.mail')}</span>
                            </div>
                            <div class="col-12 col-md-8">${m:esc(userBean.mail)}</div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="form-group mt-4">
                <div class="col-12 text-center">
                    <button type="submit" class="btn btn-dark">
                    	${m:rs('iplass-wtp-messages', 'samples.ec01.member.registConfirm.regist')}
                    </button>
                </div>
            </div>
        </form>
    </div>
</div>