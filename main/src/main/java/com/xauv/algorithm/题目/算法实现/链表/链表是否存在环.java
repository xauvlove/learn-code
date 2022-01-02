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
 * @Date 2021/12/24 20:58
 * @Author Administrator
 * @Package com.xauv.algorithm.题目.算法实现.链表
 * @Desc
 */
public class 链表是否存在环 {

    public static void main(String[] args) {

        ListNode listNode = ListFactory.initList();
        System.out.println(containsCircle(listNode));
    }

    public static boolean containsCircle(ListNode head) {

        if (head == null) {
            return false;
        }
        ListNode next = head.getNext();
        if (next == null) {
            return false;
        }
        ListNode low = head;
        ListNode quick = head.getNext();

        while (low != null && quick != null) {
            low = low.getNext();
            if (low == quick) {
                return true;
            }
            quick = quick.getNext();
        }
        return false;
    }
}
