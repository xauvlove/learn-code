package com.xauv.algorithm.题目.数据结构;
/*
       /\   /\             /\.__                      
___  __)/___)/  __ _____  _)/|  |   _______  __ ____  
\  \/  /\__  \ |  |  \  \/ / |  |  /  _ \  \/ // __ \ 
 >    <  / __ \|  |  /\   /  |  |_(  <_> )   /\  ___/ 
/__/\_ \(____  /____/  \_/   |____/\____/ \_/  \___  >
      \/     \/                                    \/
*/

/**
 * @Date 2021/12/24 20:59
 * @Author Administrator
 * @Package com.xauv.algorithm.题目.数据结构
 * @Desc
 */
public class ListUtil {

    public static void print(ListNode head) {
        ListNode cur = head;
        for (;;) {
            if (cur == null) {
                break;
            }
            System.out.print(cur.getVal() + " ");
            cur = cur.getNext();
        }
    }
}
