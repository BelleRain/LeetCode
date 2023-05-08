package CodeTop;

/**
 * @author mxy
 * @create 2023-05-08 10:34
 */

/**
 * 695. 岛屿的最大面积     链接：https://leetcode.cn/problems/max-area-of-island
 * 给你一个大小为 m x n 的二进制矩阵 grid 。
 * 岛屿 是由一些相邻的 1 (代表土地) 构成的组合，这里的「相邻」要求两个 1 必须在 水平或者竖直的四个方向上 相邻。
 * 你可以假设 grid 的四个边缘都被 0（代表水）包围着。
 * 岛屿的面积是岛上值为 1 的单元格的数目。
 * 计算并返回 grid 中最大的岛屿面积。如果没有岛屿，则返回面积为 0 。
 *
 * 示例 1：
 * 输入：grid = [[0,0,1,0,0,0,0,1,0,0,0,0,0],
 *              [0,0,0,0,0,0,0,1,1,1,0,0,0],
 *              [0,1,1,0,1,0,0,0,0,0,0,0,0],
 *              [0,1,0,0,1,1,0,0,1,0,1,0,0],
 *              [0,1,0,0,1,1,0,0,1,1,1,0,0],
 *              [0,0,0,0,0,0,0,0,0,0,1,0,0],
 *              [0,0,0,0,0,0,0,1,1,1,0,0,0],
 *              [0,0,0,0,0,0,0,1,1,0,0,0,0]]
 * 输出：6
 * 解释：答案不应该是 11 ，因为岛屿只能包含水平或垂直这四个方向上的 1 。
 *
 * 示例 2：
 * 输入：grid = [[0,0,0,0,0,0,0,0]]
 * 输出：0
 *
 * 提示：
 * m == grid.length
 * n == grid[i].length
 * 1 <= m, n <= 50
 * grid[i][j] 为 0 或 1
 *
 */
public class Code695 {

    public static void main(String[] args) {
        //int[][] grid = {
        //        {0,0,1,0,0,0,0,1,0,0,0,0,0},
        //        {0,0,0,0,0,0,0,1,1,1,0,0,0},
        //        {0,1,1,0,1,0,0,0,0,0,0,0,0},
        //        {0,1,0,0,1,1,0,0,1,0,1,0,0},
        //        {0,1,0,0,1,1,0,0,1,1,1,0,0},
        //        {0,0,0,0,0,0,0,0,0,0,1,0,0},
        //        {0,0,0,0,0,0,0,1,1,1,0,0,0},
        //        {0,0,0,0,0,0,0,1,1,0,0,0,0}
        //};
        int[][] grid = {
                {0,0,0,0,0,0,0,0}
        };
        System.out.println(maxAreaOfIsland(grid));
    }

    /**
     * 在答题框中使用 尽量避免使用 static变量，官方编辑器容易保存 static变量，更换用例但不会变换结果，故报错
     */
    static int max = 0;
    static int area = 0;
    public static int maxAreaOfIsland(int[][] grid) {
        if (grid.length == 0) return 0;
        int m = grid.length, n = grid[0].length;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                area = 0;
                if (grid[i][j] == 1){
                    dfs(grid,i,j);
                    max = Math.max(max, area);
                }
            }
        }
        return max;
    }

    private static void dfs(int[][] grid, int i, int j) {
        if (!inArea(grid,i,j)) return;
        if (grid[i][j] != 1) return;
        if (grid[i][j] == 1){
            area++;
            grid[i][j] = 2;
        }
        //左、上、右、下
        dfs(grid, i, j - 1);
        dfs(grid, i - 1, j);
        dfs(grid, i, j + 1);
        dfs(grid, i + 1, j);
    }

    private static boolean inArea(int[][] grid, int i, int j) {
        if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length){
            return false;
        }
        return true;
    }


    /**
     * 另一种写法：
     * @param grid
     * @return
     */
    public int maxAreaOfIsland1(int[][] grid) {
        int res = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                int area = dfs1(grid, i, j);
                res = Math.max(res, area);
            }
        }
        return res;
    }

    public int dfs1(int[][] grid, int i, int j) {
        if (!inArea1(grid, i, j)) {
            return 0;
        }
        if (grid[i][j] != 1) {
            return 0;
        }
        grid[i][j] = 2;
        //当前值 + 上边 + 下边 + 右边 + 左边
        //即 岛屿面积 = 四面值 + 当前值
        return 1 + dfs1(grid, i - 1, j) + dfs1(grid, i + 1, j)
                + dfs1(grid, i, j - 1) + dfs1(grid, i, j + 1);
    }

    public boolean inArea1(int[][] grid, int i, int j) {
        return i >= 0 && i < grid.length && j >= 0 && j < grid[i].length;
    }

}








































