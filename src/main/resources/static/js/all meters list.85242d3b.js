(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["all meters list"],{1846:function(e,t,a){},"1b0e":function(e,t,a){"use strict";var n=a("81f7"),i=a.n(n);i.a},"345b":function(e,t,a){"use strict";a.r(t);var n=function(){var e=this,t=e.$createElement,a=e._self._c||t;return e.loading?a("div",{staticClass:"profile_container"},[e._v(" Loading... ")]):a("div",{staticClass:"profile_container__main meters",attrs:{id:"meters"}},[a("h2",{staticClass:"cabinet_header"},[e._v(" Счетчики ")]),a("h3",{staticClass:"sub_header"},[e._v(" Передача показаний приборов учета электроэнергии: ")]),a("div",{staticClass:"meters_box r-flex"},[e._m(0),e._l(e.meters,(function(e){return a("MeterItem",{key:e.id,attrs:{meter:e}})}))],2),a("div",{staticClass:"controls_container"},[a("a",{staticClass:"saveMeterIndication_btn",attrs:{href:"#"},on:{click:function(t){return t.preventDefault(),e.saveBtnHandler(t)}}},[e._v(" Сохранить показания ")]),a("a",{staticClass:"addNewMeter",attrs:{href:"#"},on:{click:function(t){t.preventDefault(),e.showModal=!e.showModal}}},[e._v(" Добавить новый счётчик ")])]),a("div",{class:[e.modal,e.showModal?e.login_show:""],attrs:{id:""}},[a("div",{staticClass:"r-flex",attrs:{id:"addNewCounter"}},[a("p",{staticClass:"modal_close",attrs:{id:"close_modal"}},[a("a",{staticClass:"close_X",attrs:{href:"#",id:"close_X"},on:{click:function(t){t.preventDefault(),e.showModal=!e.showModal}}},[e._v(" X ")])]),a("h2",[e._v(" Добавить новый счётчик ")]),a("form",{staticClass:"r-flex",attrs:{name:"addNewCounter",action:"#",method:"post"}},[a("div",{staticClass:"r-flex"},[e._m(1),a("input",{directives:[{name:"model",rawName:"v-model",value:e.newCounter.accountId,expression:"newCounter.accountId"}],attrs:{type:"text",name:"account"},domProps:{value:e.newCounter.accountId},on:{input:function(t){t.target.composing||e.$set(e.newCounter,"accountId",t.target.value)}}})]),a("div",{staticClass:"r-flex"},[e._m(2),a("input",{directives:[{name:"model",rawName:"v-model",value:e.newCounter.typeDescription,expression:"newCounter.typeDescription"}],attrs:{type:"text",name:"typeDescription"},domProps:{value:e.newCounter.typeDescription},on:{input:function(t){t.target.composing||e.$set(e.newCounter,"typeDescription",t.target.value)}}})]),a("div",{staticClass:"r-flex"},[e._m(3),a("input",{directives:[{name:"model",rawName:"v-model",value:e.newCounter.serialNumber,expression:"newCounter.serialNumber"}],attrs:{type:"text",name:"serialNumber"},domProps:{value:e.newCounter.serialNumber},on:{input:function(t){t.target.composing||e.$set(e.newCounter,"serialNumber",t.target.value)}}})]),a("div",{staticClass:"r-flex"},[e._m(4),a("input",{directives:[{name:"model",rawName:"v-model",value:e.newCounter.model,expression:"newCounter.model"}],attrs:{type:"text",name:"model"},domProps:{value:e.newCounter.model},on:{input:function(t){t.target.composing||e.$set(e.newCounter,"model",t.target.value)}}})]),a("div",{staticClass:"r-flex"},[e._m(5),a("input",{directives:[{name:"model",rawName:"v-model",value:e.newCounter.checkDate,expression:"newCounter.checkDate"}],attrs:{type:"text",name:"checkDate"},domProps:{value:e.newCounter.checkDate},on:{input:function(t){t.target.composing||e.$set(e.newCounter,"checkDate",t.target.value)}}})]),a("div",{staticClass:"r-flex"},[e._m(6),a("input",{directives:[{name:"model",rawName:"v-model",value:e.newCounter.tariffDescription,expression:"newCounter.tariffDescription"}],attrs:{type:"text",name:"tariffDescription"},domProps:{value:e.newCounter.tariffDescription},on:{input:function(t){t.target.composing||e.$set(e.newCounter,"tariffDescription",t.target.value)}}})]),a("div",{staticClass:"login_controls r-flex"},[a("input",{attrs:{type:"submit",name:"submit",value:"Создать"},on:{click:function(t){return t.preventDefault(),e.createNewCounterHandler(t)}}}),a("input",{attrs:{type:"button",name:"cancel",value:"отмена"},on:{click:function(t){t.preventDefault(),e.showModal=!e.showModal}}})])])])])])},i=[function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("div",{staticClass:"meters_box__item r-flex thead"},[a("div",{staticClass:"meter_item__col"},[a("p",[e._v(" № участка: ")])]),a("div",{staticClass:"meter_item__col"},[a("p",[e._v(" № лицевого счета: ")])]),a("div",{staticClass:"meter_item__col"},[a("p",[e._v(" Серийный № счетчика: ")])]),a("div",{staticClass:"meter_item__col"},[a("p",[e._v(" Тип счетчика: ")])]),a("div",{staticClass:"meter_item__col"},[a("p",[e._v(" Последние показания: ")])]),a("div",{staticClass:"meter_item__col"},[a("p",[e._v(" Ввести текущие показания: ")])])])},function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("label",{attrs:{for:"account"}},[a("b",[e._v(" № лицевого счета ")])])},function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("label",{attrs:{for:"typeDescription"}},[a("b",[e._v(" Тип ")])])},function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("label",{attrs:{for:"serialNumber"}},[a("b",[e._v(" Серийный номер ")])])},function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("label",{attrs:{for:"model"}},[a("b",[e._v(" Модель ")])])},function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("label",{attrs:{for:"checkDate"}},[a("b",[e._v(" Дата поверки ")])])},function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("label",{attrs:{for:"tariffDescription"}},[a("b",[e._v(" Тарифы ")])])}],s=(a("a4d3"),a("e01a"),a("d28b"),a("d3b7"),a("3ca3"),a("ddb0"),a("06c5"));function r(e,t){var a;if("undefined"===typeof Symbol||null==e[Symbol.iterator]){if(Array.isArray(e)||(a=Object(s["a"])(e))||t&&e&&"number"===typeof e.length){a&&(e=a);var n=0,i=function(){};return{s:i,n:function(){return n>=e.length?{done:!0}:{done:!1,value:e[n++]}},e:function(e){throw e},f:i}}throw new TypeError("Invalid attempt to iterate non-iterable instance.\nIn order to be iterable, non-array objects must have a [Symbol.iterator]() method.")}var r,l=!0,c=!1;return{s:function(){a=e[Symbol.iterator]()},n:function(){var e=a.next();return l=e.done,e},e:function(e){c=!0,r=e},f:function(){try{l||null==a["return"]||a["return"]()}finally{if(c)throw r}}}}var l=a("5530"),c=a("2f62"),o=function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("div",{staticClass:"meters_box__item"},[a("div",{staticClass:"meter_item__col"},[a("p",[e._v(" "+e._s(e.meter.houseNumber)+" ")])]),a("div",{staticClass:"meter_item__col"},[a("p",[e._v(" "+e._s(e.meter.accountId)+" ")])]),a("div",{staticClass:"meter_item__col"},[a("p",[a("router-link",{attrs:{to:{name:"meters single",params:{id:e.meter.meterId}}}},[e._v(" "+e._s(e.meter.serialNumber)+" ")])],1)]),a("div",{staticClass:"meter_item__col"},[a("p",[e._v(" "+e._s(e.meter.model)+" ")])]),a("div",{staticClass:"meter_item__col"},[a("p",[e._v(" "+e._s(e.meter.currentMeterData.value)+" ")])]),a("div",{staticClass:"meter_item__col"},[a("input",{directives:[{name:"model",rawName:"v-model",value:e.value,expression:"value"}],attrs:{name:"currentMeterValue",type:"number"},domProps:{value:e.value},on:{input:function(t){t.target.composing||(e.value=t.target.value)}}})])])},u=[],m={name:"MeterItem",props:["meter"],data:function(){return{value:null}},computed:{},mounted:function(){console.log(this.meter)}},d=m,_=a("2877"),v=Object(_["a"])(d,o,u,!1,null,"e4352fc8",null),f=v.exports,p={name:"meters",components:{MeterItem:f},data:function(){return{loading:!1,showModal:!1,modal:"modal",login_show:"login_active",newCounter:{accountId:"",serialNumber:"",model:"",typeDescription:"",checkDate:"",tariffDescription:""}}},computed:Object(l["a"])({},Object(c["d"])(["meters"])),methods:Object(l["a"])({saveBtnHandler:function(){var e,t=[],a=r(this.$children);try{for(a.s();!(e=a.n()).done;){var n=e.value;""!==n.value&&null!==n.value&&(t.push({value:+n.value,meterId:n.meter.meterId}),n.value="")}}catch(i){a.e(i)}finally{a.f()}this.updateMetersBatch(t)},createNewCounterHandler:function(){var e=this;this.newCounter.accountId=+this.newCounter.accountId,console.log(this.newCounter),this.createNewCounter(this.newCounter).then((function(t){return e.showModal=!1}))}},Object(c["b"])(["fetchAllMeters","updateMetersBatch","createNewCounter"])),mounted:function(){var e=this;this.loading=!0,this.fetchAllMeters().then((function(t){e.loading=!1,console.log(t)}))}},C=p,h=(a("b475"),Object(_["a"])(C,n,i,!1,null,null,null));t["default"]=h.exports},"359e":function(e,t,a){},"61e5":function(e,t,a){"use strict";a.r(t);var n=function(){var e=this,t=e.$createElement,a=e._self._c||t;return e.loading?a("div",{staticClass:"profile_container r-flex"},[e._v(" Loading... ")]):a("div",{staticClass:"profile_container__main claims",attrs:{id:"claims"}},[a("h2",{staticClass:"cabinet_header"},[e._v(" ОБРАЩЕНИЯ ")]),a("div",{staticClass:"claims_box r-flex"},[e._m(0),e._l(e.claims,(function(e){return a("ClaimItemAdmin",{key:e.id,attrs:{claim:e}})}))],2)])},i=[function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("div",{staticClass:"claims_box__item r-flex"},[a("div",{staticClass:"claim_item__col1"},[a("p",[e._v(" Дата: ")])]),a("div",{staticClass:"claim_item__col2 mg-l40"},[a("p",[e._v(" Номер: ")])]),a("div",{staticClass:"claim_item__col3 mg-l40"},[a("p",[e._v(" Тема: ")])]),a("div",{staticClass:"claim_item__col4 mg-l40"},[a("p",[e._v(" Статус: ")])])])}],s=a("5530"),r=a("2f62"),l=function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("div",{staticClass:"claims_box__item r-flex",on:{click:function(t){return t.preventDefault(),e.$router.push({name:"single claim admin",params:{id:e.claim.id}})}}},[a("div",{staticClass:"claim_item__col1"},[a("p",[e._v(" "+e._s(e.claim.creationDate)+" ")])]),a("div",{staticClass:"claim_item__col2"},[a("p",[e._v(" "+e._s(e.claim.id)+" ")])]),a("div",{staticClass:"claim_item__col3"}),a("div",{staticClass:"claim_item__col4"},[a("p",{class:e.status},[e._v(" "+e._s(e.claim.status)+" ")])])])},c=[],o={name:"ClaimItemAdmin",props:["claim"],computed:{status:function(){return{sended:"Направлено"===this.claim.status,inwork:"В работе"===this.claim.status,canceled:"Отменено"===this.claim.status,done:"Выполнено"===this.claim.status,recieved:"Получено"===this.claim.status}}}},u=o,m=(a("6456"),a("2877")),d=Object(m["a"])(u,l,c,!1,null,"125b4b61",null),_=d.exports,v={name:"ClaimsAdmin",components:{ClaimItemAdmin:_},data:function(){return{loading:!1}},computed:Object(s["a"])({},Object(r["d"])(["claims"])),methods:Object(s["a"])({},Object(r["b"])(["fetchAllClaims"])),mounted:function(){var e=this;this.loading=!0,this.fetchAllClaims().then((function(t){e.loading=!1,console.log(t)}))}},f=v,p=(a("d52d"),Object(m["a"])(f,n,i,!1,null,null,null));t["default"]=p.exports},6456:function(e,t,a){"use strict";var n=a("77db"),i=a.n(n);i.a},"77db":function(e,t,a){},"81f7":function(e,t,a){},ae1d:function(e,t,a){"use strict";a.r(t);var n=function(){var e=this,t=e.$createElement,a=e._self._c||t;return e.loading?a("div",{staticClass:"profile_container r-flex"},[e._v(" Loading... ")]):a("div",{staticClass:"profile_container__main cityzens",attrs:{id:"cityzens"}},[a("h2",{staticClass:"cabinet_header"},[e._v(" Платежи ")])])},i=[],s=(a("b9dd"),{name:"Payments"}),r=s,l=(a("1b0e"),a("2877")),c=Object(l["a"])(r,n,i,!1,null,"405adaf0",null);t["default"]=c.exports},b475:function(e,t,a){"use strict";var n=a("ed96"),i=a.n(n);i.a},b9dd:function(e,t,a){"use strict";var n=function(){var e=this,t=e.$createElement,a=e._self._c||t;return a("div",{staticClass:"cityzens_box__item r-flex",on:{click:function(t){return t.preventDefault(),e.$router.push({name:"cityzen profile",params:{id:e.cityzen.id}})}}},[a("div",{staticClass:"cityzen_item__col1"},[a("p",[e._v(" "+e._s(e.cityzen.address)+" ")])]),a("div",{staticClass:"cityzen_item__col3"},[a("p",[e._v(" "+e._s(e.cityzen.lastName)+" "+e._s(e.cityzen.firstName)+" "+e._s(e.cityzen.patronymic)+" ")])]),a("div",{staticClass:"cityzen_item__col4"},[a("p",[e._v(" "+e._s(e.cityzen.phoneNumber)+" / "+e._s(e.cityzen.username)+" ")])])])},i=[],s={name:"CityzenItem",props:["cityzen"],computed:{}},r=s,l=(a("f12c"),a("2877")),c=Object(l["a"])(r,n,i,!1,null,"78e0122c",null);t["a"]=c.exports},d52d:function(e,t,a){"use strict";var n=a("1846"),i=a.n(n);i.a},ed96:function(e,t,a){},f12c:function(e,t,a){"use strict";var n=a("359e"),i=a.n(n);i.a}}]);
//# sourceMappingURL=all meters list.85242d3b.js.map