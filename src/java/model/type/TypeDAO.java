/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.type;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import model.DAO;
import model.brand.Brand;
import utils.DBContext;

/**
 *
 * @author asus
 */
public class TypeDAO extends DBContext implements DAO<Type> {


    @Override
    public List<Type> getAll() {

        List<Type> list = new ArrayList<>();
        String sql = "select * from [types]";
        try {
            PreparedStatement st = connection.prepareStatement(sql);

            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Type type = new Type();
                type.setId(rs.getInt("id"));
                type.setName(rs.getString("name"));
                list.add(type);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;

    }

    @Override
    public void insert(Type t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void update(Type t, String[] params) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(Type t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Optional<Type> get(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
        public boolean checktype(String type1) {
        String check = "";
        String sql = "select*from types where name =?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, type1);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {

                check = rs.getString("name");

            }
            if (!check.isBlank()) {

                return true;
            }

        } catch (SQLException e) {
            System.out.println(e);
        }
        return false;
    }
        
        
         public int findtypename(String brand1) {
        int index=0;
        String sql = "select*from types where name =?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, brand1);
            ResultSet rs = st.executeQuery();
            while (rs.next()) {

               index = rs.getInt("id");

            }
           
        } catch (SQLException e) {
            System.out.println(e);
        }
        return index;
    }
}
