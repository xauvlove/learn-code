package com.xauv.algorithm.基本数据结构.并查集;
/*
       /\   /\             /\.__                      
___  __)/___)/  __ _____  _)/|  |   _______  __ ____  
\  \/  /\__  \ |  |  \  \/ / |  |  /  _ \  \/ // __ \ 
 >    <  / __ \|  |  /\   /  |  |_(  <_> )   /\  ___/ 
/__/\_ \(____  /____/  \_/   |____/\____/ \_/  \___  >
      \/     \/                                    \/
*/

import java.util.Arrays;

/**
 * @Date 2022/10/22 20:26
 * @Author Administrator
 * @Package com.xauv.algorithm.基本数据结构.并查集
 * @Desc

 *
 * ----------------- quick union - find 优化-------------------
 *
 * 当树不平衡时，复杂度可能退化为 O(n)
 *
 * 优化方式：
 * 1.将元素少的树嫁接到元素多的树上
 * 2.将比较矮的树嫁接到比较高的树上
 *
 * 注意，1 和 2 优化是不一样的，元素多不一定树就高【这并非是二叉树，而是多叉树】
 *
 *
 * ----------------- quick union - find 优化 基于 rank 的优化-------------------
 *
 * 例如树
 *
 *     2
 *  0     4               3
 *     1
 *
 * union(1, 3)
 * 本质是将 1 这棵树嫁接到 3 上
 * 但这样会首先找到 1 的父节点 = 0，然后再找到 0 的父节点 = 2，然后再将 2 的父节点改为 3，复杂度 log(n)
 *
 * 如果是直接将 3 的父节点改为 1 这棵树上，那复杂度就降低了
 *
 */
public class UnionFind_QuickUnion_OptimizeByRank extends UnionFind_QuickUnion {

    /**
     * 表示以 index 为根节点的树的高度
     */
    int[] ranks;

    public UnionFind_QuickUnion_OptimizeByRank(int capacity) {
        super(capacity);
        if (capacity < 0) {
            throw new IllegalArgumentException();
        }
        ranks = new int[capacity];
        Arrays.fill(ranks, 1);
    }

    /**
     * 先找到根节点
     * 然后对根节点进行操作
     *
     * 将 低树 嫁接到 高树 上，更加平衡
     *
     * 比如：
     *
     *     3                4
     *     2              5 6 7 8
     *     0
     *     1
     *
     *  将右树嫁接到左树，平衡一些
     *
     *            3
     *     2              4
     *     0           5 6 7 8
     *     1
     *
     *
     * @param v1
     * @param v2
     */
    public void union(int v1, int v2) {
        // p1 p2 是根节点的指针
        int p1 = find(v1);
        int p2 = find(v2);
        if (p1 == p2) {
            return;
        }
        if (ranks[p1] < ranks[p2]) {
            parents[p1] = parents[p2];
        } else if (ranks[p1] > ranks[p2]) {
            parents[p2] = parents[p1];
        } else {
            // 高度相等时，任意嫁接，高度加 1
            parents[p1] = parents[p2];
            ranks[p2]++;
        }
    }
}
