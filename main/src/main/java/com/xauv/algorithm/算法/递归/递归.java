package com.xauv.algorithm.算法.递归;
/*
       /\   /\             /\.__                      
___  __)/___)/  __ _____  _)/|  |   _______  __ ____  
\  \/  /\__  \ |  |  \  \/ / |  |  /  _ \  \/ // __ \ 
 >    <  / __ \|  |  /\   /  |  |_(  <_> )   /\  ___/ 
/__/\_ \(____  /____/  \_/   |____/\____/ \_/  \___  >
      \/     \/                                    \/
*/

/**
 * @Date 2022/10/26 20:12
 * @Author Administrator
 * @Package com.xauv.algorithm.算法.递归
 * @Desc
 *
 * 递归套路
 *
 * 1.先明确函数功能
 *
 * 2.明确问题和子问题的关系
 * 寻找 f(n)和f(n-1)的关系
 *
 * 3.明确递归基（边界条件）
 *
 * 递归使用场景
 * 大问题可分解成小问题
 * 由小问题的解获取大问题的解
 *
 * 链表，二叉树都是这种问题
 * 链表中包含小链表
 * 二叉树包含小二叉树
 *
 * 尾递归优化
 *
 */
public class 递归 {

    /**
     * 像这样的 尾递归
     * jvm 会将它优化成 while 循环的方式，不再重复创建栈帧 消耗栈空间
     * @param n
     */
    public static void test(int n) {
        if (n <= 0) {
            return;
        }
        System.out.println(n);
        test(n-1);
    }

    /**
     * jvm 会将上面的优化成下面的 while 形式
     * @param n
     */
    public static void testOptimize(int n) {
        if (n <= 0) {
            return;
        }
        while (n >= 0) {
            System.out.println(n);
            n--;
        }
    }

    public static void main(String[] args) {
        test(33333);
    }
}
