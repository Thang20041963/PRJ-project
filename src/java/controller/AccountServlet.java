/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

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
import model.cart.Cart;
import model.order.Order;
import model.order.OrderDAO;

/**
 *
 * @author asus
 */
public class AccountServlet extends HttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");
        
        HttpSession session = request.getSession(true);
        try{
        Account acc = (Account) session.getAttribute("account");
        if (acc == null) {
            request.getRequestDispatcher("login.jsp").forward(request, response);
        } else {
        String username = acc.getUsername();
        String password = acc.getPassword();
        String action = request.getParameter("action");
        
        
             OrderDAO odao = new OrderDAO();
             MoneyRequestDAO mndao = new MoneyRequestDAO();
             List<MoneyRequest> money = mndao.getCustomerRequest(acc.getId());
                List<Order> order = odao.CustomerOrder(acc.getId());
                request.setAttribute("order", order);
                request.setAttribute("moneyrequest", money);
            if (action == null) {
               
                request.getRequestDispatcher("myaccount.jsp").forward(request, response);

            }
            if(action.equals("changeinfor")){
            String update_raw = request.getParameter("update").trim();
            
            Account customer = (Account) session.getAttribute("account");
            
            Account account = new Account();
            AccountDAO accDAO = new AccountDAO();    
             String username_raw = request.getParameter("username").trim();
                String fullname_raw = request.getParameter("fullname").trim();
                String phone_raw = request.getParameter("phone").trim();
                String email_raw = request.getParameter("email").trim();

                String gender_raw = request.getParameter("gender").trim();
                String dob_raw = request.getParameter("dob").trim();
                accDAO.updateCustomer(customer.getId(), username_raw, fullname_raw, phone_raw, email_raw, gender_raw, dob_raw);
                session.removeAttribute("account");
            try {  
                Account a = accDAO.getAccount(username, password);
                session.setAttribute("account", a);
            } catch (ClassNotFoundException ex) {
                System.out.println(ex);
            }
                request.getRequestDispatcher("myaccount.jsp").forward(request, response);   
            }
            
            if(action.equals("changepass")){
             String update_raw = request.getParameter("update").trim();
            
            Account customer = (Account) session.getAttribute("account");
            
            Account account = new Account();
            AccountDAO accDAO = new AccountDAO();    
              String oldpass = request.getParameter("oldpass").trim();
                String newpass = request.getParameter("newpass").trim();
                String checkpass = request.getParameter("checkpass").trim();
                String ms = "";
                if (oldpass.equals(customer.getPassword().trim())) {
                    if (newpass.equals(checkpass)) {
                        accDAO.updateCustomerPass(customer.getId(), newpass);
                        request.getRequestDispatcher("login.jsp").forward(request, response);
                    } else {
                        ms = "Re-enter wrong password!";
                        request.setAttribute("ms", ms);
                        request.getRequestDispatcher("myaccount.jsp").forward(request, response);
                    }
                } else {
                    ms = "Wrong password!";
                    request.setAttribute("ms", ms);
                    request.getRequestDispatcher("myaccount.jsp").forward(request, response);
                }   
            }
            //---------------------
            
        }
        }catch(Exception e) {
            System.out.println(e);
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
