package com.xauv.algorithm.刷题.链表;

import com.xauv.algorithm.题目.数据结构.ListNode;

/**
 * @author: Bing Juan
 * @date: 2022/11/17 15 45
 * @desc:
 */
public class 判断链表是否有环 {

    public static boolean hasCycle(ListNode head) {
        if (head == null) {
            return false;
        }
        ListNode fast = head.next;
        ListNode slow = head;
        while (fast != null) {
            if (fast == slow) {
                return true;
            }
            ListNode fn = fast.next;
            ListNode sn = slow.next;
            if (fn == null || sn == null) {
                return false;
            }
            fast = fn.next;
            slow = sn;

        }
        return false;
    }
}
