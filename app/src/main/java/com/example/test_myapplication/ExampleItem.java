package com.example.test_myapplication;

public class ExampleItem {
    private String mImageUrl;
    private String mUser;
    private int mID;

    public ExampleItem(String imageUrl, String name, int id) {
        mImageUrl = imageUrl;
        mUser = name;
        mID = id;
    }

    public String getImage() {
        return mImageUrl;
    }

    public String getUser() {
        return mUser;
    }

    public int getID() {
        return mID;
    }
}
