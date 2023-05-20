package SortAlgorithms;

/**
 * @author mxy
 * @create 2022-10-08 20:34
 */

import java.util.Arrays;

/**
 * 1、归并排序：时间复杂度：O(nlogn)
 * 2、基本思想：利用归并的思想实现的排序方法，该算法采用经典的分治策略（分治法将问题分(divide)成一些小的问题，
 *             然后递归求解，而治(conquer)的阶段则将分的阶段得到的各答案“修补”在一起，即分而治之）。
 * 3、平均时间复杂度：O(nlogn)
 *    最好情况：O(nlogn)
 *    最坏情况：O(nlogn)
 *    空间复杂度：O(n)
 * 4、稳定性：稳定
 */
public class MergeSort {
    public static void main(String[] args) {
        int arr[] = { 8, 4, 5, 7, 1, 3, 6, 2};
        //递归排序需要一个额外的空间
        int[] temp = new int[arr.length];
        MergeSort sort = new MergeSort();
        System.out.println(Arrays.toString(sort.mergeSort(arr,0,arr.length-1,temp)));
    }

    //分+合方法
    public int[] mergeSort(int[] nums,int left,int right,int[] temp){
        if (left < right){
            int mid = (left+right)/2; //中间索引
            //向左递归分解
            mergeSort(nums, left, mid, temp);
            //向右递归分解
            mergeSort(nums, mid+1, right, temp);
            merge(nums, left, mid, right, temp);
        }
        return nums;
    }

    /**
     * 合并的方法
     * @param nums 排序的原始数组
     * @param left 左边有序序列的初始索引
     * @param mid  中间索引
     * @param right 右边索引
     * @param temp  做中转的数组
     * @return
     */
    public int[] merge(int[] nums,int left,int mid,int right,int[] temp){
        int i = left;  //左边初始索引
        int j = mid + 1; //右边初始索引
        int t = 0;  //指向temp数组的当前索引
        /*
        （1）先把左右两边（有序）的数据按照规则填充到temp数组
        直到左右两边的有序序列，有一边处理完毕为止
         */
        while (i <= mid && j <= right){
            //如果左边的有序序列的当前元素，小于等于右边有序序列的当前元素
            //即将左边的当前元素，填充到temp数组
            //然后 t++，i++
            if (nums[i] < nums[j]){
                temp[t] = nums[i];
                t++;
                i++;
            }else { //反之，将右边有序序列的当前元素，填充到temp数组
                temp[t] = nums[j];
                t++;
                j++;
            }
        }
        /*
        （2）把有剩余数据的一边的数据依次全部填充到temp
         */
        while (i <= mid){//左边的有序序列还有剩余元素，就全部填充到temp中
            temp[t] = nums[i];
            t++;
            i++;
        }
        while (j <= right){//右边的有序序列还有剩余元素，就全部填充到temp中
            temp[t] = nums[j];
            t++;
            j++;
        }

        /*
        （3）将temp数组元素拷贝到arr，注意不是每次都拷贝所有
         */
        t = 0;
        int tempLeft = left;
        //第一次合并 tempLeft = 0 , right = 1 //  tempLeft = 2  right = 3 // tL=0 ri=3
        //最后一次 tempLeft = 0  right = 7
        while (tempLeft <= right){
            nums[tempLeft] = temp[t];
            t++;
            tempLeft++;
        }
        System.out.println(Arrays.toString(temp));
        System.out.println(Arrays.toString(nums));
        return nums;
    }

}




































