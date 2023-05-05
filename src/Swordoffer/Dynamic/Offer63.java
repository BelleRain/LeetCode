package Swordoffer.Dynamic;

/**
 * @author mxy
 * @create 2022-09-23 16:18
 */

/**
 * 股票的最大利润
 * 假设把某股票的价格按照时间先后顺序存储在数组中，请问买卖该股票一次可能获得的最大利润是多少？
 *
 * 示例 1:
 * 输入: [7,1,5,3,6,4]
 * 输出: 5
 * 解释: 在第 2 天（股票价格 = 1）的时候买入，在第 5 天（股票价格 = 6）的时候卖出，最大利润 = 6-1 = 5 。
 *      注意利润不能是 7-1 = 6, 因为卖出价格需要大于买入价格。
 *
 * 示例 2:
 * 输入: [7,6,4,3,1]
 * 输出: 0
 * 解释: 在这种情况下, 没有交易完成, 所以最大利润为 0。
 */
public class Offer63 {

    public static void main(String[] args) {
        Offer63 offer = new Offer63();
        int[] prices = {7,1,5,3,6,4};
        int res = offer.maxProfit(prices);
        System.out.println(res);
    }

    /**
     * 动态规划的要点：推导转移方程，即 f(n) 与 f(n-1)...f(1)等的关系
     */

    /**
     * 解题思路： 原文链接：https://leetcode.cn/problems/gu-piao-de-zui-da-li-run-lcof/solution/mian-shi-ti-63-gu-piao-de-zui-da-li-run-dong-tai-2/
     *      设共有 n 天，第 a 天买，第 b 天卖，则需保证 a < b ；可推出交易方案数共有：
     *      (n - 1) + (n - 2) + …… + 2 + 1 = n(n - 1) / 2
     * 因此，暴力法的时间复杂度为 O(n^2)。考虑使用动态规划降低时间复杂度，以下按照流程解题。
     * 动态规划解析：
     *      状态定义： 设动态规划列表 dp，dp[i] 代表以 prices[i] 为结尾的子数组的最大利润（以下简称为 前 i 日的最大利润 ）。
     *      转移方程： 由于题目限定 “买卖该股票一次” ，因此前 i 日最大利润 dp[i] 等于前 i - 1 日最大利润 dp[i-1] 和第 i 日卖出的最大利润中的最大值。
     *
     *      前 i 日最大利润 = max(前 (i-1) 日最大利润, 第 i 日价格 - 前 i 日最低价格)
     *      dp[i] = max(dp[i - 1], prices[i] - min(prices[0:i]))
     *
     *      初始状态： dp[0] = 0 ，即首日利润为 0 ；
     *      返回值： dp[n - 1] ，其中 n 为 dp 列表长度。
     *
     * 效率优化：
     * 时间复杂度降低： 前 i 日的最低价格 min(prices[0:i]) 时间复杂度为 O(i) 。而在遍历 prices 时，可以借助一个变量（记为成本 cost ）每日更新最低价格。
     *      优化后的转移方程为： dp[i]=max(dp[i−1],prices[i]−min(cost,prices[i])
     *
     * 空间复杂度降低： 由于 dp[i] 只与 dp[i - 1] , prices[i] , cost 相关，因此可使用一个变量（记为利润 profit）代替 dp 列表。
     * 优化后的转移方程为：  profit=max(profit,prices[i]−min(cost,prices[i])
     *
     * 复杂度分析：
     * 时间复杂度 O(N) ： 其中 N 为 prices 列表长度，动态规划需遍历 prices 。
     * 空间复杂度 O(1) ： 变量 cost 和 profit 使用常数大小的额外空间。
     *
     *
     * @param prices
     * @return
     */
    //转移方程：dp[i] = max(dp[i-1],prices[i] - min(prices[0:i]))
    public int maxProfit(int[] prices) {
        int cost = Integer.MAX_VALUE;
        int profit = 0;
        for (int price : prices) {
            cost = Math.min(cost, price);
            profit = Math.max(profit, price - cost);
        }
        return profit;
    }

    /**
     * 另一种写法：
     */
    /*public int maxProfit(int[] prices) {
        if(prices.length == 0) return 0;
        int res = 0, min = prices[0];
        for(int i = 1; i < prices.length; i ++){
            min = Math.min(min, prices[i]);
            res = Math.max(res, prices[i] - min);
        }
        return res;
    }*/

    /**
     * 暴力解法： 时间复杂度高
     *  复杂度分析:
     *     时间复杂度：O(n^2)。循环运行 （n(n−1)）/2 次。
     *     空间复杂度：O(1)。只使用了常数个变量。
     *
     */
    //public int maxProfit(int[] prices) {
    //    int maxprofit = 0;
    //    for (int i = 0; i < prices.length-1; i++) {
    //        for (int j = i+1; j < prices.length; j++) {
    //            int profit = prices[j] - prices[i];
    //            if (profit > maxprofit) maxprofit = profit;
    //        }
    //    }
    //    return maxprofit;
    //}

    /**
     * 一次遍历： int[] prices = {7,1,5,3,6,4};
     */
    //public int maxProfit(int prices[]) {
    //    int minprice = Integer.MAX_VALUE;
    //    int maxprofit = 0;
    //    for (int i = 0; i < prices.length; i++) {
    //        if (prices[i] < minprice) {
    //            minprice = prices[i];  //minprices 为prices[i]之前的最小值
    //        } else if (prices[i] - minprice > maxprofit) {
    //            maxprofit = prices[i] - minprice;
    //        }
    //    }
    //    return maxprofit;
    //}


}




































