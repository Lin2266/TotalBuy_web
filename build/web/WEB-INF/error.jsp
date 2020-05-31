
<%@page contentType="text/html" pageEncoding="UTF-8" isErrorPage="true"%>
<!--page加上isErrorPage="true，exception就可以用了,要顯示的畫面要在page加上errorPage="錯誤訊息的位址"-->
<jsp:include page="/WEB-INF/subviews/header.jsp" >
    <jsp:param name="subtitle" value="系統錯誤"/>
</jsp:include>

<!--<div id="section">section</div>-->
<div id="article">
        <script>
//                錯誤訊息
            var s1 = "block"; //區塊
            var s2 = "width:80%;display:blocked;font-size:70%;color:blue";
            function show_details() {
//                document文件取得ElementById元素編號查詢("details")詳細資料
                var d = document.getElementById("details");
                try {
                    d.style.setAttribute("display", s1); //顯示display              
                    if (s1 == "none") {
                        s1 = "block";
                    } else {
                        s1 = "none";
                    }
                } catch (err) {
                    d.setAttribute("style", (s2 == null ? "width:80%;display:none;" : s2));
                    if (s2 == null) {
                        s2 = "width:80%;display:blocked;font-size:60%;color:blue";
                    } else {
                        s2 = null;
                    }
                }
            }
            
           
                
        </script>
    </head>
    <body>                  <!--請求取得錯誤的uri-->
        <p style='font-size:80%'>執行<span style='color:darkred'><%= request.getAttribute("javax.servlet.error.request_uri")%></span>時發生下列錯誤：<br/>
            <% if (exception != null) {
                 out.println(exception);
                %>
            <!--錯誤訊息的超連結-->
            <a href="javascript:show_details()">details...</a><br/>
        
        <div>
            <img style="width:60%" src="<%= request.getContextPath()%>/images/error.jpg" alt="error">
   </div>
            </p>
            <span id='details' style="width:60%;display:none;color:blue">
                <!--PrintWriter參數為out-->
                <!--printStackTrace()命令行顯示异常信息在程序中出错的位置及原因-->
                <%
                    exception.printStackTrace(new java.io.PrintWriter(out));
                %>
            </span>
            <!--顯示404畫面status_code表示可加圖檔-->
            <% } else {
                Integer status = (Integer) request.getAttribute("javax.servlet.error.status_code");//status_code狀態代碼轉為整數
                if (status != null) {
            %>            
        <div>
            <img style="width:60%" src="<%= request.getContextPath()%>/images/4041.jpg" alt="<%= status%>">
        </div>
        <%
                }
            }
        %>          
    
<!--<div id="aside">aside</div>  -->
