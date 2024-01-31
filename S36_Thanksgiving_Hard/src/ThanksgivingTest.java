import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * ThanksgivingTest is a class with JUnit tests inside to ensure the methods and algorithm are working correctly.
 */
public class ThanksgivingTest {

    @Test
    public void testEnjoymentTest1() {
        List<Food> foodList = Arrays.asList(
                new Food(3, 4), // Turkey
                new Food(2, 2), // Pie
                new Food(4, 5), // Potatoes
                new Food(10, 10), // Gravy
                new Food(2, 3), // Stuffing
                new Food(7, 5), // Cranberries
                new Food(12, 17) // Casserole
        );
        int tummyCapacity = 20;
        System.out.println(Thanksgiving.calculateMaxEnjoyment(foodList, tummyCapacity));
        assertEquals(30, Thanksgiving.calculateMaxEnjoyment(foodList, tummyCapacity));
    }

    @Test
    public void testEnjoymentTest2() {
        List<Food> foodList = Arrays.asList(
                new Food(3, 4), // Turkey
                new Food(2, 2), // Pie
                new Food(4, 5), // Potatoes
                new Food(1, 1), // Gravy
                new Food(2, 3), // Stuffing
                new Food(10, 14), // Cranberries
                new Food(15, 24) // Casserole
        );
        int tummyCapacity = 41;
        assertEquals(64, Thanksgiving.calculateMaxEnjoyment(foodList, tummyCapacity));
    }

    @Test
    public void testEnjoymentTest3() {
        List<Food> foodList = Arrays.asList(
                new Food(3, 5), // Turkey
                new Food(4, 12), // Pie
                new Food(1, 1), // Potatoes
                new Food(2, 5), // Gravy
                new Food(1, 1), // Stuffing
                new Food(2, 2), // Cranberries
                new Food(3, 3) // Casserole
        );
        int tummyCapacity = 7;
        assertEquals(18, Thanksgiving.calculateMaxEnjoyment(foodList, tummyCapacity));
    }

    @Test
    public void testEnjoymentTest4() {
        List<Food> foodList = Arrays.asList(
                new Food(3, 6), // Turkey
                new Food(4, 9), // Pie
                new Food(1, 0.5), // Potatoes
                new Food(2, 4), // Gravy
                new Food(1, 1), // Stuffing
                new Food(2, 2), // Cranberries
                new Food(3, 3) // Casserole
        );
        int tummyCapacity = 9;
        assertEquals(19, Thanksgiving.calculateMaxEnjoyment(foodList, tummyCapacity));
    }

    @Test
    public void testEnjoymentTest5() {
        List<Food> foodList = Arrays.asList(
                new Food(6, 7), // Turkey
                new Food(7, 8), // Pie
                new Food(8, 9), // Potatoes
                new Food(9, 10), // Gravy
                new Food(10, 11), // Stuffing
                new Food(11, 12), // Cranberries
                new Food(12, 13) // Casserole
        );
        int tummyCapacity = 5;
        assertEquals(0, Thanksgiving.calculateMaxEnjoyment(foodList, tummyCapacity));
    }

}