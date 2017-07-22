package com.company.Offers.Chapter2;

import java.util.Arrays;

/**
 * Created by รอ on 2017/7/22.
 */
public class String_p {
    public static String replaceBlank(String string) {
        char[] strChar = string.toCharArray();
        if (string.isEmpty())
            return "";
        int numberOfBlank = 0;
        for (int i = 0; i < string.length(); i++) {
            if (string.charAt(i) == ' ')
                numberOfBlank += 2;
        }

        char[] replaceString = new char[string.length() + numberOfBlank];
        int indexOfOriginal = string.length()-1;
        int indexOfNew = string.length() + numberOfBlank-1;

        while (indexOfOriginal>=0 && indexOfNew>=indexOfOriginal){
            if (string.charAt(indexOfOriginal) != ' '){
                replaceString[indexOfNew--] = string.charAt(indexOfOriginal);
            }else{
                replaceString[indexOfNew--] ='0';
                replaceString[indexOfNew--] ='2';
                replaceString[indexOfNew--] ='%';
            }
            indexOfOriginal--;
        }
//        System.out.println(replaceString);

        return String.valueOf(replaceString);

    }

    public static void main(String[] args) {
        String str =  " we are  happy! ";
        System.out.println(replaceBlank(str));
    }
}
