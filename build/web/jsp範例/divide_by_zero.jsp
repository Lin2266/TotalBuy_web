<%-- 
    Document   : divide_by_zero
    Created on : 2015/9/10, 上午 11:37:03
    Author     : Administrator
--%>
<!--要用exception，page要加上errorPage="錯誤訊息的地址"-->
<%@page contentType="text/html" pageEncoding="UTF-8" errorPage="/WEB-INF/error.jsp"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Divide By Zero</title>
    </head>
    <body>
        <!--exception-->
        <h1><%= 100/0%></h1>
    </body>
</html>
