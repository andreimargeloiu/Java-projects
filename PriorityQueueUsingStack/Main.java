package com.company;

import java.util.Stack;

public class Main {

    static abstract class PriorityQueueUsingStack {
        protected Stack<Integer> stack = new Stack<>();

        public abstract void add(int x);

        public int first() {
            if (stack.size() == 0) {
                return Integer.MIN_VALUE;
            }
            else return stack.peek();
        }

        public void remove() {
            if (stack.size() == 0) {
                System.out.println("Stack is empty, you cannot remove elements.");
            }
            else stack.pop();
        }
    }

    static class PriorityQueueUsingTwoStacks extends PriorityQueueUsingStack {
        private Stack<Integer> auxStack = new Stack<>();

        @Override
        public void add(int x) {
            /*
                Move elements from the top of stack into auxStack, until we can add x into stack.
                Then move back the elements from auxStack into stack.
             */

            while (stack.size() > 0 && stack.peek() > x) {
                auxStack.push(stack.pop());
            }

            stack.push(x);

            while (auxStack.size() > 0) {
                stack.push(auxStack.pop());
            }
        }
    }

    static class PriorityQueueUsingOneStack extends PriorityQueueUsingStack {
        @Override
        public void add(int x) {
            if (stack.size() == 0) {
                stack.push(x);
            }

            if (x >= stack.peek()) {
                stack.push(x);
            } else {
                int top = stack.pop();

                add(x);

                stack.push(top);
            }
        }
    }

    public static void main(String[] args) {
        PriorityQueueUsingStack heap = null;

        // Choose the Priority Queue you want: that uses one stack or two stacks
        heap = new PriorityQueueUsingOneStack();
        //heap = new PriorityQueueUsingTwoStacks();

        heap.add(1);
        heap.add(2);
        heap.add(3);
        System.out.println(heap.first()); // 3
        heap.remove();
        System.out.println(heap.first()); // 2
        heap.add(10);
        System.out.println(heap.first()); // 10
    }
}
