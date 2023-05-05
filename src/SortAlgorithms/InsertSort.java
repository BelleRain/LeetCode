package SortAlgorithms;

/**
 * @author mxy
 * @create 2022-10-08 15:17
 */

import java.util.Arrays;

/**
 * 1、插入排序：时间复杂度：O(n^2)
 * 2、基本思想：把n个待排序的元素看成为一个有序表和一个无序表，开始时有序表中只包含一个元素，无序表中包含有n-1个元素，
 *             排序过程中每次从无序表中取出第一个元素，把它的排序码依次与有序表元素的排序码进行比较，将它插入到有序表中
 *             的适当位置，使之成为新的有序表。
 * 3、平均时间复杂度：O(n^2)
 *    最好情况：O(n)
 *    最坏情况：O(n^2)
 *    空间复杂度：O(1)
 * 4、稳定性：稳定
 */
public class InsertSort {
    public static void main(String[] args) {
        int[] arr = {101, 34, 119, 1, -1, 89};
        InsertSort sort = new InsertSort();
        System.out.println(Arrays.toString(sort.insertSort(arr)));
    }

    public int[] insertSort(int[] nums){
        int inserVal = 0;
        int insertIndex = 0;
        for (int i = 1; i < nums.length; i++) {
            //定义待插入的数
            inserVal = nums[i];
            insertIndex = i - 1; //即arr[i]的前面这个数的下标
            /*
              给insertVal找到插入位置
              说明：
              1、insertIndex >= 0 保证在给insertVal找插入位置，不越界
              2、insertVal < arr[insertIndex] 待插入的数，还没有找到插入位置
              3、就需要将arr[insertIndex]后移
             */
            while (insertIndex >= 0 && inserVal < nums[insertIndex]){
                nums[insertIndex + 1] = nums[insertIndex]; //元素后移
                insertIndex--; //下标前移
            }
            if (insertIndex + 1 != i){ //如果等于i的话，不需要移动
                nums[insertIndex + 1] = inserVal;
            }
        }
        return nums;
    }

}










































