package Test;

import java.util.Stack;

public class MinStack {
    Stack<Integer> data ;
    Stack<Integer> minStack;

    public MinStack() {
        data = new Stack();
        minStack = new Stack();
    }

    public void push(int val) {

        data.push(val);

        if (minStack.isEmpty() || minStack.peek() >= val) {
            minStack.push(val);
        }
    }

    public void pop() {
        int val = data.pop();

        if (minStack.peek() == val) {
            minStack.pop();
        }

    }

    public int top() {
        return data.peek();
    }

    public int getMin() {
      return   minStack.peek();
    }

    public static void main(String[] args) {
        MinStack minStack = new MinStack();

        minStack.push(-2);
        minStack.push(0);
        minStack.push(-3);
        System.out.println(minStack.getMin());
        minStack.pop();
       int v1 =  minStack.top();
        System.out.println(v1);
        System.out.println(minStack.getMin());


    }
}
