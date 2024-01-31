import javafx.scene.shape.Rectangle;

/**
 * Hour extends ClockHand and represents the hour hands.
 * It implements Runnable to allow each hour hand to be updated in its own thread.
 */
public class Hour extends ClockHand implements Runnable {
    /**
     * The time value representing the initial position of the hour hands.
     */
    private double time;

    /**
     * Constructor for the Hour class.
     * Initializes the hour hands and sets their initial positions.
     *
     * @param rectangles Array of Rectangle objects representing the hour hands
     * @param time Initial position for the hour hands, based on the current time
     */
    public Hour (Rectangle[] rectangles, double time) {
        super(rectangles);

        this.time = time;

        // Rotate each hand to its initial position
        for (Rectangle rect : rects) {
            Controller.rotateRect(rect, time);
            time++;
        }
    }

    /**
     * The run method updates the position of each hour hand every hour.
     */
    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(3600 * 1000); // 1 hour
            } catch (InterruptedException interruptedException) {
                throw new RuntimeException(interruptedException);
            }
            tick(time);
            time++;
        }
    }
}
