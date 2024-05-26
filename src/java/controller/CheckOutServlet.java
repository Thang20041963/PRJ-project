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
import java.util.logging.Level;
import java.util.logging.Logger;
import model.account.Account;
import model.account.AccountDAO;
import model.cart.Cart;
import model.cart.CartDAO;
import model.order.OrderDAO;
import model.product.Product;
import model.product.ProductDAO;

/**
 *
 * @author asus
 */
public class CheckOutServlet extends HttpServlet {
   
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
       HttpSession session = request.getSession(true);
       try{
        Account acc1 = (Account) session.getAttribute("account");
        String action = request.getParameter("action");
        if (acc1 == null) {
            request.getRequestDispatcher("login.jsp").forward(request, response);
        } else {
        if(action==null){
            request.getRequestDispatcher("checkout.jsp").forward(request, response);
        }
        if(action.equals("order")){
        Cart cart = (Cart) session.getAttribute("cart");
        Account customer = (Account) session.getAttribute("account");
        AccountDAO accDAO = new AccountDAO();
        String username = customer.getUsername();
        String password = customer.getPassword();
        Product product = new Product();
        ProductDAO productDAO =new ProductDAO();
        String address =request.getParameter("address");
        String city =request.getParameter("city");
        String province =request.getParameter("province");
        String country =request.getParameter("country");
        String zipcode =request.getParameter("zip-code");
        String ms="";
        if(cart.getItems().size()==0){
           ms="Your cart is empty!";
           request.setAttribute("ms", ms);
           request.getRequestDispatcher("checkout.jsp").forward(request, response); 
        }
        else if(address.isBlank()||city.isBlank()||province.isBlank()||country.isBlank()||zipcode.isBlank()){
           ms="Please fill all the address infomation!";
           request.setAttribute("ms", ms);
           request.getRequestDispatcher("checkout.jsp").forward(request, response); 
        }
        else if(customer.getFullname()==null||customer.getEmail()==null||customer.getPhonenumber()==null||customer.getGender()==null||customer.getBirthday()==null){
           ms="Fill all your account`s information befor place order! ";
           request.setAttribute("ms", ms);
           request.getRequestDispatcher("checkout.jsp").forward(request, response);
        }
        else if(cart.getTotalMoney()>customer.getBalance()){
           ms="Your balance isn`t enough";
           request.setAttribute("ms", ms);
           request.getRequestDispatcher("checkout.jsp").forward(request, response);
        } else{
        ms="order successful!";
        double returnbalance = customer.getBalance()-cart.getTotalMoney();
        
        accDAO.updateBalance(customer.getId(), returnbalance);
        
        String location = address +"-"+city+"-"+country+"-"+province+"-"+zipcode;
        for(int i =0;i<cart.getItems().size();i++){
            productDAO.updateStock(cart.getItems().get(i).getProduct().getId(), cart.getItems().get(i).getProduct().getStock()-cart.getItems().get(i).getQuantity());
        }
        productDAO.updateStock(cart.getItems().get(0).getProduct().getId(), cart.getItems().get(0).getProduct().getStock()-cart.getItems().get(0).getQuantity());
        OrderDAO orderDAO = new OrderDAO();
        orderDAO.addOrder(customer, cart,location);
        CartDAO cartDAO = new CartDAO();
        cartDAO.removeCart(customer.getId());
        request.setAttribute("ms", ms);
        session.removeAttribute("cart");
        
        session.removeAttribute("customer");
            try {  
                Account a = accDAO.getAccount(username, password);
                session.setAttribute("account", a);
            } catch (ClassNotFoundException ex) {
               
            }
        session.setAttribute("size", 0);
        request.getRequestDispatcher("checkout.jsp").forward(request, response);
        }
        }
        }
       }catch(Exception e){
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
//         HttpSession session = request.getSession(true);
//        Account acc1 = (Account) session.getAttribute("account");
//
//        if (acc1 == null) {
//            request.getRequestDispatcher("login.jsp").forward(request, response);
//        } else {
//        
//        Cart cart = (Cart) session.getAttribute("cart");
//        Account customer = (Account) session.getAttribute("account");
//        AccountDAO accDAO = new AccountDAO();
//        String username = customer.getUsername();
//        String password = customer.getPassword();
//        Product product = new Product();
//        ProductDAO productDAO =new ProductDAO();
//        String address =request.getParameter("address");
//        String city =request.getParameter("city");
//        String province =request.getParameter("province");
//        String country =request.getParameter("country");
//        String zipcode =request.getParameter("zip-code");
//        String ms="";
//        if(cart.getItems().size()==0){
//           ms="Your cart is empty!";
//           request.setAttribute("ms", ms);
//           request.getRequestDispatcher("checkout.jsp").forward(request, response); 
//        }
//        else if(address.isBlank()||city.isBlank()||province.isBlank()||country.isBlank()||zipcode.isBlank()){
//           ms="Please fill all the address infomation!";
//           request.setAttribute("ms", ms);
//           request.getRequestDispatcher("checkout.jsp").forward(request, response); 
//        }
//        else if(customer.getFullname()==null||customer.getEmail()==null||customer.getPhonenumber()==null||customer.getGender()==null||customer.getBirthday()==null){
//           ms="Fill all your account`s information befor place order! ";
//           request.setAttribute("ms", ms);
//           request.getRequestDispatcher("checkout.jsp").forward(request, response);
//        }
//        else if(cart.getTotalMoney()>customer.getBalance()){
//           ms="Your balance isn`t enough";
//           request.setAttribute("ms", ms);
//           request.getRequestDispatcher("checkout.jsp").forward(request, response);
//        } else{
//        ms="order successful!";
//        double returnbalance = customer.getBalance()-cart.getTotalMoney();
//        
//        accDAO.updateBalance(customer.getId(), returnbalance);
//        
//        String location = address +"-"+city+"-"+country+"-"+province+"-"+zipcode;
//        for(int i =0;i<cart.getItems().size();i++){
//            productDAO.updateStock(cart.getItems().get(i).getProduct().getId(), cart.getItems().get(i).getProduct().getStock()-cart.getItems().get(i).getQuantity());
//        }
//        productDAO.updateStock(cart.getItems().get(0).getProduct().getId(), cart.getItems().get(0).getProduct().getStock()-cart.getItems().get(0).getQuantity());
//        OrderDAO orderDAO = new OrderDAO();
//        orderDAO.addOrder(customer, cart,location);
//        CartDAO cartDAO = new CartDAO();
//        cartDAO.removeCart(customer.getId());
//        request.setAttribute("ms", ms);
//        session.removeAttribute("cart");
//        
//        session.removeAttribute("customer");
//            try {  
//                Account a = accDAO.getAccount(username, password);
//                session.setAttribute("account", a);
//            } catch (ClassNotFoundException ex) {
//               
//            }
//        session.setAttribute("size", 0);
//        request.getRequestDispatcher("checkout.jsp").forward(request, response);
//        }
//        }
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
