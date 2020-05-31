package org.apache.jsp.WEB_002dINF.admin;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class login_jsp extends org.apache.jasper.runtime.HttpJspBase
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

      out.write('\n');
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "/WEB-INF/subviews/header.jsp" + "?" + org.apache.jasper.runtime.JspRuntimeLibrary.URLEncode("subtitle", request.getCharacterEncoding())+ "=" + org.apache.jasper.runtime.JspRuntimeLibrary.URLEncode("會員登入", request.getCharacterEncoding()), out, false);
      out.write("\n");
      out.write("<div id=\"section\">section</div>\n");
      out.write("<div id=\"article\">\n");
      out.write("    <form action=\"j_security_check\" method=\"POST\">\n");
      out.write("        <table>\n");
      out.write("            <tr><td>帳號</td><td><input type=\"text\" size=\"10\" name=\"j_username\"/></td></tr>\n");
      out.write("            <tr><td>密碼</td><td><input type=\"password\" size=\"10\" name=\"j_password\"/></td></tr>\n");
      out.write("        </table>\n");
      out.write("        <input class=\"btn\" type=\"submit\" value=\"登入\"/>\n");
      out.write("    </form>\n");
      out.write("</div>\n");
      out.write("<div id=\"aside\">aside</div>  \n");
      org.apache.jasper.runtime.JspRuntimeLibrary.include(request, response, "/WEB-INF/subviews/footer.jsp", out, false);
      out.write('\n');
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
