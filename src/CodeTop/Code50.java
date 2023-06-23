package CodeTop;

/**
 * @author mxy
 * @create 2023-06-18 10:42
 */

/**
 * 50. Pow(x, n)  同Offer16  链接：https://leetcode.cn/problems/powx-n
 * 实现 pow(x, n) ，即计算 x 的整数 n 次幂函数（即，x^n ）。
 *
 * 示例 1：
 * 输入：x = 2.00000, n = 10
 * 输出：1024.00000
 *
 * 示例 2：
 * 输入：x = 2.10000, n = 3
 * 输出：9.26100
 *
 * 示例 3：
 * 输入：x = 2.00000, n = -2
 * 输出：0.25000
 * 解释：2^(-2) = 1/2^2 = 1/4 = 0.25
 *  
 * 提示：
 * -100.0 < x < 100.0
 * -2^31 <= n <= 2^31-1
 * n 是一个整数
 * 要么 x 不为零，要么 n > 0 。
 * -10^4 <= x^n <= 10^4
 *
 */
public class Code50 {

    public static void main(String[] args) {
        System.out.println(myPow(2.1, 3));
        System.out.println(myPow(2, -2));
    }


    /**
     * 题解一：
     *      快速幂算法： 二分法
     * @param x
     * @param n
     * @return
     */
    public static double myPow1(double x, int n) {
        if (x == 0) return 0;
        long b = n;
        double res = 1.0;
        if (b < 0){
            x = 1/x;
            b = -b;
        }
        while (b > 0){
            if ((b & 1) == 1) res *= x;
            x *= x;
            b >>= 1;
        }
        return res;
    }


    /**
     * 解法二： 快速幂 + 递归
     * @param x
     * @param n  指数，将 n 存入 long 中防止取反时越界，int 范围 [-2^31,2^31 - 1]
     * @return
     */
    public static double myPow2(double x, int n) {
        long N = n;
        return N >= 0 ? quickMul2(x,N) : 1.0/quickMul2(x,-N);
    }

    private static double quickMul2(double x, long N) {
        if (N == 0) return 1.0;
        double y = quickMul2(x, N/2);
        return N % 2 == 0 ? y*y : y*y*x;
    }


    /**
     * 解法三：快速幂 + 迭代
     * @param x
     * @param n
     * @return
     */
    public static double myPow(double x, int n){
        long N = n;
        return N >= 0 ? quickMul(x, N) : 1.0/quickMul(x, -N);
    }

    private static double quickMul(double x, long N) {
        double ans = 1.0;
        //贡献值的初始值为 x
        double x_contribute = x;
        //在对N进行二进制拆分的同时计算答案
        while (N > 0){
            if (N % 2 == 1){
                //如果N二进制表示的最低位为 1，那么需要计入贡献
                ans *= x_contribute;
            }
            //将贡献不断平方
            x_contribute *= x_contribute;
            //N = N >> 1;
            //N >>= 1;
            //舍弃N二进制表示的最低位，这样我们每次只要半段最低位即可
            N /= 2;
        }
        return ans;
    }


}






































