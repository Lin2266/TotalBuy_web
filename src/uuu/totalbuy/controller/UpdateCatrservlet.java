/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uuu.totalbuy.controller;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import uuu.totalbuy.domain.Product;
import uuu.totalbuy.domain.ShoppingCart;

/**
 *
 * @author Administrator
 */
@WebServlet(name = "UpdateCatrservlet", urlPatterns = {"/update_cart.do"})
public class UpdateCatrservlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
              throws ServletException, IOException {
         request.setCharacterEncoding("UTF-8");
            //1.取得請求資料和檢查資料(修改購物車)
         
 //        cart在session裡
        HttpSession session = request.getSession();
        ShoppingCart cart = (ShoppingCart)session.getAttribute("cart");
        
        if(cart!=null) {
//            2.呼叫商業邏輯
           Set<Product> keySet = cart.getKeySet();
           Set<Product> removeSet = new HashSet();
           for (Product p:keySet) {
               String delete = request.getParameter("delete_" + p.getId());
               if(delete!=null){
                   removeSet.add(p);
               }
           }
           for(Product p:removeSet) {
               cart.remove(p);
           }
//           剩下來的有沒有要修改
           for (Product p:keySet) {
               String quantity = request.getParameter("quantity_" + p.getId());
//               不是null而且要是整數
               if (quantity!=null && quantity.matches("\\d+")){
                   int q = Integer.parseInt(quantity);//轉型
                   if(q>0 && q<=10) {
                       cart.update(p, q);
                   }
               }
            } 
       }    
        
                //  3.重定向到cart.jsp
        //購物車點選完選別頁在回來時的數量要判斷一樣，不要每次回購物車都一直累加
                 String submit = request.getParameter("submit");
                if (null != submit)switch (submit) {
            case "回賣場":
                response.sendRedirect("Shopping_Mall.jsp");
                break;
            case "確認結帳":
                //                    按結帳時就會轉到結帳畫面
                response.sendRedirect(request.getContextPath()+"/user/check_out.jsp");
                break;
            default:
                response.sendRedirect("cart.jsp");
                break;
        }
                    
    }
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
//    @Override
//    protected void doGet(HttpServletRequest request, HttpServletResponse response)
//              throws ServletException, IOException {
//        processRequest(request, response);
//    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
              throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
