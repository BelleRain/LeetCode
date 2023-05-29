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


    public static int[] findDiagonalOrder1(int[][] mat) {
        int m = mat.length;
        int n = mat[0].length;
        int[] arr = new int[m * n];
        int k = 0;

        /**
         *   1  2  3
         *   4  5  6
         *   7  8  9
         *         path               sum
         *   （0,0）                   0
         *   （0,1）（1,0）            1
         *   （2,0）（1,1）（0,2）      2
         *   （1,2）（2,1）             3
         *   （2,2）                   4
         *
         *   奇数趟： 只需要观察每一趟即可，在每一趟中：行--，列++
         *   第 1 趟时： （0,0）                x+y = 0
         *   第 3 趟时： （2,0）（1,1）（0,2）   x + y = 2
         *   第 5 趟时： （2,2）                x + y = 4
         *
         *   偶数趟： 只需要观察每一趟即可，在每一趟中：行++，列--
         *   第 2 趟时： （0,1）（1,0）
         *   第 4 趟时： （1,2）（2,1）
         */

        //第一趟时，x = 0，y = 0，i = x + y = 0
        int i = 0;
        //i 代表 x + y，即要走的趟数，总共要走 m + n - 1 = 5 趟，i 最大值为 4
        //对于
        while (i < m + n - 1){
            //x1的初始值
            //如果 行数未达到最大值 m--
            int x1 = i < m ? i : m - 1;
            int y1 = i - x1;
            while (x1 >= 0 && y1 <= n - 1){
                arr[k++] = mat[x1][y1];
                x1--;
                y1++;
            }
            i++;

            //
            int y2 = i < n ? i : n - 1;
            int x2 = i - y2;
            while (x2 <= m - 1 && y2 >= 0){
                arr[k++] = mat[x2][y2];
                x2++;
                y2--;
            }
            i++;
        }
        return arr;
    }


    public int[] findDiagonalOrder2(int[][] mat) {
        int m = mat.length;
        int n = mat[0].length;
        int[] res = new int[m * n];
        int pos = 0;
        for (int i = 0; i < m + n - 1; i++) {
            if (i % 2 == 1) {
                int x = i < n ? 0 : i - n + 1;
                int y = i < n ? i : n - 1;
                while (x < m && y >= 0) {
                    res[pos] = mat[x][y];
                    pos++;
                    x++;
                    y--;
                }
            } else {
                int x = i < m ? i : m - 1;
                int y = i < m ? 0 : i - m + 1;
                while (x >= 0 && y < n) {
                    res[pos] = mat[x][y];
                    pos++;
                    x--;
                    y++;
                }
            }
        }
        return res;
    }



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


































