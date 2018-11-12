package com.community.jboss.visitingcard.VisitingCard;

public class ListDetail {
    private String name = "Name";
    private String description = "this is the description";

    // Getters
    public String getName(){
        return this.name;
    }
    public String getDescription(){
        return this.description;
    }

    //Setters
    public void setName(String str) {
        this.name = str;
    }
    public void setDescription(String str) {
        this.description = str;
    }
}
