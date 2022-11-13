package com.xauv.algorithm.基本数据结构.栈;
/*
       /\   /\             /\.__                      
___  __)/___)/  __ _____  _)/|  |   _______  __ ____  
\  \/  /\__  \ |  |  \  \/ / |  |  /  _ \  \/ // __ \ 
 >    <  / __ \|  |  /\   /  |  |_(  <_> )   /\  ___/ 
/__/\_ \(____  /____/  \_/   |____/\____/ \_/  \___  >
      \/     \/                                    \/
*/

import java.util.Comparator;
import java.util.Stack;

/**
 * @Date 2022/11/12 21:46
 * @Author Administrator
 * @Package com.xauv.algorithm.基本数据结构.栈
 * @Desc
 *
 * 最小栈
 *
 * 可以在常数级别复杂度完成
 * 增加
 * 弹出
 * 获取栈顶
 * 获取栈中最小值
 *
 *
 * 比如将 3 4 2 5 2 1 入栈
 * 过程是
 *
 * stack normal                     stack min
 * 3                                3
 * 4                                3
 * 2                                2
 * 5                                2
 * 2                                2
 * 1                                1
 *
 *
 * push
 * 左边的普通栈，就当作普通数据栈使用
 * 右边的栈，是当作最小数据栈使用，当 push 元素时，会先判断最小栈栈顶元素和 push 元素的大小，最小栈会将较小元素放入
 *
 * getMin
 * 直接返回 stack min 栈顶即可
 *
 *
 * pop
 * 将普通栈元素弹出一个，再将最小栈元素弹出一个即可
 *
 * 原因是：比如我们 push 了 3 4 2 5 时，
 * normal stack: 3 4 2 5
 * min stack:    3 3 2 2
 * min stack 栈顶是 2，
 * 原由在于，当我们 push 了 3 4 2 5 时，我们的最小元素是 2，
 * normal stack 和 min stack 是顺序上一一对应的关系
 *
 * 我们假如弹出 5 和 2
 * 那么
 * normal stack: 3 4
 * min stack:    3 3
 *
 * 这就相当于，栈存在 3 和 4 时，我们的最小元素是 3
 *
 * 假如再次弹栈
 * normal stack: 3
 * min stack:    3
 *
 * 最小数据栈表明最小元素是 3
 *
 */
@SuppressWarnings("unchecked")
public class MinStack<E> {

    private Comparator<E> comparator;

    // 普通栈，普通存放栈元素
    private Stack<E> normalStack;

    // 最小栈，存放最小数据
    private Stack<E> minStack;

    private int size;

    public MinStack() {
        normalStack = new Stack<>();
        minStack = new Stack<>();
    }

    public MinStack(Comparator<E> comparator) {
        this();
        this.comparator = comparator;
    }

    public void push(E e) {
        normalStack.push(e);
        if (minStack.isEmpty()) {
            minStack.push(e);
        } else {
            E peek = minStack.peek();
            minStack.push((compare(e, peek) < 0) ? e : peek);
        }
        size++;
    }

    public E pop() {
        if (size <= 0) {
            return null;
        }
        E pop = normalStack.pop();
        minStack.pop();
        size--;
        return pop;
    }

    public E getMin() {
        if (size <= 0) {
            return null;
        }
        return minStack.peek();
    }

    public E top() {
        if (size <= 0) {
            return null;
        }
        return normalStack.peek();
    }

    private int compare(E e1, E e2) {
        if (comparator != null) {
            return comparator.compare(e1, e2);
        } else {
            return ((Comparable<E>)e1).compareTo(e2);
        }
    }
}
