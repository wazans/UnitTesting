package org.example;

public class Fibonacci_Demo {

    public static int compute(int n) {
        // Recursive method to compute the Fibonacci number for a given input 'n'
        if (n <= 0) {
            return 0;
        } else if (n == 1) {
            return 1;
        } else {
            return compute(n - 1) + compute(n - 2);
        }
    }
}
