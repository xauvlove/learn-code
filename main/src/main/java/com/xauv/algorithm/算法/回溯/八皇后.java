package com.xauv.algorithm.算法.回溯;
/*
       /\   /\             /\.__                      
___  __)/___)/  __ _____  _)/|  |   _______  __ ____  
\  \/  /\__  \ |  |  \  \/ / |  |  /  _ \  \/ // __ \ 
 >    <  / __ \|  |  /\   /  |  |_(  <_> )   /\  ___/ 
/__/\_ \(____  /____/  \_/   |____/\____/ \_/  \___  >
      \/     \/                                    \/
*/

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Date 2022/10/27 19:59
 * @Author Administrator
 * @Package com.xauv.algorithm.算法.回溯
 * @Desc
 *
 * 在 8x8 的棋盘上摆放 8 个皇后，使他们不得不相攻击，
 * 任意两个皇后不能在同一行、同一列、同一斜线上
 *
 * 有多少种摆法
 *
 *
 * 思路：
 * 1.暴力破解
 * 64 个格子选出任意 8 个格子摆放皇后，检查每一种可能性
 * 共 C8/64 = 44亿 种可能
 *
 * 2.暴力破解 - 优化
 * 根据题意，皇后不能在同一行 那么任意选择 8 个皇后时，不让他们在同一行上
 * 共 (C1/8)^8 = 1677w 种
 *
 * ----------------------------------------
 * 3.回溯法
 * 先研究 4 皇后
 *
 *   1  2  3 4
 * 1 口 口 口 口
 * 2 口 口 口 口
 * 3 口 口 口 口
 * 4 口 口 口 口
 *
 * 先摆放 p11 这个位置，换下一行
 *   1  2  3 4
 * 1 Q 口 口 口
 * 2 口 口 口 口
 * 3 口 口 口 口
 * 4 口 口 口 口
 *
 *
 * 摆放 p21 这个位置，发现处在同一列，回溯；
 * 摆放 p22 这个位置，发现在同一斜线，回溯
 * 摆放 p23 这个位置，不冲突，
 * 换下一行
 *   1  2  3 4
 * 1 Q 口 口 口
 * 2 口 口 Q 口
 * 3 口 口 口 口
 * 4 口 口 口 口
 *
 *
 * 摆放 p31 这个位置，回溯
 * 摆放 p32，回溯
 * 摆放 p33，回溯
 * 摆放 p34，冲突，回溯
 * 下面时冲突图
 *   1  2  3 4
 * 1 Q 口 口 口
 * 2 口 口 Q 口
 * 3 口 口 口 Q
 * 4 口 口 口 口
 *
 * 接下来，应该时回溯到 p24 位置，继续往下
 *
 * -----------------------------------------------
 *
 * 【剪枝】 pruning，回溯 + 剪枝 效率最高
 *
 *   1  2  3 4
 * 1 Q 口 口 口
 * 2 口 口 口 口
 * 3 口 口 口 口
 * 4 口 口 口 口
 *
 * 根据要求，皇后不能在同一行、列、斜线，
 * 因此 p11 所在行、所在列、所在斜线都不能继续摆放皇后了
 * 对这些不能摆放皇后的位置进行剪枝
 *
 *   1  2  3  4
 * 1 Q  x  x  x
 * 2 x  x 口  口
 * 3 x 口  x  口
 * 4 x 口 口   x
 *
 * 这样下来，能摆放皇后的位置大大减少，可以大大减少复杂度
 *
 * 摆放 p23，继续剪枝
 *   1  2  3  4
 * 1 Q  x  x  x
 * 2 x  x  Q  x
 * 3 x 口  x  x
 * 4 x 口  x  x
 *
 * 摆放 p32，继续剪枝
 *   1  2  3  4
 * 1 Q  x  x  x
 * 2 x  x  Q  x
 * 3 x  Q  x  x
 * 4 x  x  x  x
 * 已经无路可走，需要回溯
 *
 */
public class 八皇后 {

    // 数组索引是行号，数组元素是列号，cols[4] = 5，表示第 4 行的皇后在第 5 列
    static int[] cols;

    static List<Integer[]> solutions = new ArrayList<>();

    public static void placeNQueens(int n) {
        if (n < 1) {
            return;
        }
        cols = new int[n];
        place(0);
    }

    /**
     * 从第 row 行开始摆放皇后
     *
     * 摆放第 row 行的皇后
     * @param row
     */
    private static void place(int row) {
        for (int col = 0; col < cols.length; col++) {

            // 当 row 大于最大行数时，说明已经全部摆放完成，即找到了一种结果
            if (row >= cols.length) {
                System.out.println("---------------回溯过程-----------------");
                Integer[] solution = new Integer[cols.length];
                for (int i = 0; i < solution.length; i++) {
                    solution[i] = cols[i];
                }
                solutions.add(solution);
                return;
            }

            // 首先进行剪枝，如何剪枝，跟之前的皇后摆放在哪里有关系
            // 如果可以摆放，进行摆放
            boolean valid = isValid(row, col);
            System.out.printf("[%d][%d]=%s\n", row, col, valid);
            if (isValid(row, col)) {
                cols[row] = col;
                // 摆放下一行
                place(row + 1);
                // 下一行摆放完了，两种情况【1.摆放成功】【2.摆放失败 摆放下一行时 valid = false】
                // 1.摆放成功，会再次调用 place(row+1)
                // 2.摆放失败，外层循环会 col++，换一列进行摆放，达到回溯效果
            }
        }
    }

    /**
     * 判断第 row 行，第 col 列是否可以摆放皇后【做剪枝】
     *
     * 行不用看
     *
     * 1.看列
     *
     * 2.看斜线
     * 斜线判断，两种方法：1.看斜线上每个点是否存在皇后 2.利用斜率
     *
     *
     *
     * 1.
     * // 判断左上方斜线有无皇后
     * int r = row - 1;
     * for (int q = col - 1; q >= 0 && r >= 0; q--) {
     *     if (cols[r] == q) {
     *          return false;
     *     }
     *     r = r - 1;
     * }
     *  // 判断右上方斜线有无皇后
     * r = row - 1;
     * for (int q = col + 1; col < cols.length && r >= 0; q++) {
     *     if (cols[r] == q) {
     *        return false;
     *     }
     *     r = r - 1;
     * }
     *
     * 2.
     *  一条斜线上，相邻的两个位置 p1(x1, y1) p2(x2, y2)
     *  (x1 - x2) / (y1 - y2) = ±1
     *  也就是说，如果相邻两个位置，横坐标相减除以纵坐标相减，结果等于 1 或 -1，就表示他们在同一条斜线上
     *  1 代表 45°，-1 代表 135°
     *
     *  if (Math.abs((row - i) / (col - cols[i])) == 1) {
     *     return false;
     *  }
     * @param row
     * @param col
     * @return
     */
    private static boolean isValid(int row, int col) {
        // 第 row 行前面那些行
        for (int i = 0; i < row; i++) {
            // 皇后列号
            int queenCol = cols[i];
            // 列判断
            if (queenCol == col) {
                return false;
            }
            // 无需对行判断

            // ------------ 利用斜率判断斜线 ------------
            if (row - i == Math.abs(col - cols[i])) {
                return false;
            }
        }
        return true;
    }

    public static void show() {
        for (int i = 0; i < solutions.size(); i++) {
            Integer[] solution = solutions.get(i);
            for (int row = 0; row < solution.length; row++) {
                for (int col = 0; col < solution.length; col++) {
                    if (solution[row] == col) {
                        System.out.print("Q ");
                    } else {
                        System.out.print("口 ");
                    }
                }
                System.out.println();
            }
            System.out.println("--------------------------");
        }
    }

    public static void main(String[] args) {
        placeNQueens(4);
        show();
        System.out.println("共有" + solutions.size() + "种，解法");
    }
}