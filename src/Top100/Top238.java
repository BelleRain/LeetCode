package Top100;

/**
 * @author mxy
 * @create 2022-12-15 9:14
 */

import java.util.Arrays;

/**
 * 238. 除自身以外数组的乘积   (同剑指 Offer66)   链接：https://leetcode.cn/problems/product-of-array-except-self
 * 给你一个整数数组 nums，返回 数组 answer ，其中 answer[i] 等于 nums 中除 nums[i] 之外其余各元素的乘积 。
 * 题目数据 保证 数组 nums之中任意元素的全部前缀元素和后缀的乘积都在  32 位 整数范围内。
 *
 * 请不要使用除法，且在 O(n) 时间复杂度内完成此题。
 *
 * 示例 1:
 * 输入: nums = [1,2,3,4]
 * 输出: [24,12,8,6]
 *
 * 示例 2:
 * 输入: nums = [-1,1,0,-3,3]
 * 输出: [0,0,9,0,0]
 *  
 *
 * 提示：
 * 2 <= nums.length <= 10^5
 * -30 <= nums[i] <= 30
 * 保证 数组 nums之中任意元素的全部前缀元素和后缀的乘积都在  32 位 整数范围内
 * 进阶：你可以在 O(1) 的额外空间复杂度内完成这个题目吗？（ 出于对空间复杂度分析的目的，输出数组不被视为额外空间。）
 *
 */
public class Top238 {

    public static void main(String[] args) {
        Top238 top238 = new Top238();
        int[] nums = {1,2,3,4};
        System.out.println(Arrays.toString(top238.productExceptSelf(nums)));
    }

    /**
     * 题解链接：https://leetcode.cn/problems/gou-jian-cheng-ji-shu-zu-lcof/solution/mian-shi-ti-66-gou-jian-cheng-ji-shu-zu-biao-ge-fe/
     * 方法一： 正序 + 逆序 遍历数组（左右乘积列表，空间复杂度为 O(1) ） （上三角/下三角）
     * 复杂度分析：
     * 时间复杂度 O(N) ： 其中 N 为数组长度，两轮遍历数组 a ，使用 O(N) 时间。
     * 空间复杂度 O(1) ： 变量 tmp 使用常数大小额外空间（数组 b 作为返回值，不计入复杂度考虑）。
     * @param nums
     * @return
     */
    public int[] productExceptSelf(int[] nums) {
        int len = nums.length;
        if (len == 0) return new int[0];
        int[] b = new int[len];
        b[0] = 1;
        for (int i = 1; i < len; i++) {
            b[i] = b[i-1] * nums[i - 1];
        }
        int tmp = 1;
        for (int i = len - 2; i >= 0; i--) {
            tmp *= nums[i + 1];
            b[i] = b[i] * tmp;
        }
        return b;
    }

    /**
     * 题解链接：https://leetcode.cn/problems/gou-jian-cheng-ji-shu-zu-lcof/solution/gou-jian-cheng-ji-shu-zu-by-leetcode-sol-aqg2/
     */

    /**
     * 方法二：左右乘积列表
     * 复杂度分析
     * 时间复杂度：O(N)，其中 N 指的是数组 a 的大小。预处理 L 和 R 数组以及最后的遍历计算都是 O(N) 的时间复杂度。
     * 空间复杂度：O(N)，其中 N 指的是数组 a 的大小。使用了 L 和 R 数组去构造答案，L 和 R 数组的长度为数组 a 的大小。
     * @param nums
     * @return
     */
    /*public int[] productExceptSelf(int[] nums) {
        int[] res = new int[nums.length];
        int len = nums.length;
        if (len == 0) return new int[0];
        int[] L = new int[len];
        int[] R = new int[len];
        L[0] = 1;
        for (int i = 1; i < len; i++) {
            L[i] = L[i - 1] * nums[i - 1];
        }
        R[len - 1] = 1;
        for (int i = len - 2; i >= 0; i--) {
            R[i] = R[i + 1] * nums[i + 1];
        }
        for (int i = 0; i < len; i++) {
            res[i] = L[i] * R[i];
        }
        return res;
    }*/
}
