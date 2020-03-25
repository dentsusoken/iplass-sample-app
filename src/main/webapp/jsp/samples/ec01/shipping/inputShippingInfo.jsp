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
<%@ taglib prefix="m" uri="http://iplass.org/tags/mtp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<m:bind bean="${shippingBean}">
 <form class="custom-form" action="${m:tcPath()}/samples/ec01/shipping/confirmShippingInfo" method="post">
 <input type="hidden" name="_t" value="${m:token()}">
     <div class="form-group row">
         <div class="col-12 d-none d-md-block">
             <span class="h4"> </span>
             <nav class="breadcrumb all-breadcrumb">
                 <a class="breadcrumb-item text-primary" href="${m:tcPath()}/samples/ec01/top">
                 	${m:rs('iplass-wtp-messages', 'samples.ec01.all.breadcrumb.home')}
                 </a>
                 <span class="breadcrumb-item active">
                 	${m:rs('iplass-wtp-messages', 'samples.ec01.shipping.input.title')}
                 </span>
             </nav>
         </div>
         <div class="col-12 col-md-12">
             <h5>
             	${m:rs('iplass-wtp-messages', 'samples.ec01.shipping.input.title')}
             </h5>
         </div>
         <div class="col-12 col-md-12 mt-3">
             <div>
                 <m:bind prop="mail">
	                 <label for="${name}" class="col-form-label label-hidden">
	                 	${m:rs('iplass-wtp-messages', 'samples.ec01.shipping.input.mail')}
	                 </label>
                 	<input type="text" class="form-control border rounded input-hint-visible" name="${name}" value="${value}" placeholder="${m:rs('iplass-wtp-messages', 'samples.ec01.shipping.input.mail')}">
                 	<small class="form-text text-danger"><m:errors /></small>
                 </m:bind>
             </div>
         </div>
         <div class="col-12 col-md-6 mt-3">
             <div>
                 <m:bind prop="familyName">
	                 <label for="${name}" class="col-form-label label-hidden">
	                 	${m:rs('iplass-wtp-messages', 'samples.ec01.shipping.input.familyName')}
	                 </label>
                 	<input type="text" class="form-control border rounded input-hint-visible" name="${name}" value="${value}" placeholder="${m:rs('iplass-wtp-messages', 'samples.ec01.shipping.input.familyName')}">
                 	<small class="form-text text-danger"><m:errors /></small>
                 </m:bind>
             </div>
         </div>
         <div class="col-12 col-md-6 mt-3">
             <div>
                 <m:bind prop="firstName">
	                 <label for="${name}" class="col-form-label label-hidden">
	                 	${m:rs('iplass-wtp-messages', 'samples.ec01.shipping.input.firstName')}
	                 </label>
                 	<input type="text" class="form-control border rounded input-hint-visible" name="${name}" value="${value}" placeholder="${m:rs('iplass-wtp-messages', 'samples.ec01.shipping.input.firstName')}">
                 	<small class="form-text text-danger"><m:errors /></small>
                 </m:bind>
             </div>
         </div>
         <% if (Consts.LANGUAGE_JA.equals(TemplateUtil.getLanguage()) || TemplateUtil.getLanguage() == null) { %>
         <div class="col-12 col-md-6 mt-3">
             <div>
                 <m:bind prop="familyNameKana">
	                 <label for="${name}" class="col-form-label label-hidden">
	                 	${m:rs('iplass-wtp-messages', 'samples.ec01.shipping.input.familyNameKana')}
	                 </label>
                 	<input type="text" class="form-control border rounded input-hint-visible" name="${name}" value="${value}" placeholder="${m:rs('iplass-wtp-messages', 'samples.ec01.shipping.input.familyNameKana')}">
                 	<small class="form-text text-danger"><m:errors /></small>
                 </m:bind>
             </div>
         </div>
         <div class="col-12 col-md-6 mt-3">
             <div>
                 <m:bind prop="firstNameKana">
	                 <label for="${name}" class="col-form-label label-hidden">
	                 	${m:rs('iplass-wtp-messages', 'samples.ec01.shipping.input.firstNameKana')}
	                 </label>
                 	<input type="text" class="form-control border rounded input-hint-visible" name="${name}" value="${value}" placeholder="${m:rs('iplass-wtp-messages', 'samples.ec01.shipping.input.firstNameKana')}">
                 	<small class="form-text text-danger"><m:errors /></small>
                 </m:bind>
             </div>
         </div>
         <% } %>
         <div class="col-12 col-md-12 mt-3">
             <div>
                 <m:bind prop="address">
	                 <label for="${name}" class="col-form-label label-hidden">
	                 	${m:rs('iplass-wtp-messages', 'samples.ec01.shipping.input.address')}
	                 </label>
	                 <input type="text" class="form-control border rounded input-hint-visible" name="${name}" value="${value}" placeholder="${m:rs('iplass-wtp-messages', 'samples.ec01.shipping.input.address')}">
	                 <small class="form-text text-danger"><m:errors /></small>
                 </m:bind>
             </div>
         </div>
         <div class="col-12 col-md-12 mt-3">
             <div>
                 <m:bind prop="tel">
	                 <label for="${name}" class="col-form-label label-hidden">
	                 	${m:rs('iplass-wtp-messages', 'samples.ec01.shipping.input.tel')}
	                 </label>
                 	<input type="text" class="form-control border rounded input-hint-visible" name="${name}" value="${value}" placeholder="${m:rs('iplass-wtp-messages', 'samples.ec01.shipping.input.tel')}">
	                <small class="form-text text-danger"><m:errors /></small>
                 </m:bind>
             </div>
         </div>
         <div class="col-12 mt-4 text-center clearfix">
             <div class="float-md-right">
                 <button type="submit" class="btn btn-dark btn-lg">
                 	${m:rs('iplass-wtp-messages', 'samples.ec01.shipping.input.delivery')}
                 </button>
             </div>
             <div class="text-md-left mt-2">
                 <a class="btn btn-sm" href="${m:tcPath()}/samples/ec01/cart/cartInfo" role="button">&lt;
                 	${m:rs('iplass-wtp-messages', 'samples.ec01.shipping.input.return')}
                 </a>
             </div>
         </div>
     </div>
 </form>
</m:bind>
<script src="${m:esc(staticContentPath)}/scripts/custom.js?cv=<%=TemplateUtil.getAPIVersion()%>" type="text/javascript"></script>
<script type="text/javascript">
	window.onload = function(){
		initFormInputText(".custom-form");
	};
</script>