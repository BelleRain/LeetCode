package Swordoffer.LinkList;

/**
 * @author mxy
 * @create 2022-09-17 16:48
 */

/**
 * 复杂链表的复制
 * 请实现 copyRandomList 函数，复制一个复杂链表。在复杂链表中，每个节点除了有一个 next 指针指向下一个节点，
 * 还有一个 random 指针指向链表中的任意节点或者 null。
 * 输入：head = [[7,null],[13,0],[11,4],[10,2],[1,0]]
 * 输出：[[7,null],[13,0],[11,4],[10,2],[1,0]]
 */
public class Offer35 {

    /**
     * 方法一：回溯和哈希表
     * 执行用时： 0 ms , 在所有 Java 提交中击败了 100.00% 的用户
     * 内存消耗： 41 MB , 在所有 Java 提交中击败了 60.80% 的用户
     * 解题思路：
     * 1、创建map集合，用于原链表节点与新节点对应（哈希表）
     * 2、输入为空，直接返回null
     * 3、判断新节点的创建情况，
     *    1）若未创建，则向下执行
     *      //创建新节点
     *      //放入map中与原链表节点对应
     *      //递归创建当前节点的后继节点和随机指向节点
     *    2）若已创建，则直接返回拷贝节点的指针即可。
     */
    //创建map集合，用于原链表节点与新节点对应（哈希表）
    //Map<Node, Node> cachedNode = new HashMap<Node, Node>();
    //public Node copyRandomList(Node head) {
    //    //输入为空，直接返回null
    //    if (head == null) {
    //        return null;
    //    }
    //    //判断新节点的创建情况，
    //    //若未创建，则向下执行
    //    //若已创建，则直接返回拷贝节点的指针即可。
    //    if (!cachedNode.containsKey(head)) {
    //        //创建新节点
    //        Node headNew = new Node(head.val);
    //        //放入map中与原链表节点对应
    //        cachedNode.put(head, headNew);
    //        //递归创建当前节点的后继节点和随机指向节点
    //        headNew.next = copyRandomList(head.next);
    //        headNew.random = copyRandomList(head.random);
    //    }
    //    return cachedNode.get(head);
    //}

    /**
     * 解法二：迭代 + 节点拆分  省去哈希表的空间
     * // 1. 复制各节点，并构建拼接链表
     * // 2. 构建各新节点的 random 指向
     * // 3. 拆分两链表
     */

    //public Node copyRandomList(Node head){
    //    if (head == null){
    //        return null;
    //    }
    //    //将该链表中的每一个节点拆分为两个相连的节点
    //    //例如：A->B->C，拆分为 A->A'->B->B'->C->C'
    //    for (Node node = head; node!=null;node = node.next.next){
    //        Node nodeNew = new Node(node.val);
    //        nodeNew.next = node.next;
    //        node.next = nodeNew;
    //    }
    //
    //    //对于任意一个原节点S，其拷贝节点S'即为其后继节点
    //    //判断原节点的random节点是否为null，若不为null，则令拷贝节点nodeNew的random节点指向原节点的random的下一个节点
    //    //原因：因为每一个原节点后面是其拷贝节点
    //    for (Node node = head; node != null; node = node.next.next){
    //        Node nodeNew = node.next;
    //        nodeNew.random = (node.random != null) ? node.random.next:null;
    //    }
    //
    //    //将拷贝节点在链中分离出来
    //    //A->A'->B->B'->C->C'
    //    Node headNew = head.next;
    //    for (Node node = head; node != null;node = node.next){
    //        Node nodeNew = node.next;
    //        node.next = node.next.next;
    //        nodeNew.next = (nodeNew.next != null) ? nodeNew.next.next:null;
    //    }
    //
    //    return headNew;
    //}

    public Node copyRandomList(Node head) {
        if(head == null) return null;
        Node cur = head;
        // 1. 复制各节点，并构建拼接链表
        while(cur != null) {
            Node tmp = new Node(cur.val);
            tmp.next = cur.next;
            cur.next = tmp;
            cur = tmp.next;
        }
        // 2. 构建各新节点的 random 指向
        cur = head;
        while(cur != null) {
            if(cur.random != null)
                cur.next.random = cur.random.next;
            cur = cur.next.next;
        }
        // 3. 拆分两链表
        cur = head.next;
        Node pre = head, res = head.next;
        while(cur.next != null) {
            pre.next = pre.next.next;
            cur.next = cur.next.next;
            pre = pre.next;
            cur = cur.next;
        }
        pre.next = null; // 单独处理原链表尾节点
        return res;      // 返回新链表头节点
    }

    /**
     * 方法三：哈希表
     * 解题思路：
     * 1、 复制各节点，并建立 “原节点 -> 新节点” 的 Map 映射
     * 2、 构建新链表的 next 和 random 指向
     * 3、 返回新链表的头节点
     */
    // public Node copyRandomList(Node head) {
    //     if(head == null) return null;
    //     Node cur = head;
    //     Map<Node, Node> map = new HashMap<>();
    //     // 3. 复制各节点，并建立 “原节点 -> 新节点” 的 Map 映射
    //     while(cur != null) {
    //         map.put(cur, new Node(cur.val));
    //         cur = cur.next;
    //     }
    //     cur = head;
    //     // 4. 构建新链表的 next 和 random 指向
    //     while(cur != null) {
    //         map.get(cur).next = map.get(cur.next);
    //         map.get(cur).random = map.get(cur.random);
    //         cur = cur.next;
    //     }
    //     // 5. 返回新链表的头节点
    //     return map.get(head);
    // }


}

class Node {
    int val;
    Node next;
    Node random;

    public Node(){

    }

    public Node(int val) {
        this.val = val;
        this.next = null;
        this.random = null;
    }
}