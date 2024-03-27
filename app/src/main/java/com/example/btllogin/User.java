package com.example.btllogin;

import android.net.Uri;

public class User {
//    private int resouceImage;
    private Uri resouceImage;
    private String name;
    private String price;


    public User(Uri resouceImage, String name) {
        this.resouceImage = resouceImage;
        this.name = name;
    }

    public User(Uri resouceImage, String name, String price) {
        this.resouceImage = resouceImage;
        this.name = name;
        this.price = price;
    }

    public Uri getResouceImage() {
        return resouceImage;
    }

    public void setResouceImage(Uri resouceImage) {
        this.resouceImage = resouceImage;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
