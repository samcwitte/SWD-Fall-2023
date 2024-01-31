/**
 * Represents a person with a specific name and an associated house.
 */
public class Person {
    private String name;
    private House house;

    /**
     * Constructor that initializes the person with a specified name and house.
     * @param name The name of the person.
     * @param house The house of the person.
     */
    public Person(String name, House house) {
        this.name = name;
        this.house = house;
    }

    /**
     * Provides a string representation of the person.
     * @return Returns a String describing the person's name and house.
     */
    @Override
    public String toString() {
        return "Name: " + name + "\nHouse: " + house.toString();
    }
}
