package Top100;

/**
 * @author mxy
 * @create 2023-02-22 10:40
 */

/**
 * 309. 最佳买卖股票时机含冷冻期     链接：https://leetcode.cn/problems/best-time-to-buy-and-sell-stock-with-cooldown/
 * 给定一个整数数组prices，其中第  prices[i] 表示第 i 天的股票价格 。​
 * 设计一个算法计算出最大利润。在满足以下约束条件下，你可以尽可能地完成更多的交易（多次买卖一支股票）:
 *
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
public class Top309 {

    public static void main(String[] args) {
        Top309 top309 = new Top309();
        int[] prices = {1,2,3,0,2};
        System.out.println(top309.maxProfit(prices));
    }


    /**
     * 题解链接： https://leetcode.cn/problems/best-time-to-buy-and-sell-stock-with-cooldown/solution/dong-tai-gui-hua-by-liweiwei1419-5/
     * 思路分析：
     *  1、解题关键：用 冷冻期 将 不持股状态 分开定义
     *      把「冷冻期」定义成一个状态，不太好推导状态转移方程，由于事实上就只有「持股」和「不持股」这两种情况，因此可以为不持股增加一种情况：
     *  2、状态定义：
     *      dp[i][j] 表示 [0, i] 区间内，在下标为 i 这一天状态为 j 时，我们手上拥有的金钱数。
     *      这里 j 可以取 3 个值（下面的定义非常重要）：
     *      0 表示：今天 不是 卖出了股票的不持股状态；
     *      1 表示：持股；
     *      2 表示：今天由于卖出了股票的不持股状态；
     * 复杂度分析：
     *      时间复杂度：O(N)，这里 N 是股价数组的长度，只遍历了一次；
     *      空间复杂度：O(N)。
     * @param prices
     * @return
     */
    public int maxProfit(int[] prices) {
        int len = prices.length;
        if (len < 2) return 0;
        int[][] dp = new int[len][3];
        dp[0][0] = 0;
        dp[0][1] = -prices[0];
        dp[0][2] = 0;

        //dp[i][0] : 手上不持有股票，并且今天【不是】由于卖出股票而不持股，我们拥有的现金数 【非冷冻期】
        //dp[i][1] : 手上持有股票，我们拥有的现金数
        //dp[i][2] : 手上不持有股票，并且今天【是】由于卖出股票而不持股，我们拥有的现金数 【冷冻期】

        for (int i = 1; i < len; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][2]);
            dp[i][1] = Math.max( dp[i - 1][1],dp[i - 1][0] - prices[i]);
            dp[i][2] = dp[i - 1][1] + prices[i];
        }
        return Math.max(dp[len - 1][0], dp[len - 1][2]);
    }

    /**
     * 空间优化：滚动变量
     *     复杂度分析：
     *          时间复杂度：O(N)，这里 N 是股价数组的长度，只遍历了一次；
     *          空间复杂度：O(1)，状态数组里元素的个数是常数。
     * @param prices
     * @return
     */
    /*public int maxProfit(int[] prices) {
        int len = prices.length;
        if (len < 2) return 0;
        int[] dp = new int[3];

        dp[0] = 0;
        dp[1] = -prices[0];
        dp[2] = 0;

        int pre0 = dp[0];
        int pre2 = dp[2];

        for (int i = 1; i < len; i++) {
            dp[0] = Math.max(dp[0], pre2);
            dp[1] = Math.max(dp[1], pre0 - prices[i]);
            dp[2] = dp[1] + prices[i];

            pre0 = dp[0];
            pre2 = dp[2];
        }
        return Math.max(dp[0], dp[2]);
    }*/

}
