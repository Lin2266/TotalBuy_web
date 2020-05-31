package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.text.NumberFormat;
import uuu.totalbuy.domain.Outlet;
import uuu.totalbuy.domain.Product;
import uuu.totalbuy.model.ProductService;

public final class get_005fproduct_jsp extends org.apache.jasper.runtime.HttpJspBase
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

      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");

    NumberFormat nf = NumberFormat.getInstance();
    nf.setMinimumFractionDigits(1);
    nf.setMaximumFractionDigits(2);

    String pid = request.getParameter("pid");
    if (pid != null && pid.matches("\\d+")) {
        ProductService service = new ProductService();
        Product p = service.get(Integer.parseInt(pid));

      out.write("\n");
      out.write("<!--dialog產品說明-->\n");
      out.write("<div id=\"article\">\n");
      out.write("    ");
 if (p == null) {
      out.write("\n");
      out.write("    <h1>查無產品</h1>\n");
      out.write("    ");
} else {
      out.write("\n");
      out.write("    <h4>");
      out.print( p.getName());
      out.write("</h4>\n");
      out.write("    <div style=\"float: left;width: 45%\" class=\"logo\">\n");
      out.write("        <a title=\"產品說明\" href=\"get_product.jsp?pid=");
      out.print( p.getId());
      out.write("\">\n");
      out.write("            <img alt=\"");
      out.print(p.getName());
      out.write(" \" src=\"");
      out.print( p.getUrl() != null ? "images/"+p.getUrl() : "images/watch-Black.png");
      out.write("\">\n");
      out.write("        </a>\n");
      out.write("    </div>\n");
      out.write("    <div style=\"float: right;width: 55%\">\n");
      out.write("        <input style=\"width:5ex\" type=\"number\" value=\"1\" name=\"quantity_");
      out.print(p.getId());
      out.write("\" min=\"1\" max=\"10\">\n");
      out.write("        <a href=\"add_cart.do?pid=");
      out.print( p.getId());
      out.write("\" title=\"加入購物車\"><img src=\"images/shopping-cart.png\" alt=\"\"/></a>\n");
      out.write("        <div>\n");
      out.write("            ");
if (p instanceof Outlet) {
      out.write("\n");
      out.write("            原價: ");
      out.print( nf.format(((Outlet) p).getListPrice()));
      out.write("<br>\n");
      out.write("            優惠: ");
      out.print( 100 - ((Outlet) p).getDiscount());
      out.write(" 折<br>\n");
      out.write("            ");
}
      out.write("\n");
      out.write("            售價:");
      out.print( nf.format(p.getUnitPrice()));
      out.write("\n");
      out.write("            <p>");
      out.print( p.getDescription()==null?"":p.getDescription());
      out.write("</p>\n");
      out.write("        </div>\n");
      out.write("    </div>\n");
      out.write("    ");
}
      out.write("\n");
      out.write("</div>\n");
      out.write(" ");
}else{
      out.write("\n");
      out.write("    <p>查無產品!</p>\n");
      out.write(" ");
}
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
