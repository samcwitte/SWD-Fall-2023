import java.util.*;

/**
 * An abstract class Game that's the basis for the 4 types of sport games.
 */
public abstract class Game {
    /**
     * The home team of the game.
     */
    protected Team homeTeam;

    /**
     * The away team of the game.
     */
    protected Team awayTeam;

    /**
     * The current game period.
     */
    protected int period;

    /**
     * List of scoring methods for the game.
     */
    protected List<ScoringMethod> scoringMethods;

    /**
     * Length of 1 game period, in minutes.
     */
    protected int periodLength;

    /**
     * Name of a game period.
     */
    protected String periodName;

    /**
     * Constructor for a Game.
     * @param homeTeam the home team
     * @param awayTeam the away team
     */
    public Game (Team homeTeam, Team awayTeam) {
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.period = 0;
    }

    /**
     * Starts/initializes the game.
     */
    public abstract void startGame();

    /**
     * Ends the current game period.
     */
    public abstract void endPeriod();

    /**
     * Returns if the game is over or not, based on game period.
     * @return if the game is over or not
     */
    public abstract boolean isGameOver();

    /**
     * Gets the list of scoring methods in a game.
     * @return List<ScoringMethod> of scoring methods for the current game.
     */
    public abstract List<ScoringMethod> getScoringMethods();

    /**
     * Adds points to a specified team.
     * @param scoringMethod the scoring method the team used to score points
     * @param team the team that scored the point(s)
     */
    public abstract void addScore (ScoringMethod scoringMethod, Team team);

    /**
     * Gets the length of 1 game period, in minutes
     * @return the length of a single game period, in minutes.
     */
    public abstract int getPeriodLength();

    /**
     * Gets the name of a game period.
     * @return the name of the game's period.
     */
    public abstract String getPeriodName();

    /**
     * Gets the winning team, based on the teams' scores.
     * @return the winning team
     */
    public abstract Team getWinningTeam();

    /**
     * Gets the home team.
     * @return the home team
     */
    public abstract Team getHomeTeam();

    /**
     * Gets the away team
     * @return the away team
     */
    public abstract Team getAwayTeam();

    /**
     * Gets the current game period
     * @return the current game period.
     */
    public abstract int getPeriod();
}
