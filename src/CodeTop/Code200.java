package CodeTop;

/**
 * @author mxy
 * @create 2023-04-13 15:17
 */

import java.util.Deque;
import java.util.LinkedList;

/**
 * 200. 岛屿数量    https://leetcode.cn/problems/number-of-islands/
 * 给你一个由 '1'（陆地）和 '0'（水）组成的的二维网格，请你计算网格中岛屿的数量。
 * 岛屿总是被水包围，并且每座岛屿只能由水平方向 和 /或竖直方向上相邻的陆地连接形成。
 * 此外，你可以假设该网格的四条边均被水包围。
 *
 * 示例 1：
 * 输入：grid = [
 *   ["1","1","1","1","0"],
 *   ["1","1","0","1","0"],
 *   ["1","1","0","0","0"],
 *   ["0","0","0","0","0"]
 * ]
 * 输出：1
 *
 * 示例 2：
 * 输入：grid = [
 *   ["1","1","0","0","0"],
 *   ["1","1","0","0","0"],
 *   ["0","0","1","0","0"],
 *   ["0","0","0","1","1"]
 * ]
 * 输出：3
 *  
 * 提示：
 * m == grid.length
 * n == grid[i].length
 * 1 <= m, n <= 300
 * grid[i][j] 的值为 '0' 或 '1'
 *
 */
public class Code200 {

    public static void main(String[] args) {
        char[][] grid1 = {
                {'1','1','1','1','0'},
                {'1','1','0','1','0'},
                {'1','1','0','0','0'},
                {'0','0','0','0','0'}
        };

        char[][] grid2 = {
                {'1','1','0','0','0'},
                {'1','1','0','0','0'},
                {'0','0','1','0','0'},
                {'0','0','0','1','1'}
        };

        System.out.println(numIslands1(grid1));
        System.out.println(numIslands2(grid2));
    }

    /**
     * 递归实现：
     * @param grid
     * @return
     */
    public static int numIslands1(char[][] grid) {
        if (grid.length == 0) return 0;
        int count = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == '1'){
                    dfs(grid,i,j);
                    count++;
                }
            }
        }
        return count;
    }

    private static void dfs(char[][] grid, int i, int j) {
        if (!inArea(grid,i,j)){
            return;
        }

        //非岛屿的时候返回
        if (grid[i][j] != '1'){
            return;
        }

        //将遍历过的 1 置为 '2',防止重复遍历
        if (grid[i][j] == '1'){
            grid[i][j] = '2';
        }

        //从左、上、右、下 四个方向遍历
        dfs(grid, i, j - 1);
        dfs(grid, i - 1, j);
        dfs(grid, i, j + 1);
        dfs(grid, i + 1, j);
        for (char[] chars : grid) {
            System.out.println(chars);
        }
        System.out.println("==================");
    }

    private static boolean inArea(char[][] grid, int i, int j) {
        if (i < 0 || i >= grid.length || j < 0 || j >= grid[0].length){
            return false;
        }
        return true;
    }


    /**
     * 迭代实现
     * @param grid
     * @return
     */
    public static int numIslands2(char[][] grid) {
        if (grid.length == 0) return 0;
        int count = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == '1'){
                    bfs(grid,i,j);
                    count++;
                }
            }
        }
        return count;
    }

    private static void bfs(char[][] grid, int i, int j) {
        Deque<int[]> list = new LinkedList<>();
        list.add(new int[]{i,j});
        //当list为空时，一个岛屿遍历完毕
        while (!list.isEmpty()){
            int[] cur = list.poll();
            int r = cur[0];
            int c = cur[1];
            if (r >= 0 && r < grid.length && c >= 0 && c < grid[0].length && grid[r][c] == '1'){
                grid[r][c] = '2';
                list.add(new int[]{r - 1,c});
                list.add(new int[]{r + 1,c});
                list.add(new int[]{r,c - 1});
                list.add(new int[]{r,c + 1});
            }
        }
    }
}































