package com.mosaiker.filegod.entity;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "link")
public class Link {
    private String title;
    private String author;
    private String file_id;
    private String description;
    private String link;

    public Link(String title, String author, String file_id, String description, String link) {
        this.title = title;
        this.author = author;
        this.file_id = file_id;
        this.description = description;
        this.link = link;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getDescription() {
        return description;
    }

    public String getLink() {
        return link;
    }

    public String getFile_id() {
        return file_id;
    }
}
