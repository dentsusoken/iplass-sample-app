/*
 * Copyright (C) 2018 INFORMATION SERVICES INTERNATIONAL - DENTSU, LTD. All Rights Reserved.
 * 
 * Unless you have purchased a commercial license,
 * the following license terms apply:
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 * 
 * You should have received a copy of the GNU Affero General Public License
 * along with this program. If not, see <https://www.gnu.org/licenses/>.
 */

//入力フォームのテキストボックスに入力ヒント用ラベルの表示と非表示を制御するスクリプト 
function initFormInputText(form){
    $(form).find("input[type='text'],input[type='password'],textarea").keyup(function(){
        var t = $(this);
        var l = t.parent().children("label");
        var text = t.val();
        if(text.length > 0){
            t.removeClass("input-hint-visible").addClass("input-hint-hidden");
            l.removeClass("label-hidden").addClass("label-visible");
        }
        else{
            t.removeClass("input-hint-hidden").addClass("input-hint-visible");
            l.removeClass("label-visible").addClass("label-hidden");
        }
    }).trigger("keyup");
}