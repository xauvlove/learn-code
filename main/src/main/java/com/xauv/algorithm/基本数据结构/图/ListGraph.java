package com.xauv.algorithm.基本数据结构.图;
/*
       /\   /\             /\.__                      
___  __)/___)/  __ _____  _)/|  |   _______  __ ____  
\  \/  /\__  \ |  |  \  \/ / |  |  /  _ \  \/ // __ \ 
 >    <  / __ \|  |  /\   /  |  |_(  <_> )   /\  ___/ 
/__/\_ \(____  /____/  \_/   |____/\____/ \_/  \___  >
      \/     \/                                    \/
*/

import java.util.*;
import java.util.stream.Collectors;

/**
 * @Date 2022/10/23 16:50
 * @Author Administrator
 * @Package com.xauv.algorithm.基本数据结构.图
 * @Desc
 *
 * 以邻接表结构实现图
 */
public class ListGraph<V, E> implements Graph<V, E> {

    private Map<V, Vertex<V, E>> vertices = new HashMap<>();

    private Set<Edge<V, E>> edges = new HashSet<>();

    @Override
    public int edgesSize() {
        return edges.size();
    }

    @Override
    public int verticesSize() {
        return vertices.size();
    }

    @Override
    public void addVertex(V v) {
        if (vertices.containsKey(v)) {
            return;
        }
        Vertex<V, E> vertex = new Vertex<>(v);
        vertices.put(v, vertex);
    }

    @Override
    public void addEdge(V from, V to, E weight) {
        Vertex<V, E> fromVertex = vertices.get(from);
        if (fromVertex == null) {
            addVertex(from);
            fromVertex = vertices.get(from);
        }
        Vertex<V, E> toVertex = vertices.get(to);
        if (toVertex == null) {
            addVertex(to);
            toVertex = vertices.get(to);
        }

        Edge<V, E> edge = new Edge<>(fromVertex, toVertex, weight);
        // 删除旧边，增加新边
        fromVertex.outEdges.remove(edge);
        toVertex.inEdges.remove(edge);
        edges.remove(edge);
        fromVertex.outEdges.add(edge);
        toVertex.inEdges.add(edge);
        edges.add(edge);
    }

    @Override
    public void addEdge(V from, V to) {
        addEdge(from, to, null);
    }

    @Override
    public void removeVertex(V v) {
        Vertex<V, E> vertex = vertices.get(v);
        if (vertex == null) {
            return;
        }
        // 删除所有相关边
        Set<Edge<V, E>> inEdges = vertex.inEdges;
        // 删除入边
        for (Edge<V, E> inEdge : inEdges) {
            Vertex<V, E> from = inEdge.from;
            from.outEdges.remove(inEdge);
            edges.remove(inEdge);
        }
        // 删除出边
        Set<Edge<V, E>> outEdges = vertex.outEdges;
        for (Edge<V, E> outEdge : outEdges) {
            Vertex<V, E> to = outEdge.to;
            to.inEdges.remove(outEdge);
            edges.remove(outEdge);
        }
        vertices.remove(v);
    }

    @Override
    public void removeEdge(V from, V to) {
        Vertex<V, E> fromVertex = vertices.get(from);
        if (fromVertex == null) {
            return;
        }
        Vertex<V, E> toVertex = vertices.get(to);
        if (toVertex == null) {
            return;
        }

        Edge<V, E> edge = new Edge<>(fromVertex, toVertex, null);
        fromVertex.outEdges.remove(edge);
        toVertex.inEdges.remove(edge);
        edges.remove(edge);
    }

    public void print() {
        vertices.forEach((V key, Vertex<V, E> vertex) -> {
            System.out.println(vertex);
        });

        edges.forEach(System.out::println);
    }

    @Override
    public List<V> topologicalSort() {
        // 最终的拓扑有序序列
        List<V> list = new ArrayList<>();
        // 队列
        Queue<Vertex<V, E>> queue = new LinkedList<>();
        // 顶点的入度 ins = in degree num
        Map<Vertex<V, E>, Integer> ins = new HashMap<>(vertices.size());
        // 初始化，将度为 0 的节点先都放入队列
        vertices.forEach((V v, Vertex<V, E> vertex) -> {
            int in = vertex.inEdges.size();
            if (in == 0) {
                queue.offer(vertex);
            }
            ins.put(vertex, in);
        });
        while (!queue.isEmpty()) {
            Vertex<V, E> vertex = queue.poll();
            list.add(vertex.value);
            for (Edge<V, E> outEdge : vertex.outEdges) {
                // to 的入度
                int toIn = ins.get(outEdge.to) - 1;
                // 入度为 0 则入队
                if (toIn == 0) {
                    queue.offer(outEdge.to);
                } else {
                    // 入度不为 0，更新度
                    ins.put(outEdge.to, toIn);
                }
            }
        }
        return list;
    }

    public void bfs(V v) {
        Vertex<V, E> vertex = vertices.get(v);
        if (vertex == null) {
            return;
        }
        Queue<Vertex<V, E>> queue = new LinkedList<>();
        Set<Vertex<V, E>> queued = new HashSet<>();
        queue.offer(vertex);
        queued.add(vertex);
        while (!queue.isEmpty()) {
            Vertex<V, E> poll = queue.poll();
            System.out.println(poll);
            for (Edge<V, E> outEdge : poll.outEdges) {
                Vertex<V, E> to = outEdge.to;
                if (!queued.contains(to)) {
                    queue.offer(to);
                    queued.add(to);
                }
            }
        }
    }

    public void _dfs(V v) {
        Vertex<V, E> vertex = vertices.get(v);
        if (vertex == null) {
            return;
        }
        dfs(vertex, new HashSet<>());
    }

    public void dfs(Vertex<V, E> vertex, Set<Vertex<V, E>> visited) {
        System.out.println(vertex);
        visited.add(vertex);
        for (Edge<V, E> outEdge : vertex.outEdges) {
            if (!visited.contains(outEdge.to)) {
                dfs(outEdge.to, visited);
            }
        }
    }

    /**
     *
     * 当弹出一个顶点时
     * 1.从 outEdges 选择一条边
     * 2.将选择边的 from，to 依次入栈
     * 3.打印选择边的 to
     * 4.将 to 加入到已经访问的集合中
     * 5.break
     *
     * @param v
     */
    public void dfs(V v) {
        Vertex<V, E> vertex = vertices.get(v);
        if (vertex == null) {
            return;
        }
        Set<Vertex<V, E>> visited = new HashSet<>();
        Stack<Vertex<V, E>> stack = new Stack<>();

        // 先访问起点
        stack.push(vertex);
        System.out.println(vertex);
        visited.add(vertex);
        while (!stack.isEmpty()) {
            vertex = stack.pop();
            for (Edge<V, E> outEdge : vertex.outEdges) {
                // 边 to 已经访问过了
                if (visited.contains(outEdge.to)) {
                    continue;
                }
                // from 入栈，后面回溯
                stack.push(outEdge.from);
                // to 入栈，继续往下深入
                stack.push(outEdge.to);
                System.out.println(outEdge.to);
                visited.add(outEdge.to);
                break;
            }
        }
    }

    /**
     * 顶点
     */
    private static class Vertex<V, E> {

        // 顶点值
        V value;

        // 以该顶点为终点的边
        // C->A B->A，则以 A 为终点有两条边
        Set<Edge<V, E>> inEdges = new HashSet<>();

        // 以该顶点为起点的边
        // A->B A->C，则以 A 为起点有两条边
        Set<Edge<V, E>> outEdges = new HashSet<>();

        public Vertex(V value) {
            this.value = value;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Vertex<?, ?> vertex = (Vertex<?, ?>) o;
            return value.equals(vertex.value);
        }

        @Override
        public int hashCode() {
            return Objects.hash(value);
        }

        @Override
        public String toString() {
            return value.toString();
        }
    }

    /**
     * 边
     */
    private static class Edge<V, E> {

        // 起点
        Vertex<V, E> from;

        // 终点
        Vertex<V, E> to;

        // 边的权重
        E weight;

        public Edge(Vertex<V, E> from, Vertex<V, E> to) {
            this.from = from;
            this.to = to;
        }

        public Edge(Vertex<V, E> from, Vertex<V, E> to, E weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Edge<?, ?> edge = (Edge<?, ?>) o;
            return Objects.equals(from, edge.from) && Objects.equals(to, edge.to);
        }

        @Override
        public int hashCode() {
            return Objects.hash(from, to);
        }

        @Override
        public String toString() {
            return "from=" + from +
                    ", to=" + to +
                    ", weight=" + weight;
        }
    }
}
