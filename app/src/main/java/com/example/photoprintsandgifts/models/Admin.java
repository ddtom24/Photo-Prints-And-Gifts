package com.example.photoprintsandgifts.models;

import android.os.Parcel;

public class Admin extends UserAccount {
    String city, position, address;

    public Admin(String id, Boolean isAdmin, String email, String name, String surname, String address) {
        super(id, isAdmin, email, name, surname, address);
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
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


    @Override
    public String getId() {
        return super.getId();
    }

    @Override
    public void setId(String password) {
        super.setId(id);
    }


    @Override
    public String getAddress() {
        return super.getAddress();
    }

    @Override
    public void setAddress(String address) {
        super.setAddress(address);
    }

    public Admin() {
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
