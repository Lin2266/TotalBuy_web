/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uuu.totalbuy.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;
import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

//圖形檢驗碼
//設定檔WebServlet(檔名:web.xml)應該部署在伺服器裡，不適合放在這，servlet要在伺服器裡跑
//要使用/images/register_check.jpg時，先找WebServlet的name的ImageCheckServlet物件，找不到在到類別的
//ImageCheckServlet在建立物件在name的ImageCheckServlet物件
//@WebServlet(name = "ImageCheckServlet", urlPatterns = {"/images/register_check.jpg"})
public class ImageCheckServlet extends HttpServlet {
        //要用亂圖檢驗碼，實體圖不能用跟他一樣的名稱，
        //不然會先找實體圖，不會產生亂圖
        //宣告3個屬性來定義圖像的字數、高度、寬度      
        private int len =6,
                    width = 16*2+12*len,
                    height = 20;
        //Servlet一定要用無參數建構子
        //不可以在實體物件建立建構子
        //11-3 測試生命週期開始
        public ImageCheckServlet(){
            System.out.println("ImageCheckServlet created...");   
        }
        //跟web.xml產生關聯，初始化參數，所以可以另外設定屬性參數
       //11-5
        @Override
        public void init(){
              System.out.println(this.getServletName() + "init....");
              //取得len名稱指派給字串變數
              String len = this.getInitParameter("len");
              
              if(len!=null && len.matches("\\d+")){
                  this.len = Integer.parseInt(len);
                  this.width = 16*2+12*this.len;
              }
              
        }
        
        
        //generateImage產生影像               
 private BufferedImage generateImage(String rand, int width, int height) {
        //開始建立影像
        BufferedImage image =
                new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics g = image.getGraphics();   //取得影像繪圖區,Graphics圖像
        g.setColor(getRandomColor(200, 250)); //設定繪圖區背景色
        g.fillRect(0, 0, width, height);  //在繪圖區畫個長方型
        g.setFont(new Font("Arial", Font.PLAIN, 18));//設定字體
 
        //產生干擾線讓影像略模糊不易識別,random隨機
        Random random = new Random();
        g.setColor(getRandomColor(170, 210));
        for (int i = 0; i < 155; i++) {
            //nextInt隨機
            int x = random.nextInt(width);
            int y = random.nextInt(height);
            //隨機12種變化
            int xl = random.nextInt(12);
            int yl = random.nextInt(12);
            g.drawLine(x, y, x + xl, y + yl);
        }
 
        //將認證數字顯示到影像
        g.setColor(getRandomColor(20, 140));
        //將認證數字顯示到影像
        g.drawString(rand, 16, 16);
        //部署
        g.dispose();
        return image;
    }
    //RandomColor隨機顏色
    private Color getRandomColor(int fc, int bc) {
        //在參數設定的範圍內，隨機產生顏色
        Random random = new Random();
        if (fc > 255) {
            fc = 255;
        }
        if (bc > 255) {
            bc = 255;
        }
        int r = fc + random.nextInt(bc - fc);
        int g = fc + random.nextInt(bc - fc);
        int b = fc + random.nextInt(bc - fc);
        return new Color(r, g, b);
    }
 
//(4)    修改processRequest的內容為：(處理請求)
    protected void processRequest(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        String rand = null;//數字是空的時候
        HttpSession session = request.getSession();
        //1. 讀取請求資料
        //    判斷要用上次請求中產生的值、還是要產生新的值
        String s = request.getParameter("get");
        if ( s == null) {
            rand = (String) session.getAttribute(getServletName());
        } else {
            session.removeAttribute(getServletName());
        }
 
        //2. 執行商業邏輯
        //亂數產生len個英數字的字串
//        字串是空的時
        if (rand == null) {
            StringBuilder sb_rand = new StringBuilder();
//            建立亂數
            Random r = new Random();
            for (int i = 0; i < len; i++) {
                //0~35=36個選擇(英數字)
                int data = r.nextInt(35);
                //1~10
                sb_rand.append(data < 10 ? (char) (data + '0') : (char) (data - 10 + 'A'));
            }//有做兩種圖(登入、註冊)轉成字串
            session.setAttribute(getServletName(), rand = sb_rand.toString());
        }
 
        //3.產生回應
        response.setContentType("image/jpeg");
        //清掉cache(底層系統訊息)
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);
        //圖是位元要OutputStream
        ServletOutputStream outStream = response.getOutputStream();
 
        // generateImage方法會將這個字串建為影像
        BufferedImage image = generateImage(rand, width, height);
        try {      //將影像輸出到頁面
            ImageIO.write(image, "JPEG", outStream);
        } finally { //最後記得關閉輸出串流
            outStream.flush();
            outStream.close();
        }
    }
    
    //測試生命週期結束
    
                        
    
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    //protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    //          throws ServletException, IOException {
        
        /*
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
             TODO output your page here. You may use following sample code. 
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ImageCheckServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ImageCheckServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }*/
    //}
    
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
              throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    //@Override
    //protected void doPost(HttpServletRequest request, HttpServletResponse response)
    //          throws ServletException, IOException {
    //    processRequest(request, response);
    //}

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
