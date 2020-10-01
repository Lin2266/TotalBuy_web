
<%@page import="uuu.totalbuy.domain.Customer"%>
<%@page import="uuu.totalbuy.domain.Outlet"%>
<%@page import="uuu.totalbuy.domain.ShoppingCart"%>
<%@page import="uuu.totalbuy.domain.VIP"%>
<%@page import="java.text.NumberFormat"%>
<%@page import="uuu.totalbuy.domain.Product"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="/WEB-INF/subviews/header.jsp" >
    <jsp:param name="subtitle" value="購物車"/>
</jsp:include>
<!--section章節article文章-->
<div id="section">section</div>
<div id="article">
    <%
        //Customer user = (Customer) session.getAttribute("user");
        //如果價錢有很多小數點的時候，取得系統的數字格式
        NumberFormat nf = NumberFormat.getInstance();
        //設置最大小數位數，(1)1.0
        nf.setMaximumFractionDigits(1);
        nf.setMaximumFractionDigits(2);

//        如果購物車不是null而且取得數量不是空的時顯示清單
      ShoppingCart cart = (ShoppingCart) session.getAttribute("cart");
        if (cart != null && !cart.getKeySet().isEmpty()) {
            //沒登入選完購物車在登入的VIP客戶打折判斷
            if (cart.getUser() == null ) {
                Customer user = (Customer)session.getAttribute("user");
                      
                    }
    %>
    <form method="POST" action="<%= request.getContextPath()%>/update_cart.do">
        <table border="1" style="width:80%">
            <tr>
                <th>No.</th>
                <th>名稱</th>
                <th>圖片</th>
                <th>原價</th>
                <th>折扣</th>
                <th>售價</th>
                <th>數量</th>
                <th>刪除</th>  
            </tr>
            <% for (Product p : cart.getKeySet()) {%>
            <tr>
                <td><%= p.getId()%></td>
                <td><%= p.getName()%></td>
                <td><img style="width: 120px" src="<%= p.getUrl() != null ? "images/" + p.getUrl() : "images/watch-Black.png"%>" alt="<%=p.getName()%>"></td>
                <td><%= p instanceof Outlet ? ((Outlet) p).getListPrice() : p.getUnitPrice()%></td>
                <td><%= p instanceof Outlet ? ((Outlet) p).getDiscount() : ""%></td>
                <td><%= nf.format(p.getUnitPrice())%></td>
                <td><input style="width:5ex" type="number" value="<%= cart.getQuentity(p)%>" name="quantity_<%= p.getId()%>" min="1" max="5"></td>
                <td><input type="checkbox" name="delete_<%= p.getId()%>"></td>   
            </tr>
            <%}
                if (cart.getUser() != null && cart.getUser() instanceof VIP) {
            %>         
            <tr>
                <td style="text-align: right" colspan="5">原總金額:</td>
                <td colspan="3"><%= cart.getListTotalAmount()%></td>
            </tr>
            <tr>
                <td style="text-align: right" colspan="5">VIP 折扣:</td>
                <td colspan="3"><%= ((VIP) cart.getUser()).getDiscount()%></td>
            </tr>
            <%}%>
            <tr>
                <td style="text-align: right" colspan="5">總金額:</td>
                <td colspan="3"><%= cart.getTotalAmount()%></td>
            </tr>
            <tr>
                <td colspan="5"><input type="submit" value="回賣場" name="submit"></td>
                <td colspan="5">
                    <input type="submit" value="修改購物車" name="submit">
                    <input type="submit" value="確認結帳" name="submit">
                </td>
            </tr>
        </table>
    </form>
    <%} else {%>
    <h4>購物車是空的</h4>
    <%}%>
</div>               
<!--aside在旁邊-->
<div id="aside">aside</div>  
<jsp:include page="/WEB-INF/subviews/footer.jsp" />  
