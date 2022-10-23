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
 * ----------------- quick union - find 优化 基于 size 的优化-------------------
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
public class UnionFind_QuickUnion_OptimizeBySize extends UnionFind_QuickUnion {

    /**
     * 元素位置代表数据编号
     * 元素值代表属于哪个集合
     */
    int[] parents;

    int[] sizes;

    public UnionFind_QuickUnion_OptimizeBySize(int capacity) {
        super(capacity);
        if (capacity < 0) {
            throw new IllegalArgumentException();
        }
        sizes = new int[capacity];
        Arrays.fill(sizes, 1);
    }

    /**
     * 先找到根节点
     * 然后对根节点进行操作
     *
     * 【重要】
     * 代码虽然看起来还是得找到 v1 v2 的根节点，然后对根节点进行操作
     * 复杂度没有降低，实际上降低了
     *
     * 比如树
     *
     *       2                      9
     *   1        0                 6
     *   4                          7
     *   5
     *
     *
     *  现在将 5 和 7 合并 union(5, 7)
     *  变为
     *
     *            2
     *   1        0       9
     *   4                6
     *   5                7
     *
     *  虽然树是长得这个样子，7 的根节点是 2
     *  但在真实的数据中【数组中】，parents[7] = 9
     *
     *  如果我要找 7 的根节点
     *  先找到 parent[7] = 9，然后再找 parent[9] = 2
     *  只需要 2 次就可以找得到
     *
     *  查找 5 需要 2 次
     *
     *  ----------------------------------------------
     *
     *  刚才是 size 较小的树嫁接到 size 较高的树上
     *  如果将 左数嫁接到右树上呢
     *
     *                     9
     *            2                  6
     *       1    0                  7
     *       4
     *       5
     *
     *  我们要查找 7，需要 2 次
     *  查找 5 则需要 3 次
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
        if (sizes[p1] < sizes[p2]) {
            parents[p1] = parents[p2];
            sizes[p2] += sizes[p1];
        } else {
            parents[p2] = parents[p1];
            sizes[p1] += sizes[p2];
        }
    }
}
