package CodeTop;

/**
 * @author mxy
 * @create 2023-04-11 13:41
 */

import java.util.List;

/**
 * 24. 两两交换链表中的节点   链接：https://leetcode.cn/problems/swap-nodes-in-pairs
 * 给你一个链表，两两交换其中相邻的节点，并返回交换后链表的头节点。
 * 你必须在不修改节点内部的值的情况下完成本题（即，只能进行节点交换）。
 *
 * 示例 1：
 * 输入：head = [1,2,3,4]
 * 输出：[2,1,4,3]
 *
 * 示例 2：
 * 输入：head = []
 * 输出：[]
 *
 * 示例 3：
 * 输入：head = [1]
 * 输出：[1]
 *  
 * 提示：
 * 链表中节点的数目在范围 [0, 100] 内
 * 0 <= Node.val <= 100
 *
 */
public class Code24 {

    public static void main(String[] args) {
        Code24 code24 = new Code24();
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);
        ListNode node5 = new ListNode(5);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        printNode(node1);
        ListNode group = swapPairs(node1);
        printNode(group);
    }

    /**
     * 解法一： 同 25. K 个一组翻转链表
     * @param head
     * @return
     */
    public static ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode start = head;
        ListNode end = start.next;
        ListNode newHead = end;
        reverseList(start, end);
        ListNode lastEnd = start;
        while (lastEnd.next != null){
            start = lastEnd.next;
            end = start.next;
            if (end == null) break;
            reverseList(start, end);
            lastEnd.next = end;
            lastEnd = start;
        }
        return newHead;
    }

    public static void reverseList(ListNode start,ListNode end){
        end = end.next;
        ListNode pre = null;
        ListNode cur = start;
        while (cur != end){
            ListNode temp = cur.next;
            cur.next = pre;
            pre = cur;
            cur = temp;
        }
        start.next = end;
    }


    /**
     * 解法二：直接交换两个节点
     * @param head
     * @return
     */
    public static ListNode swapPairs1(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode cur = dummy;
        while (cur.next != null && cur.next.next != null){
            ListNode temp = cur.next;
            cur.next = cur.next.next;
            temp.next = cur.next.next;
            cur.next.next = temp;
            cur = cur.next.next;
        }
        return dummy.next;
    }




    public static void printNode(ListNode head){
        if (head == null) return;
        ListNode cur = head;
        while (cur != null){
            System.out.print(cur.val + " ");
            cur = cur.next;
        }
        System.out.println();
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
}
