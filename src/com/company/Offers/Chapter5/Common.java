package com.company.Offers.Chapter5;

import com.company.Offers.Chapter2.Array;

import java.util.*;

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
        int end = num.length - 1;

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
        for (int n : num) {
            if (n == result)
                times++;
        }
        if (times * 2 > num.length)
            return result;
        else
            return 0;
    }


    //随机选一个数,使得左边的都小于它,右边的全大于它
    //返回值是key的下标
    private static int Partition(int[] data, int start, int end) throws Exception {
        if (data.length <= 0 || start < 0 || end >= data.length)
            throw new Exception("INvalid Parameters");
        Random random = new Random();
//        System.out.println("start:"+start + ", end:"+end);
        //generate random integer:[start, end],included
        int index = random.nextInt(end - start + 1) + start;
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

    private static int RandomInRange(int min, int max) {
        if (max - min == 0)
            return min;
        if (max - min == 1) {

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

    /*
    *解法三:
    * 数组中有一个数字出现的次数超过数组的一半,那么它出现的次数一定比其他所有数组出现次数的和还要多.
    * 在遍历整个数组的过程中,记录下数组中的当前数字,以及他出现的次数.
    * 当遍历一下个数字时:如果下一个数字与当前数字相等则次数加一,如果不相等则次数减一.
    * 如果次数为0,则记录下一个数字,并将其对应的次数设定为1.
    * 由于我们要找的数字出现次数比其他所有数字出现次数还要多,那么要找的数字肯定是最后一次把次数设为1对应的数字!
    * 然后我们再用CheckMoreThanHalf函数检测出现次数最多的数是不是满足条件!
    *
    * */
    private static int MoreThanHalfNum3(int[] num) {
        if (num.length <= 0)
            return 0;

        int result = num[0];
        int time = 0;
        for (int i = 1; i < num.length; i++) {
            if (time == 0) {
                result = num[i];
                time = 1;
            } else {
                if (result == num[i]) {
                    time++;
                } else {
                    time--;
                }
            }
        }

        if (CheckMoreThanHalf(num, result))
            return result;
        else
            return 0;
    }

    //验证频率最高的数字是不是超过数组长度的一半
    private static boolean CheckMoreThanHalf(int[] num, int number) {
        int times = 0;
        for (int n : num) {
            if (n == number)
                times++;
        }
        boolean isMoreThanHalf = false;
        if (times * 2 > num.length)
            isMoreThanHalf = true;

        return isMoreThanHalf;
    }


    /*面试题40,最小的k个数字*/
    private static int[] GetLeastKNumbers(int[] num, int k) throws Exception {
        if (num.length <= 0)
            throw new Exception("error input!");

        int start = 0;
        int end = num.length - 1;

        int index = Partition(num, start, end);
        System.out.println(index);

        while (index != k - 1) {
            if (index > k - 1) {
                end = index - 1;
                index = Partition(num, start, end);
            } else {
                start = index + 1;
                index = Partition(num, start, end);
            }
        }

        System.out.println(Arrays.toString(num));

        int[] output = new int[k];
        System.arraycopy(num, 0, output, 0, k);

        System.out.println(Arrays.toString(output));

        return output;
    }

    /*
    * 方法二:适用于大数据量找最大(最小)k个数字的情景.
    * 使用TreeSet来实现,TreeSet基于红黑树实现的的,可以在O(logk)时间内完成查找,删除及插入操作.
    *
    * 则对于这个问题:
    * 定义一个k大小的容量,在遍历所有数据的过程中,首先填满容器,当容器满之后,当来下一个数字,更新这个容器,做三件事:
    * 1.在容器中找最大数.2当这个数比最大数大则删除最大数.3.插入这个数. 则当遍历所有数,这个容器就是结果.
    * 所以需要一个能在O(logk)时间内完成查找,删除,插入操作的数据结构(大顶堆或者红黑树).
    * */
    private static void GetLeastKNumbers2(int[] num, int k) {
        TreeSet<Integer> intSet = new TreeSet<>();
        if (k < 1 || k > num.length)
            return;

        for (int n : num) {
            if (intSet.size() < k) {
                intSet.add(n);
            } else {
                int iterGreatest = intSet.last();
                if (n < intSet.last()) {
                    intSet.remove(iterGreatest);
                    intSet.add(n);
                }
            }
        }
        System.out.println(intSet);
    }

    public static void main(String[] args) throws Exception {
        //面试题39
//        int[] num = new int[]{3, 1, 4, 4, 8, 4, 9, 4, 4};
//        System.out.println(MoreThanHalfNum1(num));

//        System.out.println(Partition(num, 0, num.length - 1));
//        System.out.println(MoreThanHalfNum2(num));
//        System.out.println(MoreThanHalfNum3(num));

        //面试题40
        int[] num = new int[]{4, 5, 1, 6, 2, 7, 3, 8};
//        GetLeastKNumbers(num, 4);
        GetLeastKNumbers2(num, 4);


    }

}
