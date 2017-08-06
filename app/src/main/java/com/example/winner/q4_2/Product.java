package com.example.winner.q4_2;

/**
 * Created by Winner on 5/5/2016.
 */
public class Product {
    private String id;
    private String name;
    private String description;
    private byte[] imageFile;

    Product(String id,String name,String description){
        this.id=id;
        this.description=description;
        this.name=name;
    }
    Product(){}

    public void setId(String id){this.id=id;}
    public void setName(String name){this.name=name;}
    public void setDescription(String description){this.description=description;}
    public void setImageFile(byte[] imageFile){this.imageFile=imageFile;}

    public String getId(){return id;}
    public String getName(){return name;}
    public String getDescription(){return description;}
    public byte[] getImageFile(){return imageFile;}
}
