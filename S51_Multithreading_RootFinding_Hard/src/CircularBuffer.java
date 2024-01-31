import java.util.HashMap;

/**
 * CircularBuffer is a thread-safe implementation of a circular buffer.
 */
public class CircularBuffer {
    /**
     * The buffer array to store the values.
     */
    private final double[][] buffer;

    /**
     * The count of occupied positions in the buffer.
     */
    private int occupied = 0;

    /**
     * The index for reading from the buffer.
     */
    private int readIndex = 0;

    /**
     * The index for writing to the buffer.
     */
    private int writeIndex = 0;

    /**
     * The name of the buffer, useful for distinguishing between different buffers.
     */
    private final String bufferName;

    /**
     * Static counter to keep track of the number of solutions to be processed.
     */
    private static int solveCount = 0;

    /**
     * A map to keep track of the number of solutions processed by each thread.
     */
    private final HashMap<String, Integer> solutionCountMap = new HashMap<>();

    /**
     * Constructor for the CircularBuffer class.
     *
     * @param bufferSize The size of the buffer
     * @param bufferName The name of the buffer
     */
    public CircularBuffer (int bufferSize, String bufferName) {
        buffer = new double[bufferSize][3];
        this.bufferName = bufferName;
    }

    /**
     * Sets the static solve count.
     *
     * @param solveCount The solve count to be set
     */
    public static void setSolveCount(int solveCount) {
        CircularBuffer.solveCount = solveCount;
    }

    /**
     * Gets the current solve count.
     *
     * @return The current solve count
     */
    public static int getSolveCount() {
        return solveCount;
    }

    /**
     * Puts a new set of values into the buffer.
     * If the buffer is full, waits until space is available.
     *
     * @param values The values to put into the buffer
     * @throws InterruptedException If the thread is interrupted while waiting
     */
    public synchronized void put(double[] values) throws InterruptedException {
        while (occupied == buffer.length) {
            wait();
        }

        buffer[writeIndex] = values;
        writeIndex = (writeIndex + 1) % buffer.length;
        ++occupied;

        notifyAll();
    }

    /**
     * Retrieves and removes a set of values from the buffer. If the buffer is empty, waits until values are available.
     * Additionally, tracks the solution count for the root buffer.
     *
     * @return The retrieved values
     * @throws InterruptedException If the thread is interrupted while waiting
     */
    public synchronized double[] get() throws InterruptedException {
        // System.out.println("Getting from buffer " + Thread.currentThread().getName() + "...");
        while (occupied == 0) {
            wait();
        }

        double[] values = buffer[readIndex];

        if (bufferName.equals("Root Buffer")) {
            String name = Thread.currentThread().getName();
            if (solutionCountMap.get(name) == null) {
                solutionCountMap.put(name, 1);
            } else {
                solutionCountMap.put(name, solutionCountMap.get(name) + 1);
            }
        }

        --occupied;
        readIndex = (readIndex + 1) % buffer.length; // explanation comment

        notifyAll();
        return values;
    }

    /**
     * Gets the solution count map.
     *
     * @return The solution count map
     */
    public HashMap<String, Integer> getHashMap() {
        return solutionCountMap;
    }

}
