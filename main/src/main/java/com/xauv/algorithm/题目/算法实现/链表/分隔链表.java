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
 * @Date 2022/11/6 16:50
 * @Author Administrator
 * @Package com.xauv.algorithm.题目.算法实现.链表
 * @Desc
 */
public class 分隔链表 {

    /**
     *
     * 链表
     * 1 -> 4 -> 3 -> 2 -> 5 -> 2
     *
     * 将 大于 3 的节点放在链表左边，大于等于 3 的节点放在链表右边
     * 并且不改变节点的相对位置
     *
     * 结果为：1 -> 2 -> 2 -> 4 -> 3 -> 5
     *
     * 现在 1 2 2 在链表左半部分
     * 4 3 5 在链表右半部分
     * 并且没有改变节点之前的相对顺序
     *
     * 方法：
     * 新建两个链表，遍历一次之前链表，然后用尾部插法将元素分别插入两个链表上
     * 然后再将两个链表合并即可
     *
     * @param head
     * @param val
     * @return
     */
    public static ListNode divide(ListNode head, int val) {

        // 新建两个虚拟头节点
        ListNode head1 = new ListNode(0);
        ListNode head2 = new ListNode(0);

        ListNode cur1 = head1;
        ListNode cur2 = head2;

        ListNode node = head;

        while (node != null) {
            if (node.val < val) {
                cur1.next = node;
                cur1 = cur1.next;
                node = node.next;
                cur1.next = null;
            } else {
                cur2.next = node;
                cur2 = cur2.next;
                node = node.next;
                cur2.next = null;
            }
        }
        cur1.next = head2.next;
        return head1.next;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        ListNode n1 = new ListNode(4);
        ListNode n2 = new ListNode(3);
        ListNode n3 = new ListNode(2);
        ListNode n4 = new ListNode(5);
        ListNode n5 = new ListNode(2);

        head.setNext(n1);
        n1.setNext(n2);
        n2.setNext(n3);
        n3.setNext(n4);
        n4.setNext(n5);
        ListFactory.printList(head);
        divide(head, 3);
        ListFactory.printList(head);
    }
}
