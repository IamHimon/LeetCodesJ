package com.company;

import com.company.Offers.Chapter2.Array;
import com.company.Offers.Chapter2.String_p;
import javafx.scene.layout.BackgroundRepeat;

import java.util.Arrays;

/**
 * Created by 猛 on 2017/7/22.
 */
public class JSort {

    /*
    * 插入排序，直接插入排序
    * */
    public static void insertSort(int[] A) {
        int guard;
        for (int i = 1; i < A.length; i++) {
            guard = A[i];
            int j = i - 1;
            while (j >= 0 && A[j] > guard) {
                A[j + 1] = A[j];
                j--;
            }
            A[j + 1] = guard;
        }
    }


    /*快速排序(升序)，每一次选择一个key(一般是第一个),然后每一次循环根据key值把数组划分为两部分，左边小于key，右边大于key，
    * 结束条件low>=high
    * */
    public static void quickSort(int[] A, int low, int high) {
        if (low >= high)//结束条件
            return;
        int i = low;
        int j = high;
        int key = A[i]; //一般第一个作为key
        int temp;
        while (i < j) {
            while (i < j && A[j] > key) { //从大到小，找到比key小的下标j
                j -= 1;
            }
            temp = A[i];    //交换A[j]与A[i]
            A[i] = A[j];
            A[j] = temp;    //此时key存在A[j]位置
            while (i < j && A[i] <= key) {    //再从小到大，找到比key大的下标i
                i += 1;
            }
            temp = A[j];    //交换A[i]与A[j]
            A[j] = A[i];
            A[i] = temp;    //此时key存在A[i]位置
        }

        quickSort(A, low, i - 1); //在key右边再进行
        quickSort(A, i + 1, high); //在key左边再进行
    }


    /*给员工年龄排序，规定员工年龄的范围是0-99
    *先初始化timeOfAges[]都为0,然后根据输入ages来构造它，年龄出现了多少次就在ages里面设置几次该年龄
    *
    * */
    public static void SortAge(int ages[]) throws Exception {
        if (ages.length <= 0)
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
        for (int i = 0; i < oldestAge; i++) {
            for (int j = 0; j < timeOfAge[i]; j++) {
                ages[index] = i;
                index++;
            }
        }
//        System.out.println(Arrays.toString(ages));

    }

    public static void hello() {
        System.out.println("hello");
    }

    /*归并排序*/
    //合并相邻的两个片段,合并之后是有序的.
    private static void Merge(int num[], int low, int mid, int high) {
        int i = low;
        int j = mid + 1;
        int t = 0;
        int[] temp = new int[high - low + 1]; //临时数组用来暂存顺序合并两个片段之后的元素
        //讲两个片段合并为一个,保持顺序
        while (i <= mid && j <= high) {
            if (num[i] <= num[j]) {
                temp[t++] = num[i];
                i++;
            } else {
                temp[t++] = num[j];
                j++;
            }
        }
        //当第一个片段[low,mid]还剩下元素
        while (i <= mid)
            temp[t++] = num[i++];

        //当第二个片段[mid+1,high]还剩下元素
        while (j <= high)
            temp[t++] = num[j++];

        //讲temp数组存入原数组
        for (int n = low, tt = 0; n <= high; n++, tt++) {
            num[n] = temp[tt];
        }
    }
    //分解数组,以gap分解,可以分解为len/gap个片段,然后在每两个相邻的连段上做合并(Merge)
    // ,特殊情况:若子片段个数为奇数,最后一个轮空;若子片段为偶数,high的坐标为length-1
    public static void MergePass(int num[], int gap) {
        int i;
        //归并gap长度的两个相邻片段
        for (i = 0; i + 2 * gap - 1 < num.length; i = i + 2 * gap)
            Merge(num, i, i + gap - 1, i + 2 * gap - 1);

        //如果最后的片段不到gap长度
        if (i + gap -1 < num.length)
            Merge(num, i, i + gap -1, num.length - 1);
    }

    private static int[] merge_sort(int num[]){
        for (int gap=1;gap<num.length;gap = 2*gap){
            MergePass(num, gap);
            System.out.println("gap = " + gap + ":\t");
        }
        return num;
    }

    public static void main(String[] args) throws Exception {
        int[] a = {57, 14, 68, 59, 52, 72, 28, 96, 33, 24};
//        quickSort(a, 0, a.length - 1);
//        SortAge(a);
//        insertSort(a);
        merge_sort(a);
        System.out.println(Arrays.toString(a));
//        System.out.println(Math.sqrt(178 * 2));
//        System.out.println(10318/((187+2)*2));
    }
}
