package CodeTop;

/**
 * @author mxy
 * @create 2023-03-15 8:55
 */

import sun.text.resources.cldr.naq.FormatData_naq;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * 3. 无重复字符的最长子串    链接：https://leetcode.cn/problems/longest-substring-without-repeating-characters
 * 给定一个字符串 s ，请你找出其中不含有重复字符的 最长子串 的长度。
 *
 * 示例 1:
 * 输入: s = "abcabcbb"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
 *
 * 示例 2:
 * 输入: s = "bbbbb"
 * 输出: 1
 * 解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
 *
 * 示例 3:
 * 输入: s = "pwwkew"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
 *      请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
 *  
 * 提示：
 * 0 <= s.length <= 5 * 10^4
 * s 由英文字母、数字、符号和空格组成
 */
public class Code03 {

    public static void main(String[] args) {
        Code03 code03 = new Code03();
        String str = "abcabcbb";
        System.out.println(code03.lengthOfLongestSubstring(str));
    }

    public int lengthOfLongestSubstring(String s) {
        if (s.length() == 0) return 0;
        int res = 0;
        Map<Character, Integer> map = new HashMap<>();
        for(int i = 0,j = 0; i < s.length(); i++){
            if (map.containsKey(s.charAt(i))){
                j = Math.max(j, map.get(s.charAt(i)) + 1); //将左边界重复字符移出窗口
            }
            map.put(s.charAt(i), i);
            res = Math.max(res, i - j + 1);
        }
        return res;
    }
}
