(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["single meter"],{"741d":function(t,e,s){},dace:function(t,e,s){"use strict";var i=s("741d"),a=s.n(i);a.a},faeb:function(t,e,s){"use strict";s.r(e);var i=function(){var t=this,e=t.$createElement,s=t._self._c||e;return t.loading?s("div",{staticClass:"profile_container r-flex"},[t._v(" Loading... ")]):s("div",{staticClass:"profile_container__main meters",attrs:{id:"meters"}},[s("h2",{staticClass:"cabinet_header"},[t._v(" "+t._s(t.singleMeter.typeDescription)+" "+t._s(t.singleMeter.model)+" - С\\Н: "+t._s(t.singleMeter.serialNumber)+" ")]),s("h3",{staticClass:"sub_header"},[t._v(" № лицевого счета "+t._s(t.singleMeter.accountId)+" ")]),s("div",{staticClass:"meters_box"},[t._m(0),s("div",{staticClass:"meters_box__item"},[s("div",{staticClass:"meter_item__col"},[s("p",[t._v(" "+t._s(t.singleMeter.typeDescription)+" ")])]),s("div",{staticClass:"meter_item__col"},[s("p",[t._v(" "+t._s(t.singleMeter.tariffDescription)+" ")])]),s("div",{staticClass:"meter_item__col"},[s("p",[t._v(" "+t._s(t.singleMeter.serialNumber)+" ")])])]),t._m(1),s("div",{staticClass:"meters_box__item"},[s("div",{staticClass:"meter_item__col"},[s("p",[t._v(" "+t._s(t.singleMeter.model)+" ")])]),s("div",{staticClass:"meter_item__col"},[s("p",[t._v(" "+t._s(t.singleMeter.checkDate)+" ")])]),t._m(2)])]),s("div",{staticClass:"controls_container"},[s("a",{staticClass:"editMeter",attrs:{href:"#"},on:{click:function(e){return e.preventDefault(),t.editBtnHandler(e)}}},[t._v(" Внести изменения ")]),s("a",{staticClass:"deleteMeter",attrs:{href:"#"}},[t._v(" Удалить счетчик ")])]),s("div",{staticClass:"history_box"},[s("h3",[t._v(" История показаний: ")]),s("div",{staticClass:"history_tbl"},t._l(t.singleMeterAllIndications,(function(e){return s("div",{key:e.id,staticClass:"history_tbl_row"},[s("div",{staticClass:"history_tbl_cell"},[s("p",[t._v(" "+t._s(e.creationDate)+" ")])]),s("div",{staticClass:"history_tbl_cell"},[s("p",[t._v(" "+t._s(e.value)+" ")])])])})),0)])])},a=[function(){var t=this,e=t.$createElement,s=t._self._c||e;return s("div",{staticClass:"meters_box__item"},[s("div",{staticClass:"meter_item__col"},[s("p",[t._v(" Тип ")])]),s("div",{staticClass:"meter_item__col"},[s("p",[t._v(" Тарифы ")])]),s("div",{staticClass:"meter_item__col"},[s("p",[t._v(" Серийный номер ")])])])},function(){var t=this,e=t.$createElement,s=t._self._c||e;return s("div",{staticClass:"meters_box__item"},[s("div",{staticClass:"meter_item__col"},[s("p",[t._v(" Модель ")])]),s("div",{staticClass:"meter_item__col"},[s("p",[t._v(" Дата поверки ")])]),s("div",{staticClass:"meter_item__col"},[s("p")])])},function(){var t=this,e=t.$createElement,s=t._self._c||e;return s("div",{staticClass:"meter_item__col"},[s("p")])}],l=s("5530"),_=s("2f62"),n={name:"SingleMeter",props:["id"],components:{},data:function(){return{loading:!1}},computed:Object(l["a"])({},Object(_["d"])(["singleMeter","singleMeterAllIndications"])),methods:Object(l["a"])({},Object(_["b"])(["fetchSingleMeter","fetchAllSingleMeterIndications"])),mounted:function(){var t=this;this.loading=!0,this.fetchSingleMeter(this.id).then((function(e){t.loading=!1})),this.fetchAllSingleMeterIndications(this.id).then((function(t){return console.log(t)}))}},r=n,c=(s("dace"),s("2877")),o=Object(c["a"])(r,i,a,!1,null,null,null);e["default"]=o.exports}}]);
//# sourceMappingURL=single meter.aecb1c35.js.map