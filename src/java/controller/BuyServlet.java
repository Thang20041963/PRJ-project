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
public class BuyServlet extends HttpServlet {

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
            out.println("<title>Servlet BuyServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet BuyServlet at " + request.getContextPath() + "</h1>");
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
        HttpSession session = request.getSession(true);
        if (session.getAttribute("account") == null) {
            response.sendRedirect("login.jsp");
        } else {
        Cart cart = null;
        Object object = session.getAttribute("cart");
        if (object != null) {
            cart = (Cart) object;
        } else {
            cart = new Cart();
        }
        String amount_raw = request.getParameter("quantity");
        String productId_raw = request.getParameter("id");
        String page_raw = request.getParameter("page");
        int amount, productId, page;
        page = Integer.parseInt(page_raw);
        try {
            amount = Integer.parseInt(amount_raw);
            productId = Integer.parseInt(productId_raw);
            ProductDAO productDAO = new ProductDAO();
            Product product = productDAO.getProduct(productId);
            double price = product.getOutPrice();
            Item item = new Item(product, amount, price);
            cart.addItem(item);
        } catch (NumberFormatException e) {
            System.out.println(e);
        }

        List<Item> list = cart.getItems();
        session.setAttribute("cart", cart);
        if (list != null) {
            session.setAttribute("size", list.size());
        } else {
            session.setAttribute("size", 0);
        }
        if (page == 1) {
            response.sendRedirect("productdetails?productId=" + productId_raw);
        } else {
            response.sendRedirect("homepage");
        }
        }
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
        } else {
        Cart cart = null;
        Object object = session.getAttribute("cart");
        if (object != null) {
            cart = (Cart) object;
        } else {
            cart = new Cart();
        }
        String amount_raw = request.getParameter("quantity");
        String productId_raw = request.getParameter("id");
        String page_raw = request.getParameter("page");
        int amount, productId, page;
        page = Integer.parseInt(page_raw);
        try {
            amount = Integer.parseInt(amount_raw);
            productId = Integer.parseInt(productId_raw);
            ProductDAO productDAO = new ProductDAO();
            Product product = productDAO.getProduct(productId);
            double price = product.getOutPrice();
            Item item = new Item(product, amount, price);
            cart.addItem(item);
        } catch (NumberFormatException e) {
            System.out.println(e);
        }

        List<Item> list = cart.getItems();
        session.setAttribute("cart", cart);
        if (list != null) {
            session.setAttribute("size", list.size());
        } else {
            session.setAttribute("size", 0);
        }
        if (page == 1) {
            response.sendRedirect("productdetails?productId=" + productId_raw);
        } else {
            response.sendRedirect("index.jsp");
        }
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
