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

    /*
    * 面试题49,找到第1500个丑数
    * */
    //只能被2,3,5整除,则连续除以2,3,5,最后结果如果是1,则是丑数
    private static boolean isUglyNum(int number) {
        while (number % 2 == 0)
            number /= 2;
        while (number % 3 == 0)
            number /= 3;
        while (number % 5 == 0)
            number /= 5;
        return number == 1;
    }

    //解法一: 遍历
    private static int getUglyNum(int index) {
        if (index <= 0)
            return 0;
        int uglyFound = 0;
        int result = 0;
        while (uglyFound < index) {
            result++;
            if (isUglyNum(result)) {
//                System.out.println(result);
                uglyFound++;
            }
        }
        return result;
    }

    //解法二:
    private static int getUglyNum2(int index) {
        if (index <= 0)
            return 0;

        List<Integer> uglyNums = new LinkedList<>();    //记录已有丑数
        int uglyFound = 1;  //需要找uglyFound个丑数
        int M = 1;      //已有最大丑数
        int T2 = 0;     //在已有丑数中,第一个乘以2比M大的下标
        int T3 = 0;     //在已有丑数中,第一个乘以3比M大的下标
        int T5 = 0;     //在已有丑数中,第一个乘以5比M大的下标

        uglyNums.add(M);    //第一个丑数是1,加入到list中

        while (uglyFound < index) {
            //标志是不是找到相应下标,用来控制在一层循环中完成寻找T2,T3,T5
            boolean t2IsFound = false;
            boolean t3IsFound = false;
            boolean t5IsFound = false;
            //find T2,T3,T5
            for (int i = 0; i < uglyNums.size(); i++) {
                if (!t2IsFound && 2 * uglyNums.get(i) > M) {
                    T2 = i;
                    t2IsFound = true;
                }
                if (!t3IsFound && 3 * uglyNums.get(i) > M) {
                    T3 = i;
                    t3IsFound = true;
                }
                if (!t5IsFound && 5 * uglyNums.get(i) > M) {
                    T5 = i;
                    t5IsFound = true;
                }
            }
            T2++;
            T3++;
            T5++;
            //update M
            M = min(2 * T2, 3 * T3, 5 * T5);
            uglyNums.add(M);
            uglyFound++;
        }
//        System.out.println(uglyNums.get(uglyNums.size()-1));
        return uglyNums.get(uglyNums.size() - 1);
    }

    private static int min(int i, int i1, int i2) {
        int max1 = i <= i1 ? i : i1;
        return max1 <= i2 ? max1 : i2;
    }

    /*面试题48,最长不含重复字符的子字符串
    *
    * 动态规划
    * position数组用来存储每个字符上次出现在字符串中位置的下标,没有出现则为-1
    * f(i)表示以当前字符为结尾的最长不重复子串长度
    * 当 S(i) 之前没有出现过, f(i) = f(i-1)+1
    * 否则:
    *       当distance <= f(i-1), f(i)=d
    *       否则:    f(i) = f(i-1)+1;
    *
    * */
    private static void longestSubstringWithoutDublication(String str) {
        if (str.isEmpty()) {
            return;
        }
        int length = str.length();
        int[] position = new int[26];
        for (int i = 0; i < 26; i++)
            position[i] = -1;

        char[] S = str.toCharArray();
        int[] f = new int[length];

        for (int i = 0; i < length; i++) {
            if (position[S[i] - 'a'] == -1) { //之前没有出现过
                if (i > 1)
                    f[i] = f[i - 1] + 1;
                else    //如果从0开始
                    f[i] = 1;
            } else {
                int distance = i - position[S[i] - 'a'];    //当前字符距离上次出现的距离
//                System.out.println(S[i]+ ": "+distance);
                if (distance > f[i - 1]) {
                    if (i > 1)
                        f[i] = f[i - 1] + 1;
                    else
                        f[i] = 1;
                } else {
                    f[i] = distance;
                }

            }
            position[S[i] - 'a'] = i;   //更新当前字符出现的下标
        }
        System.out.println(Arrays.toString(f));
    }

    /*面试题50,第一个只出现一次的字符*/
    private static void firstSingleChar(String string) {
        if (string.isEmpty())
            return;
        int length = string.length();
        char[] A = new char[length];
        int i = length - 1;
        for (char c : string.toCharArray()) {  //翻转字符串
            A[i--] = c;
        }
//        System.out.println(A);
        int[] COUNT = new int[256];  //用来存放每个字符出现的次数,字符是8bit的类型,总共有256个字符
        char lastSingleChar = A[0];
        for (int j = 0; j < length; j++) {
            if (COUNT[A[j]] == 0)   //最后一个出现次数为0的就是结果
                lastSingleChar = A[j];
            COUNT[A[j]]++;
        }
//        System.out.println(Arrays.toString(COUNT));
        System.out.println(lastSingleChar);

    }

    /*面试题51, 数组中的逆序对
    *
    *
    * */
    private static int inversePairs(int[] data) {
        if (data.length == 0)
            return 0;
        int[] copy = new int[data.length];
        System.arraycopy(data, 0, copy, 0, data.length);
        int count = inversePairsCore(data, copy, 0, data.length - 1);
        System.out.println(count);
        return count;
    }

    private static int inversePairsCore(int[] data, int[] copy, int start, int end) {
        if (start == end) {
            copy[start] = data[start];
            return 0;
        }
        int length = (end - start) / 2;
        int left = inversePairsCore(copy, data, start, start + length); //调换copy和data!精妙!
        int right = inversePairsCore(copy, data, start + length + 1, end);

        int i = start + length; //前半段最后一个数字下标
        int j = end;    //后半段最后一个数字下标
        int copyIndex = end;
        int count = 0;
        while (i >= start && j >= start + length + 1) {
            if (data[i] > data[j]) {
                count += (j - start - length);
                copy[copyIndex--] = data[i--];
            } else {
                copy[copyIndex--] = data[j--];
            }
        }
        while (i >= start)
            copy[copyIndex--] = data[i--];
        while (j >= start + length + 1)
            copy[copyIndex--] = data[j--];
        return left + right + count;
    }

    //C.length = A.length + B.length
    private static int mergeAndSort(int[] A, int[] B, int[] C) {
        if (A.length == 0 || B.length == 0)
            return 0;

        int count = 0;
        int a = A.length - 1;
        int b = B.length - 1;
        int c = A.length + B.length - 1;
        while (a >= 0 && b >= 0) {
            if (A[a] > B[b]) {
                count += b + 1;
                C[c--] = A[a];
                a--;
            } else {
                C[c--] = B[b];
                b--;
            }
        }
        //将剩余的放入C中
        while (a >= 0)
            C[c--] = A[a--];
        while (b >= 0)
            C[c--] = B[b--];

        System.out.println(Arrays.toString(C));
        System.out.println(count);
        return count;
    }

    /*面试题53,题目一:在排序数组中查找数字*/

    /*面试题53,题目二, 0~n-1中缺失的数字
    * 用二分法查找第一个数值与下标不相同的数字
    * 如果值与下标相等:left=mid+1
    * 否则:
    *       如果前一位值与下标不相等: right = mid -1
    *       否则: return mid;(找到结果了)
    * */
    private static int findMissingNum(int[] num) {
        if (num.length == 0)
            return -1;
        int left = 0;
        int right = num.length;

        while (left <= right) {
            int mid = (right + left) >> 1;
            if (num[mid] == mid) {
                left = mid + 1;
            } else {
                if (mid == 0 || num[mid - 1] == mid - 1) {
                    right = mid - 1;
                } else {
                    return mid;
                }
            }
        }
        if (left == num.length)
            return num.length;

        System.out.println(left);
        return left;
    }

    /*题目三:查找数组中数值和下标相等的元素*/
    private static int getNumberSameAsIndex(int[] num) {
        if (num.length == 0)
            return -1;
        int left = 0;
        int right = num.length;
        while (left <= right) {
            int mid = (left + right) >> 1;
            if (num[mid] == mid) {
                return mid;
            } else {
                if (num[mid] < mid)
                    left = mid + 1;
                else
                    right = mid - 1;
            }
        }
        return -1;
    }

    /*面试题56,数组中数字出现的次数*/
    //题目一,数组中祝出现一次的数字

    private static void indexOf1(int[] num) {
        if (num.length == 0)
            return;
        int resultExclusive = 0;
        for (int n : num)
            resultExclusive ^= n;

        int indexOf1 = findFirstBiIs1(resultExclusive);
        int num1 = 0, num2 = 0;
        for (int n:num){
            if (isBit1(n, indexOf1))
                num1 ^= n;
            else
                num2 ^= n;
        }
        System.out.println(num1);
        System.out.println(num2);
    }

    private static int findFirstBiIs1(int num) { //找到最右边是1的位
        int indexBit1 = 0;
        while (((num & 1) == 0) && indexBit1 < 32) {
            num = num >> 1;
            indexBit1++;
        }
        return indexBit1;
    }

    private static boolean isBit1(int num, int indexBit1) {  //判断num的地indexBit1位是不是1
        num = num >> indexBit1;
        return (num & 1) == 1;
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

        //面试题45
//        int[] nums = new int[]{3,32,321,5,1,64,4};
//
//        quickSort(nums, 0, 6);
//        System.out.println(Arrays.toString(nums));

        //面试题46

//        int result = getTranslation(12218);
//        System.out.println(result);


        //面试题47
//        int[] values = new int[]{1, 10, 3, 8, 12, 2, 9, 6, 5, 7, 4, 11, 3, 7, 16, 5};
//        int rows = 4;
//        int cols = 4;
//        int maxValues = getMaxValue_solution1(values, rows, cols);
//        System.out.println(maxValues);
        //面试题49
//        System.out.println(getUglyNum(10));
//        System.out.println(getUglyNum2(10));

//        System.out.println('a' - 97);
//        System.out.println('b' - 97);
        //面试题48
//        longestSubstringWithoutDublication("arabcacfr");

        //面试题50
//        firstSingleChar("abaccdeff");

        //面试题51
//        mergeAndSort(new int[]{5, 7}, new int[]{4}, new int[3]);
//        inversePairs(new int[]{7, 5, 6, 4});

//        System.out.println(findMissingNum(new int[]{0, 1, 2, 4, 5, 6}));
//        System.out.println(getNumberSameAsIndex(new int[]{-3,-1,1,3,5}));

        //面试题题56
//        System.out.println(3 ^ 4 );
//        System.out.println(findFirstBiIs1(4));
        indexOf1(new int[]{2, 4, 3, 6, 3, 2, 5, 5});

    }

}
