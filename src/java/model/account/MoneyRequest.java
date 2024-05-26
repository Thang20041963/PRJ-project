/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.account;

import java.util.Date;

/**
 *
 * @author asus
 */
public class MoneyRequest {
    private int id;
    private int customerId;
    private String status;
    private Date requestDate;
    private Date responseDate;
    private Double money;
    private String note;
    public MoneyRequest() {
    }

    public MoneyRequest(int id, int customerId, String status, Date requestDate, Date responseDate, Double money, String note) {
        this.id = id;
        this.customerId = customerId;
        this.status = status;
        this.requestDate = requestDate;
        this.responseDate = responseDate;
        this.money = money;
        this.note = note;
    }

    

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getRequestDate() {
        return requestDate;
    }

    public void setRequestDate(Date requestDate) {
        this.requestDate = requestDate;
    }

    public Date getResponseDate() {
        return responseDate;
    }

    public void setResponseDate(Date responseDate) {
        this.responseDate = responseDate;
    }

    public Double getMoney() {
        return money;
    }

    public void setMoney(Double money) {
        this.money = money;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
    
     public Account getAccInfor(int id){
        AccountDAO adao = new AccountDAO();
        Account acc = adao.getCustomerById(id);
                return acc;
      
    }
    
}
