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
            return w2.compareTo(w1);
        }

        @Override
        public Double add(Double w1, Double w2) {
            return w1 + w2;
        }
    };

    public static void main(String[] args) {

        ListGraph<String, Double> graph = new ListGraph<>(weightManager);
        for (int i = 0; i < Data.MST_01.length; i++) {
            Object[] objects = Data.MST_01[i];
            if (objects.length == 2) {
                graph.addEdge(objects[0].toString(), objects[1].toString());
                graph.addEdge(objects[1].toString(), objects[0].toString());
            } else {
                graph.addEdge(objects[0].toString(), objects[1].toString(), (double) (int) objects[2]);
                graph.addEdge(objects[1].toString(), objects[0].toString(), (double) (int) objects[2]);
            }

        }
        //graph.bfs("5");
        graph.dfs("C");
        graph._dfs("C");
        graph.bfs("C");

        List<String> topologicalSort = graph.topologicalSort();
        System.out.println(topologicalSort);

        Set<Graph.EdgeInfo<String, Double>> mst = graph.mst();
        for (Graph.EdgeInfo<String, Double> stringIntegerEdgeInfo : mst) {
            System.out.println(stringIntegerEdgeInfo);
        }
    }
}
