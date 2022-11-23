package com.xauv.algorithm.算法.多叉树.多叉树打印为图片;

import com.xauv.algorithm.基本数据结构.图.ListGraph;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Set;

/**
 * @author: Bing Juan
 * @date: 2022/11/22 18 45
 * @desc:
 */
@SuppressWarnings({"unchecked", "rawtypes"})
public class PlantFileGenerator {

    private static final String path = "/Users/venlexi/Desktop/plant.txt";

    public static String genFilePath(ListGraph graph) throws FileNotFoundException {


        Set<? extends ListGraph.Edge<TreeNode<TreeNode>, ?>> edges = graph.edges();
        Set<? extends ListGraph.Vertex<TreeNode<TreeNode>, ?>> vertices = graph.vertices();

        StringBuilder sb = new StringBuilder();
        sb.append("@startuml\n");
        sb.append("'https://plantuml.com/use-case-diagram\n");
        sb.append("top to bottom direction\n");
        for (ListGraph.Vertex<TreeNode<TreeNode>, ?> vertex : vertices) {
            sb.append("usecase ").append(vertex.val().val).append(" \n");
        }

        for (ListGraph.Edge<TreeNode<TreeNode>, ?> edge : edges) {
            ListGraph.Vertex<TreeNode<TreeNode>, ?> from = edge.from();
            ListGraph.Vertex<TreeNode<TreeNode>, ?> to = edge.to();
            sb.append(from.val().val).append("--> ").append(to.val().val).append("\n");
        }

        sb.append("@enduml\n");
        PrintWriter pw = new PrintWriter(path);
        pw.println(sb);
        pw.flush();
        pw.close();
        return path;
    }
}
