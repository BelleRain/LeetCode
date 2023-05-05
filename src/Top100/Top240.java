package Top100;

/**
 * @author mxy
 * @create 2022-12-19 10:55
 */

/**
 * 240. 搜索二维矩阵 II   (同剑指 Offer04)  链接：https://leetcode.cn/problems/search-a-2d-matrix-ii
 * 编写一个高效的算法来搜索 m x n 矩阵 matrix 中的一个目标值 target 。该矩阵具有以下特性：
 *
 * 每行的元素从左到右升序排列。
 * 每列的元素从上到下升序排列。
 *
 * 示例 1：
 * 输入：matrix = [[1,4,7,11,15],
 *                [2,5,8,12,19],
 *                [3,6,9,16,22],
 *                [10,13,14,17,24],
 *                [18,21,23,26,30]], target = 5
 * 输出：true
 *
 * 示例 2：
 * 输入：matrix = [[1,4,7,11,15],
 *                [2,5,8,12,19],
 *                [3,6,9,16,22],
 *                [10,13,14,17,24],
 *                [18,21,23,26,30]], target = 20
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
public class Top240 {

    public static void main(String[] args) {
        Top240 top240 = new Top240();
        int[][] matrix = {
                {1, 4, 7, 11,15},
                {2, 5, 8, 12,19},
                {3, 6, 9, 16,22},
                {10,13,14,17,24},
                {18,21,23,26,30}
        };
        System.out.println(top240.searchMatrix(matrix, 5));
    }

    /**
     * 详见剑指 Offer04
     * @param matrix
     * @param target
     * @return
     */
    /*public boolean searchMatrix(int[][] matrix, int target) {
        //从左上角开始搜索
        int m = matrix.length, n = matrix[0].length;
        if (m == 0) return false;
        int i = 0, j = n - 1;
        while (i < m && j >= 0){
            if (matrix[i][j] == target) return true;
            if (matrix[i][j] < target) i++;
            else j--;
        }
        return false;
    }*/


    /**
     * 二分查找
     * @param matrix
     * @param target
     * @return
     */
    public boolean searchMatrix(int[][] matrix, int target) {
         boolean res = false;
        for (int[] ints : matrix) {
            res = search(0,ints.length - 1,ints,target);
            if (res) break;
        }
        return res;
    }

    //private boolean search(int left, int right, int[] nums, int target) {
    //    boolean res = false;
    //    if (left > right || nums.length == 0) return false;
    //    int mid = (left + right) / 2;
    //    if (target < nums[mid]) res = search(left, mid - 1, nums, target);
    //    else if (target > nums[mid]) res = search(mid + 1, right, nums, target);
    //    else res = true;
    //    return res;
    //}

    private boolean search(int left, int right, int[] nums, int target) {
        while (left <= right){
            int mid  = (left + right) / 2;
            if (target < nums[mid]) right = mid - 1;
            else if (target > nums[mid]) left = mid + 1;
            else return true;
        }
        return false;
    }
}
