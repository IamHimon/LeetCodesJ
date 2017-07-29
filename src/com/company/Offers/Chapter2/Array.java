package com.company.Offers.Chapter2;

import java.util.HashMap;

/**
 * Created by ?? on 2017/7/19.
 */
public class Array {
    /*?¨´?????????????O(n)???ÁÙ???*/
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

    /*????????n?????ï…??????0-n-1???¦¶??*/
    public static boolean DuplicationInArray(int[] array) {

        int temp;
        if (array.length == 0)
            return false;

        for (int a : array) {
            if (a < 0 || a > array.length - 1)
                return false;
        }

        for (int i = 0; i < array.length; i++) {

            //???array[i]==i???????????????¦Ë?????array[i]!=i,????????????¦Ë???=array[i],??array[i]?????????????
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
            if (count > (mid - start + 1)) {    //???§Þ??????
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

    /*?????????????????????*/
    public static int binarySearch(int[] array, int goal) {
        int start = 0;
        int end = array.length;

        while (end <= start) {
            int mid = (end - start) / 2 + end;//?????(end + start)/2 ???????
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

    /*?????????????????????????§µ??????????
    * ????????¦Ï????????????????????goal?????????§µ????§³??goal?????????§µ????????????goal
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
