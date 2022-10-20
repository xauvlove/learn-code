package com.xauv.algorithm.基本数据结构.二叉树.题目;

import com.xauv.algorithm.基本数据结构.二叉堆.BinaryHeap;
import com.xauv.algorithm.基本数据结构.二叉堆.Heap;
import com.xauv.algorithm.基本数据结构.二叉树.utils.BinaryTreeInfo;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * @author: Bing Juan
 * @date: 2022/10/20 14 54
 * @desc:
 *
 *
 */
public class 哈夫曼树 {

    static HeapNode rootNode;

    public HeapNode formTree(String code) {
        HeapNode root;
        Heap<HeapNode> heap = calculateFrequency(code);
        while (true) {
            if (heap.size() <= 1) {
                root = heap.get();
                // 只是为了打印树
                rootNode = root;
                break;
            }
            HeapNode remove1 = heap.remove();
            HeapNode remove2 = heap.remove();
            HeapNode mergeNode = new HeapNode((char) 0, remove1.times + remove2.times, false);
            mergeNode.right = remove1;
            mergeNode.left = remove2;
            heap.add(mergeNode);
        }
        return root;
    }

    public String encode(HeapNode root) {
        Stack<HeapNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            HeapNode pop = stack.pop();
            HeapNode child = pop.left;
            while (child != null) {
                stack.push(child);
                child = child.left;
            }

        }
        return "";
    }

    public Heap<HeapNode> calculateFrequency(String code) {
        Map<Character, Integer> map = new HashMap<>();
        for (char c : code.toCharArray()) {
            if (map.containsKey(c)) {
                map.put(c, map.get(c) + 1);
            } else {
                map.put(c, 1);
            }
        }

        Heap<HeapNode> heap = new BinaryHeap<>(new Comparator<HeapNode>() {
            @Override
            public int compare(HeapNode o1, HeapNode o2) {
                return o1.times - o2.times;
            }
        });
        for (Map.Entry<Character, Integer> entry : map.entrySet()) {
            heap.add(new HeapNode(entry.getKey(), entry.getValue(), true));
        }

        return heap;
    }

    public static void main(String[] args) {

        String code = "EEEEEDDDDCCCBBA";
        哈夫曼树 hufManCode = new 哈夫曼树();
        hufManCode.formTree(code);

    }

    private static class HeapNode implements Comparable<HeapNode>, BinaryTreeInfo {

        char c;

        int times;

        boolean isLeaf;

        HeapNode left;

        HeapNode right;

        public HeapNode(char c, int times) {
            this.c = c;
            this.times = times;
        }

        public HeapNode(char c, int times, boolean isLeaf) {
            this.c = c;
            this.times = times;
            this.isLeaf = isLeaf;
        }

        public boolean equals(HeapNode node) {
            return c == node.c;
        }

        public int hashCode() {
            return c;
        }

        @Override
        public int compareTo(HeapNode o) {
            return o.times - times;
        }

        @Override
        public Object root() {
            return rootNode;
        }

        @Override
        public Object left(Object node) {
            return ((HeapNode) node).left;
        }

        @Override
        public Object right(Object node) {
            return ((HeapNode) node).right;
        }

        @Override
        public Object string(Object node) {
            return ((HeapNode) node).c;
        }
    }
}
