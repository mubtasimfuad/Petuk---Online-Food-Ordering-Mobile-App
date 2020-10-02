package com.fuad.petuk.model;

public class User {
    public String Name,email,phone;
    public User(){

    }

    public User(String name, String email, String phone) {
        Name = name;
        this.email = email;
        this.phone = phone;
    }



    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }


}
