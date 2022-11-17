package com.xauv.algorithm.刷题;

import com.xauv.algorithm.题目.数据结构.ListNode;

/**
 * @author: Bing Juan
 * @date: 2022/11/17 15 45
 * @desc:
 */
public class 判断链表是否有环 {

    public static boolean hasCycle(ListNode head) {
        ListNode fast = head;
        ListNode slow = head;
        while (true) {
            if (slow == null) {
                return false;
            }

        }
    }
}
