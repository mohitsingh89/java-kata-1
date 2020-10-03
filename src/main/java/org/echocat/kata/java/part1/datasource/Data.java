package org.echocat.kata.java.part1.datasource;

import org.echocat.kata.java.part1.domain.Author;
import org.echocat.kata.java.part1.domain.Publication;

import java.util.ArrayList;
import java.util.List;

public class Data {
    private List<Author> authors = new ArrayList<>();
    private List<Publication> publications = new ArrayList<>();

    public List<Author> getAuthors() {
        return authors;
    }

    public List<Publication> getPublications() {
        return publications;
    }
}