package com.fuad.petuk.model;

import com.google.android.gms.dynamic.LifecycleDelegate;

import java.util.List;

public class Request {
    private String address;
    private String phone;
    private String name;
    private String status;



    private String total;

    private List<Order> foods;//List Of food Order
    public Request(){

    }

    public Request(String name, String address,String phone, String total, List<Order> foods) {
        this.name=name;

        this.address = address;
        this.phone=phone;
        this.total = total;
        this.status="0";

        this.foods = foods;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }



    public List<Order> getFoods() {
        return foods;
    }

    public void setFoods(List<Order> foods) {
        this.foods = foods;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
