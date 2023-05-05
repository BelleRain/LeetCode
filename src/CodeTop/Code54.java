package CodeTop;

/**
 * @author mxy
 * @create 2023-04-15 10:15
 */

import java.util.ArrayList;
import java.util.List;

/**
 * 54. 螺旋矩阵     链接：https://leetcode.cn/problems/spiral-matrix
 * 给你一个 m 行 n 列的矩阵 matrix ，请按照 顺时针螺旋顺序 ，返回矩阵中的所有元素。
 *
 * 示例 1：
 * 输入：matrix = [[1,2,3],
 *                [4,5,6],
 *                [7,8,9]]
 * 输出：[1,2,3,6,9,8,7,4,5]
 *
 * 示例 2：
 * 输入：matrix = [[1,2,3,4],[5,6,7,8],[9,10,11,12]]
 * 输出：[1,2,3,4,8,12,11,10,9,5,6,7]
 *  
 *
 * 提示：
 * m == matrix.length
 * n == matrix[i].length
 * 1 <= m, n <= 10
 * -100 <= matrix[i][j] <= 100
 *
 */
public class Code54 {

    public static void main(String[] args) {
        int[][] matrix = {
                {1,2,3},
                {4,5,6},
                {7,8,9}
        };
        System.out.println(spiralOrder(matrix));
    }

    /**
     * 题解链接： https://leetcode.cn/problems/spiral-matrix/solution/cxiang-xi-ti-jie-by-youlookdeliciousc-3/
     * 解题思路：
     *      1、首先设定上下左右边界
     *      2、其次向右移动到最右，此时第一行因为已经使用过了，可以将其从图中删去，体现在代码中就是重新定义上边界
     *      3、判断若重新定义后，上下边界交错，表明螺旋矩阵遍历结束，跳出循环，返回答案
     *      4、若上下边界不交错，则遍历还未结束，接着向下向左向上移动，操作过程与第一，二步同理
     *      5、不断循环以上步骤，直到某两条边界交错，跳出循环，返回答案
     * @param matrix
     * @return
     */
    public static List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> res = new ArrayList<>();
        if (matrix.length == 0) return res;
        int up = 0;     //上边界
        int down = matrix.length - 1;   //下边界
        int left = 0;      //左边界
        int right = matrix[0].length - 1;   //右边界
        while (true){
            //向右移动
            for (int i = left; i <= right; i++) {
                res.add(matrix[up][i]);
            }
            //向下移动
            if (++up > down) break;
            for (int i = up; i <= down; i++) {
                res.add(matrix[i][right]);
            }
            //向左移动
            if (left > --right) break;
            for (int i = right; i >= left; i--) {
                res.add(matrix[down][i]);
            }
            //向上移动
            if (up > --down) break;
            for (int i = down; i >= up; i--) {
                res.add(matrix[i][left]);
            }
            if (++left > right) break;
        }
        return res;
    }
}

























