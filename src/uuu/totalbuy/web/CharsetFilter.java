/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uuu.totalbuy.web;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;

/**
 *
 * @author Administrator
 */

//在servlet運作前，由這個filter類別統一設定編碼字元集，給前端的big5瀏覽器看的
@WebFilter(filterName = "CharsetFilter", urlPatterns = {"*.jsp", "*.view", "*.do"},       
initParams = { @WebInitParam(name = "charset", value = "big5")})
public class CharsetFilter implements Filter {
	//CharsetFilter沒父類別，一定要先初始化存起來
    private FilterConfig filterConfig;
    
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        this.filterConfig = filterConfig;
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
//        charset字符
        String charset = filterConfig.getInitParameter("charset");
//        如果設定檔是空的編碼，就用這邊的編碼
		if (charset==null) charset = "big5";
//        設定字符編碼
        request.setCharacterEncoding(charset);
        request.getParameterNames();
                
        response.setCharacterEncoding(charset);
        response.getWriter();
//        這段前面是處理前，後面是處理後交給下一個
        chain.doFilter(request, response);
        
    }

    @Override
    public void destroy() {
        
    }
}
