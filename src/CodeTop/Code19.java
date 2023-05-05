package CodeTop;

/**
 * @author mxy
 * @create 2023-04-19 10:47
 */

/**
 * 19. 删除链表的倒数第 N 个结点   链接：https://leetcode.cn/problems/remove-nth-node-from-end-of-list
 * 给你一个链表，删除链表的倒数第 n 个结点，并且返回链表的头结点。
 *
 * 示例 1：
 * 输入：head = [1,2,3,4,5], n = 2
 * 输出：[1,2,3,5]
 *
 * 示例 2：
 * 输入：head = [1], n = 1
 * 输出：[]
 *
 * 示例 3：
 * 输入：head = [1,2], n = 1
 * 输出：[1]
 *
 * 提示：
 * 链表中结点的数目为 sz
 * 1 <= sz <= 30
 * 0 <= Node.val <= 100
 * 1 <= n <= sz
 *
 * 进阶：你能尝试使用一趟扫描实现吗？
 *
 */
public class Code19 {


    public ListNode removeNthFromEnd(ListNode head, int n) {
        if (head == null) return head;
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode pre = dummy, slow = dummy.next,fast = dummy.next;
        for (int i = 1; i < n; i++) {
            fast = fast.next;
            if (fast == null) break;
        }
        while (fast != null && fast.next != null){
            slow = slow.next;
            pre = pre.next;
            fast = fast.next;
        }
        pre.next = slow.next;
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
