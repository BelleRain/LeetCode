package CodeTop;

/**
 * @author mxy
 * @create 2023-04-23 13:48
 */

import org.omg.CORBA.MARSHAL;

import java.util.*;

/**
 * 239. 滑动窗口最大值     链接：https://leetcode.cn/problems/sliding-window-maximum
 * 给你一个整数数组 nums，有一个大小为 k 的滑动窗口从数组的最左侧移动到数组的最右侧。
 * 你只可以看到在滑动窗口内的 k 个数字。滑动窗口每次只向右移动一位。
 * 返回 滑动窗口中的最大值 。
 *
 * 示例 1：
 * 输入：nums = [1,3,-1,-3,5,3,6,7], k = 3
 * 输出：[3,3,5,5,6,7]
 * 解释：
 * 滑动窗口的位置                最大值
 * ---------------               -----
 * [1  3  -1] -3  5  3  6  7       3
 *  1 [3  -1  -3] 5  3  6  7       3
 *  1  3 [-1  -3  5] 3  6  7       5
 *  1  3  -1 [-3  5  3] 6  7       5
 *  1  3  -1  -3 [5  3  6] 7       6
 *  1  3  -1  -3  5 [3  6  7]      7
 *
 * 示例 2：
 * 输入：nums = [1], k = 1
 * 输出：[1]
 *  
 * 提示：
 * 1 <= nums.length <= 10^5
 * -10^4 <= nums[i] <= 10^4
 * 1 <= k <= nums.length
 *
 */
public class Code239 {

    public static void main(String[] args) {
        //int[] nums = {1,3,-1,-3,5,3,6,7};
        //int[] nums = {1,-1};
        int[] nums = {1,3,1,2,0,5};
        System.out.println(Arrays.toString(maxSlidingWindow1(nums, 3)));
        System.out.println(Integer.MIN_VALUE - 1);
        System.out.println(Integer.MAX_VALUE + 1);
    }

    /**
     * 题解链接：https://leetcode.cn/problems/sliding-window-maximum/solution/hua-dong-chuang-kou-zui-da-zhi-by-leetco-ki6m/
     */

    /**
     * 优先队列：
     *      right - left + 1 = k   left = right - k + 1  left - 1 = right - k
     * @param nums
     * @param k
     * @return
     */
    public static int[] maxSlidingWindow1(int[] nums, int k) {
        int[] ans = new int[nums.length - k + 1];
        PriorityQueue<int[]> queue = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] != o2[0] ? o2[0] - o1[0] : o2[1] - o1[1];
            }
        });
        for (int i = 0; i < k; i++) {
            queue.add(new int[]{nums[i],i});
        }
        ans[0] = queue.peek()[0];
        // right - left + 1 = k   left = right - k + 1  left - 1 = right - k
        for (int i = k; i < nums.length; i++) {
            queue.add(new int[]{nums[i],i});
            //将不在窗口中的元素移出堆
            //此处不可以用if，若 上一轮的最大值在窗口内，则不会被移除，向堆中添加元素，堆的长度不一定总是为k
            //随着循环，很可能出现不止堆顶元素不在窗口内的情况，故用 while循环，将所有不在窗口的元素移出
            while(queue.peek()[1] <= i - k){
                queue.poll();
            }
            ans[i - k + 1] = queue.peek()[0];
        }
        return ans;
    }


    /**
     * 单调队列
     * @param nums
     * @param k
     * @return
     */
    public static int[] maxSlidingWindow2(int[] nums, int k) {
        int[] ans = new int[nums.length - k + 1];
        Deque<Integer> queue = new LinkedList<>();
        for (int i = 0; i < k; i++) {
            //当 nums[i] < nums[queue.peekLast()] 时，终止循环
            //所以，队列中元素对应的nums[i],是倒序排列
            while (!queue.isEmpty() && nums[i] >= nums[queue.peekLast()]){
                queue.pollLast();
            }
            queue.offerLast(i);
        }
        ans[0] = nums[queue.peekFirst()];
        for (int i = k; i < nums.length; i++) {
            while (!queue.isEmpty() && nums[i] >= nums[queue.peekLast()]){
                queue.pollLast();
            }
            queue.offerLast(i);
            while (!queue.isEmpty() && queue.peekFirst() <= i - k){
                queue.pollFirst();
            }
            ans[i - k + 1] = nums[queue.peekFirst()];
        }
        return ans;
    }

    /**
     * 分块 + 预处理
     * @param nums
     * @param k
     * @return
     */
    public static int[] maxSlidingWindow3(int[] nums, int k) {
        int n = nums.length;
        int[] prefixMax = new int[n];
        int[] suffiMax = new int[n];
        for (int i = 0; i < n; i++) {
            if (i % k == 0){
                prefixMax[i] = nums[i];
            }
            else {
                prefixMax[i] = Math.max(prefixMax[i - 1], nums[i]);
            }
        }
        for (int i = n - 1; i >= 0; --i) {
            if (i == n - 1 || (i + 1) % k == 0){
                suffiMax[i] = nums[i];
            }
            else {
                suffiMax[i] = Math.max(suffiMax[i + 1], nums[i]);
            }
        }
        int[] ans = new int[n - k + 1];
        for (int i = 0; i <= n - k; i++) {
            ans[i] = Math.max(suffiMax[i], prefixMax[i + k - 1]);
        }
        return ans;
    }

    /**
     * 执行用时： 2 ms , 在所有 Java 提交中击败了 100.00% 的用户
     * 内存消耗： 52 MB , 在所有 Java 提交中击败了 98.88% 的用户
     * @param nums
     * @param k
     * @return
     */
    public static int[] maxSlidingWindow4(int[] nums, int k) {
        int[] array = new int[nums.length - k + 1];
        int ans = Integer.MIN_VALUE;
        int left = 0;
        int index = -1;
        for(int right = k - 1; right < nums.length; right++){
            if(left <= index){
                if(nums[right] >= nums[index]){
                    index = right;
                    ans = nums[right];
                }
            }
            else if(nums[right] >= ans - 1){
                index = right;
                ans = nums[right];
            }
            else if(nums[left] >= ans - 1){
                index = left;
                ans = nums[left];
            }
            else{
                ans = nums[left];
                for(int j = left + 1; j <= right; j++){
                    if(nums[j] >= ans){
                        index = j;
                        ans = nums[j];
                    }
                }
            }
            array[left] = ans;
            left++;
        }
        return array;
    }
}





























