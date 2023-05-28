package CodeTop;

/**
 * @author mxy
 * @create 2023-05-27 16:14
 */

import java.util.Arrays;

/**
 * 498. 对角线遍历   链接：https://leetcode.cn/problems/diagonal-traverse
 * 给你一个大小为 m x n 的矩阵 mat ，请以对角线遍历的顺序，用一个数组返回这个矩阵中的所有元素。
 *
 * 示例 1：
 * 输入：mat = [[1,2,3],[4,5,6],[7,8,9]]
 * 输出：[1,2,4,7,5,3,6,8,9]
 *
 * 示例 2：
 * 输入：mat = [[1,2],[3,4]]
 * 输出：[1,2,3,4]
 *  
 * 提示：
 * m == mat.length
 * n == mat[i].length
 * 1 <= m, n <= 10^4
 * 1 <= m * n <= 10^4
 * -10^5 <= mat[i][j] <= 10^5
 *
 */
public class Code498 {

    public static void main(String[] args) {
        int[][] mat = {
                {1,2,3},
                {4,5,6},
                {7,8,9}
        };
        System.out.println(Arrays.toString(findDiagonalOrder(mat)));
    }

    /**
     * 题解链接：https://leetcode.cn/problems/diagonal-traverse/solution/dui-jiao-xian-bian-li-fen-xi-ti-mu-zhao-zhun-gui-l/
     */

    /**
     *
     * @param mat
     * @return
     */
    public static int[] findDiagonalOrder(int[][] mat) {
        if (mat == null || mat.length == 0) return new int[0];
        int m = mat.length;
        int n = mat[0].length;
        int[] nums = new int[m * n];

        int k = 0;
        boolean bXFlag = true;
        for (int i = 0; i < m + n; i++) {
            int pm = bXFlag ? m : n;
            int pn = bXFlag ? n : m;
            int x = (i < pm) ? i : pm - 1;
            int y = i - x;
            while (x >= 0 && y < pn){
                nums[k++] = bXFlag ? mat[x][y] : mat[y][x];
                x--;
                y++;
            }
            bXFlag = !bXFlag;
        }
        return nums;
    }
}


































