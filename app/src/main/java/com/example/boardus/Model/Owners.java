package com.example.boardus.Model;

public class Owners {
    private  String name, phoneno,maplink,nofbeds,price,description,password;

    public Owners(){

    }

    public Owners(String name, String phoneno, String maplink, String nofbeds, String price, String description, String password) {
        this.name = name;
        this.phoneno = phoneno;
        this.maplink = maplink;
        this.nofbeds = nofbeds;
        this.price = price;
        this.description = description;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneno() {
        return phoneno;
    }

    public void setPhoneno(String phoneno) {
        this.phoneno = phoneno;
    }

    public String getMaplink() {
        return maplink;
    }

    public void setMaplink(String maplink) {
        this.maplink = maplink;
    }

    public String getNofbeds() {
        return nofbeds;
    }

    public void setNofbeds(String nofbeds) {
        this.nofbeds = nofbeds;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

