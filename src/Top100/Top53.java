package Top100;

/**
 * @author mxy
 * @create 2022-11-21 15:20
 */

/**
 * 53. 最大子数组和   链接：https://leetcode.cn/problems/maximum-subarray
 * 给你一个整数数组 nums ，请你找出一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
 * 子数组 是数组中的一个连续部分。
 *
 * 示例 1：
 * 输入：nums = [-2,1,-3,4,-1,2,1,-5,4]
 * 输出：6
 * 解释：连续子数组 [4,-1,2,1] 的和最大，为 6 。
 *
 * 示例 2：
 * 输入：nums = [1]
 * 输出：1
 *
 * 示例 3：
 * 输入：nums = [5,4,-1,7,8]
 * 输出：23
 *  
 * 提示：
 * 1 <= nums.length <= 10^5
 * -10^4 <= nums[i] <= 10^4
 * 进阶：如果你已经实现复杂度为 O(n) 的解法，尝试使用更为精妙的 分治法 求解。
 */
public class Top53 {

    public static void main(String[] args) {
        Top53 top53 = new Top53();
        int[] nums = {-2,1,-3,4,-1,2,1,-5,4};
        System.out.println(top53.maxSubArray(nums));
    }

    /**
     * 动态规划经典问题解答：
     * 题解链接：https://leetcode.cn/problems/maximum-subarray/solution/dong-tai-gui-hua-fen-zhi-fa-python-dai-ma-java-dai/
     */

    /**
     * 方法一：动态规划
     * 1、状态定义（定义子问题）： dp[i] : 表示以 nums[i] 结尾 的 连续 子数组 的最大和
     * 2、状态转移方程（描述子问题之间的联系）：
     *      dp[i] = dp[i - 1] + nums[i]; dp[i - 1] > 0
     *      dp[i] = nums[i];             dp[i - 1] <= 0
     *   由于所求为最大值，故可以写为 dp[i] = max{nums[i] , dp[i-1] + nums[i]}
     *   其中 dp[0] = nums[0];
     * 3、由题意，最后的结果，为 dp[i] 数组的最大值
     * 时间复杂度：O(N) ，这里 N 是输入数组的长度。
     * @param nums
     * @return
     */
    public int maxSubArray(int[] nums) {
        int[] dp = new int[nums.length];
        dp[0] = nums[0];
        int res = dp[0];
        for (int i = 1; i < nums.length; i++) {
            if (dp[i - 1] <= 0) dp[i] = nums[i];
            else dp[i] = dp[i - 1] + nums[i];
            res = Math.max(res, dp[i]);
        }
        return res;
    }

    /**
     * 方法一的空间优化：
     * @param nums
     * @return
     */
    /*public int maxSubArray(int[] nums) {
        int res = nums[0], pre = nums[0];
        for (int i = 1; i < nums.length; i++) {
            pre = Math.max(nums[i], pre + nums[i]);
            res = Math.max(pre, res);
        }
        return res;
    }*/

    /**
     * 方法二：分治
     * 分类讨论： 连续子序列的最大和主要由这三部分子区间里的元素的最大和得到
     * 1、第 1 部分：子区间 [left,mid]
     *    第 2 部分：子区间 [mid + 1,right]
     *    第 3 部分：包含子区间 [mid,mid + 1]的子区间,即 nums[mid] 与 nums[mid + 1]一定会被选取。
     *   对这三个部分求最大值即可。
     * 2、说明：考虑第 3 部分跨越两个区间的连续子数组的时候，由于 nums[mid] 与 nums[mid + 1] 一定会被选取，可以从中间向两边扩散，扩散到底 选出最大值
     * @param nums
     * @return
     */
    /*public int maxSubArray(int[] nums) {
        int len = nums.length;
        if (len == 0) {
            return 0;
        }
        return maxSubArraySum(nums, 0, len - 1);
    }

    private int maxCrossingSum(int[] nums, int left, int mid, int right) {
        // 一定会包含 nums[mid] 这个元素
        int sum = 0;
        int leftSum = Integer.MIN_VALUE;
        // 左半边包含 nums[mid] 元素，最多可以到什么地方
        // 走到最边界，看看最值是什么
        // 计算以 mid 结尾的最大的子数组的和
        for (int i = mid; i >= left; i--) {
            sum += nums[i];
            if (sum > leftSum) {
                leftSum = sum;
            }
        }
        sum = 0;
        int rightSum = Integer.MIN_VALUE;
        // 右半边不包含 nums[mid] 元素，最多可以到什么地方
        // 计算以 mid+1 开始的最大的子数组的和
        for (int i = mid + 1; i <= right; i++) {
            sum += nums[i];
            if (sum > rightSum) {
                rightSum = sum;
            }
        }
        return leftSum + rightSum;
    }

    private int maxSubArraySum(int[] nums, int left, int right) {
        if (left == right) {
            return nums[left];
        }
        int mid = left + (right - left) / 2;
        return max3(maxSubArraySum(nums, left, mid),
                maxSubArraySum(nums, mid + 1, right),
                maxCrossingSum(nums, left, mid, right));
    }

    private int max3(int num1, int num2, int num3) {
        return Math.max(num1, Math.max(num2, num3));
    }*/

    /**
     *  分治法：线段树  链接：https://leetcode.cn/problems/maximum-subarray/solution/zui-da-zi-xu-he-by-leetcode-solution/
     * 「方法二」相较于「方法一」来说，时间复杂度相同，但是因为使用了递归，并且维护了四个信息的结构体，运行的时间略长，
     *  空间复杂度也不如方法一优秀，而且难以理解。那么这种方法存在的意义是什么呢？
     *  对于这道题而言，确实是如此的。但是仔细观察「方法二」，它不仅可以解决区间 [0, n-1]，还可以用于解决任意的子区间 [l,r] 的问题。
     *  如果我们把 [0, n-1] 分治下去出现的所有子区间的信息都用堆式存储的方式记忆化下来，
     *  即建成一颗真正的树之后，我们就可以在 O(logn) 的时间内求到任意区间内的答案，我们甚至可以修改序列中的值，
     *  做一些简单的维护，之后仍然可以在 O(logn) 的时间内求到任意区间内的答案，
     *  对于大规模查询的情况下，这种方法的优势便体现了出来。这棵树就是上文提及的一种神奇的数据结构——线段树。
     */
    /*public class Status {
        public int lSum, rSum, mSum, iSum;

        public Status(int lSum, int rSum, int mSum, int iSum) {
            this.lSum = lSum;
            this.rSum = rSum;
            this.mSum = mSum;
            this.iSum = iSum;
        }
    }

    public int maxSubArray(int[] nums) {
        return getInfo(nums, 0, nums.length - 1).mSum;
    }

    public Status getInfo(int[] a, int l, int r) {
        if (l == r) {
            return new Status(a[l], a[l], a[l], a[l]);
        }
        int m = (l + r) >> 1;
        Status lSub = getInfo(a, l, m);
        Status rSub = getInfo(a, m + 1, r);
        return pushUp(lSub, rSub);
    }

    public Status pushUp(Status l, Status r) {
        int iSum = l.iSum + r.iSum;
        int lSum = Math.max(l.lSum, l.iSum + r.lSum);
        int rSum = Math.max(r.rSum, r.iSum + l.rSum);
        int mSum = Math.max(Math.max(l.mSum, r.mSum), l.rSum + r.lSum);
        return new Status(lSum, rSum, mSum, iSum);
    }*/
}
