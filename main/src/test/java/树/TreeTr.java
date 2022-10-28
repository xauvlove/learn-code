package 树;

import com.xauv.algorithm.题目.数据结构.TreeNode;

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
    }

    public static void after(TreeNode node) {
        Stack<TreeNode> stack = new Stack<>();
        stack.push(node);
        TreeNode prev = null;
        while (!stack.isEmpty()) {
            TreeNode peek = stack.peek();
            TreeNode left = peek.left;
            TreeNode right = peek.right;
            if ((left == null && right == null) || (prev != null && (prev == left || prev == right))) {
                TreeNode pop = stack.pop();
                System.out.println(pop.code);
                prev = pop;
            } else {
                if (right != null) {
                    stack.push(right);
                }
                if (left != null) {
                    stack.push(left);
                }
            }
        }
    }

    public static void mid(TreeNode node) {
        Stack<TreeNode> stack = new Stack<>();
        while (true) {
            if (node != null) {
                stack.push(node);
                node = node.left;
            } else if (stack.isEmpty()) {
                break;
            } else {
                TreeNode pop = stack.pop();
                System.out.println(pop.code);
                node = pop.right;
            }
        }
    }

    public static void before(TreeNode node) {
        Stack<TreeNode> stack = new Stack<>();

        while (true) {
            if (node != null) {
                System.out.println(node.code);
                if (node.right != null) {
                    stack.push(node.right);
                }
                node = node.left;
            } else if (stack.isEmpty()) {
                break;
            } else {
                node = stack.pop();
            }
        }
    }
}
