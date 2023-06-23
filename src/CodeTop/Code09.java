package CodeTop;

/**
 * @author mxy
 * @create 2023-06-23 15:16
 */

/**
 * 9. 回文数   链接：https://leetcode.cn/problems/palindrome-number
 * 给你一个整数 x ，如果 x 是一个回文整数，返回 true ；否则，返回 false 。
 * 回文数是指正序（从左向右）和倒序（从右向左）读都是一样的整数。
 *
 * 例如，121 是回文，而 123 不是。
 *
 * 示例 1：
 * 输入：x = 121
 * 输出：true
 *
 * 示例 2：
 * 输入：x = -121
 * 输出：false
 * 解释：从左向右读, 为 -121 。 从右向左读, 为 121- 。因此它不是一个回文数。
 *
 * 示例 3：
 * 输入：x = 10
 * 输出：false
 * 解释：从右向左读, 为 01 。因此它不是一个回文数。
 *
 * 提示：
 * -2^31 <= x <= 2^31 - 1
 *
 * 进阶：你能不将整数转为字符串来解决这个问题吗？
 *
 */
public class Code09 {

    public static void main(String[] args) {
        System.out.println(isPalindrome(121));
        System.out.println(isPalindrome(10));
    }

    /**
     * 题解链接： https://leetcode.cn/problems/palindrome-number/solution/dong-hua-hui-wen-shu-de-san-chong-jie-fa-fa-jie-ch/
     */


    /**
     * 题解一： 将 x 转为字符串
     * 执行用时：6 ms , 在所有 Java 提交中击败了 55.55% 的用户
     * 内存消耗：41.4 MB , 在所有 Java 提交中击败了 44.12% 的用户
     * @param x
     * @return
     */
    public static boolean isPalindrome1(int x) {
        if (x < 0) return false;
        String s = String.valueOf(x);
        int length = s.length();
        int k = 0;
        if ((length & 1) == 1) {
            k = length / 2;
        } else {
            k = length / 2 - 1;
        }
        for (int i = 0; i <= k; i++) {
            if (s.charAt(i) != s.charAt(length - i - 1)) {
                return false;
            }
        }
        return true;
    }


    /**
     * 题解二 ： 数学解法
     *  通过取整和取余操作获取整数中对应的数字进行比较。
     *  举个例子： 1221 这个数字
     *     — 通过计算 1221 / 1000 . 得首位 1
     *     — 通过计算 1221 % 10. 可得末位 1
     *     — 进行比较
     *     — 再将 22 取出来继续比较
     * @param x
     * @return
     */
    public static boolean isPalindrome2(int x) {
        //边界判断
        if (x < 0) return false;
        int div = 1;
        //计算 div 的值，用于求取 最高位的数字
        while (x / div >= 10) {
            div *= 10;
        }
        while (x > 0) {
            int left = x / div;
            int right = x % 10;
            if (left != right) return false;
            // x 去掉首位和末位
            x = (x % div) / 10;
            // 数字减少两位，除数 div 减少两个量级
            div /= 100;
        }
        return true;
    }


    /**
     * 题解三：  取出后半段数字进行翻转
     *   1、注意点： 由于回文数的位数可奇可偶，所以当它的长度是偶数时，它对折过来应该是相等的；
     *         当它的长度是奇数时，那么它对折过来后，有一个的长度需要去掉一位数（除以 10 并取整）。
     *   2、具体做法如下：
     *       — 每次进行取余操作（%10），取出最低的数字： y = x % 10
     *       — 将最低的数字加到取出数的末尾： revertNum = revertNum * 10 + y
     *       — 每取一个最低位数字，x都要自除以10
     *       — 判断 x 是不是小于 revertNum，当它小于的时候，说明数字已经对半或者过半了。
     *       — 最后，判断奇偶数情况：
     *              如果是偶数的话，revertNum 和 x 相等；
     *              如果是奇数的话，最中间的数字就在 revertNum 的最低位上，将它除以 10 以后就该和 x 相等。
     * @param x
     * @return
     */
    public static boolean isPalindrome(int x) {
        // 特殊情况：
        // 如上所述，当 x < 0 时，x 不是回文数。
        // 同样地，如果数字的最后一位是 0 ，为了使该数字为回文，
        // 则其第一位数字也应该是 0
        // 只有 0 满足这一属性
        if (x < 0 || (x % 10 == 0 && x != 0)) {
            return false;
        }
        int revertNum = 0;
        while (x > revertNum) {
            revertNum = revertNum * 10 + x % 10;
            x /= 10;
        }
        //当数字长度为奇数时，我们可以通过 revertNum / 10 去除处于中位的数字。
        //例如，当输入为 12321 时，在 while 循环的末尾我们可以得到 x = 12，revertNum = 123
        //由于处于中位的数字不影响回文（它总是与自己相等），所以我们可以简单地将其去除。
        return x == revertNum || x == revertNum / 10;
    }

}



































