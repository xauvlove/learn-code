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
 * ----------------- quick find - union 实现-------------------
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
 * 直接将 1 的集合改为 0
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
 * 将 1 的集合改为 2，再将 0 的集合改为 2
 * 数据：【0 1 2 3 4】
 * 集合：【2 2 2 3 4】
 *
 * 数据结构变为：
 *
 *          2      3      4
 *       0    1
 *
 * 2 是 1 的父节点
 * 2 是 0 的父节点
 *
 *
 * 将 0，4 合并起来：union(4, 0)
 * 将 4 的集合改为 0 的集合【刚才已经将 0 的集合改为了 2】
 * 数据：【0 1 2 3 4】
 * 集合：【2 2 2 3 2】
 *
 * 数据结构变为：
 *
 *            2          3
 *       0    1    4
 *
 * 2 是 1 的父节点
 * 2 是 0 的父节点
 * 2 是 4 的父节点
 *
 *
 *
 * 将 0，3 合并：union(3, 0)
 * 数据：【0 1 2 3 4】
 * 集合：【2 2 2 2 2】
 *
 * 数据结构变为：
 *
 *              2
 *       0    1    4   3
 *
 * 我们发现，查找一个元素属于哪个集合时，只需看他的父节点是谁
 * 比如查看 4 的集合，直接就可以得到，父节点是 2 ，属于 2 集合
 *
 * 在 find 时，复杂度是 O(1)
 * 但是在 union 时，复杂度可能是 O(n)，比如在执行 union(1, 2) 时，
 * 不仅要 1 的集合改为 2，还要将 0 的集合也改为 2 【得将所有元素遍历一遍，更改集合】
 *
 * union 操作，复杂度 O(n)
 *
 * 可以发现，树只有最多两层，所以查找起来非常快
 *
 *
 */
public class UnionFind_QuickFind {

    /**
     * 元素位置代表数据编号
     * 元素值代表属于哪个集合
     */
    int[] parents;

    public UnionFind_QuickFind(int capacity) {
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
        return parents[v];
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
     * 将 v1 的父节点改为 v2 的父节点
     * 也就是将 v1 加入到 v2 的集合
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
        // 遍历所有节点，将相关的节点父节点改为 p2
        for (int i = 0; i < parents.length; i++) {
            if (find(parents[i]) == p1) {
                parents[i] = p2;
            }
        }
    }
}
