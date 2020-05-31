<%@page import="uuu.totalbuy.domain.VIP"%>
<%@page import="uuu.totalbuy.domain.Customer"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<<jsp:include page="/WEB-INF/subviews/header.jsp" >
    <jsp:param name="subtitle" value="會員註冊成功"/>
</jsp:include>
<div id="section"></div>
<div id="article">
    <%
        Customer member = (Customer)request.getAttribute("member");
    %>

        <h1>會員註冊成功</h1>
        <table cellpadding="5">
            <tr><td>ID:</td><td><%= member.getId() %></td></tr>
            <tr><td>Name:</td><td><%= member.getName() %></td></tr>
            <tr><td>Gender:</td><td><%= member.getGender() %></td></tr>
            <tr><td>Email:</td><td><%= member.getEmail()%></td></tr>
            <tr><td>Married:</td><td><%= member.isMarried()%></td></tr>
            <tr><td>Address:</td><td><%= member.getAddress() %></td></tr>
            <tr><td>BirthDate:</td><td><%= member.getBirthDateString()%></td></tr>
            <%if(member instanceof VIP){%>
            <tr><td>Discount:</td><td><%= ((VIP)member).getDiscount() %>%</td></tr>
            <%}%>
        </table>
  <div id="aside"></div>  
<jsp:include page="/WEB-INF/subviews/footer.jsp" />