package com.xauv.algorithm.刷题.并查集;


/**
 * @author: Bing Juan
 * @date: 2022/11/18 15:06
 * @desc:
 *
 * [1, 1, 0, 0, 0]
 * [0, 1, 0, 1, 1]
 * [0, 0, 0, 1, 1]
 * [0, 0, 0, 0, 0]
 * [0, 0, 1, 1, 1]
 */
public class 岛屿的数量 {

    /**
     * 判断岛屿数量
     * @param grid char字符型二维数组
     * @return int整型
     */
    public static int solve (char[][] grid) {
        // write code here
        if (grid == null || grid.length == 0 || grid[0].length == 0) {
            return 0;
        }
        int rows = grid.length;
        int cols = grid[0].length;
        int[] area = new int[rows * cols];
        for (int i = 0; i < area.length; i++) {
            area[i] = i;
        }

        // 查看上下左右是否有岛屿，如果有则加入，如果没有则创建岛屿
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {

            }
        }


        return -1;
    }

    private static class UnionFind {

        int[] parents;

        public UnionFind(int size) {
            parents = new int[size];
            for (int i = 0; i < parents.length; i++) {
                parents[i] = i;
            }
        }

        public int find(int c) {
            while (parents[c] != c) {
                c = parents[c];
            }
            return c;
        }

        public void union(int c1, int c2) {
            int p1 = find(c1);
            int p2 = find(c2);
            if (p1 != p2) {
                parents[p1] = p2;
            }
        }

        public boolean same(int c1, int c2) {
            return find(c1) == find(c2);
        }
    }

    public static void main(String[] args) {

    }
}
