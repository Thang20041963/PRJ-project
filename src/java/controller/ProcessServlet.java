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
import model.cart.Cart;
import model.item.Item;
import model.product.Product;
import model.product.ProductDAO;

/**
 *
 * @author asus
 */
public class ProcessServlet extends HttpServlet {
   
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
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ProcessServlet</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ProcessServlet at " + request.getContextPath () + "</h1>");
            out.println("</body>");
            out.println("</html>");
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
         HttpSession session = request.getSession(true);
        Cart cart = null;
        Object o = session.getAttribute("cart");
        if (o != null) {
            cart = (Cart) o;
        } else {
            cart = new Cart();
        }
        String num_raw = request.getParameter("quantity").trim();
        String id_raw = request.getParameter("id").trim();
        int id, amount;
        try {
            id = Integer.parseInt(id_raw);
            amount = Integer.parseInt(num_raw);
            String ms = "";
            if(amount==1){
            for(int i=0;i<cart.getItems().size();i++){
                if(cart.getItems().get(i).getQuantity()>=cart.getItems().get(i).getProduct().getStock()){
                 ms="Quantity of product:"+cart.getItems().get(i).getProduct().getTitle()+" reach max stock";
                request.setAttribute("ms", ms);
                request.getRequestDispatcher("cart.jsp").forward(request, response);
                return;
                }
            }
            }
            if (cart.getQuantityById(id) + amount == 0) {
                cart.removeItem(id);
                int size = (int) session.getAttribute("size");
                session.setAttribute("size", size - 1);
            } else {
                ProductDAO productDAO = new ProductDAO();
                Product product = productDAO.getProduct(id);
                double price = product.getOutPrice();
                Item item = new Item(product, amount, price);
                cart.addItem(item);
            }
        } catch (NumberFormatException e) {
            System.out.println(e);
        }
        request.getRequestDispatcher("cart.jsp").forward(request, response);
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
        HttpSession session = request.getSession(true);
        Cart cart = null;
        Object o = session.getAttribute("cart");
        if (o != null) {
            cart = (Cart) o;
        } else {
            cart = new Cart();
        }
        String page = request.getParameter("page").trim();
        String id_raw = request.getParameter("id").trim();
        String productpage_raw = request.getParameter("productId");
        int id;        
        try{
          id = Integer.parseInt(id_raw);
          cart.removeItem(id);
        }catch(NumberFormatException e){
          System.out.println(e);   
        }
         List<Item> list = cart.getItems();
            session.setAttribute("cart",cart);
        
            session.setAttribute("size", list.size());
        if(productpage_raw == null){
       // request.getRequestDispatcher(page).forward(request, response);
        response.sendRedirect(page);
        }else
        {
            int pageid= Integer.parseInt(productpage_raw);
           response.sendRedirect("productdetails?productId=" + pageid);
        }
            
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
