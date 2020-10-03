package org.echocat.kata.java.part1.domain;


import java.util.Date;
import java.util.stream.Collectors;

public class Magazine extends Publication {
    private Date publishedAt;

    public Date getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(Date publishedAt) {
        this.publishedAt = publishedAt;
    }

    @Override
    public String toString() {
        return String.format("Type :%s \t Title : %s /t ISBN : %s /t Authors : %s /t Published At: %s /n",
                "Magazine",
                this.getTitle(),
                this.getIsbn(),
                this.getAuthors().stream().collect(Collectors.joining(",")),
                this.publishedAt
        );
    }
}
