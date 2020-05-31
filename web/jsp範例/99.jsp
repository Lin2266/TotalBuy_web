
<%@page import="uuu.totalbuy.domain.Customer"%>
<%@page import="uuu.totalbuy.model.CustomersDAO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>99乘法表</title>
    </head>
    <body style="background-color: #99ccff">
        <h1>99乘法表</h1>
        <!--border邊框粗細-->
        <table border="1" cellpading="5" cellspacing="0">
                <%for(int i = 1; i<10; i++){%>
                <!--tr一列-->
                <tr style="background-color: <%=i%2==0?"lightblue":"lightyellow"%>">
                <%for (int j = 1; j<10; j++){%>
            <%-- 會顯示在畫面out.println(i+"*" +j +"=" +i*j);--%>
                <!-- =等於out.println-->
                <!--td一格-->
                <!--間距不一樣<td><%//out.println(i+"*" +j +"=" +i*j);%></td>-->
                <td><%=i%> * <%=j%> = <%=i*j%></td>
                <%}%>     
                </tr>
                <%}%>

        </table>
                <hr>
                
                <table border="1" cellpadding="5" cellspacing="5" style="background-color: #3f51b5" >
            <tr><th colspan="9" style=" color: white;font-size: 20px">99乘法表</th></tr>
            <%for(int i =1;i<10;i++){%>
            <tr style="background-color:<%= i%2==0?"lightyellow":"lightblue"%> ">
                <%for(int j =1;j<10;j++){%>
                <td><%=i%> * <%=j%> = <%=i*j%> </td>
              <%}%>   
            </tr>
            <%}%>
        </table>  
      
    </body>
</html>
