package com.xauv.algorithm.刷题.数组;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Stack;

/**
 * @author: Bing Juan
 * @date: 2022/11/18 11 35
 * @desc:
 *
 * 数组： [4,5,1,6,2,7,3,8],   找出最小的 4 个数
 *
 * 结果为：[1,2,3,4]
 *
 * 1.创建双端队列大小为 4
 * 2.如果队列大小不足 4，则从头和尾加入元素【索引】
 * 3.如果队列大小已经满 4，
 *   如果队尾比元素小，则从队头弹出一个，再将元素加入队尾
 *   如果队尾比元素大，则看队头
 *      如果队头比元素小，则忽略
 *      如果队头比元素大，则从队头不断弹出，弹出保存到栈中，如果弹出超过 1 个，则需要从队头回补
 *
 */
public class 最小的k个数 {

    public static ArrayList<Integer> GetLeastNumbers_Solution(int [] input, int k) {
        if (input == null || input.length == 0) {
            return new ArrayList<>();
        }
        if (k == 0) {
            return new ArrayList<>();
        }
        if (k >= input.length) {
            ArrayList<Integer> res = new ArrayList<>();
            for (int i : input) {
                res.add(i);
            }
            return res;
        }
        Deque<Integer> deque = new LinkedList<>();
        Stack<Integer> stack = new Stack<>();
        deque.addLast(0);
        for (int i = 1; i < input.length; i++) {
            if (deque.size() < k) {
                if (input[i] < input[deque.getLast()]) {
                    deque.addLast(i);
                } else {
                    while (input[deque.getFirst()] > input[i]) {
                        stack.push(deque.poll());
                    }
                    deque.addFirst(i);
                    while (deque.size() < k && !stack.isEmpty()) {
                        deque.addFirst(stack.pop());
                    }
                }
            } else {
                if (input[i] < input[deque.getLast()]) {
                    deque.addLast(i);
                    deque.pollFirst();
                } else {
                    while (input[deque.getFirst()] > input[i]) {
                        stack.push(deque.poll());
                    }
                    if (deque.size() < k) {
                        deque.addFirst(i);
                    }
                    while (deque.size() < k) {
                        deque.addFirst(stack.pop());
                    }
                    while (!stack.isEmpty()) {
                        stack.pop();
                    }
                }
            }
        }
        ArrayList<Integer> res = new ArrayList<>();
        while (!deque.isEmpty()) {
            res.add(input[deque.poll()]);
        }
        return res;
    }

    public static void main(String[] args) {
        int[] array = {4,5,1,6,2,7,3,8};
        System.out.println(GetLeastNumbers_Solution(array, 4));
    }
}
