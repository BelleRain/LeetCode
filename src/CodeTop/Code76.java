package CodeTop;

/**
 * @author mxy
 * @create 2023-04-24 10:23
 */

import org.omg.PortableInterceptor.INACTIVE;

/**
 * 76. 最小覆盖子串   链接：https://leetcode.cn/problems/minimum-window-substring
 * 给你一个字符串 s 、一个字符串 t 。返回 s 中涵盖 t 所有字符的最小子串。如果 s 中不存在涵盖 t 所有字符的子串，
 * 则返回空字符串 "" 。
 *
 * 注意：
 * 对于 t 中重复字符，我们寻找的子字符串中该字符数量必须不少于 t 中该字符数量。
 * 如果 s 中存在这样的子串，我们保证它是唯一的答案。
 *  
 * 示例 1：
 * 输入：s = "ADOBECODEBANC", t = "ABC"
 * 输出："BANC"
 * 解释：最小覆盖子串 "BANC" 包含来自字符串 t 的 'A'、'B' 和 'C'。
 *
 * 示例 2：
 * 输入：s = "a", t = "a"
 * 输出："a"
 * 解释：整个字符串 s 是最小覆盖子串。
 *
 * 示例 3:
 * 输入: s = "a", t = "aa"
 * 输出: ""
 * 解释: t 中两个字符 'a' 均应包含在 s 的子串中，
 * 因此没有符合条件的子字符串，返回空字符串。
 *  
 * 提示：
 * m == s.length
 * n == t.length
 * 1 <= m, n <= 10^5
 * s 和 t 由英文字母组成
 *  
 * 进阶：你能设计一个在 o(m+n) 时间内解决此问题的算法吗？
 *
 */
public class Code76 {

    public static void main(String[] args) {
        System.out.println(minWindow("ADOBECODEBANC", "ABC"));
    }

    /**
     * 题解链接：https://leetcode.cn/problems/minimum-window-substring/solution/tong-su-qie-xiang-xi-de-miao-shu-hua-dong-chuang-k/
     */

    /**
     * 滑动窗口
     * @param s
     * @param t
     * @return
     */
    public static String minWindow(String s, String t) {
        if (s == null || s.length() == 0 || t == null || t.length() == 0){
            return "";
        }
        int[] need = new int[128];
        //记录需要的字符的个数
        for (int i = 0; i < t.length(); i++) {
            need[t.charAt(i)]++;
        }
        //l是当前左边界，r是当前右边界，size记录窗口大小，count是需求的字符个数，start是最小覆盖串开始的index
        int l = 0, r = 0,size = Integer.MAX_VALUE, count = t.length(), start = 0;
        //遍历所有字符
        while (r < s.length()){
            char c = s.charAt(r);
            if (need[c] > 0){ //需要字符c
                count--;
            }
            need[c]--; //把右边的字符加入窗口
            if (count == 0) {  //窗口中已经包含所有字符
                while (l < r && need[s.charAt(l)] < 0){
                    need[s.charAt(l)]++;//释放右边移动出窗口的字符
                    l++; //指针右移
                }
                if (r - l + 1 < size){//不能右移的时候挑战最小窗口大小，更新最小窗口开始的start
                    size = r - l + 1;
                    start = l;  //记录下最小值时候的开始位置，最后返回覆盖率的时候会用到
                }
                //l向右移动窗口肯定不能满足了，重新开始循环
                //即 将属于t的字符 s.chatAt(l) 移出窗口
                need[s.charAt(l)]++;
                l++;
                count++;
            }
            r++;
        }
        return size == Integer.MAX_VALUE ? "" : s.substring(start, start + size);
    }
}




































