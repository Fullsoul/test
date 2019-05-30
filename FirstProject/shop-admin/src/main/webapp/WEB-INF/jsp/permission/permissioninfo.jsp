<%--
  Created by IntelliJ IDEA.
  User: tomcat
  Date: 2019/2/24
  Time: 23:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%@include file="../includes/include.jsp"%>
<div class="layui-container">



<table class="layui-table " lay-even lay-skin="line" lay-size="lg">
    <colgroup>
        <col width="150">
        <col width="200">
        <col>
    </colgroup>

    <tbody>
    <tr>
        <td>权限名称</td>
        <td>${perm.name}</td>

    </tr>
    <tr>
        <td>权限类型</td>

        <td>${perm.type}</td>

    </tr>
    <tr>
        <td>URL</td>

        <td>${perm.url}</td>
    </tr>
    <tr>
        <td>权限代码字符串</td>

        <td>${perm.percode}</td>
    </tr>
    <tr>
        <td>ParentId</td>

        <td>${perm.parentid}</td>
    </tr>
    </tbody>
</table>
</div>

</body>
</html>
