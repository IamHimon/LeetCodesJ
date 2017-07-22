package com.company.Offers.Chapter2;

import java.util.HashMap;

/**
 * Created by 猛 on 2017/7/19.
 */
public class Array {
    /*用哈希表的方法，需要O(n)的空间复杂度*/
    public static void DuplicationInArray1(int[] array) throws Exception {
        if (array.length == 0)
            throw new Exception("null array!");
        HashMap<Integer, Integer> wordCound = new HashMap<>();
        for (int a : array) {
            if (!wordCound.containsKey(a))
                wordCound.put(a, 1);
            else {
                System.out.println(a);
                break;
            }
        }
    }

    /*注意，长度为n的数组，数字是0-n-1的范围内*/
    public static boolean DuplicationInArray(int[] array) {

        int temp;
        if (array.length == 0)
            return false;

        for (int a : array) {
            if (a < 0 || a > array.length - 1)
                return false;
        }

        for (int i = 0; i < array.length; i++) {

            //直到array[i]==i时停止，要不然一直调换位置，当array[i]!=i,但是调换的那个位置值=array[i],则array[i]是一个重复的元素
            while (array[i] != i) {
                if (array[i] == array[array[i]]) {
                    System.out.println(array[i]);
                    return true;
                }
                temp = array[i];
                array[i] = array[temp];
                array[temp] = temp;
            }
        }
        return false;
    }


    public static int getDeplication(int[] array) {
        if (array.length == 0)
            return -1;
        int start = 1;
        int end = array.length - 1;

        while (start <= end) {
            int mid = ((end - start) >> 1) + start;
            int count = countRange(array, start, mid);
            if (start == end) {
                if (count > 1)
                    return start;
                else
                    break;
            }
            if (count > (mid - start + 1)) {    //根中间数比较
                end = mid;
            } else {
                start = mid + 1;
            }
        }
        return -1;
    }

    private static int countRange(int[] array, int start, int end) {
        int count = 0;
        for (int a : array) {
            if (a>=start && a<=end)
                count++;
        }
        return count;
    }

    /*二分查找，原始数组是有序的*/
    public static int binarySearch(int[] array, int goal) {
        int start = 0;
        int end = array.length;

        while (end <= start) {
            int mid = (end - start) / 2 + end;//直接用(end + start)/2 可能溢出
            if (array[mid] == goal)
                return array[mid];
            if (array[mid] > goal) {
                end = mid - 1;
            } else if (array[mid] <= goal) {
                start = mid + 1;
            }
        }
        return -1;
    }

    /*上下递增，左右递增的二维数组中，寻找一个元素
    * 方案：每次考察数组右上角，如果大于goal，去掉那一列，如果小于goal，去掉那一行，直到右上角等于goal
    * */
    public static boolean find(int[][] aarray, int goal){
        if (aarray.length ==0){
            return false;
        }
        int width = aarray[0].length;
        int height = aarray.length;
        if (goal > aarray[height-1][width-1])
            return false;

        int right_row = 0;
        int right_col = aarray[0].length-1;
        while (right_row<height&&right_col>=0){
            if (aarray[right_row][right_col]==goal)
                return true;
            if (aarray[right_row][right_col]<=goal){
                right_row += 1;
            }else {
                right_col -= 1;
            }
        }
        return false;
    }


    public static void main(String_p[] args) throws Exception {
        int[] a = {1, 2, 3, 4, 5, 6, 7, 8, 2};
        int[][] aa = {{1,2,3,4},{3,4,5,6},{5,7,8,10}};
//        DuplicationInArray1(a);
//        DuplicationInArray(a);
//        System.out.println(getDeplication(a));
        System.out.println(find(aa, 9));
        System.out.println(find(aa, 6));

    }
}
