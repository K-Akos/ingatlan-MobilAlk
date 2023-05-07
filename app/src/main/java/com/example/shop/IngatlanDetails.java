package com.example.shop;

public class IngatlanDetails {
    private String location;
    private String terulet;
    private String price;
    private  int imageResource;

    public IngatlanDetails() {}

    public IngatlanDetails(String location, String terulet, String price, int imageResource) {
        this.location = location;
        this.terulet = terulet;
        this.price = price;
        this.imageResource = imageResource;
    }

    public String getLocation() {
        return location;
    }
    public String getTerulet() {
        return terulet;
    }
    public String getPrice() {
        return price;
    }
    public int getImageResource() {
        return imageResource;
    }
}
