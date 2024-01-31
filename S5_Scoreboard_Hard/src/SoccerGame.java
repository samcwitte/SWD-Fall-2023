import java.util.*;

/**
 * The SoccerGame class extends the Game class, and contains Soccer-specific scoring methods and information about the game, such as the period length and name.
 */
public class SoccerGame extends Game {

    /**
     * SoccerGame class constructor
     * @param homeTeam the home team
     * @param awayTeam the away team
     */
    public SoccerGame(Team homeTeam, Team awayTeam) {
        super(homeTeam, awayTeam);
        periodLength = 45;
        periodName = "Half";
        scoringMethods = Arrays.asList(
                new ScoringMethod("Goal", 1)
        );
    }

    /**
     * Start the game
     */
    @Override
    public void startGame() {
        period = 1;
    }

    /**
     * End the current game period
     */
    @Override
    public void endPeriod() {
        period += 1;
    }

    /**
     * returns if the game is over
     * @return if the final period has ended
     */
    @Override
    public boolean isGameOver() {
        return period > 2;
    }

    /**
     * gets a List of a game's scoring methods
     * @return the List<> of ScoringMethod scoring methods
     */
    @Override
    public List<ScoringMethod> getScoringMethods() {
        return scoringMethods;
    }

    /**
     * adds score to the selected team
     * @param scoringMethod which scoring method to use
     * @param team team to add the score to
     */
    @Override
    public void addScore(ScoringMethod scoringMethod, Team team) {
        team.addScore(scoringMethod.getMethodPoints());
    }

    /**
     * getPeriodLength returns the length of the game's periods, in minutes
     * @return periodLength
     */
    @Override
    public int getPeriodLength() {
        return periodLength;
    }

    /**
     * getPeriodName returns the period name
     * @return periodName
     */
    @Override
    public String getPeriodName() {
        return periodName;
    }

    /**
     * Gets the winning team. Soccer games cannot result in tie.
     * @return the winning team
     */
    @Override
    public Team getWinningTeam() {
        if (awayTeam.getScore() > homeTeam.getScore()) {
            return awayTeam;
        } else if (homeTeam.getScore() > awayTeam.getScore()) {
            return homeTeam;
        } else return null;
    }

    /**
     * gets the home team
     * @return the home team
     */
    @Override
    public Team getHomeTeam() {
        return homeTeam;
    }

    /**
     * gets the away team
     * @return the away team
     */
    @Override
    public Team getAwayTeam() {
        return awayTeam;
    }

    /**
     * gets the current game period
     * @return the current game period
     */
    @Override
    public int getPeriod() {
        return period;
    }
}
