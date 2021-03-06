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

    public void updateHeight() {
        int leftHeight = left != null ? ((AVLNode<E>) left).height : 0;
        int rightHeight = right != null ? ((AVLNode<E>) right).height : 0;
        height = Math.max(leftHeight, rightHeight) + 1;
    }

    public int balanceFactor() {
        int leftHeight = left != null ? ((AVLNode<E>) left).height : 0;
        int rightHeight = right != null ? ((AVLNode<E>) right).height : 0;
        return leftHeight - rightHeight;
    }

    public boolean isBalance() {
        return Math.abs(balanceFactor()) <= 1;
    }

    public AVLNode<E> getTallerChild() {
        return balanceFactor() > 0 ? (AVLNode<E>) left : (AVLNode<E>) right;
    }
}
