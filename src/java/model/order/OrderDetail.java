/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.order;

import model.product.Product;
import model.product.ProductDAO;

/**
 *
 * @author asus
 */
public class OrderDetail {
    private int productId;
    private int orderId;
    private int amount;
    private double price;

    public OrderDetail() {
    }

    public OrderDetail(int productId, int orderId, int amount, double price) {
        this.productId = productId;
        this.orderId = orderId;
        this.amount = amount;
        this.price = price;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
    public Product getProductById(int id){
        Product pro = new Product();
        ProductDAO pdao = new ProductDAO();
        pro = pdao.getProduct(id);
        return pro;
        
    }
}
