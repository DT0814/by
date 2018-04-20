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
    <h1>用户管理</h1>
    <table class="table table-hover ">
        <th class="row">
        <td class=" col-md-3">用户账号</td>
        <td class=" col-md-3">名字</td>
        <td class=" col-md-2">性别</td>
        <td class=" col-md-2">状态</td>
        <td class=" col-md-2"></td>
        </th>
        <tr class="row" v-for="user in users">
            <td class=" col-md-3">{{user.account}}</td>
            <td class=" col-md-3">{{user.name}}</td>
            <td class=" col-md-2">{{user.gd}}</td>
            <td class=" col-md-2">{{user.statusStr}}</td>
            <td class=" col-md-2">
                <template v-if="user.status==2">
                    <span class="btn btn-primary " v-on:click="stop(user)">停用</span>
                </template>
                <template v-else>
                    <span class="btn btn-primary " v-on:click="start(user)">启用</span>
                </template>
                <span class="btn btn-primary " v-on:click="deleteC(user)">点击删除</span>
            </td>
        </tr>
    </table>
</div>


<script>
    var vu = new Vue({
        el: "#bd",
        data: {
            users: []
        },
        mounted(){
            this.$options.methods.findAll();
        },
        methods: {
            findAll: function () {
                axios.get('/user_findAllUser.action')
                        .then(function (response) {
                            vu.users = response.data.result.data;
                        });
            },
            deleteC: function (user) {
                axios.get('/user_delUser.action?user.uid=' + user.uid)
                        .then(function (response) {
                            var data = response.data.result;
                            if (data.code == 200) {
                                alert("删除成功");
                                vu.$options.methods.findAll();
                            }
                        });
            },
            stop: function (user) {
                var params = new URLSearchParams();
                params.append('user.uid', user.uid);
                params.append('user.status', 1);
                axios.post('/user_updateUser.action', params).then(function (response) {
                    var data = response.data.result;
                    if (data.code == 200) {
                        alert("停用成功");
                        vu.$options.methods.findAll();
                    }
                })
            },
            start: function (user) {
                var params = new URLSearchParams();
                params.append('user.uid', user.uid);
                params.append('user.status', 2);
                axios.post('/user_updateUser.action', params).then(function (response) {
                    var data = response.data.result;
                    if (data.code == 200) {
                        alert("启用成功");
                        vu.$options.methods.findAll();
                    }
                })
            }
        }

    });
</script>
</body>
</html>
