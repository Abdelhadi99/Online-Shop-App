package com.example.shop.Model;

public class Cart {
    String userID,itemName,itemPrice,itemCount,strImage;

    public Cart() {
    }

    public Cart(String userID,String itemName, String itemPrice, String itemCount, String strImage) {
        this.userID = userID;
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.itemCount = itemCount;
        this.strImage = strImage;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(String itemPrice) {
        this.itemPrice = itemPrice;
    }

    public String getItemCount() {
        return itemCount;
    }

    public void setItemCount(String itemCount) {
        this.itemCount = itemCount;
    }

    public String getStrImage() {
        return strImage;
    }

    public void setStrImage(String strImage) {
        this.strImage = strImage;
    }
}
