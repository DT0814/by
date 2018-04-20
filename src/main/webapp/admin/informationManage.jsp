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
    <h1>团队信息管理管理</h1>
    <!-- 修改展示窗口 -->
    <form class="form-group" id="updateForm">
        <div class="form-group">
            <label>名称</label>
            <input class="form-control" name="team.name" id="name" type="text">
        </div>
        <div class="form-group">
            <label>团队简介</label>
            <textarea name="team.introduce" id="" style="width: 1112px; height: 175px;"></textarea>
        </div>
        <input type="hidden" name="team.tid">
        <div class="text-right">
            <span class="btn btn-primary" v-on:click="update">提交修改</span>
        </div>
    </form>
</div>


<script>
    var vu = new Vue({
        el: "#bd",
        data: {
            team: {}
        },
        mounted(){
            this.$options.methods.findByTid();
        },
        methods: {
            findByTid: function () {
                axios.get('/team_findByTid.action?team.tid=' + $.cookie('team_tid')).then(function (response) {
                    var e = $("#updateForm");
                    var data = response.data.result;
                    if (data.code == 200) {
                        console.log(data);
                        $("#updateForm").find("input[name='team.name']").val(data.data.name);
                        $("#updateForm").find("textarea[name='team.introduce']").html(data.data.introduce);
                        $("#updateForm").find("input[name='team.tid']").val(data.data.tid);
                    } else {
                        alert(data.msg);
                    }
                });
            },
            update: function () {
                axios.post('/team_updateTeam.action', $("#updateForm").serialize()).then(function (response) {
                    var data = response.data.result;
                    if (data.code == 200) {
                        console.log(data);
                        alert("更新成功")
                    } else {
                        alert(data.msg);
                    }
                });
            }
        }
    });
</script>
</body>
</html>
