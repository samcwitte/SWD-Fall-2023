/**
 * This is a class for a Food item, each with a volume and an enjoyment value.
 */
public class Food {
    /**
     * volume of the Food
     */
    private int volume;

    /**
     * enjoyment value of the Food
     */
    private double enjoyment;

    /**
     * Constructor for a food item, which takes in volume and enjoyment value of the food.
     * @param volume the volume of the food
     * @param enjoyment the enjoyment value of the food
     */
    public Food(int volume, double enjoyment) {
        this.volume = volume;
        this.enjoyment = enjoyment;
    }

    /**
     * Gets the volume of the food.
     * @return the volume of the food
     */
    public int getVolume() {
        return volume;
    }

    /**
     * Gets the enjoyment value of the food.
     * @return the enjoyment value of the food
     */
    public double getEnjoyment() {
        return enjoyment;
    }
}
