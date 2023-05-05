package CodeTop;

import java.util.PriorityQueue;

/**
 * @author mxy
 * @create 2023-04-17 9:36
 */
public class Code_test {

    //堆
    /*public static ListNode mergeKLists(ListNode[] lists) {
        if (lists.length == 0) return null;
        PriorityQueue<ListNode> queue = new PriorityQueue<>((o1, o2) -> o1.val - o2.val);
        for (ListNode list : lists) {
            if (list != null){
                queue.add(list);
            }
        }
        ListNode dummy = new ListNode(0);
        ListNode tail = dummy;
        while (!queue.isEmpty()){
            ListNode minNode = queue.poll();
            tail.next = minNode;
            tail = minNode;
            if (minNode.next != null) queue.add(minNode.next);
        }
        return dummy.next;
    }*/

    //利用K指针
    /*public static ListNode mergeKLists(ListNode[] lists) {
        if (lists.length == 0) return null;
        ListNode dummy = new ListNode(0);
        ListNode tail = dummy;
        int k = lists.length;
        while (true){
            ListNode minNode = null;
            int min = -1;
            for (int i = 0; i < k; i++) {
                if (lists[i] == null) continue;
                if (minNode == null || lists[i].val < minNode.val){
                    minNode = lists[i];
                    min = i;
                }
            }
            if (min == -1) break; //合并完毕
            tail.next = minNode;
            tail = minNode;
            lists[min] = lists[min].next;
        }
        return dummy.next;
    }*/

    //两两合并：递归
    /*public static ListNode mergeKLists(ListNode[] lists) {
        if (lists.length == 0) return null;
        return merge(lists, 0, lists.length - 1);
    }

    private static ListNode merge(ListNode[] lists, int l, int r) {
        if (l == r) return lists[l];
        int mid = l + (r - l)/2;
        ListNode l1 = merge(lists, l, mid);
        ListNode l2 = merge(lists, mid + 1, r);
        return mergeRecur(l1, l2);
    }*/

    //两两合并：迭代
    public static ListNode mergeKLists(ListNode[] lists) {
        if (lists.length == 0) return null;
        int k = lists.length;
        while (k > 1){
            int idx = 0;
            for (int i = 0; i < k; i += 2) {
                if (i == k - 1){
                    lists[idx++] = lists[i];
                }else {
                    lists[idx++] = mergeIter(lists[i], lists[i + 1]);
                }
            }
            k = idx;
        }
        return lists[0];
    }


    //合并链表：递归
    private static ListNode mergeRecur(ListNode l1, ListNode l2) {
        if (l1 == null) return l2;
        if (l2 == null) return l1;
        if (l1.val < l2.val){
            l1.next = mergeRecur(l1.next, l2);
            return l1;
        }else {
            l2.next = mergeRecur(l1, l2.next);
            return l2;
        }
    }

    //合并链表：迭代
    private static ListNode mergeIter(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(0);
        ListNode cur = dummy;
        while (l1 != null && l2 != null){
            if (l1.val < l2.val){
                cur.next = l1;
                l1 = l1.next;
            }else {
                cur.next = l2;
                l2 = l2.next;
            }
            cur = cur.next;
        }
        cur.next = l1 == null ? l2 : l1;
        return dummy.next;
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
