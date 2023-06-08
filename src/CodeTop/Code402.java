package CodeTop;

/**
 * @author mxy
 * @create 2023-05-29 20:30
 */

import java.util.Deque;
import java.util.LinkedList;

/**
 * 402. 移掉 K 位数字    链接：https://leetcode.cn/problems/remove-k-digits
 * 给你一个以字符串表示的非负整数 num 和一个整数 k ，移除这个数中的 k 位数字，使得剩下的数字最小。
 * 请你以字符串形式返回这个最小的数字。
 *  
 *
 * 示例 1 ：
 * 输入：num = "1432219", k = 3
 * 输出："1219"
 * 解释：移除掉三个数字 4, 3, 和 2 形成一个新的最小的数字 1219 。
 *
 * 示例 2 ：
 * 输入：num = "10200", k = 1
 * 输出："200"
 * 解释：移掉首位的 1 剩下的数字为 200. 注意输出不能有任何前导零。
 *
 * 示例 3 ：
 * 输入：num = "10", k = 2
 * 输出："0"
 * 解释：从原数字移除所有的数字，剩余为空就是 0 。
 *  
 *
 * 提示：
 * 1 <= k <= num.length <= 10^5
 * num 仅由若干位数字（0 - 9）组成
 * 除了 0 本身之外，num 不含任何前导零
 *
 */
public class Code402 {

    public static void main(String[] args) {
        String nums = "1432219";
        System.out.println(removeKdigits(nums, 3));
    }


    /**
     * 题解链接：https://leetcode.cn/problems/remove-k-digits/solution/yi-diao-kwei-shu-zi-by-leetcode-solution/
     *    关键点：数字从头至尾的趋势
     *      如果 d[i] < d[i - 1]，则下降趋势，删除 d[i - 1]
     *      如果没有出现 d[i] < d[i - 1]，则字符串为非递减趋势，则每次删除最后一个元素即可
     * @param num
     * @param k
     * @return
     */
    public static String removeKdigits(String num, int k) {
        //队列模拟单调栈
        Deque<Character> deque = new LinkedList<>();
        int length = num.length();
        for (int i = 0; i < length; i++) {
            //当前字符
            char digit = num.charAt(i);
            //栈非空 && k > 0 && 栈顶元素大于当前元素
            while (!deque.isEmpty() && k > 0 && deque.peekLast() > digit){
                //栈顶元素出栈
                deque.pollLast();
                k--;
            }
            deque.offerLast(digit);
        }
        //如果字符串为非递减趋势，则栈顶元素出栈
        for (int i = 0; i < k; i++) {
            deque.pollLast();
        }
        StringBuilder ret = new StringBuilder();
        boolean leadingZero = true;
        while (!deque.isEmpty()){
            //队列头部出
            char digit = deque.pollFirst();
            //如果头部为 0 ，则继续下一个出队
            if (leadingZero && digit == '0'){
                continue;
            }
            leadingZero = false;
            //拼接字符串
            ret.append(digit);
        }
        return ret.length() == 0 ? "0" : ret.toString();
    }
}















































