package Top100;

/**
 * @author mxy
 * @create 2022-11-22 12:09
 */

import java.util.Arrays;

/**
 * 64. 最小路径和  链接：https://leetcode.cn/problems/minimum-path-sum
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
 */
public class Top64 {

    public static void main(String[] args) {
        Top64 top64 = new Top64();
        //int[][] grid = new int[1][];
        //grid[0] = new int[]{1,3,1};
        int[][] grid = {
                {1,3,1},
                {1,5,1},
                {4,2,1}
        };
        System.out.println(top64.minPathSum(grid));
    }

    /**
     * 题解链接：https://leetcode.cn/problems/minimum-path-sum/solution/zui-xiao-lu-jing-he-by-leetcode-solution/
     * 1、f(i,j) 到达 (i,j) 处的 路径和 : 从上方到达向下  或 从左边向右到达
     *    f(i,j) = f(i-1,j) + grid[i][j] 或 f(i,j) = f(i,j-1) + grid[i][j] 取两者中的最小值
     * 2、f(0,0) = grid[0][0]
     * 3、对于 边界值 f(0,j) = grid[0][j] ; f(i,0) = grid[i][0]
     * 4、返回 f[m-1][n-1]
     * 复杂度分析 ：
     *      时间复杂度：O(mn)，其中 m 和 n 分别是网格的行数和列数。需要对整个网格遍历一次，计算 dp 的每个元素的值。
     *      空间复杂度：O(mn)，其中 m 和 n 分别是网格的行数和列数。创建一个二维数组dp，和网格大小相同。
     *      空间复杂度可以优化，例如每次只存储上一行的 dp 值，则可以将空间复杂度优化到 O(n)。
     * @param grid
     * @return
     */
    public int minPathSum(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int[][] f = new int[m][n];
        f[0][0] = grid[0][0];
        for (int i = 1; i < m; i++) f[i][0] = f[i-1][0] + grid[i][0];
        for (int j = 1; j < n; j++) f[0][j] = f[0][j-1] + grid[0][j];
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                f[i][j] = Math.min(f[i-1][j] + grid[i][j], f[i][j-1] + grid[i][j]);
            }
        }
        //for (int[] ints : f) {
        //    System.out.println(Arrays.toString(ints));
        //}
        return f[m-1][n-1];
    }

    /**
     * 空间优化：在原数组上面改变，不使用额外空间
     * 题解链接：https://leetcode.cn/problems/minimum-path-sum/solution/zui-xiao-lu-jing-he-dong-tai-gui-hua-gui-fan-liu-c/
     * @param grid
     * @return
     */
    public int minPathSum2(int[][] grid) {
        for(int i = 0; i < grid.length; i++) {
            for(int j = 0; j < grid[0].length; j++) {
                if(i == 0 && j == 0) continue;
                else if(i == 0)  grid[i][j] = grid[i][j - 1] + grid[i][j];
                else if(j == 0)  grid[i][j] = grid[i - 1][j] + grid[i][j];
                else grid[i][j] = Math.min(grid[i - 1][j], grid[i][j - 1]) + grid[i][j];
            }
        }
        return grid[grid.length - 1][grid[0].length - 1];
    }

    /**
     * 空间优化：二维变一维，不改变原数组
     * @param grid
     * @return
     */
    public int minPathSum3(int[][] grid) {
        int len = grid[0].length; //len是列数
        int[] dp = new int[len];
        dp[0] = grid[0][0];
        for (int i = 1; i < len; i++)
            dp[i]=dp[i-1]+grid[0][i]; // 第一行的状态
        for (int i = 1; i < grid.length; i++) {
            dp[0] = dp[0] + grid[i][0]; //每次首行 第一个 值 的 状态
            for (int j = 1; j < len; j++)
                //本次 dp[j] 的值 = 左边d[j-1]的值 加上grid[i][j] 与 上一次 d[j]的值 加上grid[i][j] 两者之间的最小值
                dp[j] = Math.min(dp[j-1]+grid[i][j], dp[j]+grid[i][j]);
        }
        return dp[len-1];
    }

}
