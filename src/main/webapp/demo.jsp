<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%
    pageContext.setAttribute("APP_PATH", request.getContextPath());
%>
<html>
<head>
    <link rel="stylesheet" type="text/css"
          href="${APP_PATH}/css/bootstrap.min.css"/>
    <script type="text/javascript"
            src="${APP_PATH}/js/jquery-1.12.4.js"></script>
    <script type="text/javascript"
            src="${APP_PATH}/js/bootstrap.min.js"></script>
</head>
<body>
<h2>Hello World!</h2>

<body>
<h1>user_addUser</h1>
<form action="/user_addUser.action" method="post">
    用户名：<input type="text" name="user.account" value="123123123"/>
    密 码：<input type="text" name="user.password" value="1541515151"/>
    <input type="submit" value="提交"/>
</form>
<h1>User_Login</h1>
<form action="/user_login.action" method="post">
    用户名：<input type="text" name="user.account" value="123123123"/>
    密 码：<input type="password" name="user.password" value="1541515151"/>
    <input type="submit" value="提交"/>
</form>
<h1>TEAM</h1>
添加
<form action="/team_addTeam.action" method="post">
    账号：<input type="text" name="team.account"/>
    密 码: <input type="password" name="team.pass">
    名字：<input type="text" name="team.name"/>
    简介：<input type="text" name="team.introduce"/>
    <input type="submit" value="提交"/>
</form>
更新
<form action="/team_updateTeam.action" method="post">
    账号：<input type="text" name="team.account"/>
    密 码: <input type="password" name="team.pass">
    名字：<input type="text" name="team.name"/>
    简介：<input type="text" name="team.introduce"/>
    <input type="submit" value="提交"/>
</form>
<h1>
    ciceroni
</h1>
添加
<form action="/Ci_add.action" method="post">
    名字：<input type="text" name="ciceroni.name"/>
    团队Id: <input type="text" name="ciceroni.tid">
    简介：<input type="text" name="ciceroni.introduce"/>
    <input type="submit" value="提交"/>
</form>
更新
<form action="/Ci_update.action" method="post">
    名字：<input type="text" name="ciceroni.name"/>
    团队Id: <input type="text" name="ciceroni.tid">
    简介：<input type="text" name="ciceroni.introduce"/>
    <input type="submit" value="提交"/>
</form>
<h1>
    Route
</h1>
添加
<form action="/R_add.action" method="post">
    起始：<input type="text" name="route.start"/>
    终止 <input type="text" name="route.stop">
    团队ID：<input type="text" name="route.tid"/>
    <input type="submit" value="提交"/>
</form>
更新
<form action="/R_update.action" method="post">
    起始：<input type="text" name="route.start"/>
    终止 <input type="text" name="route.stop">
    <input type="submit" value="提交"/>
</form>

<h1>
    ORDERS
</h1>
添加
<form action="/O_createOrder.action" method="post">
    用户ID：<input type="text" name="order.uid"/>
    价格 <input type="text" name="order.price">
    旅行团ID <input type="text" name="order.tgid">
    <input type="submit" value="提交"/>
</form>
完成
<form action="/O_FOrder.action" method="post">
   订单ID：<input type="text" name="order.oid" />
    <input type="submit" value="提交"/>
</form>
付款
<form action="/O_POrder.action" method="post">
    订单ID：<input type="text" name="order.oid"/>
    <input type="submit" value="提交"/>
</form>

<h1>
    tourGroup
</h1>
添加
<form action="/TG_add.action" method="post">
    导游id：<input type="text" name="tourGroup.cid">
    团队ID <input type="text" name="tourGroup.tid">
    原价 <input type="text" name="tourGroup.price">
    优惠价格<input type="text" name="tourGroup.Fprice">
    开团时间<input type="text" name="tourGroup.time">
    限定人数<input type="text" name="tourGroup.limit">
    已报名人数<input type="text" name="tourGroup.real">
    团名字<input type="text" name="tourGroup.name">
    <input type="submit" value="提交"/>
</form>
修改
<form action="/TG_update.action" method="post">
    ID：<input type="text" name="tourGroup.tgid"/>
    导游id：<input type="text" name="tourGroup.cid"/>
    团队ID <input type="text" name="tourGroup.tid">
    原价 <input type="text" name="tourGroup.price">
    优惠价格<input type="text" name="tourGroup.Fprice"/>
    开团时间<input type="text" name="tourGroup.time"/>
    限定人数<input type="text" name="tourGroup.limit"/>
    已报名人数<input type="text" name="tourGroup.real"/>
    团名字<input type="text" name="tourGroup.name"/>
    <input type="submit" value="提交"/>
</form>
<%--付款
<form action="/TG_POrder.action" method="post">
    订单ID：<input type="text" name="order.oid"/>
    <input type="submit" value="提交"/>
</form>--%>


</body>
<script type="text/javascript">
</script>
</body>
</html>
