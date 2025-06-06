import tracker.VideoGame;

import java.io.*;
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
            System.out.println("5. Set a game as complete");
            System.out.println("6. Set a game as incomplete");
            System.out.println("7. Save Backlog to file");
            System.out.println("8. Load Backlog from file");
            System.out.println("9. Save filtered list of games");
            System.out.println("10. Exit");
            System.out.println("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (choice) {
                case 1 -> addVideoGame(backlog, scanner);
                case 2 -> viewAllGames(backlog);
                case 3 -> filterByConsole(backlog, scanner);
                case 4 -> filterByGenre(backlog, scanner);
                case 5 -> setGameCompleted(backlog, scanner);
                case 6 -> setGameIncomplete(backlog, scanner);
                case 7 -> saveToFile(backlog, scanner);
                case 8 -> loadFromFile(backlog, scanner);
                case 9 -> exportFilteredList(backlog, scanner);
                case 10 -> {
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

        System.out.print("Rate the game 1-10: ");
        int rating = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter the console: ");
        String console = scanner.nextLine();

        System.out.print("Is the game completed? (true/false): ");
        boolean completed = scanner.nextBoolean();
        scanner.nextLine(); // consume newline

        backlog.add(new VideoGame(title, genre, releaseYear, rating, console, completed));
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

    // set game as complete
    private static void setGameCompleted(List<VideoGame> backlog, Scanner scanner) {
        System.out.println("Enter the game you would like to mark as completed: ");
        String completedGame = scanner.nextLine();
        boolean found = false;
        for (VideoGame game : backlog) {
            if (game.getTitle().equalsIgnoreCase(completedGame)) {
                if (game.isCompleted()) {
                    System.out.println("Game already completed.");
                    return;
                }
                game.setCompleted();
                System.out.println("Successfully marked " + completedGame + " as completed!");
                found = true;
            }
        }
        if (!found) {
            System.out.println("Could not find the game with title " + completedGame);
        }
    }

    // set game as incomplete
    private static void setGameIncomplete(List<VideoGame> backlog, Scanner scanner) {
        System.out.println("Enter the game you would like to mark as incomplete: ");
        String inCompleteGame = scanner.nextLine();
        boolean found = false;
        for (VideoGame game : backlog) {
            if (game.getTitle().equalsIgnoreCase(inCompleteGame)) {
                if (!game.isCompleted()) {
                    System.out.println("Game already set as not completed");
                    return;
                }
                game.setIncomplete();
                System.out.println("Successfully marked " + inCompleteGame + " as incomplete.");
                found = true;
            }
        }
        if (!found) {
            System.out.println("Could not find game with title " + inCompleteGame);
        }
    }

    // save to file
    private static void saveToFile(List<VideoGame> backlog,Scanner scanner) {
        System.out.println("Enter filename to save the backlog: ");
        String filename = scanner.nextLine();
        if (!filename.endsWith(".txt")) {
            filename += ".txt"; // Add .txt if not included
        }

        try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) {
            for (VideoGame game : backlog) {
                writer.println(game.getTitle() + "," + game.getGenre() + "," + game.getReleaseYear() + "," + game.getConsole() + "," + game.isCompleted());
            }
            System.out.println("Backlog saved to file: " + filename);
        } catch (IOException e) {
            System.out.println("Error saving file: " + e.getMessage());
        }
    }

    // read from file
    private static void loadFromFile(List<VideoGame> backlog, Scanner scanner) {
        System.out.println("Enter filename to load the backlog: ");
        String filename = scanner.nextLine();
        if (!filename.endsWith(".txt")) {
            filename += ".txt";  // add .txt if not included
        }

        File file = new File(filename);
        if (!file.exists()) {
            System.out.println("Error. File not found. Please check the filename and try again.");
            return; // exit method if file does not exist
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            backlog.clear();
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 6) {
                    String title = parts[0];
                    String genre = parts[1];
                    int releaseYear = Integer.parseInt(parts[2]);
                    int rating = Integer.parseInt(parts[3]);
                    String console = parts[4];
                    boolean completed = Boolean.parseBoolean(parts[5]);
                    backlog.add(new VideoGame(title, genre, releaseYear, rating, console, completed));
                }
            }
            System.out.println("Games successfully loaded from " + filename);
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }

    private static void exportFilteredList(List<VideoGame> backlog, Scanner scanner) {
        if (backlog.isEmpty()) {
            System.out.println("The backlog is empty. Nothing to filter.");
            return;
        }

        System.out.println("Choose a filter to option:");
        System.out.println("1. Filter by Console");
        System.out.println("2. Filter by Genre");
        int choice = scanner.nextInt();
        scanner.nextLine();  // consume new line

        List<VideoGame> filteredList = new ArrayList<>();
        switch (choice) {
            case 1 -> {
                System.out.println("Enter the console to filter by: ");
                String filteredConsole = scanner.nextLine();
                for (VideoGame game : backlog) {
                    if (game.getConsole().equalsIgnoreCase(filteredConsole)) {
                        filteredList.add(game);
                    }
                }
            }
            case 2 -> {
                System.out.println("Enter the genre to filter by: ");
                String filteredGenre = scanner.nextLine();
                for (VideoGame game : backlog) {
                    if (game.getGenre().equalsIgnoreCase(filteredGenre)) {
                        filteredList.add(game);
                    }
                }
            }
            default -> {
                System.out.println("Invalid choice. Returning to menu.");
                return;
            }
        }

        if (filteredList.isEmpty()) {
            System.out.println("No games matched the filter criteria.");
            return;
        }

        System.out.println("Filtered list:");
        for (VideoGame game : filteredList) {
            System.out.println(game);
        }

        System.out.print("Would you like to export this filtered list to a file? (yes/no): ");
        String exportChoice = scanner.nextLine().trim().toLowerCase();
        if (exportChoice.equals("yes")) {
            System.out.print("Enter filename to save the filtered list: ");
            String filename = scanner.nextLine();
            if (!filename.endsWith(".txt")) {
                filename += ".txt";
            }

            try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) {
                for (VideoGame game : filteredList) {
                    writer.println(game.getTitle() + "," + game.getGenre() + ","
                    + game.getReleaseYear() + "," + game.getConsole() + "," + game.isCompleted());
                }
                System.out.println("Filtered list saved to file: " + filename);
            } catch (IOException e) {
                System.out.println("Error saving file: " + e.getMessage());
            }
        } else {
            System.out.println("Filtered list was not exported.");
        }
    }
}