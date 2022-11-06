package com.xauv.algorithm.题目.数据结构;
/*
       /\   /\             /\.__                      
___  __)/___)/  __ _____  _)/|  |   _______  __ ____  
\  \/  /\__  \ |  |  \  \/ / |  |  /  _ \  \/ // __ \ 
 >    <  / __ \|  |  /\   /  |  |_(  <_> )   /\  ___/ 
/__/\_ \(____  /____/  \_/   |____/\____/ \_/  \___  >
      \/     \/                                    \/
*/

import java.util.Random;

/**
 * @Date 2021/12/24 20:59
 * @Author Administrator
 * @Package com.xauv.algorithm.题目.数据结构
 * @Desc
 */
public class ListFactory {

    public static Random random = new Random();

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

    public static ListNode initListBySize(int size) {
        ListNode head = new ListNode(random.nextInt(10));
        ListNode node = head;
        for (int i = 1; i <= size; i++) {
            ListNode n1 = new ListNode(random.nextInt(10));
            node.next = n1;
            node = n1;
        }
        return head;
    }

    public static void printList(ListNode head) {
        if (head == null) {
            return;
        }
        ListNode node = head;
        while (node != null) {
            if (node == head) {
                System.out.print(node.getValue() + " ");
            } else {
                System.out.print("-> " + node.getValue() + " ");
            }
            node = node.getNext();
        }
        System.out.println("-> null");
    }
}
