package com.example.moviesqrcodeapi;

public class Movie {
    private int id;
    private int time;
    private int year;
    private int rating;
    private String category;
    private String director;

    public Movie(int id, int time, int year, int rating, String category, String director) {
        this.id = id;
        this.time = time;
        this.year = year;
        this.rating = rating;
        this.category = category;
        this.director = director;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }
}
