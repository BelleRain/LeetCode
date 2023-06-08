package Subject.移掉K位数字;

/**
 * @author mxy
 * @create 2023-05-30 22:30
 */


import java.util.Deque;
import java.util.LinkedList;

/**
 * 402. 移掉 K 位数字    链接：https://leetcode.cn/problems/remove-k-digits
 * 给你一个以字符串表示的非负整数 num 和一个整数 k ，移除这个数中的 k 位数字，使得剩下的数字最小。
 * 请你以字符串形式返回这个最小的数字。
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
 * 提示：
 * 1 <= k <= num.length <= 10^5
 * num 仅由若干位数字（0 - 9）组成
 * 除了 0 本身之外，num 不含任何前导零
 *
 */
public class Code402 {

    public static void main(String[] args) {
        String num = "1432219";
        System.out.println(removeKdigits(num, 3));
    }

    /**
     * 提示： 如果题目改成求删除 k 个字符之后的最大数，
     *  我们只需要将 stack[-1]（栈顶元素） > digit 中的大于号改成小于号即可。
     * @param num
     * @param k
     * @return
     */
    public static String removeKdigits(String num, int k) {
        int length = num.length();
        Deque<Character> stack = new LinkedList<>();
        for (int i = 0; i < length; i++) {
            //当前字符
            char digit = num.charAt(i);
            while (!stack.isEmpty() && k > 0 && digit < stack.peekLast()){
                stack.pollLast();
                k--;
            }
            stack.offerLast(digit);
        }
        for (int i = 0; i < k; i++) {
            stack.pollLast();
        }
        StringBuilder result = new StringBuilder();
        boolean leadingZero = true;
        while (!stack.isEmpty()){
            Character c = stack.pollFirst();
            if (leadingZero && c == '0'){
                continue;
            }
            leadingZero = false;
            result.append(c);
        }
        return result.toString() == "" ? "0" : result.toString();
    }
}





































