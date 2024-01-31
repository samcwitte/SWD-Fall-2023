/**
 * Represents a door with a specific color.
 */
public class Door {
    private String color;

    /**
     * Sets the color of the door.
     * @param newColor The new color to set the door.
     */
    public void setColor(String newColor) {
        color = newColor;
    }

    /**
     * Retrieves the color of the door.
     * @return The current color of the door.
     */
    public String getColor() {
        return color;
    }

    /**
     * Default constructor that initializes the door with color "unpainted".
     */
    public Door() {
        this("unpainted");
    }

    /**
     * Constructor for door that initializes the door with a specified color.
     * @param color The color to set for the door.
     */
    public Door(String color) {
        this.color = color;
    }

    /**
     * Provides a string representation of the door.
     * @return A string describing the door's color.
     */
    @Override
    public String toString() {
        return "I am a " + color + " door";
    }
}