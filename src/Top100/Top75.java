package Top100;

/**
 * @author mxy
 * @create 2022-11-23 9:44
 */

import java.util.Arrays;

/**
 * 75. 颜色分类   链接：https://leetcode.cn/problems/sort-colors
 * 给定一个包含红色、白色和蓝色、共 n 个元素的数组 nums ，原地对它们进行排序，
 * 使得相同颜色的元素相邻，并按照红色、白色、蓝色顺序排列。
 * 我们使用整数 0、 1 和 2 分别表示红色、白色和蓝色。
 *
 * 必须在不使用库的sort函数的情况下解决这个问题。
 *
 * 示例 1：
 * 输入：nums = [2,0,2,1,1,0]
 * 输出：[0,0,1,1,2,2]
 *
 * 示例 2：
 * 输入：nums = [2,0,1]
 * 输出：[0,1,2]
 *  
 * 提示：
 * n == nums.length
 * 1 <= n <= 300
 * nums[i] 为 0、1 或 2
 *
 * 进阶：
 * 你可以不使用代码库中的排序函数来解决这道题吗？
 * 你能想出一个仅使用常数空间的一趟扫描算法吗？
 *
 */
public class Top75 {

    public static void main(String[] args) {
        Top75 top75 = new Top75();
        //int[] nums = {2,0,2,1,1,0};
        //int[] nums = {3,2,0,1,2};
        //int[] nums = {2,0,1};
        int[] nums = {0,1};
        top75.sortColors(nums);
        System.out.println(Arrays.toString(nums));
    }

    /**
     * 题解一： 详细链接：https://leetcode.cn/problems/sort-colors/solution/kuai-su-pai-xu-partition-guo-cheng-she-ji-xun-huan/
     * 分区：
     *     设置循环不变量：
     *           // all in [0, zero) = 0  ，在 [0,zero) 区间 ，均为0
     *           // all in [zero, i) = 1  ，在 [zero,i) 区间 ，均为1
     *           // all in [two, len - 1] = 2  ，在 [two,len-1] 区间 ，均为2
     *    1、我们所有的操作都要遵循 所设置的 循环不变量
     *    2、对于上述区间划分，我们只有 [i,two) 区间的内容看不到，所以 当 i >= two 时，证明所有的元素已遍历完毕，循环可以终止
     *    3、初始化时，保证 所有的区间为空 ，所以 初始化各变量的值为
     *          zero = 0 ， i = 0 ，two = len
     * 复杂度分析：
     *      时间复杂度：O(N)，这里 N 是输入数组的长度；
     *      空间复杂度：O(1)。
     * @param nums
     */
    public void sortColors(int[] nums){
        int len = nums.length;
        if (len < 2) return;

        // all in [0, zero) = 0
        // all in [zero, i) = 1
        // all in [two, len - 1] = 2

        // 循环终止条件是 i == two，那么循环可以继续的条件是 i < two
        // 为了保证初始化的时候 [0, zero) 为空，设置 zero = 0，
        // 所以下面遍历到 0 的时候，先交换，再加
        int zero = 0;

        // 为了保证初始化的时候 [two, len - 1] 为空，设置 two = len
        // 所以下面遍历到 2 的时候，先减，再交换
        int two = len;
        int i = 0;

        // 当 i == two 上面的三个子区间正好覆盖了全部数组
        // 因此，循环可以继续的条件是 i < two

        // all in [0, zero) = 0
        // all in [zero, i) = 1
        // all in [two, len - 1] = 2
        while (i < two){
            if (nums[i] == 0){
                swap(nums, i, zero);
                zero++;
                i++;
            }else if (nums[i] == 1){
                i++;
            }else {
                two--;
                swap(nums, i, two);
            }
        }
    }

    /**
     * 循环不变量第二种定义：
     *         // all in [0, zero] = 0
     *         // all in (zero, i) = 1
     *         // all in (two, len - 1] = 2
     *    初始化：zero = -1 ， i = 0 ，two = len - 1
     *    终止条件： 由于[i,two] 之间的内容看不到， 条件为 i > two 终止
     * @param nums
     */
    /*public void sortColors(int[] nums){
        int len = nums.length;
        if (len < 2) return;
        // all in [0, zero] = 0
        // all in (zero, i) = 1
        // all in (two, len - 1] = 2

        // 为了保证初始化的时候 [0, zero] 为空，设置 zero = -1，
        // 所以下面遍历到 0 的时候，先加，再交换
        int zero = -1;

        // 为了保证初始化的时候 (two, len - 1] 为空，设置 two = len - 1
        // 所以下面遍历到 2 的时候，先交换，再减
        int two = len - 1;
        int i = 0;
        // 当 i == two 的时候，还有一个元素还没有看，
        // 因此，循环可以继续的条件是 i <= two
        while (i <= two){
            if (nums[i] == 0){
                zero++;
                swap(nums, i, zero);
                i++;
            }else if (nums[i] == 1){
                i++;
            }else {
                swap(nums, two, i);
                two--;
            }
        }
    }*/

    public void swap(int[] nums,int index1,int index2){
        int temp = nums[index1];
        nums[index1] = nums[index2];
        nums[index2] = temp;
    }



    /*public void sortColors(int[] nums) {
        quickSort(nums, 0, nums.length - 1);
    }

    *//**
     * 快速排序，该题不适合，空间复杂度未到达O(1)，且不是一趟扫描
     * @param nums
     * @param left
     * @param right
     *//*
    public void quickSort(int[] nums,int left,int right){
        int l = left, r = right;
        int mid = nums[(l + r)/2];
        while (l < r){
            while (l < r && nums[l] < mid) l++;
            while (l < r && nums[r] > mid) r--;
            if (l >= r) break;
            int temp = nums[l];
            nums[l] = nums[r];
            nums[r] = temp;
            if (nums[l] == mid) r--;
            if (nums[r] == mid) l++;
        }
        if (l == r){
            l++;
            r--;
        }
        if (left < r) quickSort(nums, left, r);
        if (l < right) quickSort(nums, l, right);
    }*/
}
