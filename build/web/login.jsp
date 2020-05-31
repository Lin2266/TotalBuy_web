<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!--引庫header跟標題-->
<jsp:include page="/WEB-INF/subviews/header.jsp" >  
    <jsp:param name="subtitle" value="會員登入"/>
</jsp:include>
<!--section章節article文章-->
<div id="section">section</div>
<div id="article">
    <script>
        //圖片更新
        function refreshImage() {
            var checkImage = document.getElementById("checkImage");
            //alert(checkImage);
            //images/check.jpg這裡的url跟圖片的url一樣就不會取得請求要加new Date()
            checkImage.src = "images/register_check.jpg?get=" + new Date();
        }
    </script>

    <%

        String idCookie = "";
        String autoCookie = "";

        Cookie[] cookies = request.getCookies();
        for (Cookie c : cookies) {
            if (c.getName().equals("id")) {
                idCookie = c.getValue();
            } else if (c.getName().equals("auto")) {
                autoCookie = c.getValue();
            }
        }

        List<String> errors = (List<String>) request.getAttribute("errors");
        if (errors != null && !errors.isEmpty()) {
    %>
    <ul>
        <% for (String msg : errors) {%>
        <li><%=msg%></li>
            <%}%>
    </ul>
    <%}%>
    <!--回應會員登入成功-->
    <form method="POST" action="login.do">
        <p>
            <label for="id">會員帳號</label>
            <!--getParameter("input標籤的name值")-->
            <input type ="text" id="id" name="id" pattern="[A-Za-z][12][0-9]{8}"        
                   value = "<%= request.getParameter("id") == null ? idCookie : request.getParameter("id")%>"
                   required placeholder="請輸入身分證號">
        </p>
        <p>
            <label for="pwd">會員密碼</label>
            <input type="password" id = "pwd" name="pwd" required placeholder="請輸入密碼">
        </p>
        <p>

            <!--<img src="images(圖)/CaptchaImage.jpg" alt="檢核碼"/>-->
            <!--refreshImage()點擊圖可更新圖片-->
            <a href="javascript: refreshImage()" title="點選即可更新圖片">
                <img id="checkImage" src="images/register_check.jpg" alt="檢核碼"/></a>
            <input type="text" id ="checkCode" name="checkCode" 
                   value = "<%= request.getParameter("checkCode") == null ? "" : request.getParameter("checkCode")%>"
                   required placeholder="請依左圖輸入數字">
        </p>
        <p>
            <input type="checkbox" id="auto" name="auto" 
                   <%= request.getParameter("auto") != null ? "checked" : request.getParameter("id") != null ? "" : autoCookie%>
                   >
            <label for="auto">記住帳號</label>                                
        </p>


        <input type="submit" value="確定">
    </form>
    </div>               
    <!--aside在旁邊-->
    <div id="aside">aside</div>  
    <jsp:include page="/WEB-INF/subviews/footer.jsp" />  