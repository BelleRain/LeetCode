package CodeTop;

/**
 * @author mxy
 * @create 2023-04-23 11:28
 */

import java.util.*;

/**
 * 41. 缺失的第一个正数     链接：https://leetcode.cn/problems/first-missing-positive
 * 给你一个未排序的整数数组 nums ，请你找出其中没有出现的最小的正整数。
 * 请你实现时间复杂度为 O(n) 并且只使用常数级别额外空间的解决方案。
 *
 * 示例 1：
 * 输入：nums = [1,2,0]
 * 输出：3
 *
 * 示例 2：
 * 输入：nums = [3,4,-1,1]
 * 输出：2
 *
 * 示例 3：
 * 输入：nums = [7,8,9,11,12]
 * 输出：1
 *
 * 提示：
 * 1 <= nums.length <= 5 * 10^5
 * -2^31 <= nums[i] <= 2^31 - 1
 *
 */
public class Code41 {

    public static void main(String[] args) {
        int[] nums = {3,4,-1,1};
        //int[] nums = {7,8,9,11,12};
        //int[] nums = {1,2,0};
        System.out.println(firstMissingPositive1(nums));
        System.out.println(firstMissingPositive3(nums));
    }

    /**
     * 题解链接：https://leetcode.cn/problems/first-missing-positive/solution/tong-pai-xu-python-dai-ma-by-liweiwei1419/
     */

    /**
     * 哈希表
     * @param nums
     * @return
     */
    public static int firstMissingPositive1(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] <= 0) continue;
            set.add(nums[i]);
        }
        for (int i = 1; i <= nums.length; i++) {
            if (!set.contains(i)){
                return i;
            }
        }
        return nums.length + 1;
    }

    /**
     * 利用数组作哈希:
     * 执行用时： 1 ms , 在所有 Java 提交中击败了 96.85% 的用户
     * 内存消耗： 49.5 MB , 在所有 Java 提交中击败了 56.69% 的用户
     * @param nums
     * @return
     */
    public static int firstMissingPositiveArray(int[] nums) {
        boolean[] status = new boolean[nums.length + 1];
        for (int num : nums) {
            if (num >= 1 && num <= nums.length){
                status[num] = true;
            }
        }
        for (int i = 1; i <= nums.length; i++) {
            if (status[i] == false){
                return i;
            }
        }
        return nums.length + 1;
    }

    /**
     * 二分查找
     * @param nums
     * @return
     */
    public static int firstMissingPositive2(int[] nums) {
        Arrays.sort(nums);
        for (int i = 1; i <= nums.length; i++) {
            if (!searchBinary(nums, i)){
                return i;
            }
        }
        return nums.length + 1;
    }

    public static boolean searchBinary(int[] nums,int target){
        int l = 0, r = nums.length - 1;
        while (l <= r){
            int mid = l + (r - l)/2;
            if (nums[mid] == target) return true;
            else if (nums[mid] < target) l = mid + 1;
            else r = mid - 1;
        }
        return false;
    }

    /**
     * 原地哈希：用数组作哈希
     * @param nums
     * @return
     */
    public static int firstMissingPositive3(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            while (nums[i] > 0 && nums[i] <= nums.length && nums[nums[i] - 1] != nums[i]){
                swap(nums, i, nums[i] - 1);
            }
        }
        for (int i = 0; i < nums.length; i++) {
            if (i + 1 != nums[i]){
                return i + 1;
            }
        }
        return nums.length + 1;
    }

    public static void swap(int[] nums, int i, int j){
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
































