package CodeTop;

/**
 * @author mxy
 * @create 2023-06-23 20:03
 */

/**
 * 剑指 Offer 04. 二维数组中的查找    链接：https://leetcode.cn/problems/er-wei-shu-zu-zhong-de-cha-zhao-lcof
 * 在一个 n * m 的二维数组中，每一行都按照从左到右 非递减 的顺序排序，每一列都按照从上到下 非递减 的顺序排序。
 * 请完成一个高效的函数，输入这样的一个二维数组和一个整数，判断数组中是否含有该整数。
 *
 *
 * 示例:
 * 现有矩阵 matrix 如下：
 *
 * [
 *   [1,   4,  7, 11, 15],
 *   [2,   5,  8, 12, 19],
 *   [3,   6,  9, 16, 22],
 *   [10, 13, 14, 17, 24],
 *   [18, 21, 23, 26, 30]
 * ]
 * 给定 target = 5，返回 true。
 * 给定 target = 20，返回 false。
 *
 *
 * 限制：
 * 0 <= n <= 1000
 * 0 <= m <= 1000
 *
 */
public class Offer04 {

    public static void main(String[] args) {
        int[][] matrix = {
                {1,4,7,11,15},
                {2,5,8,12,19},
                {3,6,9,16,22},
                {10,13,14,17,24},
                {18,21,23,26,30}
        };
        System.out.println(findNumberIn2DArray(matrix, 5));
        System.out.println(findNumberIn2DArray(matrix, 20));
    }


    /**
     * 题解一： 在右上角开始查找
     * @param matrix
     * @param target
     * @return
     */
    public static boolean findNumberIn2DArray1(int[][] matrix, int target) {
        if (matrix.length == 0) return false;
        int m = matrix.length, n = matrix[0].length;
        int i = 0, j = n - 1;
        while (i < m && j >= 0) {
            if (target == matrix[i][j]) {
                return true;
            } else if (target > matrix[i][j]) {
                i++;
            } else {
                j--;
            }
        }
        return false;
    }


    /**
     * 题解二：递归二分查找
     * @param matrix
     * @param target
     * @return
     */
    public static boolean findNumberIn2DArray2(int[][] matrix, int target) {
        boolean res = false;
        for (int[] ints : matrix) {
            res = searchRecur(0, matrix[0].length - 1, ints, target);
            if (res) break;
        }
        return res;
    }

    private static boolean searchRecur(int left, int right, int[] nums, int target) {
        if (left > right || nums.length == 0) {
            return false;
        }
        int mid = (left + right) / 2;
        int midVal = nums[mid];
        if (target < midVal) { //向左递归
            return searchRecur(left, mid - 1, nums, target);
        }else if (target > midVal) { //向右递归
            return searchRecur(mid + 1, right, nums, target);
        }else {
            return true;
        }
    }


    /**
     * 迭代二分查找
     * @param matrix
     * @param target
     * @return
     */
    public static boolean findNumberIn2DArray(int[][] matrix, int target) {
        boolean res = false;
        for (int[] ints : matrix) {
            res = searchIter(0, matrix[0].length - 1, ints, target);
            if (res) break;
        }
        return res;
    }

    private static boolean searchIter(int left, int right, int[] nums, int target) {
        int l = left, r = right;
        while (l <= r) {
            int mid = (l + r) / 2;
            if (target == nums[mid]) {
                return true;
            } else if (target > nums[mid]) {
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
        return false;
    }


}








































