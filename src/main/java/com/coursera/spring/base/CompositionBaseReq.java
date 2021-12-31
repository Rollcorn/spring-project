package com.coursera.spring.base;


import javax.validation.constraints.NotEmpty;

public class CompositionBaseReq extends BaseRequest {

    @NotEmpty
    private String title;

    @NotEmpty
    private String author;


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
}