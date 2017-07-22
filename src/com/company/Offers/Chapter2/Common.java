package com.company.Offers.Chapter2;

/**
 * Created by รอ on 2017/7/22.
 */
public class Common {
    public static long Fibonacci(int n){
        if (n<=0)
            return 0;
        else if (n==1)
            return 1;
        else
            return Fibonacci(n - 1) + Fibonacci(n - 2);
    }
    public static long Fibonacci_cur(int n){
        int[] result = {0,1};
        if (n<2)
            return result[n];

        long fibNMiusOne = 1;
        long fibNMiusTwo = 0;
        long fibN = 0;
        for (int i=2;i<=n;i++){
            fibN = fibNMiusOne + fibNMiusTwo;
            fibNMiusTwo = fibNMiusOne;
            fibNMiusOne = fibN;
        }
        return fibN;
    }

    public static void main(String[] args) {

        System.out.println(Fibonacci(5));
        System.out.println(Fibonacci_cur(5));
    }
}
