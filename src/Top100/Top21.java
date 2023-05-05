package Top100;

/**
 * @author mxy
 * @create 2022-11-17 10:49
 */

/** 该题同 Offer25 （剑指Offer 25）
 * 21. 合并两个有序链表  链接：https://leetcode.cn/problems/merge-two-sorted-lists
 * 将两个升序链表合并为一个新的 升序 链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。
 *
 * 示例 1：
 * 输入：l1 = [1,2,4], l2 = [1,3,4]
 * 输出：[1,1,2,3,4,4]
 *
 * 示例 2：
 * 输入：l1 = [], l2 = []
 * 输出：[]
 *
 * 示例 3：
 * 输入：l1 = [], l2 = [0]
 * 输出：[0]
 *  
 * 提示：
 * 两个链表的节点数目范围是 [0, 50]
 * -100 <= Node.val <= 100
 * l1 和 l2 均按 非递减顺序 排列
 */
public class Top21 {

    public static void main(String[] args) {

    }

    /**
     * 题解链接：https://leetcode.cn/problems/merge-two-sorted-lists/solution/he-bing-liang-ge-you-xu-lian-biao-by-leetcode-solu/
     */

    /**
     * 方法一：递归
     *   若忽略边界情况，比如空链表等：
     *      list1[0] + merge(list1[1:],list2)  list1[0] < list2[0]
     *      list2[0] + merge(list1,list2[1:])  otherwise
     *   两个链表头部值较小的一个节点与剩下元素的merge操作结果合并
     * 复杂度分析：
     *    时间复杂度：O(n + m)，其中 n 和 m 分别为两个链表的长度。因为每次调用递归都会去掉 l1 或者 l2 的头节点（直到至少有一个链表为空），函数 mergeTwoList 至多只会递归调用每个节点一次。因此，时间复杂度取决于合并后的链表长度，即 O(n+m)。
     *    空间复杂度：O(n + m)，其中 n 和 m 分别为两个链表的长度。递归调用 mergeTwoLists 函数时需要消耗栈空间，栈空间的大小取决于递归调用的深度。结束递归调用时 mergeTwoLists 函数最多调用 n+m 次，因此空间复杂度为 O(n+m)。
     * @param list1
     * @param list2
     * @return
     */
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        if (list1 == null) return list2;
        else if (list2 == null) return list1;
        else if (list1.val < list2.val) {
            list1.next = mergeTwoLists(list1.next, list2);
            return list1;
        }else{
            list2.next = mergeTwoLists(list1,list2.next);
            return list2;
        }
    }

    /**
     * 方法二：迭代
     * 复杂度分析：
     *    时间复杂度 O(M+N) ： M, N 分别为链表 l1 , l2 的长度，合并操作需遍历两链表。
     *    空间复杂度 O(1) ： 节点引用 dum , cur 使用常数大小的额外空间。
     * @param list1
     * @param list2
     * @return
     */
    public ListNode mergeTwoLists1(ListNode list1, ListNode list2) {
        ListNode dump = new ListNode(0);
        ListNode cur = dump;
        while (list1 != null && list2 != null){
            if (list1.val < list2.val){
                cur.next = list1;
                list1 = list1.next;
            }else {
                cur.next = list2;
                list2 = list2.next;
            }
            cur = cur.next;
        }
        cur.next = list1 != null ? list1 : list2;
        return dump.next;
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
