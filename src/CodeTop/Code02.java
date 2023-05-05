package CodeTop;

/**
 * @author mxy
 * @create 2023-04-21 20:07
 */

/**
 * 2. 两数相加     链接：https://leetcode.cn/problems/add-two-numbers
 * 给你两个 非空 的链表，表示两个非负的整数。它们每位数字都是按照 逆序 的方式存储的，并且每个节点只能存储 一位 数字。
 * 请你将两个数相加，并以相同形式返回一个表示和的链表。
 * 你可以假设除了数字 0 之外，这两个数都不会以 0 开头。
 *  
 * 示例 1：
 * 输入：l1 = [2,4,3], l2 = [5,6,4]
 * 输出：[7,0,8]
 * 解释：342 + 465 = 807.
 *
 * 示例 2：
 * 输入：l1 = [0], l2 = [0]
 * 输出：[0]
 *
 * 示例 3：
 * 输入：l1 = [9,9,9,9,9,9,9], l2 = [9,9,9,9]
 * 输出：[8,9,9,9,0,0,0,1]
 *
 * 提示：
 * 每个链表中的节点数在范围 [1, 100] 内
 * 0 <= Node.val <= 9
 * 题目数据保证列表表示的数字不含前导零
 *
 */
public class Code02 {

    public static void main(String[] args) {

    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(-1);
        ListNode cur1 = l1,cur2 = l2, cur = dummy;
        int temp = 0, carry = 0, r = 0;
        while (cur1 != null || cur2 != null){
            int c1 = cur1 == null ? 0 : cur1.val;
            int c2 = cur2 == null ? 0 : cur2.val;
            temp = (c1 + c2) + r;
            r = temp/10;
            carry = temp%10;
            ListNode node = new ListNode(carry);
            cur.next = node;
            cur = cur.next;
            if (cur1 != null) cur1 = cur1.next;
            if (cur2 != null) cur2 = cur2.next;
        }
        if (r != 0) cur.next = new ListNode(r);
        return dummy.next;
    }

    public class ListNode {
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










































