package 树;

/**
 * @author: Bing Juan
 * @date: 2022/10/19 14 32
 * @desc:
 */
public class Node<E> {

    E e;

    Node<E> left;

    Node<E> right;

    Node<E> parent;

    public Node(E e) {
        this.e = e;
    }

    public Node(E e, Node<E> parent) {
        this.e = e;
        this.parent = parent;
    }

    public boolean isLeft() {
        if (parent == null) {
            return false;
        }
        return parent.left == this;
    }

    public boolean isRight() {
        if (parent == null) {
            return false;
        }
        return parent.right == this;
    }
}
