package com.company;

import com.company.Offers.Chapter2.Array;
import com.company.Offers.Chapter2.String_p;

import java.util.Arrays;

/**
 * Created by �� on 2017/7/22.
 */
public class JSort {

    /*��������(����)*/
    public static void quickSort(int[] A, int low, int high){
        if (low >= high)//��������
            return;
        int i = low;
        int j = high;
        int key = A[i]; //һ���һ����Ϊkey
        int temp;
        while (i<j){
            while (i<j && A[j] > key){ //�Ӵ�С���ҵ���keyС���±�j
                j -= 1;
            }
            temp = A[i];    //����A[j]��A[i]
            A[i] = A[j];
            A[j] = temp;    //��ʱkey����A[j]λ��
            while (i<j && A[i] <= key) {    //�ٴ�С�����ҵ���key����±�i
                i += 1;
            }
            temp = A[j];    //����A[i]��A[j]
            A[j] = A[i];
            A[i] = temp;    //��ʱkey����A[i]λ��
        }
        quickSort(A, low, high - 1); //��key�ұ��ٽ���
        quickSort(A, low+1, high); //��key����ٽ���
    }

    /*��Ա���������򣬹涨Ա������ķ�Χ��0-99
    *�ȳ�ʼ��timeOfAges[]��Ϊ0,Ȼ���������ages������������������˶��ٴξ���ages�������ü��θ�����
    *
    * */
    public static void SortAge(int ages[]) throws Exception {
        if (ages.length<=0)
            return;

        int oldestAge = 99;
        int[] timeOfAge = new int[oldestAge + 1]; //��������ֵĴ���

        for (int age : ages) {
            if (age < 0 && age > oldestAge)
                throw new Exception("age out of range");
            timeOfAge[age]++;
        }
//        System.out.println(Arrays.toString(timeOfAge));

        /*index��ages������±꣬�����¸�ages���鸳ֵ
        * ��0-99�꿪ʼ��������i�����timeOfAge[i]��ֵ����������㣬˵�������������֣�Ȼ��Ž���ڶ���forѭ����ֻ�е������ڶ���for���ܸ�index��1
        * �ڵڶ���forѭ��������ages���鸳ֵ���Ҹ���ֵΪi����Ϊi�Ǵ�С����0-99���ģ�����Ҳ���൱��С���ȷŽ�ages������൱�������ˡ�
        * */
        int index = 0;
        for (int i=0;i<oldestAge;i++){
            for (int j=0;j<timeOfAge[i];j++){
                ages[index] = i;
                index++;
            }
        }
//        System.out.println(Arrays.toString(ages));

    }

    public static void main(String[] args) throws Exception {
        int[] a = {57, 14,68, 59, 52, 72, 28, 96, 33, 24};
//        quickSort(a, 0, a.length-1);
//        System.out.println(Arrays.toString(a));
        SortAge(a);
    }
}
