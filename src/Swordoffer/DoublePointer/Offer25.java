package Swordoffer.DoublePointer;

/**
 * @author mxy
 * @create 2022-09-27 9:21
 */

/**
 * 合并两个排序的链表
 * 输入两个递增排序的链表，合并这两个链表并使新链表中的节点仍然是递增排序的。
 *
 * 示例1：
 * 输入：1->2->4, 1->3->4
 * 输出：1->1->2->3->4->4
 */
public class Offer25 {

    /**
     * 关键点：构建新的头节点
     */

    /**
     * 解法一：迭代  原文链接：https://leetcode.cn/problems/he-bing-liang-ge-pai-xu-de-lian-biao-lcof/solution/mian-shi-ti-25-he-bing-liang-ge-pai-xu-de-lian-b-2/
     * 解题思路：
     * 根据题目描述， 链表 l1,l2 是 递增 的，因此容易想到使用双指针 l1和l2遍历两链表
     * 根据 l1.val 和 l2.val 的大小关系确定节点添加顺序，两节点指针交替前进，直至遍历完毕。
     * 引入伪头节点： 由于初始状态合并链表中无节点，因此循环第一轮时无法将节点添加到合并链表中。
     * 解决方案：初始化一个辅助节点 dum 作为合并链表的伪头节点，将各节点添加至 dum 之后。
     * 算法流程：
     * 1、初始化：伪头节点dum，节点cur指向dum。
     * 2、循环合并：当l1或l2为空时跳出；
     *     1、当 l1.val < l2.val 时： cur 的后继节点指定为 l1，并 l1 向前走一步；
     *     2、当 l1.val ≥ l2.val 时：  cur 的后继节点指定为 l2，并 l2 向前走一步 ；
     *     3、节点 cur 向前走一步，即 cur = cur.next。
     * 3、合并剩余尾部： 跳出时有两种情况，即 l1为空 或 l2为空。
     *     1、若 l1！=null ：将 l1 添加至节点 cur 之后；
     *     2、否则：将 l2 添加至节点 cur 之后。
     * 4、返回值：合并链表在伪头节点 dum 之后，因此返回 dum.next 即可。
     *复杂度分析：
     *  时间复杂度 O(M+N) ： M, N 分别为链表 l1 , l2 的长度，合并操作需遍历两链表。
     *  空间复杂度 O(1) ： 节点引用 dum , cur 使用常数大小的额外空间。
     *  @param l1
     * @param l2
     * @return
     */
    /*public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode dum = new ListNode(0);
        ListNode cur = dum;
        while (l1 != null && l2 != null){
            if (l1.val < l2.val){
                cur.next = l1;
                l1 = l1.next;
            }else {
                cur.next = l2;
                l2 = l2.next;
            }
            cur = cur.next;
        }
        cur.next = l1 != null ? l1 : l2;
        return dum.next;
    }*/

    /**
     * 解法二： 递归  原文链接：https://leetcode.cn/problems/he-bing-liang-ge-pai-xu-de-lian-biao-lcof/solution/he-bing-liang-ge-pai-xu-de-lian-biao-by-g3z6g/
     * 思路 ：
     *    我们可以如下递归地定义两个链表里的 merge 操作（忽略边界情况，比如空链表等）：
     *     list1[0] + merge(list1[1:], list2)   list1[0] < list2[0]
     *     list2[0] + merge(list1, list2[1:])   otherwise
     *     也就是说，两个链表头部值较小的一个节点与剩下元素的 merge 操作结果合并。
     *算法：
     *    我们直接将以上递归过程建模，同时需要考虑边界情况。
     *    如果 l1 或者 l2 一开始就是空链表 ，那么没有任何操作需要合并，所以我们只需要返回非空链表。
     *    否则，我们要判断 l1 和 l2 哪一个链表的头节点的值更小，然后递归地决定下一个添加到结果里的节点。
     *    如果两个链表有一个为空，递归结束。
     *复杂度分析：
     *   时间复杂度：O(n + m)，其中 n 和 m 分别为两个链表的长度。因为每次调用递归都会去掉 l1 或者 l2 的头节点（直到至少有一个链表为空），函数 mergeTwoList 至多只会递归调用每个节点一次。因此，时间复杂度取决于合并后的链表长度，即 O(n+m)。
     *   空间复杂度：O(n + m)，其中 n 和 m 分别为两个链表的长度。递归调用 mergeTwoLists 函数时需要消耗栈空间，栈空间的大小取决于递归调用的深度。结束递归调用时 mergeTwoLists 函数最多调用 n+m 次，因此空间复杂度为 O(n+m)。
     */
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        if (l1 == null) return l2;
        else if (l2 == null) return l1;
        else if (l1.val < l2.val){
            l1.next = mergeTwoLists(l1.next, l2);
            return l1;
        }else {
            l2.next = mergeTwoLists(l1, l2.next);
            return l2;
        }
    }


    public class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }
}
