package Top100;

/**
 * @author mxy
 * @create 2022-12-12 15:14
 */

/**
 * 221. 最大正方形     链接：https://leetcode.cn/problems/maximal-square
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
 * 输入：matrix = [["0","1"],["1","0"]]
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
public class Top221 {

    public static void main(String[] args) {
        Top221 top221 = new Top221();
        char[][] matrix = {
                {'1','0','1','0','0'},
                {'1','0','1','1','1'},
                {'1','1','1','1','1'},
                {'1','0','0','1','0'}
        };
        System.out.println(top221.maximalSquare(matrix));
    }

    /**
     * 题解链接： https://leetcode.cn/problems/maximal-square/solution/li-jie-san-zhe-qu-zui-xiao-1-by-lzhlyle/
     * 详见原文图解
     *      dp(i, j) 是以 matrix(i - 1, j - 1) 为 右下角 的正方形的最大边长。
     *      dp(i + 1, j + 1) 是以 matrix(i, j) 为右下角的正方形的最大边长
     *  意义为：
     *      若某格子值为 1，则以此为右下角的正方形的、最大边长为：
     *           上面的正方形、左面的正方形或左上的正方形中，最小的那个，再加上此格。
     *  // 伪代码
     * if (grid[i - 1][j - 1] == '1') {
     *     dp[i][j] = min(dp[i - 1][j - 1], dp[i - 1][j], dp[i][j - 1]) + 1;
     * }
     *  dp 具体定义：dp[i + 1][j + 1] 表示 「以第 i 行、第 j 列为右下角的正方形的最大边长」
     *  为何不是 dp[i][j]?
     *  回到图解中，任何一个正方形，我们都「依赖」当前格 左、上、左上三个方格的情况 但第一行的上层已经没有格子，第一列左边已经没有格子，需要做特殊 if 判断来处理 为了代码简洁，我们 假设补充 了多一行全 '0'、多一列全 '0'
     *  题目要求面积。
     *  根据 「面积 = 边长 x 边长」可知，我们只需求出 最大边长 即可
     *  定义 maxSide 表示最长边长，每次得出一个 dp，就 maxSide = max(maxSide, dp);
     *  最终返回 return maxSide * maxSide;
     *  复杂度分析：
     *      时间复杂度： O(height * width)
     *      空间复杂度： O(height * width)
     * @param matrix
     * @return
     */
    public int maximalSquare(char[][] matrix) {
        if (matrix == null || matrix.length < 1 || matrix[0].length < 1) return 0;
        int height = matrix.length;
        int width = matrix[0].length;
        int maxSide = 0;
        //相当于已经预处理新增第一行、第一列均为0
        int[][] dp = new int[height + 1][width + 1];
        for (int row = 0; row < matrix.length; row++) {
            for (int col = 0; col < matrix[0].length; col++) {
                if (matrix[row][col] == '1'){
                    //左、上、左上角
                    dp[row + 1][col + 1] = Math.min(Math.min(dp[row + 1][col], dp[row][col + 1]), dp[row][col]) + 1;
                    maxSide = Math.max(maxSide, dp[row + 1][col + 1]);
                }
            }
        }
        return maxSide * maxSide;
    }

    /**
     * 空间优化：
     *      其实只需关注"当前格子的周边"，故可二维降一维优化
     *      增加 northwest 西北角解决"左上角"的问题
     * 时间复杂度 O(height * width)
     * 空间复杂度 O(width)
     * @param matrix
     * @return
     */
    public int maximalSquare1(char[][] matrix) {
        if (matrix == null || matrix.length < 1 || matrix[0].length < 1) return 0;
        int height = matrix.length, width = matrix[0].length;
        int maxSide = 0;
        //相当于已经预处理新增第一行、第一列均为0
        //int[][] dp = new int[height + 1][width + 1];
        int[] dp = new int[width + 1];
        int northwest = 0; //西北角、左上角
        //for (int row = 0; row < matrix.length; row++) {
        for (char[] chars : matrix) {
            northwest = 0; //遍历每行时，还原回辅助的原值0
            for (int col = 0; col < matrix[0].length; col++) {
                int nextNorthwest = dp[col + 1];
                if (chars[col] == '1'){
                    //dp[row + 1][col + 1] = Math.min(Math.min(dp[row + 1][col], dp[row][col + 1]), dp[row][col]) + 1;
                    dp[col + 1] = Math.min(Math.min(dp[col],dp[col + 1]), northwest) + 1;
                    //maxSide = Math.max(maxSide, dp[row + 1][col + 1]);
                    maxSide = Math.max(maxSide, dp[col + 1]);
                }else {
                    dp[col + 1] = 0;
                }
                //对于northwest而言，计算的时候，northwest的值是 上一次 dp[col + 1] 的值，即上一行 上一列的值
                northwest = nextNorthwest;
            }
        }
        return maxSide * maxSide;
    }
}
