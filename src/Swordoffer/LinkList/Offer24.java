package Swordoffer.LinkList;

/**
 * @author mxy
 * @create 2022-09-17 12:00
 */

/**
 * 反转链表：
 * 定义一个函数，输入一个链表的头节点，反转该链表并输出反转后链表的头节点。
 * 输入: 1->2->3->4->5->NULL
 * 输出: 5->4->3->2->1->NULL
 */
public class Offer24 {

    /**
     *
     * 正向递归，逆向回溯
     * 错误解答
     * @param head
     * @return
     */
    //错误解答
    //private ListNode head;
    //
    //public ListNode reverseList(ListNode head) {
    //    ListNode resHead = traversal(head);
    //    return resHead;
    //}
    //
    //public ListNode traversal(ListNode node){
    //    if (node.next == null){
    //        head = node;
    //        return head;
    //    }
    //    traversal(node.next);
    //    node.next = node;
    //    return head;
    //}

    /**
     * 解法一：递归求解
     * 执行用时： 0 ms , 在所有 Java 提交中击败了 100.00% 的用户
     * 内存消耗： 41.1 MB , 在所有 Java 提交中击败了 33.82% 的用户
     * @param head
     * @return
     */
    //public ListNode reverseList(ListNode head) {
    //    return recur(head, null);    // 调用递归并返回
    //}
    //
    //private ListNode recur(ListNode cur, ListNode pre) {
    //
    //    if (cur == null)
    //        return pre; // 终止条件
    //    ListNode res = recur(cur.next, cur);  // 递归后继节点
    //    cur.next = pre;              // 修改节点引用指向
    //    return res;                  // 返回反转链表的头节点
    //}

    /**
     * 解法二：迭代
     * 执行用时： 0 ms , 在所有 Java 提交中击败了 100.00% 的用户
     * 内存消耗： 40.6 MB , 在所有 Java 提交中击败了 90.73% 的用户
     * 解题过程：
     * 1、输入 1->2->3->4->5->null
     * 2、1->null
     * 3、2->1->null
     * 4、3->2->1->null
     * ……
     */
    public ListNode reverseList(ListNode head) {
        ListNode cur = head; //当前节点
        ListNode pre = null;
        while(cur!=null){
            ListNode temp = cur.next; //保存后继结点
            cur.next = pre;
            pre = cur;  //保存当前节点
            cur = temp; //移动当前节点到下一位置
        }
        return pre;
    }


}





































