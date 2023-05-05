package Top100;

/**
 * @author mxy
 * @create 2022-11-15 8:17
 */

import java.awt.datatransfer.StringSelection;

/**
 * 5. 最长回文子串
 * 给你一个字符串 s，找到 s 中最长的回文子串。
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
 */
public class Top5 {

    /**
     * 题解一：暴力解法 （超时）
     * @param s
     * @return
     */
    /*public String longestPalindrome(String s) {
        String ans = "";
        int max = 0 , length = s.length();
        for (int i = 0; i < length; i++) {
            for (int j = i+1; j <= length; j++) {
                String test = s.substring(i, j);
                if (isPalindromic(test) && max < test.length()){
                    ans = s.substring(i,j);
                    max = Math.max(max,ans.length());
                }
            }
        }
        return ans;
    }

    public boolean isPalindromic(String s){
        int len = s.length(); //子串的长度
        for (int i = 0; i < len / 2; i++) {
            if (s.charAt(i) != s.charAt(len - i - 1)) return false;
        } // i + len - i - 1 = len - 1; 两位置对称
        return true;
    }*/

    /**
     * 题解链接：https://leetcode.cn/problems/longest-palindromic-substring/solution/zui-chang-hui-wen-zi-chuan-by-leetcode-solution/
     */

    /**
     * 方法一：动态规划
     * 复杂度分析：
     *      时间复杂度：O(n^2)，其中 n 是字符串的长度。动态规划的状态总数为 O(n^2)，对于每个状态，我们需要转移的时间为 O(1)。
     *      空间复杂度：O(n^2)，即存储动态规划状态需要的空间。
     * @param s
     * @return
     */
    /*public String longestPalindrome(String s) {
        int len = s.length();
        if (len < 2) return s; //如果字符串的长度 < 2,则为回文串
        int maxLen = 1,begin = 0;
        //dp[i][j]表示s[i..j]是否是回文串
        boolean[][] dp = new boolean[len][len];
        //初始化：所有长度为1的子串都是回文串
        for (int i = 0; i < len; i++) {
            dp[i][i] = true;
        }
        char[] charArray = s.toCharArray();
        //递推开始
        //先枚举子串
        for (int L = 2; L <= len; L++) {
            //枚举左边界，左边界的上限设置可以宽松一些
            for (int i = 0; i < len; i++) {
                //由L和i可以确定右边界，即j-i+1 = L得
                int j = L + i - 1;
                //如果右边界越界，就可以退出当前循环
                if (j >= len) break;
                *//*
                如果左边界不等于右边界，则s[i,...,j]不是回文串
                如果左边界等于右边界，j - i = 0,1,2 s[i...j] 为回文子串
                j - i >= 3 , 则在左右边界相等的情况下，若s[i+1,...,j-1]是回文串，则s[i,...,j]是回文串。
                 *//*
                if (charArray[i] != charArray[j]) dp[i][j] = false;
                else {
                    if (j - i < 3) dp[i][j] = true;
                    else dp[i][j] = dp[i + 1][j - 1]; //递推公式
                }
                //只要dp[i][j] == true 成立，就表示子串s[i..j]是回文，此时记录回文长度和起始点
                if (dp[i][j] && j - i + 1 > maxLen){
                    maxLen = j - i + 1;
                    begin = i;
                }
            }
        }
        return s.substring(begin, begin + maxLen);
    }*/


    /**
     * 方法二：中心扩展算法
     * 枚举所有的 回文中心 并尝试 扩展 ，直到无法扩展为止，此时的回文子串长度即为此 回文中心 下的 最长回文长度。
     * 我们对所有的长度求出最大值，即可得到最终的答案。
     * @param s
     * @return
     */
    public String longestPalindrome(String s) {
        if(s == null || s.length() < 1) return "";
        int start = 0 , end = 0;
        for (int i = 0; i < s.length(); i++) {
            //回文中心，可能是 一个字符， 也可能是 两个重复字符
            int len1 = expandAroundCenter(s, i, i);
            int len2 = expandAroundCenter(s, i, i + 1);
            int len = Math.max(len1, len2); //回文子串的长度
            if (len > end - start){
                start = i - (len - 1)/2; //回文子串的起点
                end = i + len/2; //回文子串的终点
            }
        }
        return s.substring(start, end + 1); //subString截取字符串范围[start , end + 1);
    }

    public int expandAroundCenter(String s, int left, int right){
        //由中心向两边扩展
        while (left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)){
            --left;
            ++right;
        }
        return right - left - 1;
    }


    /**
     * 较优答案1：
     * 执行用时： 6 ms , 在所有 Java 提交中击败了 95.90% 的用户
     * 内存消耗： 41.4 MB , 在所有 Java 提交中击败了 73.77% 的用户
     * @param s
     * @return
     */
    /*public String longestPalindrome(String s) {
        int maxLen = 1;
        int start = 0;
        char[] c = s.toCharArray();
        for (int i = 0; i < c.length - 1; i++) {
            int singleLen = longestSub(c, i, i);
            int doubleLen = longestSub(c, i, i + 1);
            int len = Math.max(singleLen, doubleLen);
            if (len > maxLen) {
                maxLen = len;
                start = i - (maxLen - 1) / 2; //回文串的起点
            }
        }
        return s.substring(start, start + maxLen);
    }

    //返回最大回文串长度
    public static int longestSub(char[] c, int i, int j) {
        while (i >= 0 && j <= c.length - 1) {
            if (c[i] != c[j]) {
                return j - i - 1;
            }
            i--;
            j++;
        }
        if (i == -1 || j == c.length) {
            i++;
            j--;
        }
        return j - i + 1;
    }*/

    /**
     * 较优答案2：
     * 执行用时： 1 ms , 在所有 Java 提交中击败了 100.00% 的用户
     * 内存消耗： 41.2 MB , 在所有 Java 提交中击败了 83.84% 的用户
     */
    /*int start = 0, end = 0;
    public String longestPalindrome(String s) {
        if (s.length() < 2)
            return s;
        char[] c = s.toCharArray();
        longestPallindromeAt(c, 0);
        return s.substring(start, end + 1);
    }
    private void longestPallindromeAt(char[] c, int p) {
        int a = p;
        int b = p;
        int n = c.length;
        if ((p == n - 1 || (n - p) < (end - start + 1)/2)) return;
        while (b < n - 1 && c[b] == c[b + 1]) b++;
        p = b;
        while (a > 0 && b < n - 1 && c[a - 1] == c[b + 1])
        {
            a--;
            b++;
        }
        if ((b - a) > (end - start))
        {
            end = b;
            start = a;
        }
        longestPallindromeAt(c, p + 1);
    }*/
}
