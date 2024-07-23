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
<%@ page import="samples.ec01.utils.Consts" %>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="m" uri="http://iplass.org/tags/mtp"%>

<div class="row">
    <div class="col-12">
        <div class="border-top"></div>
        <nav class="breadcrumb all-breadcrumb">
            <a class="breadcrumb-item text-primary" href="${m:tcPath()}/samples/ec01/top">
            	${m:rs('iplass-wtp-messages', 'samples.ec01.all.breadcrumb.home')}
            </a>
            <span class="breadcrumb-item active">
            	${m:rs('iplass-wtp-messages', 'samples.ec01.search.title')}
            </span>
        </nav>
    </div>
</div>
<div class="row" id="searchResultDiv">
    <div class="col-12">
        <h4>${m:rs('iplass-wtp-messages', 'samples.ec01.search.title')}</h4>
        <div class="input-group col-12">
            <input type="text" class="form-control" id="productName" placeholder="Search for...">
            <div class="dropdown float-end">
				<button class="btn btn-outline-dark dropdown-toggle" type="button" id="categoryList" data-bs-toggle="dropdown" aria-haspopup="true" aria-expanded="false" category-item-selected="all">
					${m:rs('iplass-wtp-messages', 'samples.ec01.product.category.title')}
				</button>
				<div class="dropdown-menu" aria-labelledby="categoryList">
					<a class="dropdown-item" href="javascript:void(0);" onclick="dropdownSelect(this);" category-item-value="all">
						${m:rs('iplass-wtp-messages', 'samples.ec01.product.category.all')}
					</a>
					<div class="dropdown-divider"></div>
					<c:forEach var="c" items="${categoryList}">
						<a class="dropdown-item" href="javascript:void(0);" onclick="dropdownSelect(this);" category-item-value="${c.oid}">
							${m:esc(c.name)}
						</a>
					</c:forEach>
				</div>
			</div>
            <span class="input-group-btn pl-2">
                <button class="btn btn-secondary" type="button" onclick="fullTextSearch();">
                    <span class="oi oi-magnifying-glass" title="search" aria-hidden="true"></span>
                </button>
            </span>
        </div>
        <div class="input-group col-12">
       		<small id="helpId" class="form-text text-danger"></small>
        </div>
    </div>
</div>
<script type="text/javascript">
function fullTextSearch() {
	var productName = $("#productName").val();
	var categoryOid = $("#categoryList").attr("category-item-selected");
	if(productName == "") {
		$("#helpId").html("${m:rs('iplass-wtp-messages', 'samples.ec01.search.nokeyword')}");
		return false;
	}
    var param = "{\"productName\":\"" + productName + "\",\"categoryOid\":\"" + categoryOid +"\"}";
    $.ajax({
        type: "POST",
        contentType: "application/json",
        url:"${m:tcPath()}/api/samples/ec01/search/fulltextSearch",
       	dataType: "json",
		data: param,
		success: function(commandResult){
    		if (commandResult.exceptionType != null) {
				alert("${m:rs('iplass-wtp-messages', 'samples.ec01.search.jsError')}"+ commandResult.exceptionType +"\\n"+commandResult.exceptionMessage);
				return;
			}
    		if(commandResult.status == "SUCCESS"){
    			if(commandResult.defaultResult != null && commandResult.defaultResult.length > 0){
    				var resultHtml = ListSearchResult(commandResult.defaultResult, productName);
    				$("#searchResultDiv").html(resultHtml);
    			}
    			else{
    				$("#helpId").html("${m:rs('iplass-wtp-messages', 'samples.ec01.search.keyword')}： " + productName + ", " + "${m:rs('iplass-wtp-messages', 'samples.ec01.search.noResult')}");
    			}
    		}
		}
	});
}
function dropdownSelect(item){
	var t = $(item);
	var v = t.attr("category-item-value");
	$("#categoryList").text(t.html()).attr("category-item-selected", v);
}
function ListSearchResult(entities, productName){
	var yen = "${m:rs('iplass-wtp-messages', 'samples.ec01.all.yen')}";
	var html =  "<div class=\"col-12 mb-2\">";
		html += "	<h4>${m:rs('iplass-wtp-messages', 'samples.ec01.search.result')}" + productName + "</h4>";
		html += "</div>";
	for(var i =0; i < entities.length; i++){
		var name = entities[i].name;
		var price = isNaN(entities[i].price)? "" : entities[i].price;
		var imageUrl = "${m:tcPath()}/samples/ec01/resource/bin?id=" + entities[i].productImg.lobId + "&type=${Consts.BIN_TYPE_PRODUCT_IMG}";
		var detailUrl = "${m:tcPath()}/samples/ec01/product/detail?productId=" + entities[i].oid;
		html += "<div class=\"col-12 col-md-4\">";
		html += "	<div class=\"card border-light border-0\">";
		html += "	    <a href=\"" + detailUrl + "\" class=\"h-100\">";
		html += "	       <img class=\"card-img-top img-thumbnail img-fluid all-product-img\" src=" + imageUrl + " alt=\"" + name + "\">";
		html += "	    </a>";
		html += "	    <div class=\"card-body pt-md-1 text-center\">";
		html += "	       <div>";
		html += "	           <a href=\""+ detailUrl +"\" class=\"card-link text-dark\">" + name + "</a>";
		html += "	        </div>";
		html += "	        <div class=\"all-price\">";
		html += "              <span>" + price + "</span>" + yen;
		html += "	        </div>";
		html += "	    </div>";
		html += "	</div>";
		html += "</div>";
	}
	return html;
}
</script>