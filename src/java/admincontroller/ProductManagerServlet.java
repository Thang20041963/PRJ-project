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
import java.util.ArrayList;
import java.util.List;
import model.account.Account;
import model.brand.Brand;
import model.brand.BrandDAO;
import model.product.Product;
import model.product.ProductDAO;
import model.type.Type;
import model.type.TypeDAO;

/**
 *
 * @author asus
 */
public class ProductManagerServlet extends HttpServlet {

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
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html; charset=UTF-8");
        String action = request.getParameter("action");
        try {

            HttpSession session = request.getSession();
            Account acc = (Account) session.getAttribute("account");

            if (acc.getRole() == 0) {
                if (action == null || action == "") {
                    ProductDAO p = new ProductDAO();
                    TypeDAO t = new TypeDAO();
                    BrandDAO b = new BrandDAO();
                    List<Product> product = p.getAll();
                    List<Type> type = t.getAll();
                    List<Brand> brand = b.getAll();
                    request.setAttribute("ProductData", product);
                    request.setAttribute("TypeData", type);
                    request.setAttribute("BrandData", brand);
                    request.getRequestDispatcher("AdminProduct.jsp").forward(request, response);
                }

                if (action.equalsIgnoreCase("insert")) {
                    request.getRequestDispatcher("AdminProductInsert.jsp").forward(request, response);
                }

                if (action.equalsIgnoreCase("insertproduct")) {

                    String product_name = request.getParameter("product_name").trim();
                    String product_inprice = request.getParameter("in_price").trim();
                    String product_outprice = request.getParameter("out_price").trim();
                    String product_type = request.getParameter("type").trim();
                    String product_brand = request.getParameter("brand").trim();
                    String product_stock = request.getParameter("stock").trim();
                    String product_nokc = request.getParameter("nokc").trim();
                    String product_material = request.getParameter("material").trim();
                    String product_suitability = request.getParameter("suitability").trim();
                    String product_density = request.getParameter("density").trim();
                    String product_discount = request.getParameter("discount").trim();
                    String product_img = request.getParameter("product_img");

                    int nokc = Integer.parseInt(product_nokc);
                    int stock = Integer.parseInt(product_stock);

                    double inprice = Double.parseDouble(product_inprice);
                    double outprice = Double.parseDouble(product_outprice);

                    Float density = Float.parseFloat(product_density);
                    Float discount = Float.parseFloat(product_discount);

                    
                    ProductDAO pdao = new ProductDAO();
                    pdao.insertproduct(product_name, inprice, outprice, stock, nokc, product_material, product_suitability, density, discount, product_type, product_brand,product_img);
                    response.sendRedirect("productmanager?action=insert");

                }

                if (action.equalsIgnoreCase("deleteproduct")) {
                    String product_id = request.getParameter("product_id");
                    ProductDAO pdao = new ProductDAO();
                    int id =Integer.parseInt(product_id);
                    pdao.ProductDelete(id);
                    response.sendRedirect("productmanager");
                }
                if (action.equalsIgnoreCase("addimage")) {
                    String product_id = request.getParameter("product_id");
                    String product_img = request.getParameter("product_img");
                    ProductDAO pdao = new ProductDAO();
                    int id =Integer.parseInt(product_id);
                    pdao.addImage(id, product_img);
                    response.sendRedirect("productmanager");
                }
                if (action.equalsIgnoreCase("deleteimage")) {
                    String product_id = request.getParameter("product_id");
                    String product_img = request.getParameter("product_img");
                    ProductDAO pdao = new ProductDAO();
                    int id =Integer.parseInt(product_id);
                    pdao.ProductImgDelete(id, product_img);
                    response.sendRedirect("productmanager");
                }
                if (action.equalsIgnoreCase("updateproduct")) {
                    String product_id = request.getParameter("product_id").trim();
                     String product_name = request.getParameter("product_name").trim();
                    String product_inprice = request.getParameter("in_price").trim();
                    String product_outprice = request.getParameter("out_price").trim();
                    String product_type = request.getParameter("type").trim();
                    String product_brand = request.getParameter("brand").trim();
                    String product_stock = request.getParameter("stock").trim();
                    String product_nokc = request.getParameter("nokc").trim();
                    String product_material = request.getParameter("material").trim();
                    String product_suitability = request.getParameter("suitability").trim();
                    String product_density = request.getParameter("density").trim();
                    String product_discount = request.getParameter("discount").trim();
                    String product_img = request.getParameter("product_img");
                    String img = request.getParameter("img");
                    System.out.println(product_inprice);
                    System.out.println(product_outprice);
                    String inp = product_inprice.replace(".", "");
                    String outp = product_outprice.replace(".", "");
                    int nokc = Integer.parseInt(product_nokc);
                    System.out.println(nokc);
                    int stock = Integer.parseInt(product_stock);
                    System.out.println(stock);
                    int id = Integer.parseInt(product_id);
                    System.out.println(id);
                    double inprice = Double.parseDouble(inp);
                    System.out.println(inprice);
                    double outprice = Double.parseDouble(outp);
                    System.out.println(outprice);
                    Float density = Float.parseFloat(product_density);
                    System.out.println(density);
                    Float discount = Float.parseFloat(product_discount);
                    System.out.println(discount);
                    
                    ProductDAO pdao = new ProductDAO();
                    pdao.updateproduct(product_name, inprice, outprice, stock, nokc, product_material, product_suitability, density, discount, product_type, product_brand,id,img,product_img);
                    response.sendRedirect("productmanager");
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
