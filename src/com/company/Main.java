package com.company;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static com.company.Solu.Solution.*;

public class Main {

    public static int meituan_1(int N, int[] num, int V){
        if (num.length != N)
            return 0;

        int[] W = new int[N+1];
        W[0] = 0;
        for (int i=1;i<N;i++)
            W[i] = 1;

        int[] C = new int[N+1];
        C[0] = 0;
        for (int i=1;i<N;i++) {
            C[i] = num[i - 1];
        }

        int max_sum = 0;
        for (int n:num){
            max_sum += n;
        }

        int VV = V;
        for (int k = 1;k<=max_sum/VV;k++){
            V = k*VV;
            int[][] f = new int[N + 1][V + 1];
            for (int i = 1; i <= N; i++) {
                for (int j = 1; j <= V; j++) {
                    f[i][j] = Integer.MIN_VALUE;
                }
            }
            for (int i = 1; i <= N; i++) {
                for (int j = C[i]; j <= V; j++) {
                    f[i][j] = Math.max(f[i - 1][j], f[i - 1][j - C[i]] + W[i]);
                }
            }
            System.out.println(f[N][V]);
        }




        return 1;


    }

    public static void main(String[] args) {
        // write your code here
//        System.out.println(eight("2147483648"));
//        System.out.println(eight("18446744073709551617"));
//        System.out.println(eight_L("18446744073709551617"));
//        System.out.println(eight_L("+-1234"));
//        System.out.println(eight("+-1234"));
//        char a = 1;
//        System.out.println(nine(1234));
//        System.out.println(-2147483648);
//        System.out.println(nine(-2147483648));
//        System.out.println(nine_L(12321));
//        System.out.println("hehe");
//        Object[] vals = new Object[]{1, 2, 3, 4, null, 6, 7};
//        for (Object o : vals)
//            System.out.println(o);
//        List<Object> v = new ArrayList<>();
//        v.add(1);
//        v.add(2);
//        v.add(3);
//        v.add(4);
//        v.add(5);
//        v.add(6);
//        System.out.println(v);
//        List<Object> v1 = v.subList(1,2);
//        System.out.println(v1);
//        System.out.println(v);
//        mergeTrees();
//        System.out.println(13/2);
//        System.out.println(14/2);

//        System.out.println(meituan_1(5, new int[]{1,2,3,4,5}, 5));

        System.out.println(2&1);
        System.out.println(3&1);
    }


}
