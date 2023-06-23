package CodeTop;

/**
 * @author mxy
 * @create 2023-06-23 19:04
 */

/**
 * 剑指 Offer 10-I. 斐波那契数列   链接：https://leetcode.cn/problems/fei-bo-na-qi-shu-lie-lcof
 * 写一个函数，输入 n ，求斐波那契（Fibonacci）数列的第 n 项（即 F(N)）。斐波那契数列的定义如下：
 *
 * F(0) = 0,   F(1) = 1
 * F(N) = F(N - 1) + F(N - 2), 其中 N > 1.
 *
 * 斐波那契数列由 0 和 1 开始，之后的斐波那契数就是由之前的两数相加而得出。
 * 答案需要取模 1e9+7（1000000007），如计算初始结果为：1000000008，请返回 1。
 *  
 * 示例 1：
 * 输入：n = 2
 * 输出：1
 *
 * 示例 2：
 * 输入：n = 5
 * 输出：5
 *  
 * 提示：
 * 0 <= n <= 100
 *
 */
public class Offer10_1 {

    public static void main(String[] args) {
        System.out.println(fib(45));
    }

    /**
     * 执行用时： 0 ms , 在所有 Java 提交中击败了 100.00% 的用户
     * 内存消耗： 38 MB , 在所有 Java 提交中击败了 91.74% 的用户
     * @param n
     * @return
     */
    public static int fib11(int n) {
        if (n < 2) return n;
        int p = 0, q = 1, s = 0;
        for (int i = 2; i <= n; i++) {
            s = (p + q) % 1000000007;
            p = q;
            q = s;
        }
        return s;
    }


    /**
     * 解法一：动态规划
     * @param n
     * @return
     */
    public static int fib1(int n) {
        // a = 0，dp[0]
        int a = 0, b = 1, sum = a + b;
        for (int i = 0; i < n; i++) {
            sum = (a + b) % 1000000007;
            a = b;  //a 位于 dp[p] 的位置
            b = sum; // b 位于 dp[p + 1] 的位置
        }
        return a;
    }

    /**
     * 以下两种为易理解的写法：
     */
    public static int fib2(int n) {
        if (n == 0) return 0;
        int[] dp = new int[n + 1];
        dp[0] = 0;
        dp[1] = 1;
        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
            dp[i] %= 1000000007;
        }
        return dp[n];
    }

    //优化：不需要数组
    public static int fib3(int n) {
        if (n == 0) return 0;
        int fn0 = 0;
        int fn1 = 1;
        int temp = 0;
        for (int i = 2; i <= n; i++) {
            temp = fn0 + fn1;
            fn0 = fn1;
            //每次运算都取模，避免越界
            fn1 = temp % 1000000007;
        }
        return fn1;
    }

    public static int fib4(int n) {
        final int MOD = 1000000007;
        if (n < 2) {
            return n;
        }
        int p = 0, q = 0, r = 1;
        for (int i = 2; i <= n; i++) {
            p = q;
            q = r;
            r = (p + q) % MOD;
        }
        return r;
    }


    /**
     * 各位注意：字节面试要求写出logn解法
     * 解法二：矩阵快速幂  具体图解见链接：https://leetcode.cn/problems/fei-bo-na-qi-shu-lie-lcof/solution/fei-bo-na-qi-shu-lie-by-leetcode-solutio-hbss/
     *  复杂度分析：
     *      时间复杂度：O(logn)。
     *      空间复杂度：O(1)。
     * 提示：解析中矩阵的n次方的推导：利用递推关系
     * 快速求取 矩阵 M 的 n 次幂：快速幂算法
     */
    static final int MOD = 1000000007;
    public static int fib(int n) {
        if (n < 2) {
            return n;
        }
        int[][] q = {{1,1},
                     {1,0}};
        int[][] res = pow(q,n - 1);
        return res[0][0];
    }

    private static int[][] pow(int[][] a, int n) {
        int[][] ret = {{1,0},
                       {0,1}};
        while (n > 0){
            // n&1 与运算 可以判断n是否为偶数，如果是偶数，n&1返回0：否则返回1，为奇数
            // a&b 整数 a和b 按二进制对齐，按位进行与运算（除了11得1，其他均为0）
            if ((n & 1) == 1) {
                ret = multiply(ret,a);
            }
            n >>= 1;
            a = multiply(a,a);
        }
        return ret;
    }

    private static int[][] multiply(int[][] a, int[][] b) {
        int[][] c = new int[2][2];
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                c[i][j] = (int)(((long)a[i][0] * b[0][j] + (long)a[i][1] * b[1][j]) % MOD);
            }
        }
        return c;
    }


}












































