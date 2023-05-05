package CodeTop;

/**
 * @author mxy
 * @create 2023-03-23 9:40
 */

import Top100.Top19;

import java.util.*;

/**
 * 143. 重排链表   链接：https://leetcode.cn/problems/reorder-list
 * 给定一个单链表 L 的头节点 head ，单链表 L 表示为：
 * L0 → L1 → … → Ln - 1 → Ln
 * 请将其重新排列后变为：
 * L0 → Ln → L1 → Ln - 1 → L2 → Ln - 2 → …
 * 不能只是单纯的改变节点内部的值，而是需要实际的进行节点交换。
 *
 * 示例 1：
 * 输入：head = [1,2,3,4]
 * 输出：[1,4,2,3]
 *
 * 示例 2：
 * 输入：head = [1,2,3,4,5]
 * 输出：[1,5,2,4,3]
 *  
 * 提示：
 * 链表的长度范围为 [1, 5 * 10^4]
 * 1 <= node.val <= 1000
 *
 */
public class Code143 {

    public static void main(String[] args) {
        Code143 code143 = new Code143();
        Scanner scanner = new Scanner(System.in);
        SingleLinkedList list = new SingleLinkedList();
        while (scanner.hasNextInt()){
            int value = scanner.nextInt();
            ListNode node = new ListNode(value);
            list.add(node);
        }
        //code143.reorderList1(list.getHead().next);
        //code143.reorderList2(list.getHead().next);
        code143.reorderList3(list.getHead().next);
        list.print();
    }

    /**
     * 题解链接： https://leetcode.cn/problems/reorder-list/solution/zhong-pai-lian-biao-by-leetcode-solution/
     */

    /**
     * 题解一： 线性表 （顺序存储）
     * @param head
     */
    public void reorderList1(ListNode head) {
        if (head == null || head.next == null || head.next.next == null) return;
        List<ListNode> list = new ArrayList<>();
        ListNode cur = head;
        while (cur != null){
            list.add(cur);
            cur = cur.next;
        }
        int i = 0, j = list.size() - 1;
        while (i < j){
            //1,2,3,4
            list.get(i).next = list.get(j);
            i++;
            if (i == j){
                break;
            }
            list.get(j).next = list.get(i);
            j--;
        }
        list.get(i).next = null;
    }


    /**
     * 题解二：寻找中点 ---> 后半部分链表逆序 ---> 合并两个链表
     * 执行用时： 1 ms , 在所有 Java 提交中击败了 99.81% 的用户
     * 内存消耗： 44.2 MB , 在所有 Java 提交中击败了 35.09% 的用户
     * @param head
     */
    public void reorderList2(ListNode head) {
        if (head == null || head.next == null || head.next.next == null) return;
        ListNode mid = findMidNode(head);
        ListNode l2 = mid.next;
        mid.next = null;
        l2 = reverseList(l2);
        mergeTwoList(head,l2);
    }

    //寻找中间节点
    private ListNode findMidNode(ListNode head) {
        ListNode slow = head;
        ListNode fast = head;
        while (fast.next != null && fast.next.next != null){
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }

    //反转链表（逆序）
    private ListNode reverseList(ListNode head) {
        ListNode cur = head;
        ListNode pre = null;
        while (cur != null){
            ListNode temp = cur.next;
            cur.next = pre;
            pre = cur;
            cur = temp;
        }
        return pre;
    }

    //合并链表
    private void mergeTwoList(ListNode l1, ListNode l2) {
        while (l1 != null && l2 != null){
            ListNode temp1 = l1.next;
            ListNode temp2 = l2.next;
            l1.next = l2;
            l1 = temp1;
            l2.next = l1;
            l2 = temp2;
        }
    }


    /**
     * 题解链接：https://leetcode.cn/problems/reorder-list/solution/xiang-xi-tong-su-de-si-lu-fen-xi-duo-jie-fa-by-34/
     */

    /**
     * 题解三：递归
     * 1 -> 2 -> 3 -> 4 -> 5
     * 1 -> 5 -> 2 -> 4 -> 3
     * @param head
     */
    public void reorderList3(ListNode head) {
        if(head == null || head.next == null || head.next.next == null) return;
        int len = 0;
        ListNode h = head;
        //求出节点数
        while (h != null){
            len++;
            h = h.next;
        }
        reorderListHelper(head,len);
    }

    private ListNode reorderListHelper(ListNode head, int len) {
        if (len == 1){
            ListNode outTail = head.next;
            head.next = null;
            return outTail;
        }
        if (len == 2){
            ListNode outTail = head.next.next;
            head.next.next = null;
            return outTail;
        }
        //得到对应的的尾节点，并且将头结点和尾节点之间的链表通过递归处理
        ListNode tail = reorderListHelper(head.next, len - 2);
        ListNode subHead = head.next; //中间链表的头节点
        head.next = tail;
        ListNode outTail = tail.next; //上一层head对应的tail
        tail.next = subHead;
        return outTail;
    }
}

class SingleLinkedList {
    ListNode head = new ListNode(0,null);

    public ListNode getHead() {
        return head;
    }

    public void add(ListNode node){
        ListNode temp = head;
        while (temp.next != null){
            temp = temp.next;
        }
        temp.next = node;
    }

    public void print(){
        ListNode cur = head.next;
        if (cur == null) return;
        while (cur != null){
            System.out.print(cur.val + " ");
            cur = cur.next;
        }
    }
}

class ListNode{
    int val;
    ListNode next;
    public ListNode(){}
    public ListNode(int val){
        this.val = val;
    }
    public ListNode(int val,ListNode next){
        this.val = val;
        this.next = next;
    }
}