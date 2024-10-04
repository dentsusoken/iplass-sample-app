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
<%@ page import="samples.ec01.utils.Consts" %>
<%@ page import="samples.ec01.utils.URLHelper" %>
<%@ page import="samples.ec01.bean.InquiryBean" %>
<%@ page import="samples.ec01.command.inquiry.RegistInquiryCommand" %>
<%@ taglib prefix="m" uri="http://iplass.org/tags/mtp"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%
InquiryBean inquiryBean = (InquiryBean)request.getAttribute(RegistInquiryCommand.RESULT_INQUIRY_BEAN);
if (inquiryBean == null) {
	request.setAttribute(RegistInquiryCommand.RESULT_INQUIRY_BEAN, new InquiryBean());
}
%>
<div class="row">
    <div class="col-12">
        <div class="border-top"></div>
        <nav class="breadcrumb all-breadcrumb">
            <a class="breadcrumb-item text-primary" href="${m:tcPath()}/samples/ec01/top">
            	${m:rs('iplass-wtp-messages', 'samples.ec01.all.breadcrumb.home')}
            </a>
            <span class="breadcrumb-item active">
            	${m:rs('iplass-wtp-messages', 'samples.ec01.inquiry.regist.title')}
            </span>
        </nav>
    </div>
    <div class="col-12">
        <span class="h4">${m:rs('iplass-wtp-messages', 'samples.ec01.inquiry.regist.title')}</span>
        <m:bind bean="${inquiryBean}">
        <form class="custom-form mt-3" action="${m:tcPath()}/samples/ec01/inquiry/doInquiry" method="post">
        	<input type="hidden" name="_t" value="${m:token()}">
            <div class="form-group row">
                <div class="col-12 col-md-6 mt-3">
                    <div>
                    	<m:bind prop="familyName">
	                         <label for="${name}" class="col-form-label label-hidden">${m:rs('iplass-wtp-messages', 'samples.ec01.inquiry.regist.familyName')}</label>
	                         <input type="text" class="form-control border rounded input-hint-visible" name="${name}" value="${value}" placeholder="${m:rs('iplass-wtp-messages', 'samples.ec01.inquiry.regist.familyName')}">
	                         <small class="form-text text-danger"><m:errors /></small>
                        </m:bind>
                    </div>
                </div>
                <div class="col-12 col-md-6 mt-3">
                    <div>
                		<m:bind prop="firstName">
                        	<label for="${name}" class="col-form-label label-hidden">${m:rs('iplass-wtp-messages', 'samples.ec01.inquiry.regist.firstName')}</label>
                        	<input type="text" class="form-control border rounded input-hint-visible" name="${name}" value="${value}" placeholder="${m:rs('iplass-wtp-messages', 'samples.ec01.inquiry.regist.firstName')}">
                        	<small class="form-text text-danger"><m:errors /></small>
                        </m:bind>
                    </div>
                </div>
                <% if (Consts.LANGUAGE_JA.equals(TemplateUtil.getLanguage()) || TemplateUtil.getLanguage() == null) { %>
                <div class="col-12 col-md-6 mt-3">
                    <div>
                    	<m:bind prop="familyNameKana">
                        	<label for="${name}" class="col-form-label label-hidden">${m:rs('iplass-wtp-messages', 'samples.ec01.inquiry.regist.familyNameKana')}</label>
                        	<input type="text" class="form-control border rounded input-hint-visible" name="${name}" value="${value}" placeholder="${m:rs('iplass-wtp-messages', 'samples.ec01.inquiry.regist.familyNameKana')}">
                        	<small class="form-text text-danger"><m:errors /></small>
                        </m:bind>
                    </div>
                </div>
                <div class="col-12 col-md-6 mt-3">
                    <div>
                    	<m:bind prop="firstNameKana">
                        	<label for="${name}" class="col-form-label label-hidden">${m:rs('iplass-wtp-messages', 'samples.ec01.inquiry.regist.firstNameKana')}</label>
                        	<input type="text" class="form-control border rounded input-hint-visible" name="${name}" value="${value}" placeholder="${m:rs('iplass-wtp-messages', 'samples.ec01.inquiry.regist.firstNameKana')}">
                        	<small class="form-text text-danger"><m:errors /></small>
                        </m:bind>
                    </div>
                </div>
                <% } %>
                <div class="col-12 col-md-12 mt-3">
                    <div>
                    	<m:bind prop="mail">
                        	<label for="${name}" class="col-form-label label-hidden">${m:rs('iplass-wtp-messages', 'samples.ec01.inquiry.regist.mail')}</label>
                        	<input type="text" class="form-control border rounded input-hint-visible" name="${name}" value="${value}" placeholder="${m:rs('iplass-wtp-messages', 'samples.ec01.inquiry.regist.mail')}">
                        	<small class="form-text text-danger"><m:errors /></small>
                        </m:bind>
                    </div>
                </div>
                <div class="col-12 col-md-12 mt-3">
                    <div>
                    	<m:bind prop="content">
                        	<label for="${name}" class="col-form-label label-hidden">${m:rs('iplass-wtp-messages', 'samples.ec01.inquiry.regist.content')}</label>
                        	<textarea class="form-control" name="${name}" rows="5" placeholder="${m:rs('iplass-wtp-messages', 'samples.ec01.inquiry.regist.content')}" >${value}</textarea>
                        	<small class="form-text text-danger"><m:errors /></small>
                        </m:bind>
                    </div>
                </div>
            </div>
            <div class="form-group mt-4">
                <div class="col-12 text-center">
                    <button type="submit" class="btn btn-dark">
                    	${m:rs('iplass-wtp-messages', 'samples.ec01.inquiry.regist.doRegist')}
                    </button>
                </div>
            </div>
        </form>
        </m:bind>
    </div>
</div>
<script src="${m:esc(staticContentPath)}/scripts/custom.js?cv=<%=TemplateUtil.getAPIVersion()%>" type="text/javascript"></script>
<script type="text/javascript">
	window.onload = function(){
		initFormInputText(".custom-form");
	};
</script>