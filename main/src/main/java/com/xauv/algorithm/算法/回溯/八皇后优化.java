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
public class 八皇后优化 {

    // 标记着某一列是否有皇后了，标记着第 col 列是否有皇后了
    static boolean[] cols;

    /**
     * 如果是 4 皇后，是 4x4 方格，正斜线 \，共 7 条，反斜线共 7 条，一共 14条
     * 如果是 8 皇后，是 8x8 方格，正斜线 15 条，反斜线 15 条
     * 如果是 n 皇后，正斜线 2n-1 条，反斜线 2n-1 条，共 4n-2 条斜线
     *
     * 因此 leftTop[].length = 2n-1
     * 同理 rightTop[].length = 2n-1
     *
     * 在检测皇后摆放有效性时，我们只需要看一下某条斜线上是否有皇后即可
     */
    // 标记着某一对角线是否有皇后了，方向：从左上角->到右下角，left top -> right bottom
    static boolean[] leftTop;

    // 标记着某一对角线是否有皇后了，方向：从右上角->从左下角，right top -> left bottom
    static boolean[] rightTop;

    static int ways = 0;

    public static void placeNQueens(int n) {
        if (n < 1) {
            return;
        }
        cols = new boolean[n];
        leftTop = new boolean[(n << 1) - 1];
        rightTop = new boolean[leftTop.length];
        place(0);
    }

    /**
     * 利用行号和列号计算斜线索引
     *
     * 左上角 -> 右下角：index = row - col + (n - 1)
     *
     * 右上角 -> 左下角：index = row + col
     *
     * 利用此公式计算出来的线索引，如果点在同一条斜线上，则他们的索引相同
     *
     * @param row
     * @return
     */
    private static void place(int row) {
        for (int col = 0; col < cols.length; col++) {

            // 当 row 大于最大行数时，说明已经全部摆放完成，即找到了一种结果
            if (row >= cols.length) {
                ways++;
                show();
                return;
            }

            // 检测 col 列是否已经有皇后了
            if (cols[col]) {
                continue;
            }
            // 检测正斜线是否已经有皇后了
            int ltIndex = row - col + (cols.length - 1);
            if (leftTop[ltIndex]) {
                continue;
            }
            // 检测反斜线是否已经有皇后了
            int rtIndex = row + col;
            if (rightTop[rtIndex]) {
                continue;
            }
            cols[col] = true;
            leftTop[ltIndex] = true;
            rightTop[rtIndex] = true;

            place(row + 1);
            // 回溯，重置
            cols[col] = false;
            leftTop[ltIndex] = false;
            rightTop[rtIndex] = false;
        }
    }

    public static void show() {

    }

    public static void main(String[] args) {
        int n = 20;
        placeNQueens(n);
        System.out.printf("%d皇后，共%d种摆法", n, ways);
    }
}