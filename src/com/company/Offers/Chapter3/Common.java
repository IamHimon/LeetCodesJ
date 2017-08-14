package com.company.Offers.Chapter3;

import java.math.BigInteger;

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


    /*面试题19*/
    private static boolean match(String str, String pattern) {
        if (str.isEmpty() && pattern.isEmpty())
            return false;

        char[] Str = str.toCharArray();
        char[] Pattern = pattern.toCharArray();

        if (Str.length > Pattern.length)
            return false;

        int s = 0, p = 0;


        return matchCore(Str, s, Pattern, p);
    }

    static private boolean matchCore(char[] str, int s, char[] pattern, int p) {
        if (s == str.length && p == pattern.length) {
            return true;
        }
        if (s < str.length && p == pattern.length)
            return false;

        if (p + 1 < pattern.length && pattern[p + 1] == '*') {
            if (str[s] == pattern[p] || (pattern[p] == '.' && s < str.length)) {
                return matchCore(str, s + 1, pattern, p + 2) ||
                        matchCore(str, s + 1, pattern, p) ||
                        matchCore(str, s, pattern, p + 2);
            } else {
                return matchCore(str, s, pattern, p + 2);
            }
        }

        if (str[s] == pattern[p] || (pattern[p] == '.' && s < str.length)) {
            return matchCore(str, s + 1, pattern, p + 1);
        }

        return false;
    }

    /*面试题20,判断输入的字符串是否表示数值，包括整数和小树*/
    private static boolean isNum(String str) {

        String newStr = str.trim();
        char[] Str = newStr.toCharArray();
        if (Str.length == 0 )
            return false;
        //开头只能是'+','-',数字
        if (Str[0]=='.'||Str[0]=='+'||Str[0]=='-'||(Str[0]<='9'&&Str[0]>='0'))
            return isNumCore(Str, 0);
        else
            return false;
    }

    private static boolean isNumCore(char[] str, int s) {
        boolean isAllNum;
        if (s == str.length)
            return true;
        //当前字符是'+'或者'-',下一个字符只能是数字
        if (s + 1 < str.length && (str[s] == '+' || str[s] == '-') && (str[s + 1] <= '9' && str[s + 1] >= '0'))
            return isNumCore(str, s + 1);

        //当前字符是'.'，下个字符只能是数字或者空白
        if (str[s] == '.' && (s + 1 < str.length && (str[s + 1] <= '9' && str[s + 1] >= '0'))||(s+1==str.length))
            return isNumCore(str, s + 1);

        //当前字符是'e',后面所有字符，一直到结尾只能是数字或者后面一个字符是'+','-'
        if (s+1<str.length && str[s] == 'e') {
            isAllNum = true;
            for (int i = s+1; i < str.length; i++) {
                if (str[i] > '9' || str[i] < '0') {
                    isAllNum = false;
                    break;
                }
            }
            return isAllNum;
        }
        //当前字符是数字,下个字符只能是数字,'.','e'

        if (str[s]>='0'&&str[s]<='9')
            return isNumCore(str, s + 1);

        return false;
    }
    /*可能遵循的模式：A[.[B]][e|EC]或者.B[e|EC],其中A,C都是整数部分可能是负数，B是无符号整数*/


    public static void main(String[] args) throws Exception {
//        System.out.println(Power(2,-3));
//        System.out.println(Power(2,-3));

//        System.out.println('9'-'0');
//        PrintNumber(new char[]{'0','1','2'});
//        PrintToMaxOfNDigits2(2);
//        System.out.println(match("aba", "a*a"));
        System.out.println(isNum("."));
//        System.out.println(Double.parseDouble(".1"));


    }
}
