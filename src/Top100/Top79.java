package Top100;

/**
 * @author mxy
 * @create 2022-11-23 15:22
 */

/**
 * 79. 单词搜索  (同剑指 Offer12 题)  链接：https://leetcode.cn/problems/word-search
 * 给定一个 m x n 二维字符网格 board 和一个字符串单词 word 。如果 word 存在于网格中，返回 true ；否则，返回 false 。
 * 单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。
 * 同一个单元格内的字母不允许被重复使用。
 *
 * 示例 1：
 * 输入：board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "ABCCED"
 * 输出：true
 *
 * 示例 2：
 * 输入：board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "SEE"
 * 输出：true
 *
 * 示例 3：
 * 输入：board = [["A","B","C","E"],["S","F","C","S"],["A","D","E","E"]], word = "ABCB"
 * 输出：false
 *  
 * 提示：
 * m == board.length
 * n = board[i].length
 * 1 <= m, n <= 6
 * 1 <= word.length <= 15
 * board 和 word 仅由大小写英文字母组成
 *
 * 进阶：你可以使用搜索剪枝的技术来优化解决方案，使其在 board 更大的情况下可以更快解决问题？
 */
public class Top79 {

    public static void main(String[] args) {
        Top79 top79 = new Top79();
        char[][] board = {
                {'A','B','C','E'},
                {'S','F','C','S'},
                {'A','D','E','E'}
        };
        System.out.println(top79.exist(board, "ABCCED"));
    }

    /**
     * 详细题解 ：原文链接：https://leetcode.cn/problems/ju-zhen-zhong-de-lu-jing-lcof/solution/mian-shi-ti-12-ju-zhen-zhong-de-lu-jing-shen-du-yo/
     * 同 剑指 Offer12
     * @param board
     * @param word
     * @return
     */
    public boolean exist(char[][] board, String word) {
        char[] words = word.toCharArray();
        //每一个元素都可能作为起点
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (dfs(board,words,i,j,0))
                    return true;
            }
        }
        return false;
    }

    private boolean dfs(char[][] board, char[] words, int i, int j, int k) {
        //返回 false ： (1) 行或列索引越界 或 (2) 当前矩阵元素与目标字符不同 或 (3) 当前矩阵元素已访问过 （ (3) 可合并至 (2) ）
        if (i >= board.length || i < 0 || j >= board[0].length || j < 0 || board[i][j] != words[k])
            return false;
        if (k == words.length - 1) return true;
        //代表此元素已访问过，防止之后搜索时重复访问
        board[i][j] = '\0';
        //遍历方向：下、左、上、右
        boolean res = dfs(board, words, i+1, j, k+1) || dfs(board, words, i, j-1, k+1) ||
                 dfs(board, words, i-1, j, k+1) || dfs(board, words, i, j+1, k+1);
        //回溯 : 还原当前矩阵元素： 将 board[i][j] 元素还原至初始值，即 word[k]
        board[i][j] = words[k];  //第 k+1 个元素 不影响 第 k 个元素
        return res;
    }

    /*
    使用偏移数组： 题解链接： https://leetcode.cn/problems/word-search/solution/zai-er-wei-ping-mian-shang-shi-yong-hui-su-fa-pyth/
    偏移数组：「力扣」第 130 题：被围绕的区域、「力扣」第 200 题：岛屿数量。
     */
}
