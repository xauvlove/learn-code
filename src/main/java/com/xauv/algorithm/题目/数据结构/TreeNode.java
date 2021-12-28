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

/**
 * @Date 2021/11/12 18:28
 * @Author ling yue
 * @Package com.xauv.algorithm.datastructure
 * @Desc
 */
@Data
public class TreeNode {

    private Integer code;

    private TreeNode left;

    private TreeNode right;

    public TreeNode() {
    }

    public TreeNode(int code) {
        this.code = code;
    }
}
