package tracker;

public class VideoGame {
    private String title;
    private String genre;
    private int releaseYear;
    private int rating;
    private String console;
    private boolean isCompleted;

    public VideoGame(String title, String genre, int releaseYear, int rating, String console, boolean isCompleted) {
        this.title = title;
        this.genre = genre;
        this.releaseYear = releaseYear;
        this.rating = rating;
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

    public int getRating() {
        return rating;
    }

    public void setRating(int rating)
    {
        this.rating = rating;
    }

    public String getConsole() {
        return console;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted() {
        isCompleted = true;
    }

    public void setIncomplete() {
        isCompleted = false;
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
