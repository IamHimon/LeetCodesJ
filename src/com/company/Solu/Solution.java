package com.company.Solu;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by student on 2017/7/15.
 */
public class Solution {

    /*check whether overflow? <leetcode>
*if overflow, the new result will not equal to the previous one.
* */
    public static int seven(int num) {
        int remainder;
        int result=0;
        int temp;
        while (true){
            remainder = num%10;
            temp = result;
            result = result*10 + remainder;
            if ((result - remainder)/10 != temp){
                return 0;
            }
            num = num/10;
            if (num == 0 ){
                break;
            }
        }
        return result;
    }
    /*
    * 直接用int跟Integer.MAX_VALUE和Integer.MIN_VALUE比不行，那么可以用double来存result，
    * 然后返回的结果在强制转换成int。
    * 或者直接用BigInteger。
    * */
    public static int seven2(int num){
        long result=0;
        int remainder;
        while (num!=0){
            remainder = num%10;
            result = result*10 + remainder;
            if (result>Integer.MAX_VALUE||result<Integer.MIN_VALUE){
                return 0;
            }
        }
        return (int)result;
    }


    public static int eight(String string) {
        /*用来判断是不是前一个字符是不是数字，比如“12 342”，让到字符'3'，
        因为前一个字符是空格，所以is_digitandlabel=false，所以会break掉程序。*/
        boolean is_digitandlabel= true;
        int flag = 1;
        long result = 0;
        /*num_flag,应对有两个正负号的情况，比如"+-1234",这时候num_flag>1,是错误输入return 0;*/
        int num_flag = 0;
        //去掉开头的空格
        string.trim();
//        while (string.startsWith(" ")) {//这里判断是不是空格
//            string = string.substring(1, string.length()).trim();
//        }
        for (int i = 0; i < string.length(); i++) {
//            System.out.println(is_digitandlabel);
            if (string.charAt(i) == ' ') {
                is_digitandlabel = false;
                continue;
            }else if (string.charAt(i) == '+'){
                is_digitandlabel = true;
                flag = 1;
                num_flag = num_flag +1;
            }
            else if ((string.charAt(i) == '-')&&(flag==1)){
                is_digitandlabel = true;
                flag = -1;
                num_flag = num_flag +1;
            }
            else if (Character.isDigit(string.charAt(i))&&is_digitandlabel) {
                is_digitandlabel = true;
                result = result * 10 + ((int) string.charAt(i) - 48);
                if (result>Integer.MAX_VALUE||result<Integer.MIN_VALUE) {
                    System.out.println("overflow");
                    return flag==1?Integer.MAX_VALUE:Integer.MIN_VALUE;
                }
            }else {
                break;
            }
        }
        result = result*flag;
        if (num_flag>1)
            return 0;
        return (int)result;
    }
/*
* java char to int: int x=str1.charAt(0)-'0';
* 然后判断是不是数字字母： if (x>=0&&x<=9) return true;
* */
    public static int eight_L(String str){
        if (str.length() == 0) return 0;
        //remove only the leading and trailing white spaces
        str = str.trim();

        int sign=1,total=0,index=0,len=str.length(),val=0;
        if (str.charAt(index)=='+'||str.charAt(index)=='-'){
            sign = str.charAt(index)=='+'?1:-1;
            index += 1;
        }
        while (index<len){
            val = str.charAt(index)-'0';
            if (val<0||val>9) break;
            if (total>Integer.MAX_VALUE/10||(total==Integer.MAX_VALUE/10&&val>Integer.MAX_VALUE%10)){
                return sign == 1 ? Integer.MAX_VALUE :Integer.MIN_VALUE;
            }
            total = total*10 + val;
            index++;
        }
        return sign*total;
    }

    public static boolean nine(int x){
        if (x>Integer.MAX_VALUE||x<Integer.MIN_VALUE)
            return false;
        Integer remainder=0;
        ArrayList<Integer> remainders = new ArrayList<>();
        while (x>0){
            remainder = x%10;
            remainders.add(remainder);
            x = x/10;
        }
        for (int i=0;i<remainders.size()/2;i++){
            if (remainders.get(i) != remainders.get(remainders.size()-i-1))
                return false;
        }
        return true;
    }

    /*no overflow risk*/
    public static boolean nine_L(int x){
        if (x<0) return false;
        int p=x, q=0;
        while (p>=10){
            q *= 10;
            q = q + p%10;
            p = p/10;
        }
        return q==x/10&&p==x%10;
    }

}
