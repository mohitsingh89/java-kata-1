package org.echocat.kata.java.part1.domain;


import java.util.stream.Collectors;

public class Book extends Publication {
    private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return String.format("Type :%s \t Title : %s /t ISBN : %s /t Authors : %s /t Description: %s /n" ,
                "Book",
                this.getTitle(),
                this.getIsbn(),
                this.getAuthors().stream().collect(Collectors.joining(",")),
                this.description
        ) ;
    }
}
