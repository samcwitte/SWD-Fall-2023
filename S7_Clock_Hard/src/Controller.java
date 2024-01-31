import javafx.fxml.FXML;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Translate;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Controller {

    /**
     * secondHand0 is a Rectangle that represents the second hand on clock 0 (top left).
     */
    @FXML
    public Rectangle secondHand0;

    /**
     * minuteHand0 is a Rectangle that represents the minute hand on clock 0 (top left).
     */
    @FXML
    public Rectangle minuteHand0;

    /**
     * hourHand0 is a Rectangle that represents the hour hand on clock 0 (top left).
     */
    @FXML
    public Rectangle hourHand0;

    /**
     * secondHand1 is a Rectangle that represents the second hand on clock 1.
     */
    @FXML
    public Rectangle secondHand1;

    /**
     * minuteHand1 is a Rectangle that represents the minute hand on clock 1.
     */
    @FXML
    public Rectangle minuteHand1;

    /**
     * hourHand1 is a Rectangle that represents the hour hand on clock 1.
     */
    @FXML
    public Rectangle hourHand1;

    /**
     * secondHand2 is a Rectangle that represents the second hand on clock 2.
     */
    @FXML
    public Rectangle secondHand2;

    /**
     * minuteHand2 is a Rectangle that represents the minute hand on clock 2.
     */
    @FXML
    public Rectangle minuteHand2;

    /**
     * hourHand2 is a Rectangle that represents the hour hand on clock 2.
     */
    @FXML
    public Rectangle hourHand2;

    /**
     * secondHand3 is a Rectangle that represents the second hand on clock 3.
     */
    @FXML
    public Rectangle secondHand3;

    /**
     * minuteHand3 is a Rectangle that represents the minute hand on clock 3.
     */
    @FXML
    public Rectangle minuteHand3;

    /**
     * hourHand3 is a Rectangle that represents the hour hand on clock 3.
     */
    @FXML
    public Rectangle hourHand3;

    /**
     * secondHand4 is a Rectangle that represents the second hand on clock 4.
     */
    @FXML
    public Rectangle secondHand4;

    /**
     * minuteHand4 is a Rectangle that represents the minute hand on clock 4.
     */
    @FXML
    public Rectangle minuteHand4;

    /**
     * hourHand4 is a Rectangle that represents the hour hand on clock 4.
     */
    @FXML
    public Rectangle hourHand4;

    /**
     * secondHand5 is a Rectangle that represents the second hand on clock 5.
     */
    @FXML
    public Rectangle secondHand5;

    /**
     * minuteHand5 is a Rectangle that represents the minute hand on clock 5.
     */
    @FXML
    public Rectangle minuteHand5;

    /**
     * hourHand5 is a Rectangle that represents the hour hand on clock 5.
     */
    @FXML
    public Rectangle hourHand5;

    /**
     * secondHand6 is a Rectangle that represents the second hand on clock 6.
     */
    @FXML
    public Rectangle secondHand6;

    /**
     * minuteHand6 is a Rectangle that represents the minute hand on clock 6.
     */
    @FXML
    public Rectangle minuteHand6;

    /**
     * hourHand6 is a Rectangle that represents the hour hand on clock 6.
     */
    @FXML
    public Rectangle hourHand6;

    /**
     * secondHand7 is a Rectangle that represents the second hand on clock 7.
     */
    @FXML
    public Rectangle secondHand7;

    /**
     * minuteHand7 is a Rectangle that represents the minute hand on clock 7.
     */
    @FXML
    public Rectangle minuteHand7;

    /**
     * hourHand7 is a Rectangle that represents the hour hand on clock 7.
     */
    @FXML
    public Rectangle hourHand7;

    /**
     * secondHand8 is a Rectangle that represents the second hand on clock 8.
     */
    @FXML
    public Rectangle secondHand8;

    /**
     * minuteHand8 is a Rectangle that represents the minute hand on clock 8.
     */
    @FXML
    public Rectangle minuteHand8;

    /**
     * hourHand8 is a Rectangle that represents the hour hand on clock 8.
     */
    @FXML
    public Rectangle hourHand8;

    /**
     * secondHand9 is a Rectangle that represents the second hand on clock 9.
     */
    @FXML
    public Rectangle secondHand9;

    /**
     * minuteHand9 is a Rectangle that represents the minute hand on clock 9.
     */
    @FXML
    public Rectangle minuteHand9;

    /**
     * hourHand9 is a Rectangle that represents the hour hand on clock 9.
     */
    @FXML
    public Rectangle hourHand9;

    /**
     * timeHour is the hard-coded hour value of the start state of the clocks
     */
    int timeHour = 12;

    /**
     * timeMinute is the hard-coded minute value of the start state of the clock
     */
    int timeMinute = 30;

    /**
     * timeSecond is the hard-coded second value of the start state of the clock
     */
    int timeSecond = 0;

    /**
     * The initialize method sets the initial position of the clock hands and starts the clock movements.
     * It is called automatically when the FXML file is loaded.
     */
    @FXML
    public void initialize() {
        System.out.println("init() was called");
        // Arrays of clock hands for hours, minutes, and seconds.
        Rectangle[] hours = new Rectangle[]{hourHand0, hourHand1, hourHand2, hourHand3, hourHand4, hourHand5, hourHand6, hourHand7, hourHand8, hourHand9};
        Rectangle[] minutes = new Rectangle[]{minuteHand0, minuteHand1, minuteHand2, minuteHand3, minuteHand4, minuteHand5, minuteHand6, minuteHand7, minuteHand8, minuteHand9};
        Rectangle[] seconds = new Rectangle[]{secondHand0, secondHand1, secondHand2, secondHand3, secondHand4, secondHand5, secondHand6, secondHand7, secondHand8, secondHand9};

        // Time zones for each clock
        ZoneOffset[] timeZones = {
                ZoneOffset.ofHours(-6),
                ZoneOffset.ofHours(-5),
                ZoneOffset.ofHours(-4),
                ZoneOffset.ofHours(-3),
                ZoneOffset.ofHours(-2),
                ZoneOffset.ofHours(-1),
                ZoneOffset.ofHours(0),
                ZoneOffset.ofHours(1),
                ZoneOffset.ofHours(2),
                ZoneOffset.ofHours(3)
        };

        // Setting the initial position of each clock hand based on the time zones above.
        for (int i = 0; i < timeZones.length; i++) {
            LocalDateTime localDateTime = LocalDateTime.now(timeZones[i]);
            timeHour = localDateTime.getHour();
            timeMinute = localDateTime.getMinute();
            timeSecond = localDateTime.getSecond();

            rotateRect(hours[i], timeHour * 30 + (timeMinute / 2));
            rotateRect(minutes[i], timeMinute * 6);
            rotateRect(seconds[i], timeSecond * 6);
        }

        // Creating and executing threads to update each type of hand (hour, minute, second).
        Hour hourHands = new Hour(hours, timeHour + (double)timeMinute / 60);
        Minute minuteHands = new Minute(minutes, timeMinute + (double)timeSecond / 60);
        Second secondHands = new Second(seconds, timeSecond);

        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(hourHands);
        executorService.execute(minuteHands);
        executorService.execute(secondHands);

    }

    /**
     * The rotateRect method applies a rotation transformation to a Rectangle (clock hand).
     * The rotation is based on the angle calculated for the current time.
     * @param rect the Rectangle to rotate
     * @param angle the angle of rotation (in degrees)
     */
    public static void rotateRect(Rectangle rect, double angle) {
        Rotate rotate = new Rotate();

        rect.getTransforms().clear();
        rotate.setPivotX(rect.getWidth() / 2);
        rotate.setPivotY(rect.getHeight());
        rotate.setAngle(angle);

        rect.getTransforms().add(rotate);
    }

}
