/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.review;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import model.DAO;
import utils.DBContext;

import java.util.Optional;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author asus
 */
public class ReviewDAO extends DBContext implements DAO{

    @Override
    public Optional get(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List getAll() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void insert(Object t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void update(Object t, String[] params) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(Object t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
     public List<Review> getAllReviewofProduct(int id) {
      List<Review> list = new ArrayList<>();
      String sql = "select * from [reviews] where product_id= ?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, id);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Review product = new Review();
                product.setId(rs.getInt("id"));
                
                list.add(product);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;  
    }
        public void addReview(int accid,int proid,int rvscore,String detail) {
          LocalDate curDate = java.time.LocalDate.now();
        String date = curDate.toString();
        String sql = "insert into [reviews] values (?,?,?,?,?)";
        try{
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        
            preparedStatement.setInt(1, accid);
            preparedStatement.setInt(2, proid);
            preparedStatement.setInt(3, rvscore);           
            preparedStatement.setString(4, date);
            preparedStatement.setString(5, detail);
            preparedStatement.executeUpdate();
        
        }catch (Exception e){
            System.out.println(e);
        }
    }
}
