package com.xauv.algorithm.刷题.滑动窗口;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

/**
 * @author: Bing Juan
 * @date: 2022/11/18 10 58
 * @desc:
 *
 *
 */
    public class 滑动窗口最大值 {

    /**
     *
     *
     * [2, 3, 4, 2, 6, 2, 5, 1]   size = 3
     *  w     i
     *
     * 1.如果 w 无效，则将 i 从队尾 push，push 时，需要从队尾弹出比 nums[i] 小的
     * 2.如果 w 有效，判断 队头是否有效，
     *  对头有效对头是最大的，就是窗口最大值，
     *  对头无效，则继续弹出
     *
     *
     * window = [4,4,6,6,6,5]
     * @param num
     * @param size
     * @return
     */
    public static ArrayList<Integer> maxInWindows(int [] num, int size) {
        if (num == null || num.length == 0) {
            return new ArrayList<>();
        }
        if (size == 0) {
            return new ArrayList<>();
        }
        if (size == 1) {
            ArrayList<Integer> list = new ArrayList<>();
            for (int i : num) {
                list.add(i);
            }
            return list;
        }
        if (size > num.length) {
            return new ArrayList<>();
        }

        int[] res = new int[num.length - (size - 1)];
        Deque<Integer> deque = new LinkedList<>();
        for (int i = 0; i < num.length; i++) {
            int w = i - (size - 1);
            if (w < 0) {
                // w 无效
                while (!deque.isEmpty()) {
                    if (num[i] >= num[deque.getLast()]) {
                        deque.pollLast();
                    } else {
                        break;
                    }
                }
                deque.addLast(i);
            } else {

                while (!deque.isEmpty()) {
                    if (num[i] >= num[deque.getLast()]) {
                        deque.pollLast();
                    } else {
                        break;
                    }
                }
                deque.addLast(i);

                // w 是有效值
                while (!deque.isEmpty()) {
                    Integer peek = deque.peek();
                    if (peek >= w && peek <= i) {
                        res[w] = num[peek];
                        break;
                    } else {
                        deque.poll();
                    }
                }

            }
        }
        ArrayList<Integer> list = new ArrayList<>();
        for (int i : res) {
            list.add(i);
        }
        return list;
    }

    public static void main(String[] args) {
        int[] array = {10,14,12,11};
        System.out.println(maxInWindows(array, 4));
    }
}
