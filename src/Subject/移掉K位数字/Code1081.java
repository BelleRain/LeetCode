package Subject.移掉K位数字;

/**
 * @author mxy
 * @create 2023-05-30 22:30
 */

/**
 * 1081. 不同字符的最小子序列 （同316题）  链接：https://leetcode.cn/problems/smallest-subsequence-of-distinct-characters
 * 返回 s 字典序最小的子序列，该子序列包含 s 的所有不同字符，且只包含一次。
 *
 * 示例 1：
 * 输入：s = "bcabc"
 * 输出："abc"
 *
 * 示例 2：
 * 输入：s = "cbacdcbc"
 * 输出："acdb"
 *
 * 提示：
 * 1 <= s.length <= 1000
 * s 由小写英文字母组成
 *
 */
public class Code1081 {

    public static void main(String[] args) {
        String s = "cbacdcbc";
        System.out.println(smallestSubsequence(s));
    }

    public static String smallestSubsequence(String s) {
        boolean[] vis = new boolean[26];
        int[] num = new int[26];
        for (int i = 0; i < s.length(); i++) {
            num[s.charAt(i) - 'a']++;
        }
        //注意：栈中的元素时刻不能重复
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < s.length(); i++) {
            char ch = s.charAt(i);
            //栈内元素不能重复，需要判断当前元素是否在栈内出现过
            if (!vis[ch - 'a']){
                //栈不为空 && 栈顶元素大于当前元素
                while (sb.length() > 0 && sb.charAt(sb.length() - 1) > ch){
                    //后面还有该字母，则可弹出
                    char c = sb.charAt(sb.length() - 1);
                    //从需要弹出栈时，需判断当前元素后面是否还有，如果已经没有，则不能弹栈
                    if (num[c - 'a'] > 0){
                        vis[c - 'a'] = false;
                        //弹出栈顶元素
                        sb.deleteCharAt(sb.length() - 1);
                    }else break;
                }
                sb.append(s.charAt(i));
                vis[s.charAt(i) - 'a'] = true;
            }
            //每次记录后面还剩多少个该字符，减少一次
            num[s.charAt(i) - 'a']--;
        }
        return sb.toString();
    }
}






































