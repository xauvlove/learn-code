package com.xauv.algorithm.基本数据结构.二叉堆;
/*
       /\   /\             /\.__                      
___  __)/___)/  __ _____  _)/|  |   _______  __ ____  
\  \/  /\__  \ |  |  \  \/ / |  |  /  _ \  \/ // __ \ 
 >    <  / __ \|  |  /\   /  |  |_(  <_> )   /\  ___/ 
/__/\_ \(____  /____/  \_/   |____/\____/ \_/  \___  >
      \/     \/                                    \/
*/

import com.xauv.algorithm.基本数据结构.二叉树.utils.BinaryTreeInfo;

import java.util.Arrays;
import java.util.Comparator;

/**
 *
 *
 * @Date 2022/10/18 21:13
 * @Author Administrator
 * @Package com.xauv.algorithm.基本数据结构.二叉堆
 *
 * 二叉堆是完全二叉树
 *
 * 一般使用数组实现
 *
 * 二叉堆的应用一般是寻找最大/最小元素
 *
 * 不使用节点，节点浪费空间，
 *
 * 节点的父节点位置是 floor((i-1)/2)，比如 节点位置为 4，则父节点位置是 1，节点位置是 5，则父节点位置是 2
 *
 * 二叉堆只保证堆顶是最大的，并不保证像二叉搜索树一样的顺序大小，是偏序树
 *
 *
 * @Desc
 */
@SuppressWarnings("JavadocBlankLines")
public class BinaryHeap<E> extends AbstractHeap<E> implements BinaryTreeInfo {

    private E[] elements;

    public BinaryHeap(Comparator<E> comparator) {
        super(comparator);
        this.elements = (E[])(new Object[defaultCapacity]);
    }


    @Override
    public void clear() {
        Arrays.fill(elements, null);
    }

    @Override
    public void put(E e) {

    }

    @Override
    public E get() {
        if (size == 0) {
            return null;
        }
        return elements[0];
    }


    @Override
    public void add(E e) {
        if (e == null) {
            throw new IllegalArgumentException();
        }
        if (size >= elements.length) {
            ensureCapacity();
        }
        elements[size] = e;
        siftUp(size);
        size++;
    }

    /**
     * 删除堆顶元素
     *
     * 拿最后一个元素覆盖堆顶元素
     *
     * 删除最后一个元素
     *
     * 调整堆，
     * 从堆顶开始，比较他的子节点，然后进行交换，
     * 交换后，从交换子节点位置开始，继续向下交换
     * 直到没有子节点或者节点大于等于他的所以子节点
     *
     * 原来：
     *     ┌─72─┐
     *     │    │
     *  ┌─68─┐  43
     *  │    │
     * 50    38
     *
     * 删除 72 之后
     *
     *     ┌─null─┐
     *     │      │
     *  ┌─68─┐    43
     *  │    │
     * 50    38
     *
     * 将最后一个节点替换到堆顶
     *
     *     ┌─38─┐
     *     │    │
     *  ┌─68    43
     *  │
     * 50
     *
     * 从堆顶开始，往下进行交换，交换后为
     *
     *     ┌─68─┐
     *     │    │
     *  ┌─ 38   43
     *  │
     * 50
     *
     * 发现 38 还是小于 50 的，再进行交换
     *     ┌─68─┐
     *     │    │
     *  ┌─ 50   43
     *  │
     * 38
     *
     * 调整完成
     *
     */
    @Override
    public void remove() {
        if (size == 0) {
            return;
        }
        elements[0] = elements[size-1];
        elements[size-1] = null;
        siftDown(0);
        size = size - 1;
    }

    /**
     * 删除堆顶元素，再添加一个新元素再堆顶
     *
     * 直接覆盖堆顶，然后从堆顶下滤
     *
     * @param e
     */
    @Override
    public void replace(E e) {
        if (size == 0) {
            elements[0] = e;
            size++;
        } else {
            elements[0] = e;
            siftDown(0);
        }
    }

    /**
     * 上滤
     * 让 index 位置的元素上滤
     *
     * 上滤就是不断地和父节点进行比较，并与父节点交换位置
     *
     * @param index
     */
    private void siftUp(int index) {
        // 必须右父节点才能上滤
        E e = elements[index];
        while (index > 0) {
            int pIndex = (index - 1) >> 1;
            E p = elements[pIndex];
            int cmp = compare(e, p);
            if (cmp <= 0) {
                return;
            }
            // 交换位置 == 交换值
            elements[index] = p;
            elements[pIndex] = e;
            index = pIndex;
        }
    }

    private void siftDown(int index) {
        E e = elements[0];

        // 进入循环，必须得有子节点才能进行比较交换
        // 必须保证 index 位置是非叶子节点
        // 完全二叉树的性质：如果遇到第一个叶子节点，那么之后的节点全都是叶子节点
        // 循环条件是 while (index < 第一个叶子节点的位置)
        // 定理：第一个叶子节点的位置 = 非叶子节点的数量
        // 完全二叉树：叶子节点的数量 = floor((n+1)/2)
        // 完全二叉树：非叶子节点的数量 = floor(n/2)
        // 因此 循环条件为 while (index < floor(n/2))
        int half = size >> 1;
        while (index < half) {
            // 子节点有两种情况：只有左孩子，有左右孩子

            // 先默认使用左孩子
            int lChildIndex = (index << 1) + 1;
            E lChild = elements[lChildIndex];

            int rChildIndex = lChildIndex + 1;
            E rChild = null;
            if (rChildIndex < size) {
                rChild = elements[rChildIndex];
            }

            E maxChild = rChild == null ? lChild : (compare(lChild, rChild) > 0 ? lChild : rChild);

            int maxChildIndex = rChild == null ? lChildIndex : (compare(lChild, rChild) > 0 ? lChildIndex : rChildIndex);

            int cmp = compare(e, maxChild);
            if (cmp >= 0) {
                break;
            }
            // 能到这里，说明子节点比父节点大，需要交换
            // 节点交换
            elements[maxChildIndex] = e;
            elements[index] = maxChild;
            index = maxChildIndex;
        }
    }

    /**
     * 批量建堆
     *
     * 两种做法：
     * 1.自上而下的上滤
     * 2.自下而上的下滤
     * * * * * * * * * * * * *
     *
     * 1.自上而下的上滤
     * 从堆顶 index = 1 开始，往下交换，交换自己和父节点的位置
     * 然后 index = index + 1，继续往下交换
     * 直到最后一个节点
     *
     * 比如：
     *
     *    ┌───30──┐
     *    │       │
     * ┌─34─┐   ┌─73
     * │    │   │
     * 60    68 43
     *
     * 从 34 开始，交换一次，交换后为：
     *    ┌───34──┐
     *    │       │
     * ┌─30─┐   ┌─73
     * │    │   │
     * 60    68 43
     *
     * 从 73 开始，交换一次，交换后为：
     *    ┌───73──┐
     *    │       │
     * ┌─30─┐   ┌─34
     * │    │   │
     * 60    68 43
     *
     * 从 60 开始，交换一次，交换后为：
     *    ┌───73──┐
     *    │       │
     * ┌─60─┐   ┌─34
     * │    │   │
     * 30    68 43
     *
     * 从 68 开始，交换一次，交换后为：
     *    ┌───73──┐
     *    │       │
     * ┌─68─┐   ┌─34
     * │    │   │
     * 30    60 43
     *
     * 从 43 开始，交换一次，交换后为：
     *    ┌───73──┐
     *    │       │
     * ┌─68─┐   ┌─43
     * │    │   │
     * 30    60 34
     *
     * 交换完成
     *
     * 2.自下而上的下滤
     * 从最后一个节点开始 从 index = size / 2 - 1 开始【下滤是自己和子节点交换，叶子节点无子节点，因此不从index=size-1开始】，交换自己和父节点的位置
     *
     * 然后 index = index - 1，继续向下交换
     * 直到堆顶
     *
     * 比如
     *    ┌───30──┐
     *    │       │
     * ┌─34─┐   ┌─73
     * │    │   │
     * 60    68 43
     *
     * 我们只需要从 73 的位置开始即可
     * 从 73 开始，第一次无需交换 73 > 43
     *    ┌───30──┐
     *    │       │
     * ┌─34─┐   ┌─73
     * │    │   │
     * 60    68 43
     *
     *
     * 从 34 开始，进行交换
     *    ┌───30──┐
     *    │       │
     * ┌─68─┐   ┌─73
     * │    │   │
     * 60    34 43
     *
     * 从 30 开始，进行交换
     *    ┌───73──┐
     *    │       │
     * ┌─68─┐   ┌─30
     * │    │   │
     * 60    34 43
     *
     * 继续下滤
     *    ┌───73──┐
     *    │       │
     * ┌─68─┐   ┌─43
     * │    │   │
     * 60    34 30
     *
     */
    private void heapIfy() {

        // 自上而下的上滤
        for (int i = 1; i < size; i++) {
            siftUp(i);
        }

        // 自下而上的下滤
        /*for (int i = (size >> 1) - 1; i >= 0 ; i--) {
            siftDown(i);
        }*/
    }

    private void ensureCapacity() {
        E[] nElements = (E[])new Object[size + size >> 1];
        System.arraycopy(elements, 0, nElements, 0, elements.length);
        elements = nElements;
    }

    @Override
    public Object root() {
        return 0;
    }

    @Override
    public Object left(Object index) {
        int idx = (int) index;
        idx = (idx << 1) + 1;
        return idx >= size ? null : idx;
    }

    @Override
    public Object right(Object index) {
        int idx = (int) index;
        idx = (idx << 1) + 2;
        return idx >= size ? null : idx;
    }

    @Override
    public Object string(Object index) {
        int idx = (int) index;
        return elements[idx];
    }

    private int compare(E e1, E e2) {
        return comparator != null ? comparator.compare(e1, e2) : ((Comparable<E>)e1).compareTo(e2);
    }
}
