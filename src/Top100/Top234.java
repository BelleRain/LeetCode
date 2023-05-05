package Top100;

/**
 * @author mxy
 * @create 2022-12-12 19:53
 */

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.Stack;

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
 * 链表中节点数目在范围 [1, 10^5] 内
 * 0 <= Node.val <= 9
 *  
 * 进阶：你能否用 O(n) 时间复杂度和 O(1) 空间复杂度解决此题？
 *
 */
public class Top234 {


    /**
     * 执行用时： 6 ms , 在所有 Java 提交中击败了 53.82% 的用户
     * 内存消耗： 59.3 MB , 在所有 Java 提交中击败了 12.09% 的用户
     * @param head
     * @return
     */
    public boolean isPalindrome1(ListNode head) {
        if (head == null || head.next == null) return true;
        Deque<ListNode> stack = new ArrayDeque<>();
        ListNode cur = head;
        int length = 0;
        while (cur != null){
            cur = cur.next;
            length++;
        }
        cur = head;
        for (int i = 0; i < length/2; i++) {
            stack.push(cur);
            cur = cur.next;
        }
        if ((length & 1) == 1) cur = cur.next;
        while (!stack.isEmpty()){
            ListNode node = stack.pop();
            if (node.val != cur.val) return false;
            cur = cur.next;
        }
        return true;
    }

    public boolean isPalindrome2(ListNode head) {
        ArrayList<Integer> nums = new ArrayList<Integer>();
        while(head != null){
            nums.add(head.val);
            head = head.next;
        }
        int p = 0;
        int q = nums.size()-1;
        while(q>p){
            if(nums.get(q)!=nums.get(p)){
                return false;
            }else{
                p++;
                q--;
            }
        }
        return true;
    }

    /**
     * 快慢指针：总体思路： 将前半部分链表反转，然后比对 后半部分与 反转后的链表
     *    执行用时： 3 ms , 在所有 Java 提交中击败了 99.58% 的用户
     *    内存消耗： 58 MB , 在所有 Java 提交中击败了 26.97% 的用户
     * 空间复杂度：O(1)
     * @param head
     * @return
     */
    public boolean isPalindrome(ListNode head) {
        if (head == null || head.next == null) return true;
        // 1 -> 2 -> 3 -> 4 -> 4 -> 3 -> 2 -> 1
        ListNode fast = head, slow = head, pre = null, tmp;
        //fast 每次走 2 个节点，fast走的次数即为 链表长度的一半
        //通过slow ，将链表的 前半部分 反转
        while (fast != null && fast.next != null){
            fast = fast.next.next;
            //反转链表
            tmp = slow.next;
            slow.next = pre;
            pre = slow;
            slow = tmp;
        }
        //若链表节点为单数，则 fast ！= null
        //例如：1 -> 2 -> 3 -> 4 -> 5 -> 4 -> 3 -> 2 -> 1
        if (fast != null) slow = slow.next;
        while (slow != null) {
            if (slow.val != pre.val) return false;
            slow = slow.next;
            pre = pre.next;
        }
        return true;
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
