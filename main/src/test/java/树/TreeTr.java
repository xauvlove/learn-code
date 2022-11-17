package 树;

import com.xauv.algorithm.题目.数据结构.TreeNode;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

import static com.xauv.algorithm.题目.数据结构.TreeFactory.init;

/**
 * @author: Bing Juan
 * @date: 2022/10/24 16 29
 * @desc:
 */
public class TreeTr {

    public static void main(String[] args) {
        TreeNode root = init(7);

        //1245367
        System.out.println("前序：");
        before(root);
        System.out.println();
        //4251637
        System.out.println("中序：");
        mid(root);
        System.out.println();
        //4526731
        System.out.println("后序：");
        after(root);
        System.out.println();

        System.out.println("层高：" + level2(root));

    }



    public static void after(TreeNode node) {
        Stack<TreeNode> stack = new Stack<>();

    }

    public static void mid(TreeNode node) {
        Stack<TreeNode> stack = new Stack<>();

    }

    public static void before(TreeNode node) {
        Stack<TreeNode> stack = new Stack<>();

    }

    public static int level(TreeNode node) {
        if (node == null) {
            return 0;
        }
        TreeNode left = node.left;
        TreeNode right = node.right;
        return Math.max(level(left), level(right)) +1;
    }

    public static int level2(TreeNode node) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(node);
        int level = 1;
        while (true) {
            TreeNode poll = queue.poll();
            if (poll == null) {

                break;
            }
            if (queue.isEmpty()) {
                level++;
            }
            if (poll.left != null) {
                queue.offer(poll.left);
            }
            if (poll.right != null) {
                queue.offer(poll.right);
            }
        }
        return level;
    }
}
