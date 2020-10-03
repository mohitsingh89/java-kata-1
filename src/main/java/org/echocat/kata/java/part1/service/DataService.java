package org.echocat.kata.java.part1.service;

import org.echocat.kata.java.part1.datasource.Data;
import org.echocat.kata.java.part1.domain.Publication;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class DataService {

    private final Supplier<Data> dataSupplier;

    public DataService(Supplier<Data> dataSupplier) {
        this.dataSupplier = dataSupplier;
    }

    public void printAllPublications() {
        System.out.println("All Publications (Books and Magazines ) : ");
        dataSupplier.get().getPublications()
                .stream()
                .forEach(System.out::println);
        System.out.println("Total Publications :  " + dataSupplier.get().getPublications().size());
    }

    public Optional<Publication> findPublicationByIsbn(final String isbn) {
        System.out.println("ISBN : " + isbn);
        return dataSupplier.get().getPublications().stream()
                .filter(publication -> publication.getIsbn().equalsIgnoreCase(isbn))
                .findFirst();
    }

    public List<Publication> findPublicationByAuthorEmail(final String authorEmail) {
        System.out.println("Author Email : " + authorEmail);
        return dataSupplier.get().getPublications().stream()
                .filter(publication -> publication.getAuthors().contains(authorEmail))
                .collect(Collectors.toList());
    }

    public void printAllPublicationsByTitleAscOrder() {
        System.out.println("All Publications (Books and Magazines ) sorted by title in ascending order: ");
        dataSupplier.get().getPublications()
                .stream()
                .sorted(Comparator.comparing(Publication::getTitle))
                .forEach(System.out::println);
        System.out.println("Total Publications :  " + dataSupplier.get().getPublications().size());
    }


}
