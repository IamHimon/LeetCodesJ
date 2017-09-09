package com.company.History;

import java.util.Arrays;

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

    public static void main(String[] args) {

        System.out.println(wangyi1("sg", "weoirskid"));
    }
}
