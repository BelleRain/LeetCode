package CodeTop;

/**
 * @author mxy
 * @create 2023-06-18 14:53
 */

/**
 * 补充题：字节跳动高频题——圆环回原点问题
 * 圆环上有10个点，编号为0~9。从0点出发，每次可以逆时针和顺时针走一步，问走n步回到0点共有多少种走法。
 * 输入: 2
 * 输出: 2
 * 解释：有2种方案。分别是0->1->0和0->9->0
 */
public class Code_add {

    public static void main(String[] args) {
        System.out.println(backToOrigin(2));
    }


    /**
     * 动态规划：
     *      走 n 步到 0 的方案数 = 走 n - 1步 到 1 的方案数 + 走 n - 1步到 9 的方案数
     *    因此，若设 dp[i][j] 为从 0 点出发走 i 步到 j 点的方案数，则递推式为：
     *       dp[i][j] = dp[i - 1][(j - 1 + length) % length] + dp[i - 1][(j + 1) % length]
     *    PS：公式之所以取余是因为 j - 1 或 j + 1可能会超过圆环 0~9 的范围
     * @param n
     * @return
     */
    public static int backToOrigin(int n){
        //点的个数为 10
        int length = 10;
        int[][] dp = new int[n + 1][length];
        dp[0][0] = 1;
        for (int i = 1; i < n + 1; i++) {
            for (int j = 0; j < length; j++) {
                //dp[i][j] 表示从 0 出发，走 i 步到 j的方案数
                dp[i][j] = dp[i - 1][(j - 1 + length) % length] + dp[i - 1][(j + 1) % length];
            }
        }
        return dp[n][0];
    }
}
