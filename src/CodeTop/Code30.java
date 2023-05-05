package CodeTop;

/**
 * @author mxy
 * @create 2023-04-08 15:18
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 30. 串联所有单词的子串  链接：https://leetcode.cn/problems/substring-with-concatenation-of-all-words
 * 给定一个字符串 s 和一个字符串数组 words。 words 中所有字符串 长度相同。
 *  s 中的 串联子串 是指一个包含  words 中所有字符串以任意顺序排列连接起来的子串。
 * 例如，如果 words = ["ab","cd","ef"]，
 * 那么 "abcdef"， "abefcd"，"cdabef"， "cdefab"，"efabcd"， 和 "efcdab" 都是串联子串。 
 * "acdbef" 不是串联子串，因为他不是任何 words 排列的连接。
 * 返回所有串联字串在 s 中的开始索引。你可以以 任意顺序 返回答案。
 *
 * 示例 1：
 * 输入：s = "barfoothefoobarman", words = ["foo","bar"]
 * 输出：[0,9]
 * 解释：因为 words.length == 2 同时 words[i].length == 3，连接的子字符串的长度必须为 6。
 * 子串 "barfoo" 开始位置是 0。它是 words 中以 ["bar","foo"] 顺序排列的连接。
 * 子串 "foobar" 开始位置是 9。它是 words 中以 ["foo","bar"] 顺序排列的连接。
 * 输出顺序无关紧要。返回 [9,0] 也是可以的。
 *
 * 示例 2：
 * 输入：s = "wordgoodgoodgoodbestword", words = ["word","good","best","word"]
 * 输出：[]
 * 解释：因为 words.length == 4 并且 words[i].length == 4，所以串联子串的长度必须为 16。
 * s 中没有子串长度为 16 并且等于 words 的任何顺序排列的连接。
 * 所以我们返回一个空数组。
 *
 * 示例 3：
 * 输入：s = "barfoofoobarthefoobarman", words = ["bar","foo","the"]
 * 输出：[6,9,12]
 * 解释：因为 words.length == 3 并且 words[i].length == 3，所以串联子串的长度必须为 9。
 * 子串 "foobarthe" 开始位置是 6。它是 words 中以 ["foo","bar","the"] 顺序排列的连接。
 * 子串 "barthefoo" 开始位置是 9。它是 words 中以 ["bar","the","foo"] 顺序排列的连接。
 * 子串 "thefoobar" 开始位置是 12。它是 words 中以 ["the","foo","bar"] 顺序排列的连接。
 *  
 *
 * 提示：
 * 1 <= s.length <= 10^4
 * 1 <= words.length <= 5000
 * 1 <= words[i].length <= 30
 * words[i] 和 s 由小写英文字母组成
 */
public class Code30 {

    public static void main(String[] args) {
        String[] words = {"bar","foo","the"};
        List<Integer> list = findSubstring1("barfoofoobarthefoobarman", words);
        //List<Integer> list = findSubstring1("baafoefoebarthefoobarman", words);
        List<Integer> list1 = findSubstring2("baafoefoebarthefoobarman", words);
        System.out.println(list);
        System.out.println(list1);
    }


    /**
     * s = "barfoofoobarthefoobarman", words = ["bar","foo","the"]
     * @param s
     * @param words
     * @return
     */
    public static List<Integer> findSubstring1(String s, String[] words) {
        List<Integer> res = new ArrayList<>();
        if (s == null || s.length() == 0 || words == null || words.length == 0) return res;
        HashMap<String, Integer> map = new HashMap<>();
        int one_word = words[0].length();
        int word_num = words.length;
        int all_len = one_word * word_num;
        for (String word : words) {
            map.put(word, map.getOrDefault(word, 0) + 1);
        }
        for (int i = 0; i < one_word; i++) {
            int left = i,right = i,count = 0;
            HashMap<String, Integer> tmp_map = new HashMap<>();
            while (right + one_word <= s.length()){
                String w = s.substring(right,right + one_word);
                tmp_map.put(w, tmp_map.getOrDefault(w, 0) + 1);
                right += one_word;
                count++;
                while (tmp_map.getOrDefault(w, 0) > map.getOrDefault(w, 0)){
                    String t_w = s.substring(left,left + one_word);
                    count--;
                    tmp_map.put(t_w, tmp_map.getOrDefault(t_w, 0) - 1);
                    left += one_word;
                }
                if (count == word_num) res.add(left);
            }
        }
        return res;
    }


    /**
     * 代码优化
     * @param s
     * @param words
     * @return
     */
    public static List<Integer> findSubstring2(String s, String[] words) {
        List<Integer> res = new ArrayList<>();
        if (s == null || s.length() == 0 || words == null || words.length == 0) return res;
        int one_word = words[0].length();
        int word_num = words.length;
        int all_length = one_word * word_num;
        Map<String, Integer> map = new HashMap<>();
        for (String word : words) {
            map.put(word, map.getOrDefault(word, 0) + 1);
        }
        for (int i = 0; i < one_word; i++) {
            int left = i,right = i,count = 0;
            Map<String, Integer> tmp_map = new HashMap<>();
            while (right + one_word <= s.length()){
                String w = s.substring(right, right + one_word);
                right += one_word;
                if (!map.containsKey(w)){
                    count = 0;
                    left = right;
                    tmp_map.clear();
                }else {
                    tmp_map.put(w, tmp_map.getOrDefault(w, 0) + 1);
                    count++;
                    while (tmp_map.getOrDefault(w, 0) > map.getOrDefault(w, 0)){
                        String t_w = s.substring(left, left + one_word);
                        count--;
                        tmp_map.put(t_w, tmp_map.getOrDefault(t_w, 0) - 1);
                        left += one_word;
                    }
                    if (count == word_num) res.add(left);
                }
            }
        }
        return res;
    }
}










































