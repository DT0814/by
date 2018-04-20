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
                                    <input class="form-control" type="text" placeholder="account" name="admin.anum">
                                    <input class="form-control" type="password" placeholder="Password"
                                           name="admin.apss">
                                    <span class="btn btn-default btn-login" v-on:click="login">登录</span>
                                </form>
                            </div>
                        </div>
                    </div>
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
                $("#loginModal").modal({backdrop: 'static', keyboard: false});
            },
            login: function () {
                axios.post('/ad_login.action', $("#LoginForm").serialize())
                        .then(function (response) {
                            var result = response.data.result;
                            if (result.code == 200) {
                                alert("登陆成功");
                                location.href = "http://localhost:8080/superAdmin";
                            } else {
                                alert(result.msg);
                            }
                        });
            },
        }
    });
</script>
</body>
</html>

