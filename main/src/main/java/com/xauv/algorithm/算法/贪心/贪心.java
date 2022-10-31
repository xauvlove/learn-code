package com.xauv.algorithm.算法.贪心;
/*
       /\   /\             /\.__                      
___  __)/___)/  __ _____  _)/|  |   _______  __ ____  
\  \/  /\__  \ |  |  \  \/ / |  |  /  _ \  \/ // __ \ 
 >    <  / __ \|  |  /\   /  |  |_(  <_> )   /\  ___/ 
/__/\_ \(____  /____/  \_/   |____/\____/ \_/  \___  >
      \/     \/                                    \/
*/

import java.util.Arrays;

/**
 * @Date 2022/10/28 18:58
 * @Author Administrator
 * @Package com.xauv.algorithm.算法.贪心
 * @Desc
 *
 * 每一步都采用当前最优选择【局部最优】，从而希望推导出最优解
 *
 * 贪心不一定能得到最优解，可以得到局部最优
 *
 * 贪心只看局部，不能从全局视角，因此是局部最优
 *
 * 简单高效，不用穷举所有可能，
 *
 *
 */
public class 贪心 {

    /**
     * 加勒比海盗
     *
     * 船总载重量为 W
     * 每件物品重量为 wi
     *
     * 怎么装，才能获取最大价值【假设每个物品价值相等，但重量不等】？
     *
     * 思路：按照重量大小，依次装入
     */
    public static void pirate() {
        int[] weights = {3, 5, 4, 10, 7, 14, 2, 11};
        Arrays.sort(weights);
        int capacity = 30;
        int weight = 0;
        int count = 0;
        for (int i = 0; i < weights.length; i++) {
            weight = weight + weights[i];
            if (weight > capacity) {
                weight = weight - weights[i];
                break;
            } else {
                count++;
            }
        }
        System.out.printf("最多可选 %d 件古董\n", count);
    }


    public static void main(String[] args) {
        pirate();
    }
}
