package Top100;

/**
 * @author mxy
 * @create 2022-11-28 8:54
 */

/**
 * 121. 买卖股票的最佳时机  (同剑指 Offer63)  链接：https://leetcode.cn/problems/best-time-to-buy-and-sell-stock
 * 给定一个数组 prices ，它的第 i 个元素 prices[i] 表示一支给定股票第 i 天的价格。
 * 你只能选择 某一天 买入这只股票，并选择在 未来的某一个不同的日子 卖出该股票。设计一个算法来计算你所能获取的最大利润。
 * 返回你可以从这笔交易中获取的最大利润。如果你不能获取任何利润，返回 0 。
 *
 * 示例 1：
 * 输入：[7,1,5,3,6,4]
 * 输出：5
 * 解释：在第 2 天（股票价格 = 1）的时候买入，在第 5 天（股票价格 = 6）的时候卖出，最大利润 = 6-1 = 5 。
 *      注意利润不能是 7-1 = 6, 因为卖出价格需要大于买入价格；同时，你不能在买入前卖出股票。
 *
 * 示例 2：
 * 输入：prices = [7,6,4,3,1]
 * 输出：0
 * 解释：在这种情况下, 没有交易完成, 所以最大利润为 0。
 *  
 * 提示：
 * 1 <= prices.length <= 10^5
 * 0 <= prices[i] <= 10^4
 */
public class Top121 {

    public static void main(String[] args) {
        Top121 top121 = new Top121();
        int[] prices = {7,1,5,3,6,4};
        System.out.println(top121.maxProfit(prices));
    }

    /**
     * 股票问题总结： https://leetcode.cn/problems/best-time-to-buy-and-sell-stock/solution/si-wei-dao-tu-zheng-li-gu-piao-wen-ti-da-9jir/
     */

    /**
     * 详细题解见 Offer63
     * 题解链接： 原文链接：https://leetcode.cn/problems/gu-piao-de-zui-da-li-run-lcof/solution/mian-shi-ti-63-gu-piao-de-zui-da-li-run-dong-tai-2/
     * 动态规划解析：
     *      状态定义： 设动态规划列表 dp，dp[i] 代表以 prices[i] 为结尾的子数组的最大利润（以下简称为 前 i 日的最大利润 ）。
     *      转移方程： 由于题目限定 “买卖该股票一次” ，因此前 i 日最大利润 dp[i] 等于前 i - 1 日最大利润 dp[i-1] 和第 i 日卖出的最大利润中的最大值。
     *
     *      前 i 日最大利润 = max(前 (i-1) 日最大利润, 第 i 日价格 - 前 i 日最低价格)
     *      dp[i] = max(dp[i - 1], prices[i] - min(prices[0:i]))
     *
     *      初始状态： dp[0] = 0 ，即首日利润为 0 ；
     *      返回值： dp[n - 1] ，其中 n 为 dp 列表长度。
     * 效率优化：
     * 时间复杂度降低： 前 i 日的最低价格 min(prices[0:i]) 时间复杂度为 O(i) 。而在遍历 prices 时，可以借助一个变量（记为成本 cost ）每日更新最低价格。
     *      优化后的转移方程为： dp[i]=max(dp[i−1],prices[i]−min(cost,prices[i])
     *
     * 空间复杂度降低： 由于 dp[i] 只与 dp[i - 1] , prices[i] , cost 相关，因此可使用一个变量（记为利润 profit）代替 dp 列表。
     *      优化后的转移方程为：  profit=max(profit,prices[i]−min(cost,prices[i])
     * @param prices
     * @return
     */
    public int maxProfit(int[] prices) {
        int cost = Integer.MAX_VALUE;
        int profit = 0;
        for (int price : prices) {
            cost = Math.min(price, cost);
            profit = Math.max(profit, price - cost);
        }
        return profit;
    }
}
