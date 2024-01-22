package p23;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * https://leetcode-cn.com/problems/merge-k-sorted-lists/
 * <p>
 * 思路：使用优先级队列，将三个链表的头部添加到优先级队列中
 * 弹出一个，被弹出的将是最小的元素，也就是新链表的头部，将被弹出的链表的头部的下一个元素放入到优先级队列
 * 继续弹出，并将被弹出的链表的头部的下一个元素放入到优先级队列
 * 重复上述过程，直到优先级队列中没有任何元素
 */
public class Solution1 {

    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0) {
            return null;
        }
        PriorityQueue<ListNode> heap = new PriorityQueue<>(Comparator.comparingInt(o -> o.val));
        for (ListNode node : lists) {
            if (node != null) {
                heap.add(node);
            }
        }
        if (heap.isEmpty()) {
            return null;
        }
        ListNode head = heap.poll();
        ListNode pre = head;
        if (pre.next != null) {
            heap.add(pre.next);
        }
        while (!heap.isEmpty()) {
            ListNode cur = heap.poll();
            pre.next = cur;
            if (cur.next != null) {
                heap.add(cur.next);
            }
            pre = cur;
        }
        return head;
    }

}

/*
时间复杂度估算
1. 假设链表条数为M，M条链表的总节点个数为N
2. 每次都要
 */