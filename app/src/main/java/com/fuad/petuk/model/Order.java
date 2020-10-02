package com.fuad.petuk.model;

public class Order {
    private String ProductID;
    private String Quantity;
    private String  ProductName;
    private String Price;
    private String Discount;
    public Order(){

    }

    public Order(String productId, String quantity, String productName, String price, String discount) {
        ProductID = productId;
        Quantity = quantity;
        ProductName = productName;
        Price = price;
        Discount = discount;
    }

    public String getProductId() {
        return ProductID;
    }

    public void setProductId(String productId) {
        ProductID = productId;
    }

    public String getQuantity() {
        return Quantity;
    }

    public void setQuantity(String quantity) {
        Quantity = quantity;
    }

    public String getProductName() {
        return ProductName;
    }

    public void setProductName(String productName) {
        ProductName = productName;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        Price = price;
    }

    public String getDiscount() {
        return Discount;
    }

    public void setDiscount(String discount) {
        Discount = discount;
    }
}
