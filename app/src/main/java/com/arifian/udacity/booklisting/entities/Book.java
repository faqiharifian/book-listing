package com.arifian.udacity.booklisting.entities;

/**
 * Created by faqih on 20/04/17.
 */

public class Book {
    private String title;
    private String link;
    private String thumbnail;
    private String[] authors;

    public Book(String title, String link, String thumbnail, String[] authors) {
        this.title = title;
        this.link = link;
        this.thumbnail = thumbnail;
        this.authors = authors;
    }

    public String getTitle() {
        return title;
    }

    public String getLink() {
        return link;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public String[] getAuthors() {
        return authors;
    }
}
