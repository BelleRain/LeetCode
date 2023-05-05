package CodeTop;

/**
 * @author mxy
 * @create 2023-04-20 20:52
 */

/**
 * 83. 删除排序链表中的重复元素    链接：https://leetcode.cn/problems/remove-duplicates-from-sorted-list
 * 给定一个已排序的链表的头 head ， 删除所有重复的元素，使每个元素只出现一次 。返回 已排序的链表 。
 *
 * 示例 1：
 * 输入：head = [1,1,2]
 * 输出：[1,2]
 *
 * 示例 2：
 * 输入：head = [1,1,2,3,3]
 * 输出：[1,2,3]
 *  
 * 提示：
 * 链表中节点数目在范围 [0, 300] 内
 * -100 <= Node.val <= 100
 * 题目数据保证链表已经按升序 排列
 *
 */
public class Code83 {


    public ListNode deleteDuplicates(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode cur = head, pre = cur;
        while (cur != null){
            //找到最后一个相同的节点
            while (cur.next != null && cur.val == cur.next.val){
                cur = cur.next;
            }
            cur = cur.next;
            pre.next = cur;
            pre = cur;
        }
        return head;
    }

    public ListNode deleteDuplicates1(ListNode head) {
        if(null == head) {
            return null;
        }

        ListNode slow = head, fast = head;
        while(fast != null) {
            if(slow.val != fast.val) {
                slow.next = fast;
                slow = slow.next;
            }
            fast = fast.next;
        }

        slow.next = null;
        return head;
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
