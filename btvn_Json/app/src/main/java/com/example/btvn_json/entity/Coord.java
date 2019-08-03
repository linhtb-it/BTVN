package com.example.btvn_json.entity;

public class Coord {
    private int lon;
    private int lat;

    public Coord(int lon, int lat) {
        this.lon = lon;
        this.lat = lat;
    }

    public Coord() {
    }

    public int getLon() {
        return lon;
    }

    public void setLon(int lon) {
        this.lon = lon;
    }

    public int getLat() {
        return lat;
    }

    public void setLat(int lat) {
        this.lat = lat;
    }
}
