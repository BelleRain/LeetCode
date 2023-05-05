package CodeTop;

/**
 * @author mxy
 * @create 2023-04-28 9:39
 */

/**
 * 48. 旋转图像    链接：https://leetcode.cn/problems/rotate-image
 * 给定一个 n × n 的二维矩阵 matrix 表示一个图像。请你将图像顺时针旋转 90 度。
 * 你必须在 原地 旋转图像，这意味着你需要直接修改输入的二维矩阵。请不要 使用另一个矩阵来旋转图像。
 *
 * 示例 1：
 * 输入：matrix = [[1,2,3],[4,5,6],[7,8,9]]
 * 输出：[[7,4,1],[8,5,2],[9,6,3]]
 *
 * 示例 2：
 * 输入：matrix = [[5,1,9,11],[2,4,8,10],[13,3,6,7],[15,14,12,16]]
 * 输出：[[15,13,2,5],[14,3,4,1],[12,6,8,9],[16,7,10,11]]
 *
 * 提示：
 * n == matrix.length == matrix[i].length
 * 1 <= n <= 20
 * -1000 <= matrix[i][j] <= 1000
 *
 */
public class Code48 {
    public static void main(String[] args) {

    }

    /**
     *  遵从规则：   原来   ---------》  新矩阵
     *       matrix[row][col] = matrix[col][n - 1 - row]
     * 原地旋转：
     * @param matrix
     */
    public static void rotate1(int[][] matrix) {
        int n = matrix.length;
        if(n == 0) return;
        for (int i = 0; i < n/2; i++) {
            for (int j = 0; j < (n + 1)/2; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[n - 1 -j][i];
                matrix[n - 1 - j][i] = matrix[n - i - 1][n - 1 -j];
                matrix[n - 1 - i][n - 1 - j] = matrix[j][n - 1 - i];
                matrix[j][n - 1 - i] = temp;
            }
        }
    }


    /**
     * 用翻转代替旋转：先水平翻转，后沿对角线翻转
     *      matrix[row][col]  ---->水平翻转180 ---> matrix[n - 1 - row][col]
     *                         ------> 主对角线翻转 ----> matrix[col][n - 1 - row]
     * @param matrix
     */
    public static void rotate2(int[][] matrix) {
        int n = matrix.length;
        if (n == 0) return;
        for (int i = 0; i < n / 2; i++) {
            for (int j = 0; j < n; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[n - 1 -i][j];
                matrix[n - 1 -i][j] = temp;
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }
    }

    /**
     * 先沿主对角翻转，后竖直翻转
     *      matrix[row][col]  --------> 沿对角线 ----》 matrix[col][row]
     *                      -------->  竖直翻转  -------> matrix[col][n - 1 - row]
     * @param matrix
     */
    public static void rotate(int[][] matrix) {
        int n = matrix.length;
        if (n == 0) return;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < i; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n / 2; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[i][n - 1 -j];
                matrix[i][n - 1 - j] = temp;
            }
        }
    }

}







































