package CodeTop;

/**
 * @author mxy
 * @create 2023-04-11 15:55
 */

/**
 * 21. 合并两个有序链表     链接：https://leetcode.cn/problems/merge-two-sorted-lists
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
 *
 */
public class Code21 {

    public static void main(String[] args) {

    }

    /**
     * 递归实现
     * @param list1
     * @param list2
     * @return
     */
    public ListNode mergeTwoLists1(ListNode list1, ListNode list2) {
        if (list1 == null && list2 == null) return null;
        else if (list1 == null) return list2;
        else if (list2 == null) return list1;
        else if (list1.val <= list2.val) {
            list1.next = mergeTwoLists1(list1.next, list2);
            return list1;
        }
        else{
            list2.next = mergeTwoLists1(list1, list2.next);
            return list2;
        }
    }


    /**
     * 迭代实现
     * @param list1
     * @param list2
     * @return
     */
    public ListNode mergeTwoLists2(ListNode list1, ListNode list2) {
        ListNode dump = new ListNode(0);
        ListNode cur = dump;
        while (list1 != null && list2 != null){
            if (list1.val <= list2.val){
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


    public static class ListNode {
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
