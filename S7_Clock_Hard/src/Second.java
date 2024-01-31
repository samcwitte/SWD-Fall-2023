import javafx.scene.shape.Rectangle;

/**
 * Second extends ClockHand and represents the second hands.
 * It implements Runnable to allow each second hand to be updated in its own thread.
 */
public class Second extends ClockHand implements Runnable {
    /**
     * The time value representing the initial position of the second hands.
     */
    private double time;

    /**
     * Constructor for the Second class.
     * Initializes the second hands and sets their initial positions.
     *
     * @param rectangles Array of Rectangle objects representing the second hands
     * @param time Initial position for the second hands, based on the current time
     */
    public Second (Rectangle[] rectangles, double time) {
        super(rectangles);

        this.time = time;

        // Rotate each hand to its initial position
        for (Rectangle rect : rects) {
            Controller.rotateRect(rect, time);
            time++;
        }
    }

    /**
     * The run method updates the position of each second hand every second.
     */
    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(1000); // 1 second
            } catch (InterruptedException interruptedException) {
                throw new RuntimeException(interruptedException);
            }
            tick(time);
            time++;
        }
    }
}
