package SortAlgorithms;

/**
 * @author mxy
 * @create 2022-10-08 21:49
 */

import java.util.Arrays;

/**
 * 1、基数排序：时间复杂度：O(n*k) ，是桶排序的扩展
 * 2、基本思想：将所有待比较数值统一为同样的数位长度，数位较短的数前面补零。然后，从最低位开始，依次进行一次排序。
 *             这样从最低位排序一直到最高为排序完成以后，数列就变成一个有序序列。
 * 3、平均时间复杂度：O(n*k)
 *    最好情况：O(n*k)
 *    最坏情况：O(n*k)
 *    空间复杂度：O(n+k)
 * 4、稳定性：稳定
 */
public class RadixSort {
    public static void main(String[] args) {
        int arr[] = { 53, 3, 542, 748, 14, 214};
        RadixSort sort = new RadixSort();
        System.out.println(Arrays.toString(sort.radixSort(arr)));
    }

    public int[] radixSort(int[] nums){
        //1.得到数组最大数的位数
        int max = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > max){
                max = nums[i];
            }
        }
        //得到最大数是几位数
        int maxLength = (max + "").length();

        /*
        定义一个二维数组，表示10个桶，每个桶就是一个一维数组
        说明
        1、二维数组包含10个一维数组
        2、为了防止在放入数据的时候，数据溢出，则每一个一维数组（桶），大小定为arr.length
        3、明确，基数排序是使用空间换时间的经典算法
         */
        int[][] bucket = new int[10][nums.length];
        /*
        为了记录每个桶中，实际存放了多少数据，我们定义一个一维数组来记录各个桶的每次放入的数据个数
        可以这样理解：
        比如：bucketElementCounts[0]，记录的是bucket[0]桶中放入的数据个数
         */
        int[] bucketElementCounts = new int[10];

        for (int i = 0,n = 1; i < maxLength; i++,n*=10) {
            //（针对每个元素的对应位进行排序处理）， 第一次是个位，第二次是十位，第三次是百位..
            for (int j = 0; j < nums.length; j++) {
                //取出每个元素的对应的值
                int digitOfElement = nums[j] / n % 10;
                //放入到对应的桶中
                //可以理解为，第 digitOfElement 号桶，第 bucketElementCounts[digitOfElement]个元素是 nums[j]
                //bucketElementCounts[digitOfElement] 为该桶的元素个数
                //后期取元素的个数，依次取出即可
                bucket[digitOfElement][bucketElementCounts[digitOfElement]] = nums[j];
                bucketElementCounts[digitOfElement]++;
            }
            //按照这个桶的顺序（一维数组的下标依次取出，放入原来数组）
            int index = 0;
            //遍历每一个桶，并将桶中的数据，放入到原数据
            for (int k = 0; k < bucketElementCounts.length; k++) {
                //如果桶中有数据，取出放入原数组
                if (bucketElementCounts[k] != 0){
                    for (int l = 0; l < bucketElementCounts[k]; l++) {
                        nums[index++] = bucket[k][l]; //第k个桶中的第（l+1）个数据
                    }
                }
                //第 i+1 轮处理后，需要将每个桶中的数据个数置为0，
                //即 bucketElementCounts[k] = 0 ！！！
                bucketElementCounts[k] = 0;
            }
        }
        return nums;
    }


}




































