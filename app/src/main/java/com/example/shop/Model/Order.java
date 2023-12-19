package com.example.shop.Model;

public class Order {
    int countItems,totalPrice;
    String userID;

    public Order() {
    }

    public Order(String userID,int countItems, int totalPrice) {
        this.userID = userID;
        this.countItems = countItems;
        this.totalPrice = totalPrice;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public int getCountItems() {
        return countItems;
    }

    public void setCountItems(int countItems) {
        this.countItems = countItems;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }
}
