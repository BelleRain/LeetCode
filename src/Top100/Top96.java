package Top100;

/**
 * @author mxy
 * @create 2022-11-24 8:37
 */

/**
 * 96. 不同的二叉搜索树    链接：https://leetcode.cn/problems/unique-binary-search-trees/?favorite=2cktkvj
 * 给你一个整数 n ，求恰由 n 个节点组成且节点值从 1 到 n 互不相同的 二叉搜索树 有多少种？
 * 返回满足题意的二叉搜索树的种数。
 *
 * 示例 1：
 * 输入：n = 3
 * 输出：5
 *
 * 示例 2：
 * 输入：n = 1
 * 输出：1
 *
 * 提示：
 * 1 <= n <= 19
 */
public class Top96 {

    public static void main(String[] args) {
        Top96 top96 = new Top96();
        System.out.println(top96.numTrees(3));
    }

    /**
     * 题解链接： https://leetcode.cn/problems/unique-binary-search-trees/solution/hua-jie-suan-fa-96-bu-tong-de-er-cha-sou-suo-shu-b/
     * 方法一： 动态规划
     * 1、G(n): n 个节点存在二叉排序树 的个数
     * 2、f(i): 以 f(i) 为根的，节点为 n个的，二叉搜索树的 个数
     * 3、G(n) = f(1) + f(2) + f(3) + ... + f(n)
     * 4、当以 i 为 根节点 时，其左子树节点个数 为 i - 1 个，右子树节点 为 n - i
     *      f(i) = G(i-1) * G(n-i)
     * 5、综合两个公式可得 卡特兰数 公式：
     *      G(n) = G(0)*G(n-1) + G(1)*G(n-2) + G(2)*G(n-3) + ... + G(n-1)*G(0)
     *      ( G(n) = f(1) + f(2) + f(3) + ... + f(n) )
     * @param n
     * @return
     */
    public int numTrees(int n) {
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2; i < n+1; i++) {
            for (int j = 1; j < i + 1; j++) {
                //dp[j - 1] * dp[i - j] : 以 j 为 根节点，序列长度为 i 的 种数
                dp[i] = dp[i] + dp[j - 1] * dp[i - j];
            }
        }
        return dp[n];
    }

    /**
     * 题解链接：https://leetcode.cn/problems/unique-binary-search-trees/solution/bu-tong-de-er-cha-sou-suo-shu-by-leetcode-solution/
     * 方法二 ： 数学
     *      在 方法一 推导出的 G(n) 函数值 为 在数学上为 卡特兰数 C(n)
     *      卡特兰数C(n) https://leetcode.cn/link/?target=https://baike.baidu.com/item/catalan/7605685?fr=aladdin
     *  C(0) = 1,  C(n+1) = { {2(2n + 1)}/(n+2) } C(n)
     *  证明过程参考上述链接
     * @param n
     * @return
     */
    /*public int numTrees(int n) {
        //提示：我们在这里需要用 long 类型 防止 计算过程中的溢出
        long C = 1;
        for (int i = 0; i < n; i++) {
            C = C * 2 * (2 * i + 1) / (i + 2);
        }
        return (int)C;
    }*/
}
