package Top100;

/**
 * @author mxy
 * @create 2022-11-29 9:39
 */

import java.util.List;

/**
 * 148. 排序链表  (重点，字节面试题，空间复杂度为 O(1))  链接：https://leetcode.cn/problems/sort-list
 * 给你链表的头结点 head ，请将其按 升序 排列并返回 排序后的链表 。
 *
 * 示例 1：
 * 输入：head = [4,2,1,3]
 * 输出：[1,2,3,4]
 *
 * 示例 2：
 * 输入：head = [-1,5,3,4,0]
 * 输出：[-1,0,3,4,5]
 *
 * 示例 3：
 * 输入：head = []
 * 输出：[]
 *  
 * 提示：
 * 链表中节点的数目在范围 [0, 5 * 10^4] 内
 * -10^5 <= Node.val <= 10^5
 *  
 * 进阶：你可以在 O(n log n) 时间复杂度和常数级空间复杂度下，对链表进行排序吗？
 */
public class Top148 {

    /**
     * 题解一：https://leetcode.cn/problems/sort-list/solution/sort-list-gui-bing-pai-xu-lian-biao-by-jyd/
     */

    /**
     * 方法一：归并排序（递归法）
     * @param head
     * @return
     */
    public ListNode sortList1(ListNode head) {
        if (head == null || head.next == null) return head;

        //下述写法同：
        //ListNode fast = head, slow = head;
        ////分的过程：
        //while (fast.next != null && fast.next.next != null){
        //    slow = slow.next;
        //    fast = fast.next.next;
        //}

        ListNode fast = head.next, slow = head;
        //分的过程：
        while (fast != null && fast.next != null){
            slow = slow.next;
            fast = fast.next.next;
        }
        ListNode tmp = slow.next;
        slow.next = null;
        ListNode left = sortList1(head);
        ListNode right = sortList1(tmp);
        ListNode h = new ListNode(0);
        ListNode res = h;
        //合并过程：
        while (left != null && right != null){
            if (left.val < right.val){
                h.next = left;
                left = left.next;
            }else{
                h.next = right;
                right = right.next;
            }
            h = h.next;
        }
        h.next = left != null ? left : right;
        return res.next;
    }

    /**
     * 写法二：可读性较好
     *         ListNode slow = head;
     *         ListNode fast = head.next.next;
     *
     *         while (fast != null && fast.next != null) {
     *             slow = slow.next;
     *             fast = fast.next.next;
     *         }
     *    fast 先走一步，节点数目
     *       若为偶数，返回第一个中间节点，从而 1,2 （只有两个元素）这种情况可以分开为[1][2]，不会出现栈溢出的情况
     *       若为奇数，则返回中间节点的 前一个节点 例，1,2,3,4,5，最终 slow 指向 2 ，分的数列为[1,2] [3,4,5]，符合题意，可以向下递归
     *       所以对于奇偶而言皆符合条件
     *
     *         ListNode fast = head, slow = head;
     *         while (fast.next != null && fast.next.next != null){
     *             slow = slow.next;
     *             fast = fast.next.next;
     *         }
     *  若分的过程 从同一个起点出发：
     *     1）对于偶数节点来说，返回第一个中间节点，从而 1,2 （只有两个元素）这种情况可以分开为[1][2]，不会出现栈溢出的情况
     *     2) 对于奇数节点来说，返回中间节点，例 1,2,3,4,5， slow最终指向 3，分为 [1,2,3] 和 [4,5],向下递归符合条件
     *   故：在寻找中间节点的过程中，两种写法编译均可通过 ，只是分片的初始节点不同，但符合归并排序的分片。
     *
     *   第二种：从一个节点出发，奇数不影响，返回中间节点；此种代码错误，对于偶数不合题意
     *          偶数返回 中间节点的后一个节点，则 对于 1,2 (只有两个元素) 不会分开，一直为[1,2] ,则会一直递归，出现栈溢出
     *       ListNode fast = head, slow = head;
     *         while (fast != null && fast.next != null){
     *             slow = slow.next;
     *             fast = fast.next.next;
     *         }
     *   正确：可以写为上述代码中的形式：
     *          ListNode fast = head.next, slow = head;
     *         //分的过程：
     *         while (fast != null && fast.next != null){
     *             slow = slow.next;
     *             fast = fast.next.next;
     *         }
     * @param head
     * @return
     */
    public ListNode sortList2(ListNode head) {
        // 1、递归结束条件
        if (head == null || head.next == null) {
            return head;
        }

        // 2、找到链表中间节点并断开链表 & 递归下探
        ListNode midNode = middleNode(head);
        ListNode rightHead = midNode.next;
        midNode.next = null;

        ListNode left = sortList2(head);
        ListNode right = sortList2(rightHead);

        // 3、当前层业务操作（合并有序链表）
        return mergeTwoLists(left, right);
    }

    //  找到链表中间节点（876. 链表的中间结点）
    private ListNode middleNode(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        //fast 先走一步，节点数目
        //若为偶数，返回第一个中间节点，从而 1,2 （只有两个元素）这种情况可以分开为[1][2]，不会出现栈溢出的情况
        //若为奇数，则返回中间节点的 前一个节点 例，1,2,3,4,5，最终 slow 指向 2 ，分的数列为[1,2] [3,4,5]，符合题意，可以向下递归
        //所以对于奇偶而言皆符合条件
        ListNode slow = head;
        ListNode fast = head.next.next;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        return slow;
    }

    // 合并两个有序链表（21. 合并两个有序链表）
    private ListNode mergeTwoLists(ListNode l1, ListNode l2) {
        ListNode sentry = new ListNode(-1);
        ListNode curr = sentry;

        while(l1 != null && l2 != null) {
            if(l1.val < l2.val) {
                curr.next = l1;
                l1 = l1.next;
            } else {
                curr.next = l2;
                l2 = l2.next;
            }

            curr = curr.next;
        }

        curr.next = l1 != null ? l1 : l2;
        return sentry.next;
    }

    /**
     * 题解链接： https://leetcode.cn/problems/sort-list/solution/pai-xu-lian-biao-by-leetcode-solution/
     * 方法二：从底至顶直接合并（迭代实现）
     * 空间复杂度： O(1)
     * @param head
     * @return
     */
    // 自底向上归并排序
    public ListNode sortList3(ListNode head) {
        if(head == null){
            return head;
        }

        // 1. 首先从头向后遍历,统计链表长度
        int length = 0; // 用于统计链表长度
        ListNode node = head;
        while(node != null){
            length++;
            node = node.next;
        }

        // 2. 初始化 引入dummynode
        ListNode dummyHead = new ListNode(0);
        dummyHead.next = head;

        // 3. 每次将链表拆分成若干个长度为subLen的子链表 , 并按照每两个子链表一组进行合并
        for(int subLen = 1;subLen < length;subLen <<= 1){ // subLen每次左移一位（即sublen = sublen*2） PS:位运算对CPU来说效率更高
            ListNode prev = dummyHead;
            ListNode curr = dummyHead.next;     // curr用于记录拆分链表的位置

            //边拆分，边合并
            while(curr != null){               // 如果链表没有被拆完
                // 3.1 拆分subLen长度的链表1
                ListNode head_1 = curr;        // 第一个链表的头 即 curr初始的位置
                for(int i = 1; i < subLen && curr != null && curr.next != null; i++){     // 拆分出长度为subLen的链表1
                    curr = curr.next;
                }

                // 3.2 拆分subLen长度的链表2
                ListNode head_2 = curr.next;  // 第二个链表的头  即 链表1尾部的下一个位置
                curr.next = null;             // 断开第一个链表和第二个链表的链接
                curr = head_2;                // 第二个链表头 重新赋值给curr
                for(int i = 1;i < subLen && curr != null && curr.next != null;i++){      // 再拆分出长度为subLen的链表2
                    curr = curr.next;
                }

                // 3.3 再次断开 第二个链表最后的next的链接
                ListNode next = null;
                if(curr != null){
                    next = curr.next;   // next用于记录 拆分完两个链表的结束位置
                    curr.next = null;   // 断开连接
                }

                // 3.4 合并两个subLen长度的有序链表
                ListNode merged = merge(head_1,head_2);
                prev.next = merged;        // prev.next 指向排好序链表的头
                while(prev.next != null){  // while循环 将prev移动到 subLen*2 的位置后去
                    prev = prev.next;     //prev用于连接拆分的排好序的链表
                }
                curr = next;              // next用于记录 拆分完两个链表的结束位置
            }
        }
        // 返回新排好序的链表
        return dummyHead.next;
    }

    private ListNode merge(ListNode l1, ListNode l2) {
        ListNode sentry = new ListNode(-1);
        ListNode curr = sentry;

        while(l1 != null && l2 != null) {
            if(l1.val < l2.val) {
                curr.next = l1;
                l1 = l1.next;
            } else {
                curr.next = l2;
                l2 = l2.next;
            }

            curr = curr.next;
        }

        curr.next = l1 != null ? l1 : l2;
        return sentry.next;
    }


    /**
     * 桶排序：
     * @param head
     * @return
     */
    public ListNode sortList4(ListNode head) {
        if(head==null || head.next ==null){
            return head;
        }
        int minVal = head.val;
        int maxVal = head.val;
        ListNode node = head.next;
        while(node!=null){
            if(node.val>maxVal){
                maxVal=node.val;
            }
            if(node.val<minVal){
                minVal=node.val;
            }
            node =node.next;
        }
        int count[] = new int[maxVal-minVal+1];
        node =head;
        while(node !=null){
            count[node.val-minVal]++;
            node=node.next;
        }
        node =head;
        for(int i=0;i<count.length;i++){
            while(count[i]>0){
                node.val=i+minVal;
                node=node.next;
                count[i]--;
            }
        }
        return head;
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
