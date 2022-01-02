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
 * @Date 2022/1/2 12:22
 * @Author Administrator
 * @Package com.xauv.test.算法.二叉树
 * @Desc
 */
@Data
public class AVLNode<E> extends Node<E> {

    protected int height = 1;

    public AVLNode(E e) {
        super(e);
    }
}
