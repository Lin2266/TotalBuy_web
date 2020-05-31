<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!--引庫header跟標題-->
<jsp:include page="/WEB-INF/subviews/header.jsp" >
    <jsp:param name="subtitle" value="Home"/>
</jsp:include>

<div id="section">section</div>
<div id="article">
    <!--內容-->
    <p id='test'>Hello World!</p>
</div>

<div id="aside">aside</div>  
<jsp:include page="/WEB-INF/subviews/footer.jsp" />