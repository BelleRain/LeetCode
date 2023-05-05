package CodeTop;

/**
 * @author mxy
 * @create 2023-04-17 11:25
 */

import java.util.HashSet;
import java.util.Set;

/**
 * 142. 环形链表 II     链接：https://leetcode.cn/problems/linked-list-cycle-ii
 * 给定一个链表的头节点  head ，返回链表开始入环的第一个节点。 如果链表无环，则返回 null。
 * 如果链表中有某个节点，可以通过连续跟踪 next 指针再次到达，则链表中存在环。
 * 为了表示给定链表中的环，评测系统内部使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。
 * 如果 pos 是 -1，则在该链表中没有环。注意：pos 不作为参数进行传递，仅仅是为了标识链表的实际情况。
 * 不允许修改 链表。
 *
 *
 * 示例 1：
 * 输入：head = [3,2,0,-4], pos = 1
 * 输出：返回索引为 1 的链表节点
 * 解释：链表中有一个环，其尾部连接到第二个节点。
 *
 * 示例 2：
 * 输入：head = [1,2], pos = 0
 * 输出：返回索引为 0 的链表节点
 * 解释：链表中有一个环，其尾部连接到第一个节点。
 *
 * 示例 3：
 * 输入：head = [1], pos = -1
 * 输出：返回 null
 * 解释：链表中没有环。
 *  
 *
 * 提示：
 * 链表中节点的数目范围在范围 [0, 10^4] 内
 * -10^5 <= Node.val <= 10^5
 * pos 的值为 -1 或者链表中的一个有效索引
 *  
 * 进阶：你是否可以使用 O(1) 空间解决此题？
 *
 */
public class Code142 {


    /**
     * 哈希表：
     * @param head
     * @return
     */
    public ListNode detectCycle1(ListNode head) {
        Set<ListNode> set = new HashSet<>();
        ListNode cur = head;
        while (cur != null){
            if (!set.add(cur)){
                break;
            }
            cur = cur.next;
        }
        return cur == null ? null : cur;
    }


    /**
     * 双指针
     * @param head
     * @return
     */
    public ListNode detectCycle2(ListNode head) {
        if (head == null) return head;
        ListNode slow = head;
        ListNode fast = head;
        //寻找相遇点
        while (true){
            if (fast == null || fast.next == null) return null;
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) break;
        }
        ListNode ptr = head;
        //寻找入环点
        while (ptr != slow){
            ptr = ptr.next;
            slow = slow.next;
        }
        return slow;
    }


    public class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }
}
