package com.example.exp2.entity;

public class Book {
    private int id;
    private String name;
    private String author;
    private String publisher;
    private String desc;
    private String imageURL;
    public Book(int id, String name, String author, String publisher, String desc) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.publisher = publisher;
        this.desc = desc;
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
