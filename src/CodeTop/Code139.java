package CodeTop;

/**
 * @author mxy
 * @create 2023-05-19 9:38
 */

import javax.lang.model.element.NestingKind;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 139. 单词拆分    链接：https://leetcode.cn/problems/word-break
 * 给你一个字符串 s 和一个字符串列表 wordDict 作为字典。请你判断是否可以利用字典中出现的单词拼接出 s 。
 * 注意：不要求字典中出现的单词全部都使用，并且字典中的单词可以重复使用。
 *
 * 示例 1：
 * 输入: s = "leetcode", wordDict = ["leet", "code"]
 * 输出: true
 * 解释: 返回 true 因为 "leetcode" 可以由 "leet" 和 "code" 拼接成。
 *
 * 示例 2：
 * 输入: s = "applepenapple", wordDict = ["apple", "pen"]
 * 输出: true
 * 解释: 返回 true 因为 "applepenapple" 可以由 "apple" "pen" "apple" 拼接成。
 *      注意，你可以重复使用字典中的单词。
 *
 * 示例 3：
 * 输入: s = "catsandog", wordDict = ["cats", "dog", "sand", "and", "cat"]
 * 输出: false
 *
 * 提示：
 * 1 <= s.length <= 300
 * 1 <= wordDict.length <= 1000
 * 1 <= wordDict[i].length <= 20
 * s 和 wordDict[i] 仅有小写英文字母组成
 * wordDict 中的所有字符串 互不相同
 *
 */
public class Code139 {

    public static void main(String[] args) {
        String s = "leetcode";
        List<String> wordDict = new ArrayList(){{
            add("leet");
            add("code");
        }};
        System.out.println(wordBreak2(s, wordDict));
    }

    public static boolean wordBreak1(String s, List<String> wordDict) {
        Set<String> set = new HashSet<>();
        for (String s1 : wordDict) {
            set.add(s1);
        }
        int length = s.length();
        // dp[i] 表示字符串 s 前 i 个字符组成的字符串 s[0..i-1] 是否能被空格拆分成若干个字典中出现的单词
        boolean[] dp = new boolean[length + 1];
        // 定义 dp[0]=true 表示空串且合法。
        dp[0] = true;
        for (int i = 1; i <= s.length(); i++) {
            for (int j = 0; j < i; j++) {
                // j为分界点[0,...j - 1],[j,...,i - 1]
                // dp[i] = dp[j] && check(s[j..i−1])
                if (dp[j] && set.contains(s.substring(j, i))){
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[length];
    }


    //代码优化：dp[i]只需要往前探索到词典里最长的单词即可
    public static boolean wordBreak2(String s, List<String> wordDict) {
        Set<String> set = new HashSet<>(wordDict);
        int maxw = 0;
        for (String s1 : wordDict) {
            maxw = Math.max(maxw, s1.length());
        }
        boolean[] dp = new boolean[s.length() + 1];
        dp[0] = true;
        //枚举分割点的时候倒着枚举，如果分割点 j 到 i 的长度已经大于字典列表里最长的单词的长度，那么就结束枚举，
        for (int i = 1; i <= s.length(); i++) {
            //当 截取单词长度 i - j > maxw 时,可剪枝；故循环条件：i - j <= maxw ==> j >= i - maxw
            for (int j = i; j >= 0 && i - j <= maxw; j--) {
                if (dp[j] && set.contains(s.substring(j, i))){
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[s.length()];
    }

    /**
     * dfs遍历
     * @param s
     * @param wordDict
     * @return
     */
    public static boolean wordBreak3(String s, List<String> wordDict) {
        int[] visited = new int[s.length() + 1];
        return dfs(s,0,wordDict,visited);
    }

    private static boolean dfs(String s, int start, List<String> wordDict, int[] visited) {
        if (start >= s.length()) return true;
                               if (visited[start] == 1) return false;
        boolean meet;
        for (int i = 0; i < wordDict.size(); i++) {
            String temp = s.substring(start);
            if (temp.startsWith(wordDict.get(i))){
                visited[start] = 1;
                int nextstart = start + wordDict.get(i).length();
                meet = dfs(s, nextstart, wordDict, visited);
                if (meet) return true;
            }
        }
        return false;
    }
}












































