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
import model.cart.Cart;
import model.item.Item;

/**
 *
 * @author asus
 */
public class CartLoginCheckServlet extends HttpServlet {

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
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet CartLoginCheckServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet CartLoginCheckServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
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
        HttpSession session = request.getSession(true);
       
        if (session.getAttribute("account") == null) {
            response.sendRedirect("login.jsp");
            return;
        } 
         Cart cart = (Cart) session.getAttribute("cart");
            int size = (int)session.getAttribute("size");
            String amount_raw = request.getParameter("quantity");
            String id_raw = request.getParameter("id");
            String page_raw = request.getParameter("page");
            
           
           
         if(size!=0){
            
            Item item = new Item();
            item = cart.getItemById(Integer.parseInt(id_raw));
            if(item!=null){
            int stock = item.getProduct().getStock();
            int oncartquantity = item.getQuantity();
            int enterquantity = Integer.parseInt(amount_raw);
           
               
            
            if(oncartquantity<=3){
//              String notice = ""+oncartquantity+" products already in the cart! adddition "+enterquantity+" product will exceed the stock!";
//              request.setAttribute("ms", notice);   
//                request.getRequestDispatcher("index.jsp").forward(request, response);
amount_raw ="2";
response.sendRedirect("buy?id=" + id_raw + "&quantity=" + amount_raw + "&page=" + page_raw); 
            }
            else{
              response.sendRedirect("buy?id=" + id_raw + "&quantity=" + amount_raw + "&page=" + page_raw);    
            }
            }else{
              response.sendRedirect("buy?id=" + id_raw + "&quantity=" + amount_raw + "&page=" + page_raw);    
            }
          
        }
        else{
          response.sendRedirect("buy?id=" + id_raw + "&quantity=" + amount_raw + "&page=" + page_raw);  
        }
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
//            int id=0, amount, in_cart_quantity=0,page;
//           
//            String notice = "";
//            try {
//                id = Integer.parseInt(id_raw);
//                amount = Integer.parseInt(amount_raw);
//                page = Integer.parseInt(page_raw);
//                
//                
//                if (cart_size == 0) {
//                    response.sendRedirect("buy?id=" + id_raw + "&quantity=" + amount_raw + "&page=" + page_raw);
//                } else {
//                // in_cart_quantity =cart.getQuantityById(id);   
//                    for (int i = 0; i < cart_size; i++) {
//                        if (cart.getItems().get(i).getProduct().getId() == id) {
//                            item_stock = cart.getItems().get(i).getProduct().getStock();
//                           in_cart_quantity = cart.getItems().get(i).getQuantity();
//                        }
//                    }
//                    System.out.println(item_stock);
//                    System.out.println(in_cart_quantity);
//                    if (amount + in_cart_quantity > item_stock) {
//                        notice = ""+in_cart_quantity+" already in the cart! adddition "+amount+" product will exceed the stock!";
//                        request.setAttribute("ms", notice);
//                        if (page == 1) {
//                            request.getRequestDispatcher("cart.jsp").forward(request, response);
//                            
//                        } else {
//                            request.getRequestDispatcher("index.jsp").forward(request, response);
//                           
//                        }
//                    } else {
//                        response.sendRedirect("buy?id=" + id_raw + "&quantity=" + amount_raw + "&page=" + page_raw);
//                        return;
//                    }
//                }
//            } catch (Exception e) {
//                System.out.println(e);
//            }