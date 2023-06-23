package Swordoffer.SearchAlgorithms;

/**
 * @author mxy
 * @create 2022-09-19 9:04
 */

/**
 * 二维数组中的查找
 * 在一个 n * m 的二维数组中，每一行都按照从左到右递增的顺序排序，每一列都按照从上到下递增的顺序排序。
 * 请完成一个高效的函数，输入这样的一个二维数组和一个整数，判断数组中是否含有该整数。
 *
 * 现有矩阵 matrix 如下：
 *   [  [1,   4,  7, 11, 15],
 *      [2,   5,  8, 12, 19],
 *      [3,   6,  9, 16, 22],
 *      [10, 13, 14, 17, 24],
 *      [18, 21, 23, 26, 30] ]
 *  给定 target = 5，返回 true。
 *  给定 target = 20，返回 false。
 */
public class Offer04 {

    public static void main(String[] args) {
        Offer04 offer04 = new Offer04();
        int[][] matrix = {
                {1,   4,  7, 11, 15},   //在行的角度，最后一个值为 最大值
                {2,   5,  8, 12, 19},   //在列的角度，第一个值为 最小值
                {3,   6,  9, 16, 22},
                {10, 13, 14, 17, 24},
                {18, 21, 23, 26, 30}
        };

        ////当数组为空
        //int[][] matrix = {};
        //int m = matrix.length;
        //System.out.println("m= " + m);
        ////matrix[0].length,在数组为空时，会报异常 java.lang.ArrayIndexOutOfBoundsException: 0
        //int n = matrix[0].length;
        //System.out.println("n= " + n);
        System.out.println(offer04.findNumberIn2DArray(matrix, 20));

    }

    /**
     * 解法一：递归二分查找
     * 执行用时： 0 ms , 在所有 Java 提交中击败了 100.00% 的用户
     * 内存消耗： 47 MB , 在所有 Java 提交中击败了 87.95% 的用户
     * @param matrix
     * @param target
     * @return
     */
    ////对每行使用二分查找
    //public boolean findNumberIn2DArray(int[][] matrix,int target){
    //    boolean res = false;
    //    for (int[] ints : matrix) {
    //        //System.out.println(ints.getClass().getTypeName());
    //        res = search(0,ints.length-1,ints,target);
    //        if (res) break;
    //    }
    //    return res;
    //}
    //
    ////递归 二分查找
    //public boolean search(int left,int right,int nums[],int target){
    //    if (left>right || nums.length == 0){
    //        return false;
    //    }
    //    int mid = (left + right)/2;
    //    int midVal = nums[mid];
    //    if (target < midVal){  //向左递归
    //        return search(left,mid-1,nums,target);
    //    }else if (target > midVal){ //向右递归
    //        return search(mid+1,right,nums,target);
    //    }else {
    //        return true;
    //    }
    //}

    /**
     * 迭代二分查找
     * @param matrix
     * @param target
     * @return
     */
    public static boolean findNumberIn2DArray2(int[][] matrix, int target) {
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

    /**
     * 该题注意：从左上角查找需要注意 数组为空时，matrix[0].length的数组越界问题。
     */

    /**
     * 解法二: Z字形 查找  || 旋转矩阵，看作 二叉搜素树  与 解法三一样，只是解释不同
     * 解题思路：
     *  可以从矩阵matrix的右上角（0，n-1）进行搜索，在每一步搜索过程中，如果位于位置（x,y）,
     *  则我们希望在 以matrix的左下角为左下角、以(x,y)为右上角 的矩阵中进行搜索，即行的
     *  范围为[x,m-1],列的范围为[0,y]:
     *      1、如果matrix[x,y]=target,说明搜索完成；
     *      2、如果matrix[x,y]>target,由于每一列是升序排列，那么在当前搜索矩阵中，所有位于
     *      第y列的元素都是严格大于target的，因此我们可以将它们全部忽略，即将y减少1；
     *      3、如果matrix[x,y]<target,由于每一行的元素是升序排列，那么在当前的搜索矩阵中，所有位于
     *      第x行的元素都是严格小于target的，因此我们可以将它们全部忽略，即将x增加1.
     *      4、在搜索过程中，如果超出了矩阵的边界，那么说明矩阵中不存在target
     *
     * 时间复杂度：O(n + m)。在搜索的过程中，如果我们没有找到target，那么我们要么将 y 减少 1，要么将 x 增加 1。
     * 由于 (x, y)的初始值分别为(0,m−1)，因此 y 最多能被减少 m 次，x 最多能被增加 n 次，总搜索次数为 n + m。
     * 在这之后，x 和 y 就会超出矩阵的边界。
     * 空间复杂度：O(1)。
     * @param matrix
     * @param target
     * @return
     */
    //public boolean findNumberIn2DArray(int[][] matrix,int target){
    //    int m = matrix.length;
    //    if (m == 0){
    //        return false;
    //    }
    //    int n = matrix[0].length;
    //    int x = 0, y = n-1; //右上角开始,x递增，y递减
    //    while (x < m && y >= 0){
    //        if (matrix[x][y] == target){
    //            return true;
    //        }
    //        if (matrix[x][y] > target){
    //            y--;
    //        }
    //        if (matrix[x][y] < target){
    //            x++;
    //        }
    //    }
    //    return false;
    //}

    /**
     * 解法三：旋转矩阵 与 解法2的起点不同
     * 解题思路： 具体图解见 https://leetcode.cn/problems/er-wei-shu-zu-zhong-de-cha-zhao-lcof/solution/mian-shi-ti-04-er-wei-shu-zu-zhong-de-cha-zhao-zuo/
     *   将矩阵逆时针旋转 45度，并将其转化为图形式，则其类似于 二叉搜索树，即对于每个
     * 元素，其左分支元素更小、右分支元素更大。因此，通过从 “根结点”开始搜索，遇到比 target 大的元素
     * 就向左，反之向右，即可找到目标值 target。
     *   “根节点”对应的是矩阵的“左下角”和“右下角”元素，本文称之为 标志数，以matrix中的左下角元素为标志数 flag，则有：
     *   1、（flag为所在行的最小值）若flag > target,则target一定在flag所在 行的上方，即flag所在行可被消去。
     *   2、（flag为所在列的最大值）若flag < target，则target一定在flag所在 列的右方，即flag所在列可消去。
     * 算法流程：
     * 1、从矩阵matrix左下角元素（索引为(i,j)）开始遍历，并且与目标值对比
     *     当matrix[x][y] > target ,执行 i--，即消去第 i 行元素
     *     当matrix[x][y] < target ,执行 j++, 即消去第 j 列元素
     *     当matrix[x][y] = target, 返回true，代表找到目标值
     * 2、若行的索引或列索引越界，则代表矩阵中无目标值，返回false。
     */
    public boolean findNumberIn2DArray(int[][] matrix,int target){
        //从左下角开始，避免了数组为空时的 索引越界的 异常
        int i = matrix.length-1,j = 0;
        while (i > 0 && j < matrix[0].length){
            //if (matrix[i][j] == target) return true;
            //if (matrix[i][j] > target) i--;
            //if (matrix[i][j] < target) j++;

            //if(matrix[i][j] > target) i--;
            //else if(matrix[i][j] < target) j++;
            //else return true;

            if (matrix[i][j] == target) return true;
            /**
             * //此处的两个if语句，当其中一个数组越界（i<0,或j>5）时，由于程序会继续执行判断，
             * //所以当j>5,未return时，向下会报 ArrayIndexOutOfBoundsException: 5 的异常
             * //故换为 if ... else if ......
             *
             * if...if.... 与 if...else if....的区别：
             *    if 语句至多有 1 个 else 语句，else 语句在所有的 else if 语句之后。
             *    if 语句可以有若干个 else if 语句，它们必须在 else 语句之前。
             *    一旦其中一个 else if 语句检测为 true，其他的 else if 以及 else 语句都将跳过执行。
             */

            if (matrix[i][j] < target) {
                j++;
                //return false;
            }
            else if (matrix[i][j] > target) {
                i--;
                //return false;
            }
        }
        return false;
    }
}





























