/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uuu.totalbuy.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import uuu.totalbuy.domain.Customer;
import uuu.totalbuy.domain.Order;
import uuu.totalbuy.domain.Product;
import uuu.totalbuy.domain.ShoppingCart;
import uuu.totalbuy.domain.TotalBuyException;
import uuu.totalbuy.model.OrderService;

/**
 *
 * @author Administrator
 */
@WebServlet(name = "CheckOutServlet", urlPatterns = {"/user/check_out.do"})
public class CheckOutServlet extends HttpServlet {

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
        //讀表單中的資料編碼
        request.setCharacterEncoding("UTF-8");
        List<String> errors = new ArrayList<>();
        //結帳
        //1. get data & check data
        HttpSession session = request.getSession();
        Customer user = (Customer) session.getAttribute("user");
        ShoppingCart cart = (ShoppingCart) session.getAttribute("cart");
        if (user == null || cart == null) {
            errors.add("請重新登入後購物");
        }
        //付款方式
        String pType = request.getParameter("paymentType");
        if (pType == null || !pType.matches("[0-3]")) {
            errors.add("必須選擇付款方式");
        }

        //貨運方式
        String sType = request.getParameter("shippingType");
        if (sType == null || !sType.matches("[0-2]")) {
            errors.add("必須選擇貨運方式");
        }
        
        //收件人資料
       String name = request.getParameter("receiver_name");
        if (name == null || (name = name.trim()).length() == 0) {
            errors.add("必須輸入收件人姓名");
        }

        String email = request.getParameter("receiver_email");
        if (email == null || (email = email.trim()).length() == 0) {
            errors.add("必須輸入收件人信箱");
        }

        String phone = request.getParameter("receiver_phone");
        if (phone == null || (phone = phone.trim()).length() == 0) {
            errors.add("必須輸入收件人電話");
        }

        String address = request.getParameter("receiver_address");
        if (address == null || (address = address.trim()).length() == 0) {
            errors.add("必須輸入收件人地址");
        }
        
        //如果沒有錯誤
        if(errors.isEmpty()){
             //2. call business service
           try {
               //取得使用者跟購物車放入訂單
                Order order = new Order(user, cart);
                //取得付款方式
                Order.PaymentType paymentType = Order.PaymentType.values()[Integer.parseInt(pType)];
                //設定付款方式
                order.setPaymentType(paymentType);
                //付款方式需不需要手續費
                order.setPaymentAmount(paymentType.getAmount());
                
                //購買方式
                Order.ShippingType shippingType = Order.ShippingType.values()[Integer.parseInt(sType)];
                //設定取貨方式
                order.setShippingType(shippingType);
                //購買多少
                order.setShippingAmount(shippingType.getAmount());
                //設定接收人姓名、信箱、電話、地址
                order.setReceiverName(name);
                order.setReceiverEmail(email);
                order.setReceiverPhone(phone);
                order.setShippingAddress(address);

                OrderService service = new OrderService();
                //確認結帳
                service.checkOut(order);
                //清掉購物車
                session.removeAttribute("cart");
                
                //3.1 redirect /user/orders_history
                response.sendRedirect(request.getContextPath() + "/user/orders_history.jsp");
                
                //發送信件
                //發送Mail(SMTP)Server需要TLS or SSL:smtp.gamil.com(使用身份驗證)使用身份證:yes
                String host = "smtp.gmail.com";
                int port = 587;// TLS/STARTTLS的port號:587，SSL的port號:465
                final String username = "q0961136702@gmail.com";
                final String password = "0961136702";//your password
                
                //Properties性能設定
                Properties props = new Properties();
                props.put("mail.smtp.host", host);
                props.put("mail.smtp.auth", "true");
                props.put("mail.smtp.starttls.enable", "true");
                props.put("mail.smtp.port", port);
                Session Mailsession = Session.getInstance(props, new Authenticator() {//身份驗證
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);//密碼驗證
                    }
                });

                String a = "";
                int b = 0;

                for (Product p : cart.getKeySet()) {
                    
                    a = a.concat(p.getName());
                    a = a.concat("\t數量:"+Integer.toString(cart.getQuentity(p))+"\n");
                    //b = cart.getQuentity(p); 
                }
                
            
                try {
                    //信件訊息設定
                    Message message = new MimeMessage(Mailsession);//郵件會話
                    message.setFrom(new InternetAddress("TotalBuyOffical@gmail.com", "通通買購物網"));//寄件人email，名稱
                    //收件人
                    message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(order.getReceiverEmail()));
                    message.setSubject("通通買購物網訂單通知");//主題
                    //   String test = String.format(a,"Dear Levin, \n\n 測試 測試 測試 測試 測試 測試 email !%s"); 
                    //order.getReceiverName()收件人姓名
                    message.setText("您好!" + user.getName() + "\n您訂購了:\n " + 
                             a + "總金額:"+ cart.getTotalAmount()+ "\n謝謝您的訂購。" + "\n訂購時間為: " + 
                            order.getOrderTime() );

                    //    message.setText("嚕拉拉");
                    //傳輸
                    Transport transport = Mailsession.getTransport("smtp");
                    //取這個身份發送
                    transport.connect(host, port, username, password);
                    //將訊息傳送
                    Transport.send(message);

                    System.out.println("寄送email結束.");

                } catch (MessagingException e) {
                    throw new RuntimeException(e);
                } catch (UnsupportedEncodingException ex) {
                    System.out.println(ex);
                }
                return;
            } catch (TotalBuyException ex) {
                ex.printStackTrace(System.out);
                errors.add(ex.toString());
            } catch (Exception ex) {
                ex.printStackTrace(System.out);
                this.log("結帳失敗!", ex);
                errors.add(ex.toString());
            }
        }

        System.out.println(errors);
        //3.2 forward to /user/check_out.jsp
        request.setAttribute("errors", errors);
        request.getRequestDispatcher("/user/check_out.jsp").forward(request, response);
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
