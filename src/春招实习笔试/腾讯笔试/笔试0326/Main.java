package 春招实习笔试.腾讯笔试.笔试0326;

/**
 * @author mxy
 * @create 2023-03-26 18:38
 */
public class Main {

    public static void main(String[] args) {

    }

    public ListNode reorderList (ListNode head) {
        if (head.next == null || head.next.next == null) return head;
        ListNode head1 = new ListNode();
        ListNode head2 = new ListNode();
        ListNode slow = head;
        ListNode fast = slow.next.next;
        ListNode newNode = head;
        while (fast != null){
            insert(head1, slow);
            slow = slow.next;
            insert(head1, slow);
            slow.next = null;
            insert(head2, fast);
            newNode = merge(head1, head2);
            fast = fast.next;
            if (fast == null) break;
            insert(head2, fast);
            if (fast.next == null) break;
            slow = fast.next;
            fast = slow.next.next;
            head1.next = null;
            head2.next = null;
        }
        return newNode;
    }

    public void insert(ListNode head,ListNode node){
        ListNode cur = head;
        while (cur != null){
            cur = cur.next;
        }
        cur.next = node;
    }

    public ListNode merge(ListNode head1,ListNode head2){
        ListNode cur1 = head1.next;
        ListNode cur2 = head2.next;
        while (cur2.next != null){
            cur2 = cur2.next;
        }
        cur2.next = cur1;
        return head2.next;
    }




    public class ListNode {
        int val;
        ListNode next = null;
    }
}
