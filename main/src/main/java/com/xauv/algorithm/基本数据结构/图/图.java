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
            /*if (w1 == null && w2 == null) {
                return 0;
            }
            if (w1 == null) {
                return -1;
            }
            if (w2 == null) {
                return 1;
            }*/
            if (w1 == null) {
                w1 = zero();
            }
            if (w2 == null) {
                w2 = zero();
            }
            return w1.compareTo(w2);
        }

        @Override
        public Double zero() {
            return 0d;
        }

        @Override
        public Double add(Double w1, Double w2) {
            /*if (w1 == null) {
                w1 = 0d;
            }
            if (w2 == null) {
                w2 = 0d;
            }*/
            if (w1 == null) {
                w1 = zero();
            }
            if (w2 == null) {
                w2 = zero();
            }
            return w1 + w2;
        }
    };

    public static void main(String[] args) throws InterruptedException {

        // ---------------初始化--------------
        ListGraph<String, Double> graph = new ListGraph<>(weightManager);
        for (int i = 0; i < Data.NEGATIVE_WEIGHT1.length; i++) {
            Object[] objects = Data.NEGATIVE_WEIGHT1[i];
            if (objects.length == 2) {
                graph.addEdge(objects[0].toString(), objects[1].toString());
                //graph.addEdge(objects[1].toString(), objects[0].toString());
            } else {
                graph.addEdge(objects[0].toString(), objects[1].toString(), (double) (int) objects[2]);
                //graph.addEdge(objects[1].toString(), objects[0].toString(), (double) (int) objects[2]);
            }

        }

        // ---------------深度广度优先搜索--------------
        System.out.println("---------------深度优先搜索--------------");
        graph.dfs("C");
        System.out.println("---------------广度优先搜索--------------");
        graph.bfs("C");


        // ---------------拓扑排序--------------
        System.out.println("---------------拓扑排序--------------");
        List<String> topologicalSort = graph.topologicalSort();
        System.out.println(topologicalSort);

        // ---------------最小生成树--------------
        System.out.println("---------------最小生成树--------------");
        Set<Graph.EdgeInfo<String, Double>> mst = graph.mst();
        if (mst.isEmpty()) {
            System.err.println("没有最小生成树");
        } else {
            for (Graph.EdgeInfo<String, Double> stringIntegerEdgeInfo : mst) {
                System.out.println(stringIntegerEdgeInfo);
            }
        }

        // ---------------最短路经--------------

        System.out.println("--------------- Dijkstra 最短路经 --------------");

        Map<String, Graph.PathInfo<String, Double>> pathInfos = graph.calShortPathByDijkstra("A");
        for (Map.Entry<String, Graph.PathInfo<String, Double>> entry : pathInfos.entrySet()) {
            System.out.println(entry);
        }

        System.out.println("--------------- Bellman-Ford 最短路经 --------------");
        pathInfos = graph.calShortPathByBellmanFord("A");
        for (Map.Entry<String, Graph.PathInfo<String, Double>> entry : pathInfos.entrySet()) {
            System.out.println(entry);
        }

        Thread.sleep(1000);

        System.out.println("--------------- floyd 最短路经 --------------");
        Map<String, Map<String, Graph.PathInfo<String, Double>>> map = graph.calShortPathByFloyd();
        for (Map.Entry<String, Map<String, Graph.PathInfo<String, Double>>> entry : map.entrySet()) {
            System.out.println(entry.getKey() + "------------------------");
            for (Map.Entry<String, Graph.PathInfo<String, Double>> pathInfoEntry : entry.getValue().entrySet()) {
                System.out.println("\t" + pathInfoEntry);
            }
        }
    }
}
