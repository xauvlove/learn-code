package com.xauv.algorithm.基本数据结构.图;
/*
       /\   /\             /\.__                      
___  __)/___)/  __ _____  _)/|  |   _______  __ ____  
\  \/  /\__  \ |  |  \  \/ / |  |  /  _ \  \/ // __ \ 
 >    <  / __ \|  |  /\   /  |  |_(  <_> )   /\  ___/ 
/__/\_ \(____  /____/  \_/   |____/\____/ \_/  \___  >
      \/     \/                                    \/
*/

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @Date 2022/10/23 16:28
 * @Author Administrator
 * @Package com.xauv.algorithm.基本数据结构.图
 * @Desc
 *
 *
 * 图的实现方式一般有
 *
 * 1.邻接矩阵
 * 2.邻接表
 *
 *
 *
 * ---------------1.邻接矩阵---------------
 *
 * 比如
 *
 * A->B  B->C  C->A
 * 用矩阵表示为：
 *
 *   A B C
 * A 0 1 0
 * B 0 0 1
 * C 1 0 0
 *
 * 1 表示有线链接
 * 0 表示无链接
 *
 * 同样，有权图仍然可用邻接矩阵表示
 *
 * A-30>B  B-90>C C-85>A
 * 矩阵表示为：
 *   A    B   C
 * A  /  30   /
 * B  /   /  90
 * C 85   /   /
 *
 * 比较浪费内存，每个节点都要存储
 *
 * 适合稠密图（很多节点都有连接）
 *
 *
 *
 * ---------------2.邻接表---------------
 * 只记录有连接的节点
 * A->B  B->C  C->A  A->C
 * 表示为：
 *
 * A ->[B]->[C]
 * B ->[C]
 * C ->[A]
 *
 * 只记录了有效连接，节省空间
 *
 */
public interface Graph<V, E> {

    /**
     * 有多少边
     */
    int edgesSize();

    /**
     * 有多少节点
     * @return
     */
    int verticesSize();

    /**
     * 增加一个顶点
     * @param v
     */
    void addVertex(V v);

    /**
     *
     * @param from
     * @param to
     * @param weight
     */
    void addEdge(V from, V to, E weight);

    /**
     * 增加一条边
     * @param from
     * @param to
     */
    void addEdge(V from, V to);

    /**
     * 删除顶点
     * @param v
     */
    void removeVertex(V v);

    /**
     * 删除边
     * @param from
     * @param to
     */
    void removeEdge(V from, V to);

    /**
     * 进行拓扑排序
     * @see Kahn
     *
     * @return
     */
    List<V> topologicalSort();

    /**
     * 求最小生成树
     *
     * set 集合是边
     *
     * 根据这些边就可知道最小生成树
     *
     * @see 最小生成树
     *
     * @return
     */
    Set<EdgeInfo<V, E>> mst();

    /**
     * 求最短路径
     *
     * 求 begin 到每个顶点的最短路径值
     *
     *
     * @param begin
     */
    Map<V, E> shortPath(V begin);

    class EdgeInfo<V, E> {
        V from;

        V to;

        E weight;

        public EdgeInfo(V from, V to, E weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }

        @Override
        public String toString() {
            return "{" +
                    "from=" + from +
                    ", to=" + to +
                    ", weight=" + weight +
                    '}';
        }
    }

    interface WeightManager<E> {

        int compare(E w1, E w2);

        E add(E w1, E w2);
    }
}
