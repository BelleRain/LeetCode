package Swordoffer.SearchAlgorithms;

/**
 * @author mxy
 * @create 2022-09-19 7:57
 */

/**
 * 0～n-1中缺失的数字
 * 一个 长度为n-1 的 递增排序数组 中的所有数字都是 唯一的，并且每个数字都在范围0～n-1之内。
 * 在范围0～n-1内的n个数字中有且只有一个数字不在该数组中，请找出这个数字。
 * 输入: [0,1,3]
 * 输出: 2
 */
public class Offer53_2 {

    public static void main(String[] args) {
        //int[] nums = {0,1,2,3};
        int[] nums = {0,2,3};
        Offer53_2 offer = new Offer53_2();
        System.out.println(offer.missingNumber(nums));
    }

    /**该题关键：梳理好索引和元素的对应关系
     * 解法一：
     * 行用时： 0 ms , 在所有 Java 提交中击败了 100.00% 的用户
     * 内存消耗： 42 MB , 在所有 Java 提交中击败了 80.46% 的用户
     * @param nums
     * @return
     */
    //public int missingNumber(int[] nums) {
    //    int res = 0;
    //    //缺失0
    //    if (nums[0] - 0 == 1){
    //        return res;
    //    }
    //    //缺失的数字在中间
    //    for (int i = 0; i < nums.length; i++) {
    //        if (nums[i] - i == 1){
    //            res = i;
    //            break;
    //        }
    //    }
    //    //缺失的数字在最后
    //    if (res == 0){
    //        res = nums.length;
    //    }
    //    return res;
    //}

    /**
     * 解法二：二分查找最佳
     *
     * 解题思路：
     *   1、按照规则，数组可分为两部分：
     *    左子数组：nums[i] = i
     *    右子数组：nums[i] != i
     *  2、缺失的数字等于 “右子数组的首位元素”对应的索引，故使用二分法查找 右子数组的首位元素
     */

    public int missingNumber(int[] nums){
        int i=0,j=nums.length-1;
        while (i<=j){
            int m = (i+j)/2;
            if (nums[m] == m){  //说明在右边
                i = m+1;
            }
            else { //说明在左边，向左移动
                j=m-1;
            }
        }
        return i;
    }
}
