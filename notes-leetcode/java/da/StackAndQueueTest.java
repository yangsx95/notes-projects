package da;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Stack;

public class StackAndQueueTest {

    /**
     * 单链表实现队列
     *
     * @param <T> 内部的数据类型
     */
    static class MyQueue<T> {

        static class Node<T> {
            T value;
            Node<T> next;

            public Node(T data) {
                this.value = data;
            }
        }

        private Node<T> head;

        private Node<T> tail;

        private int size;

        public int size() {
            return this.size;
        }

        public void put(T value) {
            Node<T> node = new Node<>(value);
            if (head == null) {
                head = node;
            } else {
                tail.next = node;
            }
            tail = node;
            this.size++;
        }

        public T peek() {
            return this.head != null ? this.head.value : null;
        }

        public T poll() {
            T result = null;
            if (this.head != null) {
                result = this.head.value;
                this.head = this.head.next;
                this.size--;
            }

            // 移除最后一个元素时，需要将尾部置空，否则可能存在垃圾对象的问题
            if (this.head == null) {
                tail = null;
            }
            return result;
        }
    }

    @Test
    public void testMyQueue() {
        MyQueue<String> queue = new MyQueue<>();
        queue.put("a");
        queue.put("b");
        queue.put("c");
        Assert.assertEquals(queue.size, 3);
        Assert.assertEquals(queue.peek(), "a");
        Assert.assertEquals(queue.poll(), "a");
        Assert.assertEquals(queue.poll(), "b");
        Assert.assertEquals(queue.poll(), "c");
        Assert.assertEquals(queue.size, 0);
        Assert.assertNull(queue.head);
        Assert.assertNull(queue.tail);
    }

    /**
     * 单链表实现栈
     *
     * @param <T> 值类型参数
     */
    public static class MyStack<T> {
        static class Node<V> {
            V value;

            Node<V> next;

            public Node(V value) {
                this.value = value;
            }
        }

        private Node<T> head;

        private int size;

        public int size() {
            return this.size;
        }

        public void push(T value) {
            Node<T> node = new Node<>(value);
            if (head != null) {
                node.next = head;
            }
            head = node;
            this.size++;
        }

        public T pop() {
            if (size == 0) {
                return null;
            }
            T value = head.value;
            this.head = head.next;
            this.size--;
            return value;
        }
    }

    @Test
    public void testMyStack() {
        MyStack<String> stack = new MyStack<>();
        Assert.assertEquals(stack.size, 0);
        stack.push("a");
        stack.push("b");
        stack.push("c");
        Assert.assertEquals(stack.size, 3);
        Assert.assertEquals(stack.pop(), "c");
        Assert.assertEquals(stack.pop(), "b");
        Assert.assertEquals(stack.pop(), "a");
        Assert.assertEquals(stack.size, 0);
    }

    /**
     * 双链表实现双端队列
     *
     * @param <T> 节点内容类型参数
     */
    public static class MyDeque<T> {

        static class Node<V> {
            V value;
            Node<V> next;
            Node<V> last;

            public Node(V value) {
                this.value = value;
            }
        }

        private Node<T> left;

        private Node<T> right;

        private int size;

        public int size() {
            return this.size;
        }

        public void lpush(T value) {
            Node<T> node = new Node<>(value);
            if (this.left == null) {
                this.right = node;
            } else {
                this.left.last = node;
                node.next = this.left;
            }
            this.left = node;
            this.size++;
        }

        public void rpush(T value) {
            Node<T> node = new Node<>(value);
            if (this.right == null) {
                this.left = node;
            } else {
                this.right.next = node;
                node.last = this.right;
            }
            this.right = node;
            this.size++;
        }

        public T lpoll() {
            if (this.size == 0) {
                return null;
            }

            T result = this.left.value;
            if (this.size == 1) {
                this.left = null;
                this.right = null;
            } else {
                this.left = this.left.next;
                this.left.last = null;
            }
            this.size--;
            return result;
        }

        public T rpoll() {
            if (this.size == 0) {
                return null;
            }

            T result = this.right.value;
            if (this.size == 1) {
                this.left = null;
                this.right = null;
            } else {
                this.right = this.right.last;
                this.right.next = null;
            }
            this.size--;
            return result;
        }
    }


    @Test
    public void testMyDeque() {
        MyDeque<String> deque = new MyDeque<>();
        Assert.assertEquals(deque.size(), 0);
        deque.lpush("a");
        deque.lpush("b");
        Assert.assertEquals(deque.size(), 2);
        deque.rpush("c");
        deque.rpush("d");
        Assert.assertEquals(deque.size(), 4);
        Assert.assertEquals(deque.lpoll(), "b");
        Assert.assertEquals(deque.lpoll(), "a");
        Assert.assertEquals(deque.rpoll(), "d");
        Assert.assertEquals(deque.rpoll(), "c");
        Assert.assertEquals(deque.size(), 0);
    }

    /**
     * 使用数组实现栈结构
     */
    static class MyArrayStack {
        private final int[] array;

        private int cur; // 当前位置的索引，代表下一个可填充的位置

        public MyArrayStack(int size) {
            if (size < 1) {
                throw new IllegalArgumentException();
            }
            array = new int[size];
            cur = 0;
        }

        public void push(int value) {
            if (cur == array.length) {
                throw new IllegalArgumentException("栈已经满了");
            }
            array[cur] = value;
            cur++;
        }

        public int pop() {
            if (cur == 0) { // 没有元素
                throw new IllegalArgumentException("栈已经空了不能再取了");
            }
            return array[cur - 1];
        }
    }

    /**
     * 使用数组实现队列
     * 使用环形buffer，两个指针表示开始与结尾的位置。
     * 将队列长度与数组长度比较，避免处理两个指针之间复杂的前后关系
     */
    static class MyArrayQueue {
        private final int[] array;
        private int pushIndex;
        private int popIndex;
        private final int limit;
        private int size;

        public MyArrayQueue(int limit) {
            if (limit <= 0) {
                throw new IllegalArgumentException();
            }
            this.pushIndex = 0;
            this.popIndex = 0;
            this.limit = limit;
            this.size = 0;
            this.array = new int[limit];
        }

        public void push(int value) {
            if (this.size == this.limit) {
                throw new RuntimeException("队列已经满了");
            }
            array[this.pushIndex] = value;
            size++;
            pushIndex = nextIndex(pushIndex);
            pushIndex++;
        }

        public int pop() {
            if (this.size == 0) {
                throw new RuntimeException("队列已经空了，没有元素可以弹出");
            }
            size--;
            int res = array[popIndex];
            popIndex = nextIndex(popIndex);
            return res;
        }

        // 计算下一次的索引值（环状）
        private int nextIndex(int index) {
            return index == this.limit - 1 ? 0 : index + 1;
        }

        public boolean isEmpty() {
            return this.size == 0;
        }
    }


    static class MyMinStack {
        private final Stack<Integer> dataStack;
        private final Stack<Integer> minStack;

        public MyMinStack() {
            this.dataStack = new Stack<>();
            this.minStack = new Stack<>();
        }

        public void push(int data) {
            if (dataStack.size() == 0) {
                this.minStack.push(data);
            } else {
                int preMin = this.minStack.peek();
                this.minStack.push(Math.min(preMin, data));
            }
            this.dataStack.push(data);
        }

        public int pop() {
            this.minStack.pop();
            return this.dataStack.pop();
        }

        public int getMin() {
            return this.minStack.peek();
        }
    }

    @Test
    public void testStackMin() {
        MyMinStack stack = new MyMinStack();
        stack.push(5);
        Assert.assertEquals(stack.getMin(), 5);

        stack.push(7);
        Assert.assertEquals(stack.getMin(), 5);

        stack.push(6);
        Assert.assertEquals(stack.getMin(), 5);

        stack.push(4);
        Assert.assertEquals(stack.getMin(), 4);
    }

    /**
     * 使用队列实现栈
     */
    static class QueueStack {
        private Queue<Integer> mainQueue;
        private Queue<Integer> helperQueue;

        public QueueStack() {
            mainQueue = new ArrayDeque<>();
            helperQueue = new ArrayDeque<>();
        }

        public void push(Integer value) {
            mainQueue.add(value);
        }

        public Integer pop() {
            while (mainQueue.size() != 1) {
                helperQueue.add(mainQueue.poll());
            }
            Queue<Integer> mainTemp = mainQueue;
            this.mainQueue = helperQueue;
            this.helperQueue = mainTemp;
            return helperQueue.poll();
        }
    }

    @Test
    public void testQueueStack() {
        QueueStack qs = new QueueStack();
        qs.push(1);
        qs.push(2);
        qs.push(3);
        qs.push(4);
        qs.push(5);
        System.out.println(qs.pop());
        System.out.println(qs.pop());
        System.out.println(qs.pop());
        System.out.println(qs.pop());
        System.out.println(qs.pop());
    } 


    /**
     * 使用栈实现队列
     */
    static class StackQueue {
        private final Stack<Integer> push;
        private final Stack<Integer> pop;
        public StackQueue() {
            this.push = new Stack<>();
            this.pop = new Stack<>();
        }

        public void push(Integer value) {
            push.push(value);
        }

        public Integer pop() {
            if (pop.size() == 0 && push.size() == 0) {
                throw new RuntimeException("没有元素可以弹出 ");
            }
            if (pop.size() == 0) {
                while (!push.isEmpty()) {
                    pop.push(push.pop());
                }
            }
            return pop.pop();
        }
    }

    @Test
    public void testStackQueue() {
        StackQueue sq = new StackQueue();
        sq.push(1);
        sq.push(2);
        sq.push(3);
        sq.push(4);
        System.out.println(sq.pop());
        sq.push(5);
        System.out.println(sq.pop());
        System.out.println(sq.pop());
        System.out.println(sq.pop());
        System.out.println(sq.pop());
    }
}
