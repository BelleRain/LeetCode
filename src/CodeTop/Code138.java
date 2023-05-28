package CodeTop;

/**
 * @author mxy
 * @create 2023-05-26 19:58
 */

import java.util.HashMap;
import java.util.Map;

/**
 * 138. 复制带随机指针的链表     链接：https://leetcode.cn/problems/copy-list-with-random-pointer
 * 给你一个长度为 n 的链表，每个节点包含一个额外增加的随机指针 random ，该指针可以指向链表中的任何节点或空节点。
 * 构造这个链表的 深拷贝。 深拷贝应该正好由 n 个 全新 节点组成，其中每个新节点的值都设为其对应的原节点的值。
 * 新节点的 next 指针和 random 指针也都应指向复制链表中的新节点，并使原链表和复制链表中的这些指针能够表示相同的链表状态。
 * 复制链表中的指针都不应指向原链表中的节点 。
 * 例如，如果原链表中有 X 和 Y 两个节点，其中 X.random --> Y 。那么在复制链表中对应的两个节点 x 和 y ，同样有 x.random --> y 。
 * 返回复制链表的头节点。
 * 用一个由 n 个节点组成的链表来表示输入/输出中的链表。每个节点用一个 [val, random_index] 表示：
 * val：一个表示 Node.val 的整数。
 * random_index：随机指针指向的节点索引（范围从 0 到 n-1）；如果不指向任何节点，则为  null 。
 * 你的代码 只 接受原链表的头节点 head 作为传入参数。
 *
 * 示例 1：
 * 输入：head = [[7,null],[13,0],[11,4],[10,2],[1,0]]
 * 输出：[[7,null],[13,0],[11,4],[10,2],[1,0]]
 *
 * 示例 2：
 * 输入：head = [[1,1],[2,1]]
 * 输出：[[1,1],[2,1]]
 *
 * 示例 3：
 * 输入：head = [[3,null],[3,0],[3,null]]
 * 输出：[[3,null],[3,0],[3,null]]
 *
 * 提示：
 * 0 <= n <= 1000
 * -10^4 <= Node.val <= 10^4
 * Node.random 为 null 或指向链表中的节点。
 *
 */
public class Code138 {

    public static void main(String[] args) {

    }

    /**
     * 题解链接：https://leetcode.cn/problems/copy-list-with-random-pointer/solution/liang-chong-shi-xian-tu-jie-138-fu-zhi-dai-sui-ji-/
     */

    /**
     * 解法一：详细流程见原文图解
     *     1 -> 2 -> 3
     *     (节点 1 的随机指针指向节点 3 ，节点 3 的随机指针指向节点 2，节点 2 的随机指针指向空)
     *  三步走解决问题：
     *      第一步 ，根据遍历到的原节点创建对应的新节点，每个新创建的节点是在原节点后面，比如原节点1不再指向原节点2，而是指向新节点1
     *      第二步，设置新链表的随机指针
     *              原节点 1 的随机指针指向原节点 3，新节点 1 的随机指针指向的是原节点 3 的 next
     *              原节点 3 的随机指针指向原节点 2，新节点 3 的随机指针指向的是原节点 2 的 next
     *         也就是说，原节点 i 的随机指针（如果有的话），指向的是原节点 j
     *         那么新节点 i 的随机指针，指向的是原节点 j 的 next。
     *      第三步，将两个链表分离开，再返回新链表。
     * @param head
     * @return
     */
    public static Node copyRandomList1(Node head) {
        if (head == null) return null;
        Node p = head;
        //第一步，在每个原节点后面创建一个新节点
        //1 -> 1' -> 2 -> 2' -> 3 -> 3'
        while (p != null){
            Node newNode = new Node(p.val);
            newNode.next = p.next;
            p.next = newNode;
            p = newNode.next;
        }
        p = head;
        //第二步，设置新节点的随机节点
        while (p != null){
            if (p.random != null){
                p.next.random = p.random.next;
            }
            p = p.next.next;
        }
        Node dummy = new Node(-1);
        p = head;
        Node cur = dummy;
        //第三步，将两个链表分离
        while (p != null){
            cur.next = p.next;
            cur = cur.next;
            p.next = cur.next;
            p = p.next;
        }
        return dummy.next;
    }


    /**
     * 解法二：用哈希表来解决这个问题
     *   第一步： 首先创建一个哈希表，再遍历原链表，遍历的同时再不断创建新节点
     *   将原点作为 key ，新节点作为 value 放入哈希表中
     *   第二步：我们再遍历原链表，这次我们要将新链表的 next 和 random 指针设置上
     *      原节点和新节点为 一一对应的关系，所以
     *          map.get(原节点)，得到的就是对应的新节点
     *          map.get(原节点.next)，得到的就是对应的新节点.next
     *          map.get(原节点.random)，得到的就是新节点.random
     *       故，只需要再次遍历原链表，然后设置：
     *        新节点.next -> map.get(原节点.next)
     *        新节点.random -> map.get(原节点.random)
     *        这样新链表的next 和 random都被串联起来了
     *       最后，我们然后 map.get(head)，也就是对应的新链表的头节点。
     * @param head
     * @return
     */
    public static Node copyRandomList2(Node head) {
        if (head == null){
            return null;
        }
        //创建一个哈希表，key是原节点，value是新节点
        Map<Node, Node> map = new HashMap<>();
        Node p = head;
        //将原节点和新节点放入哈希表中
        while (p != null){
            Node newNode = new Node(p.val);
            map.put(p, newNode);
            p = p.next;
        }
        p = head;
        //遍历原链表，设置新节点的 next 和 random
        while (p != null){
            Node newNode = map.get(p);
            //p 是原节点，map.get(p)是对应的新节点，p.next 是原节点的下一个
            //map.get(p.next)是原节点下一个对应的新节点
            if (p.next != null){
                newNode.next = map.get(p.next);
            }
            //p.random 是原节点随机指向
            //map.get(p.random) 是原节点随机指向 对应的新节点
            if (p.random != null){
                newNode.random = map.get(p.random);
            }
            p = p.next;
        }
        //返回头节点，即原节点对应的value(新节点)
        return map.get(head);
    }

    public static class Node {
        int val;
        Node next;
        Node random;

        public Node(int val) {
            this.val = val;
            this.next = null;
            this.random = null;
        }
    }
}












































