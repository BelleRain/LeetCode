package SortAlgorithms;

/**
 * @author mxy
 * @create 2022-10-09 9:12
 */

import java.util.Arrays;

/**
 * 1、堆排序：时间复杂度：O(nlogn) ,一般升序采用大顶堆，降序采用小顶堆
 * 2、基本思想：1）将待排序序列构造成一个大顶堆
 *            2）此时，整个序列的最大值就是堆顶的根节点
 *            3）将其与末尾元素进行交换，此时末尾就为最大值。
 *            4）然后将剩余（n-1）个元素重新构造成一个堆，这样会得到n个元素的次小值。
 *            如此反复执行，便能得到一个有序序列了。
 *     可以看到在构建大顶堆的过程中，元素的个数逐渐减少，最后就得到一个有序序列了。
 * 3、平均时间复杂度：O(nlogn)
 *    最好情况：O(nlogn)
 *    最坏情况：O(nlogn)
 *    空间复杂度：O(1)
 * 4、稳定性：不稳定
 */
public class HeapSort {
    public static void main(String[] args) {
        int arr[] = {4, 6, 8, 5, 9};
        HeapSort sort = new HeapSort();
        System.out.println(Arrays.toString(sort.heapSort(arr)));
    }

    public int[] heapSort(int[] nums){
        int temp = 0;
        //		//分步完成
        //		adjustHeap(arr, 1, arr.length);
        //		System.out.println("第一次" + Arrays.toString(arr)); // 4, 9, 8, 5, 6
        //
        //		adjustHeap(arr, 0, arr.length);
        //		System.out.println("第2次" + Arrays.toString(arr)); // 9,6,8,5,4

        //将无序序列构建成一个堆，根据升序降序需求选择大顶堆或小顶堆
        //2*i+2 = length-1 i = (length/2) - (3/2)
        for (int i = nums.length/2-1; i >= 0; i--) {
            adjustHeap(nums, i, nums.length);
        }
        System.out.println("========>" + Arrays.toString(nums));

        /*
        2)将堆顶元素与末尾元素交换，将最大元素“沉”到数组末端；
        3)重新调整结构，使其满足堆定义，然后继续交换堆顶元素与当前末尾元素，反复执行调整+交换步骤，知道整个序列有序
         */
        for (int j = nums.length-1; j > 0 ; j--) {
            temp = nums[j];
            nums[j] = nums[0];
            nums[0] = temp;
            System.out.println("堆调整之前：---->" + Arrays.toString(nums));
            adjustHeap(nums, 0, j);
            System.out.println("堆调整之后：====>" + Arrays.toString(nums));
        }
        return nums;
    }

    /**
     * 将一个数组（二叉树），调整成一个大顶堆
     * 功能：完成将以i对应的非叶子节点的树调整成大顶堆
     * 举例：int arr[] = {4, 6, 8, 5, 9}; => i = 1 => adjustHeap => 得到 {4, 9, 8, 5, 6}
     * 如果我们再次调用  adjustHeap 传入的是 i = 0 => 得到 {4, 9, 8, 5, 6} => {9,6,8,5,4}
     * @param nums 待调整的数组
     * @param i    表示非叶子结点在数组中的索引
     * @param length  表示对多少个元素继续调整，length是在逐渐减少
     */
    public void adjustHeap(int[] nums,int i,int length){
        int temp = nums[i]; //先取出当前元素的值，保存在临时变量
        //开始调整
        //说明
        //1、k = i*2+1，k是i结点的左子节点
        for (int k = 2*i + 1; k < length; k = k*2+1) {
            if (k+1 < length && nums[k] < nums[k+1]){//左子节点的值小于右子节点的值
                k++; //k指向右子节点
            }
            if (nums[k] > temp){ //如果子节点大于父节点
                nums[i] = nums[k]; //把较大的值赋给当前节点
                i = k; //!!! i指向k,继续循环比较
            }else {
                break; //!
            }
        }
        //当for循环结束后，我们已经将以i为父节点的树的最大值，放在了最顶（局部）
        nums[i] = temp;
    }
}

































