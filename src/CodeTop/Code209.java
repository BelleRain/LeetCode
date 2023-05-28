package CodeTop;

/**
 * @author mxy
 * @create 2023-05-26 21:48
 */

import java.util.Arrays;

/**
 * 209. 长度最小的子数组    链接：https://leetcode.cn/problems/minimum-size-subarray-sum
 * 给定一个含有 n 个正整数的数组和一个正整数 target 。
 * 找出该数组中满足其和 ≥ target 的长度最小的 连续子数组 [numsl, numsl+1, ..., numsr-1, numsr] ，并返回其长度。
 * 如果不存在符合条件的子数组，返回 0 。
 *
 * 示例 1：
 * 输入：target = 7, nums = [2,3,1,2,4,3]
 * 输出：2
 * 解释：子数组 [4,3] 是该条件下的长度最小的子数组。
 *
 * 示例 2：
 * 输入：target = 4, nums = [1,4,4]
 * 输出：1
 *
 * 示例 3：
 * 输入：target = 11, nums = [1,1,1,1,1,1,1,1]
 * 输出：0
 *
 * 提示：
 * 1 <= target <= 10^9
 * 1 <= nums.length <= 10^5
 * 1 <= nums[i] <= 10^5
 *
 * 进阶：
 * 如果你已经实现 O(n) 时间复杂度的解法, 请尝试设计一个 O(n log(n)) 时间复杂度的解法。
 *
 */
public class Code209 {

    public static void main(String[] args) {
        int[] nums = {2,3,1,2,4,3};
        System.out.println(minSubArrayLen2(7, nums));
    }

    /**
     * 官方题解：https://leetcode.cn/problems/minimum-size-subarray-sum/solution/chang-du-zui-xiao-de-zi-shu-zu-by-leetcode-solutio/
     */

    /**
     * 解法一：暴力法
     *   使用两个 for 循环，一个 for 循环固定一个数字比如 m，另一个 for循环从 m 的下一个元素开始累加，
     *   当和大于等于 s 的时候终止内层循环，顺便记录下最小长度
     * @param target
     * @param nums
     * @return
     */
    public static int minSubArrayLen1(int target, int[] nums) {
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < nums.length; i++) {
            int sum = nums[i];
            if (sum >= target){
                return 1;
            }
            for (int j = i + 1; j < nums.length; j++) {
                sum += nums[j];
                if (sum >= target){
                    min = Math.min(min, j - i + 1);
                    break;
                }
            }
        }
        return min == Integer.MAX_VALUE ? 0 : min;
    }


    /**
     * 解法二：使用队列相加（实际上我们也可以把它称作是滑动窗口，这里的队列其实就相当于一个窗口）
     *    我们把数组中的元素不停的入队，直到总和大于等于 target 为止，接着记录下队列中元素的个数，
     *    然后再不停的出队，直到队列中元素的和小于 target 为止（如果不小于 target ，也要记录下队列
     *    中的元素的个数，这个个数其实就是不小于 target 的连续子数组长度，我们记录最小的即可）。
     *    接着再把数组中的元素添加到队列中 ... 重复上面的操作，直到数组中的元素全部使用完为止。
     * @param target
     * @param nums
     * @return
     */
    public static int minSubArrayLen2(int target, int[] nums) {
        int lo = 0, hi = 0, sum = 0, min = Integer.MAX_VALUE;
        while (hi < nums.length){
            sum += nums[hi++];
            while (sum >= target){
                // hi - lo 的原因：nums[hi++] 中，hi + 1
                min = Math.min(min, hi - lo);
                sum -= nums[lo++];
            }
        }
        return min == Integer.MAX_VALUE ? 0 : min;
    }


    /**
     * 解法三：使用队列相减
     * @param target
     * @param nums
     * @return
     */
    public static int minSubArrayLen3(int target, int[] nums) {
        int lo = 0, hi = 0, min = Integer.MAX_VALUE;
        while (hi < nums.length){
            target = target - nums[hi++];
            while (target <= 0){
                min = Math.min(min, hi - lo);
                target = target + nums[lo++];
            }
        }
        return min == Integer.MAX_VALUE ? 0 : min;
    }


    /**
     * 解法四：二分法查找
     *    我们申请一个临时数组sums，其中sums[i]表示的是原数组 nums 前 i 个元素的和，题中说了 “给定一个含有 n 个正整数的数组”，
     *    既然是正整数，那么相加的和会越来越大，也就是 sums 数组中的元素是递增的。我们只需要找到 sums[k] - sums[j] >= target,
     *    那么 k - j 就是满足的连续子数组，但不一定是最小的，所以要继续找，直到找到最小的为止。怎么找呢，我们可以使用两个for循环来枚举，
     *    但这又和第一种暴力求解一样了，所以我们可以换种思路，求 sums[k] - sums[j] >= target 我们可以求 sums[j] + target <= sums[k],
     *    那这样就好办了，因为数组 sums 中的元素是递增的，也就是排序的，我们只需要求出 sums[j] + target 的值，然后使用二分法查找即可找到这个 k。
     * @param target
     * @param nums
     * @return
     */
    public static int minSubArrayLen4(int target, int[] nums) {
        int length = nums.length;
        int min = Integer.MAX_VALUE;
        //前缀和
        int[] sums = new int[length + 1];
        for (int i = 1; i <= length; i++) {
            sums[i] = sums[i - 1] + nums[i - 1];
        }
        //sums[k] - sums[j] >= target,sums[j] + target <= sums[k]
        for (int i = 0; i <= length; i++) {
            int s = target + sums[i];
            // 函数 binarySearch ：找到就会返回值的下标，如果没找到就会返回一个负数，这个负数取反之后就是查找的值在数组中的位置。
            // 举个例子，比如排序数组 [2，5，7，10，15，18，20]
            // 如果我们查找 18，因为有这个数会返回 18 的下标 5，如果我们查找 9，因为没这个数会返回 -4
            //（至于这个是怎么得到的，大家可以看下源码，这里不再过多展开讨论），我们对他取反之后就是3，也就是说如果我们在数组中添加一个 9，
            // 他在数组的下标是 3，也就是第 4 个位置（也可以这么理解，只要取反之后不是数组的长度，那么他就是原数组中第一个比他大的值的下标）
            int index = Arrays.binarySearch(sums, s);
            if (index < 0){
                index = ~index;
            }
            if (index <= length){
                min = Math.min(min, index - i);
            }
        }
        return min == Integer.MAX_VALUE ? 0 : min;
    }


    /**
     * 解法五：直接使用窗口
     *    先固定一个窗口大小比如 length，然后遍历数组，查看在数组中的 length 个元素长度的和是否有满足的，
     *    如果没有，则扩大窗口的大小继续查找，如果有满足的就记录下窗口的大小 length，因为这个length不一定是最小的，
     *    则缩小窗口的大小再继续找。。。。
     * @param target
     * @param nums
     * @return
     */
    public static int minSubArrayLen5(int target, int[] nums) {
        int lo = 1, hi = nums.length, min = 0;
        while (lo <= hi){
            int mid = (lo + hi) >> 1;
            if (windowExist(mid,nums,target)){
                hi = mid - 1; //找到就缩小窗口的大小
                min = mid; //如果找到就记录下来
            }else {
                lo = mid + 1; //没找到就扩大窗口的大小
            }
        }
        return min;
    }

    //size窗口的大小
    private static boolean windowExist(int size, int[] nums, int target) {
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            if (i >= size){
                sum -= nums[i - size];
            }
            sum += nums[i];
            if (sum >= target){
                return true;
            }
        }
        return false;
    }

}








































