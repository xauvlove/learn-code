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
 * @Date 2022/10/23 16:56
 * @Author Administrator
 * @Package com.xauv.algorithm.基本数据结构.图
 * @Desc
 */
public class 图 {

    static Graph.WeightManager<Double> weightManager = new Graph.WeightManager<Double>() {
        @Override
        public int compare(Double w1, Double w2) {
            if (w1 == null) {
                return -1;
            }
            return w1.compareTo(w2);
        }

        @Override
        public Double add(Double w1, Double w2) {
            if (w1 == null) {
                w1 = 0d;
            }
            if (w2 == null) {
                w2 = 0d;
            }
            return w1 + w2;
        }
    };

    public static void main(String[] args) {

        // ---------------初始化--------------
        ListGraph<String, Double> graph = new ListGraph<>(weightManager);
        for (int i = 0; i < Data.WEIGHT3.length; i++) {
            Object[] objects = Data.WEIGHT3[i];
            if (objects.length == 2) {
                graph.addEdge(objects[0].toString(), objects[1].toString());
                //graph.addEdge(objects[1].toString(), objects[0].toString());
            } else {
                graph.addEdge(objects[0].toString(), objects[1].toString(), (double) (int) objects[2]);
                //graph.addEdge(objects[1].toString(), objects[0].toString(), (double) (int) objects[2]);
            }

        }

        // ---------------深度广度优先搜索--------------
        graph.dfs("C");
        graph._dfs("C");
        graph.bfs("C");


        // ---------------拓扑排序--------------
        List<String> topologicalSort = graph.topologicalSort();
        System.out.println(topologicalSort);


        // ---------------最小生成树--------------
        Set<Graph.EdgeInfo<String, Double>> mst = graph.mst();
        if (mst.isEmpty()) {
            System.err.println("没有最小生成树");
        } else {
            for (Graph.EdgeInfo<String, Double> stringIntegerEdgeInfo : mst) {
                System.out.println(stringIntegerEdgeInfo);
            }
        }

        // ---------------最短路经--------------

        Map<String, Double> paths = graph.shortPath("广州");
        System.out.println(paths);
    }
}
