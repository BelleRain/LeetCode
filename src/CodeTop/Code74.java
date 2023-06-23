package CodeTop;

/**
 * @author mxy
 * @create 2023-06-23 18:38
 */

/**
 * 74. 搜索二维矩阵   链接：https://leetcode.cn/problems/search-a-2d-matrix
 * 编写一个高效的算法来判断 m x n 矩阵中，是否存在一个目标值。该矩阵具有如下特性：
 *
 * 每行中的整数从左到右按升序排列。
 * 每行的第一个整数大于前一行的最后一个整数。
 *  
 * 示例 1：
 * 输入：matrix = [[1,3,5,7],
 *                [10,11,16,20],
 *                [23,30,34,60]], target = 3
 * 输出：true
 *
 * 示例 2：
 * 输入：matrix = [[1,3,5,7],
 *                [10,11,16,20],
 *                [23,30,34,60]], target = 13
 * 输出：false
 *  
 *
 * 提示：
 * m == matrix.length
 * n == matrix[i].length
 * 1 <= m, n <= 100
 * -10^4 <= matrix[i][j], target <= 10^4
 *
 */
public class Code74 {

    public static void main(String[] args) {
        int[][] matrix = {
                {1,3,5,7},
                {10,11,16,20},
                {23,30,34,60}
        };
        System.out.println(searchMatrix(matrix, 21));
    }

    /**
     * 题解一：从右上角开始
     * @param matrix
     * @param target
     * @return
     */
    public static boolean searchMatrix(int[][] matrix, int target) {
        if (matrix.length == 0) return false;
        int m = matrix.length, n = matrix[0].length;
        int i = 0, j = n - 1;
        while (i < m && j >= 0) {
            if (target == matrix[i][j]) {
                return true;
            } else if (target > matrix[i][j]) {
                i++;
            }else {
                j--;
            }
        }
        return false;
    }
}


































