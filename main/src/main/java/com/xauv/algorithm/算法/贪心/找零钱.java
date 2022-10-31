package com.xauv.algorithm.算法.贪心;
/*
       /\   /\             /\.__                      
___  __)/___)/  __ _____  _)/|  |   _______  __ ____  
\  \/  /\__  \ |  |  \  \/ / |  |  /  _ \  \/ // __ \ 
 >    <  / __ \|  |  /\   /  |  |_(  <_> )   /\  ___/ 
/__/\_ \(____  /____/  \_/   |____/\____/ \_/  \___  >
      \/     \/                                    \/
*/

/**
 * @Date 2022/10/28 19:11
 * @Author Administrator
 * @Package com.xauv.algorithm.算法.贪心
 * @Desc
 *
 * 有 25 分，10 分，5 分，1 分，的硬币
 * 现在要找给客户 41 分钱，如何找钱，使硬币数量最少
 *
 * 思路：每次都找面额最大的硬币
 * 1.拿 25 分，剩余 16 分
 * 2.拿 10 分，剩余 6 分
 * 3.拿 5 分，剩余 1 分
 * 4.拿 1 分
 * 结束
 *
 */
public class 找零钱 {

    public static void changes() {
        int[] faces = {25, 10, 5, 1};
        int money = 41;
        int coins = 0;
        for (int i = 0; i < faces.length; i++) {
            if (money >= faces[i]) {
                money = money - faces[i];
                coins++;
                i--;
            }
        }
        System.out.printf("共需要 %d 个硬币", coins);
    }

    /**
     * 如果将面额换一下，这里求出结果为 5 个硬币，分别是 25 5 5 5 1
     * 但其实最优解是 3 个硬币，分别是 20 20 1
     *
     * 贪心不一定能得到最优解，可以得到局部最优
     *
     * 贪心只看局部，不能从全局视角，因此是局部最优
     */
    public static void changes2() {
        int[] faces = {25, 20, 5, 1};
        int money = 41;
        int coins = 0;
        for (int i = 0; i < faces.length; i++) {
            if (money >= faces[i]) {
                money = money - faces[i];
                coins++;
                i--;
            }
        }
        System.out.printf("共需要 %d 个硬币\n", coins);
    }

    public static void main(String[] args) {
        changes();
        changes2();
    }
}
