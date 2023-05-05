package Swordoffer.Math;

/**
 * @author mxy
 * @create 2022-11-03 10:08
 */

/**
 * 数字序列中某一位的数字
 * 数字以0123456789101112131415…的格式序列化到一个字符序列中。
 * 在这个序列中，第5位（从下标0开始计数）是5，第13位是1，第19位是4，等等。
 * 请写一个函数，求任意第n位对应的数字。
 *
 * 示例 1：
 * 输入：n = 3
 * 输出：3
 *
 * 示例 2：
 * 输入：n = 11
 * 输出：0
 *
 * 限制：
 * 0 <= n < 2^31
 */
public class Offer44 {

    public static void main(String[] args) {
        Offer44 offer = new Offer44();
        System.out.println(offer.findNthDigit(11));
    }

    /**
     * 题解链接：https://leetcode.cn/problems/shu-zi-xu-lie-zhong-mou-yi-wei-de-shu-zi-lcof/solution/mian-shi-ti-44-shu-zi-xu-lie-zhong-mou-yi-wei-de-6/
     * 方法一：迭代 + 求整/求余
     *      1、将 101112⋯ 中的每一位称为 数位 ，记为 n ；
     *      2、将 10, 11, 12,⋯ 称为 数字 ，记为 num ；
     *      3、数字 10 是一个两位数，称此数字的 位数 为 2 ，记为 digit ；
     *      4、每 digit 位数的起始数字（即：1, 10, 100, ⋯），记为 start 。
     *
     * 复杂度分析：
     *      时间复杂度 O(logn) ： 所求数位 n 对应数字 num 的位数 digit 最大为 O(logn) ；
     *          第一步最多循环 O(logn) 次；第三步中将 num 转化为字符串使用 O(logn) 时间；因此总体为 O(logn) 。
     *      空间复杂度 O(logn) ： 将数字 num 转化为字符串 str(num) ，占用 O(logn) 的额外空间。
     * @param n
     * @return
     */
    public int findNthDigit(int n) {
        int digit = 1; //初始位数 1
        long start = 1; //初始值 1
        long count = 9; //count：初始数位数量 9 (仅一位数)
        //数字序列的组成：一位数(1-9)，两位数(10-99)，三位数...
        while (n > count){ //1. 确定所求数位的所在的数字位数
            //循环减去 一位数、两位数、...、(digit - 1)位数 的 数位数量 count，
            //因此此时的起始数字start开始计数
            n -= count;
            //数字位数：digit = digit + 1
            digit += 1;
            //每次减去 x位数，则起始值 *10，即1,10,100,...
            //start = start * 10
            start *= 10;
            //数字位数为digit时，数位数量 count = 9 * digit * start
            //例如：1位数：count = 1*1*9 = 9；2位数：(10-99) count = 2*10*9 = 180; 3位数：(100-999) count = 3*100*9 = 2700,...
            count = digit * start * 9;
            //当 n <= count 时，证明所求数位在当前的count中，跳出循环
            //例如：n=11，n=n-count = 2，count = 180，证明11所在的数字位数为2
        }
        //循环结束，可得，所求数位 ① 在某个digit位数中；② 为从数字start开始的第 n个 数位(数位非数字)

        //2.确定所求数位所在的数字
        //所求数位 在从数字start 开始的第 [(n - 1) / digit] 个数字中，(start为 第 0 个数字，所以此处为 n-1)
        // (n - 1) / digit 为 从start开始的 n 个数位可以构建 几个 digit 位数 ，可以理解为下标[0,...,n-1]
        //例如：10,11，12，... 若此时所求数位为 12中的2，则 从10-12共 6 个数位，由于 start的索引为0，故 2的索引为 5
        // 2/5 = 2,10+2 = 12,即可得所求的数字 num = 12
        long num = start + (n - 1) / digit;

        //3.确定所求数位在num的哪一数位
        // (n - 1) % digit 为 第几位数
        //例如：digit = 2时，(n - 1) % 2 = 0,1;
        return Long.toString(num).charAt((n - 1) % digit) - '0';
    }
}
