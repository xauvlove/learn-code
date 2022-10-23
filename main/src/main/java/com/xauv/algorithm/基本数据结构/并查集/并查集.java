package com.xauv.algorithm.基本数据结构.并查集;
/*
       /\   /\             /\.__                      
___  __)/___)/  __ _____  _)/|  |   _______  __ ____  
\  \/  /\__  \ |  |  \  \/ / |  |  /  _ \  \/ // __ \ 
 >    <  / __ \|  |  /\   /  |  |_(  <_> )   /\  ___/ 
/__/\_ \(____  /____/  \_/   |____/\____/ \_/  \___  >
      \/     \/                                    \/
*/

/**
 * @Date 2022/10/22 21:08
 * @Author Administrator
 * @Package com.xauv.algorithm.基本数据结构.并查集
 * @Desc
 */
public class 并查集 {

    public static void main(String[] args) {

        UnionFind_QuickUnion unionFind = new UnionFind_QuickUnion(12);
        unionFind.union(0,1);
        unionFind.union(0,3);
        unionFind.union(0,4);
        unionFind.union(2,3);
        unionFind.union(2,5);
        unionFind.union(6,7);
        unionFind.union(8,10);
        unionFind.union(9,10);
        unionFind.union(9,11);

        System.out.println(unionFind.same(2,7));
        unionFind.union(4,6);
        System.out.println(unionFind.same(2,7));

    }
}
