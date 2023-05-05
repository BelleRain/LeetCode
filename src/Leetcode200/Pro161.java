package Leetcode200;

/**
 * @author mxy
 * @create 2022-11-10 9:00
 */

/**
 * 相隔为 1 的编辑距离
 * 给定两个字符串 s 和 t ，如果它们的编辑距离为 1 ，则返回 true ，否则返回 false 。
 * 字符串 s 和字符串 t 之间满足编辑距离等于 1 有三种可能的情形：
 *
 * 往 s 中插入 恰好一个 字符得到 t
 * 从 s 中删除 恰好一个 字符得到 t
 * 在 s 中用 一个不同的字符 替换 恰好一个 字符得到 t
 *  
 * 示例 1：
 * 输入: s = "ab", t = "acb"
 * 输出: true
 * 解释: 可以将 'c' 插入字符串 s 来得到 t。
 *
 * 示例 2:
 * 输入: s = "cab", t = "ad"
 * 输出: false
 * 解释: 无法通过 1 步操作使 s 变为 t。
 *  
 * 提示:
 * 0 <= s.length, t.length <= 10^4
 * s 和 t 由小写字母，大写字母和数字组成
 */
public class Pro161 {

    public static void main(String[] args) {
        Pro161 pro161 = new Pro161();
        //System.out.println(pro161.isOneEditDistance("acbbcda", "abbdad"));
        System.out.println(pro161.isOneEditDistance("ab", "acd"));
    }

    /**
     * 解题思路：分情况讨论
     * 双指针遍历比较
     * @param s
     * @param t
     * @return
     */
    public boolean isOneEditDistance(String s, String t) {
        int len1 = s.length();
        int len2= t.length();
        if(len1 == len2) // only replace
        {
            int cnt = 0;
            for(int i =0 ; i < len1;i++)
            {
                if(s.charAt(i) == t.charAt(i)) continue;
                else
                {
                    cnt++;
                }
            }

            return cnt ==1;
        }
        else if(Math.abs(len1 -len2) == 1)  // insert or delete one charc
        {
            if(len1 > len2) return isOneEditDistance(t,s);

            // make sure len1 < len2
            int cnt = 0;
            int left = 0;
            int right = 0;
            while(left < len1 && right < len2)
            {
                if(s.charAt(left) == t.charAt(right))
                {
                    left++;
                    right++;
                }
                else
                {
                    right++;
                    cnt++;
                }
            }

            return cnt ==1 || ( cnt == 0 && right == len2 -1);
        }

        else return false;
    }

  /*  public boolean isOneEditDistance(String s, String t) {
        int n = s.length(), m = t.length();
        if (Math.abs(n - m) > 1) return false;
        if (n > m) return isOneEditDistance(t, s);

        int i = 0, j = n - 1, k = m -1;
        while (i <= j) {
            if (s.charAt(i) != t.charAt(i) && s.charAt(j) != t.charAt(k))
                break;
            if (s.charAt(i) == t.charAt(i)) i++;
            if (s.charAt(j) == t.charAt(k)) { j--; k--; }
        }

        return n == m ? i == j : i > j;
    }*/


    /*public boolean isOneEditDistance(String s, String t) {
        int m = s.length() , n = t.length() , det = m - n;
        if(Math.abs(det) > 1) return false;
        int i = 0 , j = 0 , cnt = 0;
        while (i < m && j < n){
            if (det == 0){
                if (s.charAt(i) != t.charAt(j)) cnt++;
                i++;
                j++;
            }
            if (det == 1 || det == -1){
                if (s.charAt(i) != t.charAt(j)){
                    if (m < n){
                        j++;
                        cnt++;
                    }else {
                        i++;
                        cnt++;
                    }
                }else {
                    i++;
                    j++;
                }
            }
        }
        return cnt == 1 || (cnt == 0 && (j == n - 1 || i == m - 1));
    }*/
}
