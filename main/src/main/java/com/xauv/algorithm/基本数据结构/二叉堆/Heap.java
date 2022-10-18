package com.xauv.algorithm.基本数据结构.二叉堆;


public interface Heap<E> {

    int size();

    boolean isEmpty();

    void clear();

    void put(E e);

    E get();

    void add(E e);

    void remove();

    void replace(E e);
}
