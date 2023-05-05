package CodeTop;

/**
 * @author mxy
 * @create 2023-05-02 9:59
 */

/**
 * 221. 最大正方形   https://leetcode.cn/problems/maximal-square
 * 在一个由 '0' 和 '1' 组成的二维矩阵内，找到只包含 '1' 的最大正方形，并返回其面积。
 *
 * 示例 1：
 * 输入：matrix = [["1","0","1","0","0"],
 *                ["1","0","1","1","1"],
 *                ["1","1","1","1","1"],
 *                ["1","0","0","1","0"]]
 * 输出：4
 *
 * 示例 2：
 * 输入：matrix = [["0","1"],
 *                ["1","0"]]
 * 输出：1
 *
 * 示例 3：
 * 输入：matrix = [["0"]]
 * 输出：0
 *  
 * 提示：
 * m == matrix.length
 * n == matrix[i].length
 * 1 <= m, n <= 300
 * matrix[i][j] 为 '0' 或 '1'
 *
 */
public class Code221 {

    public static void main(String[] args) {
        char[][] matrix = {
                {'1','0','1','0','0'},
                {'1','0','1','1','1'},
                {'1','1','1','1','1'},
                {'1','0','0','1','0'}
        };
        System.out.println(maximalSquare(matrix));
    }

    public static int maximalSquare(char[][] matrix) {
        if (matrix.length == 0) return 0;
        int maxSize = Integer.MIN_VALUE;
        int[][] dp = new int[matrix.length + 1][matrix[0].length + 1];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == '1'){
                    //左、上、左上的最小值 + 1
                    dp[i + 1][j + 1] = Math.min(dp[i][j], Math.min(dp[i][j + 1], dp[i + 1][j])) + 1;
                    maxSize = Math.max(dp[i + 1][j + 1], maxSize);
                }
            }
        }
        return maxSize * maxSize;
    }
}

































