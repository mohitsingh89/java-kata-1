package org.echocat.kata.java.part1;

import org.echocat.kata.java.part1.datasource.DataProviderCsvImpl;
import org.echocat.kata.java.part1.domain.Publication;
import org.echocat.kata.java.part1.service.DataService;

import java.util.List;
import java.util.Scanner;

public class MainApp {

    public static void main(String[] args) {
        welcomeMessage();
        Scanner scanner = new Scanner(System.in);
        String option = scanner.next();
        DataService dataService = new DataService(new DataProviderCsvImpl());
        while (!option.equals("9")) {
            welcomeMessage();
            processOptions(dataService, scanner, option);
        }
        //System.out.println(getHelloWorldText());

    }

    private static void processOptions(DataService dataService, Scanner scanner, String option) {
        switch (option) {
            case "1":
                printAllPublications(dataService, scanner);
                break;
            case "2":
                printPublicationByIsbn(dataService, scanner);
                break;
            case "3":
                printPublicationByAuthor(dataService, scanner);
                break;
            default:
                System.out.println("wrong option, please try again !!");
        }
    }

    private static void welcomeMessage() {
        System.out.print("\n\n Welcome to the Book Service. Enter options to know more : \n");
        System.out.print("Enter the following : \n");
        System.out.print("1 : To list all Books and Magazines (Publications)\n");
        System.out.print("2 : To find a Book or Magazine by ISBN number\n");
        System.out.print("3 : To find all Books or Magazines by author email\n");
        System.out.print("Enter 9 to terminate any time.");
    }

    private static void printPublicationByIsbn(DataService dataService, Scanner scanner) {
        System.out.print("Please enter an ISBN : \n");
        final String isbn = scanner.next();
        dataService.findPublicationByIsbn(isbn).ifPresentOrElse(System.out::println,
                () -> System.out.println("No book found for ISBN : " + isbn + "!!!"));
        nextOption();
        if (scanner.next().equals("9")) {
            System.exit(0);
        }
    }

    private static void printPublicationByAuthor(DataService dataService, Scanner scanner) {
        System.out.print("Please enter an Author email : \n");
        final String authorEmail = scanner.next();
        final List<Publication> publications = dataService.findPublicationByAuthorEmail(authorEmail);
        if (publications.isEmpty()) {
            System.out.print("No book or magazine found by Author email : " + authorEmail + "!!!");
            nextOption();
            if (scanner.next().equals("9")) {
                System.exit(0);
            }
            return;
        }
        System.out.println("Books or magazine by Author : " + authorEmail);
        publications.stream().forEach(System.out::print);
        nextOption();
        if (scanner.next().equals("9")) {
            System.exit(0);
        }
    }

    private static void printAllPublications(DataService dataService, Scanner scanner) {
        System.out.print("Do you want list to be sorted by title (enter Y/yes or N/no): \n");
        if (scanner.next().equalsIgnoreCase("Y") || scanner.next().equalsIgnoreCase("yes")) {
            dataService.printAllPublicationsByTitleAscOrder();
        } else if (scanner.next().equalsIgnoreCase("N") || scanner.next().equalsIgnoreCase("no")) {
            dataService.printAllPublications();
        } else {
            System.out.println("Option not found ! Printing list without sorting: ");
            dataService.printAllPublications();
        }
        nextOption();
        if (scanner.next().equals("9")) {
            System.exit(0);
        }
    }

    private static void nextOption() {
        System.out.print("Enter 7 to get to Main Menu and 9 to terminate.");
    }

    protected static String getHelloWorldText() {
        return "Hello world!";
    }

}
