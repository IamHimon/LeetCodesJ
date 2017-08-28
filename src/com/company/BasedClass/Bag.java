package com.company.BasedClass;

import com.company.Offers.Chapter2.Array;

import java.util.Arrays;

public class Bag {

    /*O1背包问题:
    *有N件商品，每个商品只有一件，一个容量为V的背包，第i件商品的费用是c[i],价值是w[i], 求将那些商品放入背包可使价值总和最大。
    *基本思路：
    *用子问题定义状态：f[i][v]表示前i件商品恰好放入到容量为v的背包中获得的最大价值总量。其状态转移方程是：
    * f[i][v] = max{f[i-1][v], f[i-1][v-c[i]]+w[i]}
    * */
    private static int N = 3; //物品数量
    private static int V = 9;  //书包容量

    private static int Package_1(int[] W, int[] C) {
        int[][] f = new int[N + 1][V + 1];
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= V; j++) {
                f[i][j] = Integer.MIN_VALUE;
            }
        }
        for (int i = 1; i <= N; i++) {
            for (int j = C[i]; j <= V; j++) {
                f[i][j] = Math.max(f[i - 1][j], f[i - 1][j - C[i]] + W[i]);
//                System.out.println("f["+i+"]["+j+"]="+f[i][j]);
            }
        }
        return f[N][V];
    }


    private static int Package_2(int[] W, int[] C) {
        //初始化f
        //如果全部初始化为0,表示并没有要求必须要背包装满,而只是希望价格尽量大.
        //只讲f[0]初始化为0,其他初始化为MIN_VALUE,就是要求必须恰好把包装满.
        int[] f = new int[V + 1];
        for (int i = 1; i < V + 1; i++)
            f[i] = Integer.MIN_VALUE;

        int bound;
        int sum = 0;
        for (int i = 1; i <= N; i++) {
            for (int n = i; n <= N; n++)
                sum += W[n];
            bound = Math.max(V - sum, C[i]);
            for (int j = V; j >= bound; j--) {   //费用为C[i]的物品不会影响状态f[0...C[i]-1]
                f[j] = Math.max(f[j], f[j - C[i]] + W[i]);
            }
        }
        return f[V];
    }

    private static int[] ZeroOnePack(int cost, int weight, int[] f) {

        for (int i = V; i > cost; i--) {
            f[i] = Math.max(f[i], f[i - cost] + weight);
        }
        return f;
    }


    /*完全背包问题:
    *有N种物品和一个容量为V的背包，每种物品都有无限件可用。第i种物品的费用是c[i]，价值是w[i]。求解将哪些物品装入背包可使这些物品的费
    * 用总和不超过背包容量，且价值总和最大。
    *
    *
    * */
    private static int Full_Package_1(int[] W, int[] C) {
        int[][] f = new int[N + 1][V + 1];
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= V; j++) {
                f[i][j] = Integer.MIN_VALUE;
            }
        }

        for (int i = 1; i <= N; i++) {
            for (int j = C[i]; j <= V; j++) {
                int max = Integer.MIN_VALUE;
                for (int k = 0; k * C[i] <= j; k++) {
                    max = Math.max(max, f[i - 1][V - k * C[i]] + k * W[i]);
                }
                f[i][j] = max;
                System.out.println("f[" + i + "][" + j + "]=" + f[i][j]);
            }
        }

        return f[N][V];
    }

    private static int Full_Package_2(int[] W, int[] C) {
        int[] f = new int[V + 1];
        for (int i = 1; i < V + 1; i++)
            f[i] = Integer.MIN_VALUE;

        for (int i = 1; i <= N; i++) {
            for (int j = C[i]; j <= V; j++) {
                f[j] = Math.max(f[j], f[j - C[i]] + W[i]);
                System.out.println("f[" + i + "][" + j + "]=" + f[j]);
            }
        }
        return f[V];
    }


    /*
    * 给你六种面额 1、5、10、20、50、100 元的纸币，假设每种币值的数量都足够多，编写程序求组成N元（N为0~10000的非负整数）的不同组合的个数。
    * */
    private static int Meituan(int N) {
        int[] f = new int[N + 1];

    }


    public static void main(String[] args) {
        int[] W = new int[]{0, 7, 5, 8};
        int[] C = new int[]{0, 2, 3, 4};
//        System.out.println(Package_1(W, C));
//        System.out.println(Package_2(W, C));
        System.out.println(Full_Package_1(W, C));
        System.out.println(Full_Package_2(W, C));
    }
}
