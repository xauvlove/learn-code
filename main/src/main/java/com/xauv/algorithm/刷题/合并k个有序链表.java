package com.xauv.algorithm.刷题;

import com.xauv.algorithm.题目.数据结构.ListFactory;
import com.xauv.algorithm.题目.数据结构.ListNode;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author: Bing Juan
 * @date: 2022/11/17 15 09
 * @desc:
 *
 * 合并 k 个升序的链表并将结果作为一个升序的链表返回其头节点。
 */
public class 合并k个有序链表 {

    public static ListNode mergeKLists(ArrayList<ListNode> lists) {
        if (lists == null || lists.size() <= 0) {
            return null;
        }
        return merge(lists, 0, lists.size()-1);
    }

    /**
     * 两两合并，速度更快
     *
     * 如果依次合并，则链表会越来越长，
     *
     * @param lists
     * @param begin
     * @param end
     * @return
     */
    public static ListNode merge(ArrayList<ListNode> lists, int begin, int end) {
        if (end - begin <= 0) {
            return lists.get(begin);
        }
        int mid = (begin + end) / 2;
        ListNode l = merge(lists, begin, mid);
        ListNode r = merge(lists, mid+1, end);
        return merge(l, r);
    }

    public static ListNode merge(ListNode head1, ListNode head2) {

        if (head1 == null) {
            return head2;
        }

        if (head2 == null) {
            return head1;
        }

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
        ListNode head1 = ListFactory.initListBySizeInc(4);
        ListFactory.printList(head1);

        ListNode head2 = ListFactory.initListBySizeInc(3);
        ListFactory.printList(head2);

        ListNode head3 = ListFactory.initListBySizeInc(7);
        ListFactory.printList(head3);

        ListNode head4 = ListFactory.initListBySizeInc(6);
        ListFactory.printList(head4);

        //ListNode listNode = mergeKLists(new ArrayList<>(Arrays.asList(head1, head2)));

        ListNode listNode = mergeKLists(new ArrayList<>(Arrays.asList(head1, head2, head3, head4)));
        ListFactory.printList(listNode);
    }
}
