package com.company.Offers.Chapter2;

import java.util.Arrays;

/**
 * Created by student on 2017/7/22.
 */
public class Common {
    public static long Fibonacci(int n) {
        if (n <= 0)
            return 0;
        else if (n == 1)
            return 1;
        else
            return Fibonacci(n - 1) + Fibonacci(n - 2);
    }

    public static long Fibonacci_cur(int n) {
        int[] result = {0, 1};
        if (n < 2)
            return result[n];

        long fibNMiusOne = 1;
        long fibNMiusTwo = 0;
        long fibN = 0;
        for (int i = 2; i <= n; i++) {
            fibN = fibNMiusOne + fibNMiusTwo;
            fibNMiusTwo = fibNMiusOne;
            fibNMiusOne = fibN;
        }
        return fibN;
    }

    /*面试题12*/
    public static boolean hasPath(char[][] matrix, String str) {
        if (matrix.length < 1 || matrix[0].length < 1 || str.isEmpty())
            return false;
        int rows = matrix.length;
        int cols = matrix[0].length;

        boolean[] visited = new boolean[rows * cols];   //已经初始化为false了

        int pathLength = 0;
        //从左上角点开始
        for (int row = 0; row < rows; row++) {
            for (int col = 0; col < cols; col++) {
                if (hasPathCore(matrix, row, rows, col, cols, pathLength, visited, str))
                    return true;
            }
        }
        return false;
    }

    private static boolean hasPathCore(char[][] matrix, int row, int rows, int col, int cols, int pathLength, boolean[] visited, String str) {
        //路径字符串上所有的字符都在矩阵中找到合适的位置，则结束。
        if (pathLength == str.length())
            return true;
        boolean hasPath = false;
        if (row >= 0 && row < rows && col >= 0 && col < cols && matrix[row][col] == (str.charAt(pathLength)) && !visited[row * cols + col]) {
            ++pathLength;
            visited[row * cols + col] = true;

            /*上下左右*/
            hasPath = hasPathCore(matrix, row, rows, col - 1, cols, pathLength, visited, str) ||
                    hasPathCore(matrix, row, rows, col + 1, cols, pathLength, visited, str) ||
                    hasPathCore(matrix, row - 1, rows, col, cols, pathLength, visited, str) ||
                    hasPathCore(matrix, row + 1, rows, col, cols, pathLength, visited, str);
            /*如果上下左右都没有匹配到相同的，回到前一个字符（pathLength-1）*/
            if (!hasPath) {
                --pathLength;
                visited[row * cols + col] = false;
            }
        }
        return hasPath;
    }

    /*面试题13*/
    public static int movingCount(int cols, int rows, int threshold) {
        if (cols < 1 && rows < 1 && threshold < 0)
            return 0;

        boolean[] visited = new boolean[cols * rows];
        int count;

        count = movingCountCore(0, rows, 0, cols, threshold, visited);//从(0,0)开始移动

        return count;
    }

    private static int movingCountCore(int row, int rows, int col, int cols, int threshold, boolean[] visited) {
        int count = 0;
        /*从坐标点来判断是不是能进入（row, col）这个格子*/
        if (row >= 0 && row < rows && col >= 0 && col < cols && getDigitSum(col, row) <= threshold && !visited[row * cols + col]) {
            visited[row * cols + col] = true;
            /*然后进入下一个格子，将结果相加，用1加上下一步的结果。*/
            count = 1 + movingCountCore(row + 1, rows, col, cols, threshold, visited) +
                    movingCountCore(row - 1, rows, col, cols, threshold, visited) +
                    movingCountCore(row, rows, col + 1, cols, threshold, visited) +
                    movingCountCore(row, rows, col - 1, cols, threshold, visited);
        }
        return count;

    }

    private static int getDigitSum(int x, int y) {
        int sum = 0;
        while (x > 0 && y > 0) {
            sum += x % 10;
            sum += y % 10;
            x = x / 10;
            y = y / 10;
        }
        return sum;
    }

    /*面试题14，动态规划，剪绳子*/
    public static int maxProductAfterCutting_solution1(int length) {
        if (length < 2)
            return 0;
        if (length == 2)
            return 1;
        if (length == 3)
            return 2;

        int[] products = new int[length + 1];

        products[0] = 0;
        products[1] = 1;
        products[2] = 2;
        products[3] = 3;

        int max;
        for (int i = 4; i <= length; i++) {
            max = 0;
            for (int j = 1; j <= i / 2; j++) {
                int product = products[j] * products[i - j];
                if (max < product)
                    max = product;
            }
            products[i] = max;
        }
        max = products[length];

        return max;

    }


    /*面试题15
    * NumbeOf1没有考虑负数情况。
    * */
    public static int NumberOf1(int n) {
        int count = 0;
        while (n > 0) {
            if ((n & 1 ) != 0) //与1与操作，如果等于1,则最右边一位是1.
                count++;
            n = n >> 1;//整数右移一位
        }
        return count;
    }

    /*因为负数的右移要在末尾添加1，这样会导致死循环，所以干脆左移
    * 从1开始，一次左移一位，然后与n做与运算。
    * 这种解法循环的次数等于整数二进制的位数，32位整数需要循环32次。
    * */
    public static int NumberOf2(int n){
        int count = 0;
        int flag = 1;
        while (flag != 0){
            if ((n & flag) != 0)
                count++;
            flag = flag << 1;
        }
        return count;
    }

    /*一个整数减去1的结果与原整数做与运算，会把该整数最右边的1变成0.
    * 基于这个，一个整数有多少个1，就可以做多少次这种操作！*/
    public static int NumberOf3(int n){
        int count = 0;
        while (n>0){
            count++;
            n = n & (n - 1);
        }
        return count;
    }

    /*判断一个整数是不是2的整数次方,(一个整数减去1的结果与原整数做与运算，会把改整数最右边的1变为0)，
    * 如果一个数是2的整数次方，则这个整数的二进制中只有一个1，则只可以做一次这种运算！
    * */
    public static boolean is2IntPower(int n){
        int count = 0;
        while (n>0){
            if (count>1) {
                break;
            }
            count++;
            n = n & (n - 1);
        }

        return count <= 1;
    }


    public static void main(String[] args) {

//        System.out.println(Fibonacci(5));
//        System.out.println(Fibonacci_cur(5));
//        char[][] matrix = new char[][]{{'a','b','t','g'},{'c','f','c','s'},{'j','d','e','h'}};
//        System.out.println(hasPath(matrix, "bfce"));

//        getDigitSum(12, 34);
//        System.out.println(movingCount(4, 4, 3));
//        System.out.println(maxProductAfterCutting_solution1(4));
//        System.out.println(NumberOf1(9));
//        System.out.println(NumberOf2(9));
//        System.out.println(NumberOf3(9));

        System.out.println(is2IntPower(0));


    }
}
