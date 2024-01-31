import java.util.concurrent.BlockingQueue;
import java.lang.Math;

/**
 * The Slave class implements the Runnable interface and is designed to calculate the roots of quadratic equations.
 * It retrieves coefficients from a buffer, computes the roots, and stores them in another buffer.
 */
public class Slave implements Runnable {
    /**
     * Buffer for storing and retrieving the coefficients of quadratic equations.
     */
    private final CircularBuffer coefficientBuffer;

    /**
     * Buffer for storing the roots of the quadratic equations.
     */
    private final CircularBuffer rootBuffer;

    /**
     * Constructor for the Slave class.
     * Initializes the class with buffers for coefficients and roots.
     *
     * @param coefficientBuffer Buffer to store and retrieve coefficients
     * @param rootBuffer Buffer to store the calculated roots
     */
    public Slave(CircularBuffer coefficientBuffer, CircularBuffer rootBuffer) {
        this.coefficientBuffer = coefficientBuffer;
        this.rootBuffer = rootBuffer;
    }

    /**
     * The run method is invoked when the thread starts.
     * It calculates the roots of quadratic equations until a specified count is reached.
     */
    @Override
    public void run() {
        // System.out.println("Thread " + Thread.currentThread().getName() + " started");
        while (CircularBuffer.getSolveCount() != 0) {
            try {
                // Get coefficients from buffer
                double[] coefficients = coefficientBuffer.get();
                double a = coefficients[0];
                double b = coefficients[1];
                double c = coefficients[2];

                double plusMinusPartOfTheSong = b * b - (4 * a * c);

                // Compute roots
                if (plusMinusPartOfTheSong > 0) {
                    // Real roots
                    double root1 = (-b + Math.sqrt(plusMinusPartOfTheSong)) / (2 * a);
                    double root2 = (-b - Math.sqrt(plusMinusPartOfTheSong)) / (2 * a);
                    rootBuffer.put(new double[] {root1, root2, 0});
                } else if (plusMinusPartOfTheSong == 0) {
                    // Double roots
                    double root = -b / (2 * a);
                    rootBuffer.put(new double[] {root, 0, 1});
                } else {
                    // Complex roots
                    double real = -b / (2 * a);
                    double imag = Math.sqrt(Math.abs(plusMinusPartOfTheSong)) / (2 * a);
                    rootBuffer.put(new double[] {real, imag, 2});
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            // Update the count of equations to be solved.
            CircularBuffer.setSolveCount(CircularBuffer.getSolveCount() - 1);
            System.out.println("Solve count: " + CircularBuffer.getSolveCount());
        }
    }
}
