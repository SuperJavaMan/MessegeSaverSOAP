package com.example.messegesaver.models;

import java.io.Serializable;
import java.util.Date;

public class MessageM implements Serializable {

    private static final long serialVersionUID = 1L;

    private String author;
    private Date postDate;
    private String body;

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Date getPostDate() {
        return postDate;
    }

    public void setPostDate(Date postDate) {
        this.postDate = postDate;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
