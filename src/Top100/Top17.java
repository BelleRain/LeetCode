package Top100;

/**
 * @author mxy
 * @create 2022-11-16 10:59
 */

import java.util.*;

/**
 * 17. 电话号码的字母组合  链接：https://leetcode.cn/problems/letter-combinations-of-a-phone-number
 * 给定一个仅包含数字 2-9 的字符串，返回所有它能表示的字母组合。答案可以按 任意顺序 返回。
 * 给出数字到字母的映射如下（与电话按键相同）。注意 1 不对应任何字母。
 * 1        2(abc)  3(def)
 * 4(ghi)   5(jkl)  6(mno)
 * 7(pqrs)  8(tuv)  9(wxyz)
 *
 * 示例 1：
 * 输入：digits = "23"
 * 输出：["ad","ae","af","bd","be","bf","cd","ce","cf"]
 *
 * 示例 2：
 * 输入：digits = ""
 * 输出：[]
 *
 * 示例 3：
 * 输入：digits = "2"
 * 输出：["a","b","c"]
 *  
 * 提示：
 * 0 <= digits.length <= 4
 * digits[i] 是范围 ['2', '9'] 的一个数字。
 */
public class Top17 {

    public static void main(String[] args) {
        Top17 top17 = new Top17();
        String digits = "23";
        System.out.println(top17.letterCombinations(digits));
    }

    /**
     * 题解一：回溯法  题解链接：https://leetcode.cn/problems/letter-combinations-of-a-phone-number/solution/dian-hua-hao-ma-de-zi-mu-zu-he-by-leetcode-solutio/
     * @param digits
     * @return
     */
    public List<String> letterCombinations(String digits) {
        List<String> combinations = new ArrayList<>();
        if (digits.length() == 0) return combinations;
        Map<Character, String> phoneMap = new HashMap<Character, String>() {{
            put('2', "abc");
            put('3', "def");
            put('4', "ghi");
            put('5', "jkl");
            put('6', "mno");
            put('7', "qprs");
            put('8', "tuv");
            put('9', "wxyz");
        }};
        backtrack(combinations, phoneMap, digits, 0, new StringBuffer());
        return combinations;
    }

    /**
     * 回溯法组合字符串
     * @param combinations   最终的结果列表
     * @param phoneMap       数字和字符串的对应关系
     * @param digits         所求的数字字符串
     * @param index          当前数字的索引
     * @param combination    临时存储每次组合的结果
     */
    public void backtrack(List<String> combinations, Map<Character,String> phoneMap,String digits,int index,StringBuffer combination){
        //当 index == 数字字符串的长度时，表示已遍历完毕，将该次组合的结果加入结果集中
        if (index == digits.length()) combinations.add(combination.toString());
        else {
            char digit = digits.charAt(index);
            String letters = phoneMap.get(digit); //取出该数字对应的字符串
            int lettersCount = letters.length();
            for (int i = 0; i < lettersCount; i++) {
                combination.append(letters.charAt(i));
                backtrack(combinations, phoneMap,digits ,index+1 ,combination); //组合下一个字符
                combination.deleteCharAt(index); //每次移出该位置上新加入的字符，为下一次组合准备
            }
        }
    }
}
