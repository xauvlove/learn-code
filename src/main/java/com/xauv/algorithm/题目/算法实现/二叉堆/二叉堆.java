package com.xauv.algorithm.题目.算法实现.二叉堆;

/*
       /\   /\             /\.__                      
___  __)/___)/  __ _____  _)/|  |   _______  __ ____  
\  \/  /\__  \ |  |  \  \/ / |  |  /  _ \  \/ // __ \ 
 >    <  / __ \|  |  /\   /  |  |_(  <_> )   /\  ___/ 
/__/\_ \(____  /____/  \_/   |____/\____/ \_/  \___  >
      \/     \/                                    \/
*/

import com.xauv.algorithm.题目.数据结构.ArrayFactory;
import lombok.SneakyThrows;

import java.util.*;

/**
 * @Date 2021/11/13 13:59
 * @Author ling yue
 * @Package com.xauv.algorithm
 * @Desc
 */
public class 二叉堆 {

    public static void main(String[] args) {
        int[] heap = ArrayFactory.initRandomArray(7);
        System.out.println("乱序堆：" + Arrays.toString(heap));
        formMinHeap(heap);
        System.out.println("最小堆：" +Arrays.toString(heap));

        Random random = new Random();
        int r = random.nextInt(10);
        int[] insert = insert(heap, r);
        System.out.println("插入 r = " + r + " 后，最小堆：" +Arrays.toString(insert));

        int deleteIndex = random.nextInt(heap.length);
        int[] delete = delete(heap, deleteIndex);
        System.out.println("删除 r = " + heap[deleteIndex] + " 后，最小堆：" +Arrays.toString(delete));

        Deque<Integer> queue = priorityQueue(heap);
        System.out.println("优先级队列，数值低的优先: " + queue);
    }

    /**
     * 使用数组构建的二叉树
     * 左孩子 = 父亲 * 2
     * 右孩子 = 父亲 * 2 + 1
     * @param length
     * @return
     */
    @SneakyThrows
    public static int[] init(int length) {
        int[] heap = new int[length + 1];
        for (int i = 1; i < heap.length; i++) {
            heap[i] = new Random().nextInt(heap.length * 10);
        }
        return heap;
    }

    public static Deque<Integer> priorityQueue(int[] heap) {
        Deque<Integer> queue = new LinkedList<>();
        int[] delete = heap;
        while (delete.length > 1) {
            queue.offer(delete[1]);
            delete = delete(delete, 1);
        }
        return queue;
    }

    public static int[] insert(int[] oldHeap, int value) {
        int[] heap = new int[oldHeap.length + 1];
        System.arraycopy(oldHeap, 0, heap, 0, oldHeap.length);
        heap[heap.length - 1] = value;
        formMinHeap(heap);
        return heap;
    }

    public static int[] delete(int[] oldHeap, int index) {
        int[] heap = new int[oldHeap.length - 1];
        for (int i = 0; i < index; i++) {
            heap[i] = oldHeap[i];
        }
        for (int i = index+1; i < oldHeap.length; i++) {
            heap[i-1] = oldHeap[i];
        }
        formMinHeap(heap);
        return heap;
    }

    public static void formMinHeap(int[] heap) {
        // 找到第一个非叶子节点
        int adjIndex = (heap.length - 1) / 2;

        for (;adjIndex > 0;adjIndex--) {
            int parentIndex = adjIndex;
            int leftChildIndex = parentIndex * 2;
            int rightChildIndex = parentIndex * 2 + 1;

            // 如果左孩子并且右孩子大于数组长度，则表示为叶子节点，无需做交换
            // 其实只要左孩子大于数组长度 就已经是叶子节点了，因为二叉堆是完全二叉树
            if (leftChildIndex >= heap.length && rightChildIndex >= heap.length) {
                continue;
            }

            int parent = heap[parentIndex];
            int lefChild = heap[leftChildIndex];
            int rightChild = Integer.MAX_VALUE;
            if (rightChildIndex < heap.length) {
                rightChild = heap[rightChildIndex];
            }
            // 如果父节点比孩子大
            if (parent > lefChild || parent > rightChild) {
                // 如果右孩子是最小的，则右孩子和父节点交换
                if (lefChild > rightChild) {
                    heap[rightChildIndex] = parent;
                    heap[parentIndex] = rightChild;
                    adjIndex = rightChildIndex + 1;
                } else {
                    heap[leftChildIndex] = parent;
                    heap[parentIndex] = lefChild;
                    adjIndex = leftChildIndex + 1;
                }
            }
        }
    }
}
