package com.xauv.algorithm.基本数据结构.栈;
/*
       /\   /\             /\.__                      
___  __)/___)/  __ _____  _)/|  |   _______  __ ____  
\  \/  /\__  \ |  |  \  \/ / |  |  /  _ \  \/ // __ \ 
 >    <  / __ \|  |  /\   /  |  |_(  <_> )   /\  ___/ 
/__/\_ \(____  /____/  \_/   |____/\____/ \_/  \___  >
      \/     \/                                    \/
*/

/**
 * @Date 2022/11/12 22:07
 * @Author Administrator
 * @Package com.xauv.algorithm.基本数据结构.栈
 * @Desc
 */
public class 最小栈 {

    public static void main(String[] args) {
        MinStack<Integer> stack = new MinStack<>();
        stack.push(2);
        stack.push(4);
        stack.push(1);
        stack.push(5);
        stack.push(3);
        stack.push(1);
        System.out.println(stack.pop());
        System.out.println(stack.getMin());
        System.out.println("========================");
        QueueMinStack<Integer> queueMinStack = new QueueMinStack<>();
        queueMinStack.push(2);
        queueMinStack.push(4);
        queueMinStack.push(1);
        queueMinStack.push(5);
        queueMinStack.push(3);
        queueMinStack.push(1);
        System.out.println(queueMinStack.pop());
        System.out.println(queueMinStack.getMin());
    }
}
