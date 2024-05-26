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
import model.order.Order;
import model.order.OrderDAO;
import model.order.OrderDetail;
import model.order.OrderDetailDAO;

/**
 *
 * @author asus
 */
public class OrderManagerServlet extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
     protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        try {
            HttpSession session = request.getSession();
            Account acc = (Account) session.getAttribute("account");
            String action = request.getParameter("action");
            OrderDAO odao = new OrderDAO();
            OrderDetailDAO oddao = new OrderDetailDAO();
            if (acc.getRole()==0) {
                if (action == null) {
                    List<Order> order = odao.getAllOrder();
                    request.setAttribute("order", order);
                    request.getRequestDispatcher("AdminOrder.jsp").forward(request, response);
                  
                }
                if(action.equals("showdetail")){
                   String order_id = request.getParameter("order_id");
                   int id = Integer.parseInt(order_id);
                   List<OrderDetail> detail = oddao.getDetail(id);
                   request.setAttribute("detail", detail);
                   request.getRequestDispatcher("AdminOrderDetail.jsp").forward(request, response); 
                }
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
