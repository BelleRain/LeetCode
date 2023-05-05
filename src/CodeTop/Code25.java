package CodeTop;

/**
 * @author mxy
 * @create 2023-04-11 9:59
 */

import java.util.List;

/**
 * 25. K 个一组翻转链表    链接：https://leetcode.cn/problems/reverse-nodes-in-k-group
 * 给你链表的头节点 head ，每 k 个节点一组进行翻转，请你返回修改后的链表。
 * k 是一个正整数，它的值小于或等于链表的长度。如果节点总数不是 k 的整数倍，那么请将最后剩余的节点保持原有顺序。
 * 你不能只是单纯的改变节点内部的值，而是需要实际进行节点交换。
 *
 * 示例 1：
 * 输入：head = [1,2,3,4,5], k = 2
 * 输出：[2,1,4,3,5]
 *
 * 示例 2：
 * 输入：head = [1,2,3,4,5], k = 3
 * 输出：[3,2,1,4,5]
 *  
 *
 * 提示：
 * 链表中的节点数目为 n
 * 1 <= k <= n <= 5000
 * 0 <= Node.val <= 1000
 *
 * 进阶：你可以设计一个只用 O(1) 额外内存空间的算法解决此问题吗？
 *
 */
public class Code25 {

    public static void main(String[] args) {
        Code25 code25 = new Code25();
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
        ListNode group1 = reverseKGroup1(node1, 2);
        //ListNode group2 = reverseKGroup2(node1, 2);
        printNode(group1);
        //printNode(group2);
    }


    public static ListNode reverseKGroup1(ListNode head, int k) {
        if (head == null || head.next == null) return head;
        ListNode start = head;
        ListNode end = getKNode(head, k);
        if (end == null) return head;
        ListNode newHead = end;
        reverseList(start, end);
        ListNode lastEnd = start;
        while (lastEnd.next != null){
            start = lastEnd.next;
            end = getKNode(start, k);
            if (end == null) break;
            reverseList(start, end);
            //reverseList 调用结束后，end 和 start 原来的值不变，即局部变量的改变 不更改 原来的变量
            lastEnd.next = end;
            lastEnd = start;
        }
        return newHead;
    }

    //寻找第k个节点
    public static ListNode getKNode(ListNode head,int k){
        ListNode cur = head;
        for (int i = 1; i < k; i++) {
            cur =cur.next;
            if (cur == null) break;
        }
        return cur;
    }

    //翻转部分链表
    public static void reverseList(ListNode l,ListNode r){
        r = r.next;
        ListNode pre = null;
        ListNode cur = l;
        while (cur != r){
            ListNode temp = cur.next;
            cur.next = pre;
            pre = cur;
            cur = temp;
        }
        l.next = r;
    }




    /**
     * 代码繁琐
     * 执行用时： 0 ms , 在所有 Java 提交中击败了 100.00% 的用户
     * 内存消耗： 40.9 MB , 在所有 Java 提交中击败了 74.15% 的用户
     * @param head
     * @param k
     * @return
     */
    public static ListNode reverseKGroup2(ListNode head, int k) {
        if (head == null || head.next == null) return head;
        ListNode pre = head;
        ListNode cur = head;
        for (int i = 1; i < k; i++) {
            cur = cur.next;
            if (cur == null) return head;
        }
        ListNode temp = cur.next;
        cur.next = null;
        pre = reverseList2(pre);
        head = pre;
        if (temp == null) return head;
        cur = pre;
        while (cur.next != null) cur = cur.next;
        ListNode pre_cur = cur;
        cur.next = temp;
        cur = cur.next;
        pre = cur;
        while (cur != null){
            for (int i = 1; i < k; i++) {
                cur = cur.next;
                if (cur == null) break;
            }
            if (cur == null) break;
            ListNode tempt = cur.next;
            cur.next = null;
            pre = reverseList2(pre);
            pre_cur.next = pre;
            if (tempt == null) break;
            cur = pre;
            while (cur.next != null) cur = cur.next;
            pre_cur = cur;
            cur.next = tempt;
            cur = cur.next;
            pre = cur;
        }
        return head;
    }

    //反转链表
    private static ListNode reverseList2(ListNode head){
        ListNode pre = null;
        ListNode cur = head;
        while (cur != null){
            ListNode tmp = cur.next;
            cur.next = pre;
            pre = cur;
            cur = tmp;
        }
        return pre;
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
