package Top100;

/**
 * @author mxy
 * @create 2022-11-28 10:34
 */

/**
 * 136. 只出现一次的数字  (类似题目：剑指 Offer56.1、Offer56.2)  链接：https://leetcode.cn/problems/single-number
 * 给你一个 非空 整数数组 nums ，除了某个元素只出现一次以外，其余每个元素均出现两次。找出那个只出现了一次的元素。
 *
 * 你必须设计并实现线性时间复杂度的算法来解决此问题，且该算法只使用常量额外空间。
 *
 * 示例 1 ：
 * 输入：nums = [2,2,1]
 * 输出：1
 *
 * 示例 2 ：
 * 输入：nums = [4,1,2,1,2]
 * 输出：4
 *
 * 示例 3 ：
 * 输入：nums = [1]
 * 输出：1
 *  
 * 提示：
 * 1 <= nums.length <= 3 * 10^4
 * -3 * 10^4 <= nums[i] <= 3 * 10^4
 * 除了某个元素只出现一次以外，其余每个元素均出现两次。
 */
public class Top136 {

    public static void main(String[] args) {
        Top136 top136 = new Top136();
        int[] nums = {4,1,2,1,2};
        System.out.println(top136.singleNumber(nums));
    }

    /**
     * 执行用时： 1 ms , 在所有 Java 提交中击败了 100.00% 的用户
     * 内存消耗： 41.3 MB , 在所有 Java 提交中击败了 72.83% 的用户
     * a XOR a = 0;
     * 0 XOR a = a;
     * 同时满足交换律 和 结合律
     * @param nums
     * @return
     */
    public int singleNumber(int[] nums) {
        int res = 0;
        for (int num : nums) {
            res ^= num;
        }
        return res;
    }
}
