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
<%@ page import="samples.ec01.utils.URLHelper"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="m" uri="http://iplass.org/tags/mtp"%>

<div class="row">
    <div class="col-12">
        <div class="border-top"></div>
        <nav class="breadcrumb all-breadcrumb">
            <a class="breadcrumb-item text-primary" href="${m:tcPath()}/samples/ec01/top">
            	${m:rs('iplass-wtp-messages', 'samples.ec01.all.breadcrumb.home')}
            </a>
            <span class="breadcrumb-item active" >
            	${m:rs('iplass-wtp-messages', 'samples.ec01.member.login.title')}
            </span>
        </nav>
    </div>
    <div class="col-12">
        <span class="h4">
        	${m:rs('iplass-wtp-messages', 'samples.ec01.member.login.title')}
        </span>
        <form class="custom-form mt-3" action="${m:tcPath()}/samples/ec01/member/doLogin" method="post">
		<input type="hidden" name="_t" value="${m:token()}">
            <div class="form-group row">
                <div class="col-12">
                    <div>
                        <label for="id" class="col-form-label label-hidden">
                        	${m:rs('iplass-wtp-messages', 'samples.ec01.member.login.userid')}
                        </label>
                        <input type="text" name="id" class="form-control border rounded input-hint-visible" placeholder="${m:rs('iplass-wtp-messages', 'samples.ec01.member.login.userid')}">
                    </div>
                </div>
                <div class="col-12 mt-3">
                    <div>
                        <label for="password" class="col-form-label label-hidden">
                        	${m:rs('iplass-wtp-messages', 'samples.ec01.member.login.password')}
                        </label>
                        <input type="password" name="password" class="form-control border rounded input-hint-visible" placeholder="${m:rs('iplass-wtp-messages', 'samples.ec01.member.login.password')}">
                    </div>
                </div>
            </div>
            <div class="form-group mt-5">
                <div class="col-12 text-center">
                    <button type="submit" class="btn btn-dark">
                    	${m:rs('iplass-wtp-messages', 'samples.ec01.member.login.doLogin')}
                    </button>
                </div>
            </div>
           	<div class="col-12 text-start">
           		<p>
           			${m:rs('iplass-wtp-messages', 'samples.ec01.member.login.registMemberInfo')}
           		</p>
           		<p>
           			<a class="text-dark" href="${m:tcPath()}/samples/ec01/member/inputMemberInfo">
           				${m:rs('iplass-wtp-messages', 'samples.ec01.member.login.registMember')}
           			</a>
           		</p>
           		<p>
           			${m:rs('iplass-wtp-messages', 'samples.ec01.member.login.payInfo')}
           		</p>
           		<p>
           			<a class="text-dark" href="${m:tcPath()}/samples/ec01/shipping/inputShippingInfoNoMember">
           				${m:rs('iplass-wtp-messages', 'samples.ec01.member.login.pay')}
           			</a>
           		</p>
           	</div>
        </form>
    </div>
</div>
<script src="${m:esc(staticContentPath)}/scripts/custom.js?cv=<%=TemplateUtil.getAPIVersion()%>" type="text/javascript"></script>
<script type="text/javascript">
	window.onload = function() {
		initFormInputText(".custom-form");
	}
</script>

