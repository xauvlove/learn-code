package com.xauv.algorithm.基本数据结构.并查集;
/*
       /\   /\             /\.__                      
___  __)/___)/  __ _____  _)/|  |   _______  __ ____  
\  \/  /\__  \ |  |  \  \/ / |  |  /  _ \  \/ // __ \ 
 >    <  / __ \|  |  /\   /  |  |_(  <_> )   /\  ___/ 
/__/\_ \(____  /____/  \_/   |____/\____/ \_/  \___  >
      \/     \/                                    \/
*/

/**
 * @Date 2022/10/22 20:26
 * @Author Administrator
 * @Package com.xauv.algorithm.基本数据结构.并查集
 * @Desc
 *
 *
 * 并查集有两个核心操作：
 * 查找 find：查找元素所在集合
 * 合并 union：将两个元素所在的集合合并成为一个集合
 *
 * 并查集实现有两种思路：
 * 1.quick find - union
 * 2.quick union - find
 *
 *
 * 假设有一批数字   0 1 2 3 4 5 6 7
 * 他们分别属于集合：1 1 2 1 5 6 6 6
 *
 * 我们根据上面的集合映射关系可以看到：
 * 0 1 3 属于 1 这个集合
 * 2 属于 2 这个集合
 * 4 5 6 7 属于 6 这个集合
 *
 *
 * ----------------- quick union - find 实现-------------------
 *
 * 初始化：每个元素各自属于一个集合：
 *  数据：0 1 2 3 4
 *  集合：0 1 2 3 4
 *
 * 数据结构上来看，每个节点是一个根节点
 *
 * 0    1    2    3    4
 *
 *
 * 合并操作：
 *
 * 将 0，1 合并起来：union(1, 0)
 * 直接将 1 的集合改为 0，将 1 的【根节点】改为 0 的【根节点】
 * 数据：【0 1 2 3 4】
 * 集合：【0 0 2 3 4】
 *
 * 数据结构变为：
 *
 *    0    2    3    4
 *    1
 *
 * 0 是 1 的父节点
 *
 *
 * 将 1，2 合并起来：union(1, 2)
 * 将 1 的集合改为 2，将 1 的【根节点】改为 2 的【根节点】
 * 数据：【0 1 2 3 4】
 * 集合：【2 0 2 3 4】
 *
 * 数据结构变为：
 *
 *          2      3      4
 *          0
 *          1
 *
 *
 *
 * 将 0，4 合并起来：union(4, 0)
 * 将 4 的【根节点】改为 0 的【根节点】
 * 数据：【0 1 2 3 4】
 * 集合：【2 2 2 3 2】
 *
 * 数据结构变为：
 *
 *            2          3
 *        0       4
 *        1
 *
 *
 *
 * 将 0，3 合并：union(0, 3)
 * 数据：【0 1 2 3 4】
 * 集合：【2 0 3 3 2】
 *
 * 数据结构变为：
 *            3
 *            2
 *        0       4
 *        1
 *
 *
 *
 *
 * 在合并时，只要找到根节点，然后根节点与根节点直接合并即可
 * 比如：合并 1  2，只需要找到 1 的根节点 root1 和 2 的根节点 root2
 * 然后将 root1 的根节点改为 root2 即可，
 *
 * 因此，union 的复杂度较低，复杂度 log(n)
 *
 * find 需要逐个往上找到根节点，复杂度较高，等于树的高度，为 log(n)
 *
 *
 *
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
 */
public class UnionFind_QuickUnion {

    /**
     * 元素位置代表数据编号
     * 元素值代表属于哪个集合
     */
    int[] parents;

    public UnionFind_QuickUnion(int capacity) {
        if (capacity < 0) {
            throw new IllegalArgumentException();
        }
        parents = new int[capacity];
        // 初始化，每个节点属于单独集合
        for (int i = 0; i < parents.length; i++) {
            parents[i] = i;
        }
    }

    /**
     * 查找 v 所在集合，也即父节点
     * @param v
     * @return
     */
    public int find(int v) {
        if (v < 0 || v > parents.length) {
            throw new ArrayIndexOutOfBoundsException(v);
        }
        while (v != parents[v]) {
            v = parents[v];
        }
        return v;
    }

    /**
     * 是否属于同一个集合
     * @param v1
     * @param v2
     * @return
     */
    public boolean same(int v1, int v2) {
        return find(v1) == find(v2);
    }

    /**
     * 先找到根节点
     * 然后对根节点进行操作
     *
     * @param v1
     * @param v2
     */
    public void union(int v1, int v2) {
        int p1 = find(v1);
        int p2 = find(v2);
        if (p1 == p2) {
            return;
        }
        parents[p1] = parents[p2];
    }
}
