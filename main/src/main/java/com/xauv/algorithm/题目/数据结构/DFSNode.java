package com.xauv.algorithm.题目.数据结构;

/*
       /\   /\             /\.__                      
___  __)/___)/  __ _____  _)/|  |   _______  __ ____  
\  \/  /\__  \ |  |  \  \/ / |  |  /  _ \  \/ // __ \ 
 >    <  / __ \|  |  /\   /  |  |_(  <_> )   /\  ___/ 
/__/\_ \(____  /____/  \_/   |____/\____/ \_/  \___  >
      \/     \/                                    \/
*/

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @Date 2021/11/07 22:28
 * @Author ling yue
 * @Package com.xauv.algorithm.datastructure
 * @Desc
 */
@Data
public class DFSNode {

    private Integer val;

    private Boolean visited = Boolean.FALSE;

    private List<DFSNode> adjNodes = new ArrayList<>();

    public static void makeRelation(List<DFSNode> nodes, int parent, int child) {
        nodes.get(parent - 1).getAdjNodes().add(nodes.get(child - 1));
    }
}
