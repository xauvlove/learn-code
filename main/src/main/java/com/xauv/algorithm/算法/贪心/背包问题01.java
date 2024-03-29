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
 * @Date 2022/10/28 19:36
 * @Author Administrator
 * @Package com.xauv.algorithm.算法.贪心
 * @Desc
 *
 * n 件物品，和一个最大承重为 W 的背包
 * 每件物品重量 wi，价值 vi
 *
 * 每个物品只有一件，也就是物品只能选 0 件或者 1 件，因此叫做 0-1 背包0
 *
 *
 * 贪心策略的思路：
 * 1.优先选择价值最高的物品放入背包
 *
 * 2.优先选择重量最轻的物品放入背包
 *
 * 3.考虑性价比最高的物品放入背包，价值密度主导，价值密度 = 价值 / 重量
 *
 * 但是这三种，可能都只能得到局部最优解
 *
 * ----------------------------------------------
 *
 * 贪心算法应用：
 * 哈夫曼树
 * 最小生成树
 * 最短路径
 *
 */
public class 背包问题01 {

}
