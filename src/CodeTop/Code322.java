package CodeTop;

/**
 * @author mxy
 * @create 2023-04-26 9:39
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 322. 零钱兑换    链接：https://leetcode.cn/problems/coin-change
 * 给你一个整数数组 coins ，表示不同面额的硬币；以及一个整数 amount ，表示总金额。
 * 计算并返回可以凑成总金额所需的 最少的硬币个数 。如果没有任何一种硬币组合能组成总金额，返回 -1 。
 * 你可以认为每种硬币的数量是无限的。
 *
 * 示例 1：
 * 输入：coins = [1, 2, 5], amount = 11
 * 输出：3
 * 解释：11 = 5 + 5 + 1
 *
 * 示例 2：
 * 输入：coins = [2], amount = 3
 * 输出：-1
 *
 * 示例 3：
 * 输入：coins = [1], amount = 0
 * 输出：0
 *  
 * 提示：
 * 1 <= coins.length <= 12
 * 1 <= coins[i] <= 2^31 - 1
 * 0 <= amount <= 10^4
 *
 */
public class Code322 {

    public static void main(String[] args) {
        int[] coins = {1,2,5};
        System.out.println(coinChange(coins, 11));
    }

    /**
     * 题解链接：https://leetcode.cn/problems/coin-change/solution/322-ling-qian-dui-huan-by-leetcode-solution/
     */

    /**
     * 记忆化搜索
     * 1、 F(S) : 组成金额 S 最少的硬币数
     * 2、 组成 S 的最后一枚硬币的面值为 C，则 F(S) = F(S - C) + 1
     * 3、 但无法确定C的数值，则需要列举
     *          c0,c1,c2,...,c(n-1). 并取 min
     *          F(S) = min(F(S - ci)) + 1  , S - ci >= 0
     * 4、 F(S) = -1 ，n = 0
     *     F(S) = 0 ，S = 0
     * @param coins
     * @param amount
     * @return
     */
    public static int coinChange1(int[] coins, int amount) {
        if (amount < 1) return 0;
        return coinChange(coins, amount,new int[amount]);
    }

    /**
     * 求组成金额 S 的最少的硬币数
     * @param coins  硬币数组
     * @param rem    当前剩余的钱数
     * @param count  保存路径结果，即 F(S - C)
     * @return
     */
    private static int coinChange(int[] coins, int rem, int[] count) {
        if (rem < 0) return -1;
        if (rem == 0) return rem;
        if (count[rem - 1] != 0){
            return count[rem - 1];
        }
        int min = Integer.MAX_VALUE;
        for (int coin : coins) {
            int res = coinChange(coins, rem - coin,count);
            if (res >= 0 && res < min){
                min = 1 + res;
            }
        }
        count[rem - 1] = (min == Integer.MAX_VALUE) ? -1 : min;
        return count[rem - 1];
    }


    /**
     * 动态规划：
     * F(S) : 组成金额 S 最少的硬币数
     * 1、F(0) = 0
     * 2、F(1) = min(F(1 - 1), F(1 - 2),F(1 - 5)) + 1 = 1
     * 3、F(2) = min(F(2 - 1), F(2 - 2),F(2 - 5)) + 1 = 1
     * 4、F(3) = min(F(3 - 1), F(3 - 2),F(3 - 5)) + 1 = 2
     *  ......
     * F(11) = min(F(11 - 1), F(11 - 2),F(11 - 5)) + 1 = 2
     *
     * 判断金额凑不出的小技巧：
     *      先初始化DP table各个元素为amount + 1（代表不可能存在的情况），
     *      在遍历时如果金额凑不出则不更新，于是若最后结果仍然是amount + 1，
     *      则表示金额凑不出
     * @param coins
     * @param amount
     * @return
     */
    public static int coinChange2(int[] coins, int amount) {
        int max = amount + 1;
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, max);
        dp[0] = 0;
        for (int i = 1; i <= amount; i++) {
            for (int j = 0; j < coins.length; j++) {
                if (coins[j] <= i){
                    dp[i] = Math.min(dp[i - coins[j]] + 1, dp[i]);
                }
            }
        }
        return dp[amount] > amount ? -1 : dp[amount];
    }


    /**
     * 优化：
     * @param coins
     * @param amount
     * @return
     */
    public static int coinChange(int[] coins, int amount) {
        int[] res = new int[amount + 1];
        for (int i = 1; i < res.length; i++) {
            int tmp = Integer.MAX_VALUE - 1;
            for (int coin : coins) {
                if (coin <= i){
                    tmp = Math.min(tmp, res[i - coin]);
                }
            }
            res[i] = tmp + 1;
        }
        return res[amount] == Integer.MAX_VALUE ? -1 : res[amount];
    }
}







































