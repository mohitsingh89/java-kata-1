package org.echocat.kata.java.part1.domain;

import java.util.ArrayList;
import java.util.List;

public class Publication {
    private String title;
    private String isbn;
    private List<String> authors = new ArrayList<>();

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public List<String> getAuthors() {
        return authors;
    }
}
