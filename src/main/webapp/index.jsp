<%@ taglib prefix="v-on" uri="/struts-tags" %>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%
    pageContext.setAttribute("APP_PATH", request.getContextPath());
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>by旅游</title>
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
<style>
    body {
        width: 1080px;
        margin-left: auto;
        margin-right: auto;
    }
</style>
<body>
<div class="container">
    <div id="user">
        <ul v-if="obj.isLogin">
            <div style="height: 50px; " class="row">
                <div style="text-align: left" class="col-md-3">
                    <img style="height: 50px; width: 180px" src="images/logo.jpg">
                </div>
                <div style="text-align: right;font-family: '微软雅黑', 'Microsoft Yahei', 'Helvetica Neue', Helvetica, Arial, sans-serif;font-size: 14px;
padding-top: 6px;padding-top: 10px;font-weight: bold;" class="col-md-9">
                    欢迎 <span style="color:red">{{obj.user.name}} </span><span v-if="obj.sex">先生</span><span
                        v-else>女士</span>
                    <a v-on:click="logOut" style="margin: 10px;cursor:pointer;">注销</a>
                    <a href="${APP_PATH}/toPage.action?pageName=user"
                       target="right"><span class="icon-caret-right"></span>个人中心</a>
                </div>
            </div>
        </ul>
        <ul class="nav navbar-nav navbar-right" v-else>
            <li><a v-on:click="registerModal()"><span class=" glyphicon glyphicon-user"></span>
                注册</a>
            </li>
            <li><a v-on:click="loginModal()"><span class="glyphicon glyphicon-log-in"></span>
                登录</a>
            </li>
        </ul>
        <!-- 注册窗口 -->
        <div id="register" class="modal fade" tabindex="-1">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-body">
                        <button class="close" data-dismiss="modal">
                            <span>&times;</span>
                        </button>
                    </div>
                    <div class="modal-title">
                        <h1 class="text-center">注册</h1>
                        <span v-if="msg.has" style="font-size: 40px ;color: red">{{msg.msg}}</span>
                    </div>
                    <div class="modal-body">
                        <form class="form-group" id="registerForm">
                            <div class="form-group">
                                <label>用户名</label>
                                <input class="form-control" type="text" name="user.name"
                                       placeholder="6-15位字母或数字">
                            </div>
                            <div class="form-group">
                                <label>账号</label>
                                <input class="form-control" type="text" name="user.account"
                                       placeholder="6-15位字母或数字">
                            </div>
                            <div class="form-group">
                                <label>密码</label>
                                <input class="form-control" name="user.password" type="password"
                                       placeholder="至少6位字母或数字">
                            </div>
                            <div class="form-group">
                                <label>再次输入密码</label>
                                <input class="form-control" name="user.password" type="password"
                                       placeholder="至少6位字母或数字">
                            </div>
                            <div class="radio">
                                <label>
                                    <input type="radio" name="user.gd" value="男" checked>
                                    男
                                </label>
                            </div>
                            <div class="radio">
                                <label>
                                    <input type="radio" name="user.gd" value="女">
                                    女
                                </label>
                            </div>
                            <%--<div class="form-group">
                                <label>邮箱</label>
                                <input class="form-control" type="email" placeholder="例如:123@123.com">
                            </div>--%>
                            <div class="text-right">
                                <span class="btn btn-primary" v-on:click="register()">提交</span>
                                <button class="btn btn-danger" data-dismiss="modal">取消</button>
                            </div>
                            <a href="" data-toggle="modal" data-dismiss="modal"
                               data-target="#login">已有账号？点我登录</a>
                        </form>
                    </div>
                </div>
            </div>
        </div>
        <!-- 登录窗口 -->
        <div id="login" class="modal fade">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-body">
                        <button class="close" data-dismiss="modal">
                            <span>&times;</span>
                        </button>
                    </div>
                    <div class="modal-title">
                        <h1 class="text-center">登录</h1>
                    </div>
                    <div class="modal-body">
                        <form class="form-group" id="loginForm">
                            <div class="form-group">
                                <label>用户名</label>
                                <input class="form-control" name="user.account" type="text"
                                       placeholder="account">
                            </div>
                            <div class="form-group">
                                <label>密码</label>
                                <input class="form-control" name="user.password" type="password"
                                       placeholder="password">
                            </div>
                            <div class="text-right">
                                <span class="btn btn-primary" v-on:click="login()">登录</span>
                                <button class="btn btn-danger" data-dismiss="modal">取消</button>
                            </div>
                            <a href="" data-toggle="modal" data-dismiss="modal"
                               data-target="#register">还没有账号？点我注册</a>
                        </form>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div id="tgs" class="row">
        <div class="row">
            <div class="col-md-3">
                <ul id="catalog" class="nav nav-pills nav-stacked " style="margin-top: 20px">
                    <li class="active"><a href="http://localhost:8080">Home</a></li>
                    <template v-for="cata in catas">
                        <li><a v-on:click="select($event,cata.caid)">{{cata.cname}}</a></li>
                    </template>
                </ul>
            </div>
            <div class="col-md-9">
                <div id="myCarousel" class="carousel slide">
                    <!-- 轮播（Carousel）指标 -->
                    <ol class="carousel-indicators">
                        <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
                        <li data-target="#myCarousel" data-slide-to="1"></li>
                        <li data-target="#myCarousel" data-slide-to="2"></li>
                    </ol>
                    <!-- 轮播（Carousel）项目 -->
                    <div class="carousel-inner" style="height: 300px">
                        <div class="item active">
                            <a v-on:click="showCarousel('2258777666981')">
                                <img src="images/a.jpg" alt="First slide">
                            </a>
                        </div>
                        <div class="item"><a v-on:click="showCarousel('2258784303691')">
                            <img src="images/b.jpg" alt="Second slide"></a>
                        </div>
                        <div class="item"><a v-on:click="showCarousel('2178780544443')">
                            <img src="images/c.jpg" alt="Third slide"></a>
                        </div>
                    </div>
                    <!-- 轮播（Carousel）导航 -->
                    <a class="left carousel-control" href="#myCarousel" role="button" data-slide="prev">
                        <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
                        <span class="sr-only">Previous</span>
                    </a>
                    <a class="right carousel-control" href="#myCarousel" role="button" data-slide="next">
                        <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
                        <span class="sr-only">Next</span>
                    </a>
                </div>
            </div>
        </div>

        <div class="row">
            <div class="row" style="margin : 20px; ">
                <div class="input-group  col-md-12" style="margin: 10px">
                    <input type="text" class="form-control input-lg " name="name"><span
                        class="input-group-addon btn btn-primary" v-on:click="findByName($event)">查地点</span>
                </div>
                <form class="form-inline row" style="margin: 10px ">
                    <div class="form-group col-md-2">
                        <div class="input-group">
                            <div class="input-group-addon">$</div>
                            <input type="text" class="form-control " id="SInputAmount">
                            <div class="input-group-addon">.00</div>
                        </div>
                    </div>
                    <div class="form-group col-md-2">
                        <div class="input-group">
                            <div class="input-group-addon">$</div>
                            <input type="text" class="form-control" id="BInputAmount">
                            <div class="input-group-addon">.00</div>
                        </div>
                    </div>
                    <span class="btn btn-primary col-md-1" v-on:click="findByPrice()">查询</span>
                </form>
                <template v-for="tg in tougs" style="margin: 20px">
                    <div style="float: left;margin: 15px">
                        <a v-on:click="showForm(tg)">
                            <img class="img-rounded " style="width: 200px;height: 150px;" :src="tg[0].img"><br>
                        </a>
                        <span>{{tg[0].name}}</span>
                        <span>{{tg[1].start}}-{{tg[1].stop}}</span>
                    </div>
                </template>

                <%--<table class="table table-hover ">--%>
                <%-- <th class="row">
                 <td class=" col-md-2">旅行团名字</td>
                 <td class=" col-md-1">限定人数</td>
                 <td class=" col-md-1">已报名人数</td>
                 <td class=" col-md-2">开团时间</td>
                 <td class=" col-md-1">出发地</td>
                 <td class=" col-md-1">目的地</td>
                 <td class=" col-md-2">限时优惠价</td>
                 <td class=" col-md-2"></td>
                 </th>--%>
                <%--<tr class="row" v-for="tg in tougs">
                    <td class=" col-md-2">{{tg[0].name}}</td>
                    <td class=" col-md-1">{{tg[0].limit}}</td>
                    <td class=" col-md-1">{{tg[0].real}}</td>
                    <td class=" col-md-2">{{tg[0].timeStr}}</td>
                    <td class=" col-md-1">{{tg[1].start}}</td>
                    <td class=" col-md-1">{{tg[1].stop}}</td>
                    <td class=" col-md-2">{{tg[0].fprice}}</td>
                    <td class=" col-md-2"><span class="btn btn-primary " v-on:click="showForm(tg)">查看详细</span>
                    </td>
                </tr>--%>
                <%--</table>--%>
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
                                <h1 class="text-center"></h1>
                                <!--轮播图-->
                                <div class="container">
                                    <section id="dg-container" style="height: 350px" class="dg-container">
                                        <div id="Carousel" class="dg-wrapper">
                                            <%-- <a href="#"><img src="${APP_PATH}/images/b.jpg" alt="img1">
                                             </a>
                                             <a href="#"><img src="${APP_PATH}/images/a.jpg">
                                             </a>
                                             <a href="#"><img src="${APP_PATH}/images/b.jpg">
                                             </a>
                                             <a href="#"><img src="${APP_PATH}/images/a.jpg">
                                                 <div>http://circlemeetups.com/</div>
                                             </a>--%>
                                        </div>
                                    </section>
                                </div>

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
                                        <label class="form-control" name="tourGroup.limit">限定人数:</label>
                                    </div>
                                    <div class="form-group">
                                        <label name="tourGroup.real">已报名人数:</label>
                                    </div>
                                    <div class="form-group">
                                        <label name="route">路线:</label>
                                        <label name="route.msg"></label>
                                    </div>

                                    <div class="form-group">
                                        <label name="ciceroni">导游:</label>
                                    </div>
                                    <div class="text-right">
                                        <input type="hidden" name="tourGroup.tgid">
                                        <input type="hidden" name="tourGroup.price">
                                        <span class="btn btn-primary" v-on:click="createOrder($event)">下单</span>
                                        <button class="btn btn-danger" data-dismiss="modal">关闭</button>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<script>
    var u = new Vue({
        el: "#user",
        data: {
            obj: {
                isLogin: false,
                sex: false,
                user: {}
            },
            msg: {
                has: false,
                msg: "",
            },
        },
        mounted(){
            this.obj.user.name = $.cookie('user_name');
            // this.obj.user.gd = $.cookie('user_gd');
            if ($.trim($.cookie('isLogin')) == $.trim("true")) {
                this.obj.isLogin = true;
                this.obj.sex = $.trim($.cookie('user_gd')) == $.trim("男") ? true : false;
            } else {
                this.obj.isLogin = false;
            }
        },
        methods: {
            logOut: function () {
                if (confirm("您确定退出登录么?")) {
                    $.cookie('user_name', {expires: -1});
                    $.cookie('user_gd', {expires: -1});
                    $.cookie('isLogin', {expires: -1});
                    $.cookie('user_id', {expires: -1});
                    this.obj.isLogin = false;
                }
            },
            loginModal: function () {
                $("#login").modal();
            },
            registerModal: function () {
                $("#register").modal();
            },
            login: function () {
                axios.post('/user_login.action', $("#loginForm").serialize())
                        .then(function (response) {
                            var result = response.data.result;
                            if (result.code == 200) {
                                u.$options.methods.loginSuccess(result, 1);
                                $("#login").modal("hide");
                            } else if (result.code == 300) {
                                alert(result.msg);
                            } else {
                                alert("系统出错请联系管理员");
                            }
                        })
                        .catch(function (error) {
                            console.log(error);
                        });
            },
            register: function () {
                var pas;
                $("#registerForm").find("input[name='user.password']").each(function () {
                            if (pas == null) {
                                pas = $(this).val();
                            } else {
                                if ($.trim(pas) != $.trim($(this).val())) {
                                    u.msg.has = true;
                                    u.msg.msg = "两次密码输入不一致";
                                    return;
                                } else {
                                    u.msg.has = false;
                                }
                            }
                        }
                )
                axios.post("/user_register.action", $("#registerForm").serialize()).then(function (response) {
                    var result = response.data.result;
                    if (result.code == 200) {
                        u.$options.methods.loginSuccess(result, 2);
                        $("#register").modal("hide");
                    } else {
                        alert(result.msg);
                    }
                });
            },
            loginSuccess: function (result, msg) {
                u.obj.isLogin = true;
                u.obj.user = result.data;
                if (msg == 1) {
                    alert("尊敬的会员" + result.data.name + "欢迎登录");
                } else if (msg == 2) {
                    alert("恭喜" + result.data.name + "注册成功");
                }
                $.cookie('user_name', result.data.name, {expires: 7, path: '/'});
                $.cookie('user_gd', result.data.gd, {expires: 7, path: '/'});
                $.cookie('isLogin', true, {expires: 7, path: '/'});
                $.cookie('user_id', result.data.uid, {expires: 7, path: '/'});
            },
        }
    });

    var vu = new Vue({
        el: "#tgs",
        data: {
            tougs: [],
            isClick: false,
            data: null,
            imgs: [],
            catas: []
        },
        mounted(){
            axios.get('/TG_findTourGroup.action')
                    .then(function (response) {
                        vu.tougs = response.data.result.data;
                    });
            axios.get('/Cata_findAll.action').then(function (response) {
                vu.catas = response.data.result.data;
            });
        },
        methods: {
            showForm: function (tg) {
                console.log(tg);
                $("#tgForm").find("input[name='tourGroup.tgid']").val(tg[0].tgid);
                $("#tgForm").find("input[name='tourGroup.price']").val(tg[0].fprice);
                $("#tgForm").find("label[name='tourGroup.name']").html("旅行团名字: " + tg[0].name);
                $("#tgForm").find("label[name='tourGroup.fprice']").html("价格: " + tg[0].fprice);
                $("#tgForm").find("label[name='tourGroup.time']").html("开团时间: " + tg[0].timeStr);
                $("#tgForm").find("label[name='tourGroup.limit']").html("限定人数: " + tg[0].limit);
                $("#tgForm").find("label[name='tourGroup.real']").html("已报名人数: " + tg[0].real + "人");
                $("#tgForm").find("label[name='route']").html("路线:" + tg[1].start + "-" + tg[1].stop);
                $("#tgForm").find("label[name='route.msg']").html("路线介绍:" + tg[1].msg);
                $("#tgForm").find("label[name='ciceroni']").html("导游:" + tg[2].name);
                axios.get('/Ca_findByTgid.action?carousel.tgid=' + tg[0].tgid)
                        .then(function (response) {
                            console.log(response.data.result.data);
                            /* vu.imgs = response.data.result.data;*/
                            var e = $("#Carousel");
                            $.each(response.data.result.data, function () {
                                e.append($("<a href='#'><img src='" + this.imageUrl + "'></a>"));
                            });
                            $('#dg-container').gallery({
                                autoplay: true
                            });
                            $("#show").modal();
                        });


            },
            findByName: function (ele) {
                axios.get('/TG_findByName.action?tourGroup.name=' + $(ele.target).prev().val())
                        .then(function (response) {
                            vu.tougs = response.data.result.data;
                        });
            },
            findByPrice: function () {
                var SInputAmount = $("#SInputAmount").val();
                var BInputAmount = $("#BInputAmount").val();
                var findCondition = "{SInputAmount: '" + SInputAmount + "',BInputAmount:'" + BInputAmount + "'}";
                axios.get('/TG_findByPrice.action?tourGroup.findCondition=' + findCondition)
                        .then(function (response) {
                            if (response.data.result.code != 200) {
                                alert(response.data.result.msg);
                            } else {
                                vu.tougs = response.data.result.data;
                            }
                        });
            },
            createOrder: function (ele) {
                var params = new URLSearchParams();
                $(ele.currentTarget).find("input[name='tourGroup.tgid']").val();
                var e = $(ele.currentTarget).prev();
                params.append('order.tgid', e.prev().val());
                params.append('order.price', e.val());
                axios.post("/O_createOrder.action", params).then(function (response) {
                    var result = response.data.result;
                    if (result.code == 200) {
                        console.log(result);
                        alert("下单成功");
                    } else {
                        alert(result.msg);
                    }
                });
            },
            getPath: function (url) {
                return "/" + url;
            },
            select: function (ele, caid) {

                axios.get('/TG_findbyCaid.action?tourGroup.caid=' + caid)
                        .then(function (response) {
                            vu.tougs = response.data.result.data;
                        });
                $.each($("#catalog").find("li"), function () {
                    $(this).removeClass("active");
                });
                $(ele.currentTarget).parent().attr("class", "active");
            },
            showCarousel: function (tgid) {
                axios.get('/TG_findByTgid.action?tourGroup.tgid=' + tgid)
                        .then(function (response) {
                            console.log(response.data.result.data);
                            vu.$options.methods.showForm(response.data.result.data[0]);
                        });
            }
        }

    })
</script>
</body>
</html>
