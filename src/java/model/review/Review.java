/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.review;

import java.util.Date;

/**
 *
 * @author asus
 */
public class Review {
    private int id ;
    private int customer_id;
    private int product_id;
    private int reviewPoint;
    private Date reviewDate;
    private String detail;

    public Review() {
    }

    public Review(int id, int customer_id, int product_id, int reviewPoint, Date reviewDate, String detail) {
        this.id = id;
        this.customer_id = customer_id;
        this.product_id = product_id;
        this.reviewPoint = reviewPoint;
        this.reviewDate = reviewDate;
        this.detail = detail;
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

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public int getReviewPoint() {
        return reviewPoint;
    }

    public void setReviewPoint(int reviewPoint) {
        this.reviewPoint = reviewPoint;
    }

    public Date getReviewDate() {
        return reviewDate;
    }

    public void setReviewDate(Date reviewDate) {
        this.reviewDate = reviewDate;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }
    
}
