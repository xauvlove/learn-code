package com.xauv.algorithm.题目.数据结构;
/*
       /\   /\             /\.__                      
___  __)/___)/  __ _____  _)/|  |   _______  __ ____  
\  \/  /\__  \ |  |  \  \/ / |  |  /  _ \  \/ // __ \ 
 >    <  / __ \|  |  /\   /  |  |_(  <_> )   /\  ___/ 
/__/\_ \(____  /____/  \_/   |____/\____/ \_/  \___  >
      \/     \/                                    \/
*/

import java.util.ArrayList;
import java.util.List;

/**
 * @Date 2021/12/24 21:57
 * @Author Administrator
 * @Package com.xauv.algorithm.题目.数据结构
 * @Desc
 */
public class TreeFactory {

    public static TreeNode init(int nodeCount) {

        List<TreeNode> nodes = new ArrayList<>();

        for (int i = 0; i <nodeCount; i++) {
            TreeNode node = new TreeNode(i+1);
            nodes.add(node);
        }

        for (int i = 1; i < nodes.size(); i++) {
            int parent = i / 2 + 1;
            TreeNode parentNode = nodes.get(parent - 1);
            int leftChild = parent * 2;
            int rightChild = parent * 2 + 1;
            if (leftChild - 1 < nodes.size()) {
                parentNode.setLeft(nodes.get(leftChild - 1));
            }
            if (rightChild - 1 < nodes.size()) {
                parentNode.setRight(nodes.get(rightChild - 1));
            }
        }
        return nodes.get(0);
    }

    public static DupTreeNode initWithParent(int nodeCount) {

        List<DupTreeNode> nodes = new ArrayList<>();

        for (int i = 0; i <nodeCount; i++) {
            DupTreeNode node = new DupTreeNode(i+1);
            nodes.add(node);
        }

        for (int i = 1; i < nodes.size(); i++) {
            int parent = i / 2 + 1;
            DupTreeNode parentNode = nodes.get(parent - 1);
            int leftChild = parent * 2;
            int rightChild = parent * 2 + 1;
            if (leftChild - 1 < nodes.size()) {
                parentNode.setLeft(nodes.get(leftChild - 1));
                nodes.get(leftChild - 1).setParent(parentNode);
            }
            if (rightChild - 1 < nodes.size()) {
                parentNode.setRight(nodes.get(rightChild - 1));
                nodes.get(rightChild - 1).setParent(parentNode);
            }
        }
        return nodes.get(0);
    }
}
