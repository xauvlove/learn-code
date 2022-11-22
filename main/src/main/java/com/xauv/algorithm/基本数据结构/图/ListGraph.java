package com.xauv.algorithm.基本数据结构.图;
/*
       /\   /\             /\.__                      
___  __)/___)/  __ _____  _)/|  |   _______  __ ____  
\  \/  /\__  \ |  |  \  \/ / |  |  /  _ \  \/ // __ \ 
 >    <  / __ \|  |  /\   /  |  |_(  <_> )   /\  ___/ 
/__/\_ \(____  /____/  \_/   |____/\____/ \_/  \___  >
      \/     \/                                    \/
*/

import com.xauv.algorithm.基本数据结构.二叉堆.BinaryHeap;
import com.xauv.algorithm.基本数据结构.并查集.GenericUnionFind;

import java.util.*;

/**
 * @Date 2022/10/23 16:50
 * @Author Administrator
 * @Package com.xauv.algorithm.基本数据结构.图
 * @Desc
 *
 * 以邻接表结构实现图
 */
public class ListGraph<V, E> extends AbstractGraph<V, E> {

    private final Map<V, Vertex<V, E>> vertices = new HashMap<>();

    private final Set<Edge<V, E>> edges = new HashSet<>();

    private final Comparator<Edge<V, E>> edgeComparator = (o1, o2) -> weightManager.compare(o1.weight, o2.weight);

    public ListGraph() {
        super(null);
    }

    public ListGraph(WeightManager<E> weightManager) {
        super(weightManager);
    }

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

    @Override
    public Set<EdgeInfo<V, E>> mst() {
        // prim 算法
        //return prim();
        // kruskal 算法
        return prim();
        //return new Random().nextBoolean() ? prim() : kruskal();
    }

    @Override
    public Map<V, E> shortPath(V begin) {
        Vertex<V, E> vertex = vertices.get(begin);
        if (vertex == null) {
            return new HashMap<>();
        }

        // 已经确定了最小路径 的元素集合
        Map<V, E> selectedPaths = new HashMap<>();
        // 还未确定最小路径 的元素集合
        Map<Vertex<V, E>, E> paths = new HashMap<>();
        paths.put(vertex, null);
        while (true) {
            // 获取还未确定最小路径元素集合中，下一个立即要进行松弛操作的元素
            Map.Entry<Vertex<V, E>, E> minPathVertexEntry = paths.entrySet().stream().min(
                    (o1, o2) -> weightManager.compare(o1.getValue(), o2.getValue())).orElse(null);
            if (minPathVertexEntry == null) {
                break;
            }
            // 已经确定了最短路径的顶点
            Vertex<V, E> minPathVertex = minPathVertexEntry.getKey();
            // minPathWeightSum 是从初始点到该顶点的最短路径
            E minPathWeightSum = minPathVertexEntry.getValue();
            // 将已经确定最短路径的顶点 加入到结果集合中
            selectedPaths.put(minPathVertex.value, minPathWeightSum);
            // 将这个顶点从 未确定最短路径集合 中删除
            paths.remove(minPathVertex);
            // 对 该顶点 进行松弛操作，更新 to 顶点的最短距离
            relax(minPathVertexEntry, paths, selectedPaths);
        }
        return selectedPaths;
    }

    @Override
    public Map<V, PathInfo<V, E>> calShortPathByDijkstra(V begin) {
        Vertex<V, E> vertex = vertices.get(begin);
        if (vertex == null) {
            return new HashMap<>();
        }
        // 已经确定了最小路径 的元素集合
        Map<V, PathInfo<V, E>> selectedPaths = new HashMap<>();
        // 还未确定最小路径 的元素集合
        Map<V, PathInfo<V, E>> paths = new HashMap<>();
        // 初始化 放入初始节点
        paths.put(vertex.value, new PathInfo<>());

        while (true) {
            // 获取还未确定最小路径元素集合中，下一个立即要进行松弛操作的元素
            Map.Entry<V, PathInfo<V, E>> minPathVertexEntry = paths.entrySet().stream().min(
                    (o1, o2) -> weightManager.compare(o1.getValue().totalWeight, o2.getValue().totalWeight)).orElse(null);
            if (minPathVertexEntry == null) {
                break;
            }
            V vertexValue = minPathVertexEntry.getKey();
            Vertex<V, E> minPathVertex = vertices.get(vertexValue);
            PathInfo<V, E> minPathVertexPathInfo = minPathVertexEntry.getValue();
            selectedPaths.put(minPathVertex.value, minPathVertexPathInfo);
            paths.remove(minPathVertex.value);
            // 对顶点所有出边进行松弛
            for (Edge<V, E> outEdge : minPathVertex.outEdges) {
                // 如果已经确定最短路径，无需继续松弛，
                if (selectedPaths.containsKey(outEdge.to.value)) {
                    continue;
                }
                PathInfo<V, E> minPathInfo = selectedPaths.get(minPathVertex.value);
                relax(outEdge, minPathInfo, paths);
            }
        }
        return selectedPaths;
    }

    @Override
    public Map<V, PathInfo<V, E>> calShortPathByBellmanFord(V begin) {
        Vertex<V, E> vertex = vertices.get(begin);
        if (vertex == null) {
            return new HashMap<>();
        }
        // 已经确定了最小路径 的元素集合
        Map<V, PathInfo<V, E>> selectedPaths = new HashMap<>();
        selectedPaths.put(begin, new PathInfo<>());
        // 进行 V-1 次松弛
        for (int i = 0; i < (vertices.size() - 1); i++) {
            // 遍历所有边
            for (Edge<V, E> edge : edges) {
                PathInfo<V, E> pathInfo = selectedPaths.get(edge.from.value);
                if (pathInfo == null) {
                    continue;
                }
                relax(edge, pathInfo, selectedPaths);
            }
        }
        // 如果经过 V-1 次之后，还能找到最小路径，说明有负权环
        for (Edge<V, E> edge : edges) {
            PathInfo<V, E> pathInfo = selectedPaths.get(edge.from.value);
            if (pathInfo == null) {
                continue;
            }
            if (relax(edge, pathInfo, selectedPaths)) {
                System.err.println("存在负权环");
                return new HashMap<>();
            }
        }
        return selectedPaths;
    }

    @Override
    public Map<V, Map<V, PathInfo<V, E>>> calShortPathByFloyd() {
        Map<V, Map<V, PathInfo<V, E>>> paths = new HashMap<>();
        // paths 初始化，将所有边放到 paths 中
        for (Edge<V, E> edge : edges) {
            Map<V, PathInfo<V, E>> edgePath = paths.get(edge.from.value);
            if (edgePath == null || edgePath.isEmpty()) {
                edgePath = new HashMap<>();
                paths.put(edge.from.value, edgePath);
            }
            PathInfo<V, E> pathInfo = new PathInfo<>();
            pathInfo.totalWeight = edge.weight;
            pathInfo.edgeInfos.add(edge.info());
            edgePath.put(edge.to.value, pathInfo);
        }

        vertices.forEach((V v2, Vertex<V, E> vertex2) -> {
            vertices.forEach((V v1, Vertex<V, E> vertex1) -> {
                vertices.forEach((V v3, Vertex<V, E> vertex3) -> {
                    // 相同点到点不需要执行 v1 == v2 场景
                    if (Objects.equals(v1, v2) || Objects.equals(v2, v3) || Objects.equals(v1, v3)) {
                        return;
                    }
                    // v1->v2 最短路径
                    PathInfo<V, E> path12 = getPathInfo(v1, v2, paths);
                    if (path12 == null) {
                        return;
                    }
                    // v2 -> v3 最短路径
                    PathInfo<V, E> path23 = getPathInfo(v2, v3, paths);
                    if (path23 == null) {
                        return;
                    }
                    // v1 -> v3 最短路径
                    PathInfo<V, E> path13 = getPathInfo(v1, v3, paths);

                    E weight = weightManager.add(path12.totalWeight, path23.totalWeight);

                    if (path13 != null && weightManager.compare(weight, path13.totalWeight) >= 0) {
                        return;
                    }
                    if (path13 == null) {
                        path13 = new PathInfo<>();
                        paths.get(v1).put(v3, path13);
                    }
                    path13.edgeInfos.clear();
                    path13.totalWeight = weight;
                    path13.edgeInfos.addAll(path12.edgeInfos);
                    path13.edgeInfos.addAll(path23.edgeInfos);
                });
            });
        });
        return paths;
    }

    private PathInfo<V, E> getPathInfo(V from, V to, Map<V, Map<V, PathInfo<V, E>>> paths) {
        Map<V, PathInfo<V, E>> map = paths.get(from);
        if (map == null || map.isEmpty()) {
            return null;
        }
        return map.get(to);
    }

    private boolean relax(Edge<V, E> edge, PathInfo<V, E> minPathInfo, Map<V, PathInfo<V, E>> paths) {
        Vertex<V, E> to = edge.to;
        E newPathWeight = weightManager.add(minPathInfo.totalWeight, edge.weight);
        PathInfo<V, E> pathInfo = new PathInfo<>();
        if (paths.containsKey(to.value)) {
            PathInfo<V, E> oldPathInfo = paths.get(to.value);
            E oldPathWeight = oldPathInfo.totalWeight;
            if (weightManager.compare(newPathWeight, oldPathWeight) < 0) {
                pathInfo.totalWeight = newPathWeight;
                pathInfo.edgeInfos.addAll(minPathInfo.edgeInfos);
                pathInfo.edgeInfos.add(edge.info());
                paths.put(to.value, pathInfo);
            } else {
                // 松弛失败
                return false;
            }
        } else {
            pathInfo.edgeInfos.addAll(minPathInfo.edgeInfos);
            pathInfo.edgeInfos.add(edge.info());
            pathInfo.totalWeight = newPathWeight;
            paths.put(to.value, pathInfo);
        }
        return true;
    }

    private void relax(Map.Entry<Vertex<V, E>, E> entry, Map<Vertex<V, E>, E> paths, Map<V, E> selectedPaths) {
        Vertex<V, E> minPathVertex = entry.getKey();
        // 从起点到该顶点的当前最小路径
        E minPathWeightSum = entry.getValue();
        // 对 该顶点 进行松弛操作，更新 to 顶点的最短距离
        for (Edge<V, E> outEdge : minPathVertex.outEdges) {
            Vertex<V, E> to = outEdge.to;
            // 如果之前已经有路径，则进行比较更新
            if (selectedPaths.containsKey(to.value)) {
                continue;
            }
            // 如果之前已经有路径，则进行比较更新
            if (paths.containsKey(to)) {
                E oldPathWeight = paths.get(to);
                E newPathWeight = weightManager.add(minPathWeightSum, outEdge.weight);
                // 如果新路径权值更小，则更新
                if (weightManager.compare(newPathWeight, oldPathWeight) < 0) {
                    paths.put(to, newPathWeight);
                }
            } else {
                // 如果之前没有路径，则加入
                E newPathWeight = weightManager.add(minPathWeightSum, outEdge.weight);
                paths.put(to, newPathWeight);
            }
        }
    }

    /**
     * 使用 prim 算法求最小生成树
     * @return
     */
    private Set<EdgeInfo<V, E>> prim() {
        if (!vertices.values().iterator().hasNext()) {
            return new HashSet<>();
        }
        Set<EdgeInfo<V, E>> edgeInfos = new HashSet<>();
        // 最小生成树中，已经囊括的元素
        Set<Vertex<V, E>> addedVertex = new HashSet<>();
        // 随便选一个顶点，加入到最小生成树【元素集合】中
        Vertex<V, E> vertex = vertices.values().iterator().next();
        addedVertex.add(vertex);
        // 将这个顶点的出边加入最小堆，prim 算法是挨个节点加入，因此现在只加入这个顶点的出边
        BinaryHeap<Edge<V, E>> heap = new BinaryHeap<>(vertex.outEdges, edgeComparator, false);

        while (!heap.isEmpty()) {
            // 最小生成树节点集合已经包括了所有的图元素，表示最小生成树已找到
            if (addedVertex.size() >= vertices.size()) {
                break;
            }
            // 拿到权值最小的边
            Edge<V, E> edge = heap.remove();
            // 如果节点已经加入过最小生成树集合，就不要再进行下面操作了
            if (addedVertex.contains(edge.to)) {
                continue;
            }
            // 找到了权值最小的边，加入到结果集合
            edgeInfos.add(edge.info());
            addedVertex.add(edge.to);
            // edge 是权值最小的边，把他的 to 拿出来，将他的边全部加入最小堆
            heap.addAll(edge.to.outEdges);
        }
        if (edgeInfos.size() < vertices.size() - 1) {
            return new HashSet<>();
        }
        return edgeInfos;
    }

    /**
     * 使用 kruskal 算法求最小生成树
     * @return
     */
    private Set<EdgeInfo<V, E>> kruskal() {

        Set<EdgeInfo<V, E>> edgeInfos = new HashSet<>();
        // kruskal 是在所有边里面挑最小的，因此加入所有的边到最小堆
        BinaryHeap<Edge<V, E>> heap = new BinaryHeap<>(edges, edgeComparator, false);

        // 并查集
        GenericUnionFind<Vertex<V, E>> uf = new GenericUnionFind<>();
        // 顶点单独形成集合
        vertices.forEach((V v, Vertex<V, E> vertex) -> {
            uf.makeSet(vertex);
        });
        while (!heap.isEmpty()) {
            // 边足够，结束
            if (edgeInfos.size() >= vertices.size() - 1) {
                break;
            }
            Edge<V, E> edge = heap.remove();
            // 判断是否形成环，使用并查集
            // 如果顶点属于相同集合，说明形成了环
            if (uf.same(edge.from, edge.to)) {
                continue;
            }
            // 到这里，顶点属于不同集合，现将他们归入同一集合
            uf.union(edge.from, edge.to);
            edgeInfos.add(edge.info());
        }
        if (edgeInfos.size() < vertices.size() - 1) {
            return new HashSet<>();
        }
        return edgeInfos;
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

    public Set<Vertex<V, E>> vertices() {
        return new HashSet<>(vertices.values());
    }
    public Set<Edge<V, E>> edges() {
        return edges;
    }

    /**
     * 顶点
     */
    public static class Vertex<V, E> {

        // 顶点值
        V value;

        // 以该顶点为终点的边
        // C->A B->A，则以 A 为终点有两条边
        Set<Edge<V, E>> inEdges = new HashSet<>();

        // 以该顶点为起点的边
        // A->B A->C，则以 A 为起点有两条边
        Set<Edge<V, E>> outEdges = new HashSet<>();

        public V val() {
            return value;
        }

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
    public static class Edge<V, E> {

        // 起点
        Vertex<V, E> from;

        // 终点
        Vertex<V, E> to;

        // 边的权重
        E weight;

        public Vertex<V, E> from() {
            return from;
        }

        public Vertex<V, E> to() {
            return to;
        }

        public Edge(Vertex<V, E> from, Vertex<V, E> to) {
            this.from = from;
            this.to = to;
        }

        public Edge(Vertex<V, E> from, Vertex<V, E> to, E weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }

        public EdgeInfo<V, E> info() {
            return new EdgeInfo<>(from.value, to.value, weight);
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
