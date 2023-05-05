package Top100;

/**
 * @author mxy
 * @create 2023-01-08 9:19
 */

import java.util.Arrays;

/**
 * 279. 完全平方数   链接：https://leetcode.cn/problems/perfect-squares
 * 给你一个整数 n ，返回 和为 n 的完全平方数的最少数量 。
 * 完全平方数 是一个整数，其值等于另一个整数的平方；换句话说，其值等于一个整数自乘的积。
 * 例如，1、4、9 和 16 都是完全平方数，而 3 和 11 不是。
 *
 * 示例 1：
 * 输入：n = 12
 * 输出：3
 * 解释：12 = 4 + 4 + 4
 *
 * 示例 2：
 * 输入：n = 13
 * 输出：2
 * 解释：13 = 4 + 9
 *  
 * 提示：
 * 1 <= n <= 10^4
 *
 */
public class Top279 {

    public static void main(String[] args) {
        Top279 top279 = new Top279();
        System.out.println(top279.numSquares(4));
    }

    /**
     * 题解链接：https://leetcode.cn/problems/perfect-squares/solution/wan-quan-ping-fang-shu-by-leetcode-solut-t99c/
     */

    /**
     * 方法一：动态规划
     * 1、dp[i] : 最少需要多少个数的平方来表示整数 i。
     * 2、这些数必然落在区间 [1,sqrt(n)]
     * 3、枚举到 j，取 若干数的平方，构成 i-j^2
     * 4、状态转移方程： dp[i] = 1 + min(dp[i - j * j]) j 的范围 [1,sqrt(i)]
     *          （i = j * j + i - j * j）
     * 5、最终的动态转移方程： dp[i] = min(dp[i]),1 + dp[i - j * j]) ,最坏的情况就是每次加1 （dp[i] = i）,例如 4 = 1 + 1 + 1 + 1
     * 复杂度分析:
     *      时间复杂度：O(n*sqrt{n})，其中 n 为给定的正整数。状态转移方程的时间复杂度为 O(sqrt{n})，共需要计算 n 个状态，
     *          因此总时间复杂度为 O(n*sqrt{n})。
     *      空间复杂度：O(n)。我们需要 O(n)的空间保存状态。
     * @param n
     * @return
     */
    /*public int numSquares(int n) {
        int[] dp = new int[n + 1];
        for (int i = 1; i < n + 1; i++) {
            dp[i] = i; //最坏的情况就是每次加1,例如 4 = 1 + 1 + 1 + 1
            //j的范围为 [1,sqrt(n)], j^2的范围为 [1,n],当 j^2 = n 时，为本身，dp[i] = 1;
            for (int j = 1; i - j * j >= 0; j++) {
                dp[i] = Math.min(dp[i], dp[i - j * j] + 1);
            }
        }
        //System.out.println(Arrays.toString(dp));
        return dp[n];
    }*/

    /**
     * 方法二： 数学 （四平方和定理）（拉格朗日四平方和定理）
     * 复杂度分析：
     *      时间复杂度：O(sqrt{n})，其中 n 为给定的正整数。最坏情况下答案为 3，我们需要运行所有的判断，而判断答案是否为 1 的时间复杂度为 O(1)，
     *          判断答案是否为 4 的时间复杂度为 O(logn)，剩余判断为 O(sqrt(n))，因此总时间复杂度为 O(log n + sqrt n) = O(sqrt n)
     *      空间复杂度：O(1)。我们只需要常数的空间保存若干变量。
     * @param n
     * @return
     */
    public int numSquares(int n) {
        if (isPerfectSquare(n)) return 1;
        if (checkAnswer4(n)) return 4;
        for (int i = 1; i <= n; i++) {
            int j = n - i * i;
            if (isPerfectSquare(j)) return 2;
        }
        return 3;
    }

    //判断是否能表示为 4^k*(8m+7)
    private boolean checkAnswer4(int n) {
        while (n % 4 == 0){
            n /= 4;
        }
        return n % 8 == 7;
    }

    //判断是否为完全平方数
    private boolean isPerfectSquare(int n) {
        int y = (int)Math.sqrt(n);
        return y * y == n;
    }
}
