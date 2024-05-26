/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.brand;

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
public class BrandDAO extends DBContext implements DAO<Brand> {

    @Override
    public Optional<Brand> get(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public List<Brand> getAll() {
        List<Brand> list = new ArrayList<>();
        String sql = "select * from [brands]";
        try {
            PreparedStatement st = connection.prepareStatement(sql);

            ResultSet rs = st.executeQuery();
            while (rs.next()) {
                Brand brand = new Brand();
                brand.setId(rs.getInt("id"));
                brand.setName(rs.getString("name"));
                list.add(brand);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    @Override
    public void insert(Brand t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void update(Brand t, String[] params) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void delete(Brand t) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public boolean checkbrand(String brand1) {
        String check = "";
        String sql = "select*from brands where name =?";
        try {
            PreparedStatement st = connection.prepareStatement(sql);
            st.setString(1, brand1);
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
    
        public int findbrandname(String brand1) {
        int index=0;
        String sql = "select*from brands where name =?";
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
