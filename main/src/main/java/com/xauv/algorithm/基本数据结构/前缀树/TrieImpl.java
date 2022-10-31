package com.xauv.algorithm.基本数据结构.前缀树;
/*
       /\   /\             /\.__                      
___  __)/___)/  __ _____  _)/|  |   _______  __ ____  
\  \/  /\__  \ |  |  \  \/ / |  |  /  _ \  \/ // __ \ 
 >    <  / __ \|  |  /\   /  |  |_(  <_> )   /\  ___/ 
/__/\_ \(____  /____/  \_/   |____/\____/ \_/  \___  >
      \/     \/                                    \/
*/


import java.util.*;

/**
 * @Date 2022/10/19 21:57
 * @Author Administrator
 * @Package com.xauv.algorithm.基本数据结构.前缀树
 * @Desc
 *
 * 前缀树节点并不存储 key，节点和节点之间存储路径
 *
 * 比如，存储 dog，那么节点关系为  [节点]-d->[节点]-o-[节点]-g-[节点]  路径上存储了信息，而不是在节点里面存储
 *
 *
 * 缺点：耗费内存，只能适用于前缀搜索
 *
 */
public class TrieImpl<V> implements Trie<V> {

    int size = 0;

    Node<V> root;

    public TrieImpl() {
        root = new Node<>();
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void clear() {
        size = 0;
        root.children = new HashMap<>();
    }

    @Override
    public V add(String str, V value) {
        if (str == null || str.length() == 0) {
            throw new IllegalArgumentException();
        }
        Node<V> node = root;
        char[] chars = str.toCharArray();
        for (char c : chars) {
            Map<Character, Node<V>> children = node.children;
            Node<V> childNode = children.get(c);
            if (childNode == null) {
                childNode = new Node<>();
                childNode.parent = node;
                childNode.c = c;
                children.put(c, childNode);
            }
            node = childNode;
        }
        if (!node.word) {
            size++;
            node.word = true;
            node.value = value;
            return value;
        } else {
            // 原来已经存在该前缀，返回老值并替换
            V old = node.value;
            node.value = value;
            return old;
        }
    }

    @Override
    public V remove(String str) {
        Node<V> node = node(str);
        if (node == null) {
            return null;
        }
        V old = node.value;
        // 没有子节点，从尾开始，往上删除，如果遇到分叉，停止删除
        if (node.children.isEmpty()) {
            Node<V> parent = null;
            while ((parent = node.parent) != null) {
                Map<Character, Node<V>> children = parent.children;
                children.remove(node.c);
                if (!children.isEmpty()) {
                    break;
                }
                node = parent;
            }
        } else {
            // 有子节点，可能下面有其他字符串前缀
            node.value = null;
            node.word = false;
            size--;
        }
        return old;
    }

    @Override
    public boolean contains(String str) {
        return node(str) != null;
    }

    @Override
    public V get(String str) {
        Node<V> node = node(str);
        if (node == null) {
            return null;
        }
        return node.value;
    }

    @Override
    public boolean startWith(String prefix) {
        if (root == null) {
            return false;
        }
        Node<V> node = root;
        if (prefix == null || prefix.length() == 0) {
            return false;
        }
        char[] chars = prefix.toCharArray();
        for (char c : chars) {
            Map<Character, Node<V>> children = node.children;
            Node<V> child = children.get(c);
            if (child == null) {
                return false;
            }
            node = child;
        }
        return true;
    }

    @Override
    public Set<V> prefixValues(String prefix) {
        if (root == null) {
            return null;
        }
        Node<V> node = root;
        if (prefix == null || prefix.length() == 0) {
            return null;
        }
        char[] chars = prefix.toCharArray();
        for (char c : chars) {
            Map<Character, Node<V>> children = node.children;
            Node<V> child = children.get(c);
            if (child == null) {
                return null;
            }
            node = child;
        }

        // 使用深度优先算法，求出从 node 开始，到叶子节点每一条路径，每一条路径结束，就得到几个单词【一条路径上可能包含多个单词】
        Set<V> result = new HashSet<>();
        Stack<Node<V>> stack = new Stack<>();
        Set<Node<V>> visited = new HashSet<>();
        stack.push(node);
        visited.add(node);
        while (!stack.isEmpty()) {
            Node<V> pop = stack.pop();
            if (pop.word) {
                result.add(pop.value);
            }
            Map<Character, Node<V>> children = pop.children;
            for (Map.Entry<Character, Node<V>> entry : children.entrySet()) {
                Node<V> value = entry.getValue();
                if (!visited.contains(value)) {
                    stack.push(pop);
                    stack.push(value);
                    visited.add(value);
                }
            }
        }
        return result;
    }

    private Node<V> node(String str) {
        if (root == null) {
            return null;
        }
        Node<V> node = root;
        if (str == null || str.length() == 0) {
            return null;
        }
        char[] chars = str.toCharArray();
        for (char c : chars) {
            Map<Character, Node<V>> children = node.children;
            node = children.get(c);
            if (node == null) {
                return null;
            }
        }
        return node.word ? node : null;
    }

    private static class Node<V> {

        // 存储路径
        Map<Character, Node<V>> children = new HashMap<>();

        Node<V> parent;

        char c;

        V value;

        // 是否为单词结尾
        boolean word;
    }
}
