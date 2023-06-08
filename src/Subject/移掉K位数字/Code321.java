package Subject.移掉K位数字;

/**
 * @author mxy
 * @create 2023-05-30 22:29
 */

import java.util.Arrays;

/**
 * 321. 拼接最大数   链接：https://leetcode.cn/problems/create-maximum-number
 * 给定长度分别为 m 和 n 的两个数组，其元素由 0-9 构成，表示两个自然数各位上的数字。
 * 现在从这两个数组中选出 k (k <= m + n) 个数字拼接成一个新的数，要求从同一个数组中取出的数字保持其在原数组中的相对顺序。
 * 求满足该条件的最大数。结果返回一个表示该最大数的长度为 k 的数组。
 *
 * 说明: 请尽可能地优化你算法的时间和空间复杂度。
 *
 * 示例 1:
 * 输入:
 * nums1 = [3, 4, 6, 5]
 * nums2 = [9, 1, 2, 5, 8, 3]
 * k = 5
 * 输出:
 * [9, 8, 6, 5, 3]
 *
 * 示例 2:
 * 输入:
 * nums1 = [6, 7]
 * nums2 = [6, 0, 4]
 * k = 5
 * 输出:
 * [6, 7, 6, 0, 4]
 *
 * 示例 3:
 * 输入:
 * nums1 = [3, 9]
 * nums2 = [8, 9]
 * k = 3
 * 输出:
 * [9, 8, 9]
 *
 */
public class Code321 {

    public static void main(String[] args) {

    }

    public static int[] maxNumber(int[] nums1, int[] nums2, int k) {
        int m = 0, n = 0;
        if (nums1 == null || (m = nums1.length) == 0) {
            return maxNumber(nums2,k);
        }
        if (nums2 == null || (n = nums2.length) == 0) {
            return maxNumber(nums1, k);
        }

        /*
           0 <= i <= m
           0 <= k - i <= n  ==> k - n <= i <= k
           ==> Math.max(0, k - n) <= i <= Math.min(m,k)
         */
        int[] res = null;
        for (int i = Math.max(0, k - n), limit = Math.min(m, k); i <= limit; i++) {
            int[] a = maxNumber(nums1, i);
            int[] b = maxNumber(nums2, k - i);
            int[] c = merge(a,b);
            res = res == null || compare(res, 0, c, 0) < 0 ? c : res;
        }
        return res;
    }


    /**
     * 比较 nums1[i,m) 和 nums2[j,n)
     * nums1 != null && nums2 != null
     * @param nums1
     * @param i
     * @param nums2
     * @param j
     * @return
     */
    private static int compare(int[] nums1, int i, int[] nums2, int j) {
        int m = nums1.length;
        int n = nums2.length;
        for (int k = 0, limit = Math.min(m - i, n - j); k < limit; k++) {
            if (nums1[i + k] != nums2[j + k]) {
                return Integer.compare(nums1[i + k], nums2[j + k]);
            }
        }
        return Integer.compare(m - i, n - j);
    }

    /**
     * 合并两个数组 成 最大值
     * nums1 != null && nums2 != null
     * @param nums1
     * @param nums2
     * @return
     */
    private static int[] merge(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;

        int[] res = new int[m + n];
        int i = 0, j = 0, k = 0;
        while(i < m && j < n){
            res[k++] = compare(nums1, i, nums2, j) >= 0 ? nums1[i++] : nums2[j++];
        }
        while (i < m){
            res[k++] = nums1[i++];
        }
        while (j < n){
            res[k++] = nums2[j++];
        }
        return res;
    }


    /**
     * 单调栈 求 nums 中 长度 为 k 的最大数
     * 问题转化为 移除 nums 中 n-k 个元素
     * nums ！= null   0 <= k <= nums.length
     * @param nums
     * @param k
     * @return
     */
    private static int[] maxNumber(int[] nums, int k) {
        int n = nums.length;
        // 移除 n - k 个
        k = n - k;
        if (k == 0) return nums.clone();
        if (k == n) return new int[0];
        int[] stack = new int[n];
        int top = 0;
        for (int i = 0; i < n; i++) {
            //如果堆栈为非空，且当前元素比栈顶元素大，移除栈顶元素
            //top - 1 处为栈顶元素
            while (k > 0 && top > 0 && nums[i] > stack[top - 1]){
                top--;
                k--;
            }
            //当前元素入栈
            stack[top++] = nums[i];
        }
        //如果 k > 0，再移除 k 个 栈顶元素
        top -= k;
        //[0,top) 前闭后开
        return Arrays.copyOfRange(stack, 0, top);
    }


}

































