package Top100;

/**
 * @author mxy
 * @create 2022-11-22 10:44
 */

import java.util.Arrays;

/**
 * 62. 不同路径  链接：https://leetcode.cn/problems/unique-paths
 * 一个机器人位于一个 m x n 网格的左上角 （起始点在下图中标记为 “Start” ）。
 * 机器人每次只能向下或者向右移动一步。机器人试图达到网格的右下角（在下图中标记为 “Finish” ）。
 * 问总共有多少条不同的路径？
 *
 * 示例 1：
 * 输入：m = 3, n = 7
 * 输出：28
 *
 * 示例 2：
 * 输入：m = 3, n = 2
 * 输出：3
 * 解释：
 * 从左上角开始，总共有 3 条路径可以到达右下角。
 * 1. 向右 -> 向下 -> 向下
 * 2. 向下 -> 向下 -> 向右
 * 3. 向下 -> 向右 -> 向下
 *
 * 示例 3：
 * 输入：m = 7, n = 3
 * 输出：28
 *
 * 示例 4：
 * 输入：m = 3, n = 3
 * 输出：6
 *
 * 提示：
 * 1 <= m, n <= 100
 * 题目数据保证答案小于等于 2 * 10^9
 */
public class Top62 {

    public static void main(String[] args) {
        Top62 top62 = new Top62();
        System.out.println(top62.uniquePaths(3, 7));
    }

    /**
     * 题解链接：链接：https://leetcode.cn/problems/unique-paths/solution/bu-tong-lu-jing-by-leetcode-solution-hzjf/
     */

    /**
     * 方法一：动态规划
     * 1、状态定义： f(i,j):到达 (i,j) 的路径总数
     * 2、状态方程： 若(i,j)是由 上一步 向下得到 ，则会从 (i-1,j) 走来；若(i,j)是由上一步 向右得到 ，则会从 (i,j-1)走来
     *             f(i,j) = f(i-1,j) + f(i,j-1)
     * 3、初始状态： f(0,0) = 1 ，由左上角 到 左上角 只需一步
     * 4、最终返回: f(m-1,n-1)
     * 5、为方便操作 将 边界 f(0,j) 和 f(0,i) 提前置为 1
     * 复杂度分析
     *      时间复杂度：O(mn)。
     *      空间复杂度：O(mn)，即为存储所有状态需要的空间。注意到 f(i, j) 仅与第 i 行和第 i-1 行的状态有关，
     * 因此我们可以使用滚动数组代替代码中的二维数组，使空间复杂度降低为 O(n)。此外，由于我们交换行列的值并不会对答案产生影响，
     * 因此我们总可以通过交换 m 和 n 使得 m≤n，这样空间复杂度降低至 O(min(m,n))。
     * @param m
     * @param n
     * @return
     */
    public int uniquePaths(int m, int n) {
        int[][] f = new int[m][n];
        for (int i = 0; i < m; i++) f[i][0] = 1;
        for (int j = 0; j < n; j++) f[0][j] = 1;
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                f[i][j] = f[i - 1][j] + f[i][j - 1];
            }
        }
        return f[m-1][n-1];
    }


    /**
     * 动态规划 + 空间优化： https://leetcode.cn/problems/unique-paths/solution/san-chong-shi-xian-xiang-xi-tu-jie-62-bu-4jz1/
     * 算法思路：
     *  1、dp[i][j] 的值来自 dp[i-1][j] 和 dp[i][j-1] 即 只需要上一行的值 就可以了 ，上上一行的并不需要了，故可以利用滚动数组优化空间。
     *  2、dp[j] = dp[j] + dp[j-1] ; 本次 dp[j] 的值 = 上一次 dp[j] 的值 + 左边 dp[j-1] 的值
     *  3、申请一维数组，长度为n
     * @param m
     * @param n
     * @return
     */
    /*public int uniquePaths(int m, int n) {
        int[] dp = new int[n];
        Arrays.fill(dp, 1);
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                //等式右边的 dp[j] 为上一次计算后的，加上左边的 dp[j-1]即为本次 dp[j] 的结果
                dp[j] = dp[j] + dp[j-1];
            }
        }
        return dp[n-1];
    }*/


    /**
     * 方法二：组合数学
     * 解题思路：
     * 1、从左上角到右下角的过程中，我们需要移动 m+n-2 次，其中有 m-1 次向下移动，n-1 次向右移动。
     *   因此路径的总数，就等于从 m+n-2 次移动中选择 m-1 次向下移动的方案数，即组合数：
     *    C (m+n-2, m-1) = {(m+n-2) * ...* n} / {(m-1)!} = {(m+n-2)!} / {(m-1)! (n-1)!}
     *    （ (m+n-2) * ...* n 一共 m-1 项 ）
     * 复杂度分析
     *   时间复杂度：O(m)。由于我们交换行列的值并不会对答案产生影响，因此我们总可以通过交换 m 和 n 使得 m≤n，这样空间复杂度降低至 O(min(m,n))。
     *   空间复杂度：O(1)。
     * @param m
     * @param n
     * @return
     */
    /*public int uniquePaths(int m, int n) {
        long ans = 1;
        for (int x = n, y = 1; y < m; x++,y++) {
            ans = (ans * x) / y;
        }
        return (int)ans;
    }*/
}
