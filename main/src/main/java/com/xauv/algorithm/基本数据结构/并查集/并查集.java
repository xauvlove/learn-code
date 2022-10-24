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
        System.out.println("======================");


        GenericUnionFind<Student> uf = new GenericUnionFind<>();
        Student s1 = new Student(1, "xauv");
        Student s2 = new Student(2, "rose");
        Student s3 = new Student(3, "pick");
        Student s4 = new Student(4, "up");
        uf.makeSet(s1);
        uf.makeSet(s2);
        uf.makeSet(s3);
        uf.makeSet(s4);
        System.out.println(uf.same(s2, s4));
        uf.union(s2, s4);
        System.out.println(uf.same(s2, s4));
        uf.union(s1, s4);
        System.out.println(uf.same(s1, s2));

    }

    private static class Student {

        int age;

        String name;

        public Student(int age, String name) {
            this.age = age;
            this.name = name;
        }
    }
}
