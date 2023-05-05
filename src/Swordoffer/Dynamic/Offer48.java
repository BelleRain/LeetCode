package Swordoffer.Dynamic;

/**
 * @author mxy
 * @create 2022-09-26 9:15
 */

import java.util.HashMap;
import java.util.Map;

/**
 * 最长不含重复字符的子字符串
 * 请从字符串中找出一个最长的不包含重复字符的子字符串，计算该最长子字符串的长度。
 *
 * 示例 1:
 * 输入: "abcabcbb"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
 *
 * 示例 2:
 * 输入: "bbbbb"
 * 输出: 1
 * 解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
 *
 * 示例 3:
 * 输入: "pwwkew"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
 *      请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
 */
public class Offer48 {

    public static void main(String[] args) {
        Offer48 offer = new Offer48();
        String s = "abcabcbb";
        int res = offer.lengthOfLongestSubstring(s);
        System.out.println(res);
    }

    /**
     * 解题思路： 原文链接：https://leetcode.cn/problems/zui-chang-bu-han-zhong-fu-zi-fu-de-zi-zi-fu-chuan-lcof/solution/mian-shi-ti-48-zui-chang-bu-han-zhong-fu-zi-fu-d-9/
     * 长度为 N 的字符串共有 ((1+N)N)/2 个子字符串（复杂度为 O(N^2) 判断长度为 N 的字符串是否有重复字符的复杂度为 O(N) ，因此本题使用暴力法解决的复杂度为 O(N^3)
     * 考虑使用动态规划降低时间复杂度。
     * 动态规划解析：
     *  状态定义： 设动态规划列表 dp ，dp[j] 代表以字符 s[j] 为结尾的 “最长不重复子字符串” 的长度。
     *  转移方程： 固定右边界 j ，设字符 s[j] 左边距离最近的相同字符为 s[i] ，即 s[i] = s[j] 。
     *      1、 当 i < 0 ，即 s[j] 左边无相同字符，则 dp[j] = dp[j-1] + 1 ；
     *      2、 当 dp[j - 1] < j - i ，说明字符 s[i] 在子字符串 dp[j-1] 区间之外 ，则 dp[j] = dp[j - 1] + 1 ；
     *      3、 当 dp[j−1] ≥ j−i ，说明字符 s[i] 在子字符串 dp[j-1] 区间之中 ，则 dp[j] 的左边界由 s[i] 决定，即 dp[j] = j - i ；
     *      当 i < 0 时，由于 dp[j−1] ≤ j 恒成立，因而 dp[j - 1] < j - i 恒成立，因此分支 1. 和 2. 可被合并。
     *          dp[j]=
     *                  dp[j−1]+1   ,dp[j−1]<j−i
     *                  j−i         ,dp[j−1]≥j−i
     *     返回值： max(dp) ，即全局的 “最长不重复子字符串” 的长度。
     * 空间复杂度优化：
     *      由于返回值是取 dp 列表最大值，因此可借助变量 tmp 存储 dp[j] ，变量 res 每轮更新最大值即可。
     *      此优化可节省 dp 列表使用的 O(N) 大小的额外空间。
     * 观察转移方程，可知问题为：每轮遍历字符 s[j] 时，如何计算索引 i ？
     * 以下介绍 哈希表 ， 线性遍历 两种方法。
     *
     */

    /**
     * 方法一：动态规划 + 哈希表
     * 哈希表统计： 遍历字符串 s 时，使用哈希表（记为 dic ）统计 各字符最后一次出现的索引位置 。
     * 左边界 i 获取方式： 遍历到 s[j] 时，可通过访问哈希表 dic[s[j]] 获取最近的相同字符的索引 i 。
     * 复杂度分析：
     *      时间复杂度 O(N) ： 其中 N 为字符串长度，动态规划需遍历计算 dp 列表。
     *      空间复杂度 O(1) ： 字符的 ASCII 码范围为 0 ~ 127 ，哈希表 dic 最多使用 O(128) = O(1) 大小的额外空间。
     *
     *               dp[j]=   dp[j−1]+1   ,dp[j−1]<j−i
     *                        j−i         ,dp[j−1]≥j−i
     * @param s
     * @return
     */
    //滑动窗口
    //借助变量 tmp 存储 dp[j]
    public int lengthOfLongestSubstring(String s) {
        Map<Character, Integer> dic = new HashMap<>();
        int res = 0,tmp = 0;
        for (int j = 0; j < s.length(); j++) {
            int i = dic.getOrDefault(s.charAt(j), -1); //获取索引i
            dic.put(s.charAt(j), j);  //更新哈希表
            tmp = tmp < j-i ? tmp + 1 : j-i;  //dp[j-1] -> dp[j]
            res = Math.max(res, tmp);
        }
        return res;
    }

    /**
     * 方法二： 动态规划 + 线性遍历
     *  左边界 i 获取方式： 遍历到 s[j] 时，初始化索引 i = j - 1 ，向左遍历搜索第一个满足 s[i] = s[j] 的字符即可 。
     * 复杂度分析：
     *   时间复杂度 O(N^2) ： 其中 N 为字符串长度，动态规划需遍历计算 dp 列表，占用 O(N) ；每轮计算 dp[j] 时搜索 i 需要遍历 j 个字符，占用 O(N) 。
     *   空间复杂度 O(1) ： 几个变量使用常数大小的额外空间。
     * @param s
     * @return
     */
    //public int lengthOfLongestSubstring(String s){
    //    HashMap<Character, Integer> dic = new HashMap<>();
    //    int res = 0,tmp = 0;
    //    for (int j = 0; j < s.length(); j++) {
    //        int i = j-1;
    //        while (i>=0 && s.charAt(i) != s.charAt(j))
    //            i--;  //线性查找
    //        tmp = tmp < j-i ? tmp + 1 : j-i;
    //        res = Swordoffer.Math.max(res, tmp);
    //    }
    //    return res;
    //}

    /**
     * 方法三：双指针 + 哈希表
     * 本质上与方法一类似，不同点在于左边界 i 的定义。
     *     哈希表 dic 统计： 指针 j 遍历字符 s ，哈希表统计字符 s[j] 最后一次出现的索引 。
     *    更新左指针 i ： 根据上轮左指针 i 和 dic[s[j]] ，每轮更新左边界 i ，保证区间 [i + 1, j] 内无重复字符且最大。
     *        i=max(dic[s[j]],i)
     *    更新结果 res ： 取上轮 res 和本轮双指针区间 [i + 1,j] 的宽度（即 j - i ）中的最大值。
     *      res=max(res,j−i)
     * 复杂度分析：
     *    时间复杂度 O(N) ： 其中 N 为字符串长度，动态规划需遍历计算 dp 列表。
     *   空间复杂度 O(1) ： 字符的 ASCII 码范围为 0 ~ 127 ，哈希表 dic 最多使用 O(128) = O(1) 大小的额外空间。
     * @param s
     * @return
     */
    //public int lengthOfLongestSubstring(String s){
    //    HashMap<Character, Integer> dic = new HashMap<>();
    //    int i = -1,res = 0;
    //    for (int j = 0; j < s.length(); j++) {
    //        if (dic.containsKey(s.charAt(j)))
    //            i = Swordoffer.Math.max(i,dic.get(s.charAt(j))); //更新左指针i
    //        dic.put(s.charAt(j), j); //哈希表记录
    //        res = Swordoffer.Math.max(res, j-i);
    //    }
    //    return res;
    //}

    /**
     * 执行用时： 1 ms , 在所有 Java 提交中击败了 100.00% 的用户
     * 内存消耗： 41.3 MB , 在所有 Java 提交中击败了 74.20% 的用户
     * @param s
     * @return
     */
    //public int lengthOfLongestSubstring(String s) {
    //    int ans = 0;
    //    boolean[] vis = new boolean[128];
    //    int n = s.length();
    //    int i = 0;
    //    int start = 0;
    //    while (i < n) {
    //        int idx = s.charAt(i);
    //        while (vis[idx]) {  //寻找左指针
    //            vis[s.charAt(start++)] = false;
    //        }
    //        vis[idx] = true;
    //        ans = Swordoffer.Math.max(i - start + 1, ans);
    //        i++;
    //    }
    //    return ans;
    //}


}












































