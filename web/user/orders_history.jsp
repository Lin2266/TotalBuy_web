<%@page import="java.util.Date"%>
<%@page import="java.util.List"%>
<%@page import="uuu.totalbuy.domain.Order"%>
<%@page import="uuu.totalbuy.model.OrderService"%>
<%@page import="uuu.totalbuy.domain.Customer"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    Customer user = (Customer) session.getAttribute("user");

    if (user == null) {
        response.sendRedirect(request.getContextPath() + "/login.jsp");
        return;
    }
    //資料庫
    OrderService service = new OrderService();
    List<Order> list = service.getOrdersHistory(user.getId());
%>
<jsp:include page="/WEB-INF/subviews/header.jsp" >
    <jsp:param name="subtitle" value="訂單歷史"/>
</jsp:include>

<!--section章節article文章-->
<div id="section">section</div>
<div id="article">
    <% if (list != null && list.size() > 0) {%>
    <form method="POST" action="<%=request.getContextPath()%>/user/OrderHistoryServlet.do">
    <table>
        <thead>
            <tr>
                <th>NO.</th>
                <th>訂購時間</th>
                <th>付款方式</th>
                <th>運費方式</th>
                <th>總金額</th>
                <th>處理狀態</th>
                <th>檢視</th></tr>
        </thead>
        <tbody>
            <% for (int i = 0; i < list.size(); i++) {
                    Order order = list.get(i);

            %>
            <tr style=" background-color: #ccffcc; " >
                <td><%= order.getId()%></td>
                <td><%= order.getOrderTime()%></td>
                <td><%= order.getPaymentType()%></td>
                <td><%= order.getShippingType()%></td>
                <td><%= order.getTotalAmount()%></td>
                <td><%= order.getBadStatus().ordinal() > 0 ? order.getBadStatus() : order.getStatus()%></td>         
                <td><a href="javascript: showOrder(<%= order.getId()%>)">明細</a></td>
                <td><input type="hidden" name="hiname" value="<%= order.getId() %>" ></td> <%--這裡加隱藏 id --%>
                <td><input type="submit" value="取消訂單" name="submit" ></td>
            </tr>
            <%}%>
        </tbody>
    </table>
    <%}%>

</div>               
<!--aside在旁邊-->
<div id="aside">aside</div>
<div id="detail" title="產品明細"></div> 
<script>
    $(function () {
        $("#detail").dialog({
            autoOpen: false, width: 500, height: 300,
            show: {
                effect: "blind",
                duration: 500
            },
            hide: {
                effect: "blind",
                duration: 300
            }
        });
    });
    function showOrder(orderId) {
        //alert(orderId);
        $.ajax({
            url: "<%= request.getContextPath()%>/user/show_order.jsp",
            method: "POST",
            data: {oid: orderId}//自已塞資料方式，可用json方式傳表單
        }).done(
                function (result) {
                    //alert(result);
                    $("#detail").html(result);
                    $("#detail").dialog("open");
                }
        ).fail(
                function (xhr, status) {
                    console.log(status);
                }
        );
    }
</script>
<jsp:include page="/WEB-INF/subviews/footer.jsp" />     