/* 
 * ArrayMethods.java
 * CS 112, Boston University
 * 
 * Completed by Youjung Jung , jenjung@bu.edu
 * 
 * Array processing methods
 */

import java.util.*;

public class ArrayMethods {
    /*
     * declares an array of strings called COLLEGES
     */
    public static final String[] COLLEGES = {
        "CAS", "CFA", "CGS", "COM", "ENG", "QST", 
        "SAR", "SED", "SHA", "OTHER"
      };
    
    /*
     * getCollegeNum - returns the index of the string reference
     */
    public static int getCollegeNum(String coll) {
        for (int i = 0; i < COLLEGES.length; i++) {
            if (COLLEGES[i].equals(coll)) {
                return i;
            }
        }
        return -1;
    }

    /*
     * negateEvents - negates all of the even numbers in the given array
     */
    public static void negateEvens(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] % 2 == 0) {
                arr[i] = -1 * arr[i];
            }
        }

    }

    /*
     * slice - takes an array of int values and two integers, start and end, 
     *         then returns a new array in which the values are from index start until 
     *         index end 
     */
    public static int[] slice(int[] values, int start, int end) {
        if (start >= end) {
            return new int[0];
        }
        int[] newArray = new int[end - start];
        int idx = 0;
        for ( int i = start; i < end; i++) {
            newArray[idx] = values[i];
            idx++;
        }
        return newArray;

    }

    /*
     * isSorted - returns true if the given array is sorted
     */
    public static boolean isSorted(int[] arr) {
        for ( int i = 0; i < arr.length-1;i++) {
            if (arr[i] > arr[i+1]) {
                return false;
            }
        }
        return true;
    }

    /*
     * maxSorted - returns the length of the longest sorted sequences of integers
     *             in the array
     */
    public static int maxSorted(int[] values) {
        int count = 1;
        int maxCount = 1;
        if (values.length == 0) {
            return 0;
        }
        for ( int i = 0; i < values.length - 1; i++) {
            if (values[i] <= values[i+1]) {
                count += 1;
                if (count > maxCount) {
                    maxCount = count;
                }
            } else {
                count = 1;
            }
        }
        return maxCount;
    }













    public static void main(String[] args) {
        /* sample test call */
        System.out.println(getCollegeNum("CAS"));
        System.out.println(getCollegeNum("QST"));
        int[] a1 = {1, 2, 4, 5, -8, -10, -11};
        negateEvens(a1);
        System.out.println(Arrays.toString(a1));
        int[] a2 = {2, 5, 6, 3, 7, 4, 1};
        int[] a3 = ArrayMethods.slice(a2, 2, 5);
        System.out.println(Arrays.toString(a3));
        int[] a4 = ArrayMethods.slice(a2, 5, 2);
        System.out.println(Arrays.toString(a4));   
        int[] a5 = {2, 5, 6, 6, 9, 9, 9, 10};
        int[] a6 = {2, 5, 6, 4, 9, 9, 7, 10};
        int[] a9 = {};
        System.out.println(ArrayMethods.isSorted(a5));
        System.out.println(ArrayMethods.isSorted(a6));  
        System.out.println(ArrayMethods.isSorted(a9));   
        int[] a7 = {3, 8, 6, 14, -3, 0, 14, 207, 98, 12};
        System.out.println(ArrayMethods.maxSorted(a7));
        int[] a8 = {17, 42, 3, 5, 5, 5, 8, 4, 6, 1, 19};
        System.out.println(ArrayMethods.maxSorted(a8));
        System.out.println(ArrayMethods.maxSorted(a9));
    }
}