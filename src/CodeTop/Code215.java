package CodeTop;

/**
 * @author mxy
 * @create 2023-04-11 8:21
 */

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * 215. 数组中的第K个最大元素     链接：https://leetcode.cn/problems/kth-largest-element-in-an-array
 * 给定整数数组 nums 和整数 k，请返回数组中第 k 个最大的元素。
 * 请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。
 * 你必须设计并实现时间复杂度为 O(n) 的算法解决此问题。
 *
 * 示例 1:
 * 输入: [3,2,1,5,6,4], k = 2
 * 输出: 5
 *
 * 示例 2:
 * 输入: [3,2,3,1,2,4,5,5,6], k = 4
 * 输出: 4
 *  
 *
 * 提示：
 * 1 <= k <= nums.length <= 10^5
 * -10^4 <= nums[i] <= 10^4
 *
 */
public class Code215 {

    public static void main(String[] args) {
        int[] nums = {3,2,3,1,2,4,5,5,6};
        //int[] nums = {3,2,1,5,6,4};
        //int[] nums = {-3,2,0};
        System.out.println(findKthLargest1(nums, 4));
        System.out.println(findKthLargest2(nums, 4));
        System.out.println(nums[2] > nums[0]);
        System.out.println(nums[0] < nums[2]);
        System.out.println(nums[2] < nums[0]);
        int a = -3;
        int b = -1;
        System.out.println(a < b);
    }


    /**
     * 快速排序
     * @param nums
     * @param k
     * @return
     */
    public static int findKthLargest1(int[] nums, int k) {
        quickSort(nums,k,0,nums.length - 1);
        return nums[k - 1];
    }

    //在i处截断，当 i = k 时，代表 第 k + 1 个元素，故不可 令 i 与 k 作比
    //当 i = k - 1 时，代表 第 k 个 元素
    private static void quickSort(int[] nums, int k, int left, int right) {
        //子数组长度为1时终止递归
        if (left >= right) return;
        int i = left , j = right;
        while (i < j){
            while (nums[j] <= nums[left] && i < j) j--;
            while (nums[i] >= nums[left] && i < j) i++;
            swap(nums, i, j);
        }
        swap(nums, i, left);
        if (i > k - 1) {
            quickSort(nums, k, left, i - 1); //说明k在左数组
            return;
        }
        if (i < k - 1) {
            quickSort(nums, k, i + 1, right); //说明k在右数组
            return;
        }
    }

    public static void swap(int[] nums,int i,int j){
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }


    /**
     * 堆排序
     * @param nums
     * @param k
     * @return
     */
    public static int findKthLargest2(int[] nums, int k) {
        //构造小顶堆
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        for (int num : nums) {
            if (queue.size() == k){
                if (queue.peek() < num){
                    queue.poll();
                }else {
                    continue;
                }
            }
            queue.offer(num);
        }
        return queue.poll();
    }


}








































