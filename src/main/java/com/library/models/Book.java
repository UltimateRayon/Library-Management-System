package com.library.models;

public class Book {
    private String id;
    private String title;
    private String author;
    private String genre;
    private boolean isAvailable;


    public Book(String id, String title, String author, String genre, boolean isAvailable) {
        this.id=id;
        this.title=title;
        this.author=author;
        this.genre=genre;
        this.isAvailable=isAvailable;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }
}