/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.account;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import model.DAO;
import utils.DBContext;

/**
 *
 * @author asus
 */
public class MoneyRequestDAO extends DBContext implements DAO<MoneyRequest> {

    @Override
    public Optional<MoneyRequest> get(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<MoneyRequest> getAll() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void insert(MoneyRequest t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void update(MoneyRequest t, String[] params) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(MoneyRequest t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public void insertRequest(int cid,double money){
        LocalDate curDate = java.time.LocalDate.now();
        String date = curDate.toString();
        String sql ="insert into money_request values(?,?,?,?,?,?)";
        try{
          PreparedStatement st = connection.prepareStatement(sql);
            st.setInt(1, cid);
            st.setString(2, "processing");
            st.setString(3, date);
            st.setString(4, null);
            st.setDouble(5, money);
            st.setString(6, null);
            st.executeUpdate();  
        }catch(SQLException e){
            System.out.println(e);
        }
    }
    
    public List<MoneyRequest> getRequest(){
        List<MoneyRequest> mrlist = new ArrayList<>();
        String sql = "select * from money_request where status = 'processing'";
        try{
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()){
                MoneyRequest mr= new MoneyRequest();
                mr.setId(resultSet.getInt("id"));
                mr.setCustomerId(resultSet.getInt("customer_id"));
                mr.setStatus(resultSet.getString("status"));
                mr.setRequestDate(resultSet.getDate("request_date"));
                mr.setResponseDate(resultSet.getDate("response_date"));
                mr.setMoney(resultSet.getDouble("money"));
                mr.setNote(resultSet.getString("note"));
                mrlist.add(mr);
            }
        }catch (Exception e){
            System.out.println(e);
        }
        return mrlist;
    }
    
     public void updateRequest(int id,String status,String note){
        LocalDate curDate = java.time.LocalDate.now();
        String date = curDate.toString();
        String sql ="update money_request set status= ?, response_date=? ,note =? where id =? ";
        try{
          PreparedStatement st = connection.prepareStatement(sql);        
            st.setString(1, status);
            st.setString(2, date);
            st.setString(3, note);
            st.setInt(4, id);        
            st.executeUpdate();  
        }catch(SQLException e){
            System.out.println(e);
        }
    }
         public List<MoneyRequest> getAllRequest(){
        List<MoneyRequest> mrlist = new ArrayList<>();
        String sql = "select * from money_request";
        try{
            PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()){
                MoneyRequest mr= new MoneyRequest();
                mr.setId(resultSet.getInt("id"));
                mr.setCustomerId(resultSet.getInt("customer_id"));
                mr.setStatus(resultSet.getString("status"));
                mr.setRequestDate(resultSet.getDate("request_date"));
                mr.setResponseDate(resultSet.getDate("response_date"));
                mr.setMoney(resultSet.getDouble("money"));
                mr.setNote(resultSet.getString("note"));
                mrlist.add(mr);
            }
        }catch (Exception e){
            System.out.println(e);
        }
        return mrlist;
    }
         
          public List<MoneyRequest> getCustomerRequest(int id){
        List<MoneyRequest> mrlist = new ArrayList<>();
        String sql = "select * from money_request where customer_id =?";
        try{
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()){
                MoneyRequest mr= new MoneyRequest();
                mr.setId(resultSet.getInt("id"));
                mr.setCustomerId(resultSet.getInt("customer_id"));
                mr.setStatus(resultSet.getString("status"));
                mr.setRequestDate(resultSet.getDate("request_date"));
                mr.setResponseDate(resultSet.getDate("response_date"));
                mr.setMoney(resultSet.getDouble("money"));
                mr.setNote(resultSet.getString("note"));
                mrlist.add(mr);
            }
        }catch (Exception e){
            System.out.println(e);
        }
        return mrlist;
    }   
}
