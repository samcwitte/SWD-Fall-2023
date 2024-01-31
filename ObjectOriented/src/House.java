import java.util.ArrayList;

/**
 * Represents a house with a specific area, door, and a collection of rooms.
 */

public class House {

    private double area;
    private Door door;
    private ArrayList<Room> rooms = new ArrayList<Room>();

    /**
     * Default constructor that initializes the house with an area of 0.0 and a default Door.
     */
    public House () {
        this(0.0, new Door());
    }

    /**
     * Constructor that initializes the house with a specified area and a default Door.
     * @param area The area of the house.
     */
    public House (double area) {
        this(area, new Door());
    }

    /**
     * Constructor that initializes the house with a specified Door and an area of 0.0.
     * @param door The door for the house.
     */
    public House (Door door) {
        this(0.0, door);
    }

    /**
     * Constructor that initializes the house with a specific area and door.
     * @param area The area of the house.
     * @param door The door for the house.
     */
    public House(double area, Door door) {
        this.area = area;
        this.door = door;
    }

    /**
     * Sets the area for the house.
     * @param newArea The new area for the house.
     */
    public void setArea(double newArea) {
        area = newArea;
    }

    /**
     * Gets the area for the house.
     * @return Returns the area of the house.
     */
    public double getArea() {
        return area;
    }

    /**
     * Sets the door of the house.
     * @param door The new door to set for the house.
     */
    public void setDoor (Door door) {
        this.door = door;
    }

    /**
     * Gets the door of the house.
     * @return Returns the door of the house.
     */
    public Door getDoor () {
        return door;
    }

    /**
     * Adds a room to the house.
     * @param newRoom The room to be added.
     */
    public void addRoom (Room newRoom) {
        rooms.add(newRoom);
    }

    /**
     * Removes a room from the house based on its name.
     * @param roomToRemove The room to be removed.
     * @return Returns true or false based on the success or failure of the method.
     */
    public boolean removeRoom (String roomToRemove) {
        for (int i = 0; i < rooms.size(); ++i) {
            if (rooms.get(i).getRoomName().equals(roomToRemove)) {
                rooms.remove(i);
                return true;
            }
        }
        return false;
    }

    /**
     * Lists the rooms in the house.
     */
    public void listRooms() {
        rooms.forEach((room) -> System.out.println(room));
    }

    /**
     * Provides details of the house in a String representation.
     * @return Returns a String describing the house.
     */
    @Override
    public String toString() {
        return "I am a house, my area is " + area + " square feet.";
    }
}
