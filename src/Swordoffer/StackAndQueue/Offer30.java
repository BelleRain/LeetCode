package Swordoffer.StackAndQueue;

/**
 * @author mxy
 * @create 2022-09-16 16:28
 */

/**
 * 定义栈的数据结构，请在该类型中实现一个能够得到栈的最小元素的 min 函数在该栈中，
 * 调用 min、push 及 pop 的时间复杂度都是 O(1)。
 * 注意：与测试用例结果相同
 */

/**
 * 总结：用数组模拟栈，在速度和内存方面都比java的API优一些
 * 在内存允许的条件下，优先选用数组模拟栈
 */
public class Offer30 {

    //测试
    public static void main(String[] args) {
        Offer30 offer30 = new Offer30();
        offer30.push(8);
        //offer30.showStack();
        offer30.push(3);
        //offer30.showStack();
        offer30.push(5);
        //offer30.showStack();
        offer30.push(1);
        //offer30.showStack();
        System.out.println("min = " + offer30.min());
        //offer30.pop();
        //offer30.showStack();
        //offer30.min();
        //System.out.println("min = " + offer30.min());
    }

    /**
     * 解法一：
     * 原最小值和当前值一起入栈，出栈时一起出栈
     * 解题思路：用数组模拟栈
     * 1、申请变量，data数组，top指针，最大长度maxSize，min
     * 2、入栈，设当前值x，若 min > x，则保存原最小值，将原最小值 min 入栈，data[++top] = min
     * 令 min = x，其后将 当前值x入栈 data[++top] = x；否则，直接将当前值入栈。
     * 这个过程相当于 一次移动两次指针，两个元素入栈
     * 3、查询最小值时，直接返回 min
     * 4、出栈，如果 栈顶等于 min，表明当前栈中的最小值出栈，则拿到原最小值，
     * 并将原最小值出栈 min = data[--top] 。最后移动指针 --top；
     * 这个过程相当于 一次移动两次指针，两个元素出栈
     *
     * 过程：
     * 2147483647 8
     * 2147483647 8 8 3
     * 2147483647 8 8 3 5
     * 2147483647 8 8 3 5 3 1
     * min = 1
     * 2147483647 8 8 3 5
     * min = 3
     *
     * 执行用时：12 ms, 在所有 Java 提交中击败了97.94%的用户
     * 内存消耗：43 MB, 在所有 Java 提交中击败了98.89%的用户
     */

    //private int[] data; //栈数据
    //private int maxSize; //最大长度
    //private int top; //栈顶指针
    //private int min;  //最小值
    //
    //public Offer30() {
    //   //设置默认值
    //    maxSize = 10000;
    //    data = new int[maxSize];
    //    top = -1;
    //    min = Integer.MAX_VALUE;
    //}
    //
    //public void push(int x) {
    //    if (min >= x){
    //        //遇到了更小的值，记录原最小值
    //        data[++top] = min; //指针指向
    //        min = x;
    //    }
    //    //当前值入栈
    //    data[++top] = x;
    //}
    //
    //public void pop() {
    //    if (min == data[top]){
    //        //拿到原最小值，并将原最小值出栈
    //        min = data[--top];
    //    }
    //    --top;
    //}
    //
    //public int top() {
    //    return data[top];
    //}
    //
    //public int min() {
    //    return min;
    //}
    //
    //public void showStack(){
    //    for (int i = 0; i <= top; i++) {
    //        System.out.print(data[i] + " ");
    //    }
    //    System.out.println();
    //}
    /**
     * 解法二：运行时间12ms，但消耗内存大， 43.8MB
     * 解题思路：
     * 1、申请两个栈空间，栈B作为辅助栈，用于存放不用阶段的最小值
     * 2、入栈时，对于栈A，直接入栈；对于栈B，比较栈顶与当前值的大小，
     * 若当前值小于栈顶，则入栈
     * 3、当前栈中的最小值即为栈B的栈顶
     * 4、出栈时，A直接出栈；栈B，若栈A的栈顶等于栈B，则栈B一起出栈，
     *  表明当前栈中的最小值已出栈，否则，不出栈
     */
    //Stack<Integer> A, B;
    //public Offer30() {
    //    A = new Stack<>(); //栈A，存放值
    //    B = new Stack<>(); //栈B，辅助栈，存放不同时候的最小值
    //}
    //public void push(int x) {
    //    //当前值入栈A
    //    A.add(x);
    //    //如果栈B为空，则当前值直接入栈
    //    //如果B不为空，B的栈顶大于当前值，则当前值入栈B，否则不入栈
    //    if(B.empty() || B.peek() >= x)
    //        B.add(x);
    //}
    //public void pop() {
    //    //当A的栈顶 == B的栈顶，则A，B一起出栈
    //    //否则，仅A出栈，B不出栈
    //    if(A.pop().equals(B.peek()))
    //        B.pop();
    //}
    //public int top() {
    //    return A.peek();
    //}
    //public int min() {
    //    return B.peek();
    //}
    //public void showStack(){
    //    while (!A.empty()){
    //        System.out.print(A.pop() + " ");
    //    }
    //}

    /**
     * 解法三：
     * 执行用时：11 ms, 在所有 Java 提交中击败了100.00%的用户
     * 内存消耗：43.5 MB, 在所有 Java 提交中击败了47.82%的用户
     *
     * 解题思路：用链表模拟栈，每个节点带有该处节点的最小值的信息，查询速度快
     * 1、每个节点处保存的信息有：节点值，最小值，next
     * 2、每次查询最小值时，直接查询当前节点的最小值即可。
     *
     */

    private Node head; //定义头节点
    /** initialize your data structure here. */
    public Offer30() {

    }

    public void push(int x) {
        //如果头节点为空，则直接加入节点
        if(head==null){
            head=new Node(x,x,null);
        }else{
            //如果头节点不为空，
            //比较当前节点与前一节点处的最小值，将较小的一方赋给当前节点的最小值
            //并将当前节点置为头节点，将前一节点置为next节点
            head=new Node(x,Math.min(x,head.min),head);
        }
    }

    public void pop() {
        //移动头节点到下一节点
        head=head.next;
    }

    public int top() {
        return head.val;
    }

    public int min() {
        return head.min;
    }

    //节点
    private class Node{
        int val; //值
        int min; //最小值
        Node next; //指向下一个节点

        public Node(int val,int min, Node next){
            this.val=val;
            this.min=min;
            this.next=next;
        }
    }

}
