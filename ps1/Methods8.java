/*
 * Methods8.java
 * 
 * Code added by: Youjung Jung , jenjung@bu.edu
 *
 * Practice with static methods, part II
 */

public class Methods8 {
    /*
     * 1) printDiag - takes string s then diagonlly prints each character 
     *    on a separate line.
     */
    public static void printDiag(String s) {
       for ( int r = 0; r < s.length(); r++) {
           for (int c = 0; c < s.length(); c++) {
               if ( r == c) {
                   System.out.print(s.substring(r, r+1));
               } else {
                   System.out.print(" ");
               }
           }
           System.out.println();
       }
    }
    /*
     * 2) lastN - takes string s and integer n and returns a string
     *    containing n last characters of s. 
     */
    public static String lastN(String s, int n) {
        if ( n > s.length()) {
            return s;
        } else {
            return s.substring(s.length() - n);
        }
    }
    /*
     * 3) remSub - takes strings s1 and s2 and returns the string 
     *    in which the first occurence of s2 has been removed from s1. 
     */
    public static String remSub(String s1, String s2) {
        int idx = s1.indexOf(s2);
        if (idx == -1) {
            return s1;
        } else {
            return s1.substring(0,idx) + s1.substring(idx + s2.length());
        }
    }
    /*
     * 4) interleave - takes two strings s1 and s2 and returns the string formed
     *    by alternating characters of the two strings. 
     */
    public static String interleave(String s1, String s2) {
        String result = "";
        if (s1.length() <= s2.length()) {
            for (int i = 0; i < s1.length(); i++) {
                result = result + s1.charAt(i) + s2.charAt(i);
            }
            result += s2.substring(s1.length());
        } else {
            for ( int i = 0; i < s2.length(); i++) {
                result = result + s1.charAt(i) + s2.charAt(i);
            }
            result += s1.substring(s2.length());
        }
        return result;
    }











    public static void main(String[] args) {
        /* Sample test call */
        printDiag("method");
        System.out.println(lastN("programming",5 ));
        System.out.println(lastN("programming", 1));
        System.out.println(lastN("face", 7));
        System.out.println(remSub("ding-a-ling", "ing"));
        System.out.println(remSub("variable", "var"));
        System.out.println(interleave("leaving", "NOW"));
        System.out.println(interleave("", "hello"));
    }
}