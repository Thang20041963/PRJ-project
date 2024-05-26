/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.product;

import java.util.ArrayList;
import java.util.List;
import model.account.Account;
import model.account.AccountDAO;
import model.brand.Brand;
import model.review.Review;
import model.type.Type;

/**
 *
 * @author asus
 */
public class Product {
    private int id;
    private String title;
    private double inPrice;
    private double outPrice;
    private int stock;
    private Type type;
    private Brand brand;
    private int nokc;
    private String material;
    private String suitability;
    private float desity;
    private float discount;
    private List<String> img;
    private List<Review> reviews;
    public Product() {
    }



    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    public Product(int id, String title, double inPrice, double outPrice, int stock, Type type, Brand brand, int nokc, String material, String suitability, float desity, float discount, List<String> img, List<Review> reviews) {
        this.id = id;
        this.title = title;
        this.inPrice = inPrice;
        this.outPrice = outPrice;
        this.stock = stock;
        this.type = type;
        this.brand = brand;
        this.nokc = nokc;
        this.material = material;
        this.suitability = suitability;
        this.desity = desity;
        this.discount = discount;
        this.img = img;
        this.reviews = reviews;
    }

    public List<String> getImg() {
        return img;
    }

    public void setImg(List<String> img) {
        this.img = img;
    }




    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getInPrice() {
        return inPrice;
    }

    public void setInPrice(double inPrice) {
        this.inPrice = inPrice;
    }

    public double getOutPrice() {
        return outPrice;
    }

    public void setOutPrice(double outPrice) {
        this.outPrice = outPrice;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public int getNokc() {
        return nokc;
    }

    public void setNokc(int nokc) {
        this.nokc = nokc;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public String getSuitability() {
        return suitability;
    }

    public void setSuitability(String suitability) {
        this.suitability = suitability;
    }

    public float getDesity() {
        return desity;
    }

    public void setDesity(float desity) {
        this.desity = desity;
    }

    public float getDiscount() {
        return discount;
    }

    public void setDiscount(float discount) {
        this.discount = discount;
    }
    public int getProductReviewScore(){
        List<Review> rv = getReviews();
        int score = 0;
       int count = 0;
        for(int i=0;i<rv.size();i++){
            score += rv.get(i).getReviewPoint();
            count++;
        }
        if(count>0){
            score = score/count;
        }
        
        return score;
    }
    
     public int getProductReviewScoreDetail1(int number){
        List<Review> rv = getReviews();      
       int count = 0;
        for(int i=0;i<rv.size();i++){
            if(rv.get(i).getReviewPoint()==number){
            count++;    
            } 
        }      
        return count;
    }
         public int getProductReviewScoreDetail2(int number){
        List<Review> rv = getReviews();      
       double count = 0;
       double temp =0;
       int total =0;
        for(int i=0;i<rv.size();i++){
            if(rv.get(i).getReviewPoint()==number){
            count++;    
            } 
        }
        temp=(count/rv.size())*100;
        total = (int) temp;
        return total;
    }
         public String getReviewerName(int customer_id){
             AccountDAO acc = new AccountDAO();
            String name = acc.getCustomerById(customer_id).getFullname();
            
            return name;
         }
}
