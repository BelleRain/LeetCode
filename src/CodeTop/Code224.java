package CodeTop;

/**
 * @author mxy
 * @create 2023-06-12 8:54
 */

import java.util.*;

/**
 * 224. 基本计算器    链接：https://leetcode.cn/problems/basic-calculator
 * 给你一个字符串表达式 s ，请你实现一个基本计算器来计算并返回它的值。
 * 注意:不允许使用任何将字符串作为数学表达式计算的内置函数，比如 eval() 。
 *
 * 示例 1：
 * 输入：s = "1 + 1"
 * 输出：2
 *
 * 示例 2：
 * 输入：s = " 2-1 + 2 "
 * 输出：3
 *
 * 示例 3：
 * 输入：s = "(1+(4+5+2)-3)+(6+8)"
 * 输出：23
 *  
 *
 * 提示：
 * 1 <= s.length <= 3 * 10^5
 * s 由数字、'+'、'-'、'('、')'、和 ' ' 组成
 * s 表示一个有效的表达式
 * '+' 不能用作一元运算(例如， "+1" 和 "+(2 + 3)" 无效)
 * '-' 可以用作一元运算(即 "-1" 和 "-(2 + 3)" 是有效的)
 * 输入中不存在两个连续的操作符
 * 每个数字和运行的计算将适合于一个有符号的 32位 整数
 *
 */
public class Code224 {

    public static void main(String[] args) {
        String s = "(1+(4+5+2)-3)+(6+8)";
        System.out.println(inorderConvertToAfter(s));
        System.out.println(calculate(s));
        System.out.println(calculate("2-1 + 2"));
        System.out.println(calculate1("2-1 + 2"));

        Deque<Character> stack = new LinkedList<>();
        for (int i = 0; i < s.length(); i++) {
            //从队头入
            stack.push(s.charAt(i));
        }
        while (!stack.isEmpty()){
            //从队头出
            stack.pop();
        }

        for (int i = 0; i < s.length(); i++) {
            //从队尾入
            stack.offerLast(s.charAt(i));
        }
        while (!stack.isEmpty()){
            //从队尾出
            stack.pollLast();
        }
    }


    /**
     * 方法一：
     *      转为逆波兰表达式计算
     * @param s
     * @return
     */
    public static int calculate(String s) {
        Deque<Integer> stack = new LinkedList<>();
        Deque<String> after = inorderConvertToAfter(s);
        System.out.println(after);
        int res = 0;
        while (!after.isEmpty()){
            String s1 = after.pollFirst();
            if (!s1.equals("+") && !s1.equals("-") && !s1.equals("*") && !s1.equals("/")){
                stack.offerLast(Integer.parseInt(s1));
            }else {
                int num1 = stack.pollLast();
                int num2 = stack.pollLast();
                String sign = s1;
                switch (sign){
                    case "+" : res = num2 + num1; break;
                    case "-" : res = num2 - num1; break;
                    case "*" : res = num2 * num1; break;
                    case "/" : res = num2 / num1; break;
                }
                stack.offerLast(res);
            }
        }
        return stack.pollLast();
    }

    /**
     * 中缀表达式转后缀表达式
     * @return
     */
    public static Deque<String> inorderConvertToAfter(String s){
        //1、申请两个栈,数字栈nums，操作数栈operate
        Deque<String> nums = new LinkedList<>();
        Deque<Character> operate = new LinkedList<>();
        //2、规定优先级
        Map<Character, Integer> map = new HashMap<Character, Integer>(){{
            put('+', 1);
            put('-', 1);
            put('*', 2);
            put('/', 2);
        }};
        char[] array = s.toCharArray();
        for (int i = 0; i < array.length; i++) {
            int num = 0;
            if (Character.isDigit(array[i])){
                while (i < array.length && Character.isDigit(array[i])){
                    num = num * 10 + (array[i] - '0');
                    i++;
                }
                i--;
                nums.offerLast(String.valueOf(num));
            }else if (!Character.isDigit(array[i]) && array[i] != ' '){
                if (array[i] == '('){
                    operate.offerLast(array[i]);
                }else if (array[i] == ')'){
                    while (operate.peekLast() != '('){
                        Character last = operate.pollLast();
                        nums.offerLast(String.valueOf(last));
                    }
                    operate.pollLast();
                }else {
                    //栈不为空，且栈顶元素的优先级大于当前元素的优先级
                    while (!operate.isEmpty() && operate.peekLast() != '(' && map.get(operate.peekLast()) >= map.get(array[i])){
                        Character c = operate.pollLast();
                        nums.offerLast(String.valueOf(c));
                    }
                    operate.offerLast(array[i]);
                }
            }
        }
        while (!operate.isEmpty()) {
            Character c = operate.pollLast();
            nums.offerLast(String.valueOf(c));
        }
        return nums;
    }


    /**
     * 双栈解法：
     *    1、使用两个栈： nums 和 ops
     *         nums：存放所有的数字
     *         ops：存放所有的数字以外的操作，+/-也看做是一种操作
     *    2、然后从前往后做，对遍历的到的字符做分情况讨论：
     *        空格：跳过
     *        （ ： 直接加入 ops 中，等待与之匹配的 ）
     *         ）： 使用现有的 nums 和 ops 进行计算，直到遇到左边最近的一个左括号为止，计算结果放到 nums
     *       数字： 从当前位置开始继续往后取，将整一个连续数字整体取出，加入 nums。
     *       +/- ：需要将操作放入 ops 中。在放入之前先把栈内可以算的都算掉，使用现有的 nums 和 ops 进行计算，直到没有操作或者遇到左括号，计算结果放到 nums
     *    3、一些细节：
     *        由于第一个数可能是负数，为了减少边界判断。一个小技巧是先往 nums 添加 一个 0
     *        为防止（）内出现的首个字符为运算符，将所有的空格去掉，并将 （- 替换为 （0-，
     *        （+ 替换为 （0+ （当然也可以不进行这样的预处理，将这个处理逻辑放到循环里去做）
     *
     * @param s
     * @return
     */
    public static int calculate1(String s) {
        //存放所有的数字
        Deque<Integer> nums = new ArrayDeque<>();
        //为了防止第一个数字为负数，先往nums加个0
        nums.addLast(0);
        //将所有的空格去掉
        s = s.replaceAll(" ", "");
        //存放所有的操作，包括 +/-
        Deque<Character> ops = new ArrayDeque<>();
        int n = s.length();
        char[] cs = s.toCharArray();
        for (int i = 0; i < n; i++) {
            char c = cs[i];
            if (c == '('){
                ops.addLast(c);
            }else if (c == ')'){
                //计算到最近一个左括号位置
                while (!ops.isEmpty()){
                    char op = ops.peekLast();
                    if (op != '('){
                        calc(nums,ops);
                    }else {
                        ops.pollLast();
                        break;
                    }
                }
            }else {
                if (isNum(c)){
                    int u = 0;
                    int j = i;
                    //将从 i 位置开始后面的连续数字整体取出，加入nums
                    while (j < n && isNum(cs[j])){
                        u = u * 10 + (int)(cs[j++] - '0');
                    }
                    nums.addLast(u);
                    i = j - 1;
                }else {
                    if (i > 0 && (cs[i - 1] == '(' || cs[i - 1] == '+' || cs[i - 1] == '-')) {
                        nums.addLast(0);
                    }
                    //有一个新操作要入栈时，先把栈内可以算的都算了
                    while (!ops.isEmpty() && ops.peekLast() != '(') {
                        calc(nums, ops);
                    }
                    ops.addLast(c);
                }
            }
        }
        while (!ops.isEmpty()) {
            calc(nums, ops);
        }
        return nums.peekLast();
    }

    private static void calc(Deque<Integer> nums, Deque<Character> ops) {
        if (nums.isEmpty() || nums.size() < 2) return;
        if (ops.isEmpty()) return;
        int b = nums.pollLast();
        int a = nums.pollLast();
        char op = ops.pollLast();
        nums.addLast(op == '+' ? a + b : a - b);
    }

    public static boolean isNum(char c){
        return Character.isDigit(c);
    }
}
































