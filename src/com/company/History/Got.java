package com.company.History;

import java.util.HashSet;

/**
 * Created by himon on 17-9-9.
 */
public class Got {
    /*网易2017,数据挖掘1*/
    private static boolean wangyi1(String s1, String s2) {
        char[] S1 = s1.toCharArray();
        char[] S2 = s2.toCharArray();

        int i = 0;
        int j = 0;
        while (i < S1.length && j < S2.length) {
            if (S1[i] == S2[j]) {
                i++;
                j++;
            } else {
                j++;
            }

        }
        System.out.println(i);
        return i == s1.length();
    }

    /*网易2017,数据挖掘2*/
    private static int wangyi2(){
        return 0;
    }

    /*网易2017,数据挖掘3*/
    private static int wangyi3(int n, int k){
        //全排列
        int[][] all_com = new int[n][n];
        for (int i=0;i<n;i++){
            for (int j=0;j<n;j++){

            }
        }
        return 0;

    }

    public static void main(String[] args) {

        System.out.println(wangyi1("sg", "weoirskid"));

        HashSet<String> set = new HashSet<>();
        set.add("BUTTER");
        set.add("BUTTER");
        set.add("flow");
        System.out.println(set);
    }
}
