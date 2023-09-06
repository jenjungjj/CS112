import java.util.*;

public class Buggy {
    /*
     * This static method should take an integer x and return:
     *    x + 1 if x is even
     *    the unchanged value of x when x is odd
     */
    public static int makeOdd(int x){
        int val = x;
        if (x % 2 == 0);
            val += 1;
        return val;
    }

    public static void main(String [] args) {
        Scanner console = new Scanner(System.in);

        System.out.print("Enter an integer x: ");
        int x = console.nextInt();
        int newVal = makeOdd(x);

        System.out.println("makeOdd(x) = " + newVal);
    
    }
}