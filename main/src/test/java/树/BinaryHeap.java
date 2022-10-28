package 树;

import com.xauv.algorithm.基本数据结构.二叉树.utils.BinaryTreeInfo;
import com.xauv.algorithm.基本数据结构.二叉树.utils.BinaryTrees;

/**
 * @author: Bing Juan
 * @date: 2022/10/25 14 29
 * @desc:
 */
public class BinaryHeap implements BinaryTreeInfo {

    public static void main(String[] args) {
        BinaryHeap heap = new BinaryHeap();
        heap.add(68);
        heap.add(72);
        heap.add(43);
        heap.add(50);
        heap.add(38);
        heap.add(10);
        heap.add(90);
        heap.add(65);
        BinaryTrees.println(heap);
        heap.remove();
        heap.remove();
        heap.remove();
        BinaryTrees.println(heap);
    }

    int[] elements;

    int size = 0;

    public BinaryHeap() {
        elements = new int[10];
    }

    public void remove() {
        elements[0] = elements[size-1];
        elements[size-1] = 0;
        siftDown(0);
        size--;
    }

    public void add(int value) {
        if (size > elements.length) {
            ensureSize();
        }
        elements[size] = value;
        siftUp(size++);
    }

    /**
     * 左孩子 l = i * 2 + 1
     * 右孩子 r = i * 2 + 2
     * @param index
     */
    private void siftUp(int index) {
        while (index > 0) {
            int parentIndex = (index-1) >> 1;
            if (elements[index] > elements[parentIndex]) {
                int tmp = elements[index];
                elements[index] = elements[parentIndex];
                elements[parentIndex] = tmp;
                index = parentIndex;
            } else {
                break;
            }
        }
    }

    private void siftDown(int index) {
        while (index < size>>1) {
            int left = index * 2 + 1;
            int right = (index+1)*2;
            int maxChild = elements[left];
            int maxIndex = left;
            if (right < size && elements[right] > maxChild) {
                maxChild = elements[right];
                maxIndex = right;
            }
            if (elements[index] < maxChild) {
                int tmp = elements[index];
                elements[index] = maxChild;
                elements[maxIndex] = tmp;
                index = maxIndex;
            } else {break;}

        }
    }

    private void ensureSize() {
        int[] nElements = new int[elements.length << 1];
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
}
