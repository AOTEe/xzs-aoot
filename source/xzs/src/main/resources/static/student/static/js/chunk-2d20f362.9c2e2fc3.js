(window["webpackJsonp"]=window["webpackJsonp"]||[]).push([["chunk-2d20f362"],{b350:function(e,t,a){"use strict";a.r(t);var r=function(){var e=this,t=e._self._c;return t("div",{staticClass:"app-contain",staticStyle:{"margin-top":"10px"}},[t("el-row",{attrs:{gutter:50}},[t("el-col",{attrs:{span:7}},[t("el-card",[t("div",{staticClass:"clearfix",attrs:{slot:"header"},slot:"header"},[t("span",[e._v("个人信息")])]),t("el-row",{staticStyle:{"text-align":"center"}},[t("el-upload",{attrs:{action:"/api/student/upload/image",accept:".jpg,.png","show-file-list":!1,"on-success":e.uploadSuccess}},[t("el-avatar",{staticClass:"el-dropdown-avatar",attrs:{size:100,src:null===e.form.imagePath?a("1195"):e.form.imagePath}})],1)],1),t("el-row",{staticClass:"user-info-userName"},[t("label",[e._v(e._s(e.form.userName))])]),t("el-divider"),t("el-row",{staticClass:"user-info-fullInfo"},[t("label",[e._v("姓名："+e._s(e.form.realName))]),t("br"),t("label",[e._v("年级："+e._s(e.levelFormatter(e.form.userLevel)))]),t("br"),t("label",[e._v("注册时间："+e._s(e.form.createTime))]),t("br")])],1)],1),t("el-col",{attrs:{span:17}},[t("el-card",{attrs:{shadow:"hover"}},[t("el-tabs",{attrs:{"active-name":"event",type:"card"}},[t("el-tab-pane",{attrs:{label:"用户动态",name:"event"}},[t("div",{staticClass:"block"},[t("el-timeline",e._l(e.event,(function(a){return t("el-timeline-item",{key:a.id,attrs:{timestamp:a.createTime,placement:"top"}},[t("el-card",[t("p",[e._v(e._s(a.content))])])],1)})),1)],1)]),t("el-tab-pane",{attrs:{label:"个人资料修改",name:"update"}},[t("el-form",{directives:[{name:"loading",rawName:"v-loading",value:e.formLoading,expression:"formLoading"}],ref:"form",attrs:{model:e.form,"label-width":"100px",rules:e.rules}},[t("el-form-item",{attrs:{label:"真实姓名：",prop:"realName",required:""}},[t("el-input",{model:{value:e.form.realName,callback:function(t){e.$set(e.form,"realName",t)},expression:"form.realName"}})],1),t("el-form-item",{attrs:{label:"年龄："}},[t("el-input",{model:{value:e.form.age,callback:function(t){e.$set(e.form,"age",t)},expression:"form.age"}})],1),t("el-form-item",{attrs:{label:"性别："}},[t("el-select",{attrs:{placeholder:"性别",clearable:""},model:{value:e.form.sex,callback:function(t){e.$set(e.form,"sex",t)},expression:"form.sex"}},e._l(e.sexEnum,(function(e){return t("el-option",{key:e.key,attrs:{value:e.key,label:e.value}})})),1)],1),t("el-form-item",{attrs:{label:"出生日期："}},[t("el-date-picker",{attrs:{"value-format":"yyyy-MM-dd",type:"date",placeholder:"选择日期"},model:{value:e.form.birthDay,callback:function(t){e.$set(e.form,"birthDay",t)},expression:"form.birthDay"}})],1),t("el-form-item",{attrs:{label:"手机："}},[t("el-input",{model:{value:e.form.phone,callback:function(t){e.$set(e.form,"phone",t)},expression:"form.phone"}})],1),t("el-form-item",{attrs:{label:"年级：",prop:"userLevel",required:""}},[t("el-select",{attrs:{placeholder:"年级"},model:{value:e.form.userLevel,callback:function(t){e.$set(e.form,"userLevel",t)},expression:"form.userLevel"}},e._l(e.levelEnum,(function(e){return t("el-option",{key:e.key,attrs:{value:e.key,label:e.value}})})),1)],1),t("el-form-item",[t("el-button",{attrs:{type:"primary"},on:{click:e.submitForm}},[e._v("更新")])],1)],1)],1)],1)],1)],1)],1)],1)},l=[],s=a("5530"),o=a("c24f"),n=a("2f62"),i={data:function(){return{event:[],form:{userName:"",realName:"",age:"",sex:"",birthDay:null,phone:null,userLevel:null,createTime:null,imagePath:null},formLoading:!1,rules:{realName:[{required:!0,message:"请输入真实姓名",trigger:"blur"}],userLevel:[{required:!0,message:"请选择年级",trigger:"change"}]}}},created:function(){var e=this;o["a"].getUserEvent().then((function(t){e.event=t.response})),o["a"].getCurrentUser().then((function(t){e.form=t.response}))},methods:{uploadSuccess:function(e,t){1===e.code?window.location.reload():this.$message.error(e.message)},submitForm:function(){var e=this,t=this;this.$refs.form.validate((function(a){if(!a)return!1;e.formLoading=!0,o["a"].update(e.form).then((function(e){1===e.code?t.$message.success(e.message):t.$message.error(e.message),t.formLoading=!1})).catch((function(e){t.formLoading=!1}))}))},levelFormatter:function(e){return this.enumFormat(this.levelEnum,e)}},computed:Object(s["a"])(Object(s["a"])({},Object(n["c"])("enumItem",["enumFormat"])),Object(n["e"])("enumItem",{sexEnum:function(e){return e.user.sexEnum},levelEnum:function(e){return e.user.levelEnum}}))},m=i,u=a("2877"),c=Object(u["a"])(m,r,l,!1,null,"eb22e912",null);t["default"]=c.exports}}]);