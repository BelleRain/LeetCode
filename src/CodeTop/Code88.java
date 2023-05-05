package CodeTop;

/**
 * @author mxy
 * @create 2023-04-12 11:38
 */

import java.util.Arrays;

/**
 * 88. 合并两个有序数组     链接：https://leetcode.cn/problems/merge-sorted-array
 * 给你两个按 非递减顺序 排列的整数数组 nums1 和 nums2，另有两个整数 m 和 n ，分别表示 nums1 和 nums2 中的元素数目。
 * 请你 合并 nums2 到 nums1 中，使合并后的数组同样按 非递减顺序 排列。
 * 注意：最终，合并后数组不应由函数返回，而是存储在数组 nums1 中。为了应对这种情况，nums1 的初始长度为 m + n，
 * 其中前 m 个元素表示应合并的元素，后 n 个元素为 0 ，应忽略。nums2 的长度为 n 。
 *
 * 示例 1：
 * 输入：nums1 = [1,2,3,0,0,0], m = 3, nums2 = [2,5,6], n = 3
 * 输出：[1,2,2,3,5,6]
 * 解释：需要合并 [1,2,3] 和 [2,5,6] 。
 * 合并结果是 [1,2,2,3,5,6] ，其中斜体加粗标注的为 nums1 中的元素。
 *
 * 示例 2：
 * 输入：nums1 = [1], m = 1, nums2 = [], n = 0
 * 输出：[1]
 * 解释：需要合并 [1] 和 [] 。
 * 合并结果是 [1] 。
 *
 * 示例 3：
 * 输入：nums1 = [0], m = 0, nums2 = [1], n = 1
 * 输出：[1]
 * 解释：需要合并的数组是 [] 和 [1] 。
 * 合并结果是 [1] 。
 * 注意，因为 m = 0 ，所以 nums1 中没有元素。nums1 中仅存的 0 仅仅是为了确保合并结果可以顺利存放到 nums1 中。
 *
 * 提示：
 * nums1.length == m + n
 * nums2.length == n
 * 0 <= m, n <= 200
 * 1 <= m + n <= 200
 * -10^9 <= nums1[i], nums2[j] <= 10^9
 *
 * 进阶：你可以设计实现一个时间复杂度为 O(m + n) 的算法解决此问题吗？
 *
 */
public class Code88 {

    public static void main(String[] args) {
        //int[] nums1 = {1,2,3,0,0,0};
        //int[] nums2 = {2,5,6};
        int[] nums1 = {2,0};
        int[] nums2 = {1};
        //merge(nums1, 3, nums2, 3);
        //merge(nums1, 1, nums2, 1);
        merge1(nums1, 1, nums2, 1);
        System.out.println(Arrays.toString(nums1));
    }

    //从大到小遍历
    public static void merge1(int[] nums1, int m, int[] nums2, int n) {
        int tail = nums1.length - 1;
        int p1 = m - 1;
        int p2 = n - 1;
        while (p2 >= 0){
            if (p1 < 0 || nums1[p1] < nums2[p2]){
                nums1[tail--] = nums2[p2--];
            }else {
                nums1[tail--] = nums1[p1--];
            }
        }
    }


    /**
     * 从小到大遍历
     * 1、nums1.length = m + n
     *    nums2.length = n
     * @param nums1
     * @param m
     * @param nums2
     * @param n
     */
    public static void merge(int[] nums1, int m, int[] nums2, int n) {
        if (nums1.length == 0 && nums2.length == 0) return;
        //如果nums2为空，直接返回nums1
        if (m == nums1.length) return;
        //m == 0，则将 nums2 中的元素放入nums1，返回nums1
        if (m == 0){
            if (nums2.length == 0) return;
            for (int i = 0; i < nums2.length; i++) {
                nums1[i] = nums2[i];
            }
            return;
        }
        //合并数组
        int[] temp = new int[m + n];
        int i = 0,j = 0,k = 0;
        while (i < m && j < n){
            while (i < m && nums1[i] <= nums2[j]){
                temp[k] = nums1[i];
                k++;
                i++;
            }
            while (j < n && nums2[j] <= nums1[i]){
                temp[k] = nums2[j];
                k++;
                j++;
            }
        }

        while (i < m){
            temp[k++] = nums1[i++];
        }

        while (j < n){
            temp[k++] = nums2[j++];
        }

        for (int l = 0; l < temp.length; l++) {
            nums1[l] = temp[l];
        }
    }
}
