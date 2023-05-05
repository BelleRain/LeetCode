package CodeTop;

/**
 * @author mxy
 * @create 2023-04-11 15:19
 */

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;


/**
 * 912. 排序数组    链接：https://leetcode.cn/problems/sort-an-array
 * 给你一个整数数组 nums，请你将该数组升序排列。
 *
 * 示例 1：
 * 输入：nums = [5,2,3,1]
 * 输出：[1,2,3,5]
 *
 * 示例 2：
 * 输入：nums = [5,1,1,2,0,0]
 * 输出：[0,0,1,1,2,5]
 *  
 *
 * 提示：
 * 1 <= nums.length <= 5 * 10^4
 * -5 * 10^4 <= nums[i] <= 5 * 10^4
 *
 */
public class Code912 {

    public static void main(String[] args) {
        int[] nums = {5,1,1,2,0,0};
        System.out.println(Arrays.toString(sortArray2(nums)));
        System.out.println(Arrays.toString(sortArray1(nums)));
    }

    /**
     * 桶排序
     * @param nums
     * @return
     */
    public static int[] sortArray1(int[] nums) {
        int max = nums[0], min = nums[0];
        //求出数组 最大值 和 最小值
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > max) max = nums[i];
            if (nums[i] < min) min = nums[i];
        }
        //构建 max - min + 1 个桶
        int[] temp = new int[max - min + 1];
        //将 数字 的个数 放入 对应 大小的桶中，若出现相同数字，则 桶位置 > 1
        //nums[i] - min 越大，nums[i] 越大
        for (int i = 0; i < nums.length; i++) {
            temp[nums[i] - min]++;
        }
        int j = nums.length - 1;
        //从最大的桶开始统计
        for (int i = max - min; i >= 0;i--) {
            int n = temp[i];
            while (n > 0) {
                //temp 数组的下标 ： i = nums[j] - min
                //所以该位置的值为 ： i + min
                nums[j] = i + min;
                n--;
                j--;
            }
        }
        return nums;
    }


    public static int[] sortArray2(int[] nums) {
        if (nums.length == 0) return nums;
        int[] res = new int[nums.length];
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        //PriorityQueue<Integer> queue1 = new PriorityQueue<Integer>(new Comparator<Integer>() {
        //    @Override
        //    public int compare(Integer o1, Integer o2) {
        //        return o1 - o2;
        //    }
        //});
        for (int num : nums) {
            queue.offer(num);
        }
        for (int i = 0; i < res.length; i++) {
            res[i] = queue.poll();
        }
        return res;
    }


    /**
     * 在本题的数据量下超时
     * 手撕快速排序
     * @param nums
     * @return
     */
    public static int[] sortArray3(int[] nums) {
        if (nums.length == 0) return nums;
        quickSort(nums,0,nums.length - 1);
        return nums;
    }

    private static void quickSort(int[] nums, int l, int r) {
        if (l >= r) return;
        int i = l, j = r;
        while (i < j){
            while (i < j && nums[j] >= nums[l]) j--;
            while (i < j && nums[i] <= nums[l]) i++;
            swap(nums,i,j);
        }
        swap(nums, i, l);
        quickSort(nums, l, i - 1);
        quickSort(nums, i + 1, r);
    }

    private static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }


}
