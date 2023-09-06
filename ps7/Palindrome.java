/*
 * Palindrome.java
 *
 * Computer Science 112
 *
 * Modifications and additions by:
 *     name: Youjung Jung
 *     username:
 */
   
public class Palindrome {
    /*
     * isPal: takes in a string parameter and determines if the string
     * reads the same in either direction without considering spaces, punctuations,
     * and cases of the letters. 
     * returns true if determined to be palindrome and false if otherwise.
     */
    public static boolean isPal(String s) {
        if ( s == null) {
            throw new  IllegalArgumentException();
        } else if (s.length() == 1 || s.length() == 0) {
            return true;
        }
        Queue<Character> sQueue = new ArrayQueue<Character>(s.length());
        ArrayStack<Character> sStack = new ArrayStack<Character> (s.length());
        for (int i = 0; i < s.length(); i++) {
            if((s.charAt(i) >= 65 && s.charAt(i) <= 90) ||
                (s.charAt(i) >= 97 && s.charAt(i) <= 122)) { 
                Character newS = s.charAt(i);
                sQueue.insert(Character.toLowerCase(newS));
                sStack.push(Character.toLowerCase(newS));
            }
        }
        while (!sStack.isEmpty()) {
            if (sQueue.remove() != sStack.pop()) {
                return false;
            }
        }
        return true;
        


    }
    
    public static void main(String[] args) {
        System.out.println("--- Testing method isPal ---");
        System.out.println();

        System.out.println("(0) Testing on \"A man, a plan, a canal, Panama!\"");
        try {
            boolean results = isPal("A man, a plan, a canal, Panama!");
            boolean expected = true;
            System.out.println("actual results:");
            System.out.println(results);
            System.out.println("expected results:");
            System.out.println(expected);
            System.out.print("MATCHES EXPECTED RESULTS?: ");
            System.out.println(results == expected);
        } catch (Exception e) {
            System.out.println("INCORRECTLY THREW AN EXCEPTION: " + e);
        }
        
        System.out.println();    // include a blank line between tests
        
        /*
         * Add five more unit tests that test a variety of different
         * cases. Follow the same format that we have used above.
         */
        System.out.println("(1) Testing on \'a\'");
        try {
            boolean results = isPal("a");
            boolean expected = true;
            System.out.println("actual results: ");
            System.out.println(results);
            System.out.println("expected results: ");
            System.out.println(expected);
            System.out.print("MATCHES EXPECTED RESULTS?: ");
            System.out.println(results == expected);
        } catch (IllegalArgumentException i) {
            System.out.println("INCORRECTLY THREW AN EXCEPTION: " + i);
        }
        System.out.println();

        System.out.println("(2) Testing on \"I cannot wait til sUmMer~!\"");
        try {
            boolean results = isPal("I cannot wait til sUmMer~!");
            boolean expected = false;
            System.out.println("actual results: ");
            System.out.println(results);
            System.out.println("expected results: ");
            System.out.println(expected);
            System.out.print("MATCHES EXPECTED RESULTS?: ");
            System.out.println(results == expected);
        } catch (Exception e) {
            System.out.println("INCORRECTLY THREW AN EXCEPTION: " + e);
        }
        System.out.println();

        System.out.println("(3) Testing on never odd or even");
        try {
            String s = "never odd or even";
            boolean results = isPal(s);
            boolean expected = true;
            System.out.println("actual results: ");
            System.out.println(results);
            System.out.println("expected results: ");
            System.out.println(expected);
            System.out.print("MATCHES EXPECTED RESULTS?: ");
            System.out.println(results == expected);
        } catch (Exception e) {
            System.out.println("INCORRECTLY THREW AN EXCEPTION: " + e);
        }
        System.out.println();

        System.out.println("(4) Testing on wAs iT A cAT i sAW?");
        try {
            String s = "wAs iT A cAT i sAW?";
            boolean results = isPal(s);
            boolean expected = true;
            System.out.println("actual results: ");
            System.out.println(results);
            System.out.println("expected results: ");
            System.out.println(expected);
            System.out.print("MATCHES EXPECTED RESULTS?: ");
            System.out.println(results == expected);
        } catch (Exception e) {
            System.out.println("INCORRECTLY THREW AN EXCEPTION: " + e);
        }
        System.out.println();

        System.out.println("(5) Testing on \"\"");
        try {
            String s = "";
            boolean results = isPal(s);
            boolean expected = true;
            System.out.println("actual results: ");
            System.out.println(results);
            System.out.println("expected results: ");
            System.out.println(expected);
            System.out.print("MATCHES EXPECTED RESULTS?: ");
            System.out.println(results == expected);
        } catch (Exception e) {
            System.out.println("INCORRECTLY THREW AN EXCEPTION: " + e);
        }
        System.out.println();

        System.out.println("(6) Testing on null");
        try {
            boolean results = isPal(null);
        } catch (IllegalArgumentException i) {
            System.out.println("CORRECTLY THREW AN EXCEPTION: " + i);
        }
        System.out.println();




    }
}