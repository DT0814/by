<%--
  Created by IntelliJ IDEA.
  User: DT
  Date: 2018/4/3
  Time: 9:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>个人中心</title>
    <link rel="stylesheet" type="text/css"
          href="${APP_PATH}/css/bootstrap.css"/>
    <script type="text/javascript" src="${APP_PATH}/js/jquery-1.12.4.js"></script>
    <script type="text/javascript" src="${APP_PATH}/js/jquery.cookie.js"></script>
    <script type="text/javascript" src="${APP_PATH}/js/bootstrap.js"></script>
    <script type="text/javascript" src="${APP_PATH}/js/vue.js"></script>
    <script type="text/javascript" src="${APP_PATH}/js/axios.js"></script>
    <script type="text/javascript" src="${APP_PATH}/js/jquery.gallery.js"></script>
    <script type="text/javascript" src="${APP_PATH}/js/modernizr.custom.53451.js"></script>
    <link rel="stylesheet" type="text/css" href="${APP_PATH}/css/style.css"/>
</head>
<body>
<div id="bd">
    <h1>个人信息管理</h1>
    <a v-on:click="myOrder()"><span class=" glyphicon glyphicon-user"></span>
        我的订单</a>
    <a v-on:click="myInf()"><span class=" glyphicon glyphicon-user"></span>
        个人信息</a>
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
    <!--订单展示-->
    <div v-if="myOr">
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
                    <template v-if="isPay(o.status)">
                        <span class="btn btn-primary " v-on:click="pay(o.oid)">支付</span>
                    </template>
                </td>
            </tr>
        </table>
    </div>

    <!-- 修改展示窗口 -->
    <div v-if="myIn">
        <form class="form-group" id="updateForm">
            <div class="form-group">
                <label>用户名</label>
                <input class="form-control" type="text" name="user.name">
            </div>
            <div class="form-group">
                <label>账号</label>
                <input class="form-control" type="text" name="user.account" placeholder="6-15位字母或数字">
            </div>
            <div class="radio">
                <label>
                    <input type="radio" name="user.gd" value="男">
                    男
                </label>
            </div>
            <div class="radio">
                <label>
                    <input type="radio" name="user.gd" value="女">
                    女
                </label>
            </div>
            <input type="hidden" name="user.uid">
            <span class="btn btn-primary" v-on:click="update()">提交</span>
        </form>
    </div>
</div>


<script>
    var vu = new Vue({
        el: "#bd",
        data: {
            myIn: true,
            myOr: false,
            user: {},
            orders: []
        },
        mounted(){
            this.$options.methods.findByUid();
        },
        methods: {
            findByUid: function () {
                axios.get('/user_findByUid.action?user.uid=' + $.cookie('user_id')).then(function (response) {
                    var e = $("#updateForm");
                    var data = response.data.result;
                    console.log(data);
                    vu.user = data.data;
                    if (data.code == 200) {
                        console.log(data);
                        $("#updateForm").find("input[name='user.name']").val(vu.user.name);
                        $("#updateForm").find("input[name='user.account']").val(vu.user.account);
                        $("#updateForm").find("input[name='user.uid']").val(vu.user.uid);
                        $.each($("#updateForm").find("input[name='user.gd']"), function () {
                            if ($(this).val() == vu.user.gd) {
                                $(this).attr("checked", "checked");
                            }
                        });
                    } else {
                        alert(data.msg);
                    }
                });
            },
            update: function () {
                axios.post('/user_updateUser.action', $("#updateForm").serialize()).then(function (response) {
                    var data = response.data.result;
                    if (data.code == 200) {
                        console.log(data);
                        alert("更新成功")
                    } else {
                        alert(data.msg);
                    }
                });
            },
            myOrder: function () {
                vu.myIn = false;
                vu.myOr = true;
                axios.get('/O_findByUid.action').then(function (response) {
                    vu.orders = response.data.result.data;
                    console.log(response.data.result.data);
                });
            },
            myInf: function () {
                vu.myIn = true;
                vu.myOr = false;
                $("#updateForm").find("input[name='user.name']").val(vu.user.name);
                $("#updateForm").find("input[name='user.account']").val(vu.user.account);
                $("#updateForm").find("input[name='user.uid']").val(vu.user.uid);
                $.each($("#updateForm").find("input[name='user.gd']"), function () {
                    if ($(this).val() == vu.user.gd) {
                        $(this).attr("checked", "checked");
                    }
                });
            },
            showForm: function (o) {
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
            isPay: function (status) {
                return $.trim(status) == $.trim("1");
            },
            pay: function (oid) {
                axios.get('/O_POrder.action?order.oid=' + oid).then(function (response) {
                    var data = response.data.result;
                    if (data.code == 200) {
                        console.log(data);
                        alert("支付成功");
                        vu.$options.methods.myOrder();
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
