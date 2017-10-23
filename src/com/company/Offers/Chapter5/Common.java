package com.company.Offers.Chapter5;

import com.company.Offers.Chapter2.Array;
import com.sun.org.apache.bcel.internal.generic.IF_ACMPEQ;

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


    /*
    * 面试题41，数据流中的中位数
    *
    * */
    private static PriorityQueue<Integer> maxHeap, minHeap;

    private static void addFlowNums(int[] nums) {

        Comparator<Integer> revCamp = new Comparator<Integer>() {
            @Override
            public int compare(Integer left, Integer right) {
                return right.compareTo(left);
            }
        };

        maxHeap = new PriorityQueue<Integer>(20, revCamp);
        minHeap = new PriorityQueue<Integer>(20);


    }

    private static void insertElem(int num) {
        if ((maxHeap.size() + minHeap.size() & 1) == 0) {  //奇数->minHeap
            //如果num比maxHeap中的最大值还小，则先将num加入maxHeap中，再取maxHeap中的最大值取出来，插入minHeap中
            if (maxHeap.size() > 0 && num < maxHeap.peek()) {
                maxHeap.add(num);
                num = maxHeap.peek();
                maxHeap.remove(num);
            }


        } else {     //偶数->maxHeap

            //如果num比minHeap中最小的值还大，则现将num加入到minHeap中，再取出minHeap中的最小值，插入到maxHeap中

        }
    }

    private static int getMedian() {
        int size = minHeap.size() + maxHeap.size();
        if ((size & 1) == 1) {  //奇数
            return minHeap.peek();
        } else {
            return (minHeap.peek() + maxHeap.peek()) / 2;
        }
    }


    /*
    * 面试题45,把数组排成最小数
    *将这个问题看作排序问题，自定义compareTo函数
    * */
    private static void combineNum2Min(int[] nums) {
        quickSort(nums, 0, nums.length - 1);
        System.out.println(Arrays.toString(nums));
    }

    //自定义排序函数，传入自定义比较器，从'小'到'大'排序
    private static void quickSort(int[] nums, int low, int high) {
        if (low >= high)    //必须先判断
            return;
        int mid = partition(nums, low, high);
        quickSort(nums, low, mid - 1);
        quickSort(nums, mid + 1, high);
    }

    private static int partition(int[] nums, int low, int high) {
        int i = low;
        int j = high;
        int temp = nums[low];
        while (i < j) {
            while (i < j && compareString(nums[j], temp) >= 0)   //从后往前找比temp大的，交换
                j--;
            nums[i] = nums[j];
            while (i < j && compareString(nums[i], temp) <= 0)   //从前往后找比temp小的，交换
                i++;
            nums[j] = nums[i];
        }
        nums[j] = temp;
        return j;
    }

    //实现字符串表示数字的比较函数
    private static int compareString(int a, int b) {
        String s1 = String.valueOf(a) + String.valueOf(b);
        String s2 = String.valueOf(b) + String.valueOf(a);
        s1.compareTo(s2);
        return compareTo(s1, s2);
    }

    //比较两个字符串s1,s2,如果s1<s2，返回-1，否则返回1
    private static int compareTo(String s1, String s2) {
        if (s1 == null || s2 == null)
            return -1;

        int len1 = s1.length();
        int len2 = s2.length();
        int lenLim = Math.min(len1, len2);
        //"32321","32132",比较是从k=0，即'3'，'3'开始
        int k = 0;
        while (k < lenLim) {
            char c1 = s1.charAt(k);
            char c2 = s2.charAt(k);
            if (c1 != c2)
                return c1 - c2;
            k++;
        }
        return len1 - len2;

    }


    //面试题46
    /*
    * Fibonacci数列的解法，方程：f(i)=f(i+1)+g(i,i+1)f(i+2),
    * 递归的解法存在重复。可转变为自下向上的解法，也就是从最后一个字符开始。
    * */
    private static int getTranslation(int number) {
        if (number < 0)
            return 0;
        return getTranslationCount(String.valueOf(number));
    }

    //将f(i)存在数组counts[i]的位置，初始值：f(length-1)=1
    private static int getTranslationCount(String number) {
        int length = number.length();
        int[] counts = new int[length];
        int count;
        for (int i = length - 1; i >= 0; i--) {     //从下向上
            if (i < length - 1)
                count = counts[i + 1];
            else
                count = 1;
            if (i < length - 1) {
                int digit1 = number.charAt(i) - '0';
                int digit2 = number.charAt(i + 1) - '0';
                int converted = digit1 * 10 + digit2;
                if (converted >= 10 && converted <= 25) {
                    if (i < length - 2)
                        count += counts[i + 2];
                    else
                        count += 1;
                }
            }
            counts[i] = count;
        }
        System.out.println(Arrays.toString(counts));
        return counts[0];
    }

    /*
    * 面试题47，礼物的最大值
    *
    * */
    //解法一：动态规划，用maxValues[i][j]来暂存坐标为（i，j）的格子时能拿到礼物的最大值
    private static int getMaxValue_solution1(int[] values, int rows, int cols) {
        if (values.length <= 0 || rows <= 0 || cols <= 0)
            return 0;
        int[][] maxValues = new int[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                int left = 0;
                int up = 0;
                if (i > 0)
                    up = maxValues[i - 1][j];
                if (j > 0)
                    left = maxValues[i][j - 1];
                maxValues[i][j] = Math.max(up, left) + values[i * cols + j];
            }
        }
        return maxValues[rows - 1][cols - 1];   //走到右下角的最大价值
    }

    public static void main(String[] args) throws Exception {
        //面试题39
//        int[] num = new int[]{3, 1, 4, 4, 8, 4, 9, 4, 4};
//        System.out.println(MoreThanHalfNum1(num));

//        System.out.println(Partition(num, 0, num.length - 1));
//        System.out.println(MoreThanHalfNum2(num));
//        System.out.println(MoreThanHalfNum3(num));

        //面试题40
//        int[] num = new int[]{4, 5, 1, 6, 2, 7, 3, 8};
//        GetLeastKNumbers(num, 4);
//        GetLeastKNumbers2(num, 4);

        //面试题45
//        int[] nums = new int[]{3,32,321,5,1,64,4};
//
//        quickSort(nums, 0, 6);
//        System.out.println(Arrays.toString(nums));

        //面试题46

//        int result = getTranslation(12218);
//        System.out.println(result);


        //面试题47
        int[] values = new int[]{1, 10, 3, 8, 12, 2, 9, 6, 5, 7, 4, 11, 3, 7, 16, 5};
        int rows = 4;
        int cols = 4;
        int maxValues = getMaxValue_solution1(values, rows, cols);
        System.out.println(maxValues);


    }

}
