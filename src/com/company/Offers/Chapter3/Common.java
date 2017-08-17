package com.company.Offers.Chapter3;

import com.company.BasedClass.ListNode;
import com.company.BasedClass.TreeNode;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;

import static com.company.BasedClass.ListNode.buildListNode;

public class Common {
    /*面试题16,求c中的power(base,exponent)求base的exponent次方函数java实现，
    * 需要考虑的问题：
    * 1.边界,base=0无意义
    * 2.exponent是正数和负数的处理不同，（负数时先求exponent的绝对值次方然后求倒数).
    *
    * */
    private static double Power(double base, int exponent) throws Exception {

        if (base == 0)
            throw new Exception("error input!");
//        if (base == 1 || exponent==0)
//            return 1;
        double result;
        int absExponent = exponent;
        if (exponent < 0)
            absExponent = -exponent;

        result = PowerWithUnsignedExponent1(base, absExponent);

        if (exponent < 0)
            return 1.0 / result;
        else
            return result;

    }

    private static double PowerWithUnsignedExponent1(double base, int exponent) {
        double result = 1.0;
        for (int i = 0; i < exponent; i++) {
            result *= base;
        }
        return result;
    }

    /*如果exponent是32，需要循环32次，可以用递归优化之，并用位运算来代替‘/’，‘%’运算*/
    private static double PowerWithUnsignedExponent2(double base, int exponent) {
        if (exponent == 0)
            return 1;
        if (exponent == 1)
            return base;

        // “ a/2 ==> a>>1 ”
        double result = PowerWithUnsignedExponent2(base, exponent >> 1);
        result *= result;

        // “ a%2 == 1(奇数)  ==>  a&1 == 1(奇数)  ”
        if ((exponent & 1) == 1)  //如果n为奇数，还需要另外乘以一个base
            result *= base;

        return result;

    }


    /*面试题17*/
    private static void PrintToMaxOfNDigits1(int n) {
        if (n < 0)
            return;

        char[] number = new char[n];
        System.out.println(number);

    }

    private static boolean Increment(char[] number) {
        boolean isOverflow = false;
        int nTakeOver = 0;//进位
        for (int i = number.length - 1; i >= 0; i--) {
            int nSum = number[i] - '0' + nTakeOver; //对应的数字大小，加上进位
            if (i == number.length - 1)
                nSum++; //最低位加一

            /*判断是不是有进位，如果有进位又有两种情况，当i=0说明是第一个字符有进位，这说明这个数字是”99.9“，
            * 普通进位，设置nTakeOver = 1。
            * 如果没有进位，就直接给number[i]赋值。
            * */
            if (nSum >= 10) { //有进位
                if (i == 0) {
                    isOverflow = true;
                } else {
                    nSum -= 10;
                    nTakeOver = 1;
                    number[i] = (char) (nSum + '0');
                }
            } else { //普通+1的结果保存进数组，+1后程序退出循环。
                number[i] = (char) (nSum + '0');
                break;
            }
        }
        return isOverflow;
    }


    /*打印字符数组，前面的‘0’不打印*/
    private static void PrintNumber(char[] number) {
        boolean isBegining0 = true;
        for (char aNumber : number) {
            if (isBegining0 && aNumber != '0') {
                isBegining0 = false;
            }
            if (!isBegining0)
                System.out.printf("%c", aNumber);
        }
        System.out.print("\t");
    }


    /*全排列*/
    private static void PrintToMaxOfNDigits2(int n) {
        if (n < 0)
            return;
        char[] number = new char[n];
        //第一位全排列赋值
        for (int i = 0; i < 10; i++) {
            number[0] = (char) (i + '0');
            Print1ToMaxOfNDigitsRecursively(number, 0);
        }
    }

    private static void Print1ToMaxOfNDigitsRecursively(char[] number, int index) {
        if (index == number.length - 1) {
            PrintNumber(number);
            return;
        }
        //给index下一位赋值
        for (int i = 0; i < 10; i++) {
            number[index + 1] = (char) (i + '0');
            Print1ToMaxOfNDigitsRecursively(number, index + 1);
        }
    }

    /*面试题20*/
    static int s = 0;

    private static boolean isNumeric(String string) {
        if (string.isEmpty())
            return false;
        char[] str = string.toCharArray();
        boolean numeric = scanInteger(str);
        if (s < str.length && str[s] == '.') {
            s++;
            numeric = scanUnsignedInteger(str) || numeric;
        }
        if (s < str.length && (str[s] == 'e' || str[s] == 'E')) {
            s++;
            numeric = numeric && scanInteger(str);
        }

        return numeric && (s == str.length);
    }

    private static boolean scanInteger(char[] str) {
        if (str[s] == '+' || str[s] == '-')
            s++;
        return scanUnsignedInteger(str);
    }

    private static boolean scanUnsignedInteger(char[] str) {
        int numEnd = s;
        while (s < str.length && str[s] >= '0' && str[s] <= '9')
            s++;
        return numEnd < s;
    }

    /*面试题21,调整整数数组,奇数在前,偶数在后*/
    private static int[] reorderOddEven(int[] nums) throws Exception {
        if (nums.length == 0)
            throw new Exception("null arrays");

        int head = 0, tail = nums.length - 1;
        int temp;
        while (head <= tail) {
            if (((nums[head] & 1) == 1) && ((nums[tail] & 1) == 1)) { //都为奇数
                head++;
            } else if (((nums[head] & 1) == 0) && ((nums[tail] & 1) == 0)) {
                tail--;
            } else if (((nums[head] & 1) == 0) && ((nums[tail] & 1) == 1)) {
                temp = nums[head];
                nums[head] = nums[tail];
                nums[tail] = temp;
                head++;
                tail--;
            } else {
                head++;
                tail--;
            }
        }

        return nums;
    }

    /*面试提22, 链表中倒数地k个节点,
    * 两个指针,头指针先走到k-1个节点,然后尾指针开始一起走,当头指针走到链表结尾,此时尾指针所在的节点就是倒数第k个
    * */
    private static ListNode findKthToTail(ListNode head, int k) {
        if (head==null||k==0)
            return null;
        ListNode pHead = head;
        ListNode pBehind = head;

        for (int i=0;i<k-1;i++){
            if (pHead.next!=null)
                pHead = pHead.next;
            else
                return null;
        }

        while (pHead.next!=null){
            pHead = pHead.next;
            pBehind = pBehind.next;
        }

        return pBehind;
    }


    public static void main(String[] args) throws Exception {
//        System.out.println(Power(2,-3));
//        System.out.println(Power(2,-3));

//        System.out.println('9'-'0');
//        PrintNumber(new char[]{'0','1','2'});
//        PrintToMaxOfNDigits2(2);

//        System.out.println(isNumeric("e"));
//        int[] nums = new int[]{1, 2, 3, 4, 5, 6, 7, 8};
//        System.out.println(Arrays.toString(reorderOddEven(nums)));
//        System.out.println(nums[0]&1);
//        System.out.println(nums[1]&1);

        Object[] obj = {1, 2, 3, 4, 5, 6, 7, 8};
        ListNode h2 = buildListNode(obj);
        System.out.println(findKthToTail(h2, 1).value);


    }
}
