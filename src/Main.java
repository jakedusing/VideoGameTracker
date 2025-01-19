import tracker.VideoGame;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        List<VideoGame> backlog = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n=== Video Game Backlog Tracker ===");
            System.out.println("1. Add a new video game");
            System.out.println("2. View all video games");
            System.out.println("3. Filter games by console");
            System.out.println("4. Filter games by genre");
            System.out.println("5. Exit");
            System.out.println("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1 -> addVideoGame(backlog, scanner);
                case 2 -> viewAllGames(backlog);
                case 3 -> filterByConsole(backlog, scanner);
                case 4 -> filterByGenre(backlog, scanner);
                case 5 -> {
                    System.out.println("Exiting.. Goodbye!");
                    return;
                }
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    // Add a new video game to the backlog
    private static void addVideoGame(List<VideoGame> backlog, Scanner scanner) {
        System.out.print("Enter the game's title: ");
        String title = scanner.nextLine();

        System.out.print("Enter the game's genre: ");
        String genre = scanner.nextLine();

        System.out.print("Enter the release year: ");
        int releaseYear = scanner.nextInt();
        scanner.nextLine(); // consume newline

        System.out.print("Enter the console: ");
        String console = scanner.nextLine();

        System.out.print("Is the game completed? (true/false): ");
        boolean completed = scanner.nextBoolean();
        scanner.nextLine(); // consume newline

        backlog.add(new VideoGame(title, genre, releaseYear, console, completed));
        System.out.println("Game added successfully!");
    }

    // View all games in the backlog
    private static void viewAllGames(List<VideoGame> backlog) {
        System.out.println("\n=== All Video Games ===");
        if (backlog.isEmpty()) {
            System.out.println("No games in the backlog yet.");
        } else {
            System.out.println("\n--- Your Backlog ---");
            for (VideoGame game : backlog) {
                System.out.println(game);
            }
        }
    }

    // Filter games by console
    private static void filterByConsole(List<VideoGame> backlog, Scanner scanner) {
        System.out.print("Enter the console to filter by: ");
        String console = scanner.nextLine();

        System.out.println("\nGames available on " + console + ":");
        boolean found = false;
        for (VideoGame game : backlog) {
            if (game.getConsole().equalsIgnoreCase(console)) {
                System.out.println(game);
                found = true;
            }
        }
        if (!found) {
            System.out.println("No games found for this console.");
        }
    }

    // Filter games by genre
    private static void filterByGenre(List<VideoGame> backlog, Scanner scanner) {
        System.out.print("Enter the genre to filter by: ");
        String genre = scanner.nextLine();

        System.out.println("\nGames in the genre: " + genre);
        boolean found = false;
        for (VideoGame game : backlog) {
            if (game.getGenre().equalsIgnoreCase(genre)) {
                System.out.println(game);
                found = true;
            }
        }
        if (!found) {
            System.out.println("No games found for this genre");
        }
    }
}