import java.util.*;

/**
 * S5_Scoreboard is a class hierarchy that allows you to score football, basketball, soccer, and hockey.
 * It utilizes polymorphism in order to create a modular and expandable set of classes that can be added to later.
 * There is also a command line aspect of this program that allows the user to interact with the classes.
 * The user can start a game and then choose from options until the game is over, at which point the console will print out the winner of the game.
 */
public class Main {
    public static void main(String[] args) {
        // Scanner setup
        Scanner scanner = new Scanner(System.in);
        String team1name = null;
        String team2name = null;

        // Initial game setup prompt
        System.out.println("Select the type of game:");
        System.out.println("1. Football");
        System.out.println("2. Basketball");
        System.out.println("3. Soccer");
        System.out.println("4. Hockey");
        int choice = scanner.nextInt();
        scanner.nextLine();

        // User input for team names
        System.out.print("Enter Home Team: ");
        Team team1 = new Team(scanner.nextLine());
        System.out.print("Enter Away Team: ");
        Team team2 = new Team(scanner.nextLine());

        // create Game
        Game game = null;
        if (choice == 1) {
            game = new FootballGame(team1, team2);
        } else if (choice == 2) {
            game = new BasketballGame(team1, team2);
        } else if (choice == 3) {
            game = new SoccerGame(team1, team2);
        } else if (choice == 4) {
            game = new HockeyGame(team1, team2);
        } else {
            throw new RuntimeException("Invalid game choice");
        }

        // Start the game
        game.startGame();

        // Game loop
        while (!game.isGameOver()) {
            // Print the current score and the current period
            System.out.println("\n" + game.getHomeTeam().getName() + " - " + game.getHomeTeam().getScore() + ", " + game.getAwayTeam().getName() + " - " + game.getAwayTeam().getScore());
            System.out.println("Current " + game.getPeriodName() + ": " + game.getPeriod());

            // List out scoring methods per team
            for (int i = 1; i < game.getScoringMethods().size() * 2 + 1; ++i) {
                if (i <= game.getScoringMethods().size()) {
                    System.out.println(i + ". " + team1.getName() + " " + game.getScoringMethods().get(i-1).getMethodName());
                } else if (i <= game.getScoringMethods().size() * 2) {
                    System.out.println(i + ". " + team2.getName() + " " + game.getScoringMethods().get(i-1-game.getScoringMethods().size()).getMethodName());
                }
            }
            // Final option of ending the current game period
            System.out.println(((game.getScoringMethods().size()*2)+1) + ". End " + game.getPeriodName());
            System.out.println("Enter choice: ");
            choice = scanner.nextInt();

            // Add score if user selected one of those options.
            if (choice <= game.getScoringMethods().size() * 2) {
                game.addScore(game.getScoringMethods().get(choice - (choice <= game.getScoringMethods().size() ? 0 : game.getScoringMethods().size()) - 1), (choice <= game.getScoringMethods().size() ? team1 : team2));
            } else {
                game.endPeriod();
            }
        }

        // Game summary
        System.out.println("Game is over.");
        System.out.println(game.getHomeTeam().getName() + " - " + game.getHomeTeam().getScore() + ", " + game.getAwayTeam().getName() + " - " + game.getAwayTeam().getScore());
        System.out.println("Current " + game.getPeriodName() + ": Final");
        System.out.println("Winner: " + game.getWinningTeam().getName());

    }
}