import javax.swing.*;
import java.lang.Math;

/**
 * The Main class provides functionality to convert numbers between different bases.
 * It also initializes the GUI.
 */
public class Main {

    /**
     * Converts a number from its current base to a desired base.
     * @param inputNum The number to be converted.
     * @param currBase The current base of the input number.
     * @param desiredBase The base to which the number should be converted to.
     * @return Returns the converted number in the desired base.
     * @throws IllegalArgumentException If the desired base is not between 2 and 32 (inclusive).
     */
    public String convertToBase (String inputNum, int currBase, int desiredBase) {
        String convertedNum = "";
        int baseTen = 0;
        boolean isNegative = false;
        String characters = "0123456789ABCDEFGHIJKLMNOPRSTUVW";

        if (inputNum.charAt(0) == '-') {
            isNegative = true;
            inputNum = inputNum.substring(1);
        }

        // Converts each char in `inputNum` to its equivalent ASCII value, and adds it to `baseTen`
        // Effectively converts the number to base 10 so processing it is easier for me.
        for (int i = inputNum.toCharArray().length - 1; i >= 0; --i) {
            char c = inputNum.toCharArray()[i];
            int placeValue = (int) Math.pow(currBase, inputNum.toCharArray().length - 1 - i);
            if ((int) c >= 48 && (int) c <= 57) { // numbers 0-9
                baseTen += ((int) c - '0') * placeValue;
            } else if ((int) c >= 65 && (int) c <= 87) { // letters A-W
                baseTen += ((int) c - 'A' + 10) * placeValue;
            } else if ((int) c >= 97 && (int) c <= 119) { // letters a-w
                baseTen += ((int) c - 'a' + 10) * placeValue;
            }
        }

        if (isNegative) {
            baseTen *= -1;
        }

        // Next, we'll
        if (desiredBase < 2 || desiredBase > 32) {
            throw new IllegalArgumentException("desiredBase must be between 2 and 32 (inclusive).");
        }

        StringBuilder newNum = new StringBuilder();

        // Converts input number to the user's desired base.
        if (baseTen == 0) {
            newNum.append('0');
        } else {
            while (baseTen > 0) {
                int remainder = baseTen % desiredBase;
                newNum.insert(0,characters.charAt(remainder));
                baseTen /= desiredBase;
            }
        }

        if (isNegative) {
            newNum.insert(0, '-');
        }

        return newNum.toString();
    }

    /**
     * The main application method. Initializes the GUI for base conversion.
     * @param args Not used.
     */
    public static void main(String[] args) {

        BaseFrame frame = new BaseFrame();
        frame.setSize(400,300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

    }
}