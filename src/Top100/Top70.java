package Top100;

/**
 * @author mxy
 * @create 2022-11-23 8:28
 */

/**
 * 70. 爬楼梯  链接：https://leetcode.cn/problems/climbing-stairs/?favorite=2cktkvj
 * 假设你正在爬楼梯。需要 n 阶你才能到达楼顶。
 * 每次你可以爬 1 或 2 个台阶。你有多少种不同的方法可以爬到楼顶呢？
 *
 * 示例 1：
 * 输入：n = 2
 * 输出：2
 * 解释：有两种方法可以爬到楼顶。
 * 1. 1 阶 + 1 阶
 * 2. 2 阶
 *
 * 示例 2：
 * 输入：n = 3
 * 输出：3
 * 解释：有三种方法可以爬到楼顶。
 * 1. 1 阶 + 1 阶 + 1 阶
 * 2. 1 阶 + 2 阶
 * 3. 2 阶 + 1 阶
 *
 * 提示：
 * 1 <= n <= 45
 */
public class Top70 {

    public static void main(String[] args) {
        Top70 top70 = new Top70();
        System.out.println(top70.climbStairs(3));
    }

    /**
     * 方法一： 动态规划
     * 解题思路：
     * 1、f(n) : 到达第 n 阶 的方法种数
     * 2、n = 1 时，f(1) = 1; n = 2 时，f(2) = 2; n = 3 时，f(3) = f(2) + f(1),...
     * 3、到达第 n 阶的跳法 = 到达 第 n-1 阶的跳法 + 到达 第 n-2 阶的跳法 （n >= 3）
     *    原因：第 n-1 阶 只跳 1 阶即可达 第 n 阶 ，第 n-2 阶 只跳 2 阶 即可达 第 n 阶;
     *    由 n-2 阶 跳 1 阶 再跳 1 阶到达 n 阶的 已经 包含在 到达 n - 1阶 的跳法之内
     *    或者说 第 n 阶 只能由 两种方法到达：由第 n-1 阶 只跳 1 阶，由第 n-2 阶 只跳 2 阶
     * 4、递推公式 ： f(n) = f(n-1) + f(n-2)
     * 5、返回 f(n)
     * @param n
     * @return
     */
    /* //递归超时
    public int climbStairs(int n) {
        if (n == 1) return 1;
        if (n == 2) return 2;
        return climbStairs(n-1) + climbStairs(n-2);
    }*/

    public int climbStairs(int n) {
        if (n == 1) return 1;
        if (n == 2) return 2;
        int[] dp = new int[n];
        dp[0] = 1;
        dp[1] = 2;
        for (int i = 2; i < n; i++) {
            dp[i] = dp[i-1] + dp[i-2];
        }
        return dp[n-1];
    }

    /**
     * 题解链接：https://leetcode.cn/problems/climbing-stairs/solution/pa-lou-ti-by-leetcode-solution/
     */
    /**
     * 方法一：动态规划，滚动数组空间优化
     * 复杂度分析
     *      时间复杂度：循环执行 n 次，每次花费常数的时间代价，故渐进时间复杂度为 O(n)。
     *      空间复杂度：这里只用了常数个变量作为辅助空间，故渐进空间复杂度为 O(1)。
     * @param n
     * @return
     */
    public int climbStairs2(int n) {
        int p = 0, q = 0, r = 1;
        for (int i = 1; i <= n; i++) {
            p = q;
            q = r;
            r = p + q;
        }
        return r;
    }

    /**
     * 方法二：矩阵快速幂
     * 复杂度分析
     *      时间复杂度：同快速幂，O(logn)。
     *      空间复杂度：O(1)。
     * @param n
     * @return
     */
    /*public int climbStairs(int n) {
        int[][] q = {{1, 1}, {1, 0}};
        int[][] res = pow(q, n);
        return res[0][0];
    }
    //矩阵快速幂
    private int[][] pow(int[][] a, int n) {
        int[][] ret = {{1,0},{0,1}};
        while (n > 0){
            if ((n & 1) == 1) ret = multiply(ret, a);
            n >>= 1;
            a = multiply(a, a);
        }
        return ret;
    }

    public int[][] multiply(int[][] a, int[][] b) {
        int[][] c = new int[2][2];
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                c[i][j] = a[i][0] * b[0][j] + a[i][1] * b[1][j];
            }
        }
        return c;
    }*/

    /**
     * 方法三：斐波那契数列 通项公式
     * @param n
     * @return
     */
    /*public int climbStairs(int n) {
        double sqrt5 = Math.sqrt(5);
        double fibn = Math.pow((1 + sqrt5) / 2, n + 1) - Math.pow((1 - sqrt5) / 2, n + 1);
        return (int) Math.round(fibn / sqrt5);
    }*/
}
