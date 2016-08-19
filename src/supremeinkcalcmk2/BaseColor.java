/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package supremeinkcalcmk2;

import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author Arch
 */
public class BaseColor {
//    private String BaseColor;
//    private double Price;
//        
//    public BaseColor(){
//    
//}
//    public BaseColor(String BaseColor, double Price){
//        this.BaseColor = BaseColor;
//        this.Price = Price;
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
    
    public  SimpleStringProperty BaseColor = new SimpleStringProperty();
    public  SimpleStringProperty Price = new SimpleStringProperty();

    public String getBaseColor() {
        return BaseColor.get();
    }

    public void setBaseColor(String BaseColorStr) {
        BaseColor.set(BaseColorStr);
    }

    public String getPrice() {
        return Price.get();
    }

    public void setPrice(String PriceStr) {
        Price.set(PriceStr);
    }
    
    
}
