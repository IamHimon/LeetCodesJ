package com.company.learnJava;

/**
 * Created by himon on 17-8-30.
 */
public class JSearch {
    private static int binarry_search(int[] num, int aim) {
        int low = 0;
        int high = num.length - 1;
        while (low <= high){
            int mid = low + (high - low)/2;
            if (aim > num[mid])
                low = mid+1;
            if (aim < num[mid])
                high = mid -1;
            if (aim == num[mid])
                return mid;
        }
        return low;
    }

    public static void main(String[] args) {
        int[] num = new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9};
        System.out.println(binarry_search(num, 3));
    }
}
