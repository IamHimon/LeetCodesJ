package com.company.Offers.Chapter5;

import com.company.Offers.Chapter2.Array;
import org.omg.CORBA.INTERNAL;

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
    *用一个最大堆实现左边的数据容器,用一个最小堆实现右边的数据容器.
    * 在新数据过来时要保证: (1)数据平均分配到两个堆中,则总数为偶数时插入到最小堆,奇数插入到最大堆.
    * (2)最大堆中所有的数据小于最小堆中的数据.需要将新数据跟最小堆以及最大堆的heap比较,来做相应处理.
    * */
    private static PriorityQueue<Integer> maxHeap, minHeap;

    private static void addFlowNums(int[] nums) {

        Comparator<Integer> revCamp = new Comparator<Integer>() {
            @Override
            public int compare(Integer left, Integer right) {
                return right.compareTo(left);
            }
        };

        maxHeap = new PriorityQueue<>(20, revCamp);
        minHeap = new PriorityQueue<>(20);

        for (int n : nums) {
            insertElem(n);
        }
        System.out.println(getMedian());

    }

    private static void insertElem(int num) {
        if ((maxHeap.size() + minHeap.size() & 1) == 0) {  //奇数->minHeap(右边)
            //如果num比maxHeap中的最大值还小，则先将num加入maxHeap中，再取maxHeap中的最大值取出来，插入minHeap中
            if (maxHeap.size() > 0 && num < maxHeap.peek()) {
                maxHeap.add(num);
                num = maxHeap.peek(); //num为最大值
                maxHeap.remove(num);
            }

            minHeap.add(num);
        } else {     //偶数->maxHeap(左边)

            //如果num比minHeap中最小的值还大，则现将num加入到minHeap中，再取出minHeap中的最小值，插入到maxHeap中
            if (minHeap.size() > 0 && num > minHeap.peek()) {
                minHeap.add(num);
                num = minHeap.peek();
                minHeap.remove(num);
            }
            maxHeap.add(num);
        }
    }

    private static double getMedian() {
        if (maxHeap.isEmpty())
            return -1;
        int size = minHeap.size() + maxHeap.size();
        if ((size & 1) == 1) {  //奇数
            return minHeap.peek();
        } else {
            return (double) (minHeap.peek() + maxHeap.peek()) / 2;
        }
    }


    /*面试题42,连续子数组的最大和*/
    private static int greatestSumOfSubArray(int[] nums) {
        if (nums.length <= 0)
            return 0;

        int sum = 0;
        int maxSum = 0;
        int low = 0;//和最大子数组的左边界
        int high = 0;
        int subHigh = 0;//记下和最大子数组的右边界

        while (low < nums.length && high < nums.length) {
            sum += nums[high];
            if (sum < 0) {      //如果sum为负,责令sum为初始值0,且更新low,high指向下一个位置.
                sum = 0;
                low = high + 1;
                high++;
            } else {
                high++;
            }
            if (maxSum < sum) {
                maxSum = sum;
                subHigh = high;
            }
        }
        int[] subArray = new int[subHigh - low];
        for (int i = low; i < subHigh; i++) {
            subArray[i - low] = nums[i];
        }
        System.out.println(Arrays.toString(subArray));
        System.out.println(maxSum);

        return maxSum;
    }

    //解法二,动态规划
    /*
    *下面是求f(i)的公式:
    * f(i) = nums[i]        , i=0或者f(i-1)<0
    *       f(i-1) + nums[i], i>0或者f(i-1)>=0
    *
    * 因此我们需要求出max(f(i)),其中0<=i<=n.
    * */
    private static int greatestSumOfSubArray2(int[] nums) {
        if (nums.length <= 0)
            return 0;

        int sum = 0;    //即是f(i)
        int maxSum = 0;     //即是max(f(i))

        for (int i = 0; i < nums.length; i++) {
            if (i == 0 || sum < 0) {
                sum = nums[i];
            } else {
                sum += nums[i];
            }

            if (maxSum < sum)
                maxSum = sum;
        }

        System.out.println(maxSum);
        return maxSum;
    }

    /*面试题44,
    * 数字序列中某一位的数字
    *
    * */
    private static int digitAtIndex(int index) {
        int i = 1;
        int sum = 1;
        //确定index在几位数段
        while (sum <= index) {
            sum += 9 * Math.pow(10, i - 1) * i;
            i++;
        }
        i -= 1;

        //index减去前面段的位数,(如:-10*1,-90*2,-900*3等)
        index--; //先减一,因为当i为2,1段的位数是10,而循环中是减去9
        for (int j = 1; j < i; j++) {
            index -= 9 * Math.pow(10, j - 1) * j;
        }
        int b = index / i; //倍数
        int y = index % i;  //余数

        int num = (int) Math.pow(10, i - 1) + b;
        int result = (num / (int) Math.pow(10, y)) % 10;

        System.out.println(result);
        return result;
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

        //面试题41
//        addFlowNums(num);

        //面试题42
//        int[] nums = new int[]{1, -2, 3, 10, -4, 7, 2, -5};
//        greatestSumOfSubArray(nums);
//        greatestSumOfSubArray2(nums);

        //面试题44
        digitAtIndex(13);

    }

}
