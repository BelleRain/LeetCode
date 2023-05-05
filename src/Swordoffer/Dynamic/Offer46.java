package Swordoffer.Dynamic;

/**
 * @author mxy
 * @create 2022-09-26 7:48
 */

/**
 * 把数字翻译成字符串
 * 给定一个数字，我们按照如下规则把它翻译为字符串：0 翻译成 “a” ，1 翻译成 “b”，……，11 翻译成 “l”，……，25 翻译成 “z”。
 * 一个数字可能有多个翻译。请编程实现一个函数，用来计算一个数字有多少种不同的翻译方法。
 *
 * 示例 1:
 * 输入: 12258
 * 输出: 5
 * 解释: 12258有5种不同的翻译，分别是"bccfi", "bwfi", "bczi", "mcfi"和"mzi"
 *
 */
public class Offer46 {

    public static void main(String[] args) {
        Offer46 offer = new Offer46();
        int nums = 12258;
        int res = offer.translateNum(nums);
        System.out.println(res);
    }

    /** 原文链接：https://leetcode.cn/problems/ba-shu-zi-fan-yi-cheng-zi-fu-chuan-lcof/solution/mian-shi-ti-46-ba-shu-zi-fan-yi-cheng-zi-fu-chua-6/
     * 动态规划解析：
     *    记数字 num 第 i 位数字为 xi , 数字 num 的位数为 n ；
     *    例如： num = 12258 的 n = 5 , x1 = 1
     *   状态定义： 设动态规划列表 dp ，dp[i] 代表以 xi为结尾的数字的翻译方案数量。
     *   转移方程： 若 xi和 x(i−1) 组成的两位数字可以被翻译，则 dp[i] = dp[i - 1] + dp[i - 2]；否则 dp[i] = dp[i - 1]。
     *      可被翻译的两位数区间：当 x(i−1) =0 时，组成的两位数是无法被翻译的（例如 00, 01, 02,⋯ ），因此区间为 [10, 25]。
     *      dp[i]=
     *              dp[i−1]+dp[i−2]   ,(10x(i-1)+xi) ∈[10,25]
     *              dp[i−1]           ,(10x(i-1)+xi) ∈[0,10)∪(25,99]
     *    初始状态： dp[0] = dp[1] = 1 ，即 “无数字” 和 “第 1 位数字” 的翻译方法数量均为 1 ；
     *    返回值： dp[n] ，即此数字的翻译方案数量。
     *   Q： 无数字情况 dp[0] = 1 从何而来？
     *   A： 当 num 第 1, 2 位的组成的数字 ∈[10,25] 时，显然应有 2 种翻译方法，即 dp[2] = dp[1] + dp[0] = 2 ，而显然 dp[1] = 1 ，因此推出 dp[0] = 1 。
     */

    /**
     * 解法一： 字符串遍历
     *     为方便获取数字的各位 xi，考虑先将数字 num 转化为字符串 s ，通过遍历 s 实现动态规划。
     *     通过字符串切片 s[i−2:i] 获取数字组合 10x(i-1)+xi ，通过对比字符串 ASCII 码判断字符串对应的数字区间。
     * 空间使用优化：由于 dp[i] 只与 dp[i - 1] 有关，因此可使用两个变量 a, b 分别记录 dp[i], dp[i - 1]，两变量交替前进即可。此方法可省去 dp 列表使用的 O(N) 的额外空间。
     * 复杂度分析：
     *      时间复杂度 O(N) ： N 为字符串 s 的长度（即数字 num 的位数 log(num) ），其决定了循环次数。
     *      空间复杂度 O(N) ： 字符串 s 使用 O(N) 大小的额外空间。
     * @param num
     * @return
     */
    public int translateNum(int num) {
        String s = String.valueOf(num);
        // a, b 分别记录 dp[i], dp[i - 1]
        int a = 1,b = 1;
        for (int i = 2; i <= s.length(); i++) {
            String tmp = s.substring(i-2,i);
            int c = tmp.compareTo("10") >= 0 && tmp.compareTo("25") <= 0 ? a+b : a;
            b = a;
            a = c;
        }
        return a;
    }

    //使用数组，比较直观
    //public int translateNum(int num) {
    //    String s = String.valueOf(num);
    //    int[] dp = new int[s.length()+1];
    //    dp[0] = 1;
    //    dp[1] = 1;
    //    for(int i = 2; i <= s.length(); i ++){
    //        String temp = s.substring(i-2, i);
    //        if(temp.compareTo("10") >= 0 && temp.compareTo("25") <= 0)
    //            dp[i] = dp[i-1] + dp[i-2];
    //        else
    //            dp[i] = dp[i-1];
    //    }
    //    return dp[s.length()];
    //}

    /**
     * 此题的动态规划计算是 对称的 ，即 从左向右 遍历（从第 dp[2] 计算至 dp[n] ）和
     * 从右向左 遍历（从第 dp[n - 2] 计算至 dp[0] ）所得方案数一致。从右向左遍历的代码如下所示。
     */
    //public int translateNum(int num) {
    //        String s = String.valueOf(num);
    //        int a = 1, b = 1;
    //        for(int i = s.length() - 2; i > -1; i--) {
    //            String tmp = s.substring(i, i + 2);
    //            int c = tmp.compareTo("10") >= 0 && tmp.compareTo("25") <= 0 ? a + b : a;
    //            b = a;
    //            a = c;
    //        }
    //        return a;
    //}

    /**
     * 解法二：数字求余
     *    上述方法虽然已经节省了 dp 列表的空间占用，但字符串 s 仍使用了 O(N) 大小的额外空间。
     * 空间复杂度优化：
     *    利用求余运算 num%10 和 求整运算 num/10 ，可获取数字 num 的各位数字（获取顺序为个位、十位、百位…）。
     *    因此，可通过 求余 和 求整 运算实现 从右向左 的遍历计算。而根据上述动态规划 “对称性” ，可知从右向左的计算是正确的。
     *   自此，字符串 s 的空间占用也被省去，空间复杂度从 O(N) 降至 O(1) 。
     * 复杂度分析：
     *    时间复杂度 O(N) ： N 为字符串 s 的长度（即数字 num 的位数 log(num) ），其决定了循环次数。
     *   空间复杂度 O(1) ： 几个变量使用常数大小的额外空间。
     */
    //public int translateNum(int num) {
    //    int a = 1, b = 1, x, y = num % 10;
    //    while(num != 0) {
    //        num /= 10;
    //        x = num % 10;
    //        int tmp = 10 * x + y;
    //        int c = (tmp >= 10 && tmp <= 25) ? a + b : a;
    //        b = a;
    //        a = c;
    //        y = x;
    //    }
    //    return a;
    //}

}








































