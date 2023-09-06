/*
 * LLBag.java
 *
 * Computer Science 112, Boston University
 * 
 * modified by: Youjung Jung
 */

import java.util.*;
public class LLBag implements Bag {
    // inner class for the node
    private class Node {
        private Object item;
        private Node next;

        private Node(Object i, Node n) {
            item = i;
            next = n;
        }
    }

    // fields of the LLBag object
    private Node head;
    private int size;

    /*
     * constructs a LLBag object for a list that is initially empty
     */
    public LLBag() {
        head = null;
        size = 0;
    }

    /*
     * overriding add method
     * adds the specified item to the Bag.  Returns true on success
     * and false if there is no more room in the Bag.
     */
    public boolean add(Object item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }
        head = new Node(item, head);
        size ++;
        return true;
    }

    /*
     * overring remove method
     * removes one occurrence of the specified item (if any) from the
     * Bag.  Returns true on success and false if the specified item
     * (i.e., an object equal to item) is not in the Bag.
     */
    public boolean remove(Object item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }
        Node trav = head;
        while (trav.next != null) {
            if (trav.next.item.equals(item)) {
                trav.next = trav.next.next;
                size--;
                return true;
            }
            trav = trav.next;
        }
        return false;
    }


    /*
     * overriding contains method
     * returns true if the specified item is in the Bag, and false
     * otherwise.
     */
    public boolean contains(Object item) {
        if (item == null) {
            throw new IllegalArgumentException();
        }
        Node trav = head;
        while (trav != null) {
            if (trav.item.equals(item)) {
                return true;
            }
            trav = trav.next;
        }
        return false;

    }

    /*
     * overriding numItems method
     * returns the number of items in the Bag.
     */
    public int numItems() {
        return size;
    }

    /*
     * overriding grab method
     * grab - returns a reference to a randomly chosen in the Bag.
     */
    public Object grab() {
        if (size == 0) {
            throw new IllegalStateException("the bag is empty");
        }
        Node trav = head;
        int whichOne = (int)(Math.random() * numItems());
        for (int i = 0; i < whichOne; i++) {
            trav = trav.next;
        }
        return trav.item;
    }


    /*
     * overriding toArray method
     * toArray - return an array containing the current contents of the bag
     */
    public Object[] toArray() {
        Object[] copy = new Object[size];
        Node trav = head;

        for (int i = 0; i < size; i++) {
            copy[i] = trav.item;
            trav = trav.next;
        }
        
        return copy;

    }

    /*
     * toString - overriding default toString method from Object class
     */
    public String toString() {
        String str = "{";
        Node trav = head;

        while(trav != null) {
            str = str + trav.item;
            trav = trav.next;
            if (trav != null) {
                str += ", ";
            }
        }
        str = str + "}";
        return str;
    }



    /* Test the ArrayBag implementation. */
    public static void main(String[] args) {
        // Create a Scanner object for user input.
        Scanner scan = new Scanner(System.in);
        
        // Create an ArrayBag named bag1.
        System.out.print("number of items in bag 1: ");
        int numItems = scan.nextInt();
        LLBag bag1 = new LLBag();
        scan.nextLine();    // consume the rest of the line
        
        // Read in strings, add them to bag1, and print out bag1.
        String itemStr;        
        for (int i = 0; i < numItems; i++) {
            System.out.print("item " + i + ": ");
            itemStr = scan.nextLine();
            bag1.add(itemStr);
        }
        System.out.println("bag 1 = " + bag1);
        System.out.println();
        
        // Select a random item and print it.
        Object item = bag1.grab();
        System.out.println("grabbed " + item);
        System.out.println();
        
        // Iterate over the objects in bag1, printing them one per line.
        Object[] items = bag1.toArray();
        for (int i = 0; i < items.length; i++) {
            System.out.println(items[i]);
        }
        System.out.println();
        
        // Get an item to remove from bag1, remove it, and reprint the bag.
        System.out.print("item to remove: ");
        itemStr = scan.nextLine();
        if (bag1.contains(itemStr)) {
            bag1.remove(itemStr);
        }
        System.out.println("bag 1 = " + bag1);
        System.out.println();
        
        scan.close();
    }



    
}
