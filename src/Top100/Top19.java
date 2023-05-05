package Top100;

/**
 * @author mxy
 * @create 2022-11-17 8:08
 */

import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

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
 *
 * 提示：
 * 链表中结点的数目为 sz
 * 1 <= sz <= 30
 * 0 <= Node.val <= 100
 * 1 <= n <= sz
 *  
 * 进阶：你能尝试使用一趟扫描实现吗？
 */
public class Top19 {

    public static void main(String[] args) {
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(4);
        ListNode node5 = new ListNode(5);
        //node1.next = node2;
        //node2.next = node3;
        //node3.next = node4;
        //node4.next = node5;
        Top19 top19 = new Top19();
        top19.removeNthFromEnd(node1, 1);
        ListNode.print(node1);
    }

    /**
     * 题解链接：https://leetcode.cn/problems/remove-nth-node-from-end-of-list/solution/shan-chu-lian-biao-de-dao-shu-di-nge-jie-dian-b-61/
     */

    /**
     * 方法一：计算链表长度
     * @param head
     * @param n
     * @return
     */
    public ListNode removeNthFromEnd1(ListNode head, int n) {
        ListNode dummy = new ListNode(0, head); //防止删除头节点时，出现空指针异常
        int length = getLength(head);
        ListNode cur = dummy;
        for (int i = 1; i < length - n + 1; ++i) {
            cur = cur.next;
        }
        cur.next = cur.next.next;
        ListNode ans = dummy.next; //即使删除头节点，也不会出现空指针异常（比如：只一个节点）
        return ans;
    }

    public int getLength(ListNode head) {
        int length = 0;
        while (head != null) {
            ++length;
            head = head.next;
        }
        return length;
    }


    /**
     * 方法二：栈
     * @param head
     * @param n
     * @return
     */
    public ListNode removeNthFromEnd2(ListNode head, int n) {
        ListNode dummy = new ListNode(0, head); //防止删除头节点时，出现空指针异常
        Deque<ListNode> stack = new LinkedList<ListNode>();
        ListNode cur = dummy;
        while (cur != null) {
            stack.push(cur);
            cur = cur.next;
        }
        for (int i = 0; i < n; ++i) {
            stack.pop();
        }
        ListNode prev = stack.peek();
        prev.next = prev.next.next;
        ListNode ans = dummy.next; //即使删除头节点，也不会出现空指针异常（比如：只一个节点）
        return ans;
    }

    /**
     * 方法三：双指针：快慢指针
     * first 与 second 指针之间 相隔 N 个节点，则当 first 指向末尾时，second指向要删除的节点
     * 但由于是单链表删除，要找到被删除节点的前一个节点，则 first - second - 1 = N
     * 当 first指向末尾 null 时，second 指向 倒数第 N+1 个节点，即被删除节点的前一个节点
     * @param head
     * @param n
     * @return
     */
    public ListNode removeNthFromEnd3(ListNode head, int n) {
        ListNode dummy = new ListNode(0, head); //防止删除头节点时，出现空指针异常
        ListNode first = head,second = dummy;
        for (int i = 0; i < n; i++) { //first先向前移动 n 个位置
            first = first.next;
        }
        while (first != null){
            first = first.next;
            second = second.next;
        }
        second.next = second.next.next; //second指向被删除节点的前一个节点
        ListNode ans = dummy.next; //即使删除头节点，也不会出现空指针异常（比如：只一个节点）
        return ans;
    }

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


    /**
     * 执行用时： 1 ms , 在所有 Java 提交中击败了 100.00% 的用户
     * 内存消耗： 39.8 MB , 在所有 Java 提交中击败了 30.73% 的用户
     * 倒数第N个节点，正数第 length - N + 1 个节点，要找到 length - N 个节点
     * @param head
     * @param n
     * @return
     */
    public ListNode removeNthFromEnd4(ListNode head, int n) {
        ListNode cur = head;
        Map<Integer, ListNode> map = new HashMap<>();
        int i = 1;
        while (cur != null){
            map.put(i,cur);
            cur = cur.next;
            i++;
        }
        //删除头节点 !!! (while中的 i 多加一次， i - 1 才为链表的长度)
        if (n == i - 1) head = head.next;
        else map.get(i - 1 - n).next = map.get(i - 1 - n).next.next;
        return head;
    }





    public static class ListNode{
        int val;
        ListNode next;

        public ListNode() {
        }

        public ListNode(int val) {
            this.val = val;
        }

        public ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }

        public String toString() {
            return val + "";
        }

        public static void print(ListNode node){
            while (node != null){
                System.out.print(node + "\t");
                node = node.next;
            }
        }
    }
}
