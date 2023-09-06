/* 
 * ArrayBag.java
 *
 * A blueprint class for objects that represent a *bag* of other objects --
 * i.e., a collection of items in which the items do not have a position.
 * 
 * Computer Science 112
 *
 * modified by: Youjung Jung, jenjung@bu.edu
 */

import java.util.*;

/**
 * An implementation of a bag data structure using an array.
 */
public class ArrayBag {
    /** 
     * The array used to store the items in the bag.
     */
    private Object[] items;
    
    /** 
     * The number of items in the bag.
     */
    private int numItems;
    
    public static final int DEFAULT_MAX_SIZE = 50;
    
    /**
     * Constructor with no parameters - creates a new, empty ArrayBag with 
     * the default maximum size.
     */
    public ArrayBag() {
        this.items = new Object[DEFAULT_MAX_SIZE];
        this.numItems = 0;
    }
    
    /** 
     * A constructor that creates a new, empty ArrayBag with the specified
     * maximum size.
     */
    public ArrayBag(int maxSize) {
        if (maxSize <= 0) {
            throw new IllegalArgumentException("maxSize must be > 0");
        }
        this.items = new Object[maxSize];
        this.numItems = 0;
    }
    
    /**
     * numItems - accessor method that returns the number of items 
     * in this ArrayBag.
     */
    public int numItems() {
        return this.numItems;
    }
    
    /** 
     * add - adds the specified item to this ArrayBag. Returns true if there 
     * is room to add it, and false otherwise.
     * Throws an IllegalArgumentException if the item is null.
     */
    public boolean add(Object item) {
        if (item == null) {
            throw new IllegalArgumentException("item must be non-null");
        } else if (this.numItems == this.items.length) {
            return false;    // no more room!
        } else {
            this.items[this.numItems] = item;
            this.numItems++;
            return true;
        }
    }
    
    /** 
     * remove - removes one occurrence of the specified item (if any)
     * from this ArrayBag.  Returns true on success and false if the
     * specified item (i.e., an object equal to item) is not in this ArrayBag.
     */
    public boolean remove(Object item) {
        for (int i = 0; i < this.numItems; i++) {
            if (this.items[i].equals(item)) {
                // Shift the remaining items left by one.
                for (int j = i; j < this.numItems - 1; j++) {
                    this.items[j] = this.items[j + 1];
                }
                this.items[this.numItems - 1] = null;
                
                this.numItems--;
                return true;
            }
        }
        
        return false;  // item not found
    }
    
    /**
     * contains - returns true if the specified item is in the Bag, and
     * false otherwise.
     */
    public boolean contains(Object item) {
        for (int i = 0; i < this.numItems; i++) {
            if (this.items[i].equals(item)) {
                return true;
            }
        }
        
        return false;
    }
    
    /**
     * grab - returns a reference to a randomly chosen item in this ArrayBag.
     */
    public Object grab() {
        if (this.numItems == 0) {
            throw new IllegalStateException("the bag is empty");
        }
        
        int whichOne = (int)(Math.random() * this.numItems);
        return this.items[whichOne];
    }
    
    /**
     * toArray - return an array containing the current contents of the bag
     */
    public Object[] toArray() {
        Object[] copy = new Object[this.numItems];
        
        for (int i = 0; i < this.numItems; i++) {
            copy[i] = this.items[i];
        }
        
        return copy;
    }
    
    /**
     * toString - converts this ArrayBag into a string that can be printed.
     * Overrides the version of this method inherited from the Object class.
     */
    public String toString() {
        String str = "{";
        
        for (int i = 0; i < this.numItems; i++) {
            str = str + this.items[i];
            if (i != this.numItems - 1) {
                str += ", ";
            }
        }
        
        str = str + "}";
        return str;
    }

    /*
     * capacity - returns the maximum number of items 
     * that the called ArrayBag can hold
     */
    public int capacity() {
        return this.items.length;
    }

    /*
     * isEmpty - returns true if the arraybag is empty
     */
    public boolean isEmpty() {
        if (this.numItems() == 0) {
            return true;
        } else {
            return false;
        }
    }

    /* 
     * shrink - decreases the capacity of the bag by the given parameter
     */
    public boolean shrink(int decrease) {
        if (decrease < 0) {
            throw new IllegalArgumentException();
        } else if (decrease == 0) {
            return true;
        } else if ((capacity() - decrease) < numItems()) {
            return false;
        } else {
            Object[] items2 = new Object[items.length - decrease];
            for(int i = 0; i < numItems; i++) {
                items2[i] = items[i];
            }
            items = items2;
            return true;
        }
    }

    /* 
     * addItems - adds the items found in the parameter to the called ArrayBag
     */
    public boolean addItems(ArrayBag other) {
        if (other.isEmpty() == true) {
            return true;
        } else if (other.equals(null)) {
            throw new IllegalArgumentException();
        } else if ((capacity() - numItems()) < other.numItems()) {
            return false;
        } else {
            Object[] otherArr = other.toArray();
            for (int i = 0; i < otherArr.length; i++) {
                add(otherArr[i]);
            }
            return true;
        }
    } 

    /*
     * unionWith - creates and returns an arrayBag containing one occurrence of any item
     * found in either the called object or the parameter other.
     */
    public ArrayBag unionWith(ArrayBag other) {
        if (other.equals(null)) {
            throw new IllegalArgumentException();
        } else if (this.numItems() == 0 && other.numItems() == 0) {
            return new ArrayBag();
        }
        ArrayBag union = new ArrayBag(capacity() + other.capacity());
        Object[] thisArr = this.toArray();
        Object[] otherArr = other.toArray();
        for(int i = 0; i < thisArr.length; i++) {
            if (union.contains(thisArr[i]) == false) {
                union.add(thisArr[i]);
            }
        }
        for (int i = 0; i < otherArr.length; i++) {
            if (union.contains(otherArr[i])== false) {
                union.add(otherArr[i]);
            }
        }
        return union;
    }
    
    /* Test the ArrayBag implementation. */
    public static void main(String[] args) {
        /* testing codes */
        ArrayBag b1 = new ArrayBag(10);
        System.out.println(b1.capacity());
        ArrayBag b2 = new ArrayBag(5);
        System.out.println(b2.isEmpty());
        b2.add("hello");
        System.out.println(b2.isEmpty()); 
        ArrayBag b3 = new ArrayBag(8);
        b3.add("hello");
        b3.add("world");
        System.out.println(b3);
        System.out.println("capacity before: " + b3.capacity());

        b3.shrink(5);
        System.out.println(b3);
        System.out.println("capacity after: " + b3.capacity());  

        ArrayBag b4 = new ArrayBag(15);
        String[] words1 = {"hello", "you", "how", "are", "you", "today?"};
        for (String w : words1) {
            b4.add(w);
        }        
        System.out.println(b4);

        ArrayBag b5 = new ArrayBag(10);
        String[] words2 = {"not", "bad", "how", "are", "you"};
        for (String w : words2) {
            b5.add(w);
        }        
        System.out.println(b5);

        System.out.println(b4.addItems(b5));
        System.out.println(b4);
        System.out.println(b5);

        ArrayBag b6 = new ArrayBag(15);
        String[] letters1 = {"a", "a", "b", "d", "f", "f", "f", "g"};
        for (String ltr: letters1) {
            b6.add(ltr);
        }
        System.out.println(b6);

        ArrayBag b7 = new ArrayBag(10);
        String[] letters2 = {"a", "b", "c", "d", "d", "e", "f"};
        for (String ltr: letters2) {
            b7.add(ltr);
        }
        System.out.println(b7);

        ArrayBag b8 = b6.unionWith(b7);
        System.out.println(b8);
        System.out.println(b8.numItems());
        System.out.println(b8.capacity());
        System.out.println(b6);   // make sure original bags are unchanged
        System.out.println(b7);
        /* testcodes for PS 3 ends here */


        
        // Create a Scanner object for user input.
        Scanner scan = new Scanner(System.in);
        
        // Create an ArrayBag named bag1.
        System.out.print("size of bag 1: ");
        int size = scan.nextInt();
        ArrayBag bag1 = new ArrayBag(size);
        scan.nextLine();    // consume the rest of the line
        
        // Read in strings, add them to bag1, and print out bag1.
        String itemStr;        
        for (int i = 0; i < size; i++) {
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
        
        // Iterate over the objects in bag1, printing them one per
        // line.
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
    }
}
