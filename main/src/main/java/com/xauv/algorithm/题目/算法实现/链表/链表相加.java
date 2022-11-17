package com.xauv.algorithm.题目.算法实现.链表;
/*
       /\   /\             /\.__                      
___  __)/___)/  __ _____  _)/|  |   _______  __ ____  
\  \/  /\__  \ |  |  \  \/ / |  |  /  _ \  \/ // __ \ 
 >    <  / __ \|  |  /\   /  |  |_(  <_> )   /\  ___/ 
/__/\_ \(____  /____/  \_/   |____/\____/ \_/  \___  >
      \/     \/                                    \/
*/

import com.xauv.algorithm.题目.数据结构.ListFactory;
import com.xauv.algorithm.题目.数据结构.ListNode;

/**
 * @Date 2022/11/6 15:31
 * @Author Administrator
 * @Package com.xauv.algorithm.题目.算法实现.链表
 * @Desc
 */
public class 链表相加 {

    /**
     *
     * 给定两个链表表示两个非负整数，他们的位是按照逆序存储的，将他们相加
     *
     * 比如
     * 2 -> 4 -> 3
     *
     * 5 -> 6 -> 4
     *
     * 他们分别表示：342 和 465
     *
     * 将他们相加结果为
     *
     * 7 -> 0 -> 8
     *
     * 结果是：342 + 465 = 708
     *
     * @return
     */
    public static ListNode add(ListNode head1, ListNode head2) {

        ListNode node1 = head1;
        ListNode node2 = head2;

        ListNode prev1 = null;
        ListNode prev2 = null;

        boolean exc = false;
        while (node1 != null && node2 != null) {
            node1.val = node1.val + node2.val;
            if (exc) {
                node1.val = node1.val + 1;
            }
            if (node1.val >= 10) {
                node1.val = node1.val - 10;
                exc = true;
            } else {
                exc = false;
            }
            prev1 = node1;
            prev2 = node2;
            node1 = node1.next;
            node2 = node2.next;
        }
        assert prev1 != null;

        if (prev1.next == null && prev2.next == null) {
            if (exc) {
                prev1.next = new ListNode(1);
            }
        }

        if (prev1.next == null && prev2.next != null) {
            ListNode prev = prev2;
            prev1.next = prev2.next;
            while (node2 != null) {
                if (exc) {
                    node2.val = node2.val + 1;
                }
                if (node2.val >= 10) {
                    node2.val = node2.val - 10;
                    exc = true;
                } else {
                    exc = false;
                }
                prev = node2;
                node2 = node2.next;
            }
            if (exc) {
                prev.next = new ListNode(1);
            }
        }

        if (prev2.next == null && prev1.next != null) {
            ListNode prev = prev1;
            while (node1 != null) {
                if (exc) {
                    node1.val = node1.val + 1;
                }
                if (node1.val >= 10) {
                    node1.val = node1.val - 10;
                    exc = true;
                } else {
                    exc = false;
                }
                prev = node1;
                node1 = node1.next;
            }
            if (exc) {
                prev.next = new ListNode(1);
            }
        }

        return head1;
    }

    public static void main(String[] args) {
        ListNode listNode1 = ListFactory.initListBySize(3);
        ListNode listNode2 = ListFactory.initListBySize(5);
        ListFactory.printList(listNode1);
        ListFactory.printList(listNode2);

        ListNode add = add(listNode1, listNode2);
        ListFactory.printList(add);
    }
}
