package com.test.QueueTrain;

import java.util.Stack;

/**
 * 每次压入两个数，当前的数和当前的最小数
 */
class MinStack {
    Stack stack;

    /** initialize your data structure here. */
    public MinStack() {
        stack = new Stack<Integer>();
    }
    
    public void push(int x) {
        if(stack.isEmpty())
        {
            stack.push(x);
            stack.push(x);
        }else
        {
            int tmp = (int)stack.peek();
            stack.push(x);
            if(tmp<x)
            {
                stack.push(tmp);
            }
            else{
                stack.push(x);
            }
        }
    }
    
    public void pop() {
        stack.pop();
        stack.pop();
    }
    
    public int top() {
        return (int)stack.get(stack.size()-2);
    }
    
    public int getMin() {
        return (int)stack.peek();
    }
}