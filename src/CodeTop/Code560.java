package CodeTop;

/**
 * @author mxy
 * @create 2023-05-25 15:38
 */

import java.util.HashMap;
import java.util.Map;

/**
 * 560. 和为 K 的子数组   链接：https://leetcode.cn/problems/subarray-sum-equals-k
 * 给你一个整数数组 nums 和一个整数 k ，请你统计并返回 该数组中和为 k 的 连续子数组 的个数 。
 *
 * 示例 1：
 * 输入：nums = [1,1,1], k = 2
 * 输出：2
 *
 * 示例 2：
 * 输入：nums = [1,2,3], k = 3
 * 输出：2
 *
 * 提示：
 * 1 <= nums.length <= 2 * 10^4
 * -1000 <= nums[i] <= 1000
 * -10^7 <= k <= 10^7
 *
 */
public class Code560 {

    public static void main(String[] args) {
        int[] nums = {1,2,3};
        System.out.println(subarraySum(nums, 3));
    }

    /**
     * 题解链接：https://leetcode.cn/problems/subarray-sum-equals-k/solution/bao-li-jie-fa-qian-zhui-he-qian-zhui-he-you-hua-ja/
     */

    /**
     * 方法一：暴力解法
     *      枚举左右边界（超时）
     * 复杂度分析：
     *     时间复杂度：O(N^3)，这里 N 是数组的长度；
     *     空间复杂度：O(1)。
     * @param nums
     * @param k
     * @return
     */
    public static int subarraySum1(int[] nums, int k) {
       int len = nums.length;
       int count = 0;
        for (int left = 0; left < len; left++) {
            for (int right = left; right < len; right++) {
                int sum = 0;
                for (int i = left; i <= right; i++) {
                    sum += nums[i];
                }
                if (sum == k){
                    count++;
                }
            }
        }
        return count;
    }


    /**
     * 方法二：暴力解法的优化
     *   固定了起点，即先固定左边界，然后枚举右边界，时间复杂度降一维。
     * 复杂度分析：
     * 时间复杂度：O(N^2)，这里 N 是数组的长度；
     * 空间复杂度：O(1)。
     * @param nums
     * @param k
     * @return
     */
    public static int subarraySum2(int[] nums, int k) {
        int count = 0;
        int len = nums.length;
        for (int left = 0; left < len; left++) {
            int sum = 0;
            //区间里可能会有一些互相抵销的元素
            for (int right = left; right < len; right++) {
                sum += nums[right];
                if (sum == k){
                    count++;
                }
            }
        }
        return count;
    }


    /**
     * 方法三：前缀和
     *    构建前缀和数组，以快速计算区间和；
     *    注意在计算区间和的时候，下标有偏移。
     * 复杂度分析：
     *  时间复杂度：O(N^2)，这里 N 是数组的长度；
     *  空间复杂度：O(N)。
     * @param nums
     * @param k
     * @return
     */
    public static int subarraySum3(int[] nums, int k) {
        int len = nums.length;
        //计算前缀和数组
        int[] preSum = new int[len + 1];
        preSum[0] = 0;
        for (int i = 0; i < nums.length; i++) {
            //preSum[1] = 0 + nums[0];
            //preSum[2] = 0 + nums[0] + nums[1];
            //preSum[3] = 0 + nums[0] + nums[1] + nums[2]
            preSum[i + 1] = preSum[i] + nums[i];
        }

        int count = 0;
        for (int left = 0; left < nums.length; left++) {
            for (int right = left; right < len; right++) {
                //区间和 [left...right],注意下标偏移
                //nums[left] + ... + nums[right]
                //0 + ... + left - 1 + left + ...+ right
                //0 + ... + left - 1
                //两者相减 left + ... + right
                if (preSum[right + 1] - preSum[left] == k){
                    count++;
                }
            }
        }
        return count;
    }


    /**
     * 方法四：前缀和 + 哈希表优化
     *    1、由于只关心次数，不关心具体的解，我们可以使用哈希表加速运算
     *    2、由于保存了之前相同前缀和的个数，计算区间总数的时候不是一个一个地加，时间复杂度降到了 O(N).
     * 解释一开始 preSumFreq.put(0,1); 的意义
     *    计算完包括了当前数前缀和以后，我们去查一查在当前数之前，有多少个前缀和等于 preSume - k 呢？
     *  这是因为满足 preSum - （preSum - k） == k 的区间的个数是我们所关心的。
     *    对于一开始的情况，下标 0 之前没有元素，可以认为前缀和为 0 ，个数为 1 个，因此 preSumFreq.put(0,1);,这一点是必要且合理的。
     *  具体的例子是：nums = [3,....], k = 3, 和 nums = [1,1,1,...], k = 3.
     *
     *  官方题解：
     *    定义 pre[i] 为[0,i]里所有数的和，则 pre[i] 可以由 pre[i - 1]递推而来，即：
     *          pre[i] = pre[i - 1] + nums[i]
     *    那么 [j...i]这个子数组和为 k  这个条件我们转化为
     *                                      pre[i] - pre[j - 1] == k
     *    简单移项可得符合条件的下标为 j 需要满足
     *                                      pre[j - 1] == pre[i] - k
     *    考虑以 i 结尾的和为 k 的连续子数组个数时只要统计有多少个前缀和为 pre[i] - k 的 pre[j]即可。
     *    我们建立哈希表 mp，以和为键，出现次数为对应的值，记录 pre[i] 出现的次数，从左往右边更新mp边计算答案。
     *    则以 i 结尾的答案 mp[pre[i] - k] 即可在 O(1) 时间内得到。
     *    最后的答案即为所有下标结尾的和为 k 的子数组个数之和。
     * 复杂度分析：
     *      时间复杂度：O(N)，这里 N 是数组的长度；
     *      空间复杂度：O(N)。
     * @param nums
     * @param k
     * @return
     */
    public static int subarraySum(int[] nums, int k) {
        //key : 前缀和，value ：key 对应的前缀和的个数
        Map<Integer, Integer> preSumFreq = new HashMap<>();
        //对于下标为 0 的元素，前缀和为 0，个数为 1
        preSumFreq.put(0,1);
        int preSum = 0;
        int count = 0;
        for (int num : nums) {
            preSum += num;
            //先获得前缀和为preSum - k 的个数，加到计数变量里
            if (preSumFreq.containsKey(preSum - k)){
                count += preSumFreq.get(preSum - k);
            }
            //然后维护preSumFreq的定义
            preSumFreq.put(preSum,preSumFreq.getOrDefault(preSum, 0) + 1);
        }
        return count;
    }
}



























