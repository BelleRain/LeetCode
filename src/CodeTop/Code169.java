package CodeTop;

/**
 * @author mxy
 * @create 2023-05-05 14:26
 */

import java.util.Arrays;

/**
 * 169. 多数元素    链接：https://leetcode.cn/problems/majority-element
 * 给定一个大小为 n 的数组 nums ，返回其中的多数元素。多数元素是指在数组中出现次数 大于 ⌊ n/2 ⌋ 的元素。
 * 你可以假设数组是非空的，并且给定的数组总是存在多数元素。
 *
 * 示例 1：
 * 输入：nums = [3,2,3]
 * 输出：3
 *
 * 示例 2：
 * 输入：nums = [2,2,1,1,1,2,2]
 * 输出：2
 *
 * 提示：
 * n == nums.length
 * 1 <= n <= 5 * 10^4
 * -10^9 <= nums[i] <= 10^9
 *  
 * 进阶：尝试设计时间复杂度为 O(n)、空间复杂度为 O(1) 的算法解决此问题。
 *
 */
public class Code169 {

    public static void main(String[] args) {
        int[] nums = {2,2,1,1,1,2,2};
        System.out.println(majorityElement(nums));
    }

    public static int majorityElement1(int[] nums) {
        Arrays.sort(nums);
        int l = 0,r = 0, length = 0;
        while (r < nums.length - 1){
            while (r < nums.length - 1 && nums[r] == nums[r + 1]) r++;
            length = r - l + 1;
            if (length > nums.length / 2) return nums[r];
            r++;
            l = r;
        }
        return nums[r];
    }

    public static int majorityElement2(int[] nums) {
        Arrays.sort(nums);
        return nums[nums.length >> 1];
    }

    /**
     * 摩尔投票法：
     * @param nums
     * @return
     */
    public static int majorityElement(int[] nums) {
        int res = nums[0], count = 1;
        for (int num : nums) {
            if (num == res) count++;
            else {
                count--;
            }
            if (count == 0){
                res = num;
                count = 1;
            }
        }
        return res;
    }
}
































