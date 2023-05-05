package Swordoffer.StackAndQueue;

/**
 * @author mxy
 * @create 2022-10-26 14:06
 */

import java.util.*;

/**
 * 滑动窗口的最大值
 * 给定一个数组 nums 和滑动窗口的大小 k，请找出所有滑动窗口里的最大值。
 *
 * 示例:
 * 输入: nums = [1,3,-1,-3,5,3,6,7], 和 k = 3
 * 输出: [3,3,5,5,6,7]
 * 解释:
 *
 *   滑动窗口的位置                最大值
 * ---------------               -----
 * [1  3  -1] -3  5  3  6  7       3
 *  1 [3  -1  -3] 5  3  6  7       3
 *  1  3 [-1  -3  5] 3  6  7       5
 *  1  3  -1 [-3  5  3] 6  7       5
 *  1  3  -1  -3 [5  3  6] 7       6
 *  1  3  -1  -3  5 [3  6  7]      7
 *  
 * 提示：
 * 你可以假设 k 总是有效的，在输入数组 不为空 的情况下，1 ≤ k ≤ nums.length
 */
public class Offer59_1 {

    public static void main(String[] args) {
        Offer59_1 offer = new Offer59_1();
        int[] nums = {1,3,-1,-3,5,3,6,7};
        System.out.println(Arrays.toString(offer.maxSlidingWindow(nums, 3)));
    }

    /**
     * 题解链接：https://leetcode.cn/problems/hua-dong-chuang-kou-de-zui-da-zhi-lcof/solution/mian-shi-ti-59-i-hua-dong-chuang-kou-de-zui-da-1-6/
     * 本题难点： 如何在每次窗口滑动后，将 “获取窗口内最大值” 的时间复杂度从 O(k) 降低至 O(1) 。
     * 方法一：单调队列
     * 复杂度分析：
     * 时间复杂度 O(n)： 其中 n 为数组 nums 长度；线性遍历 nums 占用 O(n) ；每个元素最多仅入队和出队一次，因此单调队列 deque 占用 O(2n)。
     * 空间复杂度 O(k)： 双端队列 deque 中最多同时存储 k 个元素（即窗口大小）。
     * @param nums
     * @param k
     * @return
     */
    public int[] maxSlidingWindow(int[] nums, int k) {
        if (nums.length == 0 || k == 0) return new int[0];
        Deque<Integer> deque = new LinkedList<>();
        int[] res = new int[nums.length - k + 1];
        for (int j = 0,i = 1-k; j < nums.length; i++,j++) {
            //nums[i-1]上一轮的队首，加入队列之前先判断上一轮的队首是否还在deque中，
            // 即是否为上一轮的最大值，而位于队首deque[0]，保证deque中只含有窗口内的元素
            //i<0时，未形成窗口
            //每轮窗口滑动，且队首元素deque[0] == nums[i-1],则队首元素出队
            //等于比较的原因：nums[i-1]很有可能在下面 deque.peekLast() < nums[j] 过程中被移除，已出队列
            //所以，队首元素deque[0]很有可能 ！= nums[i-1]
            //删除deque中对应的nums[i-1]
            if (i > 0 && deque.peekFirst() == nums[i-1])
                deque.removeFirst();
            //删除deque内所有 < nums[j] 的元素
            //保持deque递减，则 队首元素deque[0]即为窗口最大值
            while (!deque.isEmpty() && deque.peekLast() < nums[j])
                deque.removeLast();
            deque.addLast(nums[j]);
            //若已形成窗口（i>=0）,则将窗口最大值(即队首元素)，添加至res
            //记录窗口最大值
            if (i >= 0)
                res[i] = deque.peekFirst();
        }
        return res;
    }

    //可以将 “未形成窗口” 和 “形成窗口后” 两个阶段拆分到两个循环里实现。代码虽变长，但减少了冗余的判断操作。
    /*public int[] maxSlidingWindow(int[] nums, int k) {
        if(nums.length == 0 || k == 0) return new int[0];
        Deque<Integer> deque = new LinkedList<>();
        int[] res = new int[nums.length - k + 1];
        // 未形成窗口
        for(int i = 0; i < k; i++) {
            while(!deque.isEmpty() && deque.peekLast() < nums[i])
                deque.removeLast();
            deque.addLast(nums[i]);
        }
        res[0] = deque.peekFirst();
        // 形成窗口后
        for(int i = k; i < nums.length; i++) {
            if(deque.peekFirst() == nums[i - k])
                deque.removeFirst();
            while(!deque.isEmpty() && deque.peekLast() < nums[i])
                deque.removeLast();
            deque.addLast(nums[i]);
            res[i - k + 1] = deque.peekFirst();
        }
        return res;
    }*/


    /**
     * 题解链接：https://leetcode.cn/problems/hua-dong-chuang-kou-de-zui-da-zhi-lcof/solution/hua-dong-chuang-kou-de-zui-da-zhi-by-lee-ymyo/
     */
    /**
     * 方法一：优先队列
     * 复杂度分析
     * 时间复杂度：O(nlogn)，其中 n 是数组 nums 的长度。在最坏情况下，数组 nums 中的元素单调递增，那么最终优先队列中包含了所有元素，没有元素被移除。
     *      由于将一个元素放入优先队列的时间复杂度为 O(logn)，因此总时间复杂度为 O(nlogn)。
     * 空间复杂度：O(n)，即为优先队列需要使用的空间。这里所有的空间复杂度分析都不考虑返回的答案需要的 O(n) 空间，只计算额外的空间使用。
     * @param nums
     * @param k
     * @return
     */
    /*public int[] maxSlidingWindow(int[] nums, int k) {
        int n = nums.length;
        //构建大顶堆，堆中元素为数组。大顶堆的堆顶元素为窗口最大值
        PriorityQueue<int[]> pq = new PriorityQueue<int[]>(new Comparator<int[]>() {
            public int compare(int[] pair1, int[] pair2) {
                return pair1[0] != pair2[0] ? pair2[0] - pair1[0] : pair2[1] - pair1[1];
            }
        });
        //每个数组中放置两个元素，一个是当前元素值，另一个为当前元素下标
        //由大顶堆构建规则得，若两数组的首元素不相等，则递减排列，若相等，则按照下标递减排列
        for (int i = 0; i < k; ++i) {
            pq.offer(new int[]{nums[i], i});
        }
        int[] ans = new int[n - k + 1];
        //查询堆顶元素中数组的首位值
        ans[0] = pq.peek()[0];
        for (int i = k; i < n; ++i) {
            pq.offer(new int[]{nums[i], i});
            //查询的是堆顶元素中数组的第二个元素的值，即当前元素的下标值
            //队首元素是上一个滑动窗口中的元素，即队首元素不是当前滑动窗口中的元素，则出队
            while (pq.peek()[1] <= i - k) {
                pq.poll();
            }
            ans[i - k + 1] = pq.peek()[0];
        }
        return ans;
    }*/

    /**
     * 方法二：单调队列 同上述方法一
     * 复杂度分析
     * 时间复杂度：O(n)，其中 n 是数组 nums 的长度。每一个下标恰好被放入队列一次，并且最多被弹出队列一次，因此时间复杂度为 O(n)。
     * 空间复杂度：O(k)。与方法一不同的是，在方法二中我们使用的数据结构是双向的，因此「不断从队首弹出元素」保证了队列中最多不会有超过 k+1 个元素，因此队列使用的空间为 O(k)。
     * @param nums
     * @param k
     * @return
     */
    /*public int[] maxSlidingWindow(int[] nums, int k) {
        int n = nums.length;
        Deque<Integer> deque = new LinkedList<Integer>();
        for (int i = 0; i < k; ++i) {
            while (!deque.isEmpty() && nums[i] >= nums[deque.peekLast()]) {
                deque.pollLast();
            }
            deque.offerLast(i);
        }

        int[] ans = new int[n - k + 1];
        ans[0] = nums[deque.peekFirst()];
        for (int i = k; i < n; ++i) {
            while (!deque.isEmpty() && nums[i] >= nums[deque.peekLast()]) {
                deque.pollLast();
            }
            deque.offerLast(i);
            while (deque.peekFirst() <= i - k) {
                deque.pollFirst();
            }
            ans[i - k + 1] = nums[deque.peekFirst()];
        }
        return ans;
    }*/

    /**
     * 方法三：分块 + 预处理
     * 执行用时： 8 ms , 在所有 Java 提交中击败了 97.19% 的用户
     * 内存消耗： 57.1 MB , 在所有 Java 提交中击败了 65.49% 的用户
     * 复杂度分析
     * 时间复杂度：O(n)，其中 n 是数组 nums 的长度。我们需要 O(n) 的时间预处理出数组 prefixMax，suffixMax 以及计算答案。
     * 空间复杂度：O(n)，即为存储 prefixMax 和 suffixMax 需要的空间。
     * @param nums
     * @param k
     * @return
     */
    /*public int[] maxSlidingWindow(int[] nums, int k) {
        int n = nums.length;
        int[] prefixMax = new int[n];
        int[] suffixMax = new int[n];
        for (int i = 0; i < n; ++i) {
            if (i % k == 0) {
                prefixMax[i] = nums[i];
            }
            else {
                prefixMax[i] = Math.max(prefixMax[i - 1], nums[i]);
            }
        }
        for (int i = n - 1; i >= 0; --i) {
            if (i == n - 1 || (i + 1) % k == 0) {
                suffixMax[i] = nums[i];
            } else {
                suffixMax[i] = Math.max(suffixMax[i + 1], nums[i]);
            }
        }

        int[] ans = new int[n - k + 1];
        for (int i = 0; i <= n - k; ++i) {
            ans[i] = Math.max(suffixMax[i], prefixMax[i + k - 1]);
        }
        return ans;
    }*/



    /*//错误解答：超时处理
    public int[] maxSlidingWindow(int[] nums, int k) {
        if (nums.length == 0) return new int[0];
        int[] res = new int[nums.length-k+1];
        int i = 0,temp = 0,max = Integer.MIN_VALUE;
        for (int j = k-1; j < nums.length; j++) {
            for (int l = i; l <= j; l++) {
                if (nums[l] > max) max = nums[l];
            }
            res[temp++] = max;
            i++;
            max = Integer.MIN_VALUE;
        }

        return res;
    }*/

}
















