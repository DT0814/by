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
    <h1>分类管理</h1>
    <button type="button" v-on:click="addmodal" class="btn btn-default btn-lg active">添加分类</button>
    <table class="table table-hover ">
        <th class="row">
        <td class=" col-md-6">分类名</td>
        <td class=" col-md-6"></td>
        </th>
        <tr class="row" v-for="cata in catalog">
            <td class=" col-md-6">{{cata.cname}}</td>
            <td class=" col-md-6"><span class="btn btn-primary " v-on:click="updateModal(cata)">点击修改</span>
            <td class=" col-md-6"><span class="btn btn-primary " v-on:click="deleteC(cata)">点击删除</span>
            </td>
        </tr>
    </table>
    <!-- 添加展示窗口 -->
    <div id="addModal" class="modal fade">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-body">
                    <button class="close" data-dismiss="modal">
                        <span>&times;</span>
                    </button>
                </div>
                <div class="modal-title">
                    <h1 class="text-center">添加</h1>
                </div>
                <div class="modal-body">
                    <form class="form-group" id="addForm">
                        <div class="form-group">
                            <label>分类名</label>
                            <input class="form-control" name="catalog.cname" type="text">
                        </div>
                        <div class="text-right">
                            <span class="btn btn-primary" v-on:click="add">提交</span>
                            <button class="btn btn-danger" data-dismiss="modal">取消</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
    <!-- 修改展示窗口 -->
    <div id="updateModal" class="modal fade">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-body">
                    <button class="close" data-dismiss="modal">
                        <span>&times;</span>
                    </button>
                </div>
                <div class="modal-title">
                    <h1 class="text-center">修改</h1>
                </div>
                <div class="modal-body">
                    <form class="form-group" id="updateForm">
                        <div class="form-group">
                            <label>分类名</label>
                            <input class="form-control" name="catalog.cname" type="text">
                        </div>
                        <input type="hidden" name="catalog.caid">
                        <div class="text-right">
                            <span class="btn btn-primary" v-on:click="update">提交修改</span>
                            <button class="btn btn-danger" data-dismiss="modal">取消</button>
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
            catalog: []
        },
        mounted(){
            this.$options.methods.findAll();
        },
        methods: {
            updateModal: function (cata) {
                $("#updateForm").find("input[name='catalog.cname']").val(cata.cname);
                $("#updateForm").find("input[name='catalog.caid']").val(cata.caid);
                $("#updateModal").modal();
            },
            update: function () {
                axios.post('/Cata_update.action', $("#updateForm").serialize())
                        .then(function (response) {
                            var result = response.data.result;
                            if (result.code == 200) {
                                alert("更新成功");
                                $("#updateModal").modal("hide");
                                vu.$options.methods.findAll();
                            } else {
                                alert(result.msg);
                            }
                        });
            },
            addmodal: function () {
                $("#addModal").modal();
            },
            add: function () {
                axios.post('/Cata_add.action', $("#addForm").serialize())
                        .then(function (response) {
                            var result = response.data.result;
                            if (result.code == 200) {
                                alert("添加成功");
                                $("#addModal").modal("hide");
                                vu.$options.methods.findAll();
                            } else {
                                alert(result.msg);
                            }
                        });
            },
            findAll: function () {
                axios.get('/Cata_findAll.action')
                        .then(function (response) {
                            vu.catalog = response.data.result.data;
                        });
            },
            deleteC: function (cata) {
                axios.get('/Cata_delete.action?catalog.caid=' + cata.caid)
                        .then(function (response) {
                            var result = response.data.result;
                            if (result.code == 200) {
                                vu.$options.methods.findAll();
                                alert("删除成功");
                            }

                        });
            }
        }

    });
</script>
</body>
</html>
