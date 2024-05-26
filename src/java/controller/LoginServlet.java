/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
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

/**
 *
 * @author asus
 */
public class LoginServlet extends HttpServlet {

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
            out.println("<title>Servlet LoginServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet LoginServlet at " + request.getContextPath() + "</h1>");
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
        if (session.getAttribute("cart") != null) {
            CartDAO cartDAO = new CartDAO();
            cartDAO.addCart((Cart) session.getAttribute("cart"), (Account) session.getAttribute("account"));
        }

        session.removeAttribute("account");
        session.removeAttribute("cart");
        session.removeAttribute("size");
        response.sendRedirect("index.jsp");
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
        String login = request.getParameter("login");
        String signup = request.getParameter("signup");

        if (login!=null&& signup==null) {
            String u = request.getParameter("user");
            String p = request.getParameter("pass");
            String r = request.getParameter("rem");

            Cookie cu = new Cookie("cuser", u);
            Cookie cp = new Cookie("cpass", p);
            Cookie cr = new Cookie("crem", r);

            if (r != null) {
                cu.setMaxAge(60 * 60 * 24 * 7);
                cp.setMaxAge(60 * 60 * 24 * 7);
                cr.setMaxAge(60 * 60 * 24 * 7);
            } else {
                cu.setMaxAge(0);
                cp.setMaxAge(0);
                cr.setMaxAge(0);
            }
            response.addCookie(cu);
            response.addCookie(cp);
            response.addCookie(cr);
            AccountDAO d = new AccountDAO();
            try {
                Account a = d.getAccount(u, p);
                if (a == null) {
                    request.setAttribute("ms", "invalid pass or username!");
                    request.getRequestDispatcher("login.jsp").forward(request, response);
                } else {
                    HttpSession session = request.getSession();
                    session.setAttribute("account", a);

                    CartDAO cartDAO = new CartDAO();
                    Cart cart = cartDAO.getCart(a.getId());
                    cartDAO.removeCart(a.getId());
                    session.setAttribute("cart", cart);
                    session.setAttribute("size", cart.getItems().size());
                    if (a.getRole() == 0) {
                        response.sendRedirect("dashboard");
                    } else {
                        response.sendRedirect("homepage");
                    }
                }
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        if (login==null && signup!=null) {
            String u = request.getParameter("regname");
            String p = request.getParameter("regpass");
            String rep = request.getParameter("reregpass");
            String ms = "";
            if (u.isEmpty() || p.isEmpty() || rep.isEmpty()) {
                ms = "Must fill all the infomation!";
                request.setAttribute("ms", ms);
                request.getRequestDispatcher("login.jsp").forward(request, response);

            } else {
                AccountDAO d = new AccountDAO();
                if (d.checkUsername(u) == true) {
                    ms = " Username has exit!pick another! ";
                    request.setAttribute("ms", ms);
                    request.getRequestDispatcher("login.jsp").forward(request, response);
                } else {
                    if (!p.equals(rep)) {
                        ms = "Re-enter fail!";
                        request.setAttribute("ms", ms);
                        request.getRequestDispatcher("login.jsp").forward(request, response);
                    } else {
                        d.addAccount(u, p);
                        try {
                            Account a = d.getAccount(u, p);
                            HttpSession session = request.getSession();
                            session.setAttribute("account", a);

                            CartDAO cartDAO = new CartDAO();
                            Cart cart = cartDAO.getCart(a.getId());
                            cartDAO.removeCart(a.getId());
                            session.setAttribute("cart", cart);
                            session.setAttribute("size", cart.getItems().size());
                            response.sendRedirect("account");

                        } catch (ClassNotFoundException ex) {
                            Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }

                }
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
