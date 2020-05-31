<!--頁首-->
<%@page import="uuu.totalbuy.domain.Customer"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
//    標題設定
    String subtitle = request.getParameter("subtitle"); 
    if (subtitle == null) {
        subtitle = "TotalBuy";
    }

    String url = request.getRequestURL().toString();
    Customer user = (Customer) session.getAttribute("user");
%>
<html>
    <head>
        <title><%= subtitle%></title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <%if (url.indexOf("/user") > 0 && user == null) { %>
        <meta http-equiv="refresh" content="0; url=<%= request.getContextPath() %>/login.jsp"/>
        <%return;}%>
<link href="<%= request.getContextPath()%>/style/totalbuy.css" rel="stylesheet" type="text/css"/>
<link rel="stylesheet" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
<script src="http://code.jquery.com/jquery-1.11.3.min.js"></script>
 
<script>
//        登出設定
     function logout(){
                $.ajax({
                    url: "<%= request.getContextPath()%>/logout.do",
                    type: "POST",
                    contentType: "text/plain;charset=UTF-8"                    
                }).done(function(msg) {
                    alert(msg);
//                    登出時顯示的字串
                    $("#welcomeSpan").text("你好  歡迎光臨");
//                    改前端，取得指定的路徑
                    if(location.href.indexOf("/user")>0 || location.href.indexOf("/admin")>0){
//                        強迫轉址到這個目錄底下
                        location.href = "<%= request.getContextPath()%>/";
                    }
//                    request.getContextPath()後端程式請求把/totalbuy路徑帶回來，登出時把登入跟註冊帶回來
                    $("#userSpan").html("<a href='<%= request.getContextPath()%>/login.jsp'>會員登入</a>  "+
                                       "<a href='<%= request.getContextPath()%>/register.jsp'>會員註冊</a>  ");                    
                });                
            }
//            時間設定
            var clockThread = window.setInterval(setClock, 1000);
         //取得現在時間
                 function setClock(){
                    var clock = document.getElementById("clock");
                    clock.innerHTML = new Date();
                 }
    </script>
</head>

<body>
    <!--頁首-->
    <div id="header">
        <h1 style=" color: #cc6600; font-size:250%">TotalBuy購物網&nbsp;&nbsp;<span style="font-size: medium; " ><%=subtitle%></span></h1>
        <span id="welcomeSpan" style="font-size:15px; color: #cc6600">你好&nbsp;&nbsp;<%= user != null ? user.getName() : "歡迎光臨"%>!</span>
        </div>
        
        <!--導航-->
        <div id="nav">
        <!-- 以下為相對路徑!-->
        <a href="<%= request.getContextPath()%>/" title="首頁"><img src="<%= request.getContextPath()%>/images/home.png" alt="" width="40" height="40"/></a>&nbsp; 
        <a href="<%= request.getContextPath()%>/Shopping_Mall.jsp">產品清單</a>&nbsp;
        <a href="<%= request.getContextPath()%>/cart.jsp">購物車</a>&nbsp;
        
            <!--使用者是空的就顯示會員登入跟註冊-->
            <% if(user==null){%>
            <a href="<%= request.getContextPath()%>/login.jsp">會員登入</a>
            <a href="<%= request.getContextPath()%>/register.jsp">會員註冊</a>&nbsp;
        <%}else{%>
            <a href="javascript: logout()">會員登出</a>
            <a href="<%= request.getContextPath()%>/user/update.jsp">修改會員</a>
            <a href="<%= request.getContextPath()%>/user/orders_history.jsp">查詢訂單</a>
        <%}%>
        <a href="<%= request.getContextPath()%>/hello.view">HelloServlet</a>&nbsp;
        <a href="<%= request.getContextPath()%>/html_sample"> html範例 </a> &nbsp;
        <a href="<%= request.getContextPath()%>/jsp範例">jsp範例</a>
        <a href="http://www.google.com">Google</a>
        
        <form style="text-align:right">
            <input style="width:50ex" type="search" name="keyword" placeholder="請輸入產品代號或關鍵字...">
            <input type="submit" value="搜尋">
        </form>
</div>
        <hr>