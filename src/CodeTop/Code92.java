package CodeTop;

/**
 * @author mxy
 * @create 2023-04-15 11:35
 */

import com.sun.corba.se.pept.transport.ListenerThread;

/**
 * 92. 反转链表 II   链接：https://leetcode.cn/problems/reverse-linked-list-ii
 * 给你单链表的头指针 head 和两个整数 left 和 right ，其中 left <= right 。
 * 请你反转从位置 left 到位置 right 的链表节点，返回 反转后的链表 。
 *  
 * 示例 1：
 * 输入：head = [1,2,3,4,5], left = 2, right = 4
 * 输出：[1,4,3,2,5]
 *
 * 示例 2：
 * 输入：head = [5], left = 1, right = 1
 * 输出：[5]
 *  
 * 提示：
 * 链表中节点数目为 n
 * 1 <= n <= 500
 * -500 <= Node.val <= 500
 * 1 <= left <= right <= n
 *
 * 进阶： 你可以使用一趟扫描完成反转吗？
 *
 */
public class Code92 {

    public static void main(String[] args) {
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
        //ListNode listNode1 = reverseBetween1(node1, 1, 4);
        ListNode listNode = reverseBetween2(node1, 1, 4);
        printNode(listNode);
    }

    public static ListNode reverseBetween1(ListNode head, int left, int right) {
        if (head == null || left == right) return head;
        ListNode start,end,pre;
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode cur = dummy;
        for (int i = 0; i < left - 1; i++) { //寻找第 left - 1 个节点
            cur = cur.next;
        }
        pre = cur;
        start = cur.next;
        end = start;
        for (int i = 0; i < right - left; i++) { //寻找第 right 个节点
            end = end.next;
        }
        ListNode node = reverse(start, end); //反转之后的头节点
        pre.next = node;
        return dummy.next;
    }

    public static ListNode reverse(ListNode start,ListNode end){
        ListNode tmp = end.next;
        ListNode cur = start;
        ListNode pre = null;
        while (cur != tmp){
            ListNode temp = cur.next;
            cur.next = pre;
            pre = cur;
            cur = temp;
        }
        start.next = tmp;
        return end;
    }

    public static ListNode reverseBetween2(ListNode head, int m, int n) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode pre = dummy;
        for(int i = 1; i < m; i++){
            pre = pre.next;
        }
        head = pre.next;
        for(int i = m; i < n; i++){
            ListNode nex = head.next;
            head.next = nex.next;
            nex.next = pre.next;
            pre.next = nex;
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































