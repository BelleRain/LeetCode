package SortAlgorithms;

/**
 * @author mxy
 * @create 2022-10-08 14:23
 */

import java.util.Arrays;

/**
 * 1、冒泡排序 : 时间复杂度 O(n^2)
 * 2、基本思想：通过对待排序序列从前向后（从下标较小的元素开始），依次比较相邻元素的值，若发现逆序则交换，
 *           使值较大的元素逐渐从前移向后部，就像水底下的气泡一样逐渐向上冒。
 * 3、优化：因为排序过程中，各元素不断接近自己的位置，如果一趟比较下来没有进行过交换，就说明序列有序，因此
 *       要在排序过程中设置一个标志flag判断元素是否进行过交换。从而减少不必要的比较。
 * 4、平均时间复杂度： O(n^2)
 *    最好情况：O(n)
 *    最坏情况：O(n^2)
 *    空间复杂度：O(1)
 * 5、稳定性：稳定
 */
public class BubbleSort {
    public static void main(String[] args) {
        int arr[] = {3, 9, -1, 10, 20};
        BubbleSort sort = new BubbleSort();
        System.out.println(Arrays.toString(sort.bubbleSort(arr)));
    }

    public int[] bubbleSort(int[] nums){
        for (int i = 0; i < nums.length - 1; i++) {
            boolean flag = false; //标识是否交换
            for (int j = 0; j < nums.length - 1 - i; j++) {
                if (nums[j] > nums[j+1]){
                    int temp = nums[j];
                    nums[j] = nums[j+1];
                    nums[j+1] = temp;
                    flag = true;
                }
            }
            if (!flag){
                return nums;
            }
        }
        return nums;
    }
}
