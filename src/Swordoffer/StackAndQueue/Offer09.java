package Swordoffer.StackAndQueue;


import java.util.Deque;
import java.util.LinkedList;

/**
 * @author mxy
 * @create 2022-09-12 9:00
 */

/**
 * 链接：https://leetcode.cn/problems/yong-liang-ge-zhan-shi-xian-dui-lie-lcof
 * 问题描述：用两个栈实现一个队列。队列的声明如下，请实现它的两个函数 appendTail 和 deleteHead ，
 * 分别完成在队列尾部插入整数和在队列头部删除整数的功能。(若队列中没有元素，deleteHead 操作返回 -1 )
 *
 * 示例 1：
 *
 * 输入：
 * ["CQueue","appendTail","deleteHead","deleteHead","deleteHead"]
 * [[],[3],[],[],[]]
 * 输出：[null,null,3,-1,-1]
 * 示例 2：
 *
 * 输入：
 * ["CQueue","deleteHead","appendTail","appendTail","deleteHead","deleteHead"]
 * [[],[],[5],[2],[],[]]
 * 输出：[null,-1,null,null,5,2]
 * 提示：
 *
 * 1 <= values <= 10000
 * 最多会对 appendTail、deleteHead 进行 10000 次调用
 *
 */
public class Offer09 {

    //声明两个栈，将给定的数列，先入栈1.后入栈2，再出栈2
    //效果：1234 ==》4321 ==》1234
    /**
     instack负责入栈，向队尾添加元素；
     outstack负责出栈，从队头删除元素
     1.当outstack不为空时，直接出栈；
     2.当outstack为空时，
        1）若instack为空，则return -1；
        2）若instack不为空，则将instack中的元素放入outstack中，然后outstack出栈
     */
    //55ms
    //private Stack<Integer> inStack; //inStack负责入栈
    //private Stack<Integer> outStack; //outStack负责出栈
    //
    //public Offer09() {
    //    inStack = new Stack();
    //    outStack = new Stack();
    //}
    //
    //public void appendTail(int value) {
    //    inStack.push(value);
    //}
    //
    //public int deleteHead() {
    //    if(!outStack.empty()){
    //        return outStack.pop();
    //    }
    //    if(inStack.empty()){
    //        return -1;
    //    }
    //    while(!inStack.empty()){
    //        outStack.push(inStack.pop());
    //    }
    //    return outStack.pop();
    //}


    //35 ms,
    // Deque<Integer> instack;
    // Deque<Integer> outstack;
    // public Offer09() {
    //     instack=new ArrayDeque<Integer>();
    //     outstack=new ArrayDeque<Integer>();
    // }
    //
    // public void appendTail(int value) {
    //     instack.addFirst(value);
    // }
    //
    // public int deleteHead() {
    //     if(outstack.peekFirst()!=null)
    //     {
    //         return outstack.pollFirst();
    //     }
    //     else if(instack.peekFirst()!=null)
    //     {
    //         while(instack.peekFirst()!=null)
    //         {
    //             outstack.addFirst(instack.pollFirst());
    //         }
    //
    //         return outstack.pollFirst();
    //     }
    //     else{
    //         return -1;
    //     }
    // }

    Deque<Integer> inDeque;
    Deque<Integer> outDeque;

    public Offer09(){
        inDeque = new LinkedList<>();
        outDeque = new LinkedList<>();
    }

    public void appendTail(int value){
        inDeque.addFirst(value);
    }

    public int deleteHead(){
        if (!outDeque.isEmpty()){
            return outDeque.removeFirst();
        }
        if (inDeque.isEmpty()){
            return -1;
        }
        while (!inDeque.isEmpty()){
            outDeque.addFirst(inDeque.removeFirst());
        }
        return outDeque.removeFirst();
    }




    //45ms
    // Deque<Integer> inStack;
    // Deque<Integer> outStack;

    // public Offer09() {
    //     inStack = new ArrayDeque<Integer>();
    //     outStack = new ArrayDeque<Integer>();
    // }

    // public void appendTail(int value) {
    //     inStack.push(value);
    // }

    // public int deleteHead() {
    //     if (outStack.isEmpty()) {
    //         if (inStack.isEmpty()) {
    //             return -1;
    //         }
    //         in2out();
    //     }
    //     return outStack.pop();
    // }

    // private void in2out() {
    //     while (!inStack.isEmpty()) {
    //         outStack.push(inStack.pop());
    //     }
    // }

    //32ms
    // int []number=new int [10010];
    // int start=0;
    // int end=0;
    // public Offer09() {

    // }
    // public void appendTail(int value) {
    //     number[end]=value;
    //     end++;
    // }
    // public int deleteHead() {
    //     if(start!=end){
    //         int number1=number[start];
    //     start++;
    //     return number1;
    //     }else{
    //         return -1;
    //     }

    // }

    //35ms
    // Deque<Integer> queue;
    // public Offer09() {
    //     queue = new ArrayDeque<>();
    // }

    // public void appendTail(int value) {
    //     queue.add(value);
    // }

    // public int deleteHead() {
    //     if(queue.size()!=0){
    //         return queue.poll();
    //     }else{
    //         return -1;
    //     }
    // }
}
