package Top100;

/**
 * @author mxy
 * @create 2022-11-18 14:21
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 34. 在排序数组中查找元素的第一个和最后一个位置  链接：https://leetcode.cn/problems/find-first-and-last-position-of-element-in-sorted-array
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
 * 0 <= nums.length <= 10^5
 * -10^9 <= nums[i] <= 10^9
 * nums 是一个非递减数组
 * -10^9 <= target <= 10^9
 */
public class Top34 {

    public static void main(String[] args) {
        Top34 top34 = new Top34();
        //int[] nums = {5,7,7,8,8,10};
        //int[] nums = {2,2};
        //int[] nums = {1,3};
        int[] nums = {3,3,3};
        System.out.println(Arrays.toString(top34.searchRange(nums, 3)));
    }

    /**
     * 执行用时： 0 ms , 在所有 Java 提交中击败了 100.00% 的用户
     * 内存消耗： 44.5 MB , 在所有 Java 提交中击败了 63.45%
     * @param nums
     * @param target
     * @return
     */
    public int[] searchRange(int[] nums, int target) {
        int[] res = new int[2]; //存储起点和终点
        int n = nums.length;
        if (n == 0) return new int[]{-1,-1};
        if (n == 1) return nums[0] == target ? new int[]{0,0} : new int[]{-1,-1};
        int left = 0, right = n - 1;
        while (left <= right){
            int mid = (left + right)/2;
            if (target < nums[mid]) right = mid - 1;
            else if (target > nums[mid]) left = mid + 1;
            else {
                int leftTemp = mid - 1;
                while (leftTemp >= 0 && nums[leftTemp] == target){ //向左查找并跳过相同的值
                    leftTemp--;
                }
                res[0] = ++leftTemp; //加入起点
                int rightTemp = mid + 1;
                while (rightTemp <= n - 1 && nums[rightTemp] == target){ //向右查找并跳过相同的值
                    rightTemp++;
                }
                res[1] = --rightTemp; //加入终点
                return res;
            }
        }
        return new int[]{-1,-1};
    }


    /**
     * 题解链接：https://leetcode.cn/problems/find-first-and-last-position-of-element-in-sorted-array/solution/zai-pai-xu-shu-zu-zhong-cha-zhao-yuan-su-de-di-3-4/
     * 方法一：二分查找
     * 二分查找中，寻找 leftIdx 即为在数组中寻找 第一个 大于等于 target 的下标，寻找 rightIdx 即为在数组中寻找 第一个 大于 target 的下标，然后将下标减一。
     * @param nums
     * @param target
     * @return
     */
    public int[] searchRange1(int[] nums, int target) {
        int leftIdx = binarySearch(nums, target, true);
        int rightIdx = binarySearch(nums, target, false) - 1;
        if (leftIdx <= rightIdx && rightIdx < nums.length && nums[leftIdx] == target && nums[rightIdx] == target){
            return new int[]{leftIdx,rightIdx};
        }
        return new int[]{-1,-1};
    }

    //lower为true，则查找 第一个 大于等于 target 的下标，
    //否则查找 第一个 大于 target 的下标。
    public int binarySearch(int[] nums,int target,boolean lower){
        int left = 0,right = nums.length - 1,ans = nums.length;
        while (left <= right){ //因为是寻找第一个，所以找到后不立即返回，而是继续寻找第一个
            int mid = (left + right)/2;
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
