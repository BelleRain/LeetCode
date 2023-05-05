package Subject.滑动窗口;

/**
 * @author mxy
 * @create 2022-11-11 17:19
 */

/**
 * 无重复字符的最长子串
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
public class Top3 {

    public static void main(String[] args) {
        Top3 top3 = new Top3();
        System.out.println(top3.lengthOfLongestSubstring("tmmzuxt"));
        System.out.println(top3.lengthOfLongestSubstring("abba"));
        //System.out.println(top3.lengthOfLongestSubstring("pwwkew"));
        //System.out.println(top3.lengthOfLongestSubstring("bbbbb"));
    }

    /**
     * 1、利用哈希表记录 字符 和 下标 ，map<Integer,Integer>
     * 2、申请双指针 i，j ，遍历 字符串
     * 3、判断当前字符是否存在，若不存在，直接加入；若存在，则 确定最左侧的位置 Math.max(j, map.get(s.charAt(i)) + 1)
     * 4、计算窗口的值 res = i - j + 1，每次取 Math.max(res,i-j+1))，本次循环结束
     * 5、大循环结束，返回 res
     * @param s
     * @return
     */
    /*public int lengthOfLongestSubstring(String s) {
        Map<Character, Integer> map = new HashMap<>();
        int res = 0;
        for (int i = 0,j = 0; i < s.length(); i++) {
            if (map.containsKey(s.charAt(i))){
                j = Math.max(j, map.get(s.charAt(i)) + 1);
            }
            map.put(s.charAt(i), i);
            res = Math.max(res, i - j + 1);
        }
        return res;
    }*/

    /**
     * 1、数组和hashmap类似，只是数组 int[128] 中下标即代表了字符，数组中的值代表了字符在字符串中的索引；
     *    而hashmap要将char 和 索引 具体放入map中，形成 key - value
     * 2、对于map每次要用containsKey判断 char 是否出现，若出现，则确定左侧的位置 Math.max(j, map.get(s.charAt(i)) + 1)
     *    对于数组，赋初始值全为-1。每次循环，确定start的位置。若字符第一次出现，last[index] + 1 = 0
     *    否则，last[index] + 1 即为 该字符上一次出现的下一个位置，start = Math.max(start,last[index] + 1);
     *    而后赋这一次字符的索引 last[index] = i
     * 执行用时： 1 ms , 在所有 Java 提交中击败了 100.00% 的用户
     * 内存消耗： 41.1 MB , 在所有 Java 提交中击败了 98.00% 的用户
     * @param s
     * @return
     */
    public int lengthOfLongestSubstring(String s) {
        int[] last = new int[128];
        for(int i = 0;i<128;i++){
            last[i] = -1;
        }
        int n = s.length();
        int res = 0;
        int start = 0;
        for(int i = 0;i < n;i++){
            int index = s.charAt(i);
            start = Math.max(start,last[index] + 1); //确定左边界的位置
            res = Math.max(res,i - start + 1);
            last[index] = i; //将该字符的下标写入数组中该字符对应的位置上
        }
        return res;

    }
}
