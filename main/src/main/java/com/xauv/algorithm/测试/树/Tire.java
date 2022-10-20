package com.xauv.algorithm.测试.树;

import com.xauv.algorithm.基本数据结构.映射.HashMap;
import com.xauv.algorithm.基本数据结构.映射.Map;

/**
 * @author: Bing Juan
 * @date: 2022/10/20 14 53
 * @desc:
 */
public class Tire {

    int size = 0;

    public static class Node<E> {

        char c;

        Map<Character, Node<E>> children = new HashMap<>();

        boolean word;


    }
}
