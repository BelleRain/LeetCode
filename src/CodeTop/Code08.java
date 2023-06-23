package CodeTop;

/**
 * @author mxy
 * @create 2023-04-04 14:27
 */

/**
 * 8. 字符串转换整数 (atoi)    链接：https://leetcode.cn/problems/string-to-integer-atoi
 * 请你来实现一个 myAtoi(string s) 函数，使其能将字符串转换成一个 32 位有符号整数（类似 C/C++ 中的 atoi 函数）。
 * 函数 myAtoi(string s) 的算法如下：
 *
 * 读入字符串并丢弃无用的前导空格
 * 检查下一个字符（假设还未到字符末尾）为正还是负号，读取该字符（如果有）。 确定最终结果是负数还是正数。
 * 如果两者都不存在，则假定结果为正。
 * 读入下一个字符，直到到达下一个非数字字符或到达输入的结尾。字符串的其余部分将被忽略。
 * 将前面步骤读入的这些数字转换为整数（即，"123" -> 123， "0032" -> 32）。
 * 如果没有读入数字，则整数为 0 。必要时更改符号（从步骤 2 开始）。
 * 如果整数数超过 32 位有符号整数范围 [−2^31,  2^31 − 1] ，需要截断这个整数，使其保持在这个范围内。
 * 具体来说，小于 −2^31 的整数应该被固定为 −2^31 ，大于 2^31 − 1 的整数应该被固定为 2^31 − 1 。
 * 返回整数作为最终结果。
 * 注意：
 * 本题中的空白字符只包括空格字符 ' ' 。
 * 除前导空格或数字后的其余字符串外，请勿忽略任何其他字符。
 *  
 *
 * 示例 1：
 * 输入：s = "42"
 * 输出：42
 * 解释：加粗的字符串为已经读入的字符，插入符号是当前读取的字符。
 * 第 1 步："42"（当前没有读入字符，因为没有前导空格）
 * 第 2 步："42"（当前没有读入字符，因为这里不存在 '-' 或者 '+'）
 * 第 3 步："42"（读入 "42"）
 *            ^
 * 解析得到整数 42 。
 * 由于 "42" 在范围 [-2^31, 2^31 - 1] 内，最终结果为 42 。
 *
 * 示例 2：
 * 输入：s = "   -42"
 * 输出：-42
 * 解释：
 * 第 1 步："   -42"（读入前导空格，但忽视掉）
 * 第 2 步："   -42"（读入 '-' 字符，所以结果应该是负数）
 * 第 3 步："   -42"（读入 "42"）
 *                ^
 * 解析得到整数 -42 。
 * 由于 "-42" 在范围 [-2^31, 2^31 - 1] 内，最终结果为 -42 。
 *
 * 示例 3：
 * 输入：s = "4193 with words"
 * 输出：4193
 * 解释：
 * 第 1 步："4193 with words"（当前没有读入字符，因为没有前导空格）
 *          ^
 * 第 2 步："4193 with words"（当前没有读入字符，因为这里不存在 '-' 或者 '+'）
 *          ^
 * 第 3 步："4193 with words"（读入 "4193"；由于下一个字符不是一个数字，所以读入停止）
 *              ^
 * 解析得到整数 4193 。
 * 由于 "4193" 在范围 [-2^31, 2^31 - 1] 内，最终结果为 4193 。
 *  
 *
 * 提示：
 * 0 <= s.length <= 200
 * s 由英文字母（大写和小写）、数字（0-9）、' '、'+'、'-' 和 '.' 组成
 */
public class Code08 {

    public static void main(String[] args) {
        Code08 c = new Code08();
        System.out.println(c.myAtoi("42"));
        System.out.println(c.myAtoi("-2147483648"));
        System.out.println(-1*Integer.MIN_VALUE); //-2147483648
    }

    /**
     * 题解链接：https://leetcode.cn/problems/string-to-integer-atoi/solution/jin-liang-bu-shi-yong-ku-han-shu-nai-xin-diao-shi-/
     */

    /**
     * 第一步：跳过前置空格
     * 第二步：判断正负号 ‘+’，‘-’，整数 sign = 1 ，负数 sign = -1
     * 第三步：判断是否为数字，即 ‘0’ <= c <= '9',若是继续向下遍历，否则停止遍历
     * 第四步：逐步计算数值，并判断是否溢出，若溢出，则按题目要求返回
     *
     * Integer.MIN_VALUE - 1 = Integer.MAX_VALUE
     * Integer.MAX_VALUE + 1 = Integer.MIN_VALUE
     * -Integer.MIN_VALUE = Integer.MIN_VALUE
     * -Integer.MAX_VALUE = Integer.MIN_VALUE + 1
     * @param s
     * @return
     */
    public int myAtoi(String s) {
        int sign = 1,ans = 0,index = 0;
        int len = s.length();
        char[] array = s.toCharArray();
        //第一步：跳过前置空格
        while (index < len && array[index] == ' ') {
            index++;
        }
        //第二步：判断正负号 ‘+’，‘-’，整数sign = 1 ，负数 sign = -1
        if (index < len && (array[index] == '+' || array[index] == '-')) {
            sign = array[index] == '+' ? 1 : -1;
            index++;
        }
        //第三步：判断是否为数字，即 '0' <= c <= '9',若是继续向下遍历，否则停止遍历
        while (index < len && (array[index] >= '0' && array[index] <= '9')) {
            int dight = array[index] - '0';
            //第四步：逐步计算数值，并判断是否溢出，若溢出，则按题目要求返回
            //判断溢出：
            //ans * 10 + dight > Integer.MAX_VALUE  -----> ans > (Integer.MAX_VALUE - dight) / 10
            if (ans > (Integer.MAX_VALUE - dight) / 10) {
                ans = sign == 1 ? Integer.MAX_VALUE : Integer.MIN_VALUE;
                break;
            }
            ans = ans * 10 + dight;
            index++;
        }
        //-Integer.MIN_VALUE = Integer.MIN_VALUE
        return ans * sign;
    }
}
