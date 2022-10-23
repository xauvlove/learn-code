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
 * ----------------- quick union - find 优化-------------------
 *
 * 使用路径压缩
 * 在 find 时，不断将途径节点改为根节点
 *
 *
 * 例如树
 *
 *     2
 *  0     4
 *     1
 *     3
 *
 * 数据：【0 1 2 3 4】
 * 数组：【2 0 2 1 2】
 *
 * 在 find(3) 时
 * 首先找到 parents[3] = 1，并且 1 != parents[1]
 * 将 parents[3] 改为 parents[1]
 * 发现 1 != parents[1]，因此还需要找 1 的根节点，继续修改
 *
 * 应该使用递归修改
 *
 * 这样，就将树变为
 *                 2
 *             0  1  4  3
 *
 * 树只只有两层了，后续再 find 将会极大节省复杂度
 *
 *
 */
public class UnionFind_QuickUnion_OptimizeByPathCompression extends UnionFind_QuickUnion_OptimizeByRank {

    public UnionFind_QuickUnion_OptimizeByPathCompression(int capacity) {
        super(capacity);
    }

    /**
     * 使用递归
     * @param v
     * @return
     */
    public int find(int v) {
        if (v < 0 || v > parents.length) {
            throw new ArrayIndexOutOfBoundsException(v);
        }
        if (v != parents[v]) {
            parents[v] = find(parents[v]);
        }
        return parents[v];
    }
}
