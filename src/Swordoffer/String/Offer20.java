package Swordoffer.String;

/**
 * @author mxy
 * @create 2022-10-25 16:20
 */

/**
 * 表示数值的字符串
 * 请实现一个函数用来判断字符串是否表示数值（包括整数和小数）。
 *
 * 数值（按顺序）可以分成以下几个部分：
 *  1、 若干空格
 *  2、一个 小数 或者 整数
 *  3、（可选）一个 'e' 或 'E' ，后面跟着一个 整数
 *  4、若干空格
 * 小数（按顺序）可以分成以下几个部分：
 *  1、（可选）一个符号字符（'+' 或 '-'）
 *  2、下述格式之一：
 *      1、至少一位数字，后面跟着一个点 '.'
 *      2、至少一位数字，后面跟着一个点 '.' ，后面再跟着至少一位数字
 *      3、一个点 '.' ，后面跟着至少一位数字
 * 整数（按顺序）可以分成以下几个部分：
 *  1、（可选）一个符号字符（'+' 或 '-'）
 *  2、至少一位数字
 * 部分数值列举如下：
 *      ["+100", "5e2", "-123", "3.1416", "-1E-16", "0123"]
 * 部分非数值列举如下：
 *      ["12e", "1a3.14", "1.2.3", "+-5", "12e+5.4"]
 *  
 *
 * 示例 1：
 * 输入：s = "0"
 * 输出：true
 *
 * 示例 2：
 * 输入：s = "e"
 * 输出：false
 *
 * 示例 3：
 * 输入：s = "."
 * 输出：false
 *
 * 示例 4：
 * 输入：s = "    .1  "
 * 输出：true
 *  
 *
 * 提示：
 * 1 <= s.length <= 20
 * s 仅含英文字母（大写和小写），数字（0-9），加号 '+' ，减号 '-' ，空格 ' ' 或者点 '.' 。
 */
public class Offer20 {

    /*
    ①    '0'   代表    字符0  ，对应ASCII码值为   0x30 (也就是十进制 48)

    ②    '\0'  代表     空字符(转义字符)【输出为空】， 对应ASCII码值为   0x00(也就是十进制 0)， 用作字符串结束符

    ③     0    代表     数字0，  若把 数字0 赋值给 某个字符，对应ASCII码值为    0x00(也就是十进制0)

    ④     "0"  代表    一个字符串，  字符串中含有 2个字符，分别是 '0' 和  '\0'
     */
    public static void main(String[] args) {
        String  s = "123";
        System.out.println(s+'\0');
    }

    /**
     * 解法一：遍历
     * @param s
     * @return
     */
    public boolean isNumber(String s) {
        //空格 +、- num . num e\E +\- num 空格
        s = s.trim();
        if (s.length() == 0) return false;
        s += '\0';
        int k = 0;
        boolean f = false;
        if (s.charAt(k) == '+' || s.charAt(k) == '-') k++;
        while (s.charAt(k) >= '0' && s.charAt(k) <= '9') {
            f = true;
            k++;
        }

        if (s.charAt(k) == '.') k++;

        while (s.charAt(k) >= '0' && s.charAt(k) <= '9') {
            f = true;
            k++;
        }
        if (f && (s.charAt(k) == 'e' || s.charAt(k) == 'E')) {
            k++;
            f = false;
            if (s.charAt(k) == '+' || s.charAt(k) == '-') k++;
            while (s.charAt(k) >= '0' && s.charAt(k) <= '9') {
                f = true;
                k++;
            }
        }
        return f && s.charAt(k) == '\0';
    }

    /**
     * 在实际工作中请不要使用try-catch进行流程控制。 try...catch... 效率低，不符合《阿里编程规范》的异常处理（一）
     * 2. 【强制】异常不要用来做流程控制，条件控制，因为异常的处理效率比条件分支低。
     * @param s
     * @return
     */
    //public boolean isNumber(String s) {
    //    if(s == null || s.length() == 0){
    //        return false;
    //    }
    //
    //    //面向测试用例编程，末尾有空格算数字？不解
    //    s = s.trim();
    //    try{
    //        double a = Double.parseDouble(s);
    //    }catch (Exception e){
    //        return false;
    //    }
    //
    //    char c = s.charAt(s.length()-1);
    //    //特，末尾有f，d,D这些不算，但是3.算数字（面向测试用例编程）
    //    return (c >= '0' && c <= '9') || c == '.';
    //}

    /**
     * 解法三：有限状态自动机 详细题解及代码见原文
     * 题解链接：https://leetcode.cn/problems/biao-shi-shu-zhi-de-zi-fu-chuan-lcof/solution/mian-shi-ti-20-biao-shi-shu-zhi-de-zi-fu-chuan-y-2/
     *  https://leetcode.cn/problems/biao-shi-shu-zhi-de-zi-fu-chuan-lcof/solution/biao-shi-shu-zhi-de-zi-fu-chuan-by-leetcode-soluti/
     */
}

































