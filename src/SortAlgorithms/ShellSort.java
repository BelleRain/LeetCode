package SortAlgorithms;

/**
 * @author mxy
 * @create 2022-10-08 16:01
 */

import java.util.Arrays;

/**
 * 时间复杂度不确定
 * 1、希尔排序：时间复杂度：O(nlogn)；简单插入排序的改进版，缩小增量排序
 * 2、基本思想：把记录按下标的一定的增量分组，对每组使用直接插入算法排序；随着增量逐渐减少，每组包含的关键词越来越多，
 *             当增量减至1时，整个文件恰被分成一组，算法终止。
 * 3、平均时间复杂度：O(nlogn)
 *    空间复杂度：O(1)
 * 4、稳定性：不稳定
 */
public class ShellSort {
    public static void main(String[] args) {
        int[] arr = { 8, 9, 1, 7, 2, 3, 5, 4, 6, 0 };
        ShellSort sort = new ShellSort();
        System.out.println(Arrays.toString(sort.shellSort(arr)));
        System.out.println(Arrays.toString(sort.shellSort2(arr)));
    }

    //交换法
    public int[] shellSort(int[] nums){
        for (int gap = nums.length/2; gap > 0 ; gap /= 2) {
            for (int i = gap; i < nums.length ; i++) {
                for (int j = i-gap; j >= 0 ; j -= gap) {
                    //如果当前元素大于加上步长后的那个元素，说明交换
                    if (nums[j] > nums[j+gap]){
                        int temp = nums[j];
                        nums[j] = nums[j+gap];
                        nums[j+gap] = temp;
                    }
                }
            }
        }
        return nums;
    }

    //移动法
    public int[] shellSort2(int[] nums){
        //int gap = 1;
        for (int gap = nums.length/2; gap > 0 ; gap/=2) {
            //从第gap个元素，逐个对其所在的组进行直接插入排序
            for (int i = gap; i < nums.length; i++) {
                int j = i;
                int temp = nums[j];
                if (nums[j] < nums[j-gap]){
                    while (j-gap >= 0 && temp < nums[j-gap]){
                        nums[j] = nums[j-gap];
                        j-=gap;
                    }
                    //当退出while后，temp找到插入位置
                    nums[j] = temp;
                }
            }
        }
        return nums;
    }
}






































