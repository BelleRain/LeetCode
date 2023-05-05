package Swordoffer.Simulate;

/**
 * @author mxy
 * @create 2022-10-25 15:25
 */

import java.util.Stack;

/**
 * 栈的压入、弹出序列
 * 输入两个整数序列，第一个序列表示栈的压入顺序，请判断第二个序列是否为该栈的弹出顺序。假设压入栈的所有数字均不相等。
 * 例如，序列 {1,2,3,4,5} 是某栈的压栈序列，序列 {4,5,3,2,1} 是该压栈序列对应的一个弹出序列，但 {4,3,5,1,2} 就不可能是该压栈序列的弹出序列。
 *
 * 示例 1：
 * 输入：pushed = [1,2,3,4,5], popped = [4,5,3,2,1]
 * 输出：true
 * 解释：我们可以按以下顺序执行：
 * push(1), push(2), push(3), push(4), pop() -> 4,
 * push(5), pop() -> 5, pop() -> 3, pop() -> 2, pop() -> 1
 *
 * 示例 2：
 * 输入：pushed = [1,2,3,4,5], popped = [4,3,5,1,2]
 * 输出：false
 * 解释：1 不能在 2 之前弹出。
 *  
 * 提示：
 *  1、 0 <= pushed.length == popped.length <= 1000
 *  2、 0 <= pushed[i], popped[i] < 1000
 *  3、 pushed 是 popped 的排列。
 */
public class Offer31 {

    public static void main(String[] args) {
        Offer31 offer = new Offer31();
        int[] pushed = {1,2,3,4,5};
        int[] popped = {4,5,3,2,1};
        System.out.println(offer.validateStackSequences(pushed, popped));
    }

    /**
     * 题解链接：https://leetcode.cn/problems/zhan-de-ya-ru-dan-chu-xu-lie-lcof/solution/mian-shi-ti-31-zhan-de-ya-ru-dan-chu-xu-lie-mo-n-2/
     * 方法一：栈模拟
     * 复杂度分析：
     * 时间复杂度 O(N) ： 其中 N 为列表 pushed 的长度；每个元素最多入栈与出栈一次，即最多共 2N 次出入栈操作。
     * 空间复杂度 O(N) ： 辅助栈 stack 最多同时存储 N 个元素。
     * @param pushed
     * @param popped
     * @return
     */
    public boolean validateStackSequences(int[] pushed, int[] popped) {
        if (pushed.length != popped.length) return false;
        //辅助栈，模拟元素出入栈
        //将pushed中的元素压栈，再按照一定的顺序弹栈，如果最后栈为空，则popped是出栈顺序
        Stack<Integer> stack = new Stack<>();
        int i = 0;
        for (int num : pushed) {
            stack.push(num); //num入栈
            while (!stack.isEmpty() && stack.peek() == popped[i]){ //循环判断并出栈
                stack.pop();
                i++;
            }
        }
        return stack.isEmpty();
    }

    /**
     * 方法二：数组模拟栈 ： 速度较快
     * @param pushed
     * @param popped
     * @return
     */
    //public boolean validateStackSequences(int[] pushed, int[] popped) {
    //    int n = pushed.length, idx = 0;
    //    for (int i = 0, j = 0; i < n; i++) {
    //        pushed[idx++] = pushed[i];
    //        //pushed[idx - 1]:栈顶元素
    //        while (idx > 0 && pushed[idx - 1] == popped[j] && ++j >= 0)
    //            idx--;
    //    }
    //    return idx == 0;
    //}
}


































