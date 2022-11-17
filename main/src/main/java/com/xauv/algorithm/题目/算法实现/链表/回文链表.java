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

import java.util.Objects;

/**
 * @Date 2022/11/12 20:06
 * @Author Administrator
 * @Package com.xauv.algorithm.题目.算法实现.链表
 * @Desc
 */
public class 回文链表 {

    /**
     *
     * 判断链表是否回文链表
     *
     * 链表从左到右 从右到左，遍历出的元素是一样的
     *
     * 1 -> 2 -> 2 -> 1
     * 是回文链表
     *
     * 1 -> 2 -> 3 -> 2 -> 1
     * 是回文链表
     *
     * 1 -> 2
     * 不是回文链表
     *
     * 思路：
     *
     * 例如链表
     * 1 -> 2 -> 3 -> 2 -> 1
     *
     * step1.
     * 找到中间元素 3
     *
     * step2.
     * 将中间元素 3 之后的链表反转
     * 1 -> 2 -> 3 -> 2 <- 1
     *
     * step3.
     * 使用前后两个指针，分别从两端往中间移动
     * 如果移动过程中发现元素不一致，则表示链表不是回文的
     *
     *
     * notice:
     * 如果是链表：
     * 1 -> 2 -> 2 -> 1
     *
     * 则第二个元素是中间元素，
     * 中间元素：如果链表长度为偶数，那么靠左的元素是中间元素
     *
     * @return
     */
    public static boolean isPalindrome(ListNode head) {
        // 链表为空，链表只有 1 个节点，是回文链表
        if (head == null || head.next == null) {
            return true;
        }
        // 如果链表节点数量为 2，只需判断两者值
        if (head.next.next == null) {
            return Objects.equals(head.val, head.next.val);
        }

        // 1.找到中间节点
        ListNode mid = getMidNode(head);
        // 2.对中间节点后的链表进行反转
        ListNode rightHead = reverse(mid.next);
        // 3.使用双指针逐个比较元素是否相等
        ListNode r = rightHead;
        ListNode l = head;

        // 这里不判断左指针
        while (r != null) {
            // 左右指针元素不相等，则表示不对称
            if (!Objects.equals(l.val, r.val)) {
                return false;
            }
            // 双端指针往中间移动
            r = r.next;
            l = l.next;
        }

        // 将链表再反转回去，不改变原链表
        reverse(rightHead);
        return true;
    }

    /**
     * 思路 2，使用栈
     *
     * step1.
     * 仍然找出中间元素
     *
     * step2.
     * 将中间元素之前的所有元素入栈
     *
     * step3.
     * 中间元素之后的所有元素，和栈顶比较，如果不相等，则表示非回文，如果相等，弹栈继续比较
     *
     * @param head
     * @return
     */
    public static boolean isPalindrome2(ListNode head) {

        return true;
    }

    /**
     * 利用快慢指针
     *
     * 快指针一次走两步
     * 慢指针一次走一步
     *
     * 这样快指针速度是慢指针的两倍
     *
     * @param head
     * @return
     */
    private static ListNode getMidNode(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode fast = head;
        ListNode slow = head;
        while (true) {
            ListNode sn = slow.next;
            ListNode fn = fast.next;
            if (fn == null) {
                break;
            }
            ListNode fnn = fast.next.next;
            if (fnn == null) {
                break;
            }
            fast = fnn;
            slow = sn;
        }
        return slow;
    }

    /**
     * 反转链表
     * @param node
     */
    private static ListNode reverse(ListNode node) {
        if (node == null) {
            return null;
        }
        ListNode cur = node;
        ListNode prev = null;
        ListNode next = null;
        while (cur != null) {
            next = cur.next;
            cur.next = prev;
            prev = cur;
            cur = next;
        }
        return prev;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(0);
        ListNode n1 = new ListNode(1);
        ListNode n2 = new ListNode(2);
        ListNode n4 = new ListNode(3);

        head.setNext(n1);
        n1.setNext(n2);
        n2.setNext(n4);

        ListFactory.printList(head);
        System.out.println(isPalindrome(head));
    }
}
