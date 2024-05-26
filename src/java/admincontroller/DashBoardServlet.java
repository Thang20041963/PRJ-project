/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

package admincontroller;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import model.account.Account;
import model.account.AccountDAO;
import model.account.MoneyRequest;
import model.account.MoneyRequestDAO;
import model.order.Order;
import model.order.OrderDAO;
import model.product.ProductDAO;

/**
 *
 * @author asus
 */
public class DashBoardServlet extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
         try {
            HttpSession session = request.getSession();
            Account acc = (Account) session.getAttribute("account");
            if (acc.getRole()==0) {
                ProductDAO pdao = new ProductDAO();
                OrderDAO odao = new OrderDAO();
                AccountDAO adao = new AccountDAO();
                MoneyRequestDAO mrdao = new MoneyRequestDAO();
                int countproduct = pdao.CountProduct();
                int countacc = adao.CountCustomer();
                int countorder = odao.CountOrder();
                int low = pdao.CountLowProduct();
                double in = pdao.Totalin();
                double out =pdao.Totalout();
                List<Order> orderbyday = odao.TodayOrder();
                List<MoneyRequest> mnrq = mrdao.getRequest();
                request.setAttribute("product", countproduct);
                request.setAttribute("user", countacc);
                request.setAttribute("order", countorder);
                request.setAttribute("low", low);
                request.setAttribute("orderbyday", orderbyday);
                request.setAttribute("moneyrequest", mnrq);
                request.setAttribute("in", in);
                request.setAttribute("out", out);
                request.getRequestDispatcher("AdminIndex.jsp").forward(request, response);
            } else {
                response.sendRedirect("login.jsp");
            }
        } catch (Exception e) {
             System.out.println(e);
        }
    } 

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
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
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
