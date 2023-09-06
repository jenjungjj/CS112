/*
 * Problem5.java
 *
 * Computer Science 112, Boston University
 * 
 * modified by:
 *   name: Youjung Jung
 *   email: jenjung@bu.edu
 */
import java.util.*;

/*
 * Class that contains the intersect method in which takes two int arrays as parameters then 
 * returns a new array of the values found in both arrays without duplicating
 */
public class Problem5 {

    /*
     * intersect method takes in two int arrays as parameters 
     * then returns all values found in both arrays
     */
    public static int[] intersect(int[] arr1, int[] arr2) {
        int[] inct = new int[arr1.length];
        if(arr1.length > arr2.length) {
            inct = new int[arr2.length];
        }
        Sort.insertionSort(arr1);
        Sort.insertionSort(arr2);
        int i = 0;
        int j = 0;
        int idx = 0;
        int idx2 = 1;
        while (i< arr1.length && j < arr2.length) {
            if(arr1[i] < arr2[j]) {
                i++;
            } else if (arr1[i] > arr2[j]) {
                j++;
            } else {
                inct[idx] = arr1[i];
                i++;
                j++;
                idx++;
            }
        }
        while(idx2 < inct.length-1) {
            if(inct[idx2] == inct[idx2-1]) {
                inct[idx2] = inct[idx2+1];
            }
            idx2++;
        }
        if(inct[inct.length-1] == inct[inct.length-2]) {
            inct[inct.length-1] = 0;
        }

        return inct;
    }

    public static void main(String[] args) {
        int[] a1 = {10, 5, 7, 5, 9, 4};
        int[] a2 = {7, 5, 15, 7, 7, 9, 10};
        int[] result1 = intersect(a1, a2);
        System.out.println("result1: " + Arrays.toString(result1));

        int[] a3 = {0, 2, -4, 6, 10, 8};
        int[] a4 = {12, 0, -4, 8};
        int[] result2 = intersect(a3, a4);
        System.out.println("result2: " + Arrays.toString(result2));

    }
}