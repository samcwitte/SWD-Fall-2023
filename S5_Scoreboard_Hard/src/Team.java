/**
 * Team is a class for game teams. It includes the team name and their current score.
 */
public class Team {
    /**
     * name is the team name.
     */
    private String name;

    /**
     * score is the team's current score.
     */
    private int score;

    /**
     * Constructor for a team. Score is initialized as 0.
     * @param name team's name
     */
    public Team (String name) {
        this.name = name;
        this.score = 0;
    }

    /**
     * Gets the team's name.
     * @return the team's name
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the team's score.
     * @return the team's score
     */
    public int getScore() {
        return score;
    }

    /**
     * Adds points to the team.
     * @param scoreToAdd amount of points to add
     */
    public void addScore(int scoreToAdd) {
        score += scoreToAdd;
    }
}