/**
 * Represents a room with a specific name, number of windows, and wall color.
 */
public class Room {
    private String roomName;
    private int numWindows; // >= 0
    private String wallColor;

    /**
     * Default constructor that initializes the room as an "empty room" with 0 windows, and "unpainted" walls.
     */
    public Room () {
        this("empty room", 0, "unpainted");
    }

    /**
     * Constructor that initializes the room with a specified name, 0 windows, and "unpainted" walls.
     * @param roomName The name of the room.
     */
    public Room (String roomName) {
        this(roomName, 0, "unpainted");
    }

    /**
     * Constructor that initializes the room with a specified name, number of windows, and "unpainted" walls.
     * @param roomName The name of the room.
     * @param numWindows The number of windows in the room.
     */
    public Room (String roomName, int numWindows) {
        this(roomName, numWindows, "unpainted");
    }

    /**
     * Constructor that initializes the room with a specified name, number of windows, and wall color.
     * @param roomName The name of the room.
     * @param numWindows The number of windows in the room.
     * @param wallColor The color of the walls in the room.
     */
    public Room (String roomName, int numWindows, String wallColor) {
        if (numWindows < 0) {
            throw new IllegalArgumentException("numWindows must be >= 0");
        }

        this.roomName = roomName;
        this.numWindows = numWindows;
        this.wallColor = wallColor;
    }

    /**
     * Sets the name of the room.
     * @param roomName The new name for the room.
     */
    public void setRoomName (String roomName) {
        this.roomName = roomName;
    }

    /**
     * Gets the name of the room.
     * @return Returns the current name of the room.
     */
    public String getRoomName () {
        return roomName;
    }

    /**
     * Sets the number of windows in the room.
     * @param numWindows The new number of windows for the room.
     */
    public void setNumWindows (int numWindows) {
        this.numWindows = numWindows;
    }

    /**
     * Gets the number of windows in the room.
     * @return Returns the number of windows in the room.
     */
    public int getNumWindows () {
        return numWindows;
    }

    /**
     * Sets the wall color of the room.
     * @param wallColor The new wall color for the room.
     */
    public void setWallColor (String wallColor) {
        this.wallColor = wallColor;
    }

    /**
     * Gets the wall color of the room.
     * @return Returns the wall color of the room.
     */
    public String getWallColor () {
        return wallColor;
    }

    /**
     * Provide a string representation of the room.
     * @return Returns a string describing the room's name, number of windows, and wall color.
     */
    @Override
    public String toString() {
        return "The " + roomName + " has " + numWindows + " windows, and has " + wallColor + " walls.";
    }
}
