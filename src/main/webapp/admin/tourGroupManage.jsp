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
    <h1>旅行团管理</h1>
    <button type="button" v-on:click="showCreateTg()" class="btn btn-default btn-lg active">创建旅行团</button>
    <table class="table table-hover ">
        <th class="row">
        <td class=" col-md-2">旅行团名字</td>
        <td class=" col-md-1">限定人数</td>
        <td class=" col-md-1">已报名人数</td>
        <td class=" col-md-2">开团时间</td>
        <td class=" col-md-1">出发地</td>
        <td class=" col-md-1">目的地</td>
        <td class=" col-md-2">限时优惠价</td>
        <td class=" col-md-2">分类</td>
        <td class=" col-md-2"></td>
        </th>
        <tr class="row" v-for="tg in tougs">
            <td class=" col-md-2">{{tg[0].name}}</td>
            <td class=" col-md-1">{{tg[0].limit}}</td>
            <td class=" col-md-1">{{tg[0].real}}</td>
            <td class=" col-md-2">{{tg[0].timeStr}}</td>
            <td class=" col-md-1">{{tg[1].start}}</td>
            <td class=" col-md-1">{{tg[1].stop}}</td>
            <td class=" col-md-2">{{tg[0].fprice}}</td>
            <td class=" col-md-2">{{tg[4].cname}}</td>
            <td class=" col-md-2"><span class="btn btn-primary " v-on:click="showForm(tg)">查看详细</span>
            </td>
        </tr>
    </table>
    <!--createTg-->
    <div id="createTg" class="modal fade">
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
                    <form class="form-group" id="createTgForm">
                        <div class="form-group">
                            <input name="file" type="file" value="选择图片"/>
                        </div>
                        <div class="form-group">
                            <label>名称:</label>
                            <input class="form-control" name="tourGroup.name" type="text">
                        </div>
                        <div class="form-group">
                            <label>价格:</label>
                            <input class="form-control" name="tourGroup.fprice" type="text">
                        </div>
                        <div class="form-group">
                            <label>开团时间:</label>
                            <input class="form-control" name="tourGroup.time" type="text">
                        </div>
                        <div class="form-group">
                            <label>限定人数:</label>
                            <input class="form-control" name="tourGroup.limit" type="text">
                        </div>
                        <div class="form-group">
                            <label name="route">路线:</label>
                            <select class="form-control">
                                <template v-for="r in Rs">
                                    <option v-on:click="changeRoute($event,r.rid)">
                                        {{r.start}}-{{r.stop}}
                                    </option>
                                </template>
                            </select>
                            <input class="form-control" name="tourGroup.rid" type="hidden">
                        </div>
                        <div class="form-group">
                            <label name="ciceroni">导游:</label>
                            <select class="form-control">
                                <template v-for="c in Cs">
                                    <option v-on:click="changeCiceroni($event,c.cid)">
                                        {{c.name}}
                                    </option>
                                </template>
                            </select>
                            <input class="form-control" name="tourGroup.cid" type="hidden">
                        </div>
                        <div class="form-group">
                            <label name="catalog">分类:</label>
                            <select class="form-control">
                                <template v-for="cata in Catas">
                                    <option v-on:click="changeCatalog($event,cata.caid)">
                                        {{cata.cname}}
                                    </option>
                                </template>
                            </select>
                            <input class="form-control" name="tourGroup.caid" type="hidden">
                        </div>
                        <input class="form-control" name="tourGroup.tid" type="hidden">
                        <div class="text-right">
                            <span class="btn btn-primary" v-on:click="create()">创建</span>
                            <button class="btn btn-danger" data-dismiss="modal">关闭</button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>

    <!-- 旅行团详细信息 -->
    <div id="show" class="modal fade">
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
                    <form class="form-group" id="tgForm">
                        <div class="form-group">
                            <label>名称:</label>
                            <input class="form-control" name="tourGroup.name" type="text">
                        </div>
                        <div class="form-group">
                            <label>价格:</label>
                            <input class="form-control" name="tourGroup.fprice" type="text">
                        </div>
                        <div class="form-group">
                            <label>开团时间:</label>
                            <input class="form-control" name="tourGroup.time" type="text">
                        </div>
                        <div class="form-group">
                            <label>限定人数:</label>
                            <input class="form-control" name="tourGroup.limit" type="text">
                        </div>
                        <div class="form-group">
                            <label name="tourGroup.real">已报名人数:</label>
                        </div>
                        <div class="form-group">
                            <label name="route">路线:</label>
                            <select class="form-control" id="updateRouteSelect">
                                <template v-for="r in Rs">
                                    <template v-if="iscurRentRid(r.rid)">
                                        <option selected="selected" v-on:click="changeRoute($event,r.rid)">
                                            {{r.start}}-{{r.stop}}
                                        </option>
                                    </template>
                                    <template v-else>
                                        <option v-on:click="changeRoute($event,r.rid)">
                                            {{r.start}}-{{r.stop}}
                                        </option>
                                    </template>
                                </template>
                            </select>
                            <input class="form-control" name="tourGroup.rid" type="hidden">
                        </div>
                        <div class="form-group">
                            <label name="catalog">分类:</label>
                            <select class="form-control" id="updateCatalogSelect">
                                <template v-for="cata in Catas">
                                    <template v-if="iscurrentCaid(cata.caid)">
                                        <option selected="selected" v-on:click="changeCatalog($event,cata.caid)">
                                            {{cata.cname}}
                                        </option>
                                    </template>
                                    <template v-else>
                                        <option v-on:click="changeCatalog($event,cata.caid)">
                                            {{cata.cname}}
                                        </option>
                                    </template>
                                </template>
                            </select>
                            <input class="form-control" name="tourGroup.caid" type="hidden">
                        </div>
                        <div class="form-group">
                            <label name="ciceroni">导游:</label>
                            <select class="form-control" id="updateCiceroniSelect">
                                <template v-for="c in Cs">
                                    <template v-if="iscurrentCid(c.cid)">
                                        <option selected="selected" v-on:click="changeCiceroni($event,c.cid)">{{c.name}}
                                        </option>
                                    </template>
                                    <template v-else>
                                        <option v-on:click="changeCiceroni($event,c.cid)">
                                            {{c.name}}
                                        </option>
                                    </template>
                                </template>
                            </select>
                            <input class="form-control" name="tourGroup.cid" type="hidden">
                            <input class="form-control" name="tourGroup.tgid" type="hidden">
                        </div>
                        <div class="text-right">
                            <span class="btn btn-primary" v-on:click="update()">更新</span>
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
            tougs: [],
            Cs: [],
            Rs: [],
            Catas: [],
            currentRid: "",
            currentCid: "",
            currentCaid: "",
            data: null
        },
        mounted(){
            this.$options.methods.findAll();
        },
        methods: {
            showForm: function (tg) {
                $("#tgForm").find("input[name='tourGroup.name']").val(tg[0].name);
                $("#tgForm").find("input[name='tourGroup.tgid']").val(tg[0].tgid);
                $("#tgForm").find("input[name='tourGroup.fprice']").val(tg[0].fprice);
                $("#tgForm").find("input[name='tourGroup.time']").val(tg[0].time);
                $("#tgForm").find("input[name='tourGroup.limit']").val(tg[0].limit);
                $("#tgForm").find("label[name='tourGroup.real']").html("已报名人数:" + tg[0].real + "人");
                $("#tgForm").find("label[name='route']").html("路线:" + tg[1].start + "-" + tg[1].stop);
                $("#tgForm").find("input[name='tourGroup.rid']").val(tg[1].rid);
                $("#tgForm").find("label[name='ciceroni']").html("导游:" + tg[2].name);
                $("#tgForm").find("input[name='tourGroup.cid']").val(tg[2].cid);
                vu.currentCid = tg[2].cid;
                vu.currentRid = tg[1].rid;
                vu.currentCaid = tg[4].caid;
                axios.get("/Ci_findByTid.action?ciceroni.tid=" + $.cookie('team_tid'))
                        .then(function (response) {
                            var result = response.data.result;
                            if (result.code = 200) {
                                vu.Cs = result.data;
                            }
                        });
                axios.get("/R_findByTid.action?route.tid=" + $.cookie('team_tid'))
                        .then(function (response) {
                            var result = response.data.result;
                            if (result.code = 200) {
                                vu.Rs = result.data;
                            }
                        });
                axios.get("/Cata_findAll.action")
                        .then(function (response) {
                            var result = response.data.result;
                            if (result.code = 200) {
                                vu.Catas = result.data;
                            }
                        });
                $("#show").modal();
            },
            update: function () {
                axios.post("/TG_update.action", $("#tgForm").serialize()).then(function (response) {
                    var result = response.data.result;
                    if (result.code = 200) {
                        alert("更新成功");
                        vu.$options.methods.findAll();
                        $("#show").modal("hide");
                    } else {
                        alert(result.data);
                    }
                });
            },
            showCreateTg: function () {
                axios.get("/Ci_findByTid.action?ciceroni.tid=" + $.cookie('team_tid'))
                        .then(function (response) {
                            var result = response.data.result;
                            if (result.code = 200) {
                                vu.Cs = result.data;
                                $("#createTgForm").find("input[name='tourGroup.cid']").val(vu.Cs[0].cid);
                            }
                        });
                axios.get("/R_findByTid.action?route.tid=" + $.cookie('team_tid'))
                        .then(function (response) {
                            var result = response.data.result;
                            if (result.code = 200) {
                                vu.Rs = result.data;
                                $("#createTgForm").find("input[name='tourGroup.rid']").val(vu.Rs[0].rid);
                            }
                        });
                axios.get("/Cata_findAll.action")
                        .then(function (response) {
                            var result = response.data.result;
                            if (result.code = 200) {
                                //console.log(result.data);
                                vu.Catas = result.data;
                                $("#createTgForm").find("input[name='tourGroup.caid']").val(vu.Catas[0].caid);
                            }
                        });
                $("#createTg").modal();
            },
            create: function () {
                $("#createTgForm").find("input[name='tourGroup.tid']").val($.cookie('team_tid'));
                var formData = new FormData($("#createTgForm")[0]);
                let config = {
                    headers: {'Content-Type': 'multipart/form-data'}
                };  //添加请求头
                axios.post("/TG_add.action", formData, config).then(function (response) {
                    var result = response.data.result;
                    if (result.code = 200) {
                        if (confirm("创建成功,继续创建请点击确定")) {
                        } else {
                            $("#createTg").modal("hide");
                            vu.$options.methods.findAll();
                        }
                    } else {
                        alert(result.data);
                    }
                });
            },
            iscurRentRid: function (rid) {
                return $.trim(rid) == $.trim(this.currentRid);
            },
            iscurrentCid: function (cid) {
                return $.trim(cid) == $.trim(this.currentCid);
            },
            iscurrentCaid: function (caid) {
                return $.trim(caid) == $.trim(this.currentCaid);
            },
            changeRoute: function (ele, rid) {
                $(ele.currentTarget).parent().next().val(rid);
            },
            changeCiceroni: function (ele, cid) {
                $(ele.currentTarget).parent().next().val(cid);
            },
            changeCatalog: function (ele, caid) {
                $(ele.currentTarget).parent().next().val(caid);
            },
            findAll: function (oid) {
                axios.get('/TG_findByTid.action?tourGroup.tid=' + $.cookie('team_tid'))
                        .then(function (response) {
                            vu.tougs = response.data.result.data;
                        });
            }
        }
    })
</script>
</body>
</html>
