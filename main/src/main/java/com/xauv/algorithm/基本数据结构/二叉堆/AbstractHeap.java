package com.xauv.algorithm.基本数据结构.二叉堆;
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
 * @Date 2022/10/18 22:03
 * @Author Administrator
 * @Package com.xauv.algorithm.基本数据结构.二叉堆
 * @Desc
 */
public abstract class AbstractHeap<E> implements Heap<E> {

    protected int size;
    protected Comparator<E> comparator;

    protected static final int defaultCapacity = 10;

    public AbstractHeap(Comparator<E> comparator) {
        this.comparator = comparator;
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
    public abstract void clear();

    @Override
    public abstract void put(E o);


    @Override
    public abstract void add(E o);

    @Override
    public abstract void remove();

    @Override
    public abstract void replace(E o);
}
