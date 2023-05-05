package CodeTop;

/**
 * @author mxy
 * @create 2023-03-14 9:46
 */

/**
 * 反转链表
 */
public class Offer24 {

    //递归算法
    /*public ListNode reverseList(ListNode head){
        return recur(head,null);
    }

    public ListNode recur(ListNode cur,ListNode pre){
        if (cur == null) return pre;
        ListNode res = recur(cur.next, cur);
        cur.next = pre;
        return res;
    }*/


    //迭代
    public ListNode reverseList(ListNode head){
        ListNode cur = head;
        ListNode pre = null;
        while (cur != null){
            ListNode temp = cur.next;
            cur.next = pre;
            pre = cur;
            cur = temp;
        }
        return pre;
    }

    public class ListNode{
        int val;
        ListNode next;
        public ListNode(){}
        public ListNode(int val){
            this.val = val;
        }
        public ListNode(int val,ListNode next){
            this.val = val;
            this.next = next;
        }
    }
}
