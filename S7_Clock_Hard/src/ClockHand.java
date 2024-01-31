import javafx.scene.shape.Rectangle;

/**
 * ClockHand represents a generic clock hand for the clock.
 * It handles the rotation of multiple clock hands.
 */
public class ClockHand {
    /**
     * Array of Rectangle objects (the clock hands)
     */
    Rectangle[] rects;

    /**
     * Constructor for ClockHand.
     * Initializes the array of Rectangle objects.
     * @param rects Array of Rectangle objects
     */
    public ClockHand(Rectangle[] rects) {
        this.rects = rects;
    }

    /**
     * The tick method rotates each clock hand to the correct position based on the current time.
     * It prints the angle of each hand if it's less than 359 degrees, and resets it to 0 otherwise.
     * @param time the time value used to calculate the angle
     */
    public void tick(double time) {
        for (Rectangle rect : rects) {
            Controller.rotateRect(rect, time);
        }
    }

}
