<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    pageContext.setAttribute("APP_PATH", request.getContextPath());
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html lang="zh-cn">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta http-equiv=X-UA-Compatible content="IE=edge,chrome=1">
    <meta content=always name=referrer>
    <meta name="renderer" content="webkit">
    <meta name="viewport"
          content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no"/>
    <meta name="renderer" content="webkit">
    <title></title>
    <link rel="stylesheet" type="text/css"
          href="${APP_PATH}/css/bootstrap.css"/>
    <script type="text/javascript" src="${APP_PATH}/js/jquery-1.12.4.js"></script>
    <script type="text/javascript" src="${APP_PATH}/js/jquery.cookie.js"></script>
    <script type="text/javascript" src="${APP_PATH}/js/bootstrap.js"></script>
    <script type="text/javascript" src="${APP_PATH}/js/vue.js"></script>
    <script type="text/javascript" src="${APP_PATH}/js/axios.js"></script>
    <script src="${APP_PATH }/admin/js/pintuer.js"></script>
    <link rel="stylesheet" href="${APP_PATH }/admin/css/pintuer.css">
    <link rel="stylesheet" href="${APP_PATH }/admin/css/admin.css">
</head>
<body>
<div id="bd">
    <h1>订单管理</h1>
    <table class="table table-hover ">
        <th class="row">
        <td class=" col-md-2">价格</td>
        <td class=" col-md-3">订单创建时间</td>
        <td class=" col-md-3">订单支付时间</td>
        <td class=" col-md-2">订单状态</td>
        <td class=" col-md-2"></td>
        </th>
        <tr class="row" v-for="o in orders">
            <td class=" col-md-2">{{o.price}}</td>
            <td class=" col-md-3">{{o.cTime}}</td>
            <td class=" col-md-3">{{o.fTime}}</td>
            <td class=" col-md-2">{{o.statusStr}}</td>
            <td class=" col-md-2"><span class="btn btn-primary " v-on:click="showForm(o)">查看详细</span>
            </td>
        </tr>
    </table>


    <div id="show" class="modal fade">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-body">
                    <button class="close" data-dismiss="modal">
                        <span>&times;</span>
                    </button>
                </div>
                <div class="modal-body">
                    <form class="form-group" id="tgForm">
                        <div class="form-group">
                            <label class="form-control" name="tourGroup.name">名称:</label>
                        </div>
                        <div class="form-group">
                            <label class="form-control" name="tourGroup.fprice">价格:</label>
                        </div>
                        <div class="form-group">
                            <label class="form-control" name="tourGroup.time">开团时间:</label>
                        </div>
                        <div class="form-group">
                            <label name="route">路线:</label>
                            <label name="route.msg"></label>
                        </div>
                        <div class="form-group">
                            <label name="ciceroni">导游:</label>
                        </div>
                        <div class="text-right">
                            <input type="hidden" name="order.oid">
                            <button class="btn btn-danger" data-dismiss="modal">关闭</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>


<script>
    var vu = new Vue({
        el: "#bd",
        data: {
            orders: []
        },
        mounted(){
            this.$options.methods.findAll();
        },
        methods: {
            findAll: function () {
                    axios.get('/O_findByTid.action?order.tid='+$.cookie('team_tid')).then(function (response) {
                        vu.orders = response.data.result.data;
                        console.log(response.data.result.data);
                    });
         },showForm: function (o) {
                axios.get('/O_findByOid.action?order.oid=' + o.oid).then(function (response) {
                    var data = response.data.result;
                    if (data.code == 200) {
                        console.log(data);
                        data = data.data;
                        $("#tgForm").find("input[name='order.oid']").val(data[0][0].oid);
                        $("#tgForm").find("input[name='tourGroup.price']").val(data[0][1].fprice);
                        $("#tgForm").find("label[name='tourGroup.name']").html("旅行团名字: " + data[0][1].name);
                        $("#tgForm").find("label[name='tourGroup.fprice']").html("价格: " + data[0][1].fprice);
                        $("#tgForm").find("label[name='tourGroup.time']").html("开团时间: " + data[0][1].timeStr);
                        $("#tgForm").find("label[name='route']").html("路线:" + data[0][2].start + "-" + data[0][2].stop);
                        $("#tgForm").find("label[name='route.msg']").html("路线介绍:" + data[0][2].msg);
                        $("#tgForm").find("label[name='ciceroni']").html("导游:" + data[0][3].name);
                        $("#show").modal();
                    } else {
                        alert(data.msg);
                    }
                });
            },
        }
    });
</script>
</body>
</html>
