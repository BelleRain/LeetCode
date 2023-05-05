package CodeTop;

/**
 * @author mxy
 * @create 2023-04-17 10:41
 */

/**
 * 415. 字符串相加   链接：https://leetcode.cn/problems/add-strings
 * 给定两个字符串形式的非负整数 num1 和num2 ，计算它们的和并同样以字符串形式返回。
 * 你不能使用任何內建的用于处理大整数的库（比如 BigInteger）， 也不能直接将输入的字符串转换为整数形式。
 *
 * 示例 1：
 * 输入：num1 = "11", num2 = "123"
 * 输出："134"
 *
 * 示例 2：
 * 输入：num1 = "456", num2 = "77"
 * 输出："533"
 *
 * 示例 3：
 * 输入：num1 = "0", num2 = "0"
 * 输出："0"
 *
 * 提示：
 * 1 <= num1.length, num2.length <= 10^4
 * num1 和num2 都只包含数字 0-9
 * num1 和num2 都不包含任何前导零
 *
 */
public class Code415 {

    public static void main(String[] args) {
        System.out.println(addStrings("956", "99"));
    }

    /**
     * 题解链接：https://leetcode.cn/problems/add-strings/solution/add-strings-shuang-zhi-zhen-fa-by-jyd/
     */

    /**
     * 注意：
     * int n1 = Integer.valueOf(num1.charAt(i));
     * 这样 返回的是 将 num1.charAt(i) 的ascii码 转为了整数 ，返回值 是 54
     * 将 num1.charAt(i) 对应的数字转为 int ，则应 使用 num1.charAt(i) - ‘0’
     * @param num1
     * @param num2
     * @return
     */
    public static String addStrings(String num1, String num2) {
        StringBuilder res = new StringBuilder();
        int i = num1.length() - 1, j = num2.length() - 1;
        int tmp = 0, carry = 0, r = 0;
        while (i >= 0 || j >= 0){
            int n1 = i >= 0 ? num1.charAt(i) - '0' : 0;
            int n2 = j >= 0 ? num2.charAt(j) - '0' : 0;
            tmp = n1 + n2 + carry;
            carry = tmp / 10;
            r = tmp % 10;
            res.append(r);
            i--;
            j--;
        }
        if (carry == 1) res.append(carry);
        return res.reverse().toString();
    }
}
