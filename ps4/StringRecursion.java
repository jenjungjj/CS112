/*
 * StringRecursion.java
 * 
 * Recursion and Strings
 */



 public class StringRecursion {

    /*
     * printLetters - uses recursion to print individual characters 
     * in the string str
     */
    public static void printLetters(String str) {
        if (str == null || str == "") {
            return;
        } else if (str.length() == 1) {
            System.out.print(str + ", ");
        } else {
            printLetters(str.substring(0,str.length()-1));
            System.out.print(str.charAt(str.length()-1) + ", ");
        }
    }

    /*
     * replace - uses recursion to return a string formed by 
     * replacing all oldChar to newChar
     */
    public static String replace(String str, char oldChar, char newChar) {
        if (str == null) {
            return null;
        } else if (str == "") {
            return "";
        } else if (str.length() == 1 && str.charAt(0) == oldChar) {
            return "" + newChar;
        }else if (str.length() == 1 && str.charAt(0) != oldChar) {
            return str;
        } else {
            if(str.charAt(0) == oldChar) {
                return newChar + replace(str.substring(1), oldChar, newChar);
                
            }
            return str.charAt(0) + replace(str.substring(1), oldChar, newChar);
        }
    }


    /*
     * findLast - uses recursion to find and return the index of the last occurence of 
     * the character ch in the string str
     */
    public static int findLast(char ch, String str) {
        if (str == null || str == "") {
            return -1;
        } 
        if (str.length() == 1 && str.charAt(0) == ch) {
            return 1;
        } else if (str.length() == 1 && str.charAt(0) != ch) {
            return -1;
        } else {
            if(str.charAt(0) == ch) {
                return 1 + findLast(ch, str.substring(0,str.length()-2)) ;
            }
            return findLast(ch, str.substring(0,str.length()-2));
        }
    }



    public static void main(String [] args) {
        printLetters("Rabbit");
        System.out.println();
        printLetters("I like to recurse!");
        System.out.println();
        System.out.println(replace("base case", 'e', 'y'));
        System.out.println(replace("base case", 'r', 'y') );
        System.out.println(findLast('r', "recurse"));
        System.out.println(findLast('p', "recurse"));

    }
 }