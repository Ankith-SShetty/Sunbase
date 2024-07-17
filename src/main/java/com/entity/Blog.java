package com.entity;

public class Blog {
    private int id;
    private String title;
    private String content;
    private File file;
    private int creatorId;
    private String author;

    public Blog() {
    }

    public Blog(int id, String title, String content, File file, String author) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.file = file;
        this.author = author;
    }

    public Blog(int id, String title, String content, File file, int creatorId) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.file = file;
        this.creatorId = creatorId;
    }

    public Blog(int id, String title, String content, File file) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.file = file;
    }

    public Blog(String title, String content, File file, int creatorId) {
        this.title = title;
        this.content = content;
        this.file = file;
        this.creatorId = creatorId;
    }

    public Blog(String title, String content, File file) {
        this.title = title;
        this.content = content;
        this.file = file;
    }



    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public int getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(int creatorId) {
        this.creatorId = creatorId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
