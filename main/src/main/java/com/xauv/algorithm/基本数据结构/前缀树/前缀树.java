package com.xauv.algorithm.基本数据结构.前缀树;
/*
       /\   /\             /\.__                      
___  __)/___)/  __ _____  _)/|  |   _______  __ ____  
\  \/  /\__  \ |  |  \  \/ / |  |  /  _ \  \/ // __ \ 
 >    <  / __ \|  |  /\   /  |  |_(  <_> )   /\  ___/ 
/__/\_ \(____  /____/  \_/   |____/\____/ \_/  \___  >
      \/     \/                                    \/
*/

import java.util.Set;

/**
 * @Date 2022/10/19 22:37
 * @Author Administrator
 * @Package com.xauv.algorithm.基本数据结构.前缀树
 * @Desc
 */
public class 前缀树 {

    public static void main(String[] args) {
        Trie<Integer> trie = new TrieImpl<>();
        trie.add("dog", 1);
        trie.add("doggy", 2);
        trie.add("cat", 3);
        trie.add("cats", 4);
        /* System.out.println(trie.get("doggy"));
        System.out.println(trie.get("dog"));
        System.out.println(trie.get("cat"));
        System.out.println(trie.get("cats"));
        System.out.println(trie.startWith("do"));
        System.out.println(trie.startWith("ca"));

        System.out.println(trie.remove("dog"));
        System.out.println(trie.get("dog"));
        System.out.println(trie.get("doggy"));*/

        System.out.println("---");
        Set<Integer> d = trie.prefixValues("d");
        for (Integer integer : d) {
            System.out.println(integer);
        }

    }
}
