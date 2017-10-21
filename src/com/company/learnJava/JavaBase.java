package com.company.learnJava;

/**
 * Created by himon on 17-9-24.
 */
public class JavaBase {
    /*
    * PriorityQueue
    * */

    private static void testObjectClass(){
        Object o1 = 1;
        Object o2 = 1;
        System.out.println(o1.hashCode());
        System.out.println(o2.hashCode());
        System.out.println(o1.equals(o2));
    }



    public static void main(String[] args) {
        testObjectClass();

    }


}
