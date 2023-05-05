package SortAlgorithms;

/**
 * @author mxy
 * @create 2022-10-08 14:41
 */

import java.util.Arrays;

/**
 * 1、简单选择排序 : 时间复杂度是 O(n^2)
 * 2、基本思想：第一次从arr[0]~arr[n-1]中选取最小值，与arr[0]交换，第二次从arr[1]~arr[n-1]中选取最小值，与arr[1]交换，
 *          第三次从arr[2]~arr[n-1]中选取最小值，与arr[2]交换，第i次从arr[i-1]~arr[n-1]中选取最小值，与arr[i-1]交换，...，
 *          第n-1次从arr[n-2]~arr[n-1]中选取最小值，与arr[n-2]交换，总共通过n-1次，得到一个按排序码从小到大的有序序列。
 * 3、平均时间复杂度： O(n^2)
 *    最好情况：O(n^2)
 *    最坏情况：O(n^2)
 *    空间复杂度：O(1)
 * 4、稳定性：不稳定
 */
public class SelectSort {
    public static void main(String[] args) {
        int [] arr = {101, 34, 119, 1, -1, 90, 123};
        SelectSort sort = new SelectSort();
        System.out.println(Arrays.toString(sort.selectSort(arr)));
    }

    public int[] selectSort(int[] nums){
        for (int i = 0; i < nums.length - 1; i++) {
            int minIndex = i;
            int min = nums[i];
            for (int j = i+1; j < nums.length; j++) {
                if (min > nums[j]){
                    min = nums[j];
                    minIndex = j;
                }
            }
            if (minIndex != i){
                nums[minIndex] = nums[i];
                nums[i] = min;
            }
        }
        return nums;
    }
}



































