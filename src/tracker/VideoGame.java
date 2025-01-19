package tracker;

public class VideoGame {
    private String title;
    private String genre;
    private int releaseYear;
    private String console;
    private boolean isCompleted;

    public VideoGame(String title, String genre, int releaseYear, String console, boolean isCompleted) {
        this.title = title;
        this.genre = genre;
        this.releaseYear = releaseYear;
        this.console = console;
        this.isCompleted = isCompleted;
    }

    public String getTitle() {
        return title;
    }

    public String getGenre() {
        return genre;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public String getConsole() {
        return console;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }

    @Override
    public String toString() {
        return "Title: " + title +
                ", Genre: " + genre +
                ", Release Year: " + releaseYear +
                ", Console: " + console +
                ", Completed: " + isCompleted;
    }
}
