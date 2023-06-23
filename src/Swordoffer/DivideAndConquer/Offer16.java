package Swordoffer.DivideAndConquer;

/**
 * @author mxy
 * @create 2022-10-13 10:12
 */

/**
 * 数值的整数次方
 * 实现 pow(x, n) ，即计算 x 的 n 次幂函数（即，x^n）。不得使用库函数，同时不需要考虑大数问题。
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
 * -100.0 < x < 100.0
 * -2^31 <= n <= 2^31-1
 * -10^4 <= x^n <= 10^4
 */
public class Offer16 {

    public static void main(String[] args) {
        Offer16 offer = new Offer16();
        System.out.println(offer.myPow(-2,-2));
        //System.out.println(Integer.MAX_VALUE);
        //System.out.println(Integer.MIN_VALUE);
    }

    /** 解法一：题解链接：https://leetcode.cn/problems/shu-zhi-de-zheng-shu-ci-fang-lcof/solution/mian-shi-ti-16-shu-zhi-de-zheng-shu-ci-fang-kuai-s/
     * 解题思路：
     *   求 x^n 最简单的方法是通过循环将 n 个 x 乘起来，依次求x^1,x^2,...,x^(n-1),x^n，时间复杂度为O(n).
     *   快速幂法可将时间复杂度降低至O(log2n)(log以2为底的n)，以下从 “二分法” 和 “二进制” 两个角度解析快速幂法。
     * 快速幂解析（二进制角度）：
     *   利用十进制数字 n 的二进制表示，可对快速幂进行数学化解释。
     *       对于任何十进制正整数 n ，设其二进制为 “bm...b3b2b1”(bi为二进制某位值，i∈[1,m])，则有：
     *           二进制转十进制：n=1b1+2b2+4b3+...+2^(m-1)bm （即二进制转十进制公式）
     *           幂的二进制展开：x^n = x^(1b1+2b2+4b3+...+2^(m-1)bm)
     *       根据以上推导，可把计算 x^n转化为解决以下两个问题：
     *          计算x^1,x^2,x^4 ,...,x^(2^(m-1))的值 ： 循环赋值操作 x = x^2即可；
     *          获取二进制各位b1,b2,b3,...,bm的值： 循环执行以下操作即可
     *            1、n&1 （与操作）： 判断 n 二进制最右一位是否为 1 ；
     *            2、n>>1 （移位操作）： n 右移一位（可理解为删除最后一位）。
     *      因此，应用以上操作，可在循环中依次计算 x(2^0b1),x^(2^1b2),...,x^(2^(m-1)bm)的值，并将所有 x^(2^(i-1)bi)累计相乘即可、
     *        当 bi = 0 时，x^(2^(i-1)bi = 1；
     *        当 bi = 1 时，x^(2^(i-1)bi = x^(2^(i-1))；
     * 快速幂解析（二分法角度）：
     * 快速幂实际上是二分思想的一种应用。
     *      二分推导：x^n = x^(n/2) * x^(n/2)=(x^2)^(n/2),令 n/2 为整数，则需要分为奇偶两种情况（设向下取整除法符号为 "//" ）：
     *          当n为偶数时，x^n = (x^2)^(n//2);
     *          当n为奇数时，x^n = x*(x^2)^(n//2),即会多一项x；
     *      幂结果获取：
     *          根据二分推导，可通过循环 x = x^2操作，每次把幂从 n 降至 n//2 ，直至将幂降为 0 ；
     *          设 res=1res=1 ，则初始状态 x^n = x^n×res 。在循环二分时，每当 n 为奇数时，将多出的一项 x 乘入 res ，
     *          则最终可化至 x^n = x^0×res=res ，返回 res 即可。
     *      转化为位运算：
     *          向下整除 n // 2 等价于 右移一位 n >> 1 ；
     *          取余数 n % 2 等价于 判断二进制最右一位值 n&1 ；
     * 算法流程：
     *      1、当 x = 0 时：直接返回 0 （避免后续 x = 1 / x 操作报错）。
     *      2、初始化 res = 1 ；
     *      3、当 n < 0 时：把问题转化至 n≥0 的范围内，即执行 x = 1/x ，n=−n ；
     *      4、循环计算：当 n = 0 时跳出；
     *          1、当 n&1=1 时：将当前 x 乘入 res （即 res *= x ）；
     *          2、执行 x = x^2 （即 x *= x ）；
     *          3、执行 n 右移一位（即 n >>= 1）。
     *      5、返回res
     *复杂度分析：
     *  时间复杂度 O(log2n):  二分的时间复杂度为对数级别。
     *  空间复杂度 O(1) ： res, b 等变量占用常数大小额外空间。
     * 代码：
     * Java 代码中 int32 变量 n∈[−2147483648,2147483647] ，因此当 n = -2147483648 时执行 n=−n 会因越界而赋值出错。解决方法是先将 n 存入 long 变量 b ，后面用 b 操作即可。
     * @param x
     * @param n
     * @return
     */
    //快速幂算法
    public double myPow(double x, int n) {
       if (x == 0) return 0;
       long b = n;
       double res = 1.0;
       if (b < 0){
           x = 1/x;
           b = -b;
       }
       while (b > 0){
           if ((b & 1) == 1) res *= x;
           x *=x;
           b >>= 1;
       }
       return res;
    }

    /**
     * 题解链接：https://leetcode.cn/problems/shu-zhi-de-zheng-shu-ci-fang-lcof/solution/shu-zhi-de-zheng-shu-ci-fang-by-leetcode-yoqr/
     */

    /**
     * 解法二：快速幂 + 递归
     * @param x
     * @param n  指数，将n存入long中防止取反时越界，int范围[-2^31,2^31-1]
     * @return
     * 复杂度分析：
     * 时间复杂度：O(logn)，即为递归的层数。
     * 空间复杂度：O(logn)，即为递归的层数。这是由于递归的函数调用会使用栈空间。
     */
    public double myPow2(double x, int n) {
        long N = n;
        return N >= 0 ? quickMul2(x, N) : 1.0/quickMul2(x, -N);
    }
    public double quickMul2(double x,long N){
        if (N == 0) return 1.0;
        double y = quickMul2(x, N/2);
        return N % 2 == 0 ? y*y : y*y*x;
    }

    /**
     * 解法三：快速幂 + 迭代
     * @param x
     * @param n
     * @return
     * 复杂度分析：
     * 时间复杂度：O(logn)，即为对 n 进行二进制拆分的时间复杂度。
     * 空间复杂度：O(1)。
     */
    public double myPow3(double x, int n) {
        long N = n;
        return N >= 0 ? quickMul(x, N) : 1.0 / quickMul(x, -N);
    }
    public double quickMul(double x,long N){
        double ans = 1.0;
        //贡献的初始值为x
        double x_contribute = x;
        //在对N进行二进制拆分的同时计算答案
        while (N > 0){
            if (N % 2 == 1){
                //如果N二进制表示的最低位为1，那么需要计入贡献
                ans *= x_contribute;
            }
            //将贡献不断平方
            x_contribute *= x_contribute;
            //N = N >> 1;
            //N >>= 1;
            //舍弃N二进制表示的最低位，这样我们每次只要判断最低位即可
            N /= 2;
        }
        return ans;
    }
}


























