package CodeTop;

/**
 * @author mxy
 * @create 2023-05-05 11:36
 */

import java.util.ArrayList;
import java.util.List;

/**
 * 234. 回文链表    链接：https://leetcode.cn/problems/palindrome-linked-list
 * 给你一个单链表的头节点 head ，请你判断该链表是否为回文链表。如果是，返回 true ；否则，返回 false 。
 *
 * 示例 1：
 * 输入：head = [1,2,2,1]
 * 输出：true
 *
 * 示例 2：
 * 输入：head = [1,2]
 * 输出：false
 *  
 * 提示：
 * 链表中节点数目在范围[1, 10^5] 内
 * 0 <= Node.val <= 9
 *
 * 进阶：你能否用 O(n) 时间复杂度和 O(1) 空间复杂度解决此题？
 *
 */
public class Code234 {


    /**
     * 利用 List 数组
     * @param head
     * @return
     */
    public static boolean isPalindrome1(ListNode head) {
        if (head == null || head.next == null) return true;
        List<ListNode> list = new ArrayList<>();
        ListNode cur = head;
        while (cur != null){
            list.add(cur);
            cur = cur.next;
        }
        int p = 0, q = list.size() - 1;
        while (p < q){
            if (list.get(p).val != list.get(q).val) return false;
            p++;
            q--;
        }
        return true;
    }


    /**
     * 翻转前半部分链表，与后半部分对比
     * @param head
     * @return
     */
    public static boolean isPalindrome(ListNode head) {
        if (head == null || head.next == null) return true;
        ListNode slow = head, fast = head, pre = null;
        while (fast != null && fast.next != null){
            fast = fast.next.next;
            ListNode tmp = slow.next;
            slow.next = pre;
            pre = slow;
            slow = tmp;
        }
        //如果链表长度为奇数，则最后fast ！= null
        if (fast != null){
            slow = slow.next;
        }
        while (slow != null){
            if (slow.val != pre.val) return false;
            slow = slow.next;
            pre = pre.next;
        }
        return true;
    }


    public static class ListNode{
        int val;
        ListNode next;

        ListNode(){}

        ListNode(int val){
            this.val = val;
        }

        ListNode(int val,ListNode next){
            this.val = val;
            this.next = next;
        }
    }
}































