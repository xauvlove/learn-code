package com.xauv.algorithm.题目.算法实现.深度广度优先;
/*
       /\   /\             /\.__                      
___  __)/___)/  __ _____  _)/|  |   _______  __ ____  
\  \/  /\__  \ |  |  \  \/ / |  |  /  _ \  \/ // __ \ 
 >    <  / __ \|  |  /\   /  |  |_(  <_> )   /\  ___/ 
/__/\_ \(____  /____/  \_/   |____/\____/ \_/  \___  >
      \/     \/                                    \/
*/

import com.xauv.algorithm.题目.数据结构.DFSNode;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @Date 2021/11/27 14:48
 * @Author Administrator
 * @Package com.xauv.algorithm.题目.算法实现.深度广度优先
 * @Desc
 */
public class DFS {

    public static void main(String[] args) {

        DFSNode root = init();
        dfs(root);
    }

    public static void dfs(DFSNode root) {

        Stack<DFSNode> stack = new Stack<>();

        stack.push(root);

        while (!stack.empty() && stack.peek() != null) {
            DFSNode pop = stack.pop();
            System.out.println(pop.getVal());

            List<DFSNode> adjNodes = pop.getAdjNodes();
            for (DFSNode adjNode : adjNodes) {
                stack.push(adjNode);
            }
        }
    }


    public static DFSNode init() {
        List<DFSNode> nodes = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            DFSNode node = new DFSNode();
            node.setVal(i+1);
            nodes.add(node);
        }
        DFSNode n1 = nodes.get(0);
        DFSNode.makeRelation(nodes, 1, 2);
        DFSNode.makeRelation(nodes, 1, 3);
        DFSNode.makeRelation(nodes, 2, 4);
        DFSNode.makeRelation(nodes, 2, 5);
        DFSNode.makeRelation(nodes, 3, 6);
        DFSNode.makeRelation(nodes, 3, 7);
        return n1;
    }
}
