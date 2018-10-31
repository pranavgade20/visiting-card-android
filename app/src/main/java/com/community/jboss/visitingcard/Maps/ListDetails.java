package com.community.jboss.visitingcard.Maps;

import com.community.jboss.visitingcard.R;

public class ListDetails {
    private String name = "Name";
    private String email = "email@domain.com";
    private int imageId = R.drawable.sample_1;

    // Getters
    public String getName(){
        return this.name;
    }
    public String getEmail(){
        return this.email;
    }
    public int getImageId(){
        return this.imageId;
    }

    //Setters
    public void setName(String str) {
        this.name = str;
    }
    public void setEmail(String str) {
        this.email = str;
    }
    public void setImageId(int id) {
        this.imageId = id;
    }
}
