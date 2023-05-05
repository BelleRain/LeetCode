package Top100;

/**
 * @author mxy
 * @create 2022-11-17 9:51
 */

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Stack;

/**
 * 20. 有效的括号  链接：https://leetcode.cn/problems/valid-parentheses
 * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串 s ，判断字符串是否有效。
 * 有效字符串需满足：
 *      左括号必须用相同类型的右括号闭合。
 *      左括号必须以正确的顺序闭合。
 *      每个右括号都有一个对应的相同类型的左括号。
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
public class Top20 {

    public static void main(String[] args) {
        Top20 top20 = new Top20();
        System.out.println(top20.isValid("()[]{}"));
        System.out.println(top20.isValid("([{})]")); //false
        System.out.println(top20.isValid("([{}])")); //true
        System.out.println(top20.isValid("?"));
    }

    /**
     * 题解一：栈 + 哈希表  题解链接：https://leetcode.cn/problems/valid-parentheses/solution/valid-parentheses-fu-zhu-zhan-fa-by-jin407891080/
     * 复杂度分析：
     *      时间复杂度 O(N)：正确的括号组合需要遍历 1 遍 s；
     *      空间复杂度 O(N)：哈希表和栈使用线性的空间大小。
     * @param s
     * @return
     */
    private static final Map<Character,Character> map = new HashMap<Character, Character>(){{
        put('{', '}');
        put('(', ')');
        put('[', ']');
        put('?','?');
    }};
    public boolean isValid1(String s) {
        if (s.length() > 0 && !map.containsKey(s.charAt(0))) return false;
        LinkedList<Character> stack = new LinkedList<Character>(){{ add('?'); }};
        for (char c : s.toCharArray()) {
            if (map.containsKey(c)) stack.addLast(c);
            else if (map.get(stack.removeLast()) != c) return false; //提前发现不符合的字符并返回
        }
        return stack.size() == 1; //最后仅 "?" 留在栈中
    }


    /**
     * 不用哈希表
     * @param s
     * @return
     */
    public boolean isValid(String s) {
        if(s.isEmpty())
            return true;
        Stack<Character> stack=new Stack<Character>();
        for(char c:s.toCharArray()){
            if(c=='(')
                stack.push(')');
            else if(c=='{')
                stack.push('}');
            else if(c=='[')
                stack.push(']');
            //注意 || 逻辑
            else if(stack.empty()||c!=stack.pop())
                return false;
        }
        return stack.empty();
    }
}
