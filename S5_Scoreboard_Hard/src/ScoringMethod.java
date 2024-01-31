/**
 * ScoringMethod is a class for storing scoring methods, which includes a name and an amount of points.
 */
public class ScoringMethod {
    /**
     * methodName is the name of the scoring method.
     */
    private String methodName;

    /**
     * methodPoints is the amount of points of the scoring method.
     */
    private int methodPoints;

    /**
     * Constructor for a ScoringMethod.
     * @param methodName name of the scoring method
     * @param methodPoints value of the scoring method
     */
    public ScoringMethod (String methodName, int methodPoints) {
        this.methodName = methodName;
        this.methodPoints = methodPoints;
    }

    /**
     * Gets the points of the scoring method
     * @return the amount of points given for scoring with the given scoring method.
     */
    public int getMethodPoints() {
        return methodPoints;
    }

    /**
     * Gets the scoring method name
     * @return the name of the scoring method.
     */
    public String getMethodName() {
        return methodName;
    }
}
