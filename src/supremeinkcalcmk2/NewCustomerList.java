/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package supremeinkcalcmk2;

import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author Archa
 */
public class NewCustomerList {

    private SimpleStringProperty BaseColor = new SimpleStringProperty();
    private SimpleStringProperty Price = new SimpleStringProperty();

    public NewCustomerList(String baseColor, String price) {
        this.BaseColor = new SimpleStringProperty(baseColor);
        this.Price = new SimpleStringProperty(price);
    }

    public String getBaseColor() {
        return BaseColor.get();
    }

    public void setBaseColor(String baseColor) {
        BaseColor.set(baseColor);
    }

    public String getPrice() {
        return Price.get();
    }

    public void setPrice(String price) {
        Price.set(price);
    }
    
//    private String BaseColor;
//    private double Price;
//    
//    public NewCustomerList(String BaseColor, double Price){
//        this.BaseColor = "";
//        this.Price = 0;
//    }
//
//    public String getBaseColor() {
//        return BaseColor;
//    }
//
//    public void setBaseColor(String BaseColor) {
//        this.BaseColor = BaseColor;
//    }
//
//    public double getPrice() {
//        return Price;
//    }
//
//    public void setPrice(double Price) {
//        this.Price = Price;
//    }
    
}
