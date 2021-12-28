package com.xauv.algorithm.题目.算法实现.链表;
/*
       /\   /\             /\.__                      
___  __)/___)/  __ _____  _)/|  |   _______  __ ____  
\  \/  /\__  \ |  |  \  \/ / |  |  /  _ \  \/ // __ \ 
 >    <  / __ \|  |  /\   /  |  |_(  <_> )   /\  ___/ 
/__/\_ \(____  /____/  \_/   |____/\____/ \_/  \___  >
      \/     \/                                    \/
*/

import com.xauv.algorithm.题目.数据结构.ListNode;

/**
 * @Date 2021/12/24 20:43
 * @Author Administrator
 * @Package com.xauv.algorithm.题目.算法实现.链表
 * @Desc
 */
public class 链表反转 {

    public static void main(String[] args) {

        ListNode listNode = initList();
        print(listNode);
        ListNode reverse = reverse(listNode);
        print(reverse);
    }

    public static void print(ListNode head) {
        ListNode cur = head;
        for (;;) {
            if (cur == null) {
                break;
            }
            System.out.print(cur.getValue() + " ");
            cur = cur.getNext();
        }
        System.out.println();
    }

    /**
     * 原地反转
     * @param node
     * @return
     */
    public static ListNode reverseLocal(ListNode node) {

        return null;
    }

    /**
     * 重建反转
     * @param head
     * @return
     */
    public static ListNode reverse(ListNode head) {

        ListNode cur = head;
        ListNode prev = null;

        while (cur != null) {
            ListNode curNext = cur.getNext();
            cur.setNext(prev);
            prev = cur;
            cur = curNext;
        }
        return prev;
    }

    public static ListNode initList() {
        ListNode head = new ListNode(0);
        ListNode n1 = new ListNode(1);
        ListNode n2 = new ListNode(2);
        ListNode n3 = new ListNode(3);
        ListNode n4 = new ListNode(4);
        ListNode n5 = new ListNode(5);
        ListNode n6 = new ListNode(6);
        ListNode n7 = new ListNode(7);
        ListNode n8 = new ListNode(8);

        head.setNext(n1);
        n1.setNext(n2);
        n2.setNext(n3);
        n3.setNext(n4);
        n4.setNext(n5);
        n5.setNext(n6);
        n6.setNext(n7);
        n7.setNext(n8);
        return head;
    }
}
