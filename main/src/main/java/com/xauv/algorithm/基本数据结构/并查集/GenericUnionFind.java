package com.xauv.algorithm.基本数据结构.并查集;
/*
       /\   /\             /\.__                      
___  __)/___)/  __ _____  _)/|  |   _______  __ ____  
\  \/  /\__  \ |  |  \  \/ / |  |  /  _ \  \/ // __ \ 
 >    <  / __ \|  |  /\   /  |  |_(  <_> )   /\  ___/ 
/__/\_ \(____  /____/  \_/   |____/\____/ \_/  \___  >
      \/     \/                                    \/
*/

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @Date 2022/10/23 14:51
 * @Author Administrator
 * @Package com.xauv.algorithm.基本数据结构.并查集
 * @Desc
 */
public class GenericUnionFind<V> {

    private Map<V, Node<V>> nodes = new HashMap<>();

    public void makeSet(V v) {
        if (nodes.containsKey(v)) {
            return;
        }
        Node<V> node = new Node<>(v);
        nodes.put(v, node);
    }

    public V find(V v) {
        Node<V> node = findNode(v);
        if (node == null) {
            return null;
        }
        return node.value;
    }

    private Node<V> findNode(V v) {
        Node<V> node = nodes.get(v);
        if (node == null) {
            return null;
        }
        while (!Objects.equals(node.value, node.parent.value)) {
            // 使用路径减半优化
            node.parent = node.parent.parent;
            node = node.parent;
        }
        return node;
    }

    public void union(V v1, V v2) {
        Node<V> p1 = findNode(v1);
        Node<V> p2 = findNode(v2);
        if (p1 == null || p2 == null) {
            return;
        }
        if (Objects.equals(p1.value, p2.value)) {
            return;
        }
        if (p1.rank < p2.rank) {
            p1.parent = p2;
        } else if (p1.rank > p2.rank) {
            p2.parent = p1;
        } else {
            p1.parent = p2;
            p2.rank++;
        }
    }

    public boolean same(V v1, V v2) {
        return Objects.equals(find(v1), find(v2));
    }

    private static class Node<V> {

        /**
         * 泛型数据
         */
        V value;

        /**
         * 默认 parent 指向自己
         */
        Node<V> parent = this;

        /**
         * 基于树的高度
         */
        int rank = 1;

        public Node(V value) {
            this.value = value;
        }
    }
}
