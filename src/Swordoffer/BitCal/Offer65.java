package Swordoffer.BitCal;

/**
 * @author mxy
 * @create 2022-10-18 8:25
 */

/**
 * 不用加减乘除做加法
 * 写一个函数，求两个整数之和，要求在函数体内不得使用 “+”、“-”、“*”、“/” 四则运算符号。
 *
 * 示例:
 * 输入: a = 1, b = 1
 * 输出: 2
 */
public class Offer65 {

    public static void main(String[] args) {
        Offer65 offer = new Offer65();
        int a = 2147483647;
        System.out.println(offer.add(a,1));
    }

    /**
     * 题解链接：https://leetcode.cn/problems/bu-yong-jia-jian-cheng-chu-zuo-jia-fa-lcof/solution/mian-shi-ti-65-bu-yong-jia-jian-cheng-chu-zuo-ji-7/
     * n = a⊕b  非进位和：异或运算
     * c = (a&b) << 1  进位：与运算+左移一位
     * @param a
     * @param b
     * @return
     * 复杂度分析：
     * 时间复杂度 O(1) ： 最差情况下（例如 a = 0x7fffffff , b = 1 时），需循环 32 次，使用 O(1) 时间；每轮中的常数次位操作使用 O(1) 时间。
     * 空间复杂度 O(1) ： 使用常数大小的额外空间。
     */
    public int add(int a, int b) {
        while (b!=0){ //当进位为0时跳出
            int c = (a&b) << 1; //c=进位
            a ^= b; //a=非进位和
            b = c;  //b=进位
        }
        return a;
    }
}
