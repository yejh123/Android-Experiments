package com.example.litepalproj.entity;

import org.litepal.annotation.Column;
import org.litepal.crud.LitePalSupport;

public class Book extends LitePalSupport {
    private int id;
    @Column(unique = true, defaultValue = "unknown")
    private String name;
    private String author;
    private String publisher;
    private String desc;
    private String imageURL;

    public Book() {
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", author='" + author + '\'' +
                ", publisher='" + publisher + '\'' +
                ", desc='" + desc + '\'' +
                ", imageURL='" + imageURL + '\'' +
                '}';
    }
}
