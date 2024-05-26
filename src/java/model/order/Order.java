/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.order;

import java.util.Date;
import model.account.Account;
import model.account.AccountDAO;

/**
 *
 * @author asus
 */
public class Order {
    private int id;
    private int customer_id;
    private Date orderDate;
    private String receiveLocation;
    private double totalPrice;

    public Order() {
    }

    public Order(int id, int customer_id, Date orderDate, String receiveLocation, double totalPrice) {
        this.id = id;
        this.customer_id = customer_id;
        this.orderDate = orderDate;
        this.receiveLocation = receiveLocation;
        this.totalPrice = totalPrice;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
    }

    public Date getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Date orderDate) {
        this.orderDate = orderDate;
    }

    public String getReceiveLocation() {
        return receiveLocation;
    }

    public void setReceiveLocation(String receiveLocation) {
        this.receiveLocation = receiveLocation;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Account getAccInfor1(int id){
        AccountDAO adao = new AccountDAO();
        Account acc = adao.getCustomerById(id);
                return acc;
      
    }
}
