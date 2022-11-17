package com.xauv.algorithm.刷题;

import com.xauv.algorithm.题目.数据结构.ListFactory;
import com.xauv.algorithm.题目.数据结构.ListNode;

/**
 * @author: Bing Juan
 * @date: 2022/11/17 14 57
 * @desc:
 */
public class 合并两个有序链表 {

    /**
     * 输入两个递增的链表，单个链表的长度为n，合并这两个链表并使新链表中的节点仍然是递增排序的
     *
     * 如输入{1,3,5},{2,4,6}时，合并后的链表为{1,2,3,4,5,6}，所以对应的输出为{1,2,3,4,5,6}，
     *
     * @param head1
     * @param head2
     * @return
     */
    public static ListNode merge(ListNode head1, ListNode head2) {
        ListNode head = new ListNode(0);
        ListNode cur = head;
        ListNode node1 = head1;
        ListNode node2 = head2;

        while (node1 != null && node2 != null) {
            if (node1.val <= node2.val) {
                cur.next = node1;
                node1 = node1.next;
            } else {
                cur.next = node2;
                node2 = node2.next;
            }
            cur = cur.next;
        }
        cur.next = node1 != null ? node1 : node2;
        return head.next;
    }

    public static void main(String[] args) {
        ListNode head1 = new ListNode(1);
        ListNode n2 = new ListNode(5);
        ListNode n3 = new ListNode(8);
        ListNode n4 = new ListNode(10);
        ListNode n5 = new ListNode(20);


        head1.setNext(n2);
        n2.setNext(n3);
        n3.setNext(n4);
        n4.setNext(n5);

        ListNode head2 = new ListNode(1);
        ListNode nn1 = new ListNode(2);
        ListNode nn2 = new ListNode(3);
        ListNode nn3 = new ListNode(4);
        ListNode nn4 = new ListNode(5);
        ListNode nn5 = new ListNode(6);
        head2.setNext(nn1);
        nn1.setNext(nn2);
        nn2.setNext(nn3);
        nn3.setNext(nn4);
        nn4.setNext(nn5);

        ListFactory.printList(head1);
        ListFactory.printList(head2);
        ListNode merge = merge(head1, head2);
        ListFactory.printList(merge);
    }
}
