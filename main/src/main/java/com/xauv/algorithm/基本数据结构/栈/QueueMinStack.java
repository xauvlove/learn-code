package com.xauv.algorithm.基本数据结构.栈;
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
 * @Date 2022/11/12 22:11
 * @Author Administrator
 * @Package com.xauv.algorithm.基本数据结构.栈
 * @Desc
 *
 * 使用一个链表，链表节点是 【节点值，当前链表最小值】
 *
 * 比如，插入 3 4 2 5 2 1
 *
 * 1.插入 3
 * 【3, 3】
 *
 * 2.插入 4
 * 【4, 3】 -> 【3, 3】
 *
 * 3.插入 2
 * 【2, 2】 -> 【4, 3】 -> 【3, 3】
 *
 * 4.插入 5
 * 【5, 2】 -> 【2, 2】 -> 【4, 3】 -> 【3, 3】
 *
 * ...
 *
 * 结果为：
 * head -> 【1, 1】 -> 【2, 2】 -> 【5, 2】 -> 【2, 2】 -> 【4, 3】 -> 【3, 3】
 *
 * 原理是空间换时间
 *
 *
 */
@SuppressWarnings({"unchecked", "rawtypes"})
public class QueueMinStack<E> {

    private Comparator<E> comparator;

    Node<E> head;

    private int size;

    public QueueMinStack() {

    }

    public QueueMinStack(Comparator<E> comparator) {
        this();
        this.comparator = comparator;
    }

    public void push(E e) {
        if (size == 0) {
            head = new Node<>(e, e, null);
        } else {
            E minVal = head.minVal;
            E min = compare(e, minVal) < 0 ? e : minVal;
            head = new Node<>(e, min, head);
        }
        size++;
    }

    public E pop() {
        if (size <= 0) {
            return null;
        }
        Node<E> pop = head;
        head = head.next;
        size--;
        return pop.val;
    }

    public E getMin() {
        if (size <= 0) {
            return null;
        }
        return head.minVal;
    }

    public E top() {
        if (size <= 0) {
            return null;
        }
        return head.val;
    }

    private int compare(E e1, E e2) {
        if (comparator != null) {
            return comparator.compare(e1, e2);
        } else {
            return ((Comparable)e1).compareTo(e2);
        }
    }

    private static class Node<E> {

        E val;

        E minVal;

        Node<E> next;

        public Node() {

        }

        public Node(E val, E minVal, Node<E> next) {
            this.val = val;
            this.minVal = minVal;
            this.next = next;
        }
    }
}
