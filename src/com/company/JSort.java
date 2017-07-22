package com.company;

import com.company.Offers.Chapter2.Array;
import com.company.Offers.Chapter2.String_p;

import java.util.Arrays;

/**
 * Created by 猛 on 2017/7/22.
 */
public class JSort {

    /*快速排序(升序)*/
    public static void quickSort(int[] A, int low, int high){
        if (low >= high)//结束条件
            return;
        int i = low;
        int j = high;
        int key = A[i]; //一般第一个作为key
        int temp;
        while (i<j){
            while (i<j && A[j] > key){ //从大到小，找到比key小的下标j
                j -= 1;
            }
            temp = A[i];    //交换A[j]与A[i]
            A[i] = A[j];
            A[j] = temp;    //此时key存在A[j]位置
            while (i<j && A[i] <= key) {    //再从小到大，找到比key大的下标i
                i += 1;
            }
            temp = A[j];    //交换A[i]与A[j]
            A[j] = A[i];
            A[i] = temp;    //此时key存在A[i]位置
        }
        quickSort(A, low, high - 1); //在key右边再进行
        quickSort(A, low+1, high); //在key左边再进行
    }

    /*给员工年龄排序，规定员工年龄的范围是0-99
    *先初始化timeOfAges[]都为0,然后根据输入ages来构造它，年龄出现了多少次就在ages里面设置几次该年龄
    *
    * */
    public static void SortAge(int ages[]) throws Exception {
        if (ages.length<=0)
            return;

        int oldestAge = 99;
        int[] timeOfAge = new int[oldestAge + 1]; //各年龄出现的次数

        for (int age : ages) {
            if (age < 0 && age > oldestAge)
                throw new Exception("age out of range");
            timeOfAge[age]++;
        }
//        System.out.println(Arrays.toString(timeOfAge));

        /*index是ages数组的下标，来重新给ages数组赋值
        * 从0-99岁开始遍历，在i岁根据timeOfAge[i]的值，如果大于零，说明有这个年龄出现，然后才进入第二层for循环，只有当进来第二层for才能给index增1
        * 在第二层for循环中来给ages数组赋值，且赋的值为i，因为i是从小到大（0-99）的，所以也就相当于小的先放进ages数组里，相当于排序了。
        * */
        int index = 0;
        for (int i=0;i<oldestAge;i++){
            for (int j=0;j<timeOfAge[i];j++){
                ages[index] = i;
                index++;
            }
        }
//        System.out.println(Arrays.toString(ages));

    }

    public static void main(String[] args) throws Exception {
        int[] a = {57, 14,68, 59, 52, 72, 28, 96, 33, 24};
//        quickSort(a, 0, a.length-1);
//        System.out.println(Arrays.toString(a));
        SortAge(a);
    }
}
