package CodeTop;

/**
 * @author mxy
 * @create 2023-05-06 12:29
 */

import java.util.Arrays;

/**
 * 34. 在排序数组中查找元素的第一个和最后一个位置    链接：https://leetcode.cn/problems/find-first-and-last-position-of-element-in-sorted-array
 * 给你一个按照非递减顺序排列的整数数组 nums，和一个目标值 target。请你找出给定目标值在数组中的开始位置和结束位置。
 * 如果数组中不存在目标值 target，返回 [-1, -1]。
 * 你必须设计并实现时间复杂度为 O(log n) 的算法解决此问题。
 *
 * 示例 1：
 * 输入：nums = [5,7,7,8,8,10], target = 8
 * 输出：[3,4]
 *
 * 示例 2：
 * 输入：nums = [5,7,7,8,8,10], target = 6
 * 输出：[-1,-1]
 *
 * 示例 3：
 * 输入：nums = [], target = 0
 * 输出：[-1,-1]
 *
 * 提示：
 *
 * 0 <= nums.length <= 10^5
 * -10^9 <= nums[i] <= 10^9
 * nums 是一个非递减数组
 * -10^9 <= target <= 10^9
 *
 */
public class Code34 {

    public static void main(String[] args) {
        int[] nums = {5,7,7,8,8,10};
        System.out.println(Arrays.toString(searchRange(nums, 8)));
    }


    /**
     * 二分查找：
     * @param nums
     * @param target
     * @return
     */
    public static int[] searchRange1(int[] nums, int target) {
        if (nums.length == 0) return new int[]{-1,-1};
        int[] res = new int[]{-1,-1};
        int l = 0, r = nums.length - 1;
        while (l <= r){
            int mid = (l + r) / 2;
            if (nums[mid] < target) l = mid + 1;
            else if (nums[mid] > target) r = mid - 1;
            else {
                int left = mid, right = mid;
                while (left >= 0 && nums[left] == target) left--;
                res[0] = left + 1;
                while (right <= nums.length - 1 && nums[right] == target) right++;
                res[1] = right - 1;
                return res;
            }
        }
        return res;
    }


    /**
     * leftIndex：左边第一个 大于等于 target的值，rightIndex：右边第一个大于target的值 - 1
     * 当 lower 为 true 时，binarySearch 寻找左边第一个 大于等于 target 的值；如果 nums[mid]>=target,则一直向左寻找
     * 当 lower 为 false 时，binarySearch 寻找右边第一个大于 target的值
     * @param nums
     * @param target
     * @return
     */
    public static int[] searchRange(int[] nums, int target) {
        int leftIndex = binarySearch(nums,target,true);
        int rightIndex = binarySearch(nums,target,false) - 1;
        if (leftIndex <= rightIndex && leftIndex >= 0 && rightIndex < nums.length && nums[leftIndex] == target && nums[rightIndex] == target){
            return new int[]{leftIndex,rightIndex};
        }
        return new int[]{-1,-1};
    }

    private static int binarySearch(int[] nums, int target, boolean lower) {
        int left = 0, right = nums.length - 1, ans = nums.length;
        while (left <= right){
            int mid = (left + right) / 2;
            if (nums[mid] > target || (lower && nums[mid] >= target)){
                right = mid - 1;
                ans = mid;
            }else {
                left = mid + 1;
            }
        }
        return ans;
    }
}







































