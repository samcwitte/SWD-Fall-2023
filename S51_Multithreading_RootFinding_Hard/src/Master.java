import java.util.HashMap;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.*;

/**
 * Master serves as the host or master for all the threads (or slaves)
 */
public class Master {
    /**
     * MAX_BUFFER_SIZE is the maximum size of the buffer
     */
    private static final int MAX_BUFFER_SIZE = 10;

    /**
     * Calculate generates new sets of coefficients and puts them into the CircularBuffer for consumption by the threads.
     * @param coefficientBuffer The buffer to put the coefficient sets into
     * @param rootBuffer the buffer to put the roots into
     * @param count 1 or 2, corresponds to the amount of coefficient sets generated
     */
    public static void calculate(CircularBuffer coefficientBuffer, CircularBuffer rootBuffer, int count) {
        Random random = new Random(); // Creates a new random number generator.
        int numToGenerate = (count == 1 ? 30 : 3000);

        // Generates random coefficients and sends them to the buffer.
        for (int i = 0; i < numToGenerate; ++i) {
            try {
                double a = random.nextDouble() * 100;
                double b = random.nextDouble() * 100;
                double c = random.nextDouble() * 100;
                // System.out.println("Coefficients: " + a + ", " + b + ", " + c);
                coefficientBuffer.put(new double[]{a, b, c});
                // roots = rootBuffer.get();
            } catch (InterruptedException interruptedException) {
                Thread.currentThread().interrupt();
            }
        }
    }

    /**
     * The main method generates either 30 or 3000 polynomials (sets of coefficients)
     * and sends them to a circular buffer to be processed by slaves.
     * @param args n/a
     * @throws InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {
        CircularBuffer coefficientBuffer;
        CircularBuffer rootBuffer;

        // Creates a thread pool that creates new threads as needed, but will reuse previously constructed threads when they are available.
        ExecutorService executorService = Executors.newCachedThreadPool();

        // Sets the count to 3000 if specified in program args and 30 otherwise.
        System.out.println("1. Generate and solve   30 sets of randomly generated polynomials.");
        System.out.println("2. Generate and solve 3000 sets of randomly generated polynomials.");
        int count = inputChoice();
        // System.out.println("Count = " + count);
        if (count == 1) { // 30
            int thread_count = 10;
            CircularBuffer.setSolveCount(30);
            coefficientBuffer = new CircularBuffer(MAX_BUFFER_SIZE, "Coefficient Buffer");
            rootBuffer = new CircularBuffer(MAX_BUFFER_SIZE, "Root Buffer");
            while (thread_count != 0) {
                // System.out.println("Launching thread " + thread_count);
                executorService.execute(new Slave(coefficientBuffer, rootBuffer));
                thread_count--;
            }

            calculate(coefficientBuffer, rootBuffer, count);
            executorService.shutdown();
            boolean complete = executorService.awaitTermination(5, TimeUnit.SECONDS);

            if (complete) {
                double[] roots = rootBuffer.get();

                for (int i = 0; i < 30; ++i) {
                    if (roots[2] == 0) { // 2 roots
                        System.out.println((i + 1) + ". 2 real roots: " + roots[0] + "; " + roots[1]);
                    } else if (roots[2] == 1) { // double roots
                        System.out.println((i + 1) + ". Double roots: " + roots[0] + "; " + roots[0]);
                    } else {
                        System.out.println((i + 1) + ". Complex roots: " + roots[0] + " + " + roots[1] + "j; " + roots[0] + " - " + roots[1] + "j");
                    }
                }
            }
        }

        if (count == 2) {
            int thread_count = 10;
            CircularBuffer.setSolveCount(3000);
            coefficientBuffer = new CircularBuffer(MAX_BUFFER_SIZE, "Coefficient Buffer");
            rootBuffer = new CircularBuffer(MAX_BUFFER_SIZE, "Root Buffer");
            while (thread_count != 0) {
                // System.out.println("Launching thread " + thread_count);
                executorService.execute(new Slave(coefficientBuffer, rootBuffer));
                thread_count--;
            }
            calculate(coefficientBuffer, rootBuffer, count);
            executorService.shutdown();
            boolean complete = executorService.awaitTermination(10, TimeUnit.SECONDS);


            if (complete) {
                // Printing stats for 3000 roots
                HashMap<String, Integer> rootsMap = rootBuffer.getHashMap();
                for (String key : rootsMap.keySet()) {
                    System.out.println("Slave \"" + key + "\" solved " + rootsMap.get(key) + " polynomials");
                }
            }
        }
    }


    /**
     * This method validates the user's choice when selecting 1 or 2.
     * @return option selected
     */
    public static int inputChoice() {
        Scanner scanner = new Scanner(System.in);
        boolean valid = false;
        int option = 0;

        while (!valid) {
            try {
                if (scanner.hasNextInt()) {
                    option = Integer.parseInt(scanner.nextLine());
                    if (option == 1 || option == 2) {
                        valid = true;
                    } else {
                        throw new Exception("Invalid choice");
                    }
                } else {
                    throw new Exception("Invalid choice");
                }
            } catch (Exception exception) {
                System.out.println("Invalid choice");
                scanner.nextLine();
            }
        }

        return option;
    }
}
