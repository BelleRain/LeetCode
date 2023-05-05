package SortAlgorithms;

/**
 * @author mxy
 * @create 2022-10-08 19:49
 */

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * 1、快速排序：时间复杂度：O(nlogn) 对冒泡排序的一种改进
 * 2、基本思想：通过一趟排序将要排序的数据分割成独立的两部分，其中一部分的所有数据都比另外一部分的所有数据都要小，
 *             然后再按此方法对这两部分分别进行快速排序，整个排序过程可以递归进行，以此达到整个数据变成有序序列
 * 3、平均时间复杂度：O(nlogn)
 *    最好情况：O(nlogn)
 *    最坏情况：O(n^2)
 *    空间复杂度：O(logn)
 * 4、稳定性：不稳定
 */
public class QuickSort {
    public static void main(String[] args) {
        //int[] arr = {-9,78,0,23,-567,70, -1,900, 4561};
        int[] arr = {0,1};
        QuickSort sort = new QuickSort();
        System.out.println(Arrays.toString(sort.quickSort(arr,0,arr.length - 1)));
    }

    public int[] quickSort(int[] nums,int left,int right){
        int l = left;
        int r = right;
        int pivot = nums[(left + right)/2]; //中轴值
        int temp = 0;
        //while循环：将比pivot大的值放在左边，比pivot小的值放在右边
        while (l<r){
            //在pivot的左边一直找，找到大于等于pivot的值，才退出
            while (nums[l] < pivot){
                l++;
            }
            //在pivot的右边一直找，找到小于等于pivot的值，才退出
            while (nums[r] > pivot){
                r--;
            }
            //如果l>=r，说明pivot的左右两边的值，已经全部按照
            //左边全部小于等于pivot的值，右边全部是大于等于pivot的值
            if (l >= r){
                break;
            }
            //交换
            temp = nums[l];
            nums[l] = nums[r];
            nums[r] = temp;

            //如果交换完后，发现这个nums[l] == pivot值，r--，前移
            if (nums[l] == pivot){
                r--;
            }

            //如果交换完后，发现这个nums[r] == pivot值，l++，后移
            if(nums[r] == pivot){
                l++;
            }
        }

        //如果 l == r,必须l++，r--，否则会出现栈溢出
        if (l==r){
            l++;
            r--;
        }
        //向左递归
        if (left < r){
            quickSort(nums, left, r);
        }
        //向右递归
        if (right > l){
            quickSort(nums, l, right);
        }
        return nums;
    }
}





































