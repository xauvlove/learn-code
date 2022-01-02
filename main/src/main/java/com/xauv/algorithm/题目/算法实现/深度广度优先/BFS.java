package com.xauv.algorithm.题目.算法实现.深度广度优先;

/*
       /\   /\             /\.__                      
___  __)/___)/  __ _____  _)/|  |   _______  __ ____  
\  \/  /\__  \ |  |  \  \/ / |  |  /  _ \  \/ // __ \ 
 >    <  / __ \|  |  /\   /  |  |_(  <_> )   /\  ___/ 
/__/\_ \(____  /____/  \_/   |____/\____/ \_/  \___  >
      \/     \/                                    \/
*/

import com.xauv.algorithm.题目.数据结构.BFSNode;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * @Date 2021/11/07 22:28
 * @Author ling yue
 * @Package com.xauv.algorithm
 * @Desc
 */
public class BFS {

    public static void main(String[] args) {

        BFSNode first = init();
        bfs(first);
    }

    public static void bfs(BFSNode first) {
        Deque<BFSNode> queue = new LinkedList<>();
        queue.offer(first);
        while (queue.peek() != null) {
            BFSNode poll = queue.poll();
            System.out.print(poll.getVal() + " ");
            for (BFSNode adjNode : poll.getAdjNodes()) {
                queue.offer(adjNode);
            }
        }
    }

    public static BFSNode init() {
        List<BFSNode> nodes = new ArrayList<>();
        for (int i = 0; i < 9; i++) {
            BFSNode node = new BFSNode();
            node.setVal(i+1);
            nodes.add(node);
        }
        BFSNode n1 = nodes.get(0);
        BFSNode.makeRelation(nodes, 1, 2);
        BFSNode.makeRelation(nodes, 1, 3);
        BFSNode.makeRelation(nodes, 2, 4);
        BFSNode.makeRelation(nodes, 2, 5);
        BFSNode.makeRelation(nodes, 3, 6);
        BFSNode.makeRelation(nodes, 3, 7);
        return n1;
    }
}
