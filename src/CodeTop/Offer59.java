package CodeTop;

/**
 * @author mxy
 * @create 2023-06-15 20:13
 */

import java.util.Arrays;

/**
 * 59. 螺旋矩阵 II   链接：https://leetcode.cn/problems/spiral-matrix-ii
 * 给你一个正整数 n ，生成一个包含 1 到 n^2 所有元素，且元素按顺时针顺序螺旋排列的 n x n 正方形矩阵 matrix 。
 *
 * 示例 1：
 * 输入：n = 3
 * 输出：[[1,2,3],
 *       [8,9,4],
 *       [7,6,5]]
 *
 * 示例 2：
 * 输入：n = 1
 * 输出：[[1]]
 *
 * 提示：
 * 1 <= n <= 20
 */
public class Offer59 {

    public static void main(String[] args) {
        int[][] matrix = generateMatrix(3);
        for (int[] grid : matrix) {
            System.out.println(Arrays.toString(grid));
        }
    }

    public static int[][] generateMatrix(int n) {
        int[][] matrix = new int[n][n];
        int up = 0;  //上边界
        int down = matrix.length - 1;  //下边界
        int left = 0; //左边界
        int right = matrix[0].length - 1; //右边界
        int k = 0;
        while (true){
            //向右移动
            for (int i = left; i <= right; i++) {
                matrix[up][i] = ++k;
            }
            if (++up > down) break;
            //向下移动
            for (int i = up; i <= down; i++) {
                matrix[i][right] = ++k;
            }
            //向左移动
            if (left > --right) break;
            for (int i = right; i >= left; i--) {
                matrix[down][i] = ++k;
            }
            //向上移动
            if (up > --down) break;
            for (int i = down; i >= up; i--) {
                matrix[i][left] = ++k;
            }
            if (++left > right) break;
        }
        return matrix;
    }
}


































