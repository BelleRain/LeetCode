package Swordoffer.DoublePointer;

/**
 * @author mxy
 * @create 2022-09-27 10:47
 */

/**
 * 两个链表的第一个公共节点
 * 输入两个链表，找出它们的第一个公共节点。
 *
 * 示例1：
 * 输入：intersectVal = 8, listA = [4,1,8,4,5], listB = [5,0,1,8,4,5], skipA = 2, skipB = 3
 * 输出：Reference of the node with value = 8
 * 输入解释：相交节点的值为 8 （注意，如果两个列表相交则不能为 0）。从各自的表头开始算起，链表 A 为 [4,1,8,4,5]，链表 B 为 [5,0,1,8,4,5]。在 A 中，相交节点前有 2 个节点；在 B 中，相交节点前有 3 个节点。
 *
 * 示例 2：
 * 输入：intersectVal = 2, listA = [0,9,1,2,4], listB = [3,2,4], skipA = 3, skipB = 1
 * 输出：Reference of the node with value = 2
 * 输入解释：相交节点的值为 2 （注意，如果两个列表相交则不能为 0）。从各自的表头开始算起，链表 A 为 [0,9,1,2,4]，链表 B 为 [3,2,4]。在 A 中，相交节点前有 3 个节点；在 B 中，相交节点前有 1 个节点。
 *
 * 示例 3：
 * 输入：intersectVal = 0, listA = [2,6,4], listB = [1,5], skipA = 3, skipB = 2
 * 输出：null
 * 输入解释：从各自的表头开始算起，链表 A 为 [2,6,4]，链表 B 为 [1,5]。由于这两个链表不相交，所以 intersectVal 必须为 0，而 skipA 和 skipB 可以是任意值。
 * 解释：这两个链表不相交，因此返回 null。
 *
 * 注意：
 *   1、如果两个链表没有交点，返回 null.
 *   2、在返回结果后，两个链表仍须保持原有的结构。
 *   3、可假定整个链表结构中没有循环。
 *   4、程序尽量满足 O(n) 时间复杂度，且仅用 O(1) 内存。
 */
public class Offer52 {

    /**
     * 关键点： 比较的是 节点的地址 而不是 节点的值
     */

    /**
     * 题解一： 双指针法 原文链接：https://leetcode.cn/problems/liang-ge-lian-biao-de-di-yi-ge-gong-gong-jie-dian-lcof/solution/yi-zhang-tu-jiu-ming-bai-ai-qing-jie-shi-up3a/
     * K神解答：https://leetcode.cn/problems/liang-ge-lian-biao-de-di-yi-ge-gong-gong-jie-dian-lcof/solution/jian-zhi-offer-52-liang-ge-lian-biao-de-gcruu/
     * 详细说明见原文链接
     *  思路：
     *      1、使用两个指针 node1，node2 分别指向两个链表 headA，headB 的头结点，然后同时分别逐结点遍历，
     *      2、当 node1 到达链表 headA 的末尾时，重新定位到链表 headB 的头结点；
     *      3、当 node2 到达链表 headB 的末尾时，重新定位到链表 headA 的头结点。
     * 理解：两个链表长度分别为L1+C、L2+C， C为公共部分的长度，按照楼主的做法： 第一个人走了L1+C步后，回到第二个人起点走L2步；
     *      第2个人走了L2+C步后，回到第一个人起点走L1步。 当两个人走的步数都为L1+L2+C时相遇
     *节点逻辑上的拼接： 注意：地址相同，不是值相同
     *  举例：1、listA = [4,1,8,4,5], listB = [5,0,1,8,4,5]
     *  node1: 4—>1—>8—>4—>5—>5—>0—>1—>8—>4—>5
     *  node2: 5—>0—>1—>8—>4—>5—>4—>1—>8—>4—>5
     *  2、没有公共节点 listA = [2,6,4], listB = [1,5]，null充当公共节点
     *  node1: 2—>6—>4—>1—>5->null
     *  node2: 1—>5—>2—>6—>4—>null
     *
     * 复杂度分析：
     *     时间复杂度：O(m+n)，其中 m 和 n 是分别是链表 headA 和 headB 的长度。两个指针同时遍历两个链表，每个指针遍历两个链表各一次。
     *     空间复杂度：O(1)。
     * @param headA
     * @param headB
     * @return
     */
    //当没有公共节点时，公共节点为 null，最终也会跳出循环，而不会陷入死循环
    ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        ListNode node1 = headA , node2 = headB;
        while (node1!=node2){
            node1 = node1 == null ? headB : node1.next;
            node2 = node2 == null ? headA : node2.next;
        }
        return node1;
    }

    /**
     * 方法二：哈希集合 原文链接：https://leetcode.cn/problems/liang-ge-lian-biao-de-di-yi-ge-gong-gong-jie-dian-lcof/solution/liang-ge-lian-biao-de-di-yi-ge-gong-gong-pzbs/
     * 思路和算法:
     *    判断两个链表是否相交，可以使用哈希集合存储链表节点。
     *   首先遍历链表 headA，并将链表 headA 中的每个节点加入哈希集合中。
     *   然后遍历链表headB，对于遍历到的每个节点，判断该节点是否在哈希集合中：
     *   如果当前节点不在哈希集合中，则继续遍历下一个节点；
     *   如果当前节点在哈希集合中，则后面的节点都在哈希集合中，即从当前节点开始的所有节点都是两个链表的公共节点，
     *   因此在链表 headB 中遍历到的第一个在哈希集合中的节点就是两个链表的第一个公共节点，返回该节点。
     *  如果链表 headB 中的所有节点都不在哈希集合中，则两个链表不相交，返回 null。
     *
     *  复杂度分析:
     *     时间复杂度：O(m+n)，其中 m 和 n 是分别是链表 headA 和 headB 的长度。需要遍历两个链表各一次。
     *     空间复杂度：O(m)，其中 m 是链表 headA 的长度。需要使用哈希集合存储链表 headA 中的全部节点。
     */
    //public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
    //    Set<ListNode> visited = new HashSet<ListNode>();
    //    ListNode temp = headA;
    //    while (temp != null) {
    //        visited.add(temp);
    //        temp = temp.next;
    //    }
    //    temp = headB;
    //    while (temp != null) {
    //        if (visited.contains(temp)) {
    //            return temp;
    //        }
    //        temp = temp.next;
    //    }
    //    return null;
    //}

    //ListNode getIntersectionNode(ListNode headA, ListNode headB) {
    //   if (headA == null || headB == null) return null;
    //    HashMap<ListNode, ListNode> map = new HashMap<>();
    //    while ( headA != null){
    //       map.put(headA, headA);
    //       headA = headA.next;
    //   }
    //   while (headB != null){
    //       if (map.containsKey(headB)){
    //           return headB;
    //       }
    //       headB = headB.next;
    //   }
    //   return headB;
    //}






    public class ListNode {
        int val;
        ListNode next;
        ListNode(int x) { val = x; }
    }

}






































