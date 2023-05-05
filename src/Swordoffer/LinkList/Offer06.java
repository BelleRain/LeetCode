package Swordoffer.LinkList;

/**
 * @author mxy
 * @create 2022-09-17 10:11
 */


/**
 * 逆向打印链表：
 * 输入一个链表的头节点，从尾到头反过来返回每个节点的值（用数组返回）。
 * 输入：head = [1,3,2]
 * 输出：[2,3,1]
 */
public class Offer06 {

    /**
     * 解法一：2ms，运行速度慢 41.8MB
     * @param head
     * @return
     */
    //public int[] reversePrint(ListNode head) {
    //    int i = 0;
    //    //申请一个栈空间
    //    Stack<Integer> stack = new Stack<>();
    //    //未到达最后一个节点
    //    while (head != null){
    //        //入栈
    //        stack.push(head.val);
    //        head = head.next;
    //    }
    //    int[] data = new int[stack.size()];
    //    //若栈不为空
    //    while (!stack.empty()){
    //        //出栈
    //        data[i++] = stack.pop();
    //    }
    //
    //    return data;
    //}

    /**
     * 解法二：0ms  倒序填充数组
     * 1、正向遍历链表，统计链表长度
     * 2、正向遍历链表，反向放入数组中
     * @param head
     * @return
     */
    //public int[] reversePrint(ListNode head) {
    //    ListNode a= new ListNode();
    //    a=head;
    //    int count=0;
    //    while(a!=null){
    //        count++;
    //        a=a.next;
    //    }
    //    int[] b=new int[count];
    //    for(int j=count-1;j>=0;j--){
    //        b[j]=head.val;
    //        head=head.next;
    //    }
    //    return b;
    //}

    /**
     * 解法三：辅助栈法， 1ms，41.8MB
     *
     */
    //public int[] reversePrint(ListNode head) {
    //    LinkedList<Integer> stack = new LinkedList<Integer>();
    //    while(head != null) {
    //        stack.addLast(head.val);
    //        head = head.next;
    //    }
    //    int[] res = new int[stack.size()];
    //    for(int i = 0; i < res.length; i++)
    //        res[i] = stack.removeLast();
    //    return res;
    //}

    /**
     * 解法四： 递归思想
     * 执行用时： 0 ms , 在所有 Java 提交中击败了 100.00% 的用户
     * 内存消耗： 40.6 MB , 在所有 Java 提交中击败了 99.95% 的用户
     * 解题思想：
     * 1、运用递归，正向遍历 在 node == null 之前，得到链表的长度。在最后一层，创建 len 长度的数组
     * 2、之后逐层回溯，每回溯一次，向数组中添加对应元素
     * 3、总结：正向递归，反向回溯
     */
    //private int len;
    //private int index;
    //private int[] res;
    //
    //public int[] reversePrint(ListNode head) {
    //    int[] result = traversal(head);
    //    System.gc();
    //    return result;
    //}
    //
    ///**
    // *  //len= 3 x= 3  x = ++len;
    // *  //len= 3 y= 2  y = len++;
    // *  ++len 和 len++ 无论是否赋值，所得结果相同，即为 len = len + 1
    // *  在赋值时，++len，将加 1 后的值 赋给 x，即先 加1 后 赋值x
    // *  len++, 将加 1 前的值 赋给 y，即先 赋值 后 加1
    // *  前者的 ++len 效率较高些
    // * @param node
    // * @return
    // */
    //public int[] traversal(ListNode node){
    //    if(node == null){
    //        return res = new int[len];
    //    }
    //    len++;
    //    traversal(node.next);
    //    res[index++] = node.val;
    //    return res;
    //}


    /**
     * 解法五：
     *执行用时： 0 ms , 在所有 Java 提交中击败了 100.00% 的用户
     * 内存消耗： 41.1 MB , 在所有 Java 提交中击败了 99.90% 的用户
     *解题思路：
     * 1、正向遍历链表，将其按序放在数组array中
     * 2、倒序遍历数组array，将其倒序填充在result数组中
     */
    public int[] reversePrint(ListNode head) {
        if(head == null)
            return new int[0];
        int[] array = new int[10000];
        array[0] = head.val;
        int i = 1;
        while(head.next != null){
            array[i] = head.next.val;
            head = head.next;
            i++;
        }
        int[] result = new int[i];
        for(int j=0;j < i; j++){
            result[j] = array[i-1-j];
        }
        return result;
    }
}

class ListNode{
    int val;
    ListNode next;

    public ListNode() {
    }

    //构造器
    public ListNode(int x){
        val = x;
    }
}
