/*
 * ChainedHashTable.java
 *
 * Computer Science 112, Boston University
 * 
 * Modifications and additions by:
 *     name: Youjung Jung
 *     email: jenjung@bu.edu
 */

import java.util.*;     // to allow for the use of Arrays.toString() in testing

/*
 * A class that implements a hash table using separate chaining.
 */
public class ChainedHashTable implements HashTable {
    /* 
     * Private inner class for a node in a linked list
     * for a given position of the hash table
     */
    private class Node {
        private Object key;
        private LLQueue<Object> values;
        private Node next;
        
        private Node(Object key, Object value) {
            this.key = key;
            values = new LLQueue<Object>();
            values.insert(value);
            next = null;
        }
    }
    
    private Node[] table;      // the hash table itself
    private int numKeys;       // the total number of keys in the table
        
    /* hash function */
    public int h1(Object key) {
        int h1 = key.hashCode() % table.length;
        if (h1 < 0) {
            h1 += table.length;
        }
        return h1;
    }
    
    /*** Add your constructor here ***/
    public ChainedHashTable(int size) {
        if (size <= 0) {
            throw new IllegalArgumentException();
        }
        this.table = new Node[size];
        this.numKeys = 0;
    }

    
    
    /*
     * insert - insert the specified (key, value) pair in the hash table.
     * Returns true if the pair can be added and false if there is overflow.
     */
    public boolean insert(Object key, Object value) {
        /** Replace the following line with your implementation. **/
        if (key == null) {
            throw new IllegalArgumentException("key must be non-null");
        }     
        int i = h1(key);
        Node newNode = new Node(key,value); 
        Node trav = table[i];
        while (trav != null) { // doesn't increase numKeys for duplicates
            if(trav.key.equals(newNode.key)) {
                return true;
            }
            trav = trav.next;
        }

        if(table[i] == null) {
            table[i] = newNode;
        } else {
            newNode.next = table[i];
            table[i] = newNode;
        }
        numKeys++;
        return true;
    }
    
    /*
     * search - search for the specified key and return the
     * associated collection of values, or null if the key 
     * is not in the table
     */
    public Queue<Object> search(Object key) {
        /** Replace the following line with your implementation. **/
        if (key == null) {
            throw new IllegalArgumentException("key must be non-null");
        }
        int i = h1(key);
        if (i == -1 || table[i] == null) {
            return null;
        } else {
            return table[i].values;
        }
    }
    
    /* 
     * remove - remove from the table the entry for the specified key
     * and return the associated collection of values, or null if the key 
     * is not in the table
     */
    public Queue<Object> remove(Object key) {
        /** Replace the following line with your implementation. **/
        if (key == null) {
            throw new IllegalArgumentException("key must be non-null");
        }
        int i = h1(key);
        if (i == -1 || table[i] == null) {
            return null;
        }
        Node first = table[i];
        Node trav = null;
        LLQueue<Object> removedVals = table[i].values;
        while (first != null) {
            if (first.key.equals(key)) {
                break;
            } 
            trav = first;
            first = first.next;
        }
        if (first == null) {
            return removedVals;
        }
        numKeys--;
        if (trav != null) {
            trav.next = first.next;
        } else {
            table[i] = first.next;
        }
        return removedVals;
    }
    
    
    /*** Add the other required methods here ***/
    /*
     * getNumKeys()- accessor method to get the number of keys in the hash table
     */
    public int getNumKeys() {
        return this.numKeys;
    }


    /*
     * load() - returns the load factor of the table: the number of keys in the table
     * divided by the size of the table
     */
    public double load() {
        return (double) getNumKeys()/table.length;
    }

    /*
     * getAllKeys() - returns an array of type Object containing all keys in the hash table
     */
    public Object[] getAllKeys() {
        Object[] keyArray = new Object[getNumKeys()];
        int idx = 0;
        for(int i = 0; i < table.length; i++) {
            Node trav = table[i];
            while (trav != null) {
                keyArray[idx] = trav.key;
                idx++;
                trav = trav.next;
            }
        }
        return keyArray;
    }

    /*
     * resize()- takes an integer representing the new size and grows the table 
     * to that new size
     */
    public void resize(int size) {
        ChainedHashTable newTable = new ChainedHashTable(size);
        if (size < table.length) {
            throw new IllegalArgumentException();
        } else if (size == table.length) {
            return;
        }
        for (int i = 0; i < table.length; i++)  {
            Node trav = table[i];
            while (trav != null) {
                //newTable.insert(trav.key, trav.values);
                newTable.insert(trav.key, trav.values);
                trav = trav.next;
            }
        }
        table = newTable.table;
    }
    
    
    
    /*
     * toString - returns a string representation of this ChainedHashTable
     * object. *** You should NOT change this method. ***
     */
    public String toString() {
        String s = "[";
        
        for (int i = 0; i < table.length; i++) {
            if (table[i] == null) {
                s += "null";
            } else {
                String keys = "{";
                Node trav = table[i];
                while (trav != null) {
                    keys += trav.key;
                    if (trav.next != null) {
                        keys += "; ";
                    }
                    trav = trav.next;
                }
                keys += "}";
                s += keys;
            }
        
            if (i < table.length - 1) {
                s += ", ";
            }
        }       
        
        s += "]";
        return s;
    }

    public static void main(String[] args) {
        /** Add your unit tests here **/
        System.out.println("(1) Testing on insert method part 1");
        try {
            ChainedHashTable table = new ChainedHashTable(5);
            String s = "[{apple; howdy}, null, null, {goodbye}, null]";
            table.insert("howdy", 15);
            table.insert("goodbye", 10);
            table.insert("apple", 5);
            table.insert("howdy", 20);
            System.out.println("actual results:");
            System.out.println(table.toString());   
            System.out.println("expected results:");
            System.out.println(s);
            System.out.println("Matches expected results?: ");
            System.out.println(table.toString().equals(s));       
        } catch (Exception e) {
            System.out.println("INCORRECTLY THREW AN EXCEPTION: " + e);
        }
        System.out.println();


        System.out.println("(2) Testing on insert method part 2");
        try {
            ChainedHashTable table = new ChainedHashTable(5);
            String s = "[{howdy; apple}, {kiwi}, null, {strawberry}, null]";
            table.insert("kiwi", 15);
            table.insert("strawberry", 10);
            table.insert("apple", 5);
            table.insert("howdy", 20);
            System.out.println("actual results:");
            System.out.println(table.toString());   
            System.out.println("expected results:");
            System.out.println(s);
            System.out.println("Matches expected results?: ");
            System.out.println(table.toString().equals(s));       
        } catch (Exception e) {
            System.out.println("INCORRECTLY THREW AN EXCEPTION: " + e);
        }
        System.out.println();

        System.out.println("(3) Testing on remove method part 1");
        try {
            ChainedHashTable table = new ChainedHashTable(5);
            String s = "[{apple; howdy}, null, null, null, null]";
            table.insert("howdy", 15);
            table.insert("goodbye", 10);
            table.insert("apple", 5);
            table.insert("howdy", 20);
            table.remove("goodbye");
            System.out.println("actual results:");
            System.out.println(table.toString());   
            System.out.println("expected results:");
            System.out.println(s);
            System.out.println("Matches expected results?: ");
            System.out.println(table.toString().equals(s));       
        } catch (Exception e) {
            System.out.println("INCORRECTLY THREW AN EXCEPTION: " + e);
        }
        System.out.println();

        System.out.println("(4) Testing on remove method part 2");
        try {
            ChainedHashTable table = new ChainedHashTable(5);
            String s = "[{apple}, {kiwi}, null, null, null]";
            table.insert("kiwi", 15);
            table.insert("strawberry", 10);
            table.insert("apple", 5);
            table.insert("howdy", 20);
            table.remove("howdy");
            table.remove("strawberry");
            System.out.println("actual results:");
            System.out.println(table.toString());   
            System.out.println("expected results:");
            System.out.println(s);
            System.out.println("Matches expected results?: ");
            System.out.println(table.toString().equals(s));       
        } catch (Exception e) {
            System.out.println("INCORRECTLY THREW AN EXCEPTION: " + e);
        }
        System.out.println();

        System.out.println("(5) Testing on search method part 1");
        try {
            ChainedHashTable table = new ChainedHashTable(5);
            String s = "{10}";
            table.insert("howdy", 15);
            table.insert("goodbye", 10);
            table.insert("apple", 5);
            table.insert("howdy", 20);
            System.out.println("actual results:");
            System.out.println(table.search("goodbye").toString());   
            System.out.println("expected results:");
            System.out.println(s);
            System.out.println("Matches expected results?: ");
            System.out.println(table.search("goodbye").toString().equals(s));       
        } catch (Exception e) {
            System.out.println("INCORRECTLY THREW AN EXCEPTION: " + e);
        }
        System.out.println();

        System.out.println("(6) Testing on search method part 2");
        try {
            ChainedHashTable table = new ChainedHashTable(5);
            String s = "{5}";
            table.insert("howdy", 15);
            table.insert("goodbye", 10);
            table.insert("apple", 5);
            table.insert("howdy", 20);
            System.out.println("actual results:");
            System.out.println(table.search("apple").toString());   
            System.out.println("expected results:");
            System.out.println(s);
            System.out.println("Matches expected results?: ");
            System.out.println(table.search("howdy").toString().equals(s));       
        } catch (Exception e) {
            System.out.println("INCORRECTLY THREW AN EXCEPTION: " + e);
        }
        System.out.println();

        System.out.println("(7) Testing on getNumKeys method part 1");
        try {
            ChainedHashTable table = new ChainedHashTable(5);
            int expected = 3;
            table.insert("howdy", 15);
            table.insert("goodbye", 10);
            table.insert("apple", 5);
            System.out.println("actual results:");
            System.out.println(table.getNumKeys());   
            System.out.println("expected results:");
            System.out.println(expected);
            System.out.println("Matches expected results?: ");
            System.out.println(table.getNumKeys() == expected);       
        } catch (Exception e) {
            System.out.println("INCORRECTLY THREW AN EXCEPTION: " + e);
        }
        System.out.println();

        System.out.println("(8) Testing on getNumKeys method part 2");
        try {
            ChainedHashTable table = new ChainedHashTable(5);
            int expected = 3;
            table.insert("howdy", 15);
            table.insert("goodbye", 10);
            table.insert("apple", 5);
            table.insert("howdy", 25);
            System.out.println("actual results:");
            System.out.println(table.getNumKeys());   
            System.out.println("expected results:");
            System.out.println(expected);
            System.out.println("Matches expected results?: ");
            System.out.println(table.getNumKeys() == expected);       
        } catch (Exception e) {
            System.out.println("INCORRECTLY THREW AN EXCEPTION: " + e);
        }
        System.out.println();

        System.out.println("(9) Testing on load method part 1");
        try {
            ChainedHashTable table = new ChainedHashTable(5);
            double expected = 0.6;
            table.insert("howdy", 15);
            table.insert("goodbye", 10);
            table.insert("apple", 5);
            System.out.println("actual results:");
            System.out.println(table.load());   
            System.out.println("expected results:");
            System.out.println(expected);
            System.out.println("Matches expected results?: ");
            System.out.println(table.load() == expected);       
        } catch (Exception e) {
            System.out.println("INCORRECTLY THREW AN EXCEPTION: " + e);
        }
        System.out.println();

        System.out.println("(9) Testing on load method part 2");
        try {
            ChainedHashTable table = new ChainedHashTable(5);
            double expected = 0.8;
            table.insert("howdy", 15);
            table.insert("goodbye", 10);
            table.insert("apple", 5);
            table.insert("pear", 6);
            System.out.println("actual results:");
            System.out.println(table.load());   
            System.out.println("expected results:");
            System.out.println(expected);
            System.out.println("Matches expected results?: ");
            System.out.println(table.load() == expected);       
        } catch (Exception e) {
            System.out.println("INCORRECTLY THREW AN EXCEPTION: " + e);
        }
        System.out.println();


        System.out.println("(10) Testing on getAllKeys method part 1");
        try {
            ChainedHashTable table = new ChainedHashTable(5);
            String expected = "[apple, howdy, goodbye, pear]";
            table.insert("howdy", 15);
            table.insert("goodbye", 10);
            table.insert("apple", 5);
            table.insert("pear", 6);
            System.out.println("actual results:");
            Object[] keys = table.getAllKeys();
            System.out.println(Arrays.toString(keys));   
            System.out.println("expected results:");
            System.out.println(expected);
            System.out.println("Matches expected results?: ");
            System.out.println(Arrays.toString(keys).equals(expected));       
        } catch (Exception e) {
            System.out.println("INCORRECTLY THREW AN EXCEPTION: " + e);
        }
        System.out.println();

        System.out.println("(11) Testing on getAllKeys method part 2");
        try {
            ChainedHashTable table = new ChainedHashTable(5);
            String expected = "[apple, howdy, goodbye]";
            table.insert("howdy", 15);
            table.insert("goodbye", 10);
            table.insert("apple", 5);
            table.insert("howdy", 25);
            System.out.println("actual results:");
            Object[] keys = table.getAllKeys();
            System.out.println(Arrays.toString(keys));   
            System.out.println("expected results:");
            System.out.println(expected);
            System.out.println("Matches expected results?: ");
            System.out.println(Arrays.toString(keys).equals(expected));       
        } catch (Exception e) {
            System.out.println("INCORRECTLY THREW AN EXCEPTION: " + e);
        }
        System.out.println();

        System.out.println("(12) Testing on resize method part 1");
        try {
            ChainedHashTable table = new ChainedHashTable(5);
            String expected = "[null, {apple}, null, null, null, {howdy}, {goodbye}]";
            table.insert("howdy", 15);
            table.insert("goodbye", 10);
            table.insert("apple", 5);
            System.out.println("actual results:");
            table.resize(7);
            System.out.println(table.toString());   
            System.out.println("expected results:");
            System.out.println(expected);
            System.out.println("Matches expected results?: ");
            System.out.println(table.toString().equals(expected));       
        } catch (Exception e) {
            System.out.println("INCORRECTLY THREW AN EXCEPTION: " + e);
        }
        System.out.println();

        System.out.println("(13) Testing on resize method part 2");
        try {
            ChainedHashTable table = new ChainedHashTable(5);
            String expected = "[null, null, {cake}, null, {food; kiwi}, {banana}]";
            table.insert("cake", 5);
            table.insert("banana", 20);
            table.insert("food", 25);
            table.insert("kiwi", 10);
            System.out.println("actual results:");
            table.resize(6);
            System.out.println(table.toString());   
            System.out.println("expected results:");
            System.out.println(expected);
            System.out.println("Matches expected results?: ");
            System.out.println(table.toString().equals(expected));       
        } catch (Exception e) {
            System.out.println("INCORRECTLY THREW AN EXCEPTION: " + e);
        }
        System.out.println();
    }
}
