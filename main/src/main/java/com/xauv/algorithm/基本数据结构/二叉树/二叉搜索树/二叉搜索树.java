package com.xauv.algorithm.基本数据结构.二叉树.二叉搜索树;
/*
       /\   /\             /\.__                      
___  __)/___)/  __ _____  _)/|  |   _______  __ ____  
\  \/  /\__  \ |  |  \  \/ / |  |  /  _ \  \/ // __ \ 
 >    <  / __ \|  |  /\   /  |  |_(  <_> )   /\  ___/ 
/__/\_ \(____  /____/  \_/   |____/\____/ \_/  \___  >
      \/     \/                                    \/
*/

import com.xauv.algorithm.基本数据结构.二叉树.utils.BinaryTrees;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @Date 2021/12/26 17:01
 * @Author Administrator
 * @Package com.xauv.algorithm.基本数据结构.二叉树
 * @Desc
 */
public class 二叉搜索树 {

    public static void main(String[] args) {
        BinarySearchTree<Point> tree = new BinarySearchTree<>();
        Point p1 = new Point(55, "BLACK");
        Point p2 = new Point(38, "RED");
        Point p3 = new Point(80, "BLACK");
        Point p4 = new Point(25, "RED");
        Point p5 = new Point(46, "BLACK");
        Point p6 = new Point(76, "BLACK");
        Point p7 = new Point(88, "BLACK");
        Point p8 = new Point(17, "BLACK");
        Point p9 = new Point(33, "BLACK");
        Point p10 = new Point(50, "RED");
        Point p11 = new Point(72, "RED");

        Point p12 = new Point(10, "RED");
        Point p13 = new Point(20, "RED");
        //Point p14 = new Point(30, "RED");
        //Point p15 = new Point(36, "RED");

        tree.add(p1);
        tree.add(p2);
        tree.add(p3);
        tree.add(p4);
        tree.add(p5);
        tree.add(p6);
        tree.add(p7);
        tree.add(p8);
        tree.add(p9);
        tree.add(p10);
        tree.add(p11);


        tree.add(p12);
        tree.add(p13);
        //tree.add(p14);
        //tree.add(p15);
        BinaryTrees.println(tree);
    }
}

@AllArgsConstructor
@Data
class Point implements Comparable<Point> {

    int value;
    String color;

    @Override
    public int compareTo(Point o) {
        return value - o.value;
    }

    @Override
    public String toString() {
        if (color .equalsIgnoreCase("RED") ) {
            return value+"";
        }
        return "[" + value + "]";
    }
}