package CodeTop;

/**
 * @author mxy
 * @create 2023-04-21 9:45
 */

/**
 * 70. 爬楼梯     链接：https://leetcode.cn/problems/climbing-stairs
 * 假设你正在爬楼梯。需要 n 阶你才能到达楼顶。
 * 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
 *
 * 示例 1：
 * 输入：n = 2
 * 输出：2
 * 解释：有两种方法可以爬到楼顶。
 * 1. 1 阶 + 1 阶
 * 2. 2 阶
 *
 * 示例 2：
 * 输入：n = 3
 * 输出：3
 * 解释：有三种方法可以爬到楼顶。
 * 1. 1 阶 + 1 阶 + 1 阶
 * 2. 1 阶 + 2 阶
 * 3. 2 阶 + 1 阶
 *  
 * 提示：
 * 1 <= n <= 45
 */
public class Code70 {

    public static void main(String[] args) {

    }


    /**
     * 动态规划：(类似于斐波那契数列)
     *  1、dp[i] : 爬到 第 i 及台阶所有的方法
     *  2、如果 由 i - 1 到 i，则 需向上爬一步，dp[i] = dp[i - 1]
     *     如果 由 i - 2 到 i，则 需向上爬两步，dp[i] = dp[i - 2]
     *     dp[i] = dp[i - 1] + dp[i - 2]
     *  3、dp[0] = 0 , dp[1] = 1, dp[2] = 2
     * @param n
     * @return
     */
    public static int climbStairs(int n) {
        if (n == 1) return 1;
        if (n == 2) return 2;
        int[] dp = new int[n + 1];
        dp[1] = 1;
        dp[2] = 2;
        for (int i = 3; i < dp.length; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }


    /**
     * 空间优化
     * @param n
     * @return
     */
    public static int climbStairs2(int n) {
        int p = 0, q = 0, r = 1;
        for (int i = 1; i <= n; i++) {
            p = q;
            q = r;
            r = p + q;
        }
        return r;
    }
}
































