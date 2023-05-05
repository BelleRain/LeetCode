package CodeTop;

/**
 * @author mxy
 * @create 2023-04-28 9:00
 */

/**
 * 64. 最小路径和    链接：https://leetcode.cn/problems/minimum-path-sum
 * 给定一个包含非负整数的 m x n 网格 grid ，请找出一条从左上角到右下角的路径，使得路径上的数字总和为最小。
 * 说明：每次只能向下或者向右移动一步。
 *
 * 示例 1：
 * 输入：grid = [[1,3,1],[1,5,1],[4,2,1]]
 * 输出：7
 * 解释：因为路径 1→3→1→1→1 的总和最小。
 *
 * 示例 2：
 * 输入：grid = [[1,2,3],[4,5,6]]
 * 输出：12
 *  
 * 提示：
 * m == grid.length
 * n == grid[i].length
 * 1 <= m, n <= 200
 * 0 <= grid[i][j] <= 100
 *
 */
public class Code64 {

    public static void main(String[] args) {
        int[][] grid = {
                {1,3,1},
                {1,5,1},
                {4,2,1}
        };
        System.out.println(minPathSum(grid));
    }

    /**
     * 动态规划：
     * 1、dp[i][j] : 到达 [i][j]处的 最小路径和  dp[0][0] = grid[0][0]
     * 2、边界点 ： dp[0][j] = dp[0][j - 1] + grid[0][j]
     *             dp[i][0] = dp[i - 1][0] + grid[i][0]
     * 3、非边界点：dp[i][j] = min(dp[i - 1][j] , dp[i][j - 1]) + grid[i][j]
     * 4、return ： dp[m - 1][n - 1]
     * @param grid
     * @return
     */
    public static int minPathSum(int[][] grid) {
        if (grid.length == 0) return 0;
        int m = grid.length, n = grid[0].length;
        int[][] dp = new int[m][n];
        dp[0][0] = grid[0][0];
        for (int i = 1; i < m; i++) {
            dp[i][0] = dp[i - 1][0] + grid[i][0];
        }
        for (int j = 1; j < n; j++) {
            dp[0][j] = dp[0][j - 1] + grid[0][j];
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = Math.min(dp[i - 1][j], dp[i][j - 1]) + grid[i][j];
            }
        }
        return dp[m - 1][n - 1];
    }
}








































