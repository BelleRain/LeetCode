package CodeTop;

/**
 * @author mxy
 * @create 2023-04-11 14:42
 */

/**
 * 53. 最大子数组和   链接：https://leetcode.cn/problems/maximum-subarray
 * 给你一个整数数组 nums ，请你找出一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
 * 子数组 是数组中的一个连续部分。
 *
 * 示例 1：
 * 输入：nums = [-2,1,-3,4,-1,2,1,-5,4]
 * 输出：6
 * 解释：连续子数组 [4,-1,2,1] 的和最大，为 6 。
 *
 * 示例 2：
 * 输入：nums = [1]
 * 输出：1
 *
 * 示例 3：
 * 输入：nums = [5,4,-1,7,8]
 * 输出：23
 *  
 *
 * 提示：
 * 1 <= nums.length <= 10^5
 * -10^4 <= nums[i] <= 10^4
 *  
 * 进阶：如果你已经实现复杂度为 O(n) 的解法，尝试使用更为精妙的 分治法 求解。
 *
 */
public class Code53 {
    public static void main(String[] args) {
        int[] nums = {-2,1,-3,4,-1,2,1,-5,4};
        System.out.println(maxSubArray1(nums));
        System.out.println(maxSubArray2(nums));
        System.out.println(maxSubArray3(nums));
    }

    /**
     * 动态规划：dp[i] : 到达 i 处 的元素之和
     * 1、dp[0] = nums[0] , max = dp[0]
     * 2、 if  dp[i - 1] + nums[i] > nums[i] , dp[i] = dp[i - 1] + nums[i]
     *     else dp[i] = nums[i]
     *     max = Math.max(max,dp[i])
     * 3、return max
     * @param nums
     * @return
     */
    public static int maxSubArray1(int[] nums) {
        if (nums.length <= 1) return nums[0];
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        int max = dp[0];
        for (int i = 1; i < nums.length; i++) {
            if (dp[i - 1]  > 0){
                dp[i] = dp[i - 1] + nums[i];
            }else {
                dp[i] = nums[i];
            }
            max = Math.max(max, dp[i]);
        }
        return max;
    }


    /**
     * 空间优化：
     * @param nums
     * @return
     */
    public static int maxSubArray2(int[] nums) {
        if (nums.length <= 1) return nums[0];
        int pre = nums[0], max = nums[0];
        for (int i = 1; i < nums.length; i++) {
            pre = Math.max(nums[i], pre + nums[i]);
            max = Math.max(max, pre);
        }
        return max;
    }


    /**
     * 分治法
     * @param nums
     * @return
     */
    public static int maxSubArray3(int[] nums) {
        int len = nums.length;
        if (len == 0) {
            return 0;
        }
        return maxSubArraySum(nums, 0, len - 1);
    }

    private static int maxCrossingSum(int[] nums, int left, int mid, int right) {
        // 一定会包含 nums[mid] 这个元素
        int sum = 0;
        int leftSum = Integer.MIN_VALUE;
        // 左半边包含 nums[mid] 元素，最多可以到什么地方
        // 走到最边界，看看最值是什么
        // 计算以 mid 结尾的最大的子数组的和
        for (int i = mid; i >= left; i--) {
            sum += nums[i];
            if (sum > leftSum) {
                leftSum = sum;
            }
        }
        sum = 0;
        int rightSum = Integer.MIN_VALUE;
        // 右半边不包含 nums[mid] 元素，最多可以到什么地方
        // 计算以 mid+1 开始的最大的子数组的和
        for (int i = mid + 1; i <= right; i++) {
            sum += nums[i];
            if (sum > rightSum) {
                rightSum = sum;
            }
        }
        return leftSum + rightSum;
    }

    private static int maxSubArraySum(int[] nums, int left, int right) {
        if (left == right) {
            return nums[left];
        }
        int mid = left + (right - left) / 2;
        return max3(maxSubArraySum(nums, left, mid),
                maxSubArraySum(nums, mid + 1, right),
                maxCrossingSum(nums, left, mid, right));
    }

    private static int max3(int num1, int num2, int num3) {
        return Math.max(num1, Math.max(num2, num3));
    }
}




























