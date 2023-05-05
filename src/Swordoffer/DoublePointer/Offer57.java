package Swordoffer.DoublePointer;

/**
 * @author mxy
 * @create 2022-09-28 9:24
 */

/**
 * 和为s的两个数字
 * 输入一个递增排序的数组和一个数字s，在数组中查找两个数，使得它们的和正好是s。如果有多对数字的和等于s，则输出任意一对即可。
 *
 * 示例 1：
 * 输入：nums = [2,7,11,15], target = 9
 * 输出：[2,7] 或者
 *
 * 示例 2：
 * 输入：nums = [10,26,30,31,47,60], target = 40
 * 输出：[10,30] 或者 [30,10]
 */
public class Offer57 {

    //该题解原理同 Offer04

    /**
     * nums[]:递增数组
     * S(i,j) :即 S(i, j) = nums[i] + nums[j] 。假设 S(i, j) < target ，则执行 i = i + 1，即状态切换至 S(i + 1, j)
     * s(0,1) s(0,2) s(0,3) s(0,4) s(0,5)
     *        s(1,2) s(1,3) s(1,4) s(1,5)
     *               s(2,3) s(2,4) s(2,5)
     *                      s(3,4) s(3,5)
     *                             s(4,5)
     */

    /**
     * 算法流程： 原文链接： https://leetcode.cn/problems/he-wei-sde-liang-ge-shu-zi-lcof/solution/mian-shi-ti-57-he-wei-s-de-liang-ge-shu-zi-shuang-/
     *    1、初始化： 双指针 i , j 分别指向数组 nums 的左右两端 （俗称对撞双指针）。
     *    2、循环搜索： 当双指针相遇时跳出；
     *         1、计算和 s = nums[i] + nums[j] ；
     *         2、若 s > target ，则指针 j 向左移动，即执行 j = j - 1 ；
     *         3、若 s < target ，则指针 i 向右移动，即执行 i = i + 1 ；
     *         4、若 s = target ，立即返回数组 [nums[i], nums[j]] ；
     *    3、返回空数组，代表无和为 target 的数字组合。
     *复杂度分析：
     *   时间复杂度 O(N) ： N 为数组 nums 的长度；双指针共同线性遍历整个数组。
     *   空间复杂度 O(1) ： 变量 i, j 使用常数大小的额外空间。
     *  @param nums
     * @param target
     * @return
     */
    public int[] twoSum(int[] nums, int target) {
        int i = 0,j = nums.length-1;
        while (i < j){
            int s = nums[i] + nums[j];
            if (s < target) i++;
            else if(s > target) j--;
            else return new int[]{nums[i],nums[j]};
        }
        return new int[0];
    }

    //public int[] twoSum(int[] nums, int target) {
    //    int length = nums.length;
    //    if(length == 0 || length == 1) return nums;
    //    int left = 0, right = length - 1;
    //    while(left < right){
    //        if(nums[left] + nums[right] > target){
    //            right --;
    //        }else if(nums[left] + nums[right] < target){
    //            left ++;
    //        }else{
    //            break;
    //        }
    //    }
    //    return new int[]{nums[left], nums[right]};
    //}
}






































