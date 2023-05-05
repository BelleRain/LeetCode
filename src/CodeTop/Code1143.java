package CodeTop;

/**
 * @author mxy
 * @create 2023-04-20 11:08
 */

/**
 * 1143. 最长公共子序列    链接：https://leetcode.cn/problems/longest-common-subsequence
 * 给定两个字符串 text1 和 text2，返回这两个字符串的最长 公共子序列 的长度。如果不存在 公共子序列 ，返回 0 。
 * 一个字符串的 子序列 是指这样一个新的字符串：
 * 它是由原字符串在不改变字符的相对顺序的情况下删除某些字符（也可以不删除任何字符）后组成的新字符串。
 *
 * 例如，"ace" 是 "abcde" 的子序列，但 "aec" 不是 "abcde" 的子序列。
 * 两个字符串的 公共子序列 是这两个字符串所共同拥有的子序列。
 *
 * 示例 1：
 * 输入：text1 = "abcde", text2 = "ace"
 * 输出：3
 * 解释：最长公共子序列是 "ace" ，它的长度为 3 。
 *
 * 示例 2：
 * 输入：text1 = "abc", text2 = "abc"
 * 输出：3
 * 解释：最长公共子序列是 "abc" ，它的长度为 3 。
 *
 * 示例 3：
 * 输入：text1 = "abc", text2 = "def"
 * 输出：0
 * 解释：两个字符串没有公共子序列，返回 0 。
 *
 * 提示：
 * 1 <= text1.length, text2.length <= 1000
 * text1 和 text2 仅由小写英文字符组成。
 *
 */
public class Code1143 {

    public static void main(String[] args) {
        System.out.println(longestCommonSubsequence("abcde", "aec"));
        System.out.println(longestCommonSubsequence("ezupkr", "ubmrapg"));
        System.out.println(longestCommonSubsequence("oxcpqrsvwf", "shmtulqrypy"));
    }

    /**
     * 题解链接：https://leetcode.cn/problems/longest-common-subsequence/solution/fu-xue-ming-zhu-er-wei-dong-tai-gui-hua-r5ez6/
     */

    /**
     * 首先，区分两个概念：子序列可以是不连续的；子数组（子字符串）需要是连续的；
     * 另外，动态规划也是有套路的：单个数组或者字符串要用动态规划时，
     * 可以把动态规划 dp[i] 定义为 nums[0:i] 中想要求的结果；
     * 当两个数组或者字符串要用动态规划时，可以把动态规划定义成两维的 dp[i][j] ，
     * 其含义是在 A[0:i] 与 B[0:j] 之间匹配得到的想要的结果。
     *
     * 二维动态规划：
     * 1、当 text1[i - 1] == text2[j - 1]时，说明两个子字符串的最后一位相等，则最长公共子序列加1，dp[i][j] = dp[i - 1][j - 1] + 1
     *    当 text1[i - 1] != text2[j - 1]时，说明两个子字符串的最后一位不相等，那么此时的状态，dp[i][j] = Math.max(dp[i - 1][j],dp[i][j - 1])
     *    举例：比如 对于 ace 和 bc 而言，其最长公共子序列为 ① ace 和 b 的 最长公共子序列 0 与
     *    ② ac 和 bc 的 最长公共子序列1 的最大值，即 1.
     * 2、状态转移方程：
     *      dp[i][j] = dp[i - 1][j - 1] + 1, 当 text1[i - 1] == text2[j - 1];
     *      dp[i][j] = max(dp[i - 1][j] , dp[i][j - 1]),当 text[i - 1] != text2[j - 1]
     * 3. 状态的初始化
     *      初始化就是要看当 i = 0 与 j = 0 时， dp[i][j] 应该取值为多少。
     *          当 i = 0 时，dp[0][j] 表示的是 text1text1 中取空字符串 跟 text2 的最长公共子序列，结果肯定为 0.
     *          当 j = 0 时，dp[i][0] 表示的是 text2text2 中取空字符串 跟 text1 的最长公共子序列，结果肯定为 0.
     *     综上，当 i = 0 或者 j = 0 时，dp[i][j] 初始化为 0.
     * 4、遍历方向与范围
     *     由于 dp[i][j] 依赖与 dp[i - 1][j - 1] , dp[i - 1][j], dp[i][j - 1]，
     *     所以 i 和 j 的遍历顺序肯定是 从小到大的。 另外，由于当 i 和 j 取值为 0 的时候，dp[i][j] = 0，而 dp 数组本身初始化就是为 0，
     *     所以，直接让 i 和 j 从 1 开始遍历。遍历的结束应该是字符串的长度为 len(text1) 和 len(text2)。
     * 5. 最终返回结果
     *    由于 dp[i][j] 的含义是 text1[0:i-1] 和 text2[0:j-1] 的最长公共子序列。
     *    我们最终希望求的是 text1 和 text2 的最长公共子序列。
     *    所以需要返回的结果是 i = len(text1) 并且 j = len(text2) 时的 dp[len(text1)][len(text2)]。
     *
     * @param text1
     * @param text2
     * @return
     */
    public static int longestCommonSubsequence(String text1, String text2) {
        int len1 = text1.length();
        int len2 = text2.length();
        int[][] dp = new int[len1 + 1][len2 + 1];
        for (int i = 1; i < len1 + 1; i++) {
            for (int j = 1; j < len2 + 1; j++) {
                if (text1.charAt(i - 1) == text2.charAt(j - 1)){
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                }else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[len1][len2];
    }

    /**
     * 优化后：
     */
    public int longestCommonSubsequence1(String text1, String text2) {
        char[] t = text2.toCharArray();
        int m = t.length;
        int[] f = new int[m + 1];
        for (char x : text1.toCharArray())
            for (int j = 0, pre = 0; j < m; ++j) {
                int tmp = f[j + 1];
                f[j + 1] = x == t[j] ? pre + 1 : Math.max(f[j + 1], f[j]);
                pre = tmp;
            }
        return f[m];
    }
}




























