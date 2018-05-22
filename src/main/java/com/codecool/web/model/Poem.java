package com.codecool.web.model;

public class Poem extends AbstractModel {

    private final String title;
    private final String content;
    private final int publishDate;

    public Poem(int id, String title, String content, int publishDate) {
        super(id);
        this.title = title;
        this.content = content;
        this.publishDate = publishDate;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public int getPublishDate() {
        return publishDate;
    }
}
