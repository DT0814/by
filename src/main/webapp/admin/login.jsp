<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
    <style>body {
        padding-top: 60px;
    }</style>
    <link href="${APP_PATH}/css/bootstrap.css" rel="stylesheet"/>
    <link href="${APP_PATH}/admin/css/login-register.css" rel="stylesheet"/>
    <link rel="stylesheet" href="http://netdna.bootstrapcdn.com/font-awesome/4.0.3/css/font-awesome.css">
    <script src="${APP_PATH}/js/jquery-1.12.4.js" type="text/javascript"></script>
    <script type="text/javascript" src="${APP_PATH}/js/jquery.cookie.js"></script>
    <script src="${APP_PATH}/js/bootstrap.js" type="text/javascript"></script>
    <script type="text/javascript" src="${APP_PATH}/js/vue.js"></script>
    <script type="text/javascript" src="${APP_PATH}/js/axios.js"></script>
</head>
<body>
<div class="container" id="login">
    <div class="row">
        <div class="col-sm-4"></div>
        <div class="col-sm-4">
            <a v-on:click="showLogin">登录</a>
            <a v-on:click="showregistert">注册</a>
        </div>
        <div class=" col-sm-4">
        </div>
    </div>

    <div class="modal fade login" id="loginModal">
        <div class="modal-dialog login animated">
            <div class="modal-content">
                <div class="modal-header">
                    <%--    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>--%>
                    <h4 class="modal-title">Login with</h4>
                </div>
                <div class="modal-body">
                    <div class="box">
                        <div class="content">
                            <div class="error"></div>
                            <div class="form loginBox">
                                <form id="LoginForm">
                                    <input class="form-control" type="text" placeholder="account" name="team.account">
                                    <input class="form-control" type="password" placeholder="Password"
                                           name="team.pass">
                                    <span class="btn btn-default btn-login" v-on:click="login">登录</span>
                                </form>
                            </div>
                        </div>
                    </div>
                    <div class="forgot login-footer">
                        <span>没有账号<a v-on:click="showregistert">去注册</a> </span>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <div class="modal fade login" id="registerModal">
        <div class="modal-dialog login animated">
            <div class="modal-content">
                <div class="modal-header">
                    <%--  <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>--%>
                    <h4 class="modal-title">Register with</h4>
                    <span v-if="msg.has" style="font-size: 40px ;color: red">{{msg.msg}}</span>
                </div>
                <div class="modal-body">
                    <div class="box">
                        <div class="content registerBox">
                            <div class="form">
                                <form id="registerForm">
                                    <input class="form-control" type="text" placeholder="name" name="team.name">
                                    <input class="form-control" type="text" placeholder="account" name="team.account">
                                    <input class="form-control" type="password" placeholder="Password"
                                           name="team.pass">
                                    <input class="form-control" type="password" placeholder="Repeat Password"
                                           name="team.pass">
                                    <span class="btn btn-default btn-register" v-on:click="registert">注册</span>
                                </form>
                            </div>
                        </div>
                    </div>
                    <div class="forgot login-footer">
                        <span>已有账号<a v-on:click="showLogin">去登录</a> </span>
                    </div>
                </div>
            </div>
        </div>
    </div>


</div>
<script>
    var vu = new Vue({
        el: "#login",
        data: {
            msg: {
                has: false,
                msg: "",
            }
        },
        mounted(){
            $("#loginModal").modal({backdrop: 'static', keyboard: false});

        },
        methods: {
            showLogin: function () {
                $("#registerModal").modal("hide");
                $("#loginModal").modal({backdrop: 'static', keyboard: false});
            },
            showregistert: function () {
                $("#loginModal").modal("hide");
                $("#registerModal").modal({backdrop: 'static', keyboard: false});
            },
            login: function () {
                axios.post('/team_login.action', $("#LoginForm").serialize())
                        .then(function (response) {
                            var result = response.data.result;
                            if (result.code == 200) {
                                alert("登陆成功");
                                vu.$options.methods.loginSuccess(result.data, 1);
                            } else {
                                alert(result.msg);
                            }
                        });
            },
            registert: function () {
                var pas;
                console.log($("#registerForm").serialize());
                $("#registerForm").find("input[name='team.pass']").each(function () {
                            if (pas == null) {
                                pas = $(this).val();
                                console.log(pas);
                            } else {
                                if ($.trim(pas) != $.trim($(this).val())) {
                                    vu.msg.has = true;
                                    vu.msg.msg = "两次密码输入不一致";
                                    console.log($(this).val() + "sss");
                                    return;
                                } else {
                                    vu.msg.has = false;
                                }
                            }
                        }
                )
                if ($.trim(vu.msg.has) == $.trim("true")) {
                    return;
                }
                axios.post("/team_register.action", $("#registerForm").serialize()).then(function (response) {
                    var result = response.data.result;
                    if (result.code == 200) {
                        vu.$options.methods.loginSuccess(result.data, 2);
                    } else {
                        alert(result.msg);
                    }
                });

            },
            loginSuccess: function (data, status) {
                if (status == 1) {
                    $.cookie('team_name', data.name, {expires: 7, path: '/'});
                    $.cookie('team_tid', data.tid, {expires: 7, path: '/'});
                    $.cookie('team_isLogin', true, {expires: 7, path: '/'});
                    location.href = "http://localhost:8080/admin";
                } else if (status == 2) {
                    if (confirm("恭喜您注册成功!点击确定进入管理页面")) {
                        $.cookie('team_name', data.name, {expires: 7, path: '/'});
                        $.cookie('team_tid', data.tid, {expires: 7, path: '/'});
                        $.cookie('team_isLogin', true, {expires: 7, path: '/'});
                        location.href = "http://localhost:8080/admin";
                    } else {
                        $("#loginModal").modal({backdrop: 'static', keyboard: false});
                    }
                }
            }
        }
    });
</script>
</body>
</html>

