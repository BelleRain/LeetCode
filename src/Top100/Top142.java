package Top100;

/**
 * @author mxy
 * @create 2022-11-28 15:21
 */

import java.util.*;

/**
 * 142. 环形链表 II     链接：https://leetcode.cn/problems/linked-list-cycle-ii
 * 给定一个链表的头节点  head ，返回链表开始入环的第一个节点。 如果链表无环，则返回 null。
 * 如果链表中有某个节点，可以通过连续跟踪 next 指针再次到达，则链表中存在环。
 * 为了表示给定链表中的环，评测系统内部使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。
 * 如果 pos 是 -1，则在该链表中没有环。注意：pos 不作为参数进行传递，仅仅是为了标识链表的实际情况。
 * 不允许修改 链表。
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
 * 提示：
 * 链表中节点的数目范围在范围 [0, 10^4] 内
 * -10^5 <= Node.val <= 10^5
 * pos 的值为 -1 或者链表中的一个有效索引
 *
 * 进阶：你是否可以使用 O(1) 空间解决此题？
 */
public class Top142 {

    public static void main(String[] args) {
        Top142 top142 = new Top142();
        ListNode node1 = new ListNode(3);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(0);
        ListNode node4 = new ListNode(-4);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node2;
        System.out.println(top142.detectCycle(node1));
    }

    /**
     * 题解一： https://leetcode.cn/problems/linked-list-cycle-ii/solution/huan-xing-lian-biao-ii-by-leetcode-solution/
     */

    /**
     * 方法一： 哈希表
     * @param head
     * @return
     */
    /*public ListNode detectCycle(ListNode head) {
        if (head == null || head.next == null) return null;
        Set<ListNode> set = new HashSet<>();
        while (head != null){
            if (!set.add(head)) return head;
            head = head.next;
        }
        return null;
    }*/

    /**
     * 方法二：快慢指针
     * 思路分析：
     * 1、起始点相同
     * 2、slow 指针每次向后移动一个位置，而 fast 指针向后移动两个位置。 如果链表中存在环，则 fast 指针最终将再次与 slow 指针在环中相遇。
     * 3、任意时刻，fast 指针走过的距离都为 slow 指针的 2 倍
     * 4、如下图所示(图见原文)，设链表中环外部分的长度为 a。slow 指针进入环后，又走了 b 的距离与fast 相遇。
     *    此时，fast 指针已经走完了环的 n 圈，因此它走过的总距离为 a+n(b+c)+b=a+(n+1)b+nc。
     * 5、根据题意，任意时刻，fast 指针走过的距离都为 slow 指针的 2 倍。
     *     因此，我们有  a+(n+1)b+nc = 2(a+b) ⟹ a=c+(n−1)(b+c)
     * 6、有了 a = c+(n-1)(b+c) 的等量关系，我们会发现：（即 链表头部 和 相遇点同时出发 最终同时到达 入环点 ）
     *    从相遇点到入环点的距离加上 n-1 圈的环长，恰好等于从链表头部到入环点的距离。
     * 7、因此，当发现slow 与 fast 相遇时，我们再额外使用一个指针 ptr。起始，它指向链表头部；随后，它和 slow 每次向后移动一个位置。最终，它们会在入环点相遇。
     * 复杂度分析
     *      时间复杂度：O(N)，其中 N 为链表中节点的数目。在最初判断快慢指针是否相遇时，slow 指针走过的距离不会超过链表的总长度；
     *         随后寻找入环点时，走过的距离也不会超过链表的总长度。因此，总的执行时间为 O(N)+O(N)=O(N)。
     *      空间复杂度：O(1)。我们只使用了 slow,fast,ptr 三个指针。
     */
    public ListNode detectCycle(ListNode head) {
        if (head == null) {
            return null;
        }
        ListNode slow = head, fast = head;
        while (true) {
            if (fast == null || fast.next == null) return null;
            slow = slow.next;
            fast = fast.next.next;
            if (fast == slow) break;
        }
        ListNode ptr = head;
        //链表头部 和 相遇点同时出发 最终同时到达 入环点
        while (ptr != slow) {
            ptr = ptr.next;
            slow = slow.next;
        }
        return ptr;
    }

    /**
     * 题解二：https://leetcode.cn/problems/linked-list-cycle-ii/solution/linked-list-cycle-ii-kuai-man-zhi-zhen-shuang-zhi-/
     * 快慢指针
     * @param head
     * @return
     */
    /*public ListNode detectCycle(ListNode head) {
        ListNode fast = head, slow = head;
        while (true) {
            if (fast == null || fast.next == null) return null;
            fast = fast.next.next;
            slow = slow.next;
            if (fast == slow) break;
        }
        fast = head;
        while (slow != fast) {
            slow = slow.next;
            fast = fast.next;
        }
        return fast;
    }*/

    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }
}
