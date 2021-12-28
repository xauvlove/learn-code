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
 * @Date 2021/12/24 21:38
 * @Author Administrator
 * @Package com.xauv.algorithm.题目.算法实现.链表
 * @Desc
 */
public class 倒数第N个节点 {

    public static void main(String[] args) {

        ListNode listNode = ListFactory.initList();
        ListNode reverseNode = getReverseNode(2, listNode);
        System.out.println(reverseNode);
    }

    public static ListNode getReverseNode(int n, ListNode head) {

        if (n <= 0) {
            return null;
        }

        ListNode slow = head;
        if (slow == null) {
            return null;
        }
        // 快指针初始位置 = 头指针向前移动 n 个点
        // 如果快指针在移动过程中为空，那么不存在倒数第 n 个节点
        ListNode quick = head;
        while (quick != null) {
            if (n <= 1) {
                break;
            }
            quick = quick.getNext();
            n = n - 1;
        }
        if (quick == null) {
            return null;
        }

        // 快慢指针同时移动
        // 当快指针到达链表末端，则慢指针的为止即倒数第 n 个节点

        for (;;) {
            quick = quick.getNext();
            if (quick == null) {
                return slow;
            }
            slow = slow.getNext();
        }
    }
}
