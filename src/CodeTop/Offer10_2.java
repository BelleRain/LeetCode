package CodeTop;

/**
 * @author mxy
 * @create 2023-06-14 9:55
 */

/**
 * 剑指 Offer 10- II. 青蛙跳台阶问题    链接：https://leetcode.cn/problems/qing-wa-tiao-tai-jie-wen-ti-lcof
 * 一只青蛙一次可以跳上 1 级台阶，也可以跳上 2 级台阶。
 * 求该青蛙跳上一个 n 级的台阶总共有多少种跳法。
 * 答案需要取模 1e9+7（1000000007），如计算初始结果为：1000000008，请返回 1。
 *
 * 示例 1：
 * 输入：n = 2
 * 输出：2
 *
 * 示例 2：
 * 输入：n = 7
 * 输出：21
 *
 * 示例 3：
 * 输入：n = 0
 * 输出：1
 *
 * 提示：
 * 0 <= n <= 100
 *
 */
public class Offer10_2 {

    public static void main(String[] args) {
        System.out.println(numWays(7));
    }


    /**
     * 动态规划：
     *   1、dp[i] : 跳上第 i 级 的跳法
     *   2、dp[0] = 0 ，dp[1] = 1，dp[2] = 2
     *   3、dp[i] = dp[i - 1] + dp[i - 2]
     * @param n
     * @return
     */
    public static int numWays(int n) {
        if (n < 2) return 1;
        int[] dp = new int[n + 1];
        dp[1] = 1;
        dp[2] = 2;
        for (int i = 3; i < n + 1; i++) {
            dp[i] = (dp[i - 1] + dp[i - 2])%1000000007;
        }
        return dp[n];
    }
}































