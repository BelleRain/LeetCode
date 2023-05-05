package CodeTop;

/**
 * @author mxy
 * @create 2023-04-08 14:08
 */

import java.util.Arrays;

/**
 * 240. 搜索二维矩阵 II   链接：https://leetcode.cn/problems/search-a-2d-matrix-ii
 * 编写一个高效的算法来搜索 m x n 矩阵 matrix 中的一个目标值 target 。该矩阵具有以下特性：
 * 每行的元素从左到右升序排列。
 * 每列的元素从上到下升序排列。
 *  
 *
 * 示例 1：
 * 输入：matrix = [[1,4,7,11,15],
 *                [2,5,8,12,19],
 *                [3,6,9,16,22],
 *                [10,13,14,17,24],
 *                [18,21,23,26,30]],
 *      target = 5
 * 输出：true
 *
 * 示例 2：
 * 输入：matrix = [[1,4,7,11,15],
 *                [2,5,8,12,19],
 *                [3,6,9,16,22],
 *                [10,13,14,17,24],
 *                [18,21,23,26,30]],
 *     target = 20
 * 输出：false
 *  
 *
 * 提示：
 * m == matrix.length
 * n == matrix[i].length
 * 1 <= n, m <= 300
 * -10^9 <= matrix[i][j] <= 10^9
 * 每行的所有元素从左到右升序排列
 * 每列的所有元素从上到下升序排列
 * -10^9 <= target <= 10^9
 *
 */
public class Code240 {
    public static void main(String[] args) {
        int[][] matrix = {
                {1,4,7,11,15},
                {2,5,8,12,19},
                {3,6,9,16,22},
                {10,13,14,17,24},
                {18,21,23,26,30}
        };
        System.out.println(searchMatrix1(matrix, 5));
        System.out.println(searchMatrix1(matrix, 20));
    }

    /**
     * 解法一：从右上角开始查找
     * @param matrix
     * @param target
     * @return
     */
    public static boolean searchMatrix(int[][] matrix, int target) {
        if (matrix.length == 0) return false;
        int m = matrix.length, n = matrix[0].length;
        int i = 0, j = n - 1; //从右上角开始查找
        while (i >= 0 && i < m && j >= 0 && j < n){
            if (target == matrix[i][j]) return true;
            else if (target > matrix[i][j]) i++;
            else if (target < matrix[i][j]) j--;
        }
        return false;
    }


    /**
     * 二分查找
     * @param matrix
     * @param target
     * @return
     */
    public static boolean searchMatrix1(int[][] matrix, int target){
        if (matrix.length == 0) return false;
        int m = matrix.length, n = matrix[0].length;
        boolean res = false;
        for (int[] ints : matrix) {
            //int res = Arrays.binarySearch(matrix[i], target); //利用库函数
            res = search(0,ints.length - 1,ints,target);
            if (res) break;
        }
        return res;
    }

    private static boolean search(int left, int right, int[] ints, int target) {
        while (left <= right){
            int mid = (left + right) / 2;
            if (target == ints[mid]) return true;
            else if (target < ints[mid]) right = mid - 1;
            else left = mid + 1;
        }
        return false;
    }
}








































