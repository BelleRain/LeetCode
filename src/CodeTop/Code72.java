package CodeTop;

/**
 * @author mxy
 * @create 2023-04-19 11:29
 */

/**
 * 72. 编辑距离    链接：https://leetcode.cn/problems/edit-distance
 * 给你两个单词 word1 和 word2， 请返回将 word1 转换成 word2 所使用的最少操作数。
 * 你可以对一个单词进行如下三种操作：
 *
 * 插入一个字符
 * 删除一个字符
 * 替换一个字符
 *
 * 示例 1：
 * 输入：word1 = "horse", word2 = "ros"
 * 输出：3
 * 解释：
 * horse -> rorse (将 'h' 替换为 'r')
 * rorse -> rose (删除 'r')
 * rose -> ros (删除 'e')
 *
 * 示例 2：
 * 输入：word1 = "intention", word2 = "execution"
 * 输出：5
 * 解释：
 * intention -> inention (删除 't')
 * inention -> enention (将 'i' 替换为 'e')
 * enention -> exention (将 'n' 替换为 'x')
 * exention -> exection (将 'n' 替换为 'c')
 * exection -> execution (插入 'u')
 *  
 * 提示：
 * 0 <= word1.length, word2.length <= 500
 * word1 和 word2 由小写英文字母组成
 *
 */
public class Code72 {

    public static void main(String[] args) {
        System.out.println(minDistance("horse", "ros"));
    }

    /**
     * 题解链接：https://leetcode.cn/problems/edit-distance/solution/zi-di-xiang-shang-he-zi-ding-xiang-xia-by-powcai-3/
     */

    /**
     * 动态规划：
     *   dp[i][j] 代表 word1 到 i 位置转换成 word2 到 j 位置需要最少步数
     * 所以，
     *      当 word1[i] == word2[j]，dp[i][j] = dp[i-1][j-1]；
     *      当 word1[i] != word2[j]，dp[i][j] = min(dp[i-1][j-1], dp[i-1][j], dp[i][j-1]) + 1
     * 其中，dp[i-1][j-1] 表示替换操作，
     *      dp[i-1][j] 表示删除操作，
     *      dp[i][j-1] 表示插入操作。
     *
     * 注意，针对第一行，第一列要单独考虑，我们引入 '' 下图所示：
     * 第一行，是 word1 为空 变成 word2 最少步数，就是插入操作
     * 第一列，是 word2 为空，需要的最少步数，就是删除操作
     *
     * 评论区解释：
     * 讲一下我自己对状态转移方程的理解,麻烦大家看看我说得对不对有没有道理:
     * (一)、当word1[i]==word2[j]时,由于遍历到了i和j,说明word1的 0~i-1 和word2的 0~j-1 的匹配结果已经生成,
     * 由于当前两个字符相同,因此无需做任何操作,dp[i][j] = dp[i-1][j-1]
     * (二)、当 word1[i] != word2[j]时,可以进行的操作有3个:
     *       ① 替换操作:可能 word1 的 0~i-1 位置与 word2 的 0~j-1 位置的字符都相同,
     *            只是当前位置的字符不匹配,进行替换操作后两者变得相同,
     *            所以此时 dp[i][j] = dp[i-1][j-1]+1 (这个加1代表执行替换操作)
     *       ② 删除操作:若 此时 word1 的 0~i-1 位置与 word2 的 0~j 位置已经匹配了,
     *          此时多出了 word1 的 i 位置字符,应把它删除掉, 才能使此时 word1 的 0~i (这个i是执行了删除操作后新的i)
     *          和 word2 的 0~j 位置匹配,因此此时 dp[i][j] = dp[i-1][j] + 1 (这个加1代表执行删除操作)
     *       ③ 插入操作: 若此时 word1 的 0~i 位置只是和 word2 的 0~j-1 位置匹配,
     *           此时只需要在原来的 i 位置后面插入一个和 word2 的 j 位置相同的字符使得
     *           此时的 word1 的 0~i (这个i是执行了插入操作后新的i) 和 word2 的 0~j 匹配得上,
     *           所以此时 dp[i][j] = dp[i][j-1] + 1 (这个加1代表执行插入操作)
     *       ④由于题目所要求的是要最少的操作数:所以当 word1[i] != word2[j] 时,
     *           需要在这三个操作中选取一个最小的值赋格当前的 dp[i][j]
     * (三)总结:状态方程为:
     * if(word1[i] == word2[j]):
     *       dp[i][j] = dp[i-1][j-1]
     * else:
     *        min(dp[i-1][j-1],dp[i-1][j],dp[i][j-1])+1
     *
     * PS:大佬的代码中word1.charAt(i-1)==word2.charAt(j-1)的原因是:
     *      初始化DP Table时dp[i][0]和dp[0][j]已经填写完成,所以接下来填表需要从1开始,
     *      但是字符的比较需要从0开始,因此才这样子写
     * @param word1
     * @param word2
     * @return
     */
    public static int minDistance(String word1, String word2) {
        int len1 = word1.length();
        int len2 = word2.length();
        // dp[i][j] 代表 word1 到 i 位置转换成 word2 到 j 位置需要最少步数
        int[][] dp = new int[len1 + 1][len2 + 1];
        dp[0][0] = 0; //word1 = '',word2 = ''
        //填充第一列，word2 = ''
        for (int i = 1; i <= len1; i++) {
            dp[i][0] = dp[i - 1][0] + 1;  //删除操作
        }
        //填充第一行，word1 = ''
        for (int j = 1; j <= len2; j++) {
            dp[0][j] = dp[0][j - 1] + 1;  //增加操作
        }
        for (int i = 1; i <= len1; i++) {
            for (int j = 1; j <= len2; j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)){
                    dp[i][j] = dp[i - 1][j - 1];
                }else {
                    dp[i][j] = Math.min(dp[i][j - 1],Math.min(dp[i - 1][j], dp[i - 1][j - 1])) + 1;
                }
            }
        }
        return dp[len1][len2];
    }
}






























