package com.example.photoprintsandgifts.models;

import android.os.Parcel;

public class Customer extends UserAccount {

    String orderID;

    public Customer(String id, Boolean isAdmin, String email, String name, String surname, String address, String orderID) {
        super(id, isAdmin, email, name, surname, address);

    }

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    @Override
    public String getId() {
        return super.getId();
    }

    @Override
    public void setId(String id) {
        super.setId(id);
    }

    @Override
    public String getEmail() {
        return super.getEmail();
    }

    @Override
    public void setEmail(String email) {
        super.setEmail(email);
    }

    @Override
    public String getName() {
        return super.getName();
    }

    @Override
    public void setName(String name) {
        super.setName(name);
    }

    @Override
    public String getSurname() {
        return super.getSurname();
    }

    @Override
    public void setSurname(String surname) {
        super.setSurname(surname);
    }

    public Customer(String id, Boolean isAdmin, String email, String name, String surname, String address) {
        super(id, isAdmin, email, name, surname, address);
    }


    @Override
    public String getAddress() {
        return super.getAddress();
    }

    @Override
    public void setAddress(String address) {
        super.setAddress(address);
    }

    public Customer() {
        super();
    }

    @Override
    public Boolean getAdmin() {
        return super.getAdmin();
    }

    @Override
    public void setAdmin(Boolean admin) {
        super.setAdmin(admin);
    }
}