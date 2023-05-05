package CodeTop;

/**
 * @author mxy
 * @create 2023-03-22 8:43
 */

/**
 * 5. 最长回文子串    链接：https://leetcode.cn/problems/longest-palindromic-substring
 * 给你一个字符串 s，找到 s 中最长的回文子串。
 * 如果字符串的反序与原始字符串相同，则该字符串称为回文字符串。
 *
 * 示例 1：
 * 输入：s = "babad"
 * 输出："bab"
 * 解释："aba" 同样是符合题意的答案。
 *
 * 示例 2：
 * 输入：s = "cbbd"
 * 输出："bb"
 *  
 * 提示：
 * 1 <= s.length <= 1000
 * s 仅由数字和英文字母组成
 *
 */
public class Code05 {

    public static void main(String[] args) {
        System.out.println(longestPalindrome1("ba"));
        System.out.println(longestPalindrome2("ba"));
    }

    /**
     * 题解链接：https://leetcode.cn/problems/longest-palindromic-substring/solution/zhong-xin-kuo-san-dong-tai-gui-hua-by-liweiwei1419/
     */

    /**
     * 动态规划：
     *  1、状态定义：dp[i][j]: 表示 s[i,...,j] 的子串是否为 回文子串，其中 s[i,..,j] 包含 s[i]、s[j]，长度严格大于2
     *  2、动态规划方程：
     *              dp[i][j] = (s[i] == s[j]) && (dp[i+1][j-1])
     *  3、 1）i 始终 <= j, 只填充 对角线上方的矩阵即可。
     *      2）i == j, dp[i][j] = true;
     *      3) i != j,
     *              1) s[i] != s[j], dp[i][j] = false
     *              2) s[i] == s[j],
     *                      1) (j-1) - (i+1) + 1 < 2（即 除去s[i],s[j]后中间的子串小于 2 ）, ==> j - i < 3,dp[i][j] = true
     *                         （ j - i + 1 < 4, 即 s[i,...,j] 的长度 为 2,3时，若 s[i] == s[j],则 为回文子串,dp[i][j] = true）
     *                      2) dp[i][j] = dp[i+1][j-1]
     *  4、记录每次 dp[i][j] == true 时 的 len，与 每次maxlen比较，最后取最大值,此时 begin = i；
     *  5、返回截取的字符串 subString(begin,begin + maxLen);
     * @param s
     * @return
     */
    public static String longestPalindrome1(String s) {
        if (s.length() < 2) return s;
        int n = s.length();
        boolean[][] dp = new boolean[n][n];
        int begin = 0;
        int maxLen = 1;

        //初始化
        for (int i = 0; i < n; i++) {
            dp[i][i] = true;
        }

        //动态规划
        for (int j = 0; j < n; j++) {
            for (int i = 0; i < j; i++) {
                if (s.charAt(i) != s.charAt(j)){
                    dp[i][j] = false;
                }else {
                    if (j - i < 3){
                        dp[i][j] = true;
                    }else {
                        dp[i][j] = dp[i + 1][j - 1];
                    }
                }

                if (dp[i][j] && (j - i + 1) > maxLen){
                    maxLen = j - i + 1;
                    begin = i;
                }
            }
        }
        return s.substring(begin, begin + maxLen);
    }


    /**
     * 中心扩散：
     * @param s
     * @return
     */
    public static String longestPalindrome2(String s) {
        if (s.length() < 2) return s;
        int len = s.length();
        int maxLen = 0;
        //第一位记录起始长度，第二位存储长度
        int[] res = new int[2];
        //回文中心，最后一位不用扩展，i 取值 截止至 len - 2
        for (int i = 0; i < len - 1; i++) {
            int[] odd = expandArroundCenter(s,i,i);  //奇数子串
            int[] even = expandArroundCenter(s,i,i + 1);  //偶数子串
            int[] max = odd[1] > even[1] ? odd : even;
            if (max[1] > maxLen){
                res = max;
                maxLen = max[1];
            }
        }
        return s.substring(res[0], res[0] + res[1]);
    }

    private static int[] expandArroundCenter(String s, int left, int right) {
        int len = s.length();
        while (left >= 0 && right < len){
            if (s.charAt(left) == s.charAt(right)){
                left--;
                right++;
            }else {
                break;
            }
        }
        //left + 1 为回文子串的起始索引
        return new int[]{left + 1,right - left - 1};  //(right - 1) - (left + 1) + 1 = right - left - 1
    }

}
