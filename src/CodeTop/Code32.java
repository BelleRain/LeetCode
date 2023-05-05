package CodeTop;

/**
 * @author mxy
 * @create 2023-04-24 11:28
 */

import com.sun.jmx.remote.internal.ArrayQueue;
import org.omg.CORBA.MARSHAL;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;

/**
 * 32. 最长有效括号   链接：https://leetcode.cn/problems/longest-valid-parentheses
 * 给你一个只包含 '(' 和 ')' 的字符串，找出最长有效（格式正确且连续）括号子串的长度。
 *
 * 示例 1：
 * 输入：s = "(()"
 * 输出：2
 * 解释：最长有效括号子串是 "()"
 *
 * 示例 2：
 * 输入：s = ")()())"
 * 输出：4
 * 解释：最长有效括号子串是 "()()"
 *
 * 示例 3：
 * 输入：s = ""
 * 输出：0
 *  
 * 提示：
 * 0 <= s.length <= 3 * 10^4
 * s[i] 为 '(' 或 ')'
 *
 */
public class Code32 {

    public static void main(String[] args) {
        System.out.println(longestValidParentheses3(")()())"));
        System.out.println(longestValidParentheses3("()(()"));
    }

    /**
     * 题解链接：https://leetcode.cn/problems/longest-valid-parentheses/solution/zui-chang-you-xiao-gua-hao-by-leetcode-solution/
     */

    /**
     * 图解：https://leetcode.cn/problems/longest-valid-parentheses/solution/dong-tai-gui-hua-si-lu-xiang-jie-c-by-zhanganan042/
     * 注意：用笔计算
     * 解法一： 动态规划
     * 1、dp[i] : 以下标i字符结尾的最长有效括号的长度。
     * 2、s[i - 1] = '(' 且 s[i] = ')',也就是字符串形如“.....()” ，得出  dp[i] = dp[i - 2] + 2;
     *    s[i] = ')' 且 s[i - 1] = ')' ,也就是字符串形如“.....))” ,得出
     *         如果 s[i - dp[i - 1] - 1] = '('则 dp[i] = dp[i - 1] + dp[i - dp[i - 1] - 2] + 2
     * 3、我们考虑如果倒数第二个 ‘)’ 是一个有效子字符串的一部分（记作 sub_s），对于最后一个 ‘)’ ，如果它是一个更长子字符串的一部分，
     *   那么它一定有一个对应的 ‘(’ ，且它的位置在倒数第二个 ‘)’ 所在的有效子字符串的前面（也就是 sub_s的前面）。
     *    因此，如果子字符串 sub_s的前面恰好是 ‘(’ ，那么我们就用 2 加上 sub_s的长度（dp[i−1]）去更新 dp[i]。
     *    同时，我们也会把有效子串 “(sub_s)”之前的有效子串的长度也加上，也就是再加上 dp[i−dp[i−1]−2]
     *
     * @param s
     * @return
     */
    public static int longestValidParentheses1(String s) {
        int maxans = 0;
        int[] dp = new int[s.length()];
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) == ')'){
                if (s.charAt(i - 1) == '('){
                    dp[i] = (i >= 2 ? dp[i - 2] : 0) + 2;
                }else if (i - dp[i - 1] > 0 && s.charAt(i - dp[i - 1] - 1) == '('){
                    dp[i] = dp[i - 1] + ((i - dp[i - 1]) >= 2 ? dp[i - dp[i - 1] - 2] : 0) + 2;
                }
                maxans = Math.max(maxans, dp[i]);
            }
        }
        return maxans;
    }

    /**
     * 栈
     * @param s
     * @return
     */
    public static int longestValidParentheses2(String s) {
        int maxans = 0;
        //保持栈底元素为当前已经遍历过的元素中「最后一个没有被匹配的右括号的下标」
        Deque<Integer> stack = new LinkedList<>();
        //如果一开始栈为空，第一个字符为左括号的时候我们会将其放入栈中，这样就不满足提及的「最后一个没有被匹配的右括号的下标」，
        // 为了保持统一，我们在一开始的时候往栈中放入一个值为 -1 的元素。
        stack.push(-1);
        for (int i = 0; i < s.length(); i++) {
            //对于遇到的每个 ‘(’ ，我们将它的下标放入栈中
            if (s.charAt(i) == '('){
                stack.push(i);
            }else {
                //对于遇到的每个 ‘)’ ，我们先弹出栈顶元素表示匹配了当前右括号：
                stack.pop();
                //如果栈为空，说明当前的右括号为没有被匹配的右括号，我们将其下标放入栈中来更新我们之前提到的「最后一个没有被匹配的右括号的下标」
                if (stack.isEmpty()){
                    stack.push(i);
                }else {
                    // 如果栈不为空，当前右括号的下标减去栈顶元素即为「以该右括号为结尾的最长有效括号的长度」
                    maxans = Math.max(maxans, i - stack.peek());
                }
            }
        }
        return maxans;
    }


    /**
     * 不需要额外的空间 ： 此题思路详见题解
     * @param s
     * @return
     */
    public static int longestValidParentheses3(String s) {
        int left = 0,right = 0,maxlength = 0;
        //正向遍历
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '('){
                left++;
            }else {
                right++;
            }
            if (left == right){
                maxlength = Math.max(maxlength, 2 * right);
            }else if (right > left){
                left = right = 0;
            }
        }
        left = right = 0;
        //倒序遍历
        for (int i = s.length() - 1; i >= 0; i--) {
            if (s.charAt(i) == ')'){
                right++;
            }else {
                left++;
            }
            if (left == right){
                maxlength = Math.max(maxlength, left * 2);
            }else if (left > right){
                left = 0;
                right = 0;
            }
        }
        return maxlength;
    }

}































