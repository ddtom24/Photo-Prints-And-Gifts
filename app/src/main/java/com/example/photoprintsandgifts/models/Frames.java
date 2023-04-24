package com.example.photoprintsandgifts.models;

public class Frames extends Product{

    String colour, theme, imagePath;

  //generating constructors
    public Frames(String productID, String productName, String price, String colour, String theme, String imagePath) {
        super(productID, productName, price);

        this.colour = colour;
        this.theme = theme;
        this.imagePath = imagePath;
    }
    //generating getters and setters


    public String getColour() {
        return colour;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }
//generating override (inherited) methods
    public Frames(String productID, String productName, String price) {
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

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }
}
