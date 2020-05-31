<%@page import="uuu.totalbuy.domain.Customer"%>
<%@page import="java.text.NumberFormat"%>
<%@page import="uuu.totalbuy.domain.Outlet"%>
<%@page import="uuu.totalbuy.domain.Product"%>
<%@page import="java.util.List"%>
<%@page import="uuu.totalbuy.model.ProductService"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<jsp:include page="/WEB-INF/subviews/header.jsp" >
    <jsp:param name="subtitle" value="產品清單"/>
</jsp:include>
<div id="section">section</div>
<div id="article">
   

    <%
        //Customer user = (Customer) session.getAttribute("user");
        //如果價錢有很多小數點的時候，取得系統的數字格式
        NumberFormat nf = NumberFormat.getInstance();
        //設置最大小數位數，(1)1.0
        nf.setMaximumFractionDigits(1);
        nf.setMaximumFractionDigits(2);

//            從資料庫取得所有產品
        ProductService service = new ProductService();
        List<Product> list = service.getAll();
//            out.println(list);
//            先判斷產品不是null而且是空的
//        if (!list.isEmpty())
        if (list != null && !list.isEmpty()) {

    %>
    <script>
//             產品說明的設計
        $(function () {
            $("#dialog").dialog({
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

        function get_product(pid) {
            $.ajax({
                url: "<%= request.getContextPath()%>/get_product.jsp?pid=" + pid,
                type: "GET",
                contentType: "text/html;charset=UTF-8"
            }).done(function (result) {
                $("#dialog").html(result);
                $("#dialog").dialog("open");
            });
        }
    </script>
<!--產品清單-->
    <ul id="pro-list">
        <%for (Product p : list) {%>
        <li>
            <h4><%= p.getName()%></h4>
            <div style="float: left;width: 45%" class="logo">
                <!-- javascript: get_product(<%= p.getId()%> 是javascript裡面的get_product(參數)方法)-->
                <a title="產品說明" href="javascript: get_product(<%= p.getId()%>)">
                    <img alt="<%= p.getName()%>" src="<%= p.getUrl() == null ?"images/watch-Black.png":"images/"+p.getUrl() %>">
                </a>
            </div>
            <div style="float: right;width: 55%">
                <a href="add_cart.do?pid=<%= p.getId()%>" title="加入購物車"><img src="images/shopping-cart.png" alt=""/></a>
                <div>
                    <%if (p instanceof Outlet) {%>
                    原價: <%= nf.format(((Outlet) p).getListPrice())%><br>
                    優惠: <%= 100 - ((Outlet) p).getDiscount()%> 折<br>
                    <%}%>
                    售價:<%= nf.format(p.getUnitPrice())%>
                </div>
            </div>
        </li>
        <%}%>        
    </ul>
    <%}%>
</div>
<div id="dialog" title="產品說明"></div>
<div id="aside">aside</div>  
<jsp:include page="/WEB-INF/subviews/footer.jsp" />
