package org.echocat.kata.java.part1.datasource;


import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.echocat.kata.java.part1.domain.Author;
import org.echocat.kata.java.part1.domain.Book;
import org.echocat.kata.java.part1.domain.Magazine;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class CsvDataLoader {
    private static final String FILE_PATH_AUTHORS = "src/main/resources/org/echocat/kata/java/part1/data/authors.csv";
    private static final String FILE_PATH_BOOKS = "src/main/resources/org/echocat/kata/java/part1/data/books.csv";
    private static final String FILE_PATH_MAGAZINES = "src/main/resources/org/echocat/kata/java/part1/data/magazines.csv";
    private static final char SEPARATOR = ';';
    private static final String[] HEADERS_AUTHORS = new String[]{"email", "firstname", "lastname"};
    private static final String[] HEADERS_BOOKS = new String[]{"title", "isbn", "authors", "description"};
    private static final String[] HEADERS_MAGAZINES = new String[]{"title", "isbn", "authors", "publishedAt"};


    public static Data loadData() {
        CSVParser csvFileParser = null;
        InputStreamReader reader = null;
        final Data data = new Data();
        try {
            reader = new InputStreamReader(new FileInputStream(FILE_PATH_AUTHORS));
            csvFileParser = new CSVParser(reader, CSVFormat.DEFAULT.withHeader(HEADERS_AUTHORS)
                    .withDelimiter(SEPARATOR));
            data.getAuthors().addAll(parse(csvFileParser, AUTHOR_FUNCTION));
            //books
            reader = new InputStreamReader(new FileInputStream(FILE_PATH_BOOKS));
            csvFileParser = new CSVParser(reader, CSVFormat.DEFAULT.withHeader(HEADERS_BOOKS)
                    .withDelimiter(SEPARATOR));
            data.getPublications().addAll(parse(csvFileParser, BOOK_FUNCTION));
            //magazines
            reader = new InputStreamReader(new FileInputStream(FILE_PATH_MAGAZINES));
            csvFileParser = new CSVParser(reader, CSVFormat.DEFAULT.withHeader(HEADERS_MAGAZINES)
                    .withDelimiter(SEPARATOR));
            data.getPublications().addAll(parse(csvFileParser, MAGAZINE_FUNCTION));
            return data;
        } catch (final IOException exception) {
            throw new IllegalStateException("error in parsing the CSV ", exception);
        } catch (final Exception exception) {
            throw new IllegalStateException("error loading data from  CSV ", exception);
        } finally {
            if (csvFileParser != null) {
                try {
                    csvFileParser.close();
                } catch (final IOException ignore) {
                }
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (final IOException ignore) {
                }
            }
        }
    }

    private static <T> List<T> parse(CSVParser csvFileParser, Function<CSVRecord, T> function) throws IOException {
        return csvFileParser.getRecords().stream()
                .skip(1) //skip header row
                .map(function)
                .collect(Collectors.toList());
    }


    private static final Function<CSVRecord, Author> AUTHOR_FUNCTION = (final CSVRecord record) -> {
        final Author author = new Author();
        author.setEmail(getCellValue(record, HEADERS_AUTHORS, 0));
        author.setFirstName(getCellValue(record, HEADERS_AUTHORS, 1));
        author.setEmail(getCellValue(record, HEADERS_AUTHORS, 2));
        return author;
    };


    private static final Function<CSVRecord, Book> BOOK_FUNCTION = (final CSVRecord record) -> {
        final Book book = new Book();
        book.setTitle(getCellValue(record, HEADERS_BOOKS, 0));
        book.setIsbn(getCellValue(record, HEADERS_BOOKS, 1));
        book.getAuthors().addAll(getMultipleCellValue(record, HEADERS_BOOKS, 2));
        book.setDescription(getCellValue(record, HEADERS_BOOKS, 3));
        return book;
    };

    private static final Function<CSVRecord, Magazine> MAGAZINE_FUNCTION = (final CSVRecord record) -> {
        final Magazine magazine = new Magazine();
        magazine.setTitle(getCellValue(record, HEADERS_BOOKS, 0));
        magazine.setIsbn(getCellValue(record, HEADERS_BOOKS, 1));
        magazine.getAuthors().addAll(getMultipleCellValue(record, HEADERS_BOOKS, 2));
        DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        try {
            magazine.setPublishedAt(dateFormat.parse(getCellValue(record, HEADERS_MAGAZINES, 3)));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return magazine;
    };

    private static String getCellValue(final CSVRecord record, String[] headers, final int index) {
        return record.get(headers[index]);
    }

    private static List<String> getMultipleCellValue(final CSVRecord record, String[] headers, final int index) {
        String[] values = record.get(headers[index]).split(",");
        return Arrays.asList(values);
    }


}
