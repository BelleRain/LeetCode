package Top100;

/**
 * @author mxy
 * @create 2022-12-06 17:02
 */

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 206. 反转链表 (同剑指Offer24)   链接：https://leetcode.cn/problems/reverse-linked-list
 * 给你单链表的头节点 head ，请你反转链表，并返回反转后的链表。
 *
 * 示例 1：
 * 输入：head = [1,2,3,4,5]
 * 输出：[5,4,3,2,1]
 *
 * 示例 2：
 * 输入：head = [1,2]
 * 输出：[2,1]
 *
 * 示例 3：
 * 输入：head = []
 * 输出：[]
 *  
 *
 * 提示：
 * 链表中节点的数目范围是 [0, 5000]
 * -5000 <= Node.val <= 5000
 *  
 * 进阶：链表可以选用迭代或递归方式完成反转。你能否用两种方法解决这道题？
 *
 */
public class Top206 {

    /**
     * 方法一：辅助栈法
     *  注意：最后反转后的链表，要处理好方向，否则会与链表1交互。
     * 执行用时： 1 ms , 在所有 Java 提交中击败了 4.71% 的用户
     * 内存消耗： 41.3 MB , 在所有 Java 提交中击败了 46.29% 的用户
     * @param head
     * @return
     */
    /*public ListNode reverseList(ListNode head) {
        if (head == null) return head;
        Deque<ListNode> stack = new ArrayDeque<>();
        ListNode cur = head;
        while (cur != null){
            stack.push(cur);
            cur = cur.next;
        }
        ListNode dump = new ListNode();
        cur = stack.pop();
        dump.next = cur;
        while (!stack.isEmpty()){
            cur.next = stack.pop();
            cur = cur.next;
        }
        //最后反转后的链表，要处理好方向，否则会与链表1交互。
        //若不处理，反转后的链表的最后一个元素，其 next 节点（默认） 会 指向 链表1中 的第二个元素，从而形成环。
        //所以，结束时要将其 置为 null
        cur.next = null;
        return dump.next;
    }*/

    /**
     * 题解链接：https://leetcode.cn/problems/reverse-linked-list/solution/dong-hua-yan-shi-206-fan-zhuan-lian-biao-by-user74/
     */

    /**
     * 方法一：迭代
     * @param head
     * @return
     */
    /*public ListNode reverseList(ListNode head) {
        ListNode cur = head; //保存当前节点
        ListNode pre = null; //初始化前驱节点
        while (cur != null){
            ListNode temp = cur.next; //保存后继节点
            cur.next = pre;
            pre = cur;
            cur = temp;
        }
        return pre;
    }*/

    /**
     * 方法二：递归
     * 递归过程： 以  1->2->3->4->5 为例
     *  (cur,pre):
     *          (1,null) -> (2,1) -> (3,2) -> (4,3) -> (5,4) -> (null,5)
     * 逐层返回：5->4 , 4->3 , 3->2 , 2->1 , 1->null
     *          5->4->3->2->1->null
     * @param head
     * @return
     */
    public ListNode reverseList(ListNode head) {
        return recur(head,null);
    }

    private ListNode recur(ListNode cur, ListNode pre) {
        if (cur == null){
            return pre;
        }
        ListNode res = recur(cur.next,cur); // 递归后继节点
        cur.next = pre;                     // 修改节点引用指向
        return res;                         // 返回反转链表的头节点
    }

    /**
     * 以链表1->2->3->4->5举例
     * @param head
     * @return
     */
    //public ListNode reverseList(ListNode head) {
    //    if (head == null || head.next == null) {
    //        /*
    //            直到当前节点的下一个节点为空时返回当前节点
    //            由于5没有下一个节点了，所以此处返回节点5
    //         */
    //        return head;
    //    }
    //    //递归传入下一个节点，目的是为了到达最后一个节点
    //    ListNode newHead = reverseList(head.next);
    //            /*
    //        第一轮出栈，head为5，head.next为空，返回5
    //        第二轮出栈，head为4，head.next为5，执行head.next.next=head也就是5.next=4，
    //                  把当前节点的子节点的子节点指向当前节点
    //                  此时链表为1->2->3->4<->5，由于4与5互相指向，所以此处要断开4.next=null
    //                  此时链表为1->2->3->4<-5
    //                  返回节点5
    //        第三轮出栈，head为3，head.next为4，执行head.next.next=head也就是4.next=3，
    //                  此时链表为1->2->3<->4<-5，由于3与4互相指向，所以此处要断开3.next=null
    //                  此时链表为1->2->3<-4<-5
    //                  返回节点5
    //        第四轮出栈，head为2，head.next为3，执行head.next.next=head也就是3.next=2，
    //                  此时链表为1->2<->3<-4<-5，由于2与3互相指向，所以此处要断开2.next=null
    //                  此时链表为1->2<-3<-4<-5
    //                  返回节点5
    //        第五轮出栈，head为1，head.next为2，执行head.next.next=head也就是2.next=1，
    //                  此时链表为1<->2<-3<-4<-5，由于1与2互相指向，所以此处要断开1.next=null
    //                  此时链表为1<-2<-3<-4<-5
    //                  返回节点5
    //        出栈完成，最终头节点5->4->3->2->1
    //     */
    //    head.next.next = head;
    //    head.next = null;   //防止链表循环，需要将head.next设置为空，否则在最后一层递归时，1,2之间会互相指向
    //    return newHead;
    //}



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
