package com.company.Offers.comp;


import java.util.Arrays;

/**
 * Created by himon on 17-9-23.
 */
public class exams {


    /*美丽联合,算法string转int
    * 需要处理几种情况:
    * 1.字符串为空
    * 2.开头的正负号
    * 3.其他非数字字符
    * 4.溢出判断
    * 5.指定进制
    *
    * */
    public static int myParseInt(String s, int radix) throws NumberFormatException {
        return parseInt(s, radix);
    }

    public static int parseInt(String s, int radix) {
        if (s.isEmpty())
            throw new NumberFormatException("null");
        if (radix < Character.MIN_RADIX || radix > Character.MAX_RADIX)
            throw new NumberFormatException("radix " + radix + " is error value");

        int result = 0;
        boolean negative = false;
        int i = 0, len = s.length();
        int limit = -Integer.MAX_VALUE;
        int multmin;
        int digit;

        if (len > 0) {
            char firstChar = s.charAt(0);
            if (firstChar < '0') { //判断是不是'+'或'-'
                if (firstChar == '-') {
                    negative = true;
                    limit = Integer.MIN_VALUE;
                } else if (firstChar != '+') { //其他字符
                    throw new NumberFormatException("have error character");
                }
                if (len == 1) {
                    throw new NumberFormatException("Cannot have long '+' or '-' ");
                }
                i++;
            }
            multmin = limit / radix;//用来判断是否越界

            while (i < len) {
                digit = Character.digit(s.charAt(i++), radix);
                if (digit < 0) //其他字符,或者是指定radix进制的无效数字,返回-1.
                    throw new NumberFormatException("have error character");
                if (result < multmin)//乘以进制之前要判断,乘radix是不是越界
                    throw new NumberFormatException("overstep the boundary");
                result *= radix;
                if (result < limit + digit)//加digit之前要判断,加digit是不是越界
                    throw new NumberFormatException("overstep the boundary");
                result -= digit;
            }
        }
        return negative ? result : -result;
    }


    /*微软*/
    public static void treeGraph() {
        int rows = 5, columns = 3;
        int[][] grids = new int[][]{{0, 0, 1}, {1, 0, 0}, {1, 1, 1}, {0, 0, 0}, {0, 1, 1}};
        System.out.println(grids[0][0]);
        System.out.println(grids[1][0]);
        System.out.println(grids[1][1]);

        boolean[][] visited = new boolean[rows][columns];

        int count = graphCount(grids, rows, columns, 0, 0, visited, 0, 0);
        System.out.println(count);
        System.out.println(Arrays.deepToString(visited));

    }

    private static int graphCount(int[][] grids, int rows, int columns, int i, int j, boolean[][] visited, int beforeRow, int beforeCol) {
        int count = 0;
        if (check(grids, rows, columns, i, i, visited, 0, 0)) {
            System.out.println("i:"+i+", j:"+j);
            visited[i][j] = true;
            count = 1 + graphCount(grids, rows, columns, i + 1, j, visited, i, j)
                    + graphCount(grids, rows, columns, i - 1, j, visited, i, j)
                    + graphCount(grids, rows, columns, i, j + 1, visited, i, j)
                    + graphCount(grids, rows, columns, i, j - 1, visited, i, j);
        }
        return count;
    }

    private static boolean check(int[][] grids, int rows, int columns, int row, int col, boolean[][] visit,
                                 int beforeRow, int beforeCol) {
        return (row >= 0 && row < rows) && (col >= 0 && col < columns) && (grids[row][col] == 0) && !visit[row][col]
                && (Math.abs(beforeCol - col) <= 1 && Math.abs(beforeRow - row) <= 1);
    }

    public static void main(String[] args) {
//        System.out.println(Integer.parseInt("-123438", 10));
//        System.out.println(myParseInt("-123438", 10));
//        System.out.println(Integer.MIN_VALUE);
//        System.out.println(-Integer.MAX_VALUE);

        treeGraph();
    }
}
