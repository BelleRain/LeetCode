package CodeTop;

/**
 * @author mxy
 * @create 2023-04-25 10:34
 */

import java.util.ArrayList;
import java.util.List;

/**
 * 43. 字符串相乘    链接：https://leetcode.cn/problems/multiply-strings
 * 给定两个以字符串形式表示的非负整数 num1 和 num2，返回 num1 和 num2 的乘积，它们的乘积也表示为字符串形式。
 * 注意：不能使用任何内置的 BigInteger 库或直接将输入转换为整数。
 *
 * 示例 1:
 * 输入: num1 = "2", num2 = "3"
 * 输出: "6"
 *
 * 示例 2:
 * 输入: num1 = "123", num2 = "456"
 * 输出: "56088"
 *  
 *
 * 提示：
 * 1 <= num1.length, num2.length <= 200
 * num1 和 num2 只能由数字组成。
 * num1 和 num2 都不包含任何前导零，除了数字0本身。
 *
 */
public class Code43 {

    public static void main(String[] args) {
        System.out.println(multiply("123", "56"));
    }

    /**
     * 题解一：https://leetcode.cn/problems/multiply-strings/solution/you-hua-ban-shu-shi-da-bai-994-by-breezean/
     */

    /**
     * 普通竖式：
     * @param num1
     * @param num2
     * @return
     */
    public static String multiply1(String num1, String num2) {
        if (num1.equals("0") || num2.equals("0")) return "0";
        String res = "0";
        //num2 与 num1 逐位相乘
        for (int i = num2.length() - 1; i >= 0; i--) {
            int carry = 0;
            //保存num2 与 num1 第i位相乘的结果
            StringBuilder temp = new StringBuilder();
            //补0
            for (int j = 0; j < num2.length() - 1 - i; j++) {
                temp.append(0);
            }
            int n2 = num2.charAt(i) - '0';
            //num2 的第i位数字n2与num1相乘
            for (int j = num1.length() - 1; j >= 0 || carry != 0; j--) {
                int n1 = j < 0 ? 0 : num1.charAt(j) - '0';
                int product = (n1 * n2 + carry) % 10;
                temp.append(product);
                carry = (n1 * n2 + carry)/10;
            }
            //将当前结果与新计算的结果求和作为新的结果
            res = addStrings(res, temp.reverse().toString());
        }
        return res;
    }

    //两个字符串相加
    public static String addStrings(String s1, String s2){
        StringBuilder builder = new StringBuilder();
        int temp = 0,carry = 0;
        int i = s1.length() - 1, j = s2.length() - 1;
        while (i >= 0 || j >= 0){
            int n1 = i >= 0 ? s1.charAt(i) - '0' : 0;
            int n2 = j >= 0 ? s2.charAt(j) - '0' : 0;
            temp = n1 + n2 + (temp/10);
            carry = temp%10;
            builder.append(carry);
            i--;
            j--;
        }
        if (temp/10 != 0) builder.append(temp/10);
        return builder.reverse().toString();
    }


    /**
     * 优化竖式：
     * @param num1
     * @param num2
     * @return
     */
    public static String multiply(String num1, String num2) {
        if (num1.equals("0") || num2.equals("0")){
            return "0";
        }
        int[] res = new int[num1.length() + num2.length()];
        for (int i = num1.length() - 1; i >= 0; i--) {
            int n1 = num1.charAt(i) - '0';
            for (int j = num2.length() - 1; j >= 0; j--) {
                int n2 = num2.charAt(j) - '0';
                int sum = (res[i + j + 1] + n1 * n2);
                res[i + j + 1] = sum%10;
                res[i + j] += sum/10;
            }
        }
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < res.length; i++) {
            if (i == 0 && res[i] == 0)
                continue;
            result.append(res[i]);
        }
        return result.toString();
    }

}


































