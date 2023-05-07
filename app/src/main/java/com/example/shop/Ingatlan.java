package com.example.shop;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "ingatlan_table")
public class Ingatlan {
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "location")
    private String location;
    @ColumnInfo(name = "terulet")
    private String terulet;
    @ColumnInfo(name = "price")
    private String price;
    @ColumnInfo(name = "img")
    private  int imageResource;

    public Ingatlan() {}

    public Ingatlan(@NonNull String location, String terulet, String price, int imageResource) {
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

    public void setLocation(@NonNull String location) {
        this.location = location;
    }

    public void setTerulet(String terulet) {
        this.terulet = terulet;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public void setImageResource(int imageResource) {
        this.imageResource = imageResource;
    }
}
