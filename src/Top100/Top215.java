package Top100;

/**
 * @author mxy
 * @create 2022-12-09 16:57
 */

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Random;

/**
 * 215. 数组中的第K个最大元素     链接：https://leetcode.cn/problems/kth-largest-element-in-an-array
 * 给定整数数组 nums 和整数 k，请返回数组中第 k 个最大的元素。
 * 请注意，你需要找的是数组排序后的第 k 个最大的元素，而不是第 k 个不同的元素。
 *
 * 你必须设计并实现时间复杂度为 O(n) 的算法解决此问题。
 *
 * 示例 1:
 * 输入: [3,2,1,5,6,4], k = 2
 * 输出: 5
 *
 * 示例 2:
 * 输入: [3,2,3,1,2,4,5,5,6], k = 4
 * 输出: 4
 *  
 *
 * 提示：
 *
 * 1 <= k <= nums.length <= 10^5
 * -10^4 <= nums[i] <= 10^4
 *
 */
public class Top215 {

    public static void main(String[] args) {
        Top215 top215 = new Top215();
        //int[] nums = {3,2,1,5,6,4};
        int[] nums = {-3,2,0};
        System.out.println(top215.findKthLargest(nums, 2));
    }

    /*
    快速排序和堆排序两种方法的优劣性比较
        在面试中，另一个常常问的问题就是这两种方法有何优劣。看起来分治法的快速选择算法的时间、空间复杂度都优于使用堆的方法，但是要注意到快速选择算法的几点局限性：
        第一，算法需要修改原数组，如果原数组不能修改的话，还需要拷贝一份数组，空间复杂度就上去了。
        第二，算法需要保存所有的数据。如果把数据看成输入流的话，使用堆的方法是来一个处理一个，不需要保存数据，只需要保存 k 个元素的最大堆。
        而快速选择的方法需要先保存下来所有的数据，再运行算法。当数据量非常大的时候，甚至内存都放不下的时候，就麻烦了。所以当数据量大的时候还是用基于堆的方法比较好。
        链接：https://leetcode.cn/problems/zui-xiao-de-kge-shu-lcof/solution/tu-jie-top-k-wen-ti-de-liang-chong-jie-fa-you-lie-/
     */

    /**
     * 题解链接：https://leetcode.cn/problems/kth-largest-element-in-an-array/solution/partitionfen-er-zhi-zhi-you-xian-dui-lie-java-dai-/
     */

    /**
     * 方法一： 暴力解法
     * 复杂度分析：
     * 时间复杂度：O(NlogN)，这里 N 是数组的长度，算法的性能消耗主要在排序，JDK 默认使用快速排序，因此时间复杂度为 O(NlogN)；
     * 空间复杂度：O(logN)，这里认为编程语言使用的排序方法是「快速排序」，空间复杂度为递归调用栈的高度，为 logN。
     * @param nums
     * @param k
     * @return
     */
    /*public int findKthLargest(int[] nums, int k) {
        int len = nums.length;
        Arrays.sort(nums);
        //升序排序以后，目标元素的下标是 N - k，这里 N 是输入数组的长度。
        return nums[len - k];
    }*/

    /**
     * 方法二：快速选择
     * 「快速排序」虽然快，但是「快速排序」在遇到特殊测试用例（「顺序数组」或者「逆序数组」）的时候，递归树会退化成链表，时间复杂度会变成 O(N^2)
     * 事实上，有一个很经典的基于「快速排序」的算法，可以通过一次遍历，确定某一个元素在排序以后的位置，这个算法叫「快速选择」。
     * 注意：本题必须随机初始化 pivot 元素，否则通过时间会很慢，因为测试用例中有极端测试用例。为了应对极端测试用例，使得递归树加深，可以在循环一开始的时候，随机交换第 1 个元素与它后面的任意 1 个元素的位置。
     * 复杂度分析：
     *      时间复杂度：O(N)，这里 N 是数组的长度，理由可以参考本题解下用户 @ZLW 的评论，需要使用主定理进行分析；
     *      空间复杂度：O(1)，在代码里没有使用递归，在逐渐缩小搜索区间的过程中只使用到常数个变量。
     *      说明：可能有一部分朋友看到这道题有「递归」的写法，但是本题解采用的是在 while (true) 循环中，通过 left 与 right 向中间靠拢的方式逐步缩小搜索区间，没有使用递归调用栈（也无须使用递归调用栈），因此空间复杂度是 O(1)。
     * @param nums
     * @param k
     * @return
     */
    /*public int findKthLargest(int[] nums, int k) {
        //System.out.println("原数组：" + Arrays.toString(nums));
        //第1大的数，下标：len-1；第2大的数，下标：len-2；... 第k大的数，下标：len-k
        int len = nums.length, target = len - k;
        int left = 0, right = len - 1;
        while (true){
            //System.out.println("====================");
            int pivotIndex = partition(nums,left,right);
            System.out.println("pivotIndex = " + pivotIndex);
            if (pivotIndex == target) return nums[pivotIndex];
            else if (pivotIndex < target) left = pivotIndex + 1;
            else right = pivotIndex - 1;
        }
    }*/

    /*private int partition(int[] nums, int left, int right) {
        Random random = new Random();
        int randomIndex = left + random.nextInt(right - left + 1);
        swap(nums,left,randomIndex);
        //all in nums[left+1,...,le) <= pivot; all in nums(ge,...,right] > pivot
        int pivot = nums[left], le = left + 1, ge = right;
        while (true){
            while (le <= ge && nums[le] < pivot) le++;
            while (le <= ge && nums[ge] > pivot) ge--;
            if (le >= ge) break;
            swap(nums,le,ge);
            le++;
            ge--;
        }
        //System.out.println("交换前: " + Arrays.toString(nums));
        //System.out.println("left = " + left + " ge = " + ge + " le = " + le);
        //将 哨兵值 放置于 ge
        swap(nums,left,ge);
        //System.out.println("交换后：" + Arrays.toString(nums));
        //ge所在的值为 哨兵值，哨兵值左边比 哨兵小，右边是比它大的值，
        //所以 当 ge == target 时，即为所找的最大值
        return ge;
    }*/

    /*private void swap(int[] nums, int index1, int index2) {
        int temp = nums[index1];
        nums[index1] = nums[index2];
        nums[index2] = temp;
    }*/

    /**
     * 递归写法：
     * @param nums
     * @param k
     * @return
     */
    public int findKthLargest(int[] nums, int k) {
        return quickSelect(nums,nums.length - k, 0, nums.length - 1);
    }

    private int quickSelect(int[] nums, int k,int left, int right) {
        //加入 随机初始化 pivot = nums[left] ,可以极大提高运行效率
        Random random = new Random();
        int randomIndex = left + random.nextInt(right - left + 1);
        swap(nums,left,randomIndex);
        int i = left, j = right;
        while (i < j){
            while (i < j && nums[j] >= nums[left]) j--;
            while (i < j && nums[i] <= nums[left]) i++;
            swap(nums,i,j);
        }
        swap(nums, i, left);
        if (i > k) return quickSelect(nums, k, left, i - 1);
        if (i < k) return quickSelect(nums, k, i + 1, right);
        return nums[i];
    }

    private void swap(int[] nums, int index1, int index2) {
        int temp = nums[index1];
        nums[index1] = nums[index2];
        nums[index2] = temp;
    }

    /**
     * 方法三：优先队列（堆）
     * 复杂度分析：
     *      时间复杂度：O(NlogK)，遍历数据 O(N)，堆内元素调整 O(logK)；
     *      空间复杂度：O(K)。
     * @param nums
     * @param k
     * @return
     */
    //public int findKthLargest(int[] nums, int k) {
    //    PriorityQueue<Integer> queue = new PriorityQueue<>();
    //    for (int num : nums) {
    //        queue.offer(num);
    //        if (queue.size() > k) queue.poll();
    //    }
    //    return queue.peek();
    //}

}
