package Swordoffer.DoublePointer;

/**
 * @author mxy
 * @create 2022-09-27 8:35
 */

import java.util.HashMap;
import java.util.Map;

/**
 * 链表中倒数第k个节点
 * 输入一个链表，输出该链表中倒数第k个节点。为了符合大多数人的习惯，本题从1开始计数，即链表的尾节点是倒数第1个节点。
 * 例如，一个链表有 6 个节点，从头节点开始，它们的值依次是 1、2、3、4、5、6。这个链表的倒数第 3 个节点是值为 4 的节点。
 * 示例：
 * 给定一个链表: 1->2->3->4->5, 和 k = 2.
 * 返回链表 4->5.
 */
public class Offer22 {

    /**
     * 解法一：
     * 执行用时： 1 ms , 在所有 Java 提交中击败了 100.00% 的用户
     * 内存消耗： 39.3 MB , 在所有 Java 提交中击败了 78.30% 的用户
     * @param head
     * @param k
     * @return
     */
    //将对应索引和节点放入哈希表中，取出对应索引的节点
    public ListNode getKthFromEnd1(ListNode head, int k) {
        if (head == null) return null;
        Map<Integer,ListNode> map = new HashMap<>();
        ListNode cur = head;
        int count = 0;
        while (cur != null){
            count++;
            map.put(count, cur);
            cur = cur.next;
        }
        int length = map.size();
        return map.get(length - k + 1);
    }

    /**
     * 解法二： 双指针   原文链接： 链接：https://leetcode.cn/problems/lian-biao-zhong-dao-shu-di-kge-jie-dian-lcof/solution/mian-shi-ti-22-lian-biao-zhong-dao-shu-di-kge-j-11/
     *  解题思路：
     *      第一时间想到的解法：
     *      先遍历统计链表长度，记为 n ；
     *          设置一个指针走 (n-k)步，即可找到链表倒数第 k 个节点。
     *      使用双指针则可以不用统计链表长度。
     * 算法流程：
     *      1、初始化： 前指针 former 、后指针 latter ，双指针都指向头节点 head​ 。
     *      2、构建双指针距离： 前指针 former 先向前走 k 步（结束后，双指针 former 和 latter 间相距 k 步）。
     *      3、双指针共同移动： 循环中，双指针 former 和 latter 每轮都向前走一步，直至 former 走过链表 尾节点 时跳出（跳出后， latter 与尾节点距离为 k-1，即 latter 指向倒数第 k 个节点）。
     *      4、返回值： 返回 latter 即可。
     * 复杂度分析：
     * 时间复杂度 O(N) ： N 为链表长度；总体看， former 走了 N 步， latter 走了 (N-k) 步。
     * 空间复杂度 O(1) ： 双指针 former , latter 使用常数大小的额外空间。
     * @param head
     * @param k
     * @return
     */
    //题中没有k越界的情况，因此不用考虑 k 越界的case;面试中尽量考虑k越界的情况
    //执行用时： 0 ms , 在所有 Java 提交中击败了 100.00% 的用户
    //内存消耗： 39.1 MB , 在所有 Java 提交中击败了 95.54% 的用户
    //同理： 指针1先走k-1步，然后指针2和指针1同时前进，当指针1指向链表最后一个元素时，指针2即为所求。
    public ListNode getKthFromEnd(ListNode head, int k) {
        ListNode latter = head, former = head;
        for (int i = 0; i < k; i++) {
            former = former.next;
        }
        while (former != null){
            former = former.next;
            latter = latter.next;
        }
        return latter;
    }

    /**
     * 实际上应该考虑的特殊情况有：
     *      1、head为空指针；
     *      2、k大于链表的长度；
     *      3、输入的参数k为0； 鲁棒性也是很重要的~
     *         if(head == NULL || k <= 0) //考虑特殊情况1、3
     *             return NULL;
     *         for(int i = 0;i<k;i++)
     *         {
     *             if(former == NULL) //考虑特殊情况2
     *                 return NULL;
     *             former = former->next;
     *         }
     */



    public class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }
}
