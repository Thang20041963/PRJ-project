/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.account;

import java.util.Date;
import java.util.List;
import model.order.Order;
import model.order.OrderDAO;
import model.review.Review;

/**
 *
 * @author asus
 */
public class Account {
 private int id;
 private String fullname;
 private String username;
 private String password;
 private String email;
 private String phonenumber;
 private String gender;
 private Date birthday;

 private double balance;
 private int role;
 private List<Review> reviews;

    public Account() {
    }

    public Account(int id, String fullname, String username, String password, String email, String phonenumber, String gender, Date birthday, double balance,int role, List<Review> reviews) {
        this.id = id;
        this.fullname = fullname;
        this.username = username;
        this.password = password;
        this.email = email;
        this.phonenumber = phonenumber;
        this.gender = gender;
        this.birthday = birthday;

        this.balance= balance;
        this.role = role;
        this.reviews = reviews;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }



    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
    
    public List<Order> getAccountOrder(){
        OrderDAO ord = new OrderDAO();
        List<Order> order = ord.CustomerOrder(id);
        return order;
    }
}
