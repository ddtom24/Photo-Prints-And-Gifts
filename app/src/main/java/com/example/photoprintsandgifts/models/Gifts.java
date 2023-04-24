package com.example.photoprintsandgifts.models;

public class Gifts extends Product{


    public Gifts(String productID, String productName, String price) {
        super(productID, productName, price);
    }

    @Override
    public String getProductID() {
        return super.getProductID();
    }

    @Override
    public void setProductID(String productID) {
        super.setProductID(productID);
    }

    @Override
    public String getProductName() {
        return super.getProductName();
    }

    @Override
    public void setProductName(String productName) {
        super.setProductName(productName);
    }

    @Override
    public String getPrice() {
        return super.getPrice();
    }

    @Override
    public void setPrice(String price) {
        super.setPrice(price);
    }
}
