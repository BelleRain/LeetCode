package Swordoffer.BitCal;

/**
 * @author mxy
 * @create 2022-10-17 21:11
 */

/**
 * 二进制中1的个数
 * 编写一个函数，输入是一个无符号整数（以二进制串的形式），返回其二进制表达式中数字位数为 '1' 的个数（也被称为 汉明重量).）。
 * 提示：
 * 请注意，在某些语言（如 Java）中，没有无符号整数类型。在这种情况下，输入和输出都将被指定为有符号整数类型，并且不应影响您的实现，因为无论整数是有符号的还是无符号的，其内部的二进制表示形式都是相同的。
 * 在 Java 中，编译器使用 二进制补码 记法来表示有符号整数。因此，在上面的 示例 3 中，输入表示有符号整数 -3。
 *  
 * 示例 1：
 * 输入：n = 11 (控制台输入 00000000000000000000000000001011)
 * 输出：3
 * 解释：输入的二进制串 00000000000000000000000000001011 中，共有三位为 '1'。
 *
 * 示例 2：
 * 输入：n = 128 (控制台输入 00000000000000000000000010000000)
 * 输出：1
 * 解释：输入的二进制串 00000000000000000000000010000000 中，共有一位为 '1'。
 *
 * 示例 3：
 * 输入：n = 4294967293 (控制台输入 11111111111111111111111111111101，部分语言中 n = -3）
 * 输出：31
 * 解释：输入的二进制串 11111111111111111111111111111101 中，共有 31 位为 '1'。
 */
public class Offer15 {

    public static void main(String[] args) {
        Offer15 offer = new Offer15();
        System.out.println(offer.hammingWeight(00000000000000000000000000001111));
        //String string = Integer.toBinaryString(n);
        //String string = Integer.toHexString(n);
        //int count1 = Integer.bitCount(n);
    }

    /*
    ">>>"无符号右移
    操作规则：无论正负数，前面补零。
    ">>"右移
    操作规则：正数前面补零，负数前面补1
    "<<"左移
    操作规则：无论正负数，后面补零。
     */

    /**
     * 题解链接：https://leetcode.cn/problems/er-jin-zhi-zhong-1de-ge-shu-lcof/solution/mian-shi-ti-15-er-jin-zhi-zhong-1de-ge-shu-wei-yun/
     */

    /**
     * 方法一：逐位判断
     * @param n 无符号数
     * @return
     * 复杂度分析：
     * 时间复杂度 O(log_2 n)： 此算法循环内部仅有 移位、与、加 等基本运算，占用 O(1) ；逐位判断需循环 log_2n次，
     *       其中 log_2 n代表数字 n 最高位 1 的所在位数（例如 log_2(4) = 2, log_2(16) = 4）。
     * 空间复杂度 O(1) ： 变量 res 使用常数大小额外空间。
     */
    // you need to treat n as an unsigned value
    //public int hammingWeight(int n) {
    //    int res = 0;
    //    while (n!=0){
    //        res += n & 1; //如果 n&1 == 1 ，则res+1
    //        n >>>= 1;  //无符号右移
    //    }
    //    return res;
    //}

    /**
     * 方法二：巧用 n&(n-1)
     * @param n
     * @return
     * 复杂度分析：
     * 时间复杂度 O(M) ： n & (n - 1) 操作仅有减法和与运算，占用 O(1) ；设 M 为二进制数字 n 中 1 的个数，则需循环 M 次（每轮消去一个 1 ），占用 O(M) 。
     * 空间复杂度 O(1) ： 变量 res 使用常数大小额外空间。
     * 补充：n&(n-1) 判断 n 是否为 2 的幂 链接：https://leetcode.cn/problems/power-of-two/solution/power-of-two-er-jin-zhi-ji-jian-by-jyd/
     */
    public int hammingWeight(int n) {
        int res = 0;
        while (n!=0){
            res ++;
            //n-1 ：二进制数字n最右边的1变成0，此1右边的 0 都变成1。
            //n&(n-1) ：二进制数字n最右边的1变成0，其余不变。
            n &= n-1;  //消去数字n最右边的1.
        }
        return res;
    }

    /**
     * 题解链接：https://leetcode.cn/problems/er-jin-zhi-zhong-1de-ge-shu-lcof/solution/er-jin-zhi-zhong-1de-ge-shu-by-leetcode-50bb1/
     * 方法三：循环检查二进制位
     */
    //public int hammingWeight(int n) {
    //    int ret = 0;
    //    for (int i = 0; i < 32; i++) {
    //        //int i1 = 1 << i;
    //        //当检查第 i 位时，让 n & 2^i ，
    //        //当且仅当n的第 i 为 1时，运算结果不为 0
    //        if ((n & (1 << i)) != 0) {
    //            ret++;
    //        }
    //    }
    //    return ret;
    //}
}




















