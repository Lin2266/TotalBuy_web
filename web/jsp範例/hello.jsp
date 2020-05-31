<%@page import="java.util.Date" info="Hello JSP" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%--buffer,autoFlush, session使用者連線(false就開不了)--%>
<%--<%@page import="java.util.Date" buffer="1kb" autoFlush="true" session="true"%>--%>
<!--charset給前端看的編碼，pageEncoding後端-->
<%--<%@page contentType="text/html;charset=big5" pageEncoding="UTF-8" info="Hello JSP"%>--%>

<!DOCTYPE html>
<!--在網頁直接存檔修改，存檔完要改延伸檔名html改jsp-->
<!--page要記得加，不然中文字會變亂碼-->
<%!
               int i = 100; //<%!會在類別建立屬性
               private String welcome = "您好";
               
                public void jspInit(){
                System.out.println(this.getClass().getName() + "init...");
                String welcome = this.getInitParameter("welcome");
                  if(welcome != null){
                  this.welcome = welcome;
                  }
               }
               
        %>
<html>
    <head>
        <meta charset="UTF-8">
        <!--info標頭跟標題顯示一樣-->
        <title><%= this.getServletInfo()%></title>
    </head>
    <body>
        <h1><%= this.getServletInfo()%></h1>
           
                <p><% out.print(welcome);%>
        <%
                
                int i = 100; //會建立在方法裡
                out.println(new Date());
                //out.flush();//配合page的buffer,滿了就送，預設是true
                //Thread.sleep(1000);測試out.flush()
                //import
                //List.(跟java一樣打上類別就可以import)
        %>
        
        <%-- 左右兩個符號中間可以用java的寫法寫，可以顯示在畫面中 --%>
        <!--12-22 request, response, session, application, config 變數-->
                 <h2>request</h2>
                <!--request可以取得客戶端的資料，取得使用者的系統版本-->
                 <p>user-agent: <%= request.getHeader("user-agent")%> </p>
                    <!--方法型態為get-->
                 <P>method type:<%= request.getMethod()%></P>
                 <!--取得現在頁面的網址專案目錄-->
                 <p>uri:<%= request.getRequestURI()%></p>
                 <!--取得現在頁面的網址-->
                 <p>url:<%= request.getRequestURL()%></p>
                 <!--取得網站專案名稱路徑-->
                 <P>context path:<%= request.getContextPath()%></P>
                 <hr>
                 
                 <h2>response</h2>
                 <!--產生結果在使用者畫面上-->
                 <!--使用者畫面的編碼-->
                 <p>response.contentType: <%= response.getContentType()%></p>
                 <hr>
                 
                 <h2>session</h2>
                 <!--session使用者連線，比喻有兩個身份-->
                 <P>session:<%= session.getId()%></P>
                 <hr>
                 
                 <h2>application</h2>
                 <!--application共用資料用，取得路徑-->
                 <p>application.context path:<%= application.getContextPath()%></p>
                 <hr>
                 
                 <h2>config</h2>
                 <!--config(配置)(用this.就可以抓到了), pageContext, page, exception-->
                 <!--抓servlet裡的welcome-->
                 <P>welcome param:<%=config.getInitParameter("welcome")%></P>
                 <!--抓servlet裡的welcome物件-->
                 <p>Servlet/JSP Name:<%= config.getServletName()%></p>
                 <hr>
                 <h2>pageContext</h2>
                 <!--取得網址做轉型-->
                 <P>pageContext->request<%= ((HttpServletRequest)pageContext.getRequest()).getRequestURL()%></P>
                 <hr>
                 <h2>page</h2>
                 <!--抓這類別的指定變數-->
                 <P>this.i:<%= this.i%></P>
                 <p>this.hashCode():<%= this.hashCode()%></p>
                 <p>page.hashCode():<%= page.hashCode()%></p>
                 
      <%--           <hr>
                 <!--錯誤畫面，後端(error.jsp)先跑前端(divide_by_zero)畫面才跑的出來-->
                 <h2>exception</h2>
                 <p>exception:<%= exception%></p>
                 --%>
                 
                 
                
        
    </body>
</html>