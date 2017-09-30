package com.company.learnJava;

import com.company.Main;
import javafx.scene.transform.MatrixType;

import java.util.Arrays;
import java.util.Objects;

/**
 * Created by himon on 17-9-29.
 */
public class Algorithm {
    /*八皇后问题*/
    /*解法1:递归法求出八个数的全排列,这样可以保证任意两个不在同一行,同一列.
    * 然后每一个排列判断是不是满足任意两个不在同一条对角线上
    * */
    static int QueenCount = 0;
    private static void permutation(int[] num, int start, int end) {
        if (end<1)
            return;
        if (start == end) {
            //判断是不是在对角线上,判断条件:Math.abs(c2-c1) != Math.abs(num[c2]-num[c1]))
            boolean isDiagonal = false;
            for (int c1=0;c1<num.length;c1++){
                for (int c2=c1+1;c2<num.length;c2++){
                    if ((c2 != c1)&&(Math.abs(c2-c1) == Math.abs(num[c2]-num[c1]))){
                        isDiagonal = true;
                        break;
                    }
                }
            }
            if (!isDiagonal){
                QueenCount++;
                System.out.println("Queen:"+Arrays.toString(num));
            }
        } else {
            for (int i = start; i <= end; i++) {
                swap(num, start, i);
                permutation(num, start + 1, end);
                swap(num, start, i);    //交换回来
            }
        }
    }
    private static void swap(int[] str, int i, int j) {
        int temp = str[j];
        str[j] = str[i];
        str[i] = temp;
    }

    /*解法2:*/
    private static void EightQuee(int max) {
        //columnIndex[n]用来存放第n个棋子所在的位置(值)
        int[] columnIndex = new int[max];
        check(0, max, columnIndex);
    }

    private static void check(int n, int max, int[] array){
        //终止条件是最后一列也也摆好,由于没摆一列都要进行判断,当最后一列摆完,则说明找到了一个正确的解
        if (n == max) {
            QueenCount++;
            System.out.println(Arrays.toString(array));
            return;
        }

        //n列从0开始依次尝试放入皇后,然后判断是不是满足条件,如果满足则递归到下一列
        for (int i=0;i<max;i++){
            array[n] = i;
            if (judeg(n, array)){
                check(n+1, max, array);
            }
        }
    }
    //判断当前列放入的值array[n]是不是与前面的满足条件的值冲突.
    private static boolean judeg(int n, int[] array) {
        for (int i=0;i<n;i++){
            if (array[i] == array[n] || Math.abs(n -i)==Math.abs(array[n]-array[i]))
                return false;
        }
        return true;
    }


    public static void main(String[] args) {
//        permutation(new int[]{0, 1, 2, 3, 4, 5, 6, 7}, 0, 7);

        EightQuee(8);
        System.out.println(QueenCount);
    }
}
