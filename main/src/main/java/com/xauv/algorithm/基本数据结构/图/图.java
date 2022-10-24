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

/**
 * @Date 2022/10/23 16:56
 * @Author Administrator
 * @Package com.xauv.algorithm.基本数据结构.图
 * @Desc
 */
public class 图 {


    public static void main(String[] args) {

        /*ListGraph<String, Integer> graph = new ListGraph<>();
        graph.addEdge("v1", "v0", 9);
        graph.addEdge("v1", "v2", 3);
        graph.addEdge("v2", "v0", 2);
        graph.addEdge("v2", "v3", 5);
        graph.addEdge("v3", "v4", 1);
        graph.addEdge("v0", "v4", 6);
        //graph.print();


        graph.bfs("v2");*/

        ListGraph<String, Integer> graph = new ListGraph<>();
        for (int i = 0; i < Data.TOPO.length; i++) {
            Object[] objects = Data.TOPO[i];
            if (objects.length == 2) {
                graph.addEdge(objects[0].toString(), objects[1].toString());
                //graph.addEdge(objects[1].toString(), objects[0].toString());
            } else {
                graph.addEdge(objects[0].toString(), objects[1].toString(), (int)objects[3]);
                //graph.addEdge(objects[1].toString(), objects[0].toString(), (int)objects[3]);
            }

        }
        //graph.bfs("5");
        graph.dfs("C");
        graph._dfs("C");

        List<String> topologicalSort = graph.topologicalSort();
        System.out.println(topologicalSort);
    }
}
