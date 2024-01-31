import datastructurs.List;
import java.util.Scanner;

/**
 * ListTests class to test the functionality of the newly-written recursive search method.
 */
public class ListTests {
    /**
     * The main method of the program. Creates a list of Integers and tests the recursiveSearch functionality
     * @param args -
     */
    public static void main(String[] args) {
        // Create a new List of Integers called "Integer List"
        List<Integer> list = new List<>("Integer List");

        // Scanner initialization
        Scanner scanner = new Scanner(System.in);

        // Create a list of numbers
        list.insertAtBack(5);
        list.insertAtBack(10);
        list.insertAtBack(15);
        list.insertAtBack(20);
        list.insertAtBack(25);
        list.insertAtBack(30);
        list.insertAtBack(35);
        list.insertAtBack(987654321);

        // Print the list
        list.print();

        // Ask the user for an input
        System.out.println("Enter a number to search: ");
        int num = scanner.nextInt();

        // Recursively search for the number in the list and return it if found.
        Integer searchResults = list.search(num);
        if (searchResults != null) {
            System.out.println("Successfully found " + searchResults + " in the list.");
        } else {
            System.out.println(num + " not found.");
        }

        // Let's wrap it up and close the scanner
        scanner.close();
    }
}
