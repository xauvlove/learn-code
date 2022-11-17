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
 * @Date 2022/11/6 14:57
 * @Author Administrator
 * @Package com.xauv.algorithm.题目.算法实现.链表
 * @Desc
 */
public class 移除链表长度 {

    /**
     * 删除链表所有给定值的节点
     *
     * 比如：
     * 1 -> 2 -> 3 -> 5 -> 8 -> 5 -> 4 -> 1 -> 5 -> null
     *
     * 删除值为 5 的节点
     *
     * 删除完成后为：
     * 1 -> 2 -> 3 -> 8 -> 4 -> 1 -> null
     *
     *
     */
    public static ListNode delete(ListNode head, int val) {
        if (head == null) {
            return null;
        }
        ListNode prev = null;
        ListNode node = head;
        while (node != null) {
            ListNode next = node.getNext();
            if (node.val == val) {
                // 如果 prev 为空，表示删除头节点，
                if (prev == null) {
                    head = next;
                    node = head;
                } else {
                    prev.next = next;
                    node = next;
                }
            } else {
                prev = node;
                node = next;
            }
        }
        return head;
    }

    public static void main(String[] args) {
        ListNode head = ListFactory.initList();
        ListFactory.printList(head);
        head = delete(head, 0);
        ListFactory.printList(head);
    }
}
