package Top100;

/**
 * @author mxy
 * @create 2022-11-28 11:01
 */

import java.util.*;

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
 */
public class Top139 {

    public static void main(String[] args) {
        Top139 top139 = new Top139();
        //String s = "applepenapple";
        //List<String> wordDict = new ArrayList(){{
        //    add("apple");
        //    add("pen");
        //}};


        String s = "catsandog";
        List<String> wordDict = new ArrayList(){{
            add("cats");
            add("dog");
            add("sand");
            add("and");
            add("cat");
        }};

        //String s = "aaaaaaa";
        //List<String> wordDict = new ArrayList(){{
        //    add("aaaa");
        //    add("aaa");
        //}};
        System.out.println(top139.wordBreak(s, wordDict));
    }

    /**
     * 题解链接： https://leetcode.cn/problems/word-break/solution/dan-ci-chai-fen-by-leetcode-solution/
     * 思路分析：
     * 1、dp[i] 表示字符串 s 前 i 个字符组成的字符串 s[0..i-1] 是否能被空格拆分成若干个字典中出现的单词
     * 2、从前往后计算考虑转移方程，每次转移的时候我们需要枚举包含位置 i−1 的最后一个单词，看它是否出现在字典中 以及 除去这部分的字符串是否合法即可。
     * 3、公式化来说，我们需要枚举 s[0..i-1] 中的分割点 j ，看 s[0..j-1] 组成的字符串 s1（默认 j = 0 时 s1 为空串）和 s[j..i-1] 组成的字符串 s2是否都合法，
     *    如果两个字符串均合法，那么按照定义 s1 和 s2 拼接成的字符串也同样合法。
     * 4、由于计算到  dp[i] 时我们已经计算出了 dp[0..i−1] 的值，因此字符串 s1 是否合法可以直接由 dp[j] 得知，
     *    剩下的我们只需要看 s2 是否合法即可，因此我们可以得出如下转移方程：
     *          dp[i] = dp[j] && check(s[j..i−1])
     *     其中 ( check(s[j..i−1] ) 表示子串 s[j..i-1]s[j..i−1] 是否出现在字典中。
     * 5、对于检查一个字符串是否出现在给定的字符串列表里一般可以考虑哈希表来快速判断，同时也可以做一些简单的剪枝，
     *     枚举分割点的时候倒着枚举，如果分割点 j 到 i 的长度已经大于字典列表里最长的单词的长度，那么就结束枚举，
     *     但是需要注意的是下面的代码给出的是不带剪枝的写法。
     * 6、对于边界条件，我们定义 dp[0]=true 表示空串且合法。
     * 7、有能力的读者也可以考虑怎么结合字典树 Trie 来实现，这里不再展开。
     * 复杂度分析：
     *      时间复杂度：O(n^2)，其中 n 为字符串 s 的长度。我们一共有 O(n) 个状态需要计算，每次计算需要枚举 O(n) 个分割点，
     *                哈希表判断一个字符串是否出现在给定的字符串列表需要 O(1) 的时间，因此总时间复杂度为 O(n^2)。
     *      空间复杂度：O(n) ，其中 n 为字符串 s 的长度。我们需要 O(n) 的空间存放 dp 值以及哈希表亦需要 O(n) 的空间复杂度，因此总空间复杂度为 O(n)。
     * @param s
     * @param wordDict
     * @return
     */
    //不剪枝
    //执行用时： 6 ms , 在所有 Java 提交中击败了 72.33% 的用户
    //内存消耗： 41.8 MB , 在所有 Java 提交中击败了 29.09% 的用户
    /*public boolean wordBreak(String s, List<String> wordDict) {
        Set<String> wordDictSet = new HashSet<>(wordDict);
        boolean[] dp = new boolean[s.length() + 1];
        dp[0] = true;
        for (int i = 1; i <= s.length(); i++) {
            for (int j = 0; j < i; j++) {
                if (dp[j] && wordDictSet.contains(s.substring(j, i))){
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[s.length()];
    }*/

    /**
     * 代码优化：dp[i]只需要往前探索到词典里最长的单词即可
     * 执行用时： 1 ms , 在所有 Java 提交中击败了 99.61% 的用户
     * 内存消耗： 39.5 MB , 在所有 Java 提交中击败了 96.09% 的用户
     */
    public boolean wordBreak(String s, List<String> wordDict) {
        int len = s.length(), maxw = 0;
        boolean[] dp = new boolean[len + 1];
        dp[0] = true;
        Set<String> set = new HashSet<>(wordDict);
        for (String s1 : set) {
            maxw = Math.max(maxw, s1.length());
        }
        //枚举分割点的时候倒着枚举，如果分割点 j 到 i 的长度已经大于字典列表里最长的单词的长度，那么就结束枚举，
        for (int i = 1; i <= len; i++) {
            //当 截取单词长度 i - j > maxw 时,可剪枝；故循环条件：i - j <= maxw ==> j >= i - maxw
            for (int j = i; j >=0 && j >= i - maxw; j--) {
                if (dp[j] && set.contains(s.substring(j, i))){
                    dp[i] = true;
                    break;
                }
            }
        }
        return dp[len];
    }


    /**
     * dfs遍历：
     * 执行用时： 1 ms , 在所有 Java 提交中击败了 99.61% 的用户
     * 内存消耗： 41.4 MB , 在所有 Java 提交中击败了 70.96% 的用户
     * @param s
     * @param wordDict
     * @return
     */
    /*public boolean wordBreak(String s, List<String> wordDict) {
        int[] visited = new int[s.length() + 1];
        return dfs(s, 0, wordDict, visited);
    }
    private boolean dfs(String s, int start, List<String> wordDict, int[] visited) {
        if (start >= s.length()) return true;
        if (visited[start] == 1) return false;
        boolean meet;
        for (int i = 0; i < wordDict.size(); i++) {
            String temp = s.substring(start);
            if (temp.startsWith(wordDict.get(i))) {
                visited[start] = 1;
                int nextstart = start + wordDict.get(i).length();
                meet = dfs(s, nextstart, wordDict, visited);
                if (meet) return true;
            }
        }
        return false;
    }*/
}
