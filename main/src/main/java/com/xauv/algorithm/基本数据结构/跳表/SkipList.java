package com.xauv.algorithm.基本数据结构.跳表;
/*
       /\   /\             /\.__                      
___  __)/___)/  __ _____  _)/|  |   _______  __ ____  
\  \/  /\__  \ |  |  \  \/ / |  |  /  _ \  \/ // __ \ 
 >    <  / __ \|  |  /\   /  |  |_(  <_> )   /\  ___/ 
/__/\_ \(____  /____/  \_/   |____/\____/ \_/  \___  >
      \/     \/                                    \/
*/


import java.util.Comparator;

/**
 * @Date 2022/10/29 16:11
 * @Author Administrator
 * @Package com.xauv.algorithm.基本数据结构.跳表
 * @Desc
 *
 * 在有序链表基础上增加了跳跃功能
 *
 * 1990 年发布
 * 设计初衷取代红黑树
 *
 * 跳表的实现和维护更加简单（相对于红黑树）
 * 搜索、删除、添加平均时间复杂度为 O(log(n))
 *
 * 一般会限制跳表的最高层数
 *
 */
@SuppressWarnings("unchecked")
public class SkipList<K ,V> {

    private Node<K, V> root;

    private Comparator<K> comparator;

    private static final int MAX_LEVEL = 32;

    private static final double P = 0.25d;

    // 有效层数，初始化时，可能有的高层是没有用到的【空的】，因此这里记录有效层数
    private int level = 0;

    public SkipList() {
        this(null);
    }

    public SkipList(Comparator<K> comparator) {
        root = new Node<>();
        root.nexts = new Node[MAX_LEVEL];
        this.comparator = comparator;
    }

    private int size;

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public V put(K k, V v) {
        if (k == null) {
            return null;
        }
        Node<K, V> node = root;
        // 记录前驱节点
        Node<K, V>[] prev = new Node[level];
        // 从有效层开始找
        for (int i = level-1; i >= 0; i--) {
            int cmp = -1;
            while (node.nexts[i] != null && (cmp = compare(k, node.nexts[i].key)) > 0) {
                node = node.nexts[i];
            }
            if (cmp == 0) {
                V old = node.nexts[i].value;
                node.nexts[i].value = v;
                return old;
            }
            prev[i] = node;
        }

        // 确定新添加元素的层数
        int newNodeLevel = getRandomLevel();

        // 能来到这里，说明 k 找不到了，需要插入新值，现在 node 是前驱节点
        Node<K, V> newNode = new Node<>(k, v, newNodeLevel);
        for (int i = 0; i < newNodeLevel; i++) {
            if (i >= level) {
                root.nexts[i] = newNode;
            } else {
                newNode.nexts[i] = prev[i].nexts[i];
                prev[i].nexts[i] = newNode;
            }
        }
        // 更新有效层数
        level = Math.max(level, newNodeLevel);
        size++;
        return newNode.value;
    }

    public V get(K k) {
        if (k == null) {
            return null;
        }
        Node<K, V> node = root;
        // 从有效层开始找
        for (int i = level-1; i >= 0; i--) {
            int cmp = -1;
            while (node.nexts[i] != null && (cmp = compare(k, node.nexts[i].key)) > 0) {
                node = node.nexts[i];
            }
            if (cmp == 0) {
                return node.nexts[i].value;
            }
        }
        return null;
    }

    public V remove(K k) {
        if (k == null) {
            return null;
        }

        Node<K, V> node = root;
        // 记录前驱节点
        Node<K, V>[] prev = new Node[level];
        boolean exist = false;
        // 从有效层开始找
        for (int i = level - 1; i >= 0; i--) {
            int cmp = -1;
            while (node.nexts[i] != null && (cmp = compare(k, node.nexts[i].key)) > 0) {
                node = node.nexts[i];
            }
            if (cmp == 0) {
                exist = true;
            }
            prev[i] = node;
        }
        if (!exist) {
            return null;
        }
        // 存在被删除的节点
        Node<K, V> removedNode = node.nexts[0];

        // 重新设置后继
        for (int i = 0; i < removedNode.nexts.length; i++) {
            prev[i].nexts[i]  = removedNode.nexts[i];
        }
        // 更新跳表层数
        int newLevel = level;
        while (--newLevel >=0 && root.nexts[newLevel] == null) {
            level = newLevel;
        }
        size--;
        return removedNode.value;
    }

    /**
     * 统计数据证实：跳表层数随机即可
     * 1 - 32 层都可以
     * @return
     */
    private int getRandomLevel() {
        int level = 1;
        while (Math.random() < P && level < MAX_LEVEL) {
            level++;
        }
        return level;
    }

    private int compare(K k1, K k2) {
        if (comparator != null) {
            return comparator.compare(k1, k2);
        } else {
            return ((Comparable<K>)(k1)).compareTo(k2);
        }
    }

    private static class Node<K, V> {

        K key;

        V value;

        Node<K, V>[] nexts;

        public Node() {

        }

        public Node(K key, V value, int level) {
            this.key = key;
            this.value = value;
            nexts = new Node[level];
        }
    }


    public static void main(String[] args) {
        SkipList<Integer, String> skipList = new SkipList<>();
        for (int i = 0; i < 100; i++) {
            skipList.put(i, String.valueOf(i));
        }
        System.out.println(skipList.get(87));
        skipList.remove(87);
        System.out.println(skipList.get(87));
        skipList.put(87, String.valueOf(87));
        System.out.println(skipList.get(87));

    }
}
