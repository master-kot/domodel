(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["Create Claim"],{"03a5":function(t,e,a){"use strict";a.r(e);var i=function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("div",{staticClass:"profile_container__main claims",attrs:{id:"claimsCreate"}},[a("h2",{staticClass:"cabinet_header"},[t._v(" НАПИШИТЕ НАМ ОБРАЩЕНИЕ ")]),a("div",{staticClass:"claim_container r-flex"},[a("div",{staticClass:"claim_row r-flex"},[t._m(0),a("div",{staticClass:"field_container"},[a("input",{directives:[{name:"model",rawName:"v-model",value:t.claim.title,expression:"claim.title"}],staticClass:"row_content",attrs:{name:"claim_title",placeholder:"Например: сломалась разетка"},domProps:{value:t.claim.title},on:{input:function(e){e.target.composing||t.$set(t.claim,"title",e.target.value)}}})])]),a("div",{staticClass:"claim_row r-flex"},[t._m(1),a("div",{staticClass:"field_container"},[a("textarea",{directives:[{name:"model",rawName:"v-model",value:t.claim.text,expression:"claim.text"}],staticClass:"row_content",attrs:{name:"claim_content",placeholder:"Введите текст"},domProps:{value:t.claim.text},on:{input:function(e){e.target.composing||t.$set(t.claim,"text",e.target.value)}}})])]),t._m(2),a("div",{staticClass:"claim_row r-flex"},[t._m(3),a("div",{staticClass:"field_container"},[a("input",{directives:[{name:"model",rawName:"v-model",value:t.claim.phoneNumber,expression:"claim.phoneNumber"}],staticClass:"row_content",attrs:{name:"claimer_phone",placeholder:"+7 (985) 123-45-67"},domProps:{value:t.claim.phoneNumber},on:{input:function(e){e.target.composing||t.$set(t.claim,"phoneNumber",e.target.value)}}})])]),a("a",{staticClass:"send_claim",attrs:{href:"#"},on:{click:function(e){return e.preventDefault(),t.sendClaimHandler(e)}}},[t._v(" Отправить ")])])])},l=[function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("div",[a("label",{staticClass:"row_header",attrs:{for:"claim_title"}},[t._v(" Тема: ")])])},function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("div",[a("label",{staticClass:"row_header",attrs:{for:"claim_content"}},[t._v(" Текст: ")])])},function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("div",{staticClass:"claim_row r-flex"},[a("div",[a("p",{staticClass:"row_header"},[t._v(" Фото: ")])]),a("div",{staticClass:"field_container"},[a("p",{staticClass:"row_content"})])])},function(){var t=this,e=t.$createElement,a=t._self._c||e;return a("div",[a("label",{staticClass:"row_header row_header__last",attrs:{for:"claimer_phone"}},[t._v(" Телефон для связи: ")])])}],r=a("5530"),s=a("2f62"),n={name:"CreateClaim",data:function(){return{claim:{title:"",text:"",phoneNumber:""}}},methods:Object(r["a"])({sendClaimHandler:function(){var t=this;this.createClaim(this.claim).then((function(){return t.$router.go(-1)}))}},Object(s["b"])(["createClaim"]))},c=n,o=(a("4886"),a("2877")),m=Object(o["a"])(c,i,l,!1,null,"4e174aea",null);e["default"]=m.exports},4886:function(t,e,a){"use strict";var i=a("4cbf"),l=a.n(i);l.a},"4cbf":function(t,e,a){}}]);
//# sourceMappingURL=Create Claim.829d8723.js.map