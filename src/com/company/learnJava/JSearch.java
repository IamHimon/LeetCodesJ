package com.company.learnJava;

/**
 * Created by himon on 17-8-30.
 */
public class JSearch {
    //二分查找
    private static int binarry_search(int[] num, int aim) {
        int low = 0;
        int high = num.length - 1;
        while (low <= high) {    //这里有等于号
            int mid = low + (high - low) / 2;
            if (aim > num[mid])
                low = mid + 1;
            if (aim < num[mid])
                high = mid - 1;
            if (aim == num[mid])
                return mid;
        }
        return low;
    }

    //找第一个key值(可能有多个key值)
    private static int findFirstKey(int[] num, int left, int right, int key) {
        if (left > right)
            return -1;
        int mid = left + (right - left) / 2;
        if (num[mid] == key) {
            if ((mid > 0 && num[mid - 1] != key) || mid == 0) { //当前一位数不是key,或者当前是第一位
                return mid;
            } else {
                right = mid - 1;
            }
        } else if (num[mid] < key) {
            left = mid + 1;
        } else {
            right = mid - 1;
        }
        return findFirstKey(num, left, right, key);
    }

    //找到最后一个key值
    private static int findLastKey(int[] num, int left, int right, int key) {
        if (left > right)
            return -1;
        int mid = left + (right - left) / 2;
        if (num[mid] == key) {
            if ((mid < num.length && num[mid + 1] != key) || mid == num.length) {   //当后一位数不是key,或者当前是最后一位
                return mid;
            } else {
                left = mid + 1;
            }
        } else if (num[mid] < key) {
            left = mid + 1;
        } else {
            right = mid - 1;
        }
        return findLastKey(num, left, right, key);
    }

    public static void main(String[] args) {
        int[] num = new int[]{1, 2, 3, 4, 5, 5, 5, 5, 6, 7, 8, 9};
//        System.out.println(binarry_search(num, 3));
        System.out.println(findFirstKey(num, 0, num.length - 1, 5));
        System.out.println(findLastKey(num, 0, num.length - 1, 5));
    }
}
