package CodeTop;

/**
 * @author mxy
 * @create 2023-03-14 10:32
 */

/**
 * 162. 寻找峰值   链接：https://leetcode.cn/problems/find-peak-element
 * 峰值元素是指其值严格大于左右相邻值的元素。
 * 给你一个整数数组 nums，找到峰值元素并返回其索引。数组可能包含多个峰值，在这种情况下，返回 任何一个峰值 所在位置即可。
 * 你可以假设 nums[-1] = nums[n] = -∞ 。
 * 你必须实现时间复杂度为 O(log n) 的算法来解决此问题。
 *
 * 示例 1：
 * 输入：nums = [1,2,3,1]
 * 输出：2
 * 解释：3 是峰值元素，你的函数应该返回其索引 2。
 *0
 * 示例 2：
 * 输入：nums = [1,2,1,3,5,6,4]
 * 输出：1 或 5
 *
 * 解释：你的函数可以返回索引 1，其峰值元素为 2；
 *      或者返回索引 5， 其峰值元素为 6。
 *
 * 提示：
 * 1 <= nums.length <= 1000
 * -2^31 <= nums[i] <= 2^31 - 1
 * 对于所有有效的 i 都有 nums[i] != nums[i + 1]
 */
public class Code162 {

    public static void main(String[] args) {
        Code162 code162 = new Code162();
        int[] nums = {1,2,1,3,5,6,4};
        System.out.println(code162.findPeakElement(nums));
    }

    /**
     * 爬坡法：由于两边都是 -∞，所以上坡的地方，一定有山峰；而下坡的地方，可能一直是下坡
     *        故，一直向上坡的方向走
     * @param nums
     * @return
     */
    public int findPeakElement(int[] nums) {
        if (nums.length == 0) return -1;
        int left = 0;
        int right = nums.length - 1;
        while (left < right){
            int mid = (left + right)/2;
            if (nums[mid] > nums[mid + 1]) right = mid;
            else left = mid + 1;
        }
        return left;
    }


}
