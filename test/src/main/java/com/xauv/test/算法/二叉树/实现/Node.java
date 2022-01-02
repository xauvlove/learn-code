package com.xauv.test.算法.二叉树.实现;
/*
       /\   /\             /\.__                      
___  __)/___)/  __ _____  _)/|  |   _______  __ ____  
\  \/  /\__  \ |  |  \  \/ / |  |  /  _ \  \/ // __ \ 
 >    <  / __ \|  |  /\   /  |  |_(  <_> )   /\  ___/ 
/__/\_ \(____  /____/  \_/   |____/\____/ \_/  \___  >
      \/     \/                                    \/
*/

import lombok.Data;

/**
 * @Date 2022/1/2 12:21
 * @Author Administrator
 * @Package com.xauv.test.算法.二叉树
 * @Desc
 */
@Data
public class Node<E> {

    protected Node<E> left;

    protected Node<E> right;

    protected Node<E> parent;

    protected E value;

    public Node() {}

    public Node(E e) {
        this.value = e;
    }

    public boolean isLeaf() {
        return left == null && right == null;
    }

    public boolean degree0() {
        return isLeaf();
    }

    public boolean degree1() {
        return (left == null && right != null) || (left != null && right == null);
    }

    public boolean degree2() {
        return left != null && right != null;
    }
}
