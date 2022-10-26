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
 * @Date 2022/10/26 20:45
 * @Author Administrator
 * @Package com.xauv.algorithm.算法.递归
 * @Desc
 *
 * 把 A 的 n 个盘子，移动到 C，每次只能移动 1 个盘子
 *
 *
 *
 *
 *    ——
 *   ————
 * ————————
 *   A          B         C
 *
 *
 * 大盘子不能放在小盘子上面
 *
 * 思路：
 *
 *
 *
 *
 */
public class 汉诺塔 {


    /**
     *
     * @param n 盘子个数
     * @param a 柱子位置
     * @param b 柱子位置
     * @param c 柱子位置
     *
     * 分两种情况讨论：
     * n = 1 时，直接将盘子从 A 到 C，一步
     * n > 1 时，
     * step1: 需要先将 n-1 个盘子移动到 b，
     * step2: 再将第 n 个盘子移动到 c，
     * step3: 然后再将 n-1 个盘子从 b 移动到 c
     * 就完成了
     *
     */
    public static void hanoi(int n, String a, String b, String c) {
        if (n == 1) {
            move(n, a, c);
            return;
        }
        hanoi(n - 1, a, c, b);
        move(n, a, c);
        hanoi(n - 1, b, a, c);
    }

    public static void move(int no, String from, String to) {
        System.out.println("编号：" + no + "，从 " + from + " 移动到 " + to);
    }

    public static void main(String[] args) {
        hanoi(33, "a", "b", "c");
    }
}
