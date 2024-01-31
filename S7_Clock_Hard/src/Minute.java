import javafx.scene.shape.Rectangle;

/**
 * Minute extends ClockHand and represents the minute hands.
 * It implements Runnable to allow each minute hand to be updated in its own thread.
 */
public class Minute extends ClockHand implements Runnable {
    /**
     * The time value representing the initial position of the minute hands.
     */
    private double time;

    /**
     * Constructor for the Minute class.
     * Initializes the minute hands and sets their initial positions.
     *
     * @param rectangles Array of Rectangle objects representing the minute hands
     * @param time Initial position for the minute hands, based on the current time
     */
    public Minute (Rectangle[] rectangles, double time) {
        super(rectangles);

        this.time = time;

        // Rotate each hand to its initial position
        for (Rectangle rect : rects) {
            Controller.rotateRect(rect, time); // 360 / 6 = 60 minutes
            time++;
        }
    }

    /**
     * The run method updates the position of each minute hand every minute.
     */
    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(60 * 1000); // 1 minute
            } catch (InterruptedException interruptedException) {
                throw new RuntimeException(interruptedException);
            }
            tick(time);
            time++;
        }
    }
}
