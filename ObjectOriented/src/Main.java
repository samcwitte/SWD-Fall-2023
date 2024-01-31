/**
 * Main class for S50_House.
 *
 * Demonstrates the creation and manipulation of houses, rooms, etc. to demonstrate basic OOP skills and concepts.
 */

public class Main {

    public static void main(String[] args) {

        SmallApartment myFirstApartment = new SmallApartment();

        myFirstApartment.addRoom(new Room("kitchen", 0, "sage green"));
        myFirstApartment.addRoom(new Room("bedroom", 1, "off-white"));
        myFirstApartment.addRoom(new Room("living room", 2, "white"));
        myFirstApartment.addRoom(new Room("bathroom", 0, "hot pink"));

        myFirstApartment.addRoom(new Room("closet", 0));
        myFirstApartment.removeRoom("bedroom");

        Person john = new Person("John Smith IV II Sr.", myFirstApartment);

        System.out.println(john);
        System.out.println(myFirstApartment);
        System.out.println();
        System.out.println("Here's a list of the rooms:");
        myFirstApartment.listRooms();
    }
}