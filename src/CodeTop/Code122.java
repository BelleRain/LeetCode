package CodeTop;

/**
 * @author mxy
 * @create 2023-05-12 13:56
 */

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * 122. 买卖股票的最佳时机 II    链接：https://leetcode.cn/problems/best-time-to-buy-and-sell-stock-ii
 * 给你一个整数数组 prices ，其中 prices[i] 表示某支股票第 i 天的价格。
 * 在每一天，你可以决定是否购买和/或出售股票。你在任何时候 最多 只能持有 一股 股票。你也可以先购买，然后在 同一天 出售。
 * 返回 你能获得的 最大 利润 。
 *
 * 示例 1：
 * 输入：prices = [7,1,5,3,6,4]
 * 输出：7
 * 解释：在第 2 天（股票价格 = 1）的时候买入，在第 3 天（股票价格 = 5）的时候卖出, 这笔交易所能获得利润 = 5 - 1 = 4 。
 *      随后，在第 4 天（股票价格 = 3）的时候买入，在第 5 天（股票价格 = 6）的时候卖出, 这笔交易所能获得利润 = 6 - 3 = 3 。
 *      总利润为 4 + 3 = 7 。
 *
 * 示例 2：
 * 输入：prices = [1,2,3,4,5]
 * 输出：4
 * 解释：在第 1 天（股票价格 = 1）的时候买入，在第 5 天 （股票价格 = 5）的时候卖出, 这笔交易所能获得利润 = 5 - 1 = 4 。
 *      总利润为 4 。
 *
 * 示例 3：
 * 输入：prices = [7,6,4,3,1]
 * 输出：0
 * 解释：在这种情况下, 交易无法获得正利润，所以不参与交易可以获得最大利润，最大利润为 0 。
 *
 * 提示：
 * 1 <= prices.length <= 3 * 10^4
 * 0 <= prices[i] <= 10^4
 *
 */
public class Code122 {

    public static void main(String[] args) {
        int[] prices = {7,1,5,3,6,4};
        System.out.println(maxProfit(prices));
    }

    /**
     * 题解一：https://leetcode.cn/problems/best-time-to-buy-and-sell-stock-ii/solution/best-time-to-buy-and-sell-stock-ii-zhuan-hua-fa-ji/
     * 贪心算法：
     * 解题思路：
     * 股票买卖策略：
     *     单独交易日： 设今天价格 p1、明天价格 p2，则今天买入、明天卖出可赚取金额 p2 − p1(负值代表亏损）。
     *     连续上涨交易日： 设此上涨交易日股票价格分别为 p1,p2,...,pn，则第一天买最后一天卖收益最大，即 pn − p1；
     *             等价于每天都买卖，即 pn − p1 = (p2 − p1)+(p3 − p2)+...+(pn − pn−1)。
     *     连续下降交易日： 则不买卖收益最大，即不会亏钱。
     * 算法流程：
     *      1、遍历整个股票交易日价格列表 price，策略是所有上涨交易日都买卖（赚到所有利润），
     *        所有下降交易日都不买卖（永不亏钱）。
     *      2、设 tmp 为第 i-1 日买入与第 i 日卖出赚取的利润，即 tmp = prices[i] - prices[i - 1] ；
     *              当该天利润为正 tmp > 0，则将利润加入总利润 profit；当利润为 0 或为负，则直接跳过；
     *      3、遍历完成后，返回总利润 profit。
     * @param prices
     * @return
     */
    public static int maxProfit1(int[] prices) {
        int tmp = 0, profit = 0;
        for (int i = 1; i < prices.length; i++) {
            tmp = prices[i] - prices[i - 1];
            if (tmp > 0) profit = profit + tmp;
        }
        return profit;
    }


    /**
     * 题解二：https://leetcode.cn/problems/best-time-to-buy-and-sell-stock-ii/solution/tan-xin-suan-fa-by-liweiwei1419-2/
     * 动态规划：
     */
    public static int maxProfit(int[] prices) {
        int len = prices.length;
        if (len < 2) return 0;
        // 0: 持有现金
        // 1：持有股票
        // 状态转移：0 -> 1 -> 0 -> 1 -> 0 -> 1 -> 0
        //dp[i][j] : 表示到下标为 i 的这一天，持股状态为 j 时，手上拥有的最大现金数
        int[][] dp = new int[len][2];
        dp[0][0] = 0; //第一天什么都不做
        dp[0][1] = -prices[0]; //第一天买入股票
        for (int i = 1; i < prices.length; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i]);
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] - prices[i]);
        }
        for (int[] ints : dp) {
            System.out.println(Arrays.toString(ints));
        }
        return dp[len - 1][0];
    }

}











































