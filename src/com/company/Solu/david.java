package com.company.Solu;

public class david {

    private static int x = 100;

    public static void main(String[] args){
        int x = 3,y=6,a=0;
        while(x++!=(y-=1)){
            a+=1;
            if(y<x)break;
        }
        System.out.println(x+" "+y+" "+a);

    }

}
