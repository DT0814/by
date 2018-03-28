<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%
    pageContext.setAttribute("APP_PATH", request.getContextPath());
%>
<html>
<head>
    <title>Title</title>
    <script type="text/javascript" src="${APP_PATH}/js/vue.js"></script>
    <script type="text/javascript" src="${APP_PATH}/js/axios.js"></script>
</head>
<body>
<script>
    window.onload = function () {
        axios.get('/user_findAllUser.action')
                .then(function (response) {
                    console.log(response.data);
                });
    }
</script>
</body>
</html>
