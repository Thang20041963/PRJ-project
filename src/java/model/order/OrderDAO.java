/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.order;

import java.time.LocalDate;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import model.DAO;
import model.account.Account;
import model.cart.Cart;
import model.item.Item;
import utils.DBContext;

/**
 *
 * @author asus
 */
public class OrderDAO extends DBContext implements DAO<Order> {

    @Override
    public Optional<Order> get(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<Order> getAll() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void insert(Order t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void update(Order t, String[] params) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(Order t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public void addOrder(Account customer, Cart cart, String location) {
        LocalDate curDate = java.time.LocalDate.now();
        String date = curDate.toString();
        try {
            String sql = "insert into [orders] values (?,?,?,?)";
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, customer.getId());
            st.setString(2, date);
            st.setString(3, location);
            st.setDouble(4, cart.getTotalMoney());
            st.executeUpdate();

            String sql1 = "select top 1 id from [orders] order by id desc";
            PreparedStatement st1 = connection.prepareStatement(sql1);
            ResultSet rs = st1.executeQuery();
            if (rs.next()) {
                int orderId = rs.getInt(1);
                for (Item i : cart.getItems()) {
                    String sql2 = "insert into [order_details] values(?,?,?,?)";
                    PreparedStatement st2 = connection.prepareStatement(sql2);
                    st2.setInt(1, i.getProduct().getId());
                    st2.setInt(2, orderId);
                    st2.setInt(3, i.getQuantity());
                    st2.setDouble(4, i.getPrice());
                    st2.executeUpdate();
                }
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public int CountOrder() {
        int total = 0;
        String sql = "select count(*) as total from orders where MONTH(order_date) = MONTH(GETDATE()) and year(order_date) = year(GETDATE())";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.executeQuery();
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
             total = rs.getInt("total");   
            }
           
        } catch (Exception e) {
            System.out.println(e);
        }
        return total;
    }

    public List<Order> TodayOrder() throws SQLException {

        List<Order> todayorder = new ArrayList<>();
        String sql = "select * from orders where DAY(order_date)=DAY(GETDATE()) and MONTH(order_date) = MONTH(GETDATE()) and year(order_date) = year(GETDATE()) ";
        PreparedStatement st1 = connection.prepareStatement(sql);
        ResultSet rs = st1.executeQuery();
        while (rs.next()) {
            Order order = new Order();
            order.setId(rs.getInt("id"));
            order.setCustomer_id(rs.getInt("customer_id"));
            order.setOrderDate(rs.getDate("order_date"));
            order.setReceiveLocation(rs.getString("receive_location"));
            order.setTotalPrice(rs.getDouble("total_price"));
            todayorder.add(order);
        }
        return todayorder;

    }
        public List<Order> CustomerOrder(int id)  {

        List<Order> todayorder = new ArrayList<>();
        String sql = "select * from orders where customer_id = ? ";
        try{
        PreparedStatement st1 = connection.prepareStatement(sql);
        st1.setInt(1, id);
        ResultSet rs = st1.executeQuery();
        while (rs.next()) {
            Order order = new Order();
            order.setId(rs.getInt("id"));
            order.setCustomer_id(rs.getInt("customer_id"));
            order.setOrderDate(rs.getDate("order_date"));
            order.setReceiveLocation(rs.getString("receive_location"));
            order.setTotalPrice(rs.getDouble("total_price"));
            todayorder.add(order);
        }
        }catch(SQLException e){
            System.out.println(e);
        }
        return todayorder;

    }
    public List<Order> getAllOrder() throws SQLException {

        List<Order> todayorder = new ArrayList<>();
        String sql = "select * from orders";
        PreparedStatement st1 = connection.prepareStatement(sql);
        ResultSet rs = st1.executeQuery();
        while (rs.next()) {
            Order order = new Order();
            order.setId(rs.getInt("id"));
            order.setCustomer_id(rs.getInt("customer_id"));
            order.setOrderDate(rs.getDate("order_date"));
            order.setReceiveLocation(rs.getString("receive_location"));
            order.setTotalPrice(rs.getDouble("total_price"));
            todayorder.add(order);
        }
        return todayorder;

    }
}
