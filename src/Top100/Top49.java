package Top100;

/**
 * @author mxy
 * @create 2022-11-21 13:27
 */

import java.util.*;
import java.util.stream.Collectors;

/**
 * 49. 字母异位词分组   链接：https://leetcode.cn/problems/group-anagrams
 * 给你一个字符串数组，请你将 字母异位词 组合在一起。可以按任意顺序返回结果列表。
 * 字母异位词 是由重新排列源单词的字母得到的一个新单词，所有源单词中的字母通常恰好只用一次。

 * 示例 1:
 * 输入: strs = ["eat", "tea", "tan", "ate", "nat", "bat"]
 * 输出: [["bat"],["nat","tan"],["ate","eat","tea"]]
 *
 * 示例 2:
 * 输入: strs = [""]
 * 输出: [[""]]
 *
 * 示例 3:
 * 输入: strs = ["a"]
 * 输出: [["a"]]
 *
 * 提示：
 * 1 <= strs.length <= 10^4
 * 0 <= strs[i].length <= 100
 * strs[i] 仅包含小写字母
 */
public class Top49 {

    public static void main(String[] args) {
        Top49 top49 = new Top49();
        String[] strs = {"eat", "tea", "tan", "ate", "nat", "bat"};
        //String[] strs = {""};
        System.out.println(top49.groupAnagrams(strs));
    }

    /**
     * 题解链接：https://leetcode.cn/problems/group-anagrams/solution/zi-mu-yi-wei-ci-fen-zu-by-leetcode-solut-gyoc/
     */

    /**
     * 方法一：排序
     * 1、模式识别：一旦需要根据特征进行归类，就应该利用散列表。
     * 2、由于互为 字母异位词 的两个字符串包含的字母相同，因此对两个字符串分别进行排序之后得到的字符串一定是相同的，
     *   故可以 将排序之后的字符串 作为 哈希表的键。
     * 3、在内容相同情况下，String会hash得到相同的key，由于char[]特殊机制，相同内容的在hash后值不会相同。 因此Map中必须使用String作为key
     * @param strs
     * @return
     */
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();
        for (String str : strs) {
            char[] array = str.toCharArray();
            Arrays.sort(array);
            String key = new String(array);
            List<String> list = map.getOrDefault(key, new ArrayList<>());
            list.add(str);
            map.put(key, list);
        }
        return new ArrayList<>(map.values());
    }

    /**
     * 方法二：计数
     * 由于互为字母异位词的两个字符串包含的字母相同，因此两个字符串中的相同字母出现的次数一定是相同的，故可以将每个字母出现的次数使用字符串表示，作为哈希表的键。
     * 由于字符串只包含小写字母，因此对于每个字符串，可以使用长度为 26 的数组记录每个字母出现的次数。
     * 需要注意的是，在使用数组作为哈希表的键时，不同语言的支持程度不同，因此不同语言的实现方式也不同。
     * @param strs
     * @return
     */
    /*public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap<>();
        for (String str : strs) {
            char[] counts = new char[26];
            for (int i = 0; i < str.length(); i++) {
                counts[str.charAt(i) - 'a']++;
            }
            StringBuilder s = new StringBuilder();
            for (int i = 0; i < 26; i++) {
                if (counts[i] != 0){
                    s.append((char)('a' + i));
                    s.append(counts[i]);
                }
            }
            String key = new String(s); //得到对应的key
            List<String> list = map.getOrDefault(key, new ArrayList<>()); //得到对应的list列表
            list.add(str); //将 str 加入对应的 list 中
            map.put(key,list); //再将 list 放入map 中，与 key 相对应
        }
        return new ArrayList<>(map.values());
    }*/


    /**
     * 执行用时： 4 ms , 在所有 Java 提交中击败了 99.92% 的用户
     * 内存消耗： 44.2 MB , 在所有 Java 提交中击败了 83.61% 的用户
     * @param strs
     * @return
     */
    /*public static List<List<String>> groupAnagrams(String[] strs){
        HashMap<Integer, ArrayList<String>> map = new HashMap<>();
        for(String each : strs){
            int len = each.length();
            int val = 0;
            int val1 = 0;
            for(int i = 0; i < len; i++){
                val = val + (1 << each.charAt(i) - 'a');
                val1 = val1 + each.charAt(i);
            }
            val = val * val1;
            ArrayList<String> p;
            if((p = map.get(val)) != null){
                p.add(each);
            }
            else{
                map.put(val,new ArrayList<String>(){{
                    add(each);
                }});
            }
        }
        List res = new ArrayList();
        for(ArrayList<String> p : map.values()){
            res.add(p);
        }
        return res;
    }*/


    /**
     * 链接：https://leetcode.cn/problems/group-anagrams/solution/kan-wo-yi-ju-hua-ac-zi-mu-yi-wei-ci-fen-yrnis/
     * 方法一简略写法：
     * @param strs
     * @return
     */
    /*public List<List<String>> groupAnagrams(String[] strs) {
        // str -> intstream -> sort -> collect by StringBuilder
        return new ArrayList<>(Arrays.stream(strs)
                .collect(Collectors
                        .groupingBy(str -> str.chars()
                                .sorted()
                                .collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
                                .toString())).values());
    }*/

}
