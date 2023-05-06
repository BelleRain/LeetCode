package CodeTop;

/**
 * @author mxy
 * @create 2023-05-06 14:34
 */

import java.util.*;

/**
 * 227. 基本计算器 II    链接：https://leetcode.cn/problems/basic-calculator-ii
 * 给你一个字符串表达式 s ，请你实现一个基本计算器来计算并返回它的值。
 * 整数除法仅保留整数部分。
 * 你可以假设给定的表达式总是有效的。所有中间结果将在 [-2^31, 2^31 - 1] 的范围内。
 * 注意：不允许使用任何将字符串作为数学表达式计算的内置函数，比如 eval() 。
 *
 * 示例 1：
 * 输入：s = "3+2*2"
 * 输出：7
 *
 * 示例 2：
 * 输入：s = " 3/2 "
 * 输出：1
 *
 * 示例 3：
 * 输入：s = " 3+5 / 2 "
 * 输出：5
 *
 * 提示：
 * 1 <= s.length <= 3 * 10^5
 * s 由整数和算符 ('+', '-', '*', '/') 组成，中间由一些空格隔开
 * s 表示一个 有效表达式
 * 表达式中的所有整数都是非负整数，且在范围 [0, 2^31 - 1] 内
 * 题目数据保证答案是一个 32-bit 整数
 *
 */
public class Code227 {

    public static void main(String[] args) {
        //String s1 = "1*(2+3)";
        //String s2 = "6*((5+(2+3)*8)+3)";
        String s3 = "1-1+1";
        //String s11 = inorderConvertAfter(s1);
        //String s22 = inorderConvertAfter(s2);
        String s33 = inorderConvertAfter(s3);
        //System.out.println(inorderConvertAfter(s1));
        //System.out.println(inorderConvertAfter(s2));
        System.out.println(inorderConvertAfter(s3));
        //System.out.println(calculateTest(s11));
        //System.out.println(calculateTest(s22));
        System.out.println(calculateTest(s33));



        //String s = " 3+5 / 2 ";
        String s = "1-1+1";
        System.out.println(calculate(s));
        System.out.println(calculateAns("3+2*2"));
    }

    /**
     *链接：https://www.cnblogs.com/lanhaicode/p/10776166.html
     * 一、逆波兰表达式 --- 中缀表达式转后缀表达式
     * 二、后缀表达式的计算
     * （表达式符号包括： （、）、+、-、*、/）
     *
     * 一、逆波兰表达式转换思路：
     *  1、申请两个栈 ：operate，nums，
     *  2、遍历中缀表达式
     *     （1）如果 遇到数字 直接入栈 nums；
     *     （2）如果遇到 “(”，直接入operate栈
     *     （3） 如果 遇到操作符，在 operate 空的情况下，直接入栈
     *          如果operate不为空，判断operate栈顶的优先级是否 大于 当前操作符，
     *          如果大于，则 operate出栈，进入nums栈中；直到operate栈顶元素的优先级小于当前元素。
     *          如果小于，则 当前运算符直接入栈
     *     （4）如果遇到 “)”,则operate出栈，直到遇到左括号“(”，（注意：左括号不入数字栈nums）
     *  3、当中缀表达式遍历完毕，将operate中的元素入nums栈即可；没有完成的话，重复上述步骤。
     *
     * 二、后缀表达式的计算
     * 1、遍历后缀表达式，申请 数字栈 stack
     * 2、遇到数字，则直接入栈；
     *    遇到 操作符，则 令栈顶两个元素出栈
     *                  nums1 = stack.poll(); nums2 = stack.poll()
     *    计算 nums1 与 nums2 的结果，并将计算结果入栈。 （注意：-，/，是 nums2 - nums1，nums2/nums1）
     * 3、遍历完毕，最后栈顶元素即为结果
     *
     */


    /**
     *
     * @param s
     * @return
     */
    //1、申请两个栈 ：operate，stack，
    static Deque<String> stack = new LinkedList<>();
    static Deque<Character> operate = new LinkedList<>();
    public static int calculate(String s) {
        if (s.length() == 0) return 0;
        int res = 0;
        //得到后缀表达式的栈 stack，从队头入，从队头出（栈顶）
        inorderConvertAfterImpro(s);
        System.out.println(stack);
        //计算后缀表达式 stack，从队尾出，申请一个新的数字栈，存储计算结果
        Deque<Integer> nums = new LinkedList<>();
        while (!stack.isEmpty()){
            String pop = stack.removeLast();
            if (!pop.equals("+") && !pop.equals("-") && !pop.equals("*") && !pop.equals("/")){
                nums.push(Integer.parseInt(pop));
            }else {
                int num1 = nums.pop();
                int num2 = nums.pop();
                switch (pop){
                    case "+" : res = num2 + num1; break;
                    case "-" : res = num2 - num1; break;
                    case "*" : res = num2 * num1; break;
                    case "/" : res = num2 / num1; break;
                }
                nums.push(res);
            }
        }
        return nums.pop();
    }

    private static void inorderConvertAfterImpro(String s) {
        if (s.length() == 0) return;
        //规定优先级
        Map<Character, Integer> map = new HashMap<Character, Integer>(){{
            put('-', 1);
            put('+', 1);
            put('*', 2);
            put('/', 2);
        }};
        char[] array = s.toCharArray();
        for (int i = 0; i < array.length; i++) {
            int tmp = 0;
            if (array[i] == ' ') continue;
            else if (array[i] >= '0' && array[i] <= '9'){
                //考虑多位数的情况
                while (i < array.length && array[i] >= '0' && array[i] <= '9'){
                    tmp = tmp * 10 + array[i] - '0';
                    i++;
                }
                i--;
                stack.push(String.valueOf(tmp));
            }else {
                while (!operate.isEmpty() && map.get(operate.peek()) >= map.get(array[i])){
                    stack.push(String.valueOf(operate.pop()));
                }
                operate.push(array[i]);
            }
        }
        while (!operate.isEmpty()){
            stack.push(String.valueOf(operate.pop()));
        }
    }


    /**
     *  1*(2+3) ---》 123+*
     *  6*((5+(2+3)*8)+3)  ---- 》 6523+8*+3+*
     * 中缀表达式转后缀表达式
     * （不考虑其他复杂情况，假设只有 0-9 之间的运算，只为验证算法的正确性）
     * @param s
     * @return
     */
    public static String inorderConvertAfter(String s) {
        if (s.length() == 0) return "";
        //1、申请两个栈 ：operate，nums，
        Deque<Character> stack = new LinkedList<>();
        Deque<Character> operate = new LinkedList<>();
        //规定优先级
        Map<Character, Integer> map = new HashMap<Character, Integer>(){{
            put('-', 1);
            put('+', 1);
            put('*', 2);
            put('/', 2);
        }};
        char[] array = s.toCharArray();
        for (int i = 0; i < array.length; i++) {
            //（1）如果 遇到数字 直接入栈 nums；
            if (array[i] >= '0' && array[i] <= '9'){
                stack.push(array[i]);
            }else if (array[i] == '('){
                //（2）如果遇到 “(”，直接入operate栈
                operate.push(array[i]);
            }else if (array[i] == ')'){
                // （4）如果遇到 “)”,则operate出栈，直到遇到左括号“(”，（注意：左括号不入数字栈nums）
                while (operate.peek() != '('){
                    stack.push(operate.pop());
                }
                operate.pop();
            }else {
                //（3）如果 遇到操作符，在 operate 空的情况下，直接入栈
                //    如果operate不为空，判断operate栈顶的优先级是否 大于等于 当前操作符，
                //    如果大于等于，则 operate出栈，进入nums栈中；直到operate栈顶元素的优先级小于当前元素。
                //    如果小于，则 当前运算符直接入栈
                while (!operate.isEmpty() && operate.peek() != '(' && map.get(array[i]) <= map.get(operate.peek())){
                    stack.push(operate.pop());
                }
                operate.push(array[i]);
            }
        }
        // 当中缀表达式遍历完毕，将operate中的元素入nums栈即可；
        while (!operate.isEmpty()){
            stack.push(operate.pop());
        }
        StringBuilder res = new StringBuilder();
        while (!stack.isEmpty()){
            res.append(stack.pop());
        }
        return res.reverse().toString();
    }

    /**
     * 后缀表达式的计算
     * @param s  逆波兰表达式
     * @return
     */
    public static int calculateTest(String s) {
        if (s.length() == 0) return 0;
        char[] array = s.toCharArray();
        //1、遍历后缀表达式，申请 数字栈 stack
        Deque<Integer> stack = new LinkedList<>();
        for (int i = 0; i < array.length; i++) {
            //2、遇到数字，则直接入栈；
            if (array[i] >= '0' && array[i] <= '9'){
                stack.push(array[i] - '0');
            }else {
                //遇到 操作符，则 令栈顶两个元素出栈
                int num1 = stack.pop();
                int num2 = stack.pop();
                int res = 0;
                char op = array[i];
                switch (op){
                    case '+' : res = num2 + num1; break;
                    case '-' : res = num2 - num1; break;
                    case '*' : res = num2 * num1; break;
                    case '/' : res = num2 / num1; break;
                }
                stack.push(res);
            }
        }
        //遍历完毕，最后栈顶元素即为结果
        return stack.pop();
    }


    /**
     * 简单的方法：
     * "3+2*2"  "+3-2*2"
     * 执行用时： 19 ms , 在所有 Java 提交中击败了 75.85% 的用户
     * 内存消耗： 44 MB , 在所有 Java 提交中击败了 31.41% 的用户
     * @param s
     * @return
     */
    public static int calculateAns(String s) {
        Deque<Integer> stack = new ArrayDeque<Integer>();
        char preSign = '+';
        int num = 0;
        int n = s.length();
        for (int i = 0; i < n; ++i) {
            if (Character.isDigit(s.charAt(i))) {
                num = num * 10 + s.charAt(i) - '0';
            }
            // "+3+2*2"  "+3-2*2"
            if (!Character.isDigit(s.charAt(i)) && s.charAt(i) != ' ' || i == n - 1) {
                //preSign: 当前符号 s.charAt(i) 的前一个符号
                switch (preSign) {
                    case '+':
                        stack.push(num);
                        break;
                    case '-':
                        stack.push(-num);
                        break;
                    case '*':
                        stack.push(stack.pop() * num);
                        break;
                    default:
                        stack.push(stack.pop() / num);
                }
                preSign = s.charAt(i);
                num = 0;
            }
        }
        int ans = 0;
        while (!stack.isEmpty()) {
            ans += stack.pop();
        }
        return ans;
    }
}



























