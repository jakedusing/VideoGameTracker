import tracker.VideoGame;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        List<VideoGame> backlog = new ArrayList<>();

        backlog.add(new VideoGame("The Legend of Zelda: Breath of the Wild",
                "Adventure", 2017, "Nintendo Switch", false));
        backlog.add(new VideoGame("Animal Crossing: New Horizons",
                "Simulation", 2020, "Nintendo Switch", false));

        System.out.println("Video Game Backlog:");
        for (VideoGame game : backlog) {
            System.out.println(game);
        }
    }
}