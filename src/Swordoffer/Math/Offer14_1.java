package Swordoffer.Math;

/**
 * @author mxy
 * @create 2022-10-22 11:50
 */

/**
 * 剪绳子
 * 给你一根长度为 n 的绳子，请把绳子剪成整数长度的 m 段（m、n都是整数，n>1并且m>1），每段绳子的长度记为 k[0],k[1]...k[m-1] 。请问 k[0]*k[1]*...*k[m-1] 可能的最大乘积是多少？
 * 例如，当绳子的长度是8时，我们把它剪成长度分别为2、3、3的三段，此时得到的最大乘积是18。
 *
 * 示例 1：
 * 输入: 2
 * 输出: 1
 * 解释: 2 = 1 + 1, 1 × 1 = 1
 *
 * 示例 2:
 * 输入: 10
 * 输出: 36
 * 解释: 10 = 3 + 3 + 4, 3 × 3 × 4 = 36
 *
 * 提示：
 * 2 <= n <= 58
 */
public class Offer14_1 {



    /**
     * 题解链接：https://leetcode.cn/problems/jian-sheng-zi-lcof/solution/mian-shi-ti-14-i-jian-sheng-zi-tan-xin-si-xiang-by/
     * 解法一：数学推导 关键点：求 a 段 长度为 x 的绳子相乘时的 极大值点。极大值点为 x = 3 （每段绳子的长度）
     *复杂度分析：
     * 时间复杂度 O(1) ： 仅有求整、求余、次方运算。
     *      求整和求余运算：资料提到不超过机器数的整数可以看作是 O(1) ；
     *      幂运算：查阅资料，提到浮点取幂为 O(1) 。
     * 空间复杂度 O(1) ： 变量 a 和 b 使用常数大小额外空间。
     * @param n
     * @return
     */
    public int cuttingRope(int n) {
        if(n <= 3) return n-1;
        int a = n/3,b = n % 3;
        //刚好等分长度为3的a段
        if (b == 0) return (int)Math.pow(3, a);
        // a个3 + 1个1：因为 1*3 < 2*2,所以将 一个1+3 拆分为 2*2
        if (b == 1) return (int)Math.pow(3, a-1) * 4;
        //a个3 + 1个2 ：直接返回 3^a * 2
        return (int)Math.pow(3, a) * 2;
    }

    /**
     * 题解链接：https://leetcode.cn/problems/jian-sheng-zi-lcof/solution/jian-sheng-zi-by-leetcode-solution-xku9/
     */

    /**
     * 解法二：动态规划
     *   每个正整数对应的最大乘积取决于比它小的正整数对应的最大乘积
     * 复杂度分析
     *
     * 时间复杂度：O(n^2)，其中 n 是给定的正整数。对于从 2 到 n 的每一个整数都要计算对应的 dp 值，计算一个整数对应的 dp 值需要 O(n) 的时间复杂度，因此总时间复杂度是 O(n^2)
     * 空间复杂度：O(n)，其中 n 是给定的正整数。创建一个数组 dp，其长度为 n+1。
     */
    //public int cuttingRope(int n) {
    //    int[] dp = new int[n+1];
    //    for (int i = 2; i <= n; i++) {
    //        int curMax = 0;
    //        for (int j = 1; j < i; j++) {
    //            curMax = Swordoffer.Math.max(curMax, Swordoffer.Math.max(j*(i-j), j*dp[i-j]));
    //        }
    //        dp[i] = curMax;
    //    }
    //    return dp[n];
    //}

    /**
     * 解法三：优化的动态规划
     * @param n
     * @return
     * 复杂度分析：
     *    时间复杂度：O(n)，其中 n 是给定的正整数。和方法一相比，计算每个整数对应的 dp 的值的时间复杂度降到 O(1)，因此总时间复杂度降到 O(n)。
     *    空间复杂度：O(n)，其中 n 是给定的正整数。创建一个数组 dp，其长度为 n+1。
     */
    //public int cuttingRope(int n) {
    //    if (n <= 3) {
    //        return n - 1;
    //    }
    //    int[] dp = new int[n + 1];
    //    dp[2] = 1;
    //    for (int i = 3; i <= n; i++) {
    //        dp[i] = Swordoffer.Math.max(Swordoffer.Math.max(2 * (i - 2), 2 * dp[i - 2]), Swordoffer.Math.max(3 * (i - 3), 3 * dp[i - 3]));
    //    }
    //    return dp[n];
    //}
}

































