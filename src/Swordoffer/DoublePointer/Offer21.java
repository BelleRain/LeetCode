package Swordoffer.DoublePointer;

/**
 * @author mxy
 * @create 2022-09-28 7:58
 */

import java.util.Arrays;

/**
 * Offer21 调整数组顺序使奇数位于偶数前面
 * 输入一个整数数组，实现一个函数来调整该数组中数字的顺序，使得所有奇数在数组的前半部分，所有偶数在数组的后半部分。
 * 示例：
 * 输入：nums = [1,2,3,4]
 * 输出：[1,3,2,4]
 * 注：[3,1,2,4] 也是正确的答案之一。
 *
 */
public class Offer21 {
    public static void main(String[] args) {
        Offer21 offer = new Offer21();
        //int[] nums = {1,2,3,4};
        int[] nums = {1,2,3,5};
        System.out.println(Arrays.toString(offer.exchange(nums)));
    }


    /**
     * 解法一：两次遍历 原文链接：https://leetcode.cn/problems/diao-zheng-shu-zu-shun-xu-shi-qi-shu-wei-yu-ou-shu-qian-mian-lcof/solution/diao-zheng-shu-zu-shun-xu-shi-qi-shu-wei-en35/
     * 复杂度分析  时间复杂度：O(n)，其中 nn 为数组 nums 的长度。需遍历 nums 两次。
     * 空间复杂度：O(1)。结果不计入空间复杂度。
     * @param nums
     * @return
     */
    public int[] exchange1(int[] nums) {
        int n = nums.length, index = 0;
        int[] res = new int[n];
        for (int num : nums) {
            if (num % 2 == 1) {
                res[index++] = num;
            }
        }
        for (int num : nums) {
            if (num % 2 == 0) {
                res[index++] = num;
            }
        }
        return res;
    }

    /**
     * 解法二：双指针 + 一次遍历 原文链接：https://leetcode.cn/problems/diao-zheng-shu-zu-shun-xu-shi-qi-shu-wei-yu-ou-shu-qian-mian-lcof/solution/diao-zheng-shu-zu-shun-xu-shi-qi-shu-wei-en35/
     * 思路：
     *  记数组 nums 的长度为 n。方法一需要遍历两次 nums，第一次遍历时遇到偶数会跳过，第二次遍历时遇到奇数会跳过，这部分可以优化。
     * 新建一个长度为 n 的数组 res 用来保存调整完成的数组。遍历一遍 nums，遇到奇数则从 res 左侧开始替换元素，遇到偶数则从res 右侧开始替换元素。
     * 遍历完成后，res 就保存了调整完成的数组。
     * 复杂度分析 ：
     * 时间复杂度：O(n)，其中 n 为数组 nums 的长度。只需遍历 nums 一次。
     * 空间复杂度：O(1)。结果不计入空间复杂度。
     * @param nums
     * @return
     */
    public int[] exchange2(int[] nums) {
        int n = nums.length;
        int[] res = new int[n];
        int left = 0, right = n - 1;
        for (int num : nums) {
            if (num % 2 == 1) {
                res[left++] = num;
            } else {
                res[right--] = num;
            }
        }
        return res;
    }

    /**
     * 方法三：原地交换 原文链接：https://leetcode.cn/problems/diao-zheng-shu-zu-shun-xu-shi-qi-shu-wei-yu-ou-shu-qian-mian-lcof/solution/diao-zheng-shu-zu-shun-xu-shi-qi-shu-wei-en35/
     * 思路
     *   记数组 nums 的长度为 n。先从 nums 左侧开始遍历，如果遇到的是奇数，就表示这个元素已经调整完成了，继续从左往右遍历，
     *   直到遇到一个偶数。然后从 nums 右侧开始遍历，如果遇到的是偶数，就表示这个元素已经调整完成了，继续从右往左遍历，直到遇到一个奇数。
     *   交换这个偶数和奇数的位置，并且重复两边的遍历，直到在中间相遇，nums 调整完成。
     * 复杂度分析：
     *   时间复杂度：O(n)：原数组中每个元素只遍历一次。
     *   空间复杂度：O(1)：原地调整，只消耗常数空间。
     */
    public int[] exchange(int[] nums) {
        int left = 0, right = nums.length - 1;
        while (left < right) {
            while (left < right && nums[left] % 2 == 1) {
                left++;
            }
            while (left < right && nums[right] % 2 == 0) {
                right--;
            }
            if (left < right) {
                int temp = nums[left];
                nums[left] = nums[right];
                nums[right] = temp;
                left++;
                right--;
            }
        }
        return nums;
    }
}
