package CodeTop;

/**
 * @author mxy
 * @create 2023-06-23 10:09
 */

/**
 * 7. 整数反转      链接：https://leetcode.cn/problems/reverse-integer
 * 给你一个 32 位的有符号整数 x ，返回将 x 中的数字部分反转后的结果。
 * 如果反转后整数超过 32 位的有符号整数的范围 [−2^31,  2^31 − 1] ，就返回 0。
 * 假设环境不允许存储 64 位整数（有符号或无符号）。
 *
 *
 * 示例 1：
 * 输入：x = 123
 * 输出：321
 *
 * 示例 2：
 * 输入：x = -123
 * 输出：-321
 *
 * 示例 3：
 * 输入：x = 120
 * 输出：21
 *
 * 示例 4：
 * 输入：x = 0
 * 输出：0
 *  
 *
 * 提示：
 * -2^31 <= x <= 2^31 - 1
 *
 */
public class Code07 {

    public static void main(String[] args) {
        System.out.println(reverse(901000));
        System.out.println(reverse(1534236469));
        System.out.println(Integer.MAX_VALUE);
        System.out.println(Integer.MIN_VALUE);
    }


    /**
     * 题解链接：https://leetcode.cn/problems/reverse-integer/solution/tu-jie-7-zheng-shu-fan-zhuan-by-wang_ni_ma/
     *     Integer.MAX_VALUE == 2147483647
     *     Integer.MIN_VALUE == -2147483648
     *
     * 一、 对正数来说：
     *   2 1 4 7 4 8 3 6 4 7
     *   2 1 4 7 4 8 3 6 5 0
     *   2 1 4 7 4 8 3 6 4 6
     *   2 1 4 7 4 8 3 6 4 7
     *   2 1 4 7 4 8 3 6 4 8
     *   第二排数字中，倒数第二个数为 5 ，大于第一排同位置的 4 ，这就意味着 5 后跟任何数字，都会比最大32为整数都大。
     *   所以，我们到 【最大数的 1/10】时，就要开始判断了。
     *   如果某个数字 【大于】 214748364 那后面就不用再判断了，肯定溢出了。
     *   如果某个数字 【等于】 214748364 这对应上述 第三、四、五排数字，需要跟最大数的【末尾数字】比较，如果这个数字比 7 还大，说明溢出了。
     *
     * 二、 对负数来说：
     *    - 2 1 4 7 4 8 3 6 4 8
     *    - 2 1 4 7 4 8 3 6 5 0
     *    - 2 1 4 7 4 8 3 6 4 7
     *    - 2 1 4 7 4 8 3 6 4 8
     *    - 2 1 4 7 4 8 3 6 4 9
     *    第一排数字是最小的32位整数，同样是在 【最小数的 1/10】时开始判断
     *    如果某个数字 【小于】 -214748364 说明溢出了
     *    如果某个数字 【等于】 -214748364 ，还需要跟最小数的末尾数比较，即它是否小于 8
     *
     * @param x
     * @return
     */
    public static int reverse(int x) {
        int res = 0;
        while (x != 0) {
            //每次取末尾数字
            int tmp = x % 10;
            //判断是否 大于 最大32位整数
            //正数溢出
            if (res > 214748364 || (res == 214748364 && tmp > 7)) {
                return 0;
            }
            //判断是否 小于 最小32位整数
            //负数溢出
            if (res < -214748364 || (res == -214748364 && tmp > 8)) {
                return 0;
            }
            res = res * 10 + tmp;
            x /= 10;
        }
        return res;
    }
}










































