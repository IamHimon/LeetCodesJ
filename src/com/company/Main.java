package com.company;

import java.util.ArrayList;
import java.util.Objects;

import static com.company.Solu.Solution.*;

public class Main {

    public static void main(String[] args) {
        // write your code here
//        System.out.println(eight("2147483648"));
//        System.out.println(eight("18446744073709551617"));
//        System.out.println(eight_L("18446744073709551617"));
//        System.out.println(eight_L("+-1234"));
//        System.out.println(eight("+-1234"));
//        char a = 1;
//        System.out.println(nine(1234));\
//        System.out.println(-2147483648);
//        System.out.println(nine(-2147483648));
//        System.out.println(nine_L(12321));
//        System.out.println("hehe");
        Object[] vals = new Object[]{1, 2, 3, 4, null, 6, 7};
        for (Object o : vals)
            System.out.println(o);
        ArrayList<Object> v = new ArrayList<>();
        v.add(1);
        v.add(1);
        v.add(null);
        v.add(2);
        System.out.println(v);
//        mergeTrees();
//        System.out.println(13/2);
//        System.out.println(14/2);
    }
}
