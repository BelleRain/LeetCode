package Swordoffer.StackAndQueue;

/**
 * @author mxy
 * @create 2022-10-31 15:13
 */

import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 队列的最大值
 * 请定义一个队列并实现函数 max_value 得到队列里的最大值，要求函数max_value、push_back 和 pop_front 的均摊时间复杂度都是O(1)。
 * 若队列为空，pop_front 和 max_value 需要返回 -1
 *
 * 示例 1：
 * 输入:
 * ["MaxQueue","push_back","push_back","max_value","pop_front","max_value"]
 * [[],[1],[2],[],[],[]]
 * 输出: [null,null,null,2,1,2]
 *
 * 示例 2：
 * 输入:
 * ["MaxQueue","pop_front","max_value"]
 * [[],[],[]]
 * 输出: [null,-1,-1]
 *  
 * 限制：
 * 1 <= push_back,pop_front,max_value的总操作数 <= 10000
 * 1 <= value <= 10^5
 *
 */
public class Offer59_2 {

    /*
    注意：此问题不同于max栈或min栈
     */

    /**
     * 题解链接：https://leetcode.cn/problems/dui-lie-de-zui-da-zhi-lcof/solution/jian-zhi-offer-59-ii-dui-lie-de-zui-da-z-0pap/
     * 题解一：
     * 复杂度分析：
     * 时间复杂度 O(1) ： max_value(), push_back(), pop_front() 方法的均摊时间复杂度均为 O(1)；
     * 空间复杂度 O(N) ： 当元素个数为 N 时，最差情况下deque 中保存 N 个元素，使用 O(N) 的额外空间；
     */
    Queue<Integer> queue;
    Deque<Integer> deque; //辅助双端单调不增队列
    public Offer59_2() {
        queue = new LinkedList<>();
        deque = new LinkedList<>();
    }

    public int max_value() {
        return deque.isEmpty() ? -1 : deque.peekFirst();
    }

    public void push_back(int value) {
        queue.offer(value);
        //保持双端队列单调不增
        //设计双向队列为单调不增的原因：若队列queue中存在两个 值相同的最大元素，此时queue和deque同时弹出一个最大元素，
        //而queue中还有一个此最大元素；即采用单调递减将导致两队列中的元素不一致。
        while (!deque.isEmpty() && deque.peekLast() < value)
            deque.pollLast();
        deque.offerLast(value);
    }

    public int pop_front() {
        if (queue.isEmpty()) return -1;
        //注意使用equals
        if (queue.peek().equals(deque.peekFirst()))
            deque.pollFirst();
        return queue.poll();
    }


    /**
     * 题解链接：https://leetcode.cn/problems/dui-lie-de-zui-da-zhi-lcof/solution/mian-shi-ti-59-ii-dui-lie-de-zui-da-zhi-by-leetcod/
     * 方法二：暴力  直接实现一个普通的队列，查询最大值时遍历计算。
     * 复杂度分析
     * 时间复杂度：O(1)（插入，删除），O(n)（求最大值）。
     * 插入与删除只需要普通的队列操作，为 O(1)，求最大值需要遍历当前的整个队列，最坏情况下为 O(n)。
     * 空间复杂度：O(n)，需要用队列存储所有插入的元素。
     */
   /* int[] q = new int[20000];
    int begin = 0, end = 0;

    public Offer59_2() {

    }

    public int max_value() {
        int ans = -1;
        for (int i = begin; i != end; ++i) {
            ans = Math.max(ans, q[i]);
        }
        return ans;
    }

    public void push_back(int value) {
        q[end++] = value;
    }

    public int pop_front() {
        if (begin == end) {
            return -1;
        }
        return q[begin++];
    }*/

}










