package Swordoffer.Dynamic;

/**
 * @author mxy
 * @create 2022-09-23 13:12
 */

/**
 * 斐波那契数列
 *写一个函数，输入 n ，求斐波那契（Fibonacci）数列的第 n 项（即 F(N)）。斐波那契数列的定义如下：
 *
 * F(0) = 0,   F(1) = 1
 * F(N) = F(N - 1) + F(N - 2), 其中 N > 1.
 * 斐波那契数列由 0 和 1 开始，之后的斐波那契数就是由之前的两数相加而得出。
 *
 * 答案需要取模 1e9+7（1000000007），如计算初始结果为：1000000008，请返回 1。
 *
 * 示例 1：
 * 输入：n = 2
 * 输出：1
 *
 * 示例 2:
 * 输入：n = 5
 * 输出：5
 */
public class Offer10_1 {
    public static void main(String[] args) {
        Offer10_1 offer = new Offer10_1();
        //int fib = offer.fib(5);
        //System.out.println(fib);
        //System.out.println(Integer.MAX_VALUE);  //2147483647
        //System.out.println(Integer.MIN_VALUE);  //-2147483648
        //System.out.println((int)Swordoffer.Math.pow(2, 31)); //2147483647

        int[][] a = {{1,1},{1,0}};
        offer.pow(a, 3);

    }

    /**
     * 错误点： 运算超时 ,取模原因：超出int类型时会出错
     * @param n
     * @return
     */
    //public int fib(int n){
    //    if (n == 0) return 0;
    //    if (n == 1) return 1;
    //    return (fib(n-1) + fib(n-2))%((int)(Swordoffer.Math.pow(10, 9) + 7));
    //}


    /**
     * 解法一：  链接：https://leetcode.cn/problems/fei-bo-na-qi-shu-lie-lcof/solution/mian-shi-ti-10-i-fei-bo-na-qi-shu-lie-dong-tai-gui/
     * 解题思路：
     *      斐波那契数列的定义是 f(n + 1) = f(n) + f(n - 1) ，生成第 n 项的做法有以下几种：
     *     递归法：
     *      原理： 把 f(n) 问题的计算拆分成 f(n-1) 和 f(n-2) 两个子问题的计算，并递归，以 f(0) 和 f(1) 为终止条件。
     *      缺点： 大量重复的递归计算，例如 f(n) 和 f(n - 1) 两者向下递归需要 各自计算 f(n - 2) 的值。
     *      举例：f(n) = f(n-1) + f(n-2),
     *           f(n-1) = f(n-2) + f(n-3),
     *           f(n-2) = f(n-3) + f(n-4)
     *           ... 各自向下递归，可以看出 f(n-2) 和 f(n-3) 重复计算
     *       将整个递归过程 以树的形式表示出来（f(n)为根节点，f(n-1)为左子节点，f(n-2)为右子节点，其他类推），
     *       可以看出 时间复杂度 ：O（2^n）(类似于二叉树的节点数)；空间复杂度：O(n)(二叉树的高度)
     * 记忆化递归法：
     *      原理： 在递归法的基础上，新建一个长度为 n 的数组，用于在递归时存储 f(0) 至 f(n) 的数字值，重复遇到某数字则直接从数组取用，避免了重复的递归计算。
     *      缺点： 记忆化存储需要使用 O(N) 的额外空间。
     * 动态规划：
     *      原理： 以斐波那契数列性质 f(n + 1) = f(n) + f(n - 1) 为转移方程。
     *      从计算效率、空间复杂度上看，动态规划是本题的最佳解法。
     * 动态规划解析：
     *      状态定义： 设 dp 为一维数组，其中 dp[i] 的值代表 斐波那契数列第 i 个数字 。
     *      转移方程： dp[i + 1] = dp[i] + dp[i - 1] ，即对应数列定义 f(n + 1) = f(n) + f(n - 1)；
     *      初始状态： dp[0] = 0, dp[1] = 1 ，即初始化前两个数字；
     *      返回值： dp[n] ，即斐波那契数列的第 n 个数字。
     * 空间复杂度优化：
     *      若新建长度为 n 的 dp 列表，则空间复杂度为 O(N) 。
     *      由于 dp 列表第 i 项只与第 i-1 和第 i-2 项有关，因此只需要初始化三个整形变量 sum, a, b ，利用辅助变量 sum 使 a, b 两数字交替前进即可 （具体实现见代码） 。
     *      节省了 dp 列表空间，因此空间复杂度降至 O(1) 。
     * 循环求余法：
     *     大数越界： 随着 n 增大, f(n) 会超过 Int32 甚至 Int64 的取值范围，导致最终的返回值错误。
     * 求余运算规则： 设正整数 x, y,求余符号为 ⊙ ，则有 (x + y) ⊙ p = (x ⊙ p + y ⊙ p) ⊙ p(x+y)⊙p=(x⊙p+y⊙p)⊙p 。
     * 解析： 根据以上规则，可推出 f(n) ⊙ p = [f(n-1) ⊙ p + f(n-2) ⊙ p]  ，从而可以在循环过程中每次计算 sum=(a+b)⊙1000000007 ，此操作与最终返回前取余等价。
     *
     * @param n
     * @return
     */
    //public int fib(int n){
    //    //a = 0，d[0]
    //    int a=0,b=1,sum = a+b;
    //    for (int i = 0; i < n; i++) {
    //        sum = (a + b) % 1000000007;
    //        a=b;  // a位于d[p]的位置
    //        b=sum; // b 位于 dp[p + 1] 的位置
    //    }
    //    return a;
    //}

    /**
     * 以下两种为易理解写法：
     */
    //  public int fib(int n) {
    //     if(n == 0) return 0;
    //     int[] dp = new int[n + 1];
    //     dp[0] = 0;
    //     dp[1] = 1;
    //     for(int i = 2; i <= n; i++){
    //         dp[i] = dp[i-1] + dp[i-2];
    //         dp[i] %= 1000000007;
    //     }
    //     return dp[n];
    //  }
    //
    //优化：不需要数组
    //public int fib(int n) {
    //    if(n == 0) return 0;
    //    int fn0 = 0;
    //    int fn1 = 1;
    //    int temp;
    //    for(int i = 2; i <= n; i++){
    //        temp = fn0 + fn1;
    //        fn0 = fn1;
    //        fn1 = temp% 1000000007; // 每次运算都取模 避免越界
    //    }
    //    return fn1;
    //}

    //public int fib(int n) {
    //    final int MOD = 1000000007;
    //    if (n < 2) {
    //        return n;
    //    }
    //    int p = 0, q = 0, r = 1;
    //    for (int i = 2; i <= n; ++i) {
    //        p = q;
    //        q = r;
    //        r = (p + q) % MOD;
    //    }
    //    return r;
    //}

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
    public int fib(int n) {
        if (n < 2){
            return n;
        }
        int[][] q = {{1,1},{1,0}};
        int[][] res = pow(q,n-1);
        return res[0][0];
    }
    public int[][] pow(int[][] a,int n){
        int[][] ret = {{1,0},{0,1}};
        while (n > 0){
            //n&1 与运算 可以判断n是否为偶数 如果是偶数，n&1返回0；否则返回1，为奇数
            //a&b 整数a和b按二进制对齐，按位进行与运算（除了11得1，其他均为0）
            if ((n & 1) == 1){
                ret = multiply(ret, a);
            }
            n >>= 1;
            a = multiply(a, a);
        }
        return ret;
    }
    public int[][] multiply(int[][] a,int[][] b){
        int[][] c = new int[2][2];
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                c[i][j] = (int)(((long)a[i][0]*b[0][j] + (long)a[i][1]*b[1][j]) % MOD);
            }
        }
        return c;
    }

    /**
     * **前置知识**
     *
     * 快速幂算法原理:
     *
     * 如需求数据 a 的幂次，此处 a 可以为数也可以为矩阵，常规做法需要对a进行不断的乘积即 a * a * a * ... 此处的时间复杂度将为 O(n)
     *
     * 以求 3^10 的结果为例：
     *
     * [优化步骤1：]
     *
     * 易知：
     *
     * 3^10=3*3*3*3*3*3*3*3*3*3
     *
     *     =9^5 = 9^4*9
     *
     *     =81^2*9
     *
     *     =6561*9
     * 基于以上原理，我们在计算一个数的多次幂时，可以先判断其幂次的奇偶性，然后：
     *
     * 如果幂次为偶直接 base(底数) 作平方，power(幂次) 除以2
     *
     * 如果幂次为奇则底数平方，幂次整除于2然后再多乘一次底数
     *
     * [优化步骤2：]
     *
     * 对于以上涉及到 [判断奇偶性] 和 [除以2] 这样的操作。使用系统的位运算比普通运算的效率是高的，因此可以进一步优化：
     *
     * 把 power % 2 == 1 变为 (power & 1) == 1
     *
     * 把 power = power / 2 变为 power = power >> 1
     *
     * **本题题解：矩阵快速幂。时间O(logn),空间O(1):**
     */
    //static final int MOD = 1000000007;
    //public int fib(int n) {
    //    //矩阵快速幂
    //    if (n < 2) {
    //        return n;
    //    }
    //    //定义乘积底数
    //    int[][] base = {{1, 1}, {1, 0}};
    //    //定义幂次
    //    int power = n - 1;
    //    int[][] ans = calc(base, power);
    //    //按照公式，返回的是两行一列矩阵的第一个数
    //    return ans[0][0];
    //}
    //
    ////定义函数,求底数为 base 幂次为 power 的结果
    //public int[][] calc(int[][] base, int power) {
    //    //定义变量，存储计算结果，此次定义为单位阵
    //    int[][] res = {{1, 0}, {0, 1}};
    //
    //    //可以一直对幂次进行整除
    //    while (power > 0) {
    //        //1.若为奇数，需多乘一次 base
    //        //2.若power除到1，乘积后得到res
    //        //此处使用位运算在于效率高
    //        if ((power & 1) == 1) {
    //            res = mul(res, base);
    //        }
    //        //不管幂次是奇还是偶，整除的结果是一样的如 5/2 和 4/2
    //        //此处使用位运算在于效率高
    //        power = power >> 1;
    //        base = mul(base, base);
    //    }
    //    return res;
    //}
    //
    ////定义函数,求二维矩阵：两矩阵 a, b 的乘积
    //public int[][] mul(int[][] a, int[][] b) {
    //    int[][] c = new int[2][2];
    //    for (int i = 0; i < 2; i++) {
    //        for (int j = 0; j < 2; j++) {
    //            //矩阵乘积对应关系，自己举例演算一遍便可找到规律
    //            c[i][j] = (int)(((long)a[i][0]*b[0][j] + (long)a[i][1]*b[1][j]) % MOD);
    //        }
    //    }
    //    return c;
    //}

}
































