package Top100;

/**
 * @author mxy
 * @create 2022-11-28 14:25
 */

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * 141. 环形链表    链接：https://leetcode.cn/problems/linked-list-cycle
 * 给你一个链表的头节点 head ，判断链表中是否有环。
 * 如果链表中有某个节点，可以通过连续跟踪 next 指针再次到达，则链表中存在环。
 * 为了表示给定链表中的环，评测系统内部使用整数 pos 来表示链表尾连接到链表中的位置（索引从 0 开始）。
 * 注意：pos 不作为参数进行传递 。仅仅是为了标识链表的实际情况。
 * 如果链表中存在环 ，则返回 true 。 否则，返回 false 。
 *
 * 示例 1：
 * 输入：head = [3,2,0,-4], pos = 1
 * 输出：true
 * 解释：链表中有一个环，其尾部连接到第二个节点。
 *
 * 示例 2：
 * 输入：head = [1,2], pos = 0
 * 输出：true
 * 解释：链表中有一个环，其尾部连接到第一个节点。
 *
 * 示例 3：
 * 输入：head = [1], pos = -1
 * 输出：false
 * 解释：链表中没有环。
 *  
 * 提示：
 * 链表中节点的数目范围是 [0, 10^4]
 * -10^5 <= Node.val <= 10^5
 * pos 为 -1 或者链表中的一个 有效索引 。
 *  
 * 进阶：你能用 O(1)（即，常量）内存解决此问题吗？
 */
public class Top141 {

    public static void main(String[] args) {
        Top141 top141 = new Top141();
        ListNode node1 = new ListNode(3);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(0);
        ListNode node4 = new ListNode(-4);
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node2;
        System.out.println(top141.hasCycle(node1));
    }

    /**
     * 题解链接：https://leetcode.cn/problems/linked-list-cycle/solution/huan-xing-lian-biao-by-leetcode-solution/
     */

    /**
     * 方法一：哈希表 (效率低)
     * 复杂度分析
     *      时间复杂度：O(N)，其中 N 是链表中的节点数。最坏情况下我们需要遍历每个节点一次。
     *      空间复杂度：O(N)，其中 N 是链表中的节点数。主要为哈希表的开销，最坏情况下我们需要将每个节点插入到哈希表中一次。
     * @param head
     * @return
     */
    public boolean hasCycle1(ListNode head) {
        if(head == null || head.next == null) return false;
        Set<ListNode> set = new HashSet<>();
        while (head != null){
            if (!set.add(head)) return true;
            head = head.next;
        }
        return false;
    }

    /**
     * 方法二：快慢指针 （龟兔赛跑算法）
     * 为什么我们要规定初始时慢指针在位置 head，快指针在位置 head.next，而不是两个指针都在位置 head（即与「乌龟」和「兔子」中的叙述相同）？
     * 观察下面的代码，我们使用的是 while 循环，循环条件先于循环体。由于循环条件一定是判断快慢指针是否重合，
     * 如果我们将两个指针初始都置于 head，那么 while 循环就不会执行。
     * 因此，我们可以假想一个在 head 之前的虚拟节点，慢指针从虚拟节点移动一步到达 head，快指针从虚拟节点移动两步到达 head.next，
     * 这样我们就可以使用 while 循环了。
     * 当然，我们也可以使用 do-while 循环。此时，我们就可以把快慢指针的初始值都置为 head。
     * 复杂度分析 :
     *      时间复杂度：O(N)，其中 N 是链表中的节点数。
     *          当链表中不存在环时，快指针将先于慢指针到达链表尾部，链表中每个节点至多被访问两次。
     *          当链表中存在环时，每一轮移动后，快慢指针的距离将减小一。而初始距离为环的长度，因此至多移动 N 轮。
     *      空间复杂度：O(1)。我们只使用了两个指针的额外空间。
     * @param head
     * @return
     */
    public boolean hasCycle(ListNode head) {
        if (head == null || head.next == null) return false;
        ListNode fast = head.next, slow = head;
        while (slow != fast){
            if (fast == null || fast.next == null) return false;
            fast = fast.next.next;
            slow = slow.next;
        }
        return true;
    }

    /**
     * 效率低：
     * 执行用时： 5 ms , 在所有 Java 提交中击败了 5.68% 的用户
     * 内存消耗： 42.4 MB , 在所有 Java 提交中击败了 73.66% 的用户
     * @return
     */
    /*public boolean hasCycle(ListNode head) {
        if (head == null || head.next == null) return false;
        HashMap<ListNode, Integer> map = new HashMap<>();
        ListNode cur = head;
        int i = 1;
        while (cur != null){
            if (map.containsKey(cur)) break;
            map.put(cur,i);
            cur = cur.next;
            i++;
        }
        if (cur == null) return false;
        return true;
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
