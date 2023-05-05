package Swordoffer.Dynamic;

/**
 * @author mxy
 * @create 2022-09-25 10:00
 */

/**
 * 礼物的最大价值
 * 在一个 m*n 的棋盘的每一格都放有一个礼物，每个礼物都有一定的价值（价值大于 0）。
 * 你可以从棋盘的左上角开始拿格子里的礼物，并每次向右或者向下移动一格、直到到达棋盘的右下角。
 * 给定一个棋盘及其上面的礼物的价值，请计算你最多能拿到多少价值的礼物？
 *
 * 示例 1:
 * 输入:
 * [
 *   [1,3,1],
 *   [1,5,1],
 *   [4,2,1]
 * ]
 * 输出: 12
 * 解释: 路径 1→3→5→2→1 可以拿到最多价值的礼物
 *
 */
public class Offer47 {

    public static void main(String[] args) {
        int[][] matrix = {
                {1,3,1},
                {1,5,1},
                {4,2,1}
        };

        Offer47 offer = new Offer47();
        offer.maxValue(matrix);

    }

    //执行用时： 3 ms , 在所有 Java 提交中击败了 18.83% 的用户
    //内存消耗： 44 MB , 在所有 Java 提交中击败了 54.38% 的用户
    //大量的重复判断消耗了时间
    //public int maxValue(int[][] grid) {
    //    int m = grid.length,n = grid[0].length;
    //    for (int i = 0; i < m; i++) {
    //        for (int j = 0; j < n; j++) {
    //            if (i == 0 && j == 0) continue;
    //            else if (i == 0 && j != 0) grid[i][j] = grid[i][j] + grid[i][j-1];
    //            else if (i != 0 && j == 0) grid[i][j] = grid[i][j] + grid[i-1][j];
    //            else if (i != 0 && j != 0) grid[i][j] = grid[i][j] + Swordoffer.Math.max(grid[i-1][j],grid[i][j-1]);
    //        }
    //    }
    //    return grid[m-1][n-1];
    //}

    /**
     * 动态规划： 原文链接：链接：https://leetcode.cn/problems/li-wu-de-zui-da-jie-zhi-lcof/solution/mian-shi-ti-47-li-wu-de-zui-da-jie-zhi-dong-tai-gu/
     * 解题思路：
     *      题目说明：从棋盘的左上角开始拿格子里的礼物，并每次 向右 或者 向下 移动一格、直到到达棋盘的右下角。
     *      根据题目说明，易得某单元格只可能从上边单元格或左边单元格到达。
     *   设 f(i, j) 为从棋盘左上角走至单元格 (i ,j) 的礼物最大累计价值，易得到以下递推关系：f(i,j) 等于 f(i,j-1) 和 f(i-1,j) 中的较大值加上当前单元格礼物价值 grid(i,j) 。
     *           f(i,j)=max[f(i,j−1),f(i−1,j)]+grid(i,j)
     *       因此，可用动态规划解决此问题，以上公式便为转移方程。
     *动态规划解析：
     *      状态定义： 设动态规划矩阵 dp ，dp(i,j) 代表从棋盘的左上角开始，到达单元格 (i,j) 时能拿到礼物的最大累计价值。
     *      转移方程：
     *              当 i = 0 且 j = 0 时，为起始元素；
     *              当 i = 0 且 j != 0 时，为矩阵第一行元素，只可从左边到达；
     *              当 i != 0 且 j = 0 时，为矩阵第一列元素，只可从上边到达；
     *              当 i != 0 且 j != 0 时，可从左边或上边到达；
     *          dp(i,j)=:
     *                  grid(i,j)                          ,i=0,j=0
     *                  grid(i,j)+dp(i,j−1)                ,i=0,j!=0
     *                  grid(i,j)+dp(i−1,j)                ,i!=0,j=0
     *                  grid(i,j)+max[dp(i−1,j),dp(i,j−1)] ,i!=0,j!=0
     * ​
     *      初始状态： dp[0][0]=grid[0][0] ，即到达单元格 (0,0) 时能拿到礼物的最大累计价值为 grid[0][0] ；
     *      返回值： dp[m−1][n−1] ，m, n 分别为矩阵的行高和列宽，即返回 dp 矩阵右下角元素。
     * 空间复杂度优化：
     *      由于 dp[i][j] 只与 dp[i-1][j] , dp[i][j-1] , grid[i][j] 有关系，因此可以将原矩阵 grid 用作 dp 矩阵，即直接在 grid 上修改即可。
     *      应用此方法可省去 dp 矩阵使用的额外空间，因此空间复杂度从 O(MN) 降至 O(1) 。
     *复杂度分析：
     * 时间复杂度 O(MN) ： M, N 分别为矩阵行高、列宽；动态规划需遍历整个 grid 矩阵，使用 O(MN) 时间。
     * 空间复杂度 O(1) ： 原地修改使用常数大小的额外空间。
     *
     * @param grid
     * @return
     */
    //执行用时： 2 ms , 在所有 Java 提交中击败了 97.76% 的用户
    //内存消耗： 43.7 MB , 在所有 Java 提交中击败了 94.29% 的用户
    //public int maxValue(int[][] grid) {
    //    int m = grid.length, n = grid[0].length;
    //    for (int i = 0; i < m; i++) {
    //        for (int j = 0; j < n; j++) {
    //            if (i == 0 && j == 0) continue;
    //            if (i == 0) grid[i][j] = grid[i][j] + grid[i][j-1];
    //            else if (j == 0) grid[i][j] = grid[i][j] + grid[i-1][j];
    //            else grid[i][j] = grid[i][j] + Swordoffer.Math.max(grid[i-1][j],grid[i][j-1]);
    //        }
    //    }
    //    return grid[m-1][n-1];
    //}

    /**
     * 以上代码逻辑清晰，和转移方程直接对应，但仍可提升效率：
     * 当 grid 矩阵很大时， i = 0 或 j = 0 的情况仅占极少数，相当循环每轮都冗余了一次判断。
     * 因此，可先初始化矩阵第一行和第一列，再开始遍历递推。
     */
    public int maxValue(int[][] grid){
        int m = grid.length, n = grid[0].length;
        for (int j = 1; j < n; j++) { //初始化第一行
            grid[0][j] = grid[0][j-1] + grid[0][j];
        }
        for (int i = 1; i < m; i++) { //初始化第一列
            grid[i][0] = grid[i][0] + grid[i-1][0];
        }
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                grid[i][j] = grid[i][j] + Math.max(grid[i-1][j],grid[i][j-1]);
            }
        }
        return grid[m-1][n-1];
    }

    //多开一行一列，简洁代码
    //public int maxValue(int[][] grid) {
    //    int row = grid.length;
    //    int column = grid[0].length;
    //    //dp[i][j]表示从grid[0][0]到grid[i - 1][j - 1]时的最大价值
    //    int[][] dp = new int[row + 1][column + 1];
    //    for (int i = 1; i <= row; i++) {
    //        for (int j = 1; j <= column; j++) {
    //            dp[i][j] = Swordoffer.Math.max(dp[i - 1][j], dp[i][j - 1]) + grid[i - 1][j - 1];
    //        }
    //    }
    //    return dp[row][column];
    //}
}



































