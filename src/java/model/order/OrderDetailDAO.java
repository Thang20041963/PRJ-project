/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.order;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import model.DAO;
import utils.DBContext;

/**
 *
 * @author asus
 */
public class OrderDetailDAO extends DBContext implements DAO<OrderDetail>{



    @Override
    public List<OrderDetail> getAll() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void insert(OrderDetail t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void update(OrderDetail t, String[] params) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(OrderDetail t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Optional<OrderDetail> get(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    public List<OrderDetail> getDetail(int id)  {

        List<OrderDetail> todayorder = new ArrayList<>();
        String sql = "select * from order_details where order_id= ?";
        try{
        PreparedStatement st1 = connection.prepareStatement(sql);
        st1.setInt(1, id);
        ResultSet rs = st1.executeQuery();
        while (rs.next()) {
            OrderDetail ord = new OrderDetail();
            ord.setOrderId(rs.getInt("order_id"));
            ord.setProductId(rs.getInt("product_id"));
            ord.setAmount(rs.getInt("amount"));
            
            ord.setPrice(rs.getDouble("price"));
            todayorder.add(ord);
        }
        }catch(SQLException e){
            System.out.println(e);
        }
        return todayorder;

    }
}
