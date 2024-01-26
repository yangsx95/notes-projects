package da;

import org.junit.Assert;
import org.junit.Test;

public class LinkedListTest {

    // 单链表
    static class SingleNode {
        int data;
        SingleNode next;

        SingleNode(int data) {
            this.data = data;
        }
    }

    // 返回一个SingleNode，防止逆序之后头结点不可达，造成jvm垃圾回收
    public SingleNode reverseSingle(SingleNode head) {
        if (head == null) {
            return head;
        }
        SingleNode newHead = null;
        SingleNode next;
        while (head != null) {
            next = head.next;
            head.next = newHead;
            newHead = head;
            head = next;
        }
        return newHead;
    }

    @Test
    public void testReverseSingle() {
        SingleNode head = new SingleNode(1);
        head.next = new SingleNode(2);
        head.next.next = new SingleNode(3);

        // 链表反转
        head = reverseSingle(head);
        Assert.assertEquals(head.data, 3);
        Assert.assertEquals(head.next.data, 2);
        Assert.assertEquals(head.next.next.data, 1);

        // 打印
        while (head != null) {
            System.out.println(head.data);
            head = head.next;
        }
    }

    static class DoubleNode {
        int data;
        DoubleNode next;
        DoubleNode last;

        public DoubleNode(int data) {
            this.data = data;
        }
    }

    public void reverseDouble(DoubleNode head) {
        DoubleNode next;
        while (head != null) {
            next = head.next;
            head.next = head.last;
            head.last = next;
            head = head.last;
        }
    }

    @Test
    public void testReverseDouble() {
        DoubleNode node1 = new DoubleNode(1);
        DoubleNode node2 = new DoubleNode(2);
        DoubleNode node3 = new DoubleNode(3);
        node1.next = node2;
        node2.next = node3;
        node2.last = node1;
        node3.last = node2;

        // 链表反转
        reverseDouble(node1);
        Assert.assertEquals(node3.next, node2);
        Assert.assertEquals(node2.next, node1);
        Assert.assertNull(node1.next);

        Assert.assertNull(node3.last);
        Assert.assertEquals(node2.last, node3);
        Assert.assertEquals(node1.last, node2);
    }

    /**
     * 删除单链表指定的值
     */
    static class SingleNode4Delete {
        int value;
        SingleNode4Delete next;

        public SingleNode4Delete(int value) {
            this.value = value;
        }
    }

    public SingleNode4Delete deleteNode(SingleNode4Delete head, int num) {
        // 先找到头结点，也就是第一个不是3的节点
        while (head != null && head.value == 3) {
            head = head.next;
        }
        
        // 指针向后移动
        //      如果当前为3，那么就将指针前一个节点指向指针后一个节点
        //      如果当前不为3，cur就继续移动
        SingleNode4Delete pre = head;
        SingleNode4Delete cur = head.next;
        while (cur != null) {
            if (cur.value != num) {
                pre = cur;
            } else {
                pre.next = cur.next;
            }
            cur = cur.next;
        }
        return head;
    }

    @Test
    public void testDeleteNode() {
        SingleNode4Delete head = new SingleNode4Delete(7);
        head.next = new SingleNode4Delete(4);
        head.next.next = new SingleNode4Delete(3);
        head.next.next.next = new SingleNode4Delete(3);
        head.next.next.next.next = new SingleNode4Delete(2);
        head.next.next.next.next.next = new SingleNode4Delete(1);
        head.next.next.next.next.next.next = new SingleNode4Delete(3);
        SingleNode4Delete r = deleteNode(head, 3);
        System.out.println(r);
    }

    public static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }

    public ListNode reverseKGroup(ListNode head, int k) {
        if (head == null || k < 2) {
            return head;
        }

        ListNode thisGroupLastNode = null;
        ListNode preGroupLastNode = null;
        ListNode next;
        ListNode newHead = null;
        ListNode groupNewHead = null;
        int count = k;
        while (count > 0) {
            // 新组的第一次循环
            if (count == k) {
                // 如果 head == null ， 或者他下面的 k - 1 个next 为空
                // 那么就退出循环，不做逆序处理。并将剩下的部分与上组结尾相连接
                ListNode h = head;
                for (int i = 0; i < k - 1; i++) {
                    if (h == null) {
                        break;
                    }
                    h = h.next;
                }
                if (h == null) {
                    if (thisGroupLastNode != null) {
                        thisGroupLastNode.next = head;
                    }
                    break;
                }

                // 否则开始新的一组
                // thisGroupLastNode 变为上一组的最后一个节点 preGroupLastNode
                // 当前节点为 thisGroupLastNode，当前最的最后一个节点，备份该节点，防止逆序后找不到
                preGroupLastNode = thisGroupLastNode;
                thisGroupLastNode = head;
            }

            // 第一组的最后一次循环，那么head就是这个链表的开始位置
            if (count == 1 && newHead == null) {
                newHead = head;
            }

            // 这组的最后一次循环，那么该节点就是与上一组的尾节点相连接的节点，对节点连接
            if (count == 1) {
                if (preGroupLastNode != null) {
                    preGroupLastNode.next = head;
                }
            }

            // 交换组内节点
            next = head.next;
            head.next = groupNewHead;
            groupNewHead = head;
            head = next;


            if (count == 1) {
                // 并重置count，再次完成k-1个循环
                count = k;
                continue;
            }

            count--;

        }
        return newHead;
    }

    @Test
    public void testReverseKGroup() {
        ListNode head = new ListNode(1);
        head.next = new ListNode(2);
        head.next.next = new ListNode(3);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(5);


        ListNode node = reverseKGroup(head, 3);
        System.out.println(node);
    }

    static class PlusNode {
        private int value;
        PlusNode next;

        public PlusNode(int value) {
            if (value < 0 || value > 9) {
                throw new IllegalArgumentException();
            }
            this.value = value;
        }

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }
    }

    /**
     * 两个链表相加 加法竖式处理
     * 备注：也可以使用字符串
     */
    public PlusNode plusLinkedList(PlusNode head1, PlusNode head2) {
        // 1. 找到长链表与短链表
        int node1Size = getPlusNodeSize(head1);
        int node2Size = getPlusNodeSize(head2);
        PlusNode l = node1Size > node2Size ? head1 : head2;
        PlusNode s = node1Size > node2Size ? head2 : head1;

        // 2. 第一阶段：循环短链表的次数，将数字依次相加，并记录进位信息
        PlusNode curL = l; // 长链表循环指针
        PlusNode curS = s; // 短链表循环指针
        int carry = 0;// 表示进位信息， 0 表示没有进位
        int curSum; // 当前的长链表指针与短链表指针的所指向的val的和
        PlusNode lastLNode = curL; // 记录最后一个元素，用于添加溢出的最后一个进位
        while (curS != null) {
            curSum = curL.getValue() + curS.getValue();
            carry = curSum / 10;
            curL.setValue(curSum % 10);
            lastLNode = curL;
            curL = curL.next;
            curS = curS.next;
        }
        // 3. 第二阶段：循环长链表多出的那部分长度，并处理进位信息\
        while (curL != null) {
            curSum = carry + curL.getValue();
            carry = curSum / 10;
            curL.setValue(curSum % 10);
            lastLNode = curL;
            curL = curL.next;
        }

        // 4. 第三阶段：处理溢出的最后一个进位
        if (carry > 0) {
            lastLNode.next = new PlusNode(carry);
        }

        return l;
    }

    private int getPlusNodeSize(PlusNode node) {
        int count = 0;
        while (node != null) {
            count++;
            node = node.next;
        }
        return count;
    }

    @Test
    public void testPlusLinkedList() {
        PlusNode head1 = new PlusNode(4);
        head1.next = new PlusNode(3);
        head1.next.next = new PlusNode(6);

        PlusNode head2 = new PlusNode(2);
        head2.next = new PlusNode(5);
        head2.next.next = new PlusNode(3);

        PlusNode node = plusLinkedList(head1, head2);
        Assert.assertNotNull(node);
        Assert.assertEquals(node.getValue(), 6);
        Assert.assertEquals(node.next.getValue(), 8);
        Assert.assertEquals(node.next.next.getValue(), 9);


        head1 = new PlusNode(0);
        head1.next = new PlusNode(0);
        head1.next.next = new PlusNode(5);

        head2 = new PlusNode(0);
        head2.next = new PlusNode(0);
        head2.next.next = new PlusNode(5);

        node = plusLinkedList(head1, head2);
        Assert.assertNotNull(node);
        Assert.assertEquals(node.getValue(), 0);
        Assert.assertEquals(node.next.getValue(), 0);
        Assert.assertEquals(node.next.next.getValue(), 0);
        Assert.assertEquals(node.next.next.next.getValue(), 1);
    }

    static class UnionNode {
        int value;
        UnionNode next;

        public UnionNode(int value) {
            this.value = value;
        }
    }

    public UnionNode unionNode(UnionNode head1, UnionNode head2) {
        if (head1 == null || head2 == null || head1 == head2) {
            return head1 != null ? head1 : head2;
        }
        UnionNode main = head1.value <= head2.value ? head1 : head2; // 主链表指针cur1
        UnionNode cur1 = main.next; // 主链表指针cur1
        UnionNode cur2 = head1.value <= head2.value ? head2 : head1;  // 副链表指针cur2
        UnionNode cur2Next; // 记录副链表指针的下一个元素用于添加元素后继续循环副链表
        UnionNode cur1Pre = main; // 记录主链表的上一个元素，用于拼接新的元素
        while (cur1 != null && cur2 != null) {
            if (cur1.value <= cur2.value) {
                // 如果cur1的新值小于cur2所处的位置，继续移动cur1
                cur1Pre = cur1;
                cur1 = cur1.next;
            } else {
                // 如果cur1的新值大于cur2所处的位置，将cur2所处元素插入到cur1，并移动cur2指针到链表的下个位置
                cur2Next = cur2.next;
                cur1Pre.next = cur2;
                cur2.next = cur1;
                cur1Pre = cur2;
                cur2 = cur2Next;
            }
        }
        // 当cur1移动到尾部时，将cur2剩余节点全部拼接到主链表中，链表的合并已经完成
        if (cur1 == null) {
            cur1Pre.next = cur2;
        }
        return main;
    }

    @Test
    public void testUnionNode() {
        UnionNode head1 = new UnionNode(1);
        head1.next = new UnionNode(3);
        head1.next.next = new UnionNode(3);
        head1.next.next.next = new UnionNode(5);
        head1.next.next.next.next = new UnionNode(7);

        UnionNode head2 = new UnionNode(2);
        head2.next = new UnionNode(2);
        head2.next.next = new UnionNode(3);
        head2.next.next.next = new UnionNode(3);

        UnionNode node = unionNode(head1, head2);
        Assert.assertNotNull(node);
        int[] resultArr = {1, 2, 2, 3, 3, 3, 3, 5, 7};
        int count = 0;
        while (node != null) {
            Assert.assertEquals(node.value, resultArr[count]);
            node = node.next;
            count++;
        }

    }
}
