package com.company.Offers.Chapter5;

import com.company.Offers.Chapter2.Array;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Created by himon on 17-10-1.
 */
public class Common {

    /*面试题39,数组中出现次数超过一半的数字*/
    //方法1:用Map来存储元素与其个数
    private static int MoreThanHalfNum1(int[] num) {
        Map<Integer, Integer> numCount = new HashMap<>();
        for (int n : num) {
            if (numCount.keySet().contains(n)) {
                numCount.put(n, numCount.get(n) + 1);
            } else {
                numCount.put(n, 1);
            }
        }
        int maxCount = 0;
        for (int c : numCount.values()) {
            if (maxCount < c)
                maxCount = c;
        }
        if (maxCount >= Math.ceil((double) num.length / 2))
            return maxCount;
        else
            return 0;
    }

    //解法二,中位数;
    /*
    * 如果将数组排序,那么排序之后位于数组中间的数字一定就是那个出现次数超过数组一半的数字.
    *
    * */
    private static int MoreThanHalfNum2(int[] num) throws Exception {
        int middle = num.length >> 1;
        int start = 0;
        int end = num.length-1;

        int index = Partition(num, start, end);

        while (index != middle) {
            if (index > middle) {
                end = index - 1;
                index = Partition(num, start, end);
            } else {
                start = index + 1;
                index = Partition(num, start, end);
            }
        }

        int result = num[middle];

        //在找到中位数之后还要判断是不是中位数数量大于一半

        int times = 0;
        for (int n:num){
            if (n == result)
                times++;
        }
        if (times*2 > num.length)
            return result;
        else
            return 0;
    }


    //随机选一个数,是的左边的都小于它,右边的全大于它
    //返回值是key的下标
    private static int Partition(int[] data, int start, int end) throws Exception {
        if (data.length <= 0 || start < 0 || end >= data.length)
            throw new Exception("INvalid Parameters");
        Random random = new Random();
//        System.out.println("start:"+start + ", end:"+end);
        //generate random integer:[start, end],included
        int index = random.nextInt(end-start+1) + start;
//        System.out.println("random index:" + index + ", data:" + data[index]);
        int i = start, j = end;

        //先把index坐在坐标换到第一个
        Swap(data, start, index);
        int key = data[start];
        while (i < j) {
            //从大到小，找到比key小的下标j
            while (i < j && data[j] > key) {
                j -= 1;
            }
            Swap(data, i, j);
            //再从小到大，找到比key大的下标i
            while (i < j && data[i] <= key) {
                i += 1;
            }
            Swap(data, j, i);
        }
//        System.out.println(Arrays.toString(data));

        return i;
    }

    private static int RandomInRange(int min, int max){
        if (max - min == 0)
            return min;
        if (max - min == 1){

        }

        return min;
    }

    private static void Swap(int[] data, int i, int j) {
        if (data.length <= 0)
            return;
        int temp = data[j];
        data[j] = data[i];
        data[i] = temp;
    }

    public static void main(String[] args) throws Exception {
        int[] num = new int[]{3, 2, 1, 4, 4, 8, 4, 5};
//        System.out.println(MoreThanHalfNum1(num));

//        System.out.println(Partition(num, 0, num.length - 1));
        System.out.println(MoreThanHalfNum2(num));



    }

}
