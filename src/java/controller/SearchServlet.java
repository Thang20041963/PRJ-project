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
import java.util.ArrayList;
import java.util.List;
import model.brand.Brand;
import model.brand.BrandDAO;
import model.cart.Cart;
import model.cart.CartDAO;
import model.product.Product;
import model.product.ProductDAO;
import model.type.Type;
import model.type.TypeDAO;

/**
 *
 * @author asus
 */
public class SearchServlet extends HttpServlet {

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
        Product product = new Product();
        ProductDAO productDAO = new ProductDAO();
        TypeDAO tdao = new TypeDAO();
        BrandDAO bdao = new BrandDAO();
        String action = request.getParameter("action");
        try {
            List<Type> tlist = tdao.getAll();
            List<Brand> blist = bdao.getAll();
            request.setAttribute("tlist", tlist);
            request.setAttribute("blist", blist);
            if (action == null) {
                List<Product> Llist = new ArrayList<>();

                Llist = productDAO.getAll();
                int lsize = Llist.size();
                request.setAttribute("lsize", lsize);
                request.setAttribute("Llist", Llist);
                request.getRequestDispatcher("search.jsp").forward(request, response);
            }
            if (action.equals("find")) {
                String type_raw = request.getParameter("type");
                String brand_raw = request.getParameter("brand");
                String minprice = request.getParameter("min_price");
                String maxprice = request.getParameter("max_price");
                String sort_raw = request.getParameter("sort");

                int type, brand, sort;
                double min, max;
                int lsize;
                String ms;
                try {
                    if (type_raw == null) {
                        type = 0;
                    } else {
                        type = Integer.parseInt(type_raw);
                    }
                    if (brand_raw == null) {
                        brand = 0;
                    } else {
                        brand = Integer.parseInt(brand_raw);
                    }
                    min = Double.parseDouble(minprice);
                    max = Double.parseDouble(maxprice);
                    sort = Integer.parseInt(sort_raw);
                    List<Product> Llist = new ArrayList<>();
                    if (type != 0 && brand != 0) {
                        Llist = productDAO.getAlltype_brand(brand, type, min, max, sort);
                    }
                    if (type == 0 && brand != 0) {
                        Llist = productDAO.getAllbrand(brand, min, max, sort);
                    }
                    if (type != 0 && brand == 0) {
                        Llist = productDAO.getAlltype(type, min, max, sort);
                    }
                    if (type == 0 && brand == 0) {
                        Llist = productDAO.getAll();
                    }

                    lsize = Llist.size();
                    request.setAttribute("lsize", lsize);
                    request.setAttribute("Llist", Llist);
                    request.getRequestDispatcher("search.jsp").forward(request, response);
                } catch (Exception e) {

                }
            }
            if (action.equals("search")) {
                String searchtype_raw = request.getParameter("search_type");
                String searchname_raw = request.getParameter("search_name");
                List<Product> Llist = new ArrayList<>();
                if (searchtype_raw.equals("0")) {
                    Llist = productDAO.getProductByName(searchname_raw);
                } else if (searchtype_raw.equals("1")) {
                    Llist = productDAO.getProductBybrand(searchname_raw);
                } else {
                    Llist = productDAO.getProductByType(searchname_raw);
                }
                int lsize = Llist.size();
                request.setAttribute("lsize", lsize);
                request.setAttribute("Llist", Llist);
                request.getRequestDispatcher("search.jsp").forward(request, response);
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
//        String type_raw = request.getParameter("type");
//        String brand_raw= request.getParameter("brand");
//        String minprice=request.getParameter("min_price");
//        String maxprice=request.getParameter("max_price");
//        String sort_raw = request.getParameter("sort");
//        Product product = new Product();
//        ProductDAO productDAO = new ProductDAO();
//        int type,brand,sort;
//            double min,max; 
//            int lsize;
//            String ms;
//        try{
//            if(type_raw==null){
//                type=0;
//            }else{
//             type = Integer.parseInt(type_raw);
//            }
//            if(brand_raw==null){
//                brand=0;
//            }else{
//             brand = Integer.parseInt(brand_raw);
//            }
//            min = Double.parseDouble(minprice);
//            max = Double.parseDouble(maxprice);
//            sort = Integer.parseInt(sort_raw);
//            List<Product> Llist = new ArrayList<>();
//        if (type!=0&&brand!=0) {   
//            Llist = productDAO.getAlltype_brand(brand, type,min,max,sort);
//        }
//        if (type==0 && brand != 0) {
//            Llist = productDAO.getAllbrand(brand,min,max,sort);
//        }
//        if (type != 0 && brand == 0) {
//            Llist = productDAO.getAlltype(type,min,max,sort);
//        }
//        if (type==0&&brand==0) {   
//            Llist = productDAO.getAll();
//        }
//        
//        lsize=Llist.size();
//        request.setAttribute("lsize", lsize);
//        request.setAttribute("Llist", Llist);
//        request.getRequestDispatcher("search.jsp").forward(request, response);
//        }catch(Exception e){
//            
//        }   
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
//    String searchtype_raw = request.getParameter("search_type");
//    String searchname_raw = request.getParameter("search_name");
//    List<Product> product = new ArrayList<>();
//    ProductDAO productDAO = new  ProductDAO();
//    if(searchtype_raw.equals("0")){
//        product= productDAO.getProductByName(searchname_raw);
//    }
//    else if(searchtype_raw.equals("1")){
//        product= productDAO.getProductBybrand(searchname_raw);
//    }else{
//        product= productDAO.getProductByType(searchname_raw);
//    }
//    int lsize=product.size();
//        request.setAttribute("lsize", lsize);
//        request.setAttribute("Llist", product);
//    request.getRequestDispatcher("search.jsp").forward(request, response);
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
