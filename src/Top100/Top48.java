package Top100;

/**
 * @author mxy
 * @create 2022-11-21 9:35
 */

import java.util.Arrays;

/**
 * 48. 旋转图像  链接：https://leetcode.cn/problems/rotate-image
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
public class Top48 {

    public static void main(String[] args) {
        Top48 top48 = new Top48();
        int[][] nums = {
                {1,2,3},
                {4,5,6},
                {7,8,9}
        };
        top48.rotate(nums);
        for (int[] num : nums) {
            System.out.println(Arrays.toString(num));
        }
    }

    /**
     * 题解链接：https://leetcode.cn/problems/rotate-image/solution/xuan-zhuan-tu-xiang-by-leetcode-solution-vu3m/
     */

    /**
     * 方法一：原地旋转 （注意：1、数学规律 2、边界条件）
     * 1、原矩阵 ---》 新矩阵:
     *      matrix[row][col] = matrix[col][n-1-row]
     *     1、原矩阵 列 = 新矩阵 行
     *     2、原矩阵 行 + 新矩阵 列 = n - 1
     * 2、每次旋转 4 个位置
     * 3、当 n 为偶数时，我们需要枚举 n^2 / 4 = (n/2)×(n/2) 个位置，可以将该图形分为四块，以 4×4 的矩阵为例；保证了不重复、不遗漏；
     *    当 n 为奇数时，由于中心的位置经过旋转后位置不变，我们需要枚举 (n^2-1) / 4 = ((n−1)/2)×((n+1)/2) 个位置，需要换一种划分的方式，以 5×5 的矩阵为例：同样保证了不重复、不遗漏，矩阵正中央的点无需旋转。
     * 复杂度分析：
     *      时间复杂度：O(N^2)，其中 N 是 matrix 的边长。我们需要枚举的子矩阵大小为 O(⌊n/2⌋×⌊(n+1)/2⌋)=O(N^2)
     *      空间复杂度：O(1)，为原地旋转。
     * @param matrix
     */
    public void rotate(int[][] matrix) {
        int n = matrix.length;
        if (n == 0) return;
        for (int i = 0; i < n / 2; i++) {
            for (int j = 0; j < (n + 1) / 2; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[n - 1 - j][i];  // = 左边 新位置 ；= 右边 原位置
                matrix[n - 1 - j][i] = matrix[n - 1 - i][n - 1 - j];
                matrix[n - 1 - i][n - 1 - j] = matrix[j][n - 1 - i];
                matrix[j][n - 1 - i] = temp;
            }
        }
    }

    /**
     * 方法二：用翻转代替旋转
     * 1、原矩阵 ---》 新矩阵:
     *      matrix[row][col] = matrix[col][n-1-row]
     * 2、matrix[row][col] 水平轴翻转  matrix[n−row−1][col]
     * ​  matrix[row][col]  主对角线翻转  matrix[col][row]
     * 3、matrix[row][col] 水平轴翻转  matrix[n−row−1][col]
     *                     主对角线翻转 matrix[col][n-row-1]
     * 复杂度分析：
     *    时间复杂度：O(N^2)，其中 N 是 matrix 的边长。对于每一次翻转操作，我们都需要枚举矩阵中一半的元素。
     *    空间复杂度：O(1)。为原地翻转得到的原地旋转。
     * @param matrix
     */
    public void rotate2(int[][] matrix) {
        int n = matrix.length;
        if (n == 0) return;
        for (int i = 0; i < n/2; i++) {
            for (int j = 0; j < n; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[n - 1 - i][j];
                matrix[n - 1 - i][j] = temp;
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
     * 方法三：先沿主对角线翻转 再 竖直翻转
     * 1、原矩阵 ---》 新矩阵:
     *      matrix[row][col] = matrix[col][n-1-row]
     * 2、matrix[row][col] 主对角线翻转  matrix[col][row]
     *                     竖直翻转     matrix[col][n-1-row]
     * @param matrix
     */
    public void rotate3(int[][] matrix) {
        int n = matrix.length;
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }
        for (int[] row : matrix) {
            reverse(row);
        }
    }

    void reverse(int[] arr) {
        int i = 0, j = arr.length - 1;
        while (j > i) {
            // swap(arr[i], arr[j]);
            int temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
            i++;
            j--;
        }
    }
}
