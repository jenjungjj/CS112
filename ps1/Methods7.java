/*
 * Methods7.java
 * 
 * Code added by: Youjung Jung , jenjung@bu.edu
 *
 * Practice with static methods, part I
 */

public class Methods7 {
    /*
     * 0) printVertical - takes a string s and prints the characters of 
     *    the string vertically -- with one character per line.
     */
    public static void printVertical(String s) {
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            System.out.println(c);
        }
    }

    /*
     * 1) printEveryOther - takes a string s and prints every other 
     *    character of the string. 
     */
    public static void printEveryOther(String s) {
        for ( int i = 0; i < s.length(); i = i + 2) {
            char c = s.charAt(i);
            System.out.print(c);
        }
        System.out.println();
    }

    /*
     * 2) longerLen - takes two strings m and n and returns the length of the 
     *    longer string -- if the two have the same length, it should return that length.
     */
    public static int longerLen(String m, String n) {
        int len;
        if (m.length() >= n.length()) {
            len = m.length();
        } else {
            len = n.length();
        }
        return len;
    }

    /*
     * 3) secondIndex - takes two parameters, string s and character c, and returns the index
     *    of the second occurence of c in s ( if any). If there aren't at least 2 occurences of 
     *    c in s then it should return -1. 
     */
    public static int secondIndex(String s, char c) {
        int i;
        i = s.indexOf(c);
        if (i < 0) {
            return i;
        }
        i += s.substring(i +1).indexOf(c);
        if ( i < 2) {
            return i;
        } else {
            return i + 1;
        }
    }

    public static void main(String[] args) {
        /* Sample test call */
        printVertical("method");  
        printEveryOther("method");  
        int len = longerLen("bye", "hello");
        System.out.println("the longer length is: " + len);    

    }
}
