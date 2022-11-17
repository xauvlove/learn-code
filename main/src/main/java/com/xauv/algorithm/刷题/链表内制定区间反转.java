package com.xauv.algorithm.刷题;

import com.xauv.algorithm.题目.数据结构.ListFactory;
import com.xauv.algorithm.题目.数据结构.ListNode;


/**
 * @author: Bing Juan
 * @date: 2022/11/17 14 20
 * @desc:
 */
public class 链表内制定区间反转 {

    /**
     *
     * 给出的链表为 1 -> 2  -> 3  -> 4  -> 5  -> NULL
     *
     * m=2,n=4
     *
     * 反转链表位置 2 到 4 的元素
     *
     * 返回 1 -> 4 -> 3 -> 2 -> 5 -> NULL
     *
     * @param head ListNode类
     * @param m int整型
     * @param n int整型
     * @return ListNode类
     */
    public static ListNode reverseBetween(ListNode head, int m, int n) {
        if (m == n) {
            return head;
        }
        ListNode cur = head;
        ListNode prev = null;

        ListNode start = null;
        // 找到第 m 个元素
        for (int i = 1; i < m; i++) {
            prev = cur;
            if (i == m-1) {
                start = cur;
            }
            cur = cur.next;
        }

        // 反转
        ListNode reverseHead = cur;
        prev = null;
        cur = head;
        while (cur != null && n >0) {
            ListNode next = cur.next;
            cur.next = prev;
            prev = cur;
            cur = next;
            n--;
        }

        if (prev != null) {
            start.next = prev;
            reverseHead.next = cur;
        }
        return head;
    }


    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        ListNode n1 = new ListNode(2);
        ListNode n2 = new ListNode(3);
        ListNode n3 = new ListNode(4);
        ListNode n4 = new ListNode(5);
        ListNode n5 = new ListNode(6);


        head.setNext(n1);
        n1.setNext(n2);
        n2.setNext(n3);
        n3.setNext(n4);
        n4.setNext(n5);

        ListFactory.printList(head);
        reverseBetween(head, 1, 2);
        ListFactory.printList(head);
    }
}
