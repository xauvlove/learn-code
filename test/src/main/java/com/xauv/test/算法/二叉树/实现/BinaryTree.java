package com.xauv.test.算法.二叉树.实现;
/*
       /\   /\             /\.__                      
___  __)/___)/  __ _____  _)/|  |   _______  __ ____  
\  \/  /\__  \ |  |  \  \/ / |  |  /  _ \  \/ // __ \ 
 >    <  / __ \|  |  /\   /  |  |_(  <_> )   /\  ___/ 
/__/\_ \(____  /____/  \_/   |____/\____/ \_/  \___  >
      \/     \/                                    \/
*/

/**
 * @Date 2022/1/2 12:19
 * @Author Administrator
 * @Package com.xauv.test.算法.二叉树
 * @Desc
 */
public abstract class BinaryTree<E> implements Tree<E> {

    protected Node<E> root;

    protected int size;
}
