package CodeTop;

/**
 * @author mxy
 * @create 2023-04-20 20:49
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 82. 删除排序链表中的重复元素 II    链接：https://leetcode.cn/problems/remove-duplicates-from-sorted-list-ii
 * 给定一个已排序的链表的头 head ， 删除原始链表中所有重复数字的节点，只留下不同的数字 。
 * 返回 已排序的链表 。
 *
 * 示例 1：
 * 输入：head = [1,2,3,3,4,4,5]
 * 输出：[1,2,5]
 *
 * 示例 2：
 * 输入：head = [1,1,1,2,3]
 * 输出：[2,3]
 *  
 * 提示：
 * 链表中节点数目在范围 [0, 300] 内
 * -100 <= Node.val <= 100
 * 题目数据保证链表已经按升序 排列
 *
 */
public class Code82 {

    /**
     * 题解链接：https://leetcode.cn/problems/remove-duplicates-from-sorted-list-ii/solution/fu-xue-ming-zhu-di-gui-die-dai-yi-pian-t-wy0h/
     */

    /**
     * 一次遍历：
     * @param head
     * @return
     */
    public ListNode deleteDuplicates1(ListNode head) {
        if (head == null || head.next == null) return head;
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode pre = dummy, cur = head;
        while (cur != null){
            //跳过当前的重复节点，使得cur指向当前重复元素的最后一个位置
            while (cur.next != null && cur.val == cur.next.val){
                cur = cur.next;
            }
            //如果pre 和 cur 之间没有重复节点，pre 后移
            if (pre.next == cur){
                pre = pre.next;
            }else {
                pre.next = cur.next;
            }
            cur = cur.next;
        }
        return dummy.next;
    }


    /**
     * 递归：
     * 1.1 递归函数定义 递归最基本的是要明白递归函数的定义！ 我反复强调过这一点。
     *     递归函数直接使用题目给出的函数 deleteDuplicates(head) ，
     *     它的含义是 删除以 head 作为开头的有序链表中，值出现重复的节点。
     * 1.2 递归终止条件 终止条件就是能想到的基本的、不用继续递归处理的case。
     *      如果 head 为空，那么肯定没有值出现重复的节点，直接返回 head；
     *      如果 head.next 为空，那么说明链表中只有一个节点，也没有值出现重复的节点，也直接返回 head。
     * 1.3 递归调用 什么时候需要递归呢？我们想一下这两种情况：
     *          如果 head.val != head.next.val ，说明头节点的值不等于下一个节点的值，所以当前的 head 节点必须保留；
     *      但是 head.next 节点要不要保留呢？我们还不知道，需要对 head.next 进行递归，即对 head.next 作为头节点的链表，去除值重复的节点。
     *      所以 head.next = self.deleteDuplicates(head.next).
     *          如果 head.val == head.next.val ，说明头节点的值等于下一个节点的值，所以当前的 head 节点必须删除，
     *      并且 head 之后所有与 head.val 相等的节点也都需要删除；删除到哪个节点为止呢？需要用 move 指针一直向后遍历寻找到与 head.val 不等的节点。
     *      此时 move 之前的节点都不保留了，因此返回 deleteDuplicates(move);
     * 1.4 返回结果 题目让我们返回删除了值重复的节点后剩余的链表，结合上面两种递归调用的情况。
     *      如果 head.val != head.next.val ，头结点需要保留，因此返回的是 head；
     *      如果 head.val == head.next.val ，头结点需要删除，需要返回的是deleteDuplicates(move);。
     * @param head
     * @return
     */
    public ListNode deleteDuplicates2(ListNode head) {
        if(head == null || head.next == null){
            return head;
        }
        if (head.val != head.next.val){
            head.next = deleteDuplicates2(head.next);
        }else {
            ListNode move = head.next;
            while (move != null && head.val == move.val){
                move = move.next;
            }
            return deleteDuplicates2(move);
        }
        return head;
    }

    /**
     * 利用计数，两次遍历：
     *  这个做法忽略了链表有序这个性质，使用了两次遍历，第一次遍历统计每个节点的值出现的次数，
     *  第二次遍历的时候，如果发现 head.next 的 val 出现次数不是 1 次，则需要删除 head.next。
     * @param head
     * @return
     */
    public ListNode deleteDuplicates3(ListNode head) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        Map<Integer,Integer> map = new HashMap<>();
        while (head != null){
            map.put(head.val, map.getOrDefault(head.val, 0) + 1);
            head = head.next;
        }
        head = dummy;
        while (head != null && head.next != null){
            if (map.get(head.next.val) != 1){
                head.next = head.next.next;
            }else {
                head = head.next;
            }
        }
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




























