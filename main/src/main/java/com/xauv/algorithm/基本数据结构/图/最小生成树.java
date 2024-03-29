package com.xauv.algorithm.基本数据结构.图;
/*
       /\   /\             /\.__                      
___  __)/___)/  __ _____  _)/|  |   _______  __ ____  
\  \/  /\__  \ |  |  \  \/ / |  |  /  _ \  \/ // __ \ 
 >    <  / __ \|  |  /\   /  |  |_(  <_> )   /\  ___/ 
/__/\_ \(____  /____/  \_/   |____/\____/ \_/  \___  >
      \/     \/                                    \/
*/

/**
 * @Date 2022/10/24 21:28
 * @Author Administrator
 * @Package com.xauv.algorithm.基本数据结构.图
 * @Desc
 *
 * 一般用于无向连通 图
 *
 * 也叫支撑树
 *
 * 连通图的极小连通子图：包含所有的 n 个顶点，恰好只有 n-1 条边
 *
 *
 *       |——————C———————|
 * A—————B——————————————E
 *       |              |
 *       D———————————————
 *
 *
 *
 * 最小生成树：所有生成树中， 权值总和最【小】的那棵 就是最小生成树
 *
 * 如果每条边权值不同，则存在唯一最小生成树，否则可能存在多个
 *
 * 应用：在 n 个城市间，铺设最短光缆，能够通信【只要所有城市能够连通即可，无需两两连接】
 *
 * 实现算法：
 * Prim 算法
 *
 *
 * Kruskal 算法
 * 将边按照权重大小正序排列，不断将小的边加入生成树，直到生成树包含 V - 1 条边为止，即是最小生成树
 * 但，如果加入某条边会生成环，则不加入该边，继续加入下一条
 * 从第 3 条边开始，就可能会形成环
 *
 */
public class 最小生成树 {
}
