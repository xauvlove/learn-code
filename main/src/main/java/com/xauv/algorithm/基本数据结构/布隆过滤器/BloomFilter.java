package com.xauv.algorithm.基本数据结构.布隆过滤器;
/*
       /\   /\             /\.__                      
___  __)/___)/  __ _____  _)/|  |    _______  __ ____
\  \/  /\__  \ |  |  \  \/ / |  |  /  _ \  \/ // __ \ 
 >    <  / __ \|  |  /\   /  |  |_(  <_> )   /\  ___/ 
/__/\_ \(____  /____/  \_/   |____/\____/ \_/  \___  >
      \/     \/                                    \/
*/

import com.xauv.algorithm.算法.哈希算法.HashAlgorithms;

/**
 * @Date 2022/10/29 11:01
 * @Author Administrator
 * @Package com.xauv.algorithm.基本数据结构.布隆过滤器
 * @Desc
 *
 * 高效率节省空间的算法
 *
 * 返回值 true false
 *
 * false：元素一定不存在
 * true：元素可能存在，可能不存在
 *
 * 有一定误判率，删除操作比较困难
 *
 * 适用场景：
 * 1.经常要判断元素是否存在
 * 2.元素数量巨大，希望节省内存
 * 3.允许有一定误判率
 *
 *
 *
 * 假设布隆过滤器有 20 个二进制，3 个哈希函数组成，每个元素经过哈希函数处理都能生成一个索引位置
 *
 *  0 0 0 1 0 0 1 0 0 0 1 0 0 0 1 0 0 0 0 1
 *
 * 添加元素：
 * 将每一个哈希函数生成的索引位置都设置为 1
 *
 * 原位：0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0
 *
 * hash1：0 1 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0
 *
 * hash2：0 1 0 0 0 0 0 1 0 0 0 0 0 0 0 0 0 0 0 0
 *
 * hash3：0 1 0 0 0 0 0 1 0 0 0 0 0 0 1 0 0 0 0 0
 *
 * 3 个位置都设置为 1
 *
 * 查询元素：
 * 计算 3 个哈希函数，取得索引值，
 * 如果 3 个位置都是 1，则为 true 【说明，如果结果表示 true，那么它可能存在，可能不存在】
 * 如果 3 个位置有 1 个为 0，则为 false 【说明，如果结果表示 false，那么它一定不存在】
 *
 *
 * 降低误判率：
 * 1.提高二进制位长度
 * 2.增加哈希函数
 *
 *
 * 科学家已经计算好一些公式，我们只需要给出需要的 误判率，就可以拿到公式，然后套用即可
 *
 * 复杂度：
 * 添加，查询复杂度：
 * O(k) k = 哈希函数个数
 *
 *
 *
 * p = 误判率
 * n = 数据规模
 * m = 二进制位个数
 * k = 哈希函数个数
 * e = 自然对数
 *
 * p = (1 - e^(-(kn/m))) ^ k
 *
 * m = -[(n * lnp) / (ln2) ^ 2]
 *
 * k = (m / n) * ln2
 *
 */
public class BloomFilter<T> {

    /**
     * 二进制向量长度
     * 共有多少二级制位
     */
    private int bitSize;

    /**
     * 哈希函数个数
     */
    private int hashFucSize;

    /**
     * 二进制向量
     *
     * long 8 个字节，可以有 64 个二进制位
     * 利用二进制数组，可以保存更多二进制位
     */
    private long[] bits;

    /**
     * 数据规模和误判率
     * @param n
     * @param p
     */
    public BloomFilter(int n, double p) {
        if (n <= 0 || p <= 0d || p >= 1d) {
            throw new IllegalArgumentException();
        }
        double ln2 = Math.log(2);
        bitSize = -(int)((n * Math.log(p)) / Math.pow(ln2, 2));
        hashFucSize = (int)(bitSize * ln2 / n);
        bits = new long[(bitSize + Long.SIZE -1) / Long.SIZE];
    }

    /**
     * 添加元素
     * @param value
     */
    public void put(T value) {
        if (value == null) {
            return;
        }
        // 利用 value 生成 2 个整数
        int hash1 = value.hashCode();
        int hash2 = hash1 >>> 16;
        for (int i = 1; i <= hashFucSize; i++) {
            int h = hash1 + (i * hash2);
            h = HashAlgorithms.FNVHash1(String.valueOf(h).getBytes());
            if (h < 0) {
                h = ~h;
            }
            int index = h % bitSize;
            setBit(index);
        }
    }

    /**
     * 判断元素是否存在
     * @param value
     * @return
     */
    public boolean contains(T value) {
        if (value == null) {
            return false;
        }
        // 利用 value 生成 2 个整数
        int hash1 = value.hashCode();
        int hash2 = hash1 >>> 16;
        for (int i = 1; i <= hashFucSize; i++) {
            int h = hash1 + (i * hash2);
            h = HashAlgorithms.FNVHash1(String.valueOf(h).getBytes());
            if (h < 0) {
                h = ~h;
            }
            int index = h % bitSize;
            int bucket = index / Long.SIZE;
            int bucketIndex = index % Long.SIZE;
            // 判断第 index 位是否为 0，如果为 0，则表示元素不存在
            /**
             * 只需要和 i << bucketIndex 进行与运算，看结果是否为 0 即可
             *
             *   1101101
             * | 0001000
             * ----------
             *   0001000
             *
             *   1100101
             * | 0001000
             * ----------
             *   0000000
             *
             */
            if ((bits[bucket] & (1L << bucketIndex)) == 0) {
                return false;
            }
        }
        return true;
    }

    /**
     *
     * 将正数第四位，置为 1
     *
     * 其实就是把之前的数据和 1 << index 进行与操作
     *
     *   10001110100001110               原位
     * | 00010000000000000        与运算  1 << index
     * ---------------------      ------------------
     *   10011110110001110
     * @param index
     */
    private void setBit(int index) {
        int bucket = index / Long.SIZE;
        int bucketIndex = index % Long.SIZE;
        bits[bucket] = bits[bucket] | (1L << bucketIndex);
    }

    public static void main(String[] args) {
        BloomFilter<Integer> bf = new BloomFilter<>(10000000, 0.01);

        for (int i = 1; i <= 10000000; i++) {
            bf.put(i);
        }

        int c = 0;
        for (int i = 10000001; i <= 20000001; i++) {
            if (bf.contains(i)) {
                c++;
            }
        }
        System.out.println(c);
    }
}
