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
    <h1>团队管理</h1>
    <table class="table table-hover ">
        <th class="row">
        <td class=" col-md-3">团队账号</td>
        <td class=" col-md-3">团队名字</td>
        <td class=" col-md-4">团队介绍</td>
        <td class=" col-md-2"></td>
        </th>
        <tr class="row" v-for="team in teams">
            <td class=" col-md-3">{{team.account}}</td>
            <td class=" col-md-3">{{team.name}}</td>
            <td class=" col-md-4">{{team.introduce}}</td>
            <td class=" col-md-2">
                <span class="btn btn-primary " v-on:click="deleteC(team)">点击删除</span>
            </td>
        </tr>
    </table>
</div>


<script>
    var vu = new Vue({
        el: "#bd",
        data: {
            teams: []
        },
        mounted(){
            this.$options.methods.findAll();
        },
        methods: {
            findAll: function () {
                axios.get('/team_findAllTeam.action')
                        .then(function (response) {
                            vu.teams = response.data.result.data;
                        });
            },
            deleteC: function (team) {
                axios.get('/team_delTeam.action?team.tid=' + team.tid)
                        .then(function (response) {
                            var data = response.data.result;
                            if (data.code == 200) {
                                alert("删除成功");
                                vu.$options.methods.findAll();
                            }
                        });
            },
        }

    });
</script>
</body>
</html>
