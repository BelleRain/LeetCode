package CodeTop;

/**
 * @author mxy
 * @create 2023-04-12 9:21
 */

import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * 20. 有效的括号    链接：https://leetcode.cn/problems/valid-parentheses
 * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串 s ，判断字符串是否有效。
 * 有效字符串需满足：
 * 左括号必须用相同类型的右括号闭合。
 * 左括号必须以正确的顺序闭合。
 * 每个右括号都有一个对应的相同类型的左括号。
 *  
 * 示例 1：
 * 输入：s = "()"
 * 输出：true
 *
 * 示例 2：
 * 输入：s = "()[]{}"
 * 输出：true
 *
 * 示例 3：
 * 输入：s = "(]"
 * 输出：false
 *  
 * 提示：
 * 1 <= s.length <= 10^4
 * s 仅由括号 '()[]{}' 组成
 *
 */
public class Code20 {

    public static void main(String[] args) {
        System.out.println(isValid1("()[]{}"));
        System.out.println(isValid1("({[]})"));
        System.out.println(isValid2("({[]})"));
    }

    /**
     * 题解一：栈 + 哈希表
     * @param s
     * @return
     */
    public static Map<Character,Character> map = new HashMap<Character, Character>(){{
        put('(', ')');
        put('{', '}');
        put('[', ']');
        put('?', '?');
    }};

    public static boolean isValid1(String s) {
        if (s.isEmpty()) return true;
        if (s.length() > 0 && !map.containsKey(s.charAt(0))) return false;
        Deque<Character> stack = new LinkedList<>();
        stack.push('?');
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (map.containsKey(c)) stack.push(c);
            else{
                if (map.get(stack.pop()) != c) return false;
            }
        }
        return stack.size() == 1;
    }


    /**
     * 题解二：使用栈
     * @param s
     * @return
     */
    public static boolean isValid2(String s) {
        if (s.isEmpty()) return true;
        Deque<Character> stack = new LinkedList<>();
        char[] str = s.toCharArray();
        for (char c : str) {
            if (c == '(')
                stack.push(')');
            else if (c == '{')
                stack.push('}');
            else if (c == '[')
                stack.push(']');
            //注意 || 逻辑
            else if (stack.isEmpty() || stack.pop() != c){
                return false;
            }
        }
        return stack.isEmpty();
    }
}




































