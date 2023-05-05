package Top100;

/**
 * @author mxy
 * @create 2022-12-02 10:55
 */

import java.util.LinkedList;
import java.util.Queue;

/**
 * 200. 岛屿数量    链接：https://leetcode.cn/problems/number-of-islands
 * 给你一个由 '1'（陆地）和 '0'（水）组成的的二维网格，请你计算网格中岛屿的数量。
 * 岛屿总是被水包围，并且每座岛屿只能由水平方向和/或竖直方向上相邻的陆地连接形成。
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
 *
 * 提示：
 * m == grid.length
 * n == grid[i].length
 * 1 <= m, n <= 300
 * grid[i][j] 的值为 '0' 或 '1'
 *
 */
public class Top200 {

    public static void main(String[] args) {
        Top200 top200 = new Top200();
        char[][] grid = {
                {'1','1','0','0','0'},
                {'1','1','0','0','0'},
                {'0','0','1','0','0'},
                {'0','0','0','1','1'}
        };
        System.out.println(top200.numIslands(grid));
    }

    /**
     * 题解链接：岛屿类问题的通用解法、DFS 遍历框架
     *      https://leetcode.cn/problems/number-of-islands/solution/dao-yu-lei-wen-ti-de-tong-yong-jie-fa-dfs-bian-li-/
     */

    /**
     * 方法一： DFS
     * @param grid
     * @return
     */
    public int numIslands(char[][] grid) {
        int count = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == '1'){ //遇到陆地，向下搜索
                    dfs(grid,i,j);
                    count++;
                }
            }
        }
        return count;
    }

    private void dfs(char[][] grid, int r, int c) {
        //判断 base case，如果越界，则直接返回
        if (!inArea(grid,r,c)){
            return;
        }
        //遇到水返回
        if (grid[r][c] != '1'){
            return;
        }
        grid[r][c] = '2'; //标记遍历过的点。
        /*
        在一些题解中，可能会把「已遍历过的陆地格子」标记为和海洋格子一样的 0，美其名曰「陆地沉没方法」，
        即遍历完一个陆地格子就让陆地「沉没」为海洋。这种方法看似很巧妙，但实际上有很大隐患，
        因为这样我们就无法区分「海洋格子」和「已遍历过的陆地格子」了。如果题目更复杂一点，这很容易出 bug。
         */
        //四个方向进行递归搜索
        dfs(grid, r - 1, c);
        dfs(grid, r + 1, c);
        dfs(grid, r, c - 1);
        dfs(grid, r, c + 1);

    }

    private boolean inArea(char[][] grid, int r, int c) {
        if (r < 0 || r >= grid.length || c < 0 || c >= grid[0].length){
            return false;
        }
        return true;
    }

    /**
     * 方法二： BFS
     *  1、主循环和思路一类似，不同点是在于搜索某岛屿边界的方法不同。
     *  2、bfs 方法： 借用一个队列 queue，判断队列首部节点 (i, j) 是否未越界且为 1：
     *     若是则置零（删除岛屿节点），并将此节点上下左右节点 (i+1,j),(i-1,j),(i,j+1),(i,j-1) 加入队列；
     *     若不是则跳过此节点； 循环 pop 队列首节点，直到整个队列为空，此时已经遍历完此岛屿。
     * @param grid
     * @return
     */
    public int numIslands1(char[][] grid) {
        int count = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++){
                if (grid[i][j] == '1'){
                    bfs(grid,i,j);
                    count++;
                }
            }
        }
        return count;
    }

    private void bfs(char[][] grid, int i, int j) {
        Queue<int[]> list = new LinkedList<>();
        list.add(new int[]{i,j});
        while (!list.isEmpty()){
            int[] cur = list.remove();
            int r = cur[0], c = cur[1];
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
