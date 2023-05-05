package Subject.岛屿问题;

/**
 * @author mxy
 * @create 2022-12-06 15:19
 */

/**
 * 463. 岛屿的周长   链接：https://leetcode.cn/problems/island-perimeter
 * 给定一个 row x col 的二维网格地图 grid ，其中：grid[i][j] = 1 表示陆地， grid[i][j] = 0 表示水域。
 * 网格中的格子 水平和垂直 方向相连（对角线方向不相连）。整个网格被水完全包围，但其中恰好有一个岛屿
 * （或者说，一个或多个表示陆地的格子相连组成的岛屿）。
 * 岛屿中没有“湖”（“湖” 指水域在岛屿内部且不和岛屿周围的水相连）。格子是边长为 1 的正方形。
 * 网格为长方形，且宽度和高度均不超过 100 。计算这个岛屿的周长。
 *
 * 示例 1：
 * 输入：grid = [[0,1,0,0],[1,1,1,0],[0,1,0,0],[1,1,0,0]]
 * 输出：16
 * 解释：它的周长是上面图片中的 16 个黄色的边
 *
 * 示例 2：
 * 输入：grid = [[1]]
 * 输出：4
 *
 * 示例 3：
 * 输入：grid = [[1,0]]
 * 输出：4
 *
 * 提示：
 * row == grid.length
 * col == grid[i].length
 * 1 <= row, col <= 100
 * grid[i][j] 为 0 或 1
 *
 */
public class Code463 {

    public static void main(String[] args) {
        Code463 code463 = new Code463();
        int[][] grid = {
                {0,1,0,0},
                {1,1,1,0},
                {0,1,0,0},
                {1,1,0,0}
        };
        System.out.println(code463.islandPerimeter(grid));
    }

    /**
     * 题解链接：https://leetcode.cn/problems/number-of-islands/solution/dao-yu-lei-wen-ti-de-tong-yong-jie-fa-dfs-bian-li-/
     * 执行用时： 6 ms , 在所有 Java 提交中击败了 64.86% 的用户
     * 内存消耗： 41.9 MB , 在所有 Java 提交中击败了 94.18% 的用户
     * @param grid
     * @return
     */
    /*public int islandPerimeter(int[][] grid) {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1){
                    //题中显示一个岛屿，故只计算一个岛屿即可
                    return dfs(grid,i,j);
                }
            }
        }
        return 0;
    }*/

    /*private int dfs(int[][] grid, int i, int j) {
        if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length) return 1; //越界证明在边上，周长 加 1
        if (grid[i][j] == 0) return 1; //遇到水周长加1
        if (grid[i][j] != 1) return 0; //既不为0，也不为1，只可能为 2 ，即遇到的为 遍历过的点
        grid[i][j] = 2;
        return dfs(grid, i - 1, j) +
               dfs(grid, i + 1, j) +
               dfs(grid, i, j - 1) +
               dfs(grid, i, j + 1);
    }*/

    /**
     * 方法二: 遍历  题解链接：https://leetcode.cn/problems/island-perimeter/solution/shou-hua-tu-jie-463-dao-yu-de-zhou-chang-by-xiao_b/
     * 思路：
     * 1、一块土地原则上会带来 4 个周长，但岛上的土地存在接壤，每一条接壤，会减掉 2 个边长。
     * 2、所以，总周长 = 4 * 土地个数 - 2 * 接壤边的条数。
     * 3、遍历矩阵，遍历到土地，就 land++，如果它的右/下边也是土地，则 border++，遍历结束后代入公式。
     * @param grid
     * @return
     */
    public int islandPerimeter(int[][] grid) {
        int land = 0, border = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == 1) {
                    land++;
                    //从[0][0]逐个遍历，向下或向右一个是否接壤即可,排除边界(最后一行和最后一列)
                    if (i < grid.length - 1 && grid[i + 1][j] == 1){ //向下接壤
                        border++;
                    }
                    if (j < grid[0].length - 1 && grid[i][j + 1] == 1){
                        border++;
                    }
                }
            }
        }
        return land * 4 - 2 * border;
    }
}
