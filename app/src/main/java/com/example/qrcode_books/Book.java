package com.example.qrcode_books;

public class Book {
    private int id;
    private String tema;
    private int lapok;
    private String szerzo;

    public Book(int id, String tema, int lapok, String szerzo) {
        this.id = id;
        this.tema = tema;
        this.lapok = lapok;
        this.szerzo = szerzo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTema() {
        return tema;
    }

    public void setTema(String tema) {
        this.tema = tema;
    }

    public int getLapok() {
        return lapok;
    }

    public void setLapok(int lapok) {
        this.lapok = lapok;
    }

    public String getSzerzo() {
        return szerzo;
    }

    public void setSzerzo(String szerzo) {
        this.szerzo = szerzo;
    }
}
