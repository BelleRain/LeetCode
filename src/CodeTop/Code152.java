package CodeTop;

/**
 * @author mxy
 * @create 2023-05-24 17:44
 */

/**
 * 152. 乘积最大子数组     链接：https://leetcode.cn/problems/maximum-product-subarray
 * 给你一个整数数组 nums ，请你找出数组中乘积最大的非空连续子数组（该子数组中至少包含一个数字），并返回该子数组所对应的乘积。
 * 测试用例的答案是一个 32-位 整数。
 * 子数组 是数组的连续子序列。
 *
 * 示例 1:
 * 输入: nums = [2,3,-2,4]
 * 输出: 6
 * 解释: 子数组 [2,3] 有最大乘积 6。
 *
 * 示例 2:
 * 输入: nums = [-2,0,-1]
 * 输出: 0
 * 解释: 结果不能为 2, 因为 [-2,-1] 不是子数组。
 *
 * 提示:
 * 1 <= nums.length <= 2 * 10^4
 * -10 <= nums[i] <= 10
 * nums 的任何前缀或后缀的乘积都 保证 是一个 32-位 整数
 *
 */
public class Code152 {

    public static void main(String[] args) {
        int[] nums = {2,3,-2,4};
        System.out.println(maxProduct(nums));
    }


    /**
     * 动态规划：
     *    1、遍历数组时计算当前最大值，不断更新
     *    2、令 imax 为当前最大值，则当前最大值为 imax = max（imax，nums[i]）
     *    3、由于存在负数，那么会导致最大的变最小的，最小的变最大的，因此还需要维护当前最小值 min，imin = min（imin * nums[i]，nums[i]）
     *    4、当负数出现时 ，imin = min（imin * nums[i], nums[i]）
     *    5、当负数出现时则 imax 与 imin 进行交换再进行下一步计算
     * 时间复杂度：O(n)
     * @param nums
     * @return
     */
    public static int maxProduct(int[] nums) {
        if (nums == null || nums.length == 0) return 0;
        int imax = 1, imin = 1,max = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] < 0){
                int tmp = imax;
                imax = imin;
                imin = tmp;
            }
            imax = Math.max(imax * nums[i], nums[i]);
            imin = Math.min(imin * nums[i], nums[i]);
            max = Math.max(max, imax);
        }
        return max;
    }
}



































