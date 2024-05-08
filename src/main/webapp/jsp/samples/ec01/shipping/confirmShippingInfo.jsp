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
<%@ page import="samples.ec01.utils.Consts" %>
<%@ taglib prefix="m" uri="http://iplass.org/tags/mtp"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<form class="custom-form" action="${m:tcPath()}/samples/ec01/shipping/doOrder" method="post">
	<input type="hidden" name="_t" value="${m:token()}">
	<div class="form-group row">
		<div class="col-12 d-none d-md-block">
			<span class="h4"> </span>
			<nav class="breadcrumb all-breadcrumb">
				<a class="breadcrumb-item text-primary" href="${m:tcPath()}/samples/ec01/top">
					${m:rs('iplass-wtp-messages', 'samples.ec01.all.breadcrumb.home')}
				</a>
				<a class="breadcrumb-item text-primary" href="${m:tcPath()}/samples/ec01/shipping/editShippingInfo">
					${m:rs('iplass-wtp-messages', 'samples.ec01.shipping.input.title')}
				</a>
				<span class="breadcrumb-item active" >
					${m:rs('iplass-wtp-messages', 'samples.ec01.shipping.confirm.title')}
				</span>
			</nav>
		</div>
		<div class="col-12 col-md-12">
			<h5>
				${m:rs('iplass-wtp-messages', 'samples.ec01.shipping.confirm.title')}
			</h5>
			<div class="card col bg-light">
				<div div class="card-body pl-0 text-left"
					style="position: relative;">
					<div class="row">
						<div class="col-12 col-md-12 text-muted font-weight-bold">
							${m:rs('iplass-wtp-messages', 'samples.ec01.shipping.confirm.addressInfo')}
						</div>
						<div class="col-12 col-md-4 text-muted font-weight-bold shipping-contract-label">
							${m:rs('iplass-wtp-messages', 'samples.ec01.shipping.input.mail')}
						</div>
						<div class="col-12 col-md-8 shipping-contract-text">
							${m:esc(shippingBean.mail)}
						</div>
						<div class="col-12 col-md-4 text-muted font-weight-bold shipping-contract-label">
							${m:rs('iplass-wtp-messages', 'samples.ec01.shipping.confirm.fullName')}
						</div>
						<div class="col-12 col-md-8 shipping-contract-text">
							${m:esc(shippingBean.familyName)}&nbsp;${m:esc(shippingBean.firstName)}
						</div>
						<% if (Consts.LANGUAGE_JA.equals(TemplateUtil.getLanguage()) || TemplateUtil.getLanguage() == null) { %>
						<div class="col-12 col-md-4 text-muted font-weight-bold shipping-contract-label">
							${m:rs('iplass-wtp-messages', 'samples.ec01.shipping.confirm.fullNameKana')}
						</div>
						<div class="col-12 col-md-8 shipping-contract-text">
							${m:esc(shippingBean.familyNameKana)}&nbsp;${m:esc(shippingBean.firstNameKana)}
						</div>
						<% } %>
						<div>
							<a class="btn btn-link shipping-contract-edit" href="${m:tcPath()}/samples/ec01/shipping/editShippingInfo" role="button">
								${m:rs('iplass-wtp-messages', 'samples.ec01.shipping.confirm.edit')}
							</a>
						</div>
						<div class="col-12 col-md-4 text-muted font-weight-bold shipping-contract-label">
							${m:rs('iplass-wtp-messages', 'samples.ec01.shipping.input.address')}
						</div>
						<div class="col-12 col-md-8 shipping-contract-text">
							${m:esc(shippingBean.address)}
						</div>
						<div class="col-12 col-md-4 text-muted font-weight-bold shipping-contract-label">
							${m:rs('iplass-wtp-messages', 'samples.ec01.shipping.input.tel')}
						</div>
						<div class="col-12 col-md-8 shipping-contract-text">
							${m:esc(shippingBean.tel)}
						</div>
					</div>
				</div>
			</div>
		</div>
		<div class="col-12 mt-4 text-center clearfix">
			<div class="float-md-right">
				<button type="submit" class="btn btn-dark btn-lg">
					${m:rs('iplass-wtp-messages', 'samples.ec01.shipping.confirm.order')}
				</button>
			</div>
			<div class="text-md-left mt-2">
				<a class="btn btn-sm" href="${m:tcPath()}/samples/ec01/shipping/editShippingInfo" role="button">
					&lt;${m:rs('iplass-wtp-messages', 'samples.ec01.shipping.confirm.return')}
				</a>
			</div>
		</div>
	</div>
</form>