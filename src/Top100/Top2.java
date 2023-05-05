package Top100;

/**
 * @author mxy
 * @create 2022-11-11 15:26
 */

/**
 * 两数相加
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
 */
public class Top2 {
    public static void main(String[] args) {

    }

    /**
     * 题解链接：https://leetcode.cn/problems/add-two-numbers/solution/liang-shu-xiang-jia-by-leetcode-solution/
     * 方法一：模拟
     * 1、遍历两个链表，默认短链表的空位为0，逐位计算各位之和
     * 2、记录进位 carry，每个位置上的值为 (n1 + n2 + carry)%10,新进位的值为 [n1 + n2 + carry]/10
     * 3、遍历完毕，若 carry > 0, 则最后附加节点，节点值为carry
     * 2 -> 4 -> 3
     * 5 -> 6 -> 4
     * 7 -> 0 -> 8
     * 复杂度分析
     * 时间复杂度：O(max(m,n))，其中 m 和 n 分别为两个链表的长度。我们要遍历两个链表的全部位置，而处理每个位置只需要 O(1) 的时间。
     * 空间复杂度：O(1)。注意返回值不计入空间复杂度。
     * @param l1
     * @param l2
     * @return
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode pre = new ListNode(0);
        ListNode cur = pre;
        int carry = 0; //进位
        while (l1 != null || l2 != null){
             int n1 = l1 != null ? l1.val : 0;
             int n2 = l2 != null ? l2.val : 0;
             int sum = n1 + n2 + carry;
             cur.next = new ListNode(sum % 10);
             carry = sum / 10;
             cur = cur.next;
             if (l1 != null) l1 = l1.next;
             if (l2 != null) l2 = l2.next;
        }
        if (carry > 0) cur.next = new ListNode(carry);
        return pre.next;
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
