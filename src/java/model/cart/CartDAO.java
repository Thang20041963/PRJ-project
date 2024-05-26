/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.cart;
import jakarta.servlet.http.HttpSession;
import model.DAO;
import model.account.Account;
import model.account.AccountDAO;
import model.item.Item;
import model.product.Product;
import model.product.ProductDAO;
import utils.DBContext;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author asus
 */
public class CartDAO extends DBContext implements DAO<Cart>{

    @Override
    public Optional<Cart> get(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<Cart> getAll() {
return null;    }

    @Override
    public void insert(Cart t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void update(Cart t, String[] params) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(Cart t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    public Cart getCart(int customerId) {
        String sql = "select * from [items] where customer_id = " + customerId;
        Cart cart = new Cart();
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                Item item = new Item();
                ProductDAO productDAO = new ProductDAO();
                item.setQuantity(resultSet.getInt("quantity"));
                item.setPrice(resultSet.getInt("price"));
                Product product = productDAO.getProduct(resultSet.getInt("product_id"));
                item.setProduct(product);
                cart.addItem(item);
            }
        }catch (Exception e){
            System.out.println(e);
        }
        return cart;
    }



    public void addCart(Cart cart, Account account) {
        String sql = "insert into [items] values (?,?,?,?)";
        try{
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        List<Item> itemList = cart.getItems();
        for (Item item : itemList){
            preparedStatement.setInt(1, account.getId());
            preparedStatement.setInt(2, item.getProduct().getId());
            preparedStatement.setInt(3, item.getQuantity());
            preparedStatement.setDouble(4, item.getPrice());
            preparedStatement.executeUpdate();
        }
        }catch (Exception e){
            System.out.println(e);
        }
    }


    public void removeCart(int customerId) {
        String sql = "delete from [items] where customer_id = " + customerId;
        try{
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.executeUpdate();
        }catch (Exception e){
            System.out.println(e);
        }
    }
}
