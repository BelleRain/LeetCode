package Top100;

/**
 * @author mxy
 * @create 2022-12-01 16:28
 */

/**
 * 198. 打家劫舍   链接：https://leetcode.cn/problems/house-robber
 * 你是一个专业的小偷，计划偷窃沿街的房屋。每间房内都藏有一定的现金，
 * 影响你偷窃的唯一制约因素就是相邻的房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警。
 * 给定一个代表每个房屋存放金额的非负整数数组，计算你 不触动警报装置的情况下 ，一夜之内能够偷窃到的最高金额。
 *
 * 示例 1：
 * 输入：[1,2,3,1]
 * 输出：4
 * 解释：偷窃 1 号房屋 (金额 = 1) ，然后偷窃 3 号房屋 (金额 = 3)。
 *      偷窃到的最高金额 = 1 + 3 = 4 。
 *
 * 示例 2：
 * 输入：[2,7,9,3,1]
 * 输出：12
 * 解释：偷窃 1 号房屋 (金额 = 2), 偷窃 3 号房屋 (金额 = 9)，接着偷窃 5 号房屋 (金额 = 1)。
 *      偷窃到的最高金额 = 2 + 9 + 1 = 12 。
 *  
 * 提示：
 * 1 <= nums.length <= 100
 * 0 <= nums[i] <= 400
 */
public class Top198 {

    public static void main(String[] args) {
        Top198 top198 = new Top198();
        //int[] nums = {2,7,9,3,1};
        int[] nums = {1,2,3,1};
        System.out.println(top198.rob(nums));
    }

    /**
     * 执行用时： 0 ms , 在所有 Java 提交中击败了 100.00% 的用户
     * 内存消耗： 38.8 MB , 在所有 Java 提交中击败了 65.45% 的用户
     * 思路分析：
     * 1、dp[i]: 到达 第 i 号房屋 能偷盗的最大金额
     * 2、dp[i] 的组成有两种可能， 取两种可能的最大值
     *     1） 第 i 家 被 偷盗，则 第 i-1 家 不能 偷盗， 前 i - 2家可以偷盗，则 dp[i] = dp[i-2] + nums[i]
     *     2） 第 i 家 没有 偷盗 ，则 前 i - 1 偷盗，即 dp[i] = dp[i-1]
     *    dp[i] = max(dp[i-2] + nums[i],dp[i-1])
     * 3、初始化： dp[0] = nums[0], dp[1] = max(nums[0],nums[1])
     *      采用这种写法，当 nums.length 为 1 时，会出现下标溢出
     * 4、边界条件：当 nums.length 为 1 时，直接 return nums[0]。
     * 5、最后 返回 dp[n]
     * @param nums
     * @return
     */
    public int rob(int[] nums) {
        int n = nums.length;
        if (n == 1) return nums[0];
        int[] dp = new int[n];
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);
        for (int i = 2; i < n; i++) {
            dp[i] = Math.max(dp[i-1], dp[i-2] + nums[i]);
        }
        return dp[n-1];
    }


    /**
     * 题解链接：https://leetcode.cn/problems/house-robber/solution/da-jia-jie-she-dong-tai-gui-hua-jie-gou-hua-si-lu-/
     * 空间优化：
     *      dp[i] 只与 dp[i-1] 和 dp[i-2] 有关
     *      pre : dp[i-2]
     *      cur : dp[i-1]
     * @param nums
     * @return
     */
    /*public int rob(int[] nums) {
        int pre = 0, cur = 0, tmp = 0;
        for (int num : nums) {
            tmp = cur;
            cur = Math.max(pre + num, cur);
            pre = tmp;
        }
        return cur;
    }*/

}
