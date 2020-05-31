package uuu.totalbuy.web;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;


//效能監控，PerformanceFilter性能過瀘器，只需寫三個方法
public class PerformanceFilter implements Filter {
//    prefix=初值"Performace:"
    private String prefix="Performace:";
//    過慮配置
    private FilterConfig filterConfig;
    
//    初始化
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.filterConfig = filterConfig;
//        透過設定檔的對應prefix為"效能監控"
        String prefix = filterConfig.getInitParameter("prefix");
//        prefix不是空的時候，把設定檔對應的prefix(效能監控)取代這裡的prefix物件
        if(prefix!=null){
            this.prefix=prefix;
        }
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
//        登入開始的毫秒
        long begin = System.currentTimeMillis();
//        這行之前是處理前，之後是處理後
        chain.doFilter(request, response);
//        登入結束的毫秒
        long end = System.currentTimeMillis();
        
        StringBuilder msg = new StringBuilder(prefix);//prefix已初始化為效能監控
      msg.append(",url-");   
        //轉型，取得請求路徑如:/totalbuy/login.jsp
        msg.append(((HttpServletRequest)request).getRequestURI());
        msg.append(",");
        msg.append(end-begin);//登入開始到結束用到的毫秒
        msg.append("ms");
        //  效能監控,url-/totalbuy/login.jsp,660ms
        this.filterConfig.getServletContext().log(msg.toString());
    }

    @Override
    public void destroy() {
        
    }
  
}
