package com.example.shop.Model;

public class Item {
    private String itemName,itemCountry,itemPrice,itemCount,itemDesc , category;
    private int itemImg;

    public Item() {
    }

    public Item(String category, String itemName, String itemCountry, String itemPrice, String itemCount, String itemDesc, int itemImg) {
        this.category = category;
        this.itemName = itemName;
        this.itemCountry = itemCountry;
        this.itemPrice = itemPrice;
        this.itemCount = itemCount;
        this.itemDesc = itemDesc;
        this.itemImg = itemImg;
    }

    public Item(int itemImg) {
        this.itemImg = itemImg;
    }

    public Item(String itemName, String itemPrice, String itemCount, int itemImg) {
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.itemCount = itemCount;
        this.itemImg = itemImg;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemCountry() {
        return itemCountry;
    }

    public void setItemCountry(String itemCountry) {
        this.itemCountry = itemCountry;
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

    public String getItemDesc() {
        return itemDesc;
    }

    public void setItemDesc(String itemDesc) {
        this.itemDesc = itemDesc;
    }

    public int getItemImg() {
        return itemImg;
    }

    public void setItemImg(int itemImg) {
        this.itemImg = itemImg;
    }
}
