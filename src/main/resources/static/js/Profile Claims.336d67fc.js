(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["Profile Claims"],{"1bbc":function(t,i,a){},"6f6d":function(t,i,a){"use strict";var s=a("f635"),e=a.n(s);e.a},"8ba9":function(t,i,a){"use strict";a.r(i);var s=function(){var t=this,i=t.$createElement,a=t._self._c||i;return t.loading?a("div",{staticClass:"profile_container r-flex"},[t._v(" Loading... ")]):a("div",{staticClass:"profile_container__main claims",attrs:{id:"claims"}},[a("h2",{staticClass:"cabinet_header"},[t._v(" ОБРАЩЕНИЯ ")]),a("div",{staticClass:"claims_box r-flex"},[t._m(0),t._l(t.claims,(function(t){return a("ClaimItem",{key:t.id,attrs:{claim:t}})}))],2),a("div",{staticClass:"sendClaim_container"},[a("router-link",{staticClass:"sendClaim",attrs:{href:"#",to:{name:"CreateClaim"}}},[t._v(" Подать новое обращение ")])],1)])},e=[function(){var t=this,i=t.$createElement,a=t._self._c||i;return a("div",{staticClass:"claims_box__item r-flex"},[a("div",{staticClass:"claim_item__col1"},[a("p",[t._v(" Дата: ")])]),a("div",{staticClass:"claim_item__col2 mg-l40"},[a("p",[t._v(" Номер: ")])]),a("div",{staticClass:"claim_item__col3 mg-l40"},[a("p",[t._v(" Тема: ")])]),a("div",{staticClass:"claim_item__col4 mg-l40"},[a("p",[t._v(" Статус: ")])])])}],l=a("5530"),c=a("2f62"),n=function(){var t=this,i=t.$createElement,a=t._self._c||i;return a("div",{staticClass:"claims_box__item r-flex"},[a("div",{staticClass:"claim_item__col1"},[a("p",[t._v(" "+t._s(t.claim.creationDate)+" ")])]),a("div",{staticClass:"claim_item__col2"},[a("p",[t._v(" "+t._s(t.claim.phoneNumber)+" ")])]),a("div",{staticClass:"claim_item__col3"},[a("router-link",{attrs:{to:{name:"single claim",params:{id:t.claim.id}}}},[t._v(" "+t._s(t.claim.title)+" ")])],1),a("div",{staticClass:"claim_item__col4"},[a("p",{class:t.status},[t._v(" "+t._s(t.claim.status)+" ")])])])},r=[],_={name:"ClaimItem",props:["claim"],computed:{status:function(){return{sended:"Направлено"===this.claim.status,inwork:"В работе"===this.claim.status,canceled:"Отменено"===this.claim.status,done:"Выполнено"===this.claim.status}}}},m=_,o=(a("f2ce"),a("2877")),d=Object(o["a"])(m,n,r,!1,null,"7fef3a9a",null),u=d.exports,v={name:"Claims",components:{ClaimItem:u},data:function(){return{loading:!1}},computed:Object(l["a"])({},Object(c["d"])(["claims"])),methods:Object(l["a"])({},Object(c["b"])(["fetchClaims"])),mounted:function(){var t=this;this.loading=!0,this.fetchClaims().then((function(i){t.loading=!1,console.log(i)}))}},f=v,C=(a("b48d"),Object(o["a"])(f,s,e,!1,null,null,null));i["default"]=C.exports},b48d:function(t,i,a){"use strict";var s=a("ef1b"),e=a.n(s);e.a},e9da:function(t,i,a){"use strict";a.r(i);var s=function(){var t=this,i=t.$createElement,a=t._self._c||i;return t.loading?a("div",{staticClass:"profile_container r-flex"},[t._v(" Loading... ")]):a("div",{staticClass:"profile_container__main claims",attrs:{id:"claims"}},[a("h2",{staticClass:"cabinet_header"},[t._v(" ОБРАЩЕНИЯ ")]),a("div",{staticClass:"claim_container r-flex"},[a("div",{staticClass:"claim_row r-flex"},[t._m(0),a("div",[a("p",{staticClass:"row_content"},[t._v(" "+t._s(t.singleClaim.creationDate)+" ")])])]),a("div",{staticClass:"claim_row r-flex"},[t._m(1),a("div",[a("p",{staticClass:"row_content"},[t._v(" "+t._s(t.singleClaim.title)+" ")])])]),a("div",{staticClass:"claim_row r-flex"},[t._m(2),a("div",[a("p",{staticClass:"row_content"},[t._v(" "+t._s(t.singleClaim.text)+" ")])])]),a("div",{staticClass:"claim_row r-flex"},[t._m(3),a("div",[a("p",{staticClass:"row_content"},[t._v(" "+t._s(t.singleClaim.photoLinks)+" ")])])]),a("div",{staticClass:"claim_row r-flex"},[t._m(4),a("div",[a("p",{class:t.status},[t._v(" "+t._s(t.singleClaim.status)+" ")])])])])])},e=[function(){var t=this,i=t.$createElement,a=t._self._c||i;return a("div",[a("p",{staticClass:"row_header"},[t._v(" Дата: ")])])},function(){var t=this,i=t.$createElement,a=t._self._c||i;return a("div",[a("p",{staticClass:"row_header"},[t._v(" Тема: ")])])},function(){var t=this,i=t.$createElement,a=t._self._c||i;return a("div",[a("p",{staticClass:"row_header"},[t._v(" Текст: ")])])},function(){var t=this,i=t.$createElement,a=t._self._c||i;return a("div",[a("p",{staticClass:"row_header"},[t._v(" Фото: ")])])},function(){var t=this,i=t.$createElement,a=t._self._c||i;return a("div",[a("p",{staticClass:"row_header"},[t._v(" Статус: ")])])}],l=a("5530"),c=a("2f62"),n={name:"ClaimSingle",props:["id"],data:function(){return{loading:!1,singleClaim:{}}},computed:Object(l["a"])(Object(l["a"])({status:function(){return{sended:"Направлено"===this.singleClaim.status,inwork:"В работе"===this.singleClaim.status,canceled:"Отменено"===this.singleClaim.status,done:"Выполнено"===this.singleClaim.status}}},Object(c["c"])(["getClaim"])),Object(c["d"])(["claims"])),methods:Object(l["a"])({},Object(c["b"])(["fetchClaims"])),mounted:function(){var t=this;0===this.claims.length&&(this.loading=!0,this.fetchClaims().then((function(i){t.loading=!1}))),this.singleClaim=this.getClaim(+this.id)}},r=n,_=(a("6f6d"),a("2877")),m=Object(_["a"])(r,s,e,!1,null,"286c55dd",null);i["default"]=m.exports},ef1b:function(t,i,a){},f2ce:function(t,i,a){"use strict";var s=a("1bbc"),e=a.n(s);e.a},f635:function(t,i,a){}}]);
//# sourceMappingURL=Profile Claims.336d67fc.js.map