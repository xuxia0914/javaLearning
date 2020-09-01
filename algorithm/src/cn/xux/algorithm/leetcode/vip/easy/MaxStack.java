package cn.xux.algorithm.leetcode.vip.easy;

import java.util.Stack;

/**
 * 716. 最大栈（双栈 / list+map）
 * 设计一个最大栈，支持 push、pop、top、peekMax 和 popMax 操作。
 * push(x) -- 将元素 x 压入栈中。
 * pop() -- 移除栈顶元素并返回这个值。
 * top() -- 返回栈顶元素。
 * peekMax() -- 返回栈中最大元素。
 * popMax() -- 返回栈中最大的元素，并将其删除。
 * 		如果有多个最大元素，只要删除最靠近栈顶的那个。
 *
 * 样例 1:
 * MaxStack stack = new MaxStack();
 * stack.push(5);
 * stack.push(1);
 * stack.push(5);
 * stack.top(); -> 5
 * stack.popMax(); -> 5
 * stack.top(); -> 1
 * stack.peekMax(); -> 5
 * stack.pop(); -> 1
 * stack.top(); -> 5
 *
 * 注释:
 * -1e7 <= x <= 1e7
 * 操作次数不会超过 10000。
 * 当栈为空的时候不会出现后四个操作。
 */
public class MaxStack {

    Stack<Integer> stack = new Stack<>();
    Stack<Integer> maxStack = new Stack<>();

    public void push(int x) {
        stack.push(x);
        if(maxStack.size()==0||x>=maxStack.peek()) {
            maxStack.push(x);
        }
    }

    public int pop() {
        int ans = stack.pop();
        if(maxStack.peek()==ans) {
            maxStack.pop();
        }
        return ans;
    }

    public int top() {
        return stack.peek();
    }

    public int peekMax() {
        return maxStack.peek();
    }

    public int popMax() {
        int ans = maxStack.pop();
        Stack<Integer> tmp = new Stack<>();
        while(stack.peek()<ans) {
            tmp.push(stack.pop());
        }
        stack.pop();
        while(tmp.size()>0) {
            stack.push(tmp.pop());
        }
        return ans;
    }

}
