package Top100;

/**
 * @author mxy
 * @create 2022-11-30 12:10
 */

/**
 * 147. 对链表进行插入排序    链接：https://leetcode.cn/problems/insertion-sort-list
 * 给定单个链表的头 head ，使用 插入排序 对链表进行排序，并返回 排序后链表的头 。
 * 插入排序 算法的步骤:
 * 1、插入排序是迭代的，每次只移动一个元素，直到所有元素可以形成一个有序的输出列表。
 * 2、每次迭代中，插入排序只从输入数据中移除一个待排序的元素，找到它在序列中适当的位置，并将其插入。
 * 3、重复直到所有输入数据插入完为止。
 * 下面是插入排序算法的一个图形示例。部分排序的列表(黑色)最初只包含列表中的第一个元素。
 * 每次迭代时，从输入数据中删除一个元素(红色)，并就地插入已排序的列表中。
 *
 * 对链表进行插入排序。
 *
 * 示例 1：
 * 输入: head = [4,2,1,3]
 * 输出: [1,2,3,4]
 *
 * 示例 2：
 * 输入: head = [-1,5,3,4,0]
 * 输出: [-1,0,3,4,5]
 *
 * 提示：
 * 列表中的节点数在 [1, 5000]范围内
 * -5000 <= Node.val <= 5000
 */
public class Top147 {


    /**
     * 题解链接： https://leetcode.cn/problems/insertion-sort-list/solution/dui-lian-biao-jin-xing-cha-ru-pai-xu-by-leetcode-s/
     * 复杂度分析：
     *      时间复杂度：O(n^2)，其中 n 是链表的长度。
     *      空间复杂度：O(1)。
     * @param head
     * @return
     */
    public ListNode insertionSortList(ListNode head) {
        // 1. 首先判断给定的链表是否为空或只有一个节点，若是，则直接返回。
        if (head == null || head.next == null) return head;
        //2. 链表初始化操作
        ListNode dummyHead = new ListNode(0); //引入哑结点
        dummyHead.next = head; //目的是在head之前插入节点
        ListNode lastSorted = head;  //维护lastSorted为链表已经排好序的最后一个节点并初始化
        ListNode curr = head.next;   //维护curr为待插入的元素并初始化
        //3. 插入排序
        while (curr != null){
            if (lastSorted.val <= curr.val){ //说明curr应该位于 lastSorted之后
                lastSorted = lastSorted.next; //将lastSorted后移一位，curr变成新的lastSorted
            }else {            //否则，从链表头节点开始向后遍历链表中的节点
                //每次都从头节点遍历查找
                ListNode prev = dummyHead;  //从链表头开始遍历 prev是插入节点curr位置 的前一个节点
                //循环结束后，在prev之后插入curr节点
                while (prev.next.val <= curr.val){
                    prev = prev.next;
                }
                //以下三行是为了完成对 curr 的插入，(配合题解动图可以直观看出)
                lastSorted.next = curr.next;  // 断开curr
                //将 curr 放入 prev 之后
                curr.next = prev.next;
                prev.next = curr;
            }
            curr = lastSorted.next; //将 curr 置为 下一个待插入元素
        }
        //返回排好序的链表
        return dummyHead.next;
    }

    public static class ListNode {
        int val;
        ListNode next;

        ListNode(){}
        ListNode(int x) {
            val = x;
        }
        ListNode(int x,ListNode next){
            val = x;
            this.next = next;
        }
    }
}
