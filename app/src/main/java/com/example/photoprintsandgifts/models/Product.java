package com.example.photoprintsandgifts.models;

public abstract class Product {
    String productID, productName, price;


    //generating constructors
    public Product( String productID, String productName, String price) {
        this.productID = productID;
        this.productName = productName;
        this.price = price;

    }

    //generating getters and setters



    public String getProductID() {
        return productID;
    }

    public void setProductID(String productID) {
        this.productID = productID;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }


}

