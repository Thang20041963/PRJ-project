/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.account;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.DAO;
import model.review.Review;
import utils.DBContext;

/**
 *
 * @author asus
 */
public class AccountDAO extends DBContext implements DAO<Account>  {

    @Override
    public Optional<Account> get(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<Account> getAll() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void insert(Account t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void update(Account t, String[] params) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(Account t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
        public Account getAccount(String username, String pass) throws ClassNotFoundException {
        String sql = "select * from accounts where username=? and password=?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, username);
            st.setString(2, pass);
            ResultSet resultSet = st.executeQuery();
            while (resultSet.next()) {
                Account account = new Account();
                account.setId(resultSet.getInt("id"));
                account.setUsername(resultSet.getString("username"));
                account.setPassword(resultSet.getString("password"));
                account.setFullname(resultSet.getString("fullname"));
                account.setPhonenumber(resultSet.getString("phone_number"));
                account.setEmail(resultSet.getString("email"));
                account.setGender(resultSet.getString("gender"));
                account.setBirthday(resultSet.getDate("birthday"));
                account.setBalance(resultSet.getDouble("balance"));
                account.setRole(resultSet.getShort("role"));
                account.setReviews(getReviewsListForCustomer(account.getId()));  
                return account;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return null;
    }

    public Account getCustomerById(int id){
        String sql = "select * from accounts where id = ?";
        
        try{
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()){
                Account account = new Account();
                account.setId(resultSet.getInt("id"));
                account.setUsername(resultSet.getString("username"));
                account.setPassword(resultSet.getString("password"));
                account.setFullname(resultSet.getString("fullname"));
                account.setPhonenumber(resultSet.getString("phone_number"));
                account.setEmail(resultSet.getString("email"));
                account.setGender(resultSet.getString("gender"));
                account.setBirthday(resultSet.getDate("birthday"));
                account.setRole(resultSet.getShort("role"));
                account.setBalance(resultSet.getDouble("balance"));
                account.setReviews(getReviewsListForCustomer(account.getId()));  
                return account;
            }
        }catch (Exception e){
            System.out.println(e);
        }
        return null;
    }
    
    public List<Account> getAllCustomerBy(){
        String sql = "select * from accounts ";
        List<Account> acc =new ArrayList<>();
        try{
            PreparedStatement statement = connection.prepareStatement(sql);
            
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()){
                Account account = new Account();
                account.setId(resultSet.getInt("id"));
                account.setUsername(resultSet.getString("username"));
                account.setPassword(resultSet.getString("password"));
                account.setFullname(resultSet.getString("fullname"));
                account.setPhonenumber(resultSet.getString("phone_number"));
                account.setEmail(resultSet.getString("email"));
                account.setGender(resultSet.getString("gender"));
                account.setBirthday(resultSet.getDate("birthday"));
                account.setRole(resultSet.getShort("role"));
                account.setBalance(resultSet.getDouble("balance"));
                account.setReviews(getReviewsListForCustomer(account.getId()));  
                acc.add(account);
            }
        }catch (Exception e){
            System.out.println(e);
        }
        return acc;
    }
        private List<Review> getReviewsListForCustomer(int customerId) throws SQLException {
        List<Review> reviews = new ArrayList<>();
        String sql = "SELECT * FROM reviews WHERE customer_id = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, customerId);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Review review = new Review();
                    review.setId(resultSet.getInt("id"));
                    review.setCustomer_id(resultSet.getInt("customer_id"));
                    review.setProduct_id(resultSet.getInt("product_id"));
                    review.setReviewPoint(resultSet.getInt("review"));
                    review.setReviewDate(resultSet.getDate("reviewDate"));
                    review.setDetail(resultSet.getString("detail"));
                    reviews.add(review);
                }
            }
        }

        return reviews;
    }
        public boolean updateBalance(int id,double balance) {
        String sql = "UPDATE accounts set balance = ? " +
                "where id = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setDouble(1, balance);
            statement.setInt(2, id);
            
            statement.executeUpdate();
            return true;
        } catch (Exception e) {
            System.out.println(e);
        }
        return false;
    } 
      
        public boolean updateCustomer(int id,String username ,String newName, String newPhone, String newEmail, String newGender, String newBirthday) {
        String sql = "UPDATE accounts set username= ?,fullname = ?, phone_number = ?, email = ?, gender = ?, birthday = ? " +
                "where id = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, username);
            statement.setString(2, newName);
            statement.setString(3, newPhone);
            statement.setString(4, newEmail);
            statement.setString(5, newGender);
            statement.setString(6, newBirthday);
            statement.setInt(7, id);
            statement.executeUpdate();
            return true;
        } catch (Exception e) {
            System.out.println(e);
        }
        return false;
    }
        
        public boolean updateCustomerPass(int id,String pass) {
        String sql = "UPDATE accounts set password = ? " +
                "where id = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            
            statement.setString(1, pass);
            statement.setInt(2, id);
            statement.executeUpdate();
            return true;
        } catch (Exception e) {
            System.out.println(e);
        }
        return false;
    }
        
        public void addAccount(String u, String p) {
        try {
            String sql = "insert into accounts values(null,?,?,null,null,null,null,null,1)";
            PreparedStatement st;
            st = connection.prepareStatement(sql);
            st.setString(1, u);
            st.setString(2, p);
            st.executeUpdate();
        } catch (SQLException ex) {
        }
    }
        public boolean checkUsername(String username) {
        String sql = "select * from  accounts  " +
                "where username = ? ";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, username);
            ResultSet resultSet = statement.executeQuery();
            if(resultSet.next()){
                return true;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return false;
    }
        
         public int CountCustomer(){
         int total= 0;
         String sql = "select count(*) as total from accounts";
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
         public void setAdmin(int id, int role){
             String sql = "update accounts set role = ? where id = ?";
            try{
            PreparedStatement st;
            st = connection.prepareStatement(sql);
            st.setInt(1, role);
            st.setInt(2,id);
            st.executeUpdate();
         }catch(SQLException e){
                System.out.println(e);
         }
        }
}
