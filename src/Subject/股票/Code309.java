package Subject.股票;

/**
 * @author mxy
 * @create 2023-05-17 16:42
 */

/**
 * 309. 最佳买卖股票时机含冷冻期    链接：https://leetcode.cn/problems/best-time-to-buy-and-sell-stock-with-cooldown
 * 给定一个整数数组prices，其中第  prices[i] 表示第 i 天的股票价格 。​
 * 设计一个算法计算出最大利润。在满足以下约束条件下，你可以尽可能地完成更多的交易（多次买卖一支股票）:
 * 卖出股票后，你无法在第二天买入股票 (即冷冻期为 1 天)。
 * 注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
 *
 * 示例 1:
 * 输入: prices = [1,2,3,0,2]
 * 输出: 3
 * 解释: 对应的交易状态为: [买入, 卖出, 冷冻期, 买入, 卖出]
 *
 * 示例 2:
 * 输入: prices = [1]
 * 输出: 0
 *
 * 提示：
 * 1 <= prices.length <= 5000
 * 0 <= prices[i] <= 1000
 *
 */
public class Code309 {

    public static void main(String[] args) {
        int[] nums = {1,2,3,0,2};
        System.out.println(maxProfit(nums));
    }

    public static int maxProfit(int[] prices) {
        if (prices == null || prices.length < 2) return 0;
        int length = prices.length;
        int[][] dp = new int[length][2];
        dp[0][0] = 0;
        dp[0][1] = -prices[0];
        for (int i = 1; i < prices.length; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i]);
            dp[i][1] = Math.max(dp[i - 1][1], (i >= 2 ? dp[i - 2][0] : 0) - prices[i]);
        }
        return dp[length - 1][0];
    }
}










































