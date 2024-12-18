package com.example.bmpsvu;

public class Commission {
    public int id;
    public double north;

    public double south;
    public double west;
    public double east;
    public double lebanon;

    public String year;
    public String month;
    public int userId;

    public String user;

    public Commission(int id, double north, double south, double west, double east, double lebanon, String year, String month, int userId, String user) {
        this.id = id;
        this.north = north;
        this.south = south;
        this.west = west;
        this.east = east;
        this.lebanon = lebanon;
        this.year = year;
        this.month = month;
        this.userId = userId;
        this.user = user;
    }

    public Commission(int id, double north, double south, double west, double east, double lebanon, String year, String month, int userId) {
        this.id = id;
        this.north = north;
        this.south = south;
        this.west = west;
        this.east = east;
        this.lebanon = lebanon;
        this.year = year;
        this.month = month;
        this.userId = userId;
    }
}
