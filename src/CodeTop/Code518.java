package CodeTop;

/**
 * @author mxy
 * @create 2023-06-18 16:33
 */

/**
 * 518. 零钱兑换 II     链接：https://leetcode.cn/problems/coin-change-ii
 * 给你一个整数数组 coins 表示不同面额的硬币，另给一个整数 amount 表示总金额。
 * 请你计算并返回可以凑成总金额的硬币组合数。如果任何硬币组合都无法凑出总金额，返回 0 。
 * 假设每一种面额的硬币有无限个。 
 *
 * 题目数据保证结果符合 32 位带符号整数。
 *
 * 示例 1：
 * 输入：amount = 5, coins = [1, 2, 5]
 * 输出：4
 * 解释：有四种方式可以凑成总金额：
 * 5=5
 * 5=2+2+1
 * 5=2+1+1+1
 * 5=1+1+1+1+1
 *
 * 示例 2：
 * 输入：amount = 3, coins = [2]
 * 输出：0
 * 解释：只用面额 2 的硬币不能凑成总金额 3 。
 *
 * 示例 3：
 * 输入：amount = 10, coins = [10]
 * 输出：1
 *
 * 提示：
 * 1 <= coins.length <= 300
 * 1 <= coins[i] <= 5000
 * coins 中的所有值 互不相同
 * 0 <= amount <= 5000
 *
 */
public class Code518 {

    public static void main(String[] args) {
        int[] coins = {1, 2, 5};
        System.out.println(change(5, coins));
    }

    /**
     * 题解链接：https://leetcode.cn/problems/coin-change-ii/solution/ling-qian-dui-huan-ii-by-leetcode-soluti-f7uh/
     */

    /**
     * 动态规划：
     *    1、dp[x] : 表示金额之和等于 x 的硬币组合数，目标是求 dp[amount].
     *    2、dp[0] = 1。当不选取任何硬币时，金额之和为 0，只有 1 种组合
     *    3、对于 coin 的硬币，当 coin <= i <= amount时，如果存在一种硬币组合的金额之和等于 i - coin。
     *       则在该硬币组合中增加一个面额为 coin 的硬币，即可得到一种金额之和等于 i 的硬币组合。
     *       因此需要遍历 coins，对于其中的每一种面额的硬币，更新数组 dp 中的每个大于或等于该面额的元素的值。
     *  流程：
     *      1、初始化 dp[0] = 1;
     *      2、遍历 coins，对于其中的每个元素coin，进行如下操作：
     *            遍历 i 从 coin 到 amount，将 dp[i - coin] 的值加到 dp[i]。
     *      3、最终得到 dp[amount] 的值即为答案。
     *    上述做法不会重复计算不同的排列。因为外层循环是遍历数组 coins 的值，内层循环是遍历不同的金额之和，
     *    在计算 dp[i] 的值时，可以确保金额之和等于 i 的硬币面额的顺序，由于顺序确定，因此不会重复计算不同的排列。
     *    例如，coins = [1,2], 对于 dp[3] 的计算，一定是先遍历硬币面额 1 后遍历硬币面额 2 ，只会出现以下 2 种组合：
     *          3 = 1 + 1 + 1     3 = 1 + 2
     *       硬币面额 2 不可能出现在硬币面额 1 之前，即不会重复计算 3 = 2 + 1 的情况。
     * @param amount
     * @param coins
     * @return
     */
    public static int change(int amount, int[] coins) {
        // coins = {1, 2, 5}, amount = 5
        int[] dp = new int[amount + 1];
        dp[0] = 1;
        for (int coin : coins) {
            for (int i = coin; i <= amount; i++) { //列举金额
                /*
                  dp[1] = dp[1] + dp[0];
                  dp[2] = dp[2] + dp[1];
                 */
                dp[i] += dp[i - coin];
            }
        }
        return dp[amount];
    }

    /**
     * 零钱兑换II和爬楼梯问题到底有什么不同？
     * https://leetcode.cn/problems/coin-change-ii/solution/ling-qian-dui-huan-iihe-pa-lou-ti-wen-ti-dao-di-yo/
     */
}
































