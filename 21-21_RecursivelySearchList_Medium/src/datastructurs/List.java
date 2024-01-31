// Fig. 21.3: List.java
// ListNode and List class declarations.
package datastructurs;

/**
 * Class to represent one node in a list
 * @param <T>
 */
class ListNode<T> {
    /**
     * Package access members; List can access these directly
     */
    T data; // data for this node

    /**
     * Reference to the next node in the list
     */
    ListNode<T> nextNode;

    /**
     * Constructor creates a ListNode that refers to object
     * @param object object referred to by created ListNode
     */
    ListNode(T object) {
        this(object, null);
    }

    /**
     * // constructor creates ListNode that refers to the specified object and to the next ListNode
     * @param object specified object to refer to
     * @param node the next ListNode
     */
    ListNode(T object, ListNode<T> node) {
        data = object;
        nextNode = node;
    }

    /**
     * Returns reference to data in node
     * @return reference to data in node
     */
    T getData() {
        return data;
    }

    /**
     * Returns reference to next node in list
     * @return reference to next node in list
     */
    ListNode<T> getNext() {
        return nextNode;
    }
}


/**
 * Class List definition
 * @param <T>
 */
public class List<T> {
    /**
     * The first node in the list
     */
    private ListNode<T> firstNode;

    /**
     * The last node in the list
     */
    private ListNode<T> lastNode;

    /**
     * String, like "list", used in printing
     */
    private String name; // string like "list" used in printing

    /**
     * Constructor creates empty list with "list" as the name
     */
    public List() {
        this("list");
    }

    /**
     * Constructor creates an empty List with a name
     * @param listName name of the list
     */
    public List(String listName) {
        name = listName;
        firstNode = lastNode = null;
    }

    /**
     * Inserts an item at the front of the list
     * @param insertItem item to insert
     */
    public void insertAtFront(T insertItem) {
        if (isEmpty()) // firstNode and lastNode refer to same object
            firstNode = lastNode = new ListNode<T>(insertItem);
        else // firstNode refers to new node
            firstNode = new ListNode<T>(insertItem, firstNode);
    }

    /**
     * Inserts an item at the back of the list
     * @param insertItem item to insert
     */
    public void insertAtBack(T insertItem) {
        if (isEmpty()) // firstNode and lastNode refer to same object
            firstNode = lastNode = new ListNode<T>(insertItem);
        else // lastNode's nextNode refers to new node
            lastNode = lastNode.nextNode = new ListNode<T>(insertItem);
    }

    /**
     * Removes the first node from the list
     * @return removed node
     * @throws EmptyListException
     */
    public T removeFromFront() throws EmptyListException {
        if (isEmpty()) // throw exception if List is empty
            throw new EmptyListException(name);

        T removedItem = firstNode.data; // retrieve data being removed

        // update references firstNode and lastNode
        if (firstNode == lastNode)
            firstNode = lastNode = null;
        else
            firstNode = firstNode.nextNode;

        return removedItem; // return removed node data
    } // end method removeFromFront

    /**
     * Removes the last node from the list
     * @return the removed item
     * @throws EmptyListException
     */
    public T removeFromBack() throws EmptyListException {
        if (isEmpty()) // throw exception if List is empty
            throw new EmptyListException(name);

        T removedItem = lastNode.data; // retrieve data being removed

        // update references firstNode and lastNode
        if (firstNode == lastNode)
            firstNode = lastNode = null;
        else // locate new last node
        {
            ListNode<T> current = firstNode;

            // loop while current node does not refer to lastNode
            while (current.nextNode != lastNode)
                current = current.nextNode;

            lastNode = current; // current is new lastNode
            current.nextNode = null;
        }

        return removedItem; // return removed node data
    }

    /**
     * isEmpty returns whether the list is empty
     * @return returns whether the list is empty
     */
    public boolean isEmpty() {
        return firstNode == null; // return true if list is empty
    }

    /**
     * Prints the list out to the console
     */
    public void print() {
        if (isEmpty()) {
            System.out.printf("Empty %s%n", name);
            return;
        }

        System.out.printf("The %s is: ", name);
        ListNode<T> current = firstNode;

        // while not at end of list, output current node's data
        while (current != null) {
            System.out.printf("%s ", current.data);
            current = current.nextNode;
        }

        System.out.println();
    }

    /**
     * Calls recursiveSearch
     * @param value the value to search for
     * @return the result
     */
    public T search(T value) {
        return recursiveSearch(firstNode, value);
    }

    /**
     * Calls a search, and if it doesn't find the value, recursively call the function again.
     * @param node The node to check
     * @param value The value to search for
     * @return null if nothing found, otherwise the found value
     */
    private T recursiveSearch(ListNode<T> node, T value) {
        // If the node is null, return null
        if (node == null) {
            return null;
        }

        // If the node's data matches the value being searched for, return the data
        if (node.data.equals(value)) {
            return node.data;
        }

        // Finally, if nothing was found, dive deeper
        return recursiveSearch(node.nextNode, value);
    }

} // end class List<T>

/**************************************************************************
 * (C) Copyright 1992-2014 by Deitel & Associates, Inc. and               *
 * Pearson Education, Inc. All Rights Reserved.                           *
 *                                                                        *
 * DISCLAIMER: The authors and publisher of this book have used their     *
 * best efforts in preparing the book. These efforts include the          *
 * development, research, and testing of the theories and programs        *
 * to determine their effectiveness. The authors and publisher make       *
 * no warranty of any kind, expressed or implied, with regard to these    *
 * programs or to the documentation contained in these books. The authors *
 * and publisher shall not be liable in any event for incidental or       *
 * consequential damages in connection with, or arising out of, the       *
 * furnishing, performance, or use of these programs.                     *
 *************************************************************************/
