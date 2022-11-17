package com.xauv.algorithm.刷题;

import com.xauv.algorithm.题目.数据结构.ListNode;

/**
 * @author: Bing Juan
 * @date: 2022/11/17 14 15
 * @desc:
 */
public class 反转链表 {

    public ListNode ReverseList(ListNode head) {
        ListNode prev = null;
        ListNode cur = head;
        while (cur != null) {
            ListNode next = cur.next;
            cur.next = prev;
            prev = cur;
            cur = next;
        }
        return prev;
    }
}
