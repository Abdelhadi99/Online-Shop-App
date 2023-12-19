package com.example.shop.Model;

import android.net.Uri;

public class Profile {
 private String userID,name,email,phoneNum,address;
 private String strImgUri;

    public Profile() {
    }

    public Profile(String userID, String name, String email, String phoneNum, String address) {
        this.userID = userID;
        this.name = name;
        this.email = email;
        this.phoneNum = phoneNum;
        this.address = address;
    }

    public Profile(String userID, String name, String email, String phoneNum, String address, String strImgUri) {
        this.userID = userID;
        this.name = name;
        this.email = email;
        this.phoneNum = phoneNum;
        this.address = address;
        this.strImgUri = strImgUri;
    }


    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getStrImgUri() {
        return strImgUri;
    }

    public void setStrImgUri(String strImgUri) {
        this.strImgUri = strImgUri;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
