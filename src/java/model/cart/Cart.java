/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.cart;

import java.util.List;
import model.item.Item;
import java.util.*;

/**
 *
 * @author asus
 */
public class Cart {
    private List<Item> items =new ArrayList<>();

    public Cart() {
    }

    public Cart(List<Item> items) {
        this.items = items;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }
    public int getQuantityById(int id){
        return getItemById(id).getQuantity();
    }

    public Item getItemById(int id) {
        for(Item i : items){
            if(i.getProduct().getId()==id){
             return i;   
            }
                
        }
        return null;
    }
    public void addItem(Item t){
    if(getItemById(t.getProduct().getId())!=null){
       Item m = getItemById(t.getProduct().getId());
       m.setQuantity(m.getQuantity()+t.getQuantity());
    }
    else {
        items.add(t);
    }
    }
     
    public void removeItem(int id){
       if(getItemById(id)!= null){
           items.remove(getItemById(id));
       } 
    }
    public double getTotalMoney(){
        double t=0;
        for(Item i:items)
            t+=(i.getQuantity()*i.getProduct().getOutPrice());
        return t;
    }
}
