package Swordoffer.Dynamic;

/**
 * @author mxy
 * @create 2022-11-01 10:40
 */

/**
 * 正则表达式匹配
 * 请实现一个函数用来匹配包含'.'和'*'的正则表达式。模式中的字符'.'表示任意一个字符，而'*'表示它前面的字符可以出现任意次（含0次）。
 * 在本题中，匹配是指字符串的所有字符匹配整个模式。例如，字符串"aaa"与模式"a.a"和"ab*ac*a"匹配，但与"aa.a"和"ab*a"均不匹配。
 *
 * 示例 1:
 * 输入:
 * s = "aa"
 * p = "a"
 * 输出: false
 * 解释: "a" 无法匹配 "aa" 整个字符串。
 *
 * 示例 2:
 * 输入:
 * s = "aa"
 * p = "a*"
 * 输出: true
 * 解释: 因为 '*' 代表可以匹配零个或多个前面的那一个元素, 在这里前面的元素就是 'a'。因此，字符串 "aa" 可被视为 'a' 重复了一次。
 *
 * 示例 3:
 * 输入:
 * s = "ab"
 * p = ".*"
 * 输出: true
 * 解释: ".*" 表示可匹配零个或多个（'*'）任意字符（'.'）。
 *
 * 示例 4:
 * 输入:
 * s = "aab"
 * p = "c*a*b"
 * 输出: true
 * 解释: 因为 '*' 表示零个或多个，这里 'c' 为 0 个, 'a' 被重复一次。因此可以匹配字符串 "aab"。
 *
 * 示例 5:
 * 输入:
 * s = "mississippi"
 * p = "mis*is*p*."
 * 输出: false
 *
 * s 可能为空，且只包含从 a-z 的小写字母。
 * p 可能为空，且只包含从 a-z 的小写字母以及字符 . 和 *，无连续的 '*'。
 */
public class Offer19 {

    public static void main(String[] args) {
        Offer19 offer = new Offer19();
        System.out.println(offer.isMatch("11", ""));
    }

    /**
     * 题解链接：https://leetcode.cn/problems/zheng-ze-biao-da-shi-pi-pei-lcof/solution/jian-zhi-offer-19-zheng-ze-biao-da-shi-pi-pei-dong/
     * 题解一：
     * s = "aaa", p = "ab*.*"
     *            a b * . *  [j]
     *          1 0 0 0 0 0
     *     [i]a 0 0 0 0 0 0
     *        a 0 0 0 0 0 0
     *        a 0 0 0 0 0 0
     * 状态转移矩阵：m*n , dp[i][j]
     * 复杂度分析：
     * 时间复杂度 O(MN) ： 其中 M, N 分别为 s 和 p 的长度，状态转移需遍历整个 dp 矩阵。
     * 空间复杂度 O(MN) ： 状态矩阵 dp 使用 O(MN) 的额外空间。
     * @param s
     * @param p
     * @return
     */
    public boolean isMatch(String s, String p) {
        //状态转移矩阵m*n
        int m = s.length() + 1 ,n = p.length() + 1;
        //dp:动态规划矩阵0
        //dp[i][j]:字符串s的前i个字符和字符串p的前j个字符能否匹配
        boolean[][] dp = new boolean[m][n];
        //dp[0][0],代表空字符状态，dp[i][j]对应添加字符是s[i-1]和p[j-1]
        //（注意i,j与真实的s,p数组的下标相差1，i,j是状态矩阵的下标，i-1,j-1才是对应的 s,p数组的索引）
        //dp[0][0]代表两个空字符串能够匹配
        dp[0][0] = true;
        //初始化首行0，即当s为空时
        //当p为空时，则匹配成功，dp[0][0] = true
        //当p不为空时，若p形如 “a*b*c*”，即当p的偶数位（从1算起，即按照j的奇偶，而不是j-1的奇偶）为 * 时，才能够匹配
        //(即让p的奇数位出现0次，保持p是空字符串)，因此循环遍历字符串p，步长为2（即只看偶数位）。
        for (int j = 2; j < n; j+=2) {
            dp[0][j] = dp[0][j-2] && p.charAt(j-1) == '*';
        }
        //状态转移
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                //当p[j-1] = '*'时，dp[i][j]在当以下任一情况为true时，等于true:
                if (p.charAt(j-1) == '*'){
                    //dp[i][j-2]=true,代表即将字符组合p[j-2]*看作0次时，能否匹配
                    //此时p[j-2]*属于冗余匹配，直接废掉，向前看dp[i][j-2]
                    if (dp[i][j-2]) dp[i][j] = true; //1.
                    //dp[i-1][j] = true, s[i-1] = p[j-2].代表让字符p[j-2]多出现一次时，能否匹配
                    //即s[i-1]是冗余匹配，可以由p[j-2]*补充
                    //所以向前看，dp[i-1][j]是否等于true，若等于true，则匹配
                    else if (dp[i-1][j] && s.charAt(i-1) == p.charAt(j-2)) dp[i][j] = true;//2.
                    //dp[i-1][j] && p[j-2]=".",代表让字符"."多出现1次时，能否匹配
                    //p[j-2]为'.',则'.'匹配了s[i-1],可以继续往前看dp[i-1][j]
                    //注意这里是".*"的情形,也就是"万能串",生成"......"可以匹配任何非空s
                    else if (dp[i-1][j] && p.charAt(j-2) == '.') dp[i][j] = true;//3.
                }else {//p[j-1] != '*'时，dp[i][j]在当以下任一情况为true时，等于true:
                    //dp[i - 1][j - 1] 且 s[i - 1] = p[j - 1]： 即让字符 p[j - 1] 多出现一次时，能否匹配；
                    //若p[j-1]==s[i-1](必定为'a'-'z'),继续看dp[i-1][j-1]
                    if (dp[i-1][j-1] && s.charAt(i-1) == p.charAt(j-1)) dp[i][j] = true; //1.
                    //dp[i - 1][j - 1] 且 p[j - 1] = '.'： 即将字符 . 看作字符 s[i - 1] 时，能否匹配；
                    //若p[j-1]为'.',直接看dp[i-1][j-1]
                    else if (dp[i-1][j-1] && p.charAt(j-1) == '.') dp[i][j] = true;//2.
                }
            }
        }
        return dp[m-1][n-1];
    }

    /*//利用布尔计算简化代码
    public boolean isMatch(String s, String p) {
        int m = s.length() + 1, n = p.length() + 1;
        boolean[][] dp = new boolean[m][n];
        dp[0][0] = true;
        for(int j = 2; j < n; j += 2)
            dp[0][j] = dp[0][j - 2] && p.charAt(j - 1) == '*';
        for(int i = 1; i < m; i++) {
            for(int j = 1; j < n; j++) {
                dp[i][j] = p.charAt(j - 1) == '*' ?
                        dp[i][j - 2] || dp[i - 1][j] && (s.charAt(i - 1) == p.charAt(j - 2) || p.charAt(j - 2) == '.') :
                        dp[i - 1][j - 1] && (p.charAt(j - 1) == '.' || s.charAt(i - 1) == p.charAt(j - 1));
            }
        }
        return dp[m - 1][n - 1];
    }*/

    /**
     * 题解链接：https://leetcode.cn/problems/zheng-ze-biao-da-shi-pi-pei-lcof/solution/zhu-xing-xiang-xi-jiang-jie-you-qian-ru-shen-by-je/
     * 递归思想
     * @param A
     * @param B
     * @return
     */
    /*public boolean isMatch(String A, String B) {
        // 如果字符串长度为0，需要检测下正则串
        if (A.length() == 0) {
            // 如果正则串长度为奇数，必定不匹配，比如 "."、"ab*",必须是 a*b*这种形式，*在奇数位上
            if (B.length() % 2 != 0) return false;
            int i = 1;
            while (i < B.length()) {
                if (B.charAt(i) != '*') return false;
                i += 2;
            }
            return true;
        }
        // 如果字符串长度不为0，但是正则串没了，return false
        if (B.length() == 0) return false;
        // c1 和 c2 分别是两个串的当前位，c3是正则串当前位的后一位，如果存在的话，就更新一下
        char c1 = A.charAt(0), c2 = B.charAt(0), c3 = 'a';
        if (B.length() > 1) {
            c3 = B.charAt(1);
        }
        // 和dp一样，后一位分为是 '*' 和不是 '*' 两种情况
        if (c3 != '*') {
            // 如果该位字符一样，或是正则串该位是 '.',也就是能匹配任意字符，就可以往后走
            if (c1 == c2 || c2 == '.') {
                return isMatch(A.substring(1), B.substring(1));
            } else {
                // 否则不匹配
                return false;
            }
        } else {
            // 如果该位字符一样，或是正则串该位是 '.'，和dp一样，有看和不看两种情况
            if (c1 == c2 || c2 == '.') {
                return isMatch(A.substring(1), B) || isMatch(A, B.substring(2));
            } else {
                // 不一样，那么正则串这两位就废了，直接往后走
                return isMatch(A, B.substring(2));
            }
        }
    }*/


}

















