package com.company.leetcodeJ;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by himon on 17-9-26.
 */
public class Solution {
    //判断是不是只需要走一不
    public boolean isOneStep(String s1, String s2){
        int diff_char_count = 0;
        for (int i=0;i<s1.length();i++){
            if (s2.charAt(i)==s2.charAt(i))
                diff_char_count++;
        }
        return diff_char_count == 1;
    }

    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {


        return null;
    }

    public static void main(String[] args) {
        String beginWord = "hit";
        String endWord = "cog";
//        List<String> wordList = new ArrayList<String>(){"hot", "dot", "dog", "lot", "log", "cog"};
        List<String> wordList = Arrays.asList("hot", "dot", "dog", "lot", "log", "cog");

    }
}
