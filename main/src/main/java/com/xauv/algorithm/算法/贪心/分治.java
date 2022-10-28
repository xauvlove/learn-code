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
 * @Date 2022/10/28 19:46
 * @Author Administrator
 * @Package com.xauv.algorithm.算法.贪心
 * @Desc
 *
 * divide and conquer
 *
 * 将问题分解为子问题
 *
 * 子问题结构是一样的，只是规模不一样
 *
 * 子问题又可以分解为子问题，直到不能分解
 *
 * note：
 * 子问题之间是相互独立的
 *
 *
 * 应用：
 * 归并排序：将数组切分到不能再分，进行组内排序
 *
 * 快速排序
 *
 * 大数乘法
 *
 *
 * ----------------- 主定理 -----------------
 * 解决规模为 n 的问题，分解为 a 个规模为 n/b 的子问题
 * 然后在 O(n^d) 时间内，将 n/b 的子问题解合并
 *
 */
public class 分治 {
}
