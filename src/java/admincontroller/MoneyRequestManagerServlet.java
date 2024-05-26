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

/**
 *
 * @author asus
 */
public class MoneyRequestManagerServlet extends HttpServlet {
   
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
            if (acc.getRole()==0) {
                if (action == null) {
                  MoneyRequestDAO mrqdao = new MoneyRequestDAO();
                  List<MoneyRequest> money = mrqdao.getAllRequest();
                  
                    request.setAttribute("moneyrequest", money);
                    request.getRequestDispatcher("AdminMoneyRequest.jsp").forward(request, response);
                }
                if(action.equals("accept")){
                    String request_id = request.getParameter("request_id");
                    String note = request.getParameter("note");
                    String money = request.getParameter("money");
                    String customerid = request.getParameter("customerid");
                    MoneyRequestDAO mndao = new MoneyRequestDAO();
                    AccountDAO adao = new AccountDAO();
                    int id = Integer.parseInt(request_id);
                    int cid =Integer.parseInt(customerid);
                    double balance = Double.parseDouble(money);
                    mndao.updateRequest(id, "accept", note);
                    Account a = adao.getCustomerById(cid);
                    adao.updateBalance(cid, a.getBalance()+balance);
                    response.sendRedirect("moneyrequestmanager");
                }if(action.equals("deny")){
                  String request_id = request.getParameter("request_id");
                    String note = request.getParameter("note");  
                    int id = Integer.parseInt(request_id);
                    MoneyRequestDAO mndao = new MoneyRequestDAO();
                    mndao.updateRequest(id, "deny", note);
                    response.sendRedirect("moneyrequestmanager");
                }
                
            }else {
                response.sendRedirect("login.jsp");
            }
        } catch (Exception e) {
            System.out.println(e);
            //response.sendRedirect("404.jsp");
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
