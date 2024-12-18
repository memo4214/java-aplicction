package com.example.bmpsvu;

public class Mndob {
    public int id;
    public String phone;

    public String name;
    public byte[] photo;
    public String region;
    public String registerDate;

    public Mndob() {
    }

    public Mndob(int id, String phone, String name, byte[] photo, String region, String registerDate) {
        this.id = id;
        this.phone = phone;
        this.name = name;
        this.photo = photo;
        this.region = region;
        this.registerDate = registerDate;
    }
}
