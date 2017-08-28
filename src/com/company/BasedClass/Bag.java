package com.company.BasedClass;

import java.util.Arrays;

public class Bag {

    /*O1背包问题，有N件商品，每个商品只有一件，一个容量为V的背包，第i件商品的费用是c[i],价值是w[i], 求将那些商品放入背包可使价值总和最大。
    *基本思路：
    *用子问题定义状态：f[i][v]表示前i件商品恰好放入到容量为v的背包中获得的最大价值总量。其状态转移方程是：
    * f[i][v] = max{f[i-1][v-1], f[i-1][v-c[i]]+w[i]}
    *
    *
    * */
    private static int N = 3; //物品数量
    private static int V = 7;  //书包容量


    private static int Package() {
        int[] W = new int[]{0, 7, 5, 8};
        int[] C = new int[]{0, 2, 3, 4};
        //初始化f
        int[] f = new int[V+1];
        for (int i=1;i<V+1;i++)
            f[i] = Integer.MIN_VALUE;

        int bound;
        for (int i = 1; i <= N; i++) {
            bound = Math.max(V - )
            for (int j = V; j >= C[i]; j--) {
                f[j] = Math.max(f[j], f[j - C[i]] + W[i]);
            }
        }

        return f[V];
    }

    private static int ZeroOnePack(int cost, int weight, int[] f){
        for (int i= N;i>cost;i--){
            f[i] = Math.max(f[i-1], f[i - cost] + weight);
        }
        return 0;
    }


    public static void main(String[] args) {
        System.out.println(Package());
    }
}
