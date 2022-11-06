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
 * @Date 2022/11/6 16:00
 * @Author Administrator
 * @Package com.xauv.algorithm.题目.算法实现.链表
 * @Desc
 */
public class 相交链表 {


    /**
     *
     * 给定两个链表，判断是否相交，并返回相交点
     *
     *       a1 -> a2
     *                  c1 -> c2 -> c3
     * b1 -> b2 -> b3
     *
     * 两个链表从 c1 开始相交
     *
     *
     *
     * 方法：
     * 将两个链表相互拼接，搞成一样长的
     *
     * a1 -> a2 -> c1 -> c2 -> c3 -> [b1 -> b2 -> b3 -> c1 -> c2 -> c3]
     *
     * b1 -> b2 -> b3 -> c1 -> c2 -> c3 -> [a1 -> a2 -> c1 -> c2 -> c3]
     *
     * 然后再从头开始遍历，给定两个指针，p1 p2
     * 按照次序逐个比较，直到比较到 c1 c1 的位置，发现内存地址相等，则判断相交
     *
     * 时间复杂度 ： O(n)
     *
     * @param head1
     * @param head2
     * @return
     */
    public static ListNode intersection(ListNode head1, ListNode head2) {

        if (head1 == null || head2 == null) {
            return null;
        }

        ListNode cur1 = head1;
        ListNode cur2 = head2;
        while (cur1 != cur2) {
            cur1 = (cur1 == null) ? head2 : cur1.next;
            cur2 = (cur2 == null) ? head1 : cur2.next;
        }
        return cur1;
    }

    public static void main(String[] args) {
        ListNode head1 = new ListNode(0);
        ListNode n1 = new ListNode(11);
        ListNode n2 = new ListNode(12);
        ListNode n3 = new ListNode(3);
        ListNode n4 = new ListNode(4);


        head1.setNext(n1);
        n1.setNext(n2);
        n2.setNext(n3);
        n3.setNext(n4);

        ListNode head2 = new ListNode(0);
        ListNode nn1 = new ListNode(21);
        ListNode nn2 = new ListNode(22);
        head2.setNext(nn1);
        nn1.setNext(nn2);
        nn2.setNext(n3);

        ListFactory.printList(head1);
        ListFactory.printList(head2);

        ListNode intersection = intersection(head1, head2);
        System.out.println("相交点：" + intersection);
    }
}
