package com.company.Solu;

/**
 * Created by student on 2017/7/15.
 */
public class Solution {
    public static int eight(String string) {
        boolean is_digitandlabel= true;
        int flag = 1;
        long result = 0;
        int num_flag = 0;

        //去掉开头的空格
        while (string.startsWith(" ")) {//这里判断是不是全角空格
            string = string.substring(1, string.length()).trim();
        }
        System.out.println(string);
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
            }else {
                break;
            }
        }
        result = result*flag;

        if (result>Integer.MAX_VALUE||result<Integer.MIN_VALUE)
            return 0;
        if (num_flag>1)
            return 0;

        return (int)result;
    }
}
