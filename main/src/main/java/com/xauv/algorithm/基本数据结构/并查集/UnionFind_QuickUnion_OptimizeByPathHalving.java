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
 * 使用路径分裂
 *
 * 在 find 时，将途径节点 每隔一个节点就指向祖父节点 parent.parent
 *
 *
 * 例如树
 *
 *      7
 *      6
 *      5
 *      4
 *      3
 *      2
 *      1
 *
 *
 * 经过 find 路径减半后，树变为
 *
 *                               7
 *                  6                            5
 *                                       4                3
 *                                                     2     1
 *
 * 可以降低层高，没有了递归调用
 *
 */
public class UnionFind_QuickUnion_OptimizeByPathHalving extends UnionFind_QuickUnion_OptimizeByRank {

    public UnionFind_QuickUnion_OptimizeByPathHalving(int capacity) {
        super(capacity);
    }

    /**
     * @param v
     * @return
     */
    public int find(int v) {
        if (v < 0 || v > parents.length) {
            throw new ArrayIndexOutOfBoundsException(v);
        }
        while (v != parents[v]){
            parents[v] = parents[parents[v]];
            v = parents[v];
        }
        return v;
    }
}
