package CodeTop;

/**
 * @author mxy
 * @create 2023-06-14 10:25
 */

/**
 * 79. 单词搜索     链接：https://leetcode.cn/problems/word-search
 * 给定一个 m x n 二维字符网格 board 和一个字符串单词 word。如果 word 存在于网格中，返回 true ；否则，返回 false。
 * 单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。
 * 同一个单元格内的字母不允许被重复使用。
 *
 * 示例 1：
 * 输入：board = [["A","B","C","E"],
 *               ["S","F","C","S"],
 *               ["A","D","E","E"]], word = "ABCCED"
 * 输出：true
 *
 * 示例 2：
 * 输入：board = [["A","B","C","E"],
 *               ["S","F","C","S"],
 *               ["A","D","E","E"]], word = "SEE"
 * 输出：true
 *
 * 示例 3：
 * 输入：board = [["A","B","C","E"],
 *               ["S","F","C","S"],
 *               ["A","D","E","E"]], word = "ABCB"
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
 *
 */
public class Code79 {

    public static void main(String[] args) {
        char[][] board = {
                {'A','B','C','E'},
                {'S','F','C','S'},
                {'A','D','E','E'}
        };
        System.out.println(exist(board, "ABCCED"));
    }

    /**
     * 同 剑指 Offer12
     */

    /**
     * @param board
     * @param word
     * @return
     */
    public static boolean exist(char[][] board, String word) {
        if (board.length == 0) return false;
        char[] words = word.toCharArray();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (dfs(board,i,j,words,0)){
                   return true;
                }
            }
        }
        return false;
    }

    private static boolean dfs(char[][] board, int i, int j, char[] words, int k) {
        //返回 false ： (1) 行或列索引越界 或 (2) 当前矩阵元素与目标字符不同 或 (3) 当前矩阵元素已访问过 （ (3) 可合并至 (2) ）
        if (i < 0 || i >= board.length || j < 0 || j >= board[0].length || board[i][j] != words[k]){
            return false;
        }
        if (k == words.length - 1) return true;
        //访问过的位置用 '\0' 填充
        board[i][j] = '\0';
        //从当前位置 下、左、上、右遍历
        boolean res = dfs(board, i + 1, j, words, k + 1) || dfs(board, i, j - 1, words, k + 1) ||
                        dfs(board, i - 1, j, words, k + 1) || dfs(board, i,  j + 1, words, k + 1);
        //将该位置还原
        board[i][j] = words[k];
        return res;
    }


}







































