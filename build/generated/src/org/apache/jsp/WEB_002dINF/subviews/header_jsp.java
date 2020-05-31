package org.apache.jsp.WEB_002dINF.subviews;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import uuu.totalbuy.domain.Customer;

public final class header_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("<!--頁首-->\n");
      out.write("\n");
      out.write("\n");

//    標題設定
    String subtitle = request.getParameter("subtitle"); 
    if (subtitle == null) {
        subtitle = "TotalBuy";
    }

    String url = request.getRequestURL().toString();
    Customer user = (Customer) session.getAttribute("user");

      out.write("\n");
      out.write("<html>\n");
      out.write("    <head>\n");
      out.write("        <title>");
      out.print( subtitle);
      out.write("</title>\n");
      out.write("    <meta charset=\"UTF-8\">\n");
      out.write("    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n");
      out.write("        ");
if (url.indexOf("/user") > 0 && user == null) { 
      out.write("\n");
      out.write("        <meta http-equiv=\"refresh\" content=\"0; url=");
      out.print( request.getContextPath() );
      out.write("/login.jsp\"/>\n");
      out.write("        ");
return;}
      out.write("\n");
      out.write("<link href=\"");
      out.print( request.getContextPath());
      out.write("/style/totalbuy.css\" rel=\"stylesheet\" type=\"text/css\"/>\n");
      out.write("<link rel=\"stylesheet\" href=\"//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css\">\n");
      out.write("<script src=\"http://code.jquery.com/jquery-1.11.3.min.js\"></script>\n");
      out.write(" \n");
      out.write("<script>\n");
      out.write("//        登出設定\n");
      out.write("     function logout(){\n");
      out.write("                $.ajax({\n");
      out.write("                    url: \"");
      out.print( request.getContextPath());
      out.write("/logout.do\",\n");
      out.write("                    type: \"POST\",\n");
      out.write("                    contentType: \"text/plain;charset=UTF-8\"                    \n");
      out.write("                }).done(function(msg) {\n");
      out.write("                    alert(msg);\n");
      out.write("//                    登出時顯示的字串\n");
      out.write("                    $(\"#welcomeSpan\").text(\"你好  歡迎光臨\");\n");
      out.write("//                    改前端，取得指定的路徑\n");
      out.write("                    if(location.href.indexOf(\"/user\")>0 || location.href.indexOf(\"/admin\")>0){\n");
      out.write("//                        強迫轉址到這個目錄底下\n");
      out.write("                        location.href = \"");
      out.print( request.getContextPath());
      out.write("/\";\n");
      out.write("                    }\n");
      out.write("//                    request.getContextPath()後端程式請求把/totalbuy路徑帶回來，登出時把登入跟註冊帶回來\n");
      out.write("                    $(\"#userSpan\").html(\"<a href='");
      out.print( request.getContextPath());
      out.write("/login.jsp'>會員登入</a>  \"+\n");
      out.write("                                       \"<a href='");
      out.print( request.getContextPath());
      out.write("/register.jsp'>會員註冊</a>  \");                    \n");
      out.write("                });                \n");
      out.write("            }\n");
      out.write("//            時間設定\n");
      out.write("            var clockThread = window.setInterval(setClock, 1000);\n");
      out.write("         //取得現在時間\n");
      out.write("                 function setClock(){\n");
      out.write("                    var clock = document.getElementById(\"clock\");\n");
      out.write("                    clock.innerHTML = new Date();\n");
      out.write("                 }\n");
      out.write("    </script>\n");
      out.write("</head>\n");
      out.write("\n");
      out.write("<body>\n");
      out.write("    <!--頁首-->\n");
      out.write("    <div id=\"header\">\n");
      out.write("        <h1 style=\" color: #cc6600; font-size:250%\">TotalBuy購物網&nbsp;&nbsp;<span style=\"font-size: medium; \" >");
      out.print(subtitle);
      out.write("</span></h1>\n");
      out.write("        <span id=\"welcomeSpan\" style=\"font-size:15px; color: #cc6600\">你好&nbsp;&nbsp;");
      out.print( user != null ? user.getName() : "歡迎光臨");
      out.write("!</span>\n");
      out.write("        </div>\n");
      out.write("        \n");
      out.write("        <!--導航-->\n");
      out.write("        <div id=\"nav\">\n");
      out.write("        <!-- 以下為相對路徑!-->\n");
      out.write("        <a href=\"");
      out.print( request.getContextPath());
      out.write("/\" title=\"首頁\"><img src=\"");
      out.print( request.getContextPath());
      out.write("/images/home.png\" alt=\"\" width=\"40\" height=\"40\"/></a>&nbsp; \n");
      out.write("        <a href=\"");
      out.print( request.getContextPath());
      out.write("/Shopping_Mall.jsp\">產品清單</a>&nbsp;\n");
      out.write("        <a href=\"");
      out.print( request.getContextPath());
      out.write("/cart.jsp\">購物車</a>&nbsp;\n");
      out.write("        \n");
      out.write("            <!--使用者是空的就顯示會員登入跟註冊-->\n");
      out.write("            ");
 if(user==null){
      out.write("\n");
      out.write("            <a href=\"");
      out.print( request.getContextPath());
      out.write("/login.jsp\">會員登入</a>\n");
      out.write("            <a href=\"");
      out.print( request.getContextPath());
      out.write("/register.jsp\">會員註冊</a>&nbsp;\n");
      out.write("        ");
}else{
      out.write("\n");
      out.write("            <a href=\"javascript: logout()\">會員登出</a>\n");
      out.write("            <a href=\"");
      out.print( request.getContextPath());
      out.write("/user/update.jsp\">修改會員</a>\n");
      out.write("            <a href=\"");
      out.print( request.getContextPath());
      out.write("/user/orders_history.jsp\">查詢訂單</a>\n");
      out.write("        ");
}
      out.write("\n");
      out.write("        <a href=\"");
      out.print( request.getContextPath());
      out.write("/hello.view\">HelloServlet</a>&nbsp;\n");
      out.write("        <a href=\"");
      out.print( request.getContextPath());
      out.write("/html_sample\"> html範例 </a> &nbsp;\n");
      out.write("        <a href=\"");
      out.print( request.getContextPath());
      out.write("/jsp範例\">jsp範例</a>\n");
      out.write("        <a href=\"http://www.google.com\">Google</a>\n");
      out.write("        \n");
      out.write("        <form style=\"text-align:right\">\n");
      out.write("            <input style=\"width:50ex\" type=\"search\" name=\"keyword\" placeholder=\"請輸入產品代號或關鍵字...\">\n");
      out.write("            <input type=\"submit\" value=\"搜尋\">\n");
      out.write("        </form>\n");
      out.write("</div>\n");
      out.write("        <hr>");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
