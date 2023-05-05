package Swordoffer.Simulate;

/**
 * @author mxy
 * @create 2022-10-25 14:21
 */

/**
 * 顺时针打印矩阵
 * 输入一个矩阵，按照从外向里以顺时针的顺序依次打印出每一个数字。
 *
 * 示例 1：
 * 输入：matrix = [[1,2,3],[4,5,6],[7,8,9]]
 * 输出：[1,2,3,6,9,8,7,4,5]
 *
 * 示例 2：
 * 输入：matrix = [[1,2,3,4],[5,6,7,8],[9,10,11,12]]
 * 输出：[1,2,3,4,8,12,11,10,9,5,6,7]
 *
 * 限制：
 *
 * 0 <= matrix.length <= 100
 * 0 <= matrix[i].length <= 100
 */
public class Offer29 {

    /**
     * 题解链接：https://leetcode.cn/problems/shun-shi-zhen-da-yin-ju-zhen-lcof/solution/mian-shi-ti-29-shun-shi-zhen-da-yin-ju-zhen-she-di/
     * 方法一：
     * 复杂度分析：
     * 时间复杂度 O(MN) ： M, N 分别为矩阵行数和列数。
     * 空间复杂度 O(1) ： 四个边界 l , r , t , b 使用常数大小的 额外 空间（ res 为必须使用的空间）。
     * @param matrix
     * @return
     */
    public int[] spiralOrder(int[][] matrix) {
        if (matrix.length == 0) return new int[0];
        //l:左边界 r:右边界 t:上边界 b:下边界
        int l = 0,r = matrix[0].length - 1,t = 0,b = matrix.length - 1,x = 0;
        int[] res = new int[(r+1) * (b+1)];
        while (true){
            //从左至右
            for (int i = l; i <= r; i++) {
                res[x++] = matrix[t][i];
            }
            if (++t > b) break; //上边界 累加超出 下边界
            //从上至下
            for (int i = t; i <= b; i++) {
                res[x++] = matrix[i][r];
            }
            if (--r < l) break; //右边界 累减低于 左边界
            //从右至左
            for (int i = r; i >= l; i--) {
                res[x++] = matrix[b][i];
            }
            if (--b < t) break; //下边界 累减低于 上边界
            //从下至上
            for (int i = b; i >= t; i--) {
                res[x++] = matrix[i][l];
            }
            if (++l > r) break; //左边界 累加超出 右边界
        }
        return res;
    }

    /**
     * 题解链接：https://leetcode.cn/problems/shun-shi-zhen-da-yin-ju-zhen-lcof/solution/shun-shi-zhen-da-yin-ju-zhen-by-leetcode-solution/
     * 方法二：模拟
     * 复杂度分析：
     *   时间复杂度：O(mn)，其中 m 和 n 分别是输入矩阵的行数和列数。矩阵中的每个元素都要被访问一次。
     *   空间复杂度：O(mn)。需要创建一个大小为 m×n 的矩阵 visited 记录每个位置是否被访问过。
     * @param matrix
     * @return
     */
    //public int[] spiralOrder(int[][] matrix) {
    //    if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
    //        return new int[0];
    //    }
    //    int rows = matrix.length, columns = matrix[0].length;
    //    boolean[][] visited = new boolean[rows][columns];
    //    int total = rows * columns;
    //    int[] order = new int[total];
    //    int row = 0, column = 0;
    //    int[][] directions = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    //    int directionIndex = 0;
    //    for (int i = 0; i < total; i++) {
    //        order[i] = matrix[row][column];
    //        visited[row][column] = true;
    //        int nextRow = row + directions[directionIndex][0], nextColumn = column + directions[directionIndex][1];
    //        if (nextRow < 0 || nextRow >= rows || nextColumn < 0 || nextColumn >= columns || visited[nextRow][nextColumn]) {
    //            directionIndex = (directionIndex + 1) % 4;
    //        }
    //        row += directions[directionIndex][0];
    //        column += directions[directionIndex][1];
    //    }
    //    return order;
    //}

}





























