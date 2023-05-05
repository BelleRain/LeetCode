package CodeTop;

/**
 * @author mxy
 * @create 2023-04-17 8:09
 */

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * 23. 合并 K 个升序链表   链接：https://leetcode.cn/problems/merge-k-sorted-lists
 * 给你一个链表数组，每个链表都已经按升序排列。
 * 请你将所有链表合并到一个升序链表中，返回合并后的链表。
 *
 * 示例 1：
 * 输入：lists = [[1,4,5],[1,3,4],[2,6]]
 * 输出：[1,1,2,3,4,4,5,6]
 * 解释：链表数组如下：
 * [
 *   1->4->5,
 *   1->3->4,
 *   2->6
 * ]
 * 将它们合并到一个有序链表中得到。
 * 1->1->2->3->4->4->5->6
 *
 * 示例 2：
 * 输入：lists = []
 * 输出：[]
 *
 * 示例 3：
 * 输入：lists = [[]]
 * 输出：[]
 *  
 *
 * 提示：
 * k == lists.length
 * 0 <= k <= 10^4
 * 0 <= lists[i].length <= 500
 * -10^4 <= lists[i][j] <= 10^4
 * lists[i] 按 升序 排列
 * lists[i].length 的总和不超过 10^4
 *
 */
public class Code23 {

    public static void main(String[] args) {
        ListNode node1 = new ListNode(1);
        ListNode node2 = new ListNode(4);
        ListNode node3 = new ListNode(5);
        ListNode node4 = new ListNode(1);
        ListNode node5 = new ListNode(3);
        ListNode node6 = new ListNode(4);
        ListNode node7 = new ListNode(2);
        ListNode node8 = new ListNode(6);

        //list1
        node1.next = node2;
        node2.next = node3;
        //list2
        node4.next = node5;
        node5.next = node6;
        //list3
        node7.next = node8;

        ListNode[] lists = {node1,node4,node7};
        //ListNode node = mergeKLists1(lists);
        ListNode node = mergeKListsIterate2(lists);
        printNode(node);
    }

    /**
     * 题解链接：https://leetcode.cn/problems/merge-k-sorted-lists/solution/4-chong-fang-fa-xiang-jie-bi-xu-miao-dong-by-sweet/
     */

    /**
     * 题解一： 小跟堆，lists中都是递增链表。每次循环，利用小跟堆选中最小的元素，添加到新链表的末尾
     * @param lists
     * @return
     */
    public static ListNode mergeKLists1(ListNode[] lists) {
        Queue<ListNode> pq = new PriorityQueue<>((v1, v2) -> v1.val - v2.val);
        for (ListNode node: lists) {
            if (node != null) {
                pq.offer(node);
            }
        }

        ListNode dummyHead = new ListNode(0);
        ListNode tail = dummyHead;
        while (!pq.isEmpty()) {
            ListNode minNode = pq.poll();
            tail.next = minNode;
            tail = minNode;
            if (minNode.next != null) {
                pq.offer(minNode.next);
            }
        }

        return dummyHead.next;
    }


    /**
     * 两两合并：迭代
     * @param lists
     * @return
     */
    public static ListNode mergeKListsIterate2(ListNode[] lists) {
        if (lists.length == 0) {
            return null;
        }
        int k = lists.length;
        while (k > 1) {
            //合并后链表中元素的索引
            int idx = 0;
            //分而治之
            for (int i = 0; i < k; i += 2) {
                if (i == k - 1) {
                    lists[idx++] = lists[i];
                } else {
                    //合并链表（表头表示）（每一次while循环）：① 1,2；3,4；5,6；
                    //② 1,3；5
                    //③ 1,5；
                    lists[idx++] = mergeRecur(lists[i], lists[i + 1]);
                }
            }
            //当 idx = 1 时，证明链表列表的长度为 1，即合并完毕
            k = idx;
        }
        return lists[0];
    }

    /**
     * 合并链表：递归
     * @param l1
     * @param l2
     * @return
     */
    private static ListNode mergeRecur(ListNode l1, ListNode l2) {
        if (l1 == null) {
            return l2;
        }
        if (l2 == null) {
            return l1;
        }
        if (l1.val < l2.val) {
            l1.next = mergeRecur(l1.next, l2);
            return l1;
        }
        l2.next = mergeRecur(l1, l2.next);
        return l2;
    }

    /**
     * 合并链表：迭代
     * @param l1
     * @param l2
     * @return
     */
    private static ListNode mergeIter(ListNode l1, ListNode l2) {
        ListNode dummyHead = new ListNode(0);
        ListNode tail = dummyHead;
        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                tail.next = l1;
                l1 = l1.next;
            } else {
                tail.next = l2;
                l2 = l2.next;
            }
            tail = tail.next;
        }

        tail.next = l1 == null? l2: l1;

        return dummyHead.next;
    }

    /**
     * 两两合并： 递归
     * @param lists
     * @return
     */
    public static ListNode mergeKListsRecur2(ListNode[] lists) {
        if (lists.length == 0) {
            return null;
        }
        return merge(lists, 0, lists.length - 1);
    }

    private static ListNode merge(ListNode[] lists, int lo, int hi) {
        if (lo == hi) {
            return lists[lo];
        }
        int mid = lo + (hi - lo) / 2;
        ListNode l1 = merge(lists, lo, mid);
        ListNode l2 = merge(lists, mid + 1, hi);
        return mergeRecur(l1, l2);
    }


    /**
     * 题解三：利用 K 个指针 选出 K 个元素的最小值
     * @param lists
     * @return
     */
    public static ListNode mergeKLists3(ListNode[] lists) {
        int k = lists.length;
        ListNode dummyHead = new ListNode(0);
        ListNode tail = dummyHead;
        while (true) {
            ListNode minNode = null;
            int minPointer = -1;
            for (int i = 0; i < k; i++) {
                //选出 k 个元素中 最小的元素
                if (lists[i] == null) {
                    continue;
                }
                if (minNode == null || lists[i].val < minNode.val) {
                    minNode = lists[i];
                    minPointer = i;
                }
            }
            //合并完毕
            if (minPointer == -1) {
                break;
            }
            tail.next = minNode;
            tail = tail.next;
            lists[minPointer] = lists[minPointer].next;
        }
        return dummyHead.next;
    }


    public static void printNode(ListNode head){
        if (head == null) return;
        ListNode cur = head;
        while (cur != null){
            System.out.print(cur.val + " ");
            cur = cur.next;
        }
        System.out.println();
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
