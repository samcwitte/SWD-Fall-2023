import java.util.Arrays;
import java.util.List;

/**
 * The Thanksgiving class is the main class for this problem.
 * It tests one food list and prints out the maximum food enjoyment to the console.
 * No user input is required.
 */
public class Thanksgiving {
    public static void main(String[] args) {
        // This is an example of what you could run. In the future this could be reworked to take in user input.
        List<Food> foodList = Arrays.asList(
                new Food(3, 4), // Turkey
                new Food(2, 2), // Pie
                new Food(4, 5), // Potatoes
                new Food(10, 10), // Gravy
                new Food(2, 3), // Stuffing
                new Food(7, 5), // Cranberries
                new Food(12, 17) // Casserole
        );
        int tummySize = 20;
        System.out.println(calculateMaxEnjoyment(foodList, tummySize));
    }

    /**
     * Calculates the max enjoyment from a given list of Food items and the stomach capacity.
     * @param foodList List<Food> of Food objects
     * @param tummyCapacity int representing the size of the stomach
     * @return the maximum enjoyment value for the given foodList and tummyCapacity
     */
    public static double calculateMaxEnjoyment(List<Food> foodList, int tummyCapacity) {
        double[] stomach = new double[tummyCapacity + 1];

        // Checks if each food item to see if it can fit in the currently sized stomach
        for (int i = 1; i <= tummyCapacity; i++) {
            // For each food in the list...
            for (Food food : foodList) {
                // If the food's volume is less than the current tummy capacity...
                if (food.getVolume() <= i) {
                    // Update the stomach array to store the max enjoyment.
                    stomach[i] = Math.max(stomach[i], stomach[i - food.getVolume()] + food.getEnjoyment());
                }
            }
        }

        // Returns the maximum enjoyment value.
        return stomach[tummyCapacity];
    }
}