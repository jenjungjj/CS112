/* 
 * BigInt.java
 *
 * A class for objects that represent non-negative integers of 
 * up to 20 digits.
 */

public class BigInt  {
    // the maximum number of digits in a BigInt -- and thus the length
    // of the digits array
    private static final int SIZE = 20;
    
    // the array of digits for this BigInt object
    private int[] digits;
    
    // the number of significant digits in this BigInt object
    private int numSigDigits;

    /*
     * Default, no-argument constructor -- creates a BigInt that 
     * represents the number 0.
     */
    public BigInt() {
        this.digits = new int[SIZE];
        this.numSigDigits = 1;  // 0 has one sig. digit--the rightmost 0!
    }

    /*
     * isValid - private helper method that checks if the parameter 
     * is a valid array
     */
    private boolean isValid(int[] arr) {
        if(arr.length < 0 || arr.length > SIZE || arr == null) {
            return false;
        } 
        for(int i = 0; i < arr.length; i++) {
            if(arr[i] < 0 || arr[i] >= 10) {
                return false;
            }
        }
        return true;
    }

    /*
     * Constructor - uses the contents of the passed array as the basis
     * of the new BigInt object
     */
    public BigInt(int[] arr) {
        
        if (isValid(arr) == false) {
            throw new IllegalArgumentException();
        }
        
        
        int idx = 0;
        for(int j = 0; j < arr.length; j++) {
            if(arr[j] == 0) {
                idx++;
            } else {
                break;
            }
        }
        this.numSigDigits = SIZE - idx;
        this.digits = new int[SIZE];
        for (int i = 0; i < SIZE - this.numSigDigits; i++) {
            this.digits[i] = 0;
        }
        int index = arr.length -1;
        for (int i = SIZE -1; i >= SIZE - arr.length; i--) {
            this.digits[i] = arr[index];
            index--;
        }
    }

    /*
     * getNumSigDigits - accessor method that returns the value of numSigDigits
     */
    public int getNumSigDigits() {
        return this.numSigDigits;
    }

    /*
     * toString - overrides the method from Object class
     */
    public String toString() {
        String val = "";
        if (this.getNumSigDigits() == 0) {
            val += 0;
        }
        for(int i = SIZE -this.getNumSigDigits(); i < SIZE; i++) {
            val += this.digits[i];
        }
        return val;
    }

    /*
     * constructor that creates a BigInt object representing the integer n
     */
    public BigInt(int n) {
        if (n < 0) {
            throw new IllegalArgumentException();
        }
        this.digits = new int[SIZE];
        int place = 0;
        for(int j = n; j > 0; j/= 10) {
            place ++;
        }
        if (place > SIZE) {
            throw new ArithmeticException();
        }
        this.numSigDigits = place;
        

        for(int i = SIZE -1 ; i >= SIZE - place; i--) {
            this.digits[i] = n % 10;
            n = n / 10;
        }
    }

   /*
    * isZero - helper method that checks if the parameter object represents 0
    */
    private boolean isZero(BigInt bigInt) {
        if (bigInt.getNumSigDigits() == 0) {
            return true;
        }
        return false;
    }

    /*
     * compareTo - compares the called object to the parameter and returns 
     * -1 if the called object is less than the integer other
     * 0 if the integer equals to the called object
     * 1 if the integer represented by the called object is greater than the integer other
     */
    public int compareTo(BigInt other) {
        if (other == null) {
            throw new IllegalArgumentException();
        } else if (this.getNumSigDigits() > other.getNumSigDigits()) {
            return 1;
        } else if (this.getNumSigDigits() < other.getNumSigDigits()) {
            return -1;
        }
        for (int i = 0; i < SIZE; i++) {
            if (this.digits[i] > other.digits[i]) {
                return 1;
            } else if (this.digits[i] < other.digits[i]) {
                return -1;
            }
        }
        return 0;
    }

    /*
     * add - returns a new BigInt object for the sum of the integers 
     * represented by the called object and other 
     */
    public BigInt add(BigInt other) {
        if (other == null) {
            throw new IllegalArgumentException();
        }
        int[] summed = new int[SIZE];
        for (int i = SIZE -1; i > 0; i--) {
            if ((summed[i] + this.digits[i] + other.digits[i]) < 10) {
                summed[i] += this.digits[i] + other.digits[i];
            } else if ((summed[i] + this.digits[i] + other.digits[i]) == 10) {
                summed[i] = 0;
                summed[i-1] = 1;
            } else {
                summed[i] += (this.digits[i] + other.digits[i]) % 10;
                summed[i -1] += (this.digits[i] + other.digits[i]) / 10;
            }
        }
        if ((this.digits[0] + other.digits[0] + summed[0]) >= 10) {
            throw new ArithmeticException();
        } else {
            summed[0] += this.digits[0] + other.digits[0];
        }
        BigInt addedBig = new BigInt(summed);
        return addedBig;
    }
    /*
     * mul - returns a new BigInt object for the product of the integers
     * represented by the called object and the other.
     */
    public BigInt mul(BigInt other) {
        if (other == null) {
            throw new IllegalArgumentException();
        } 
        int [] multiplied = new int[SIZE];
        for (int i = SIZE - 1; i > 0; i--) {
            int mulIdx = i;
            for(int j = SIZE -1; j >= SIZE -1 -i; j--) {
                if(this.digits[j] * other.digits[i] + multiplied[mulIdx] < 10) {
                    multiplied[mulIdx] += this.digits[j] * other.digits[i];
                } else {
                    multiplied[mulIdx] += (this.digits[j] * other.digits[i]) % 10;
                    multiplied[mulIdx -1] += (this.digits[j] * other.digits[i]) / 10;
                }
                mulIdx--;    
            }
        }
        if (this.digits[1] * other.digits[1] + multiplied[1] > 9) {
            multiplied[1] += this.digits[1] * other.digits[1] % 10;
            multiplied[0] += this.digits[1] * other.digits[1] / 10;
        } else {
            multiplied[1] += this.digits[1] * other.digits[1];
        }
        if ((this.digits[0] * other.digits[0] + multiplied[0]) > 9) { 
            throw new ArithmeticException();
        } else {
            multiplied[0] += this.digits[0] * other.digits[0];
        }
        BigInt mulObj = new BigInt(multiplied);
        return mulObj;
    }
    public static void main(String [] args) {
        System.out.println("Unit tests for the BigInt class.");
        System.out.println();

        /* 
         * You should uncomment and run each test--one at a time--
         * after you build the corresponding methods of the class.
         */
        
        System.out.println("Test 1: result should be 7");
        int[] a1 = { 0,0,0,0,0,0,0,0,0,0,0,0,0,1,2,3,4,5,6,7 };
        BigInt b1 = new BigInt(a1);
        System.out.println(b1.getNumSigDigits());
        System.out.println();
        
        System.out.println("Test 2: result should be 1234567");
        b1 = new BigInt(a1);
        System.out.println(b1);
        System.out.println();
        
        System.out.println("Test 3: result should be 0");
        int[] a2 = { 0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0 };
        BigInt b2 = new BigInt(a2);
        System.out.println(b2);
        System.out.println();
        
        System.out.println("Test 4: should throw an IllegalArgumentException");
        try {
            int[] a3 = { 0,0,0,0,23,0,0,0,0,0,0,0,0,0,0,0,0,0,0,0 };
            BigInt b3 = new BigInt(a3);
            System.out.println("Test failed.");
        } catch (IllegalArgumentException e) {
            System.out.println("Test passed.");
        } catch (Exception e) {
            System.out.println("Test failed: threw wrong type of exception.");
        }
        System.out.println();
        

        System.out.println("Test 5: result should be 1234567");
        b1 = new BigInt(1234567);
        System.out.println(b1);
        System.out.println();

        System.out.println("Test 6: result should be 0");
        b2 = new BigInt(0);
        System.out.println(b2);
        System.out.println();

        System.out.println("Test 7: should throw an IllegalArgumentException");
        try {
            BigInt b3 = new BigInt(-4);
            System.out.println("Test failed.");
        } catch (IllegalArgumentException e) {
            System.out.println("Test passed.");
        } catch (Exception e) {
            System.out.println("Test failed: threw wrong type of exception.");
        }
        System.out.println();
        
        

        System.out.println("Test 8: result should be 0");
        b1 = new BigInt(12375);
        b2 = new BigInt(12375);
        System.out.println(b1.compareTo(b2));
        System.out.println();

        System.out.println("Test 9: result should be -1");
        b2 = new BigInt(12378);
        System.out.println(b1.compareTo(b2));
        System.out.println();

        System.out.println("Test 10: result should be 1");
        System.out.println(b2.compareTo(b1));
        System.out.println();

        System.out.println("Test 11: result should be 0");
        b1 = new BigInt(0);
        b2 = new BigInt(0);
        System.out.println(b1.compareTo(b2));
        System.out.println();
        

        System.out.println("Test 12: result should be\n123456789123456789");
        int[] a4 = { 3,6,1,8,2,7,3,6,0,3,6,1,8,2,7,3,6 };
        int[] a5 = { 8,7,2,7,4,0,5,3,0,8,7,2,7,4,0,5,3 };
        BigInt b4 = new BigInt(a4);
        BigInt b5 = new BigInt(a5);
        BigInt sum = b4.add(b5);
        System.out.println(sum);
        System.out.println();

        System.out.println("Test 13: result should be\n123456789123456789");
        System.out.println(b5.add(b4));
        System.out.println();

        System.out.println("Test 14: result should be\n3141592653598");
        b1 = new BigInt(0);
        int[] a6 = { 3,1,4,1,5,9,2,6,5,3,5,9,8 };
        b2 = new BigInt(a6);
        System.out.println(b1.add(b2));
        System.out.println();

        System.out.println("Test 15: result should be\n10000000000000000000");
        int[] a19 = { 9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9};    // 19 nines!
        b1 = new BigInt(a19);
        b2 = new BigInt(1);
        System.out.println(b1.add(b2));
        System.out.println();

        System.out.println("Test 16: should throw an ArithmeticException");
        int[] a20 = { 9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9,9 };  // 20 nines!
        try {
            b1 = new BigInt(a20);
            System.out.println(b1.add(b2));
        } catch (ArithmeticException e) {
            System.out.println("Test passed.");
        } catch (Exception e) {
            System.out.println("Test failed: threw wrong type of exception.");
        }
        System.out.println();
        

        System.out.println("Test 17: result should be 5670");
        b1 = new BigInt(135);
        b2 = new BigInt(42);
        BigInt product = b1.mul(b2);
        System.out.println(product);
        System.out.println();

        System.out.println("Test 18: result should be\n99999999999999999999");
        b1 = new BigInt(a20);   // 20 nines -- see above
        b2 = new BigInt(1);
        System.out.println(b1.mul(b2));
        System.out.println();

        System.out.println("Test 19: should throw an ArithmeticException");
        try {
            b1 = new BigInt(a20);
            b2 = new BigInt(2);
            System.out.println(b1.mul(b2));
        } catch (ArithmeticException e) {
            System.out.println("Test passed.");
        } catch (Exception e) {
            System.out.println("Test failed: threw wrong type of exception.");
        }
        System.out.println();
        
    }
}
