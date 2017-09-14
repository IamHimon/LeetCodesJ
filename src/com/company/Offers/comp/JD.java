package com.company.Offers.comp;

import com.company.Main;

import java.util.Arrays;

/**
 * Created by himon on 17-9-7.
 */
public class JD {

    private static int[] changeStage(int index, int[] bolb){
        int[] change_bold = new int[bolb.length];
        for (int i=0;i<index;i++) {
            change_bold[i] = bolb[i];
        }
        for (int i=index;i<bolb.length;i++) {
//            bolb[i] = Math.abs(bolb[i]-1);
            change_bold[i] = Math.abs(bolb[i]-1);
        }
        System.out.println(Arrays.toString(change_bold));
        return change_bold;
    }

    private static boolean isLoseState(int[] bold){
        boolean result = true;
        int i=0;
        for (;i<bold.length;i++){
            if (bold[i]==1)
                break;
        }

        for (int j=i;j<bold.length;j++){
            if (bold[j] == 0)
                result = false;
        }
        System.out.println(result);
        return result;
    }

    private static boolean isWinState(int[] bold){
        boolean result = true;
        for (int i=0;i<bold.length;i++){
            if (bold[i]==1)
                result = false;
        }
        return result;
    }
    public static int count = 0;

    private static void change(int[] bolb){
        count++;
        for (int i=0;i<bolb.length;i++){
            if (bolb[i]==1 && isWinState(changeStage(i, bolb))){
                System.out.println("win");
                break;
            }

            if (bolb[i] == 1 && !isLoseState(changeStage(i, bolb))){
//                System.out.println("win step!"+i);
                change(changeStage(i, bolb));
            }

            if (i == bolb.length-1) {
                System.out.println("stop!"+count);
                System.out.println(Arrays.toString(bolb));
                break;
            }

        }
    }



    public static void main(String[] args) {
        int[] bolb = new int[]{1,1,1,0,0,0};
//        int[] bolb2 = new int[]{0,1,1,0};


//        isLoseState(changeStage(1, bolb));

        change(bolb);
        System.out.println(count);
//        System.out.println(isLoseState(changeStage(0, bolb)));
//        System.out.println(isLoseState(changeStage(1, bolb)));
//        System.out.println(isLoseState(changeStage(2, bolb)));




    }


}
