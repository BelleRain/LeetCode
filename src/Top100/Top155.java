package Top100;

/**
 * @author mxy
 * @create 2022-11-30 20:44
 */

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 155. 最小栈     链接：https://leetcode.cn/problems/min-stack
 * 设计一个支持 push ，pop ，top 操作，并能在常数时间内检索到最小元素的栈。
 * 实现 MinStack 类:
 *
 * MinStack() 初始化堆栈对象。
 * void push(int val) 将元素val推入堆栈。
 * void pop() 删除堆栈顶部的元素。
 * int top() 获取堆栈顶部的元素。
 * int getMin() 获取堆栈中的最小元素。
 *  
 *
 * 示例 1:
 * 输入：
 * ["MinStack","push","push","push","getMin","pop","top","getMin"]
 * [[],[-2],[0],[-3],[],[],[],[]]
 *
 * 输出：
 * [null,null,null,null,-3,null,0,-2]
 *
 * 解释：
 * MinStack minStack = new MinStack();
 * minStack.push(-2);
 * minStack.push(0);
 * minStack.push(-3);
 * minStack.getMin();   --> 返回 -3.
 * minStack.pop();
 * minStack.top();      --> 返回 0.
 * minStack.getMin();   --> 返回 -2.
 *  
 * 提示：
 * -2^31 <= val <= 2^31 - 1
 * pop、top 和 getMin 操作总是在 非空栈 上调用
 * push, pop, top, and getMin最多被调用 3 * 10^4 次
 *
 */
public class Top155 {

    public static void main(String[] args) {
        Top155 top155 = new Top155();
        top155.push(512);
        top155.push(-1024);
        top155.push(-1024);
        top155.push(512);

        top155.pop();
        System.out.println(top155.getMin());

        top155.pop();
        System.out.println(top155.getMin());

        top155.pop();
        System.out.println(top155.getMin());


    }

    /**
     * 题解链接：https://leetcode.cn/problems/min-stack/solution/min-stack-fu-zhu-stackfa-by-jin407891080/
     * 执行用时： 4 ms , 在所有 Java 提交中击败了 93.49% 的用户
     * 内存消耗： 43.8 MB , 在所有 Java 提交中击败了 6.12% 的用户
     */
    Deque<Integer> stackA;
    Deque<Integer> stackB; //辅助栈

    public Top155() {
        stackA = new ArrayDeque<>();
        stackB = new ArrayDeque<>();
    }

    public void push(int val) {
        stackA.push(val);
        if (stackB.isEmpty() || val <= stackB.peek()){
            stackB.push(val);
        }
    }

    /*
     在使用==比较Integer类型时，默认会缓存 -128至127（包括-128和127），
     如果超过这个范围，则会new，所以两个对象内存的首地址不一样，==返回false
     */
    public void pop() {
        if (stackA.peek().equals(stackB.peek())){
            stackB.pop();
        }
        stackA.pop();
    }

    public int top() {
        return stackA.peek();
    }

    public int getMin() {
        return stackB.peek();
    }
}
