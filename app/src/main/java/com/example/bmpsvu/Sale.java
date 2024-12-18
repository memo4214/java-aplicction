package com.example.bmpsvu;

public class Sale {
    public int id;
    public double north;

    public double south;
    public double west;
    public double east;
    public double lebanon;

    public String year;
    public String month;
    public int repId;

    public String repName;

    public Sale(int id, double north, double south, double west, double east, double lebanon, String year, String month, int repId) {
        this.id = id;
        this.north = north;
        this.south = south;
        this.west = west;
        this.east = east;
        this.lebanon = lebanon;
        this.year = year;
        this.month = month;
        this.repId = repId;
    }

    public Sale(int id, double north, double south, double west, double east, double lebanon, String year, String month, int repId, String repName) {
        this.id = id;
        this.north = north;
        this.south = south;
        this.west = west;
        this.east = east;
        this.lebanon = lebanon;
        this.year = year;
        this.month = month;
        this.repId = repId;
        this.repName = repName;
    }
}
