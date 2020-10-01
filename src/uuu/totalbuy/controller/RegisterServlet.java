/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uuu.totalbuy.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
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
import uuu.totalbuy.domain.BloodType;
import uuu.totalbuy.domain.Customer;
import uuu.totalbuy.model.CustomerService;


/**
 *
 * @author Administrator
 */
@WebServlet(name = "RegisterServlet", urlPatterns = {"/register.do"})
public class RegisterServlet extends HttpServlet {

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
        List<String> errors = new ArrayList<>();
        //1.讀取請求中的表單資料並檢查
        request.setCharacterEncoding("UTF-8");

        //1.1 以下為必要欄位
        String id = request.getParameter("id");
        if (id != null) {
            id = id.trim();
        }

        String name = request.getParameter("name");
        if (name != null) {
            name = name.trim();
        }
        System.out.println("name:" + name);

        String password1 = request.getParameter("pwd1");
        String password2 = request.getParameter("pwd2");
        if (password1 != null && (password1 = password1.trim()).length() > 0
                && password2 != null && (password2 = password2.trim()).length() > 0) {
            if (!password1.equals(password2)) {
                errors.add("會員密碼與確認密碼不一致.");
            }
        } else {
            errors.add("會員密碼與確認密碼都必須輸入");
        }

        char gender;
        if(request.getParameter("gender")!=null){
            gender = request.getParameter("gender").charAt(0);
        }else{
            gender = Customer.MALE;
            errors.add("必須輸入性別");
        }

        String email = request.getParameter("email");
        if (email != null) {
            email = email.trim();
        }

        //以下為非必要欄位
        String birthday = request.getParameter("birthdate");
        if (birthday != null) {
            birthday = birthday.trim();
        }

        String address = request.getParameter("address");
        if (address != null) {
            address = address.trim();
        }

        String phone = request.getParameter("phone");
        if (phone != null) {
            phone = phone.trim();
        }

        String married = request.getParameter("married");

        String bloodType = request.getParameter("bloodType");

        String checkCode = request.getParameter("checkCode");
        HttpSession session = request.getSession();
        if (checkCode == null || (checkCode = checkCode.trim()).length() == 0) {
            errors.add("必須輸入checkCode");
        } else {
            String rand = (String) session.getAttribute("RegisterImageCheckServlet");
            if (rand == null || !rand.equalsIgnoreCase(checkCode)) {
                errors.add("checkCode不正確");
            }
        }

//        response.setContentType("text/html");
//        response.setCharacterEncoding("UTF-8");
//        PrintWriter out = response.getWriter();
        //2.呼叫商業邏輯
        if (errors.isEmpty()) {
            session.removeAttribute("RegisterImageCheckServlet");

            Customer m = new Customer();
            try {
                m.setId(id);
                m.setName(name);
                m.setPassword(password1);
                m.setGender(gender);
                m.setEmail(email);

                m.setBirthDate(birthday);
                m.setAddress(address);
                m.setPhone(phone);
                m.setMarried(married != null);

                BloodType bType = null;
                if (bloodType != null && (bloodType = bloodType.trim()).length() > 0) {
                    bType = BloodType.valueOf(bloodType);
                }
                m.setBloodType(bType);

                CustomerService service = new CustomerService();
                service.register(m);
                request.setAttribute("member", m);

                //3.1 forward to ok JSP
                request.getRequestDispatcher("/WEB-INF/views/register_ok.jsp").forward(request, response);
                
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
                
                try {
                    //信件訊息設定
                    Message message = new MimeMessage(Mailsession);//郵件會話
                    message.setFrom(new InternetAddress("TotalBuyOffical@gmail.com", "通通買購物網"));//寄件人email，名稱
                    //收件人
                    message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(m.getEmail()));
                    message.setSubject("通通買購物網註冊會員通知");//主題
                    //   String test = String.format(a,"Dear Levin, \n\n 測試 測試 測試 測試 測試 測試 email !%s"); 
                    //order.getReceiverName()收件人姓名
                    message.setText("您好!" + m.getName() + "\n您註冊了通通買購物網會員成功~\n " + 
                            m +"謝謝您的歡迎。\n" + new Date());

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

            } catch (Exception ex) {
                errors.add(ex.toString());
                this.log("新會員註冊失敗", ex);
            }
        }
        //3.2 forward to register           
        request.setAttribute("errors", errors);
        //3.1 forward to ok JSP
        request.getRequestDispatcher("/register.jsp").forward(request, response);

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
//            throws ServletException, IOException {
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
