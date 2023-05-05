package Swordoffer.Dynamic;

/**
 * @author mxy
 * @create 2022-09-24 15:35
 */

/**
 * 连续子数组的最大和
 * 输入一个整型数组，数组中的一个或连续多个整数组成一个子数组。求所有子数组的和的最大值。
 *
 * 要求时间复杂度为O(n)。
 *
 * 示例1:
 *
 * 输入: nums = [-2,1,-3,4,-1,2,1,-5,4]
 * 输出: 6
 * 解释: 连续子数组 [4,-1,2,1] 的和最大，为 6。
 *
 */
public class Offer42 {
    public static void main(String[] args) {
        int[] nums = {-2,1,-3,4,-1,2,1,-5,4};
        Offer42 offer = new Offer42();
        int res = offer.maxSubArray(nums);
        System.out.println(res);
    }


    /**
     * 解法一：动态规划
     * 最佳解法： 动态规划  原文链接：https://leetcode.cn/problems/lian-xu-zi-shu-zu-de-zui-da-he-lcof/solution/mian-shi-ti-42-lian-xu-zi-shu-zu-de-zui-da-he-do-2/
     * 动态规划解析：
     *    状态定义： 设动态规划列表 dp ，dp[i] 代表以元素 nums[i] 为结尾的连续子数组最大和。
     *      为何定义最大和 dp[i] 中必须包含元素 nums[i] ：保证 dp[i] 递推到 dp[i+1] 的正确性；如果不包含 nums[i] ，递推时则不满足题目的 连续子数组 要求。
     *    转移方程： 若 dp[i−1]≤0 ，说明 dp[i - 1] 对 dp[i] 产生负贡献，即 dp[i-1] + nums[i] 还不如 nums[i] 本身大。
     *       当 dp[i - 1] > 0 时：执行 dp[i] = dp[i-1] + nums[i]；
     *      当 dp[i−1] ≤ 0 时：执行 dp[i] = nums[i]；
     *    初始状态： dp[0] = nums[0]，即以 nums[0] 结尾的连续子数组最大和为 nums[0] 。
     *
     * 返回值： 返回 dp 列表中的最大值，代表全局最大值。
     *
     * 空间复杂度降低：
     * 由于 dp[i] 只与 dp[i-1] 和 nums[i] 有关系，因此可以将原数组 nums 用作 dp 列表，即直接在 nums 上修改即可。
     * 由于省去 dp 列表使用的额外空间，因此空间复杂度从 O(N) 降至 O(1) 。
     *
     * 复杂度分析：
     * 时间复杂度 O(N) ： 线性遍历数组 nums 即可获得结果，使用 O(N) 时间。
     * 空间复杂度 O(1) ： 使用常数大小的额外空间。
     *
     * @param nums
     * @return
     */
    //nums = [-2,1,-3,4,-1,2,1,-5,4]
    //该写法改变了原来的数组
    public int maxSubArray(int[] nums) {
        int res = nums[0];
        for (int i = 1; i < nums.length; i++) {
            nums[i] = nums[i] + Math.max(nums[i-1],0);
            res = Math.max(res, nums[i]);
        }
        return res;
    }

    //利用两个参数存储d[i-1]和d[i]，不改变原数组的结构
    //public int maxSubArray(int[] nums){
    //    int max = nums[0];
    //    int former = 0; //用于记录dp[i-1]的值，对于dp[0]而言，其前面的dp[-1]=0
    //    int cur = nums[0]; //用于记录dp[i]的值
    //    for (int num : nums) {
    //        cur = num;
    //        if (former > 0) cur = former + num;
    //        if (cur > max) max = cur;
    //        former = cur;
    //    }
    //    return max;
    //}

    /**
     * 解法二：动态规划  原文链接：链接：https://leetcode.cn/problems/lian-xu-zi-shu-zu-de-zui-da-he-lcof/solution/lian-xu-zi-shu-zu-de-zui-da-he-by-leetco-tiui/
     * 思路和算法：
     * 假设 nums 数组的长度是 n，下标从 0 到 n-1。
     * 我们用  f(i) 代表以第 i 个数结尾的「连续子数组的最大和」，那么很显然我们要求的答案就是：
     * 0≤i≤n−1 max {f(i)}
     *
     * 因此我们只需要求出每个位置的 f(i)，然后返回 f 数组中的最大值即可。那么我们如何求 f(i) 呢？
     * 我们可以考虑 nums[i] 单独成为一段还是加入 f(i−1) 对应的那一段，这取决于 nums[i] 和 f(i-1) + nums[i] 的大小，
     * 我们希望获得一个比较大的，于是可以写出这样的动态规划转移方程：
     *          f(i)=max{f(i−1)+nums[i],nums[i]}
     * 不难给出一个时间复杂度 O(n)、空间复杂度  O(n) 的实现，即用一个 f 数组来保存 f(i) 的值，用一个循环求出所有 f(i)。
     * 考虑到 f(i) 只和 f(i-1) 相关，于是我们可以只用一个变量 pre 来维护对于当前 f(i) 的 f(i-1) 的值是多少，
     * 从而让空间复杂度降低到 O(1)，这有点类似「滚动数组」的思想。
     *
     * 复杂度分析：
     * 时间复杂度：O(n)，其中 n 为 nums 数组的长度。我们只需要遍历一遍数组即可求得答案。
     * 空间复杂度：O(1)。我们只需要常数空间存放若干变量。
     */
    //public int maxSubArray(int[] nums) {
    //    int pre = 0,max = nums[0];
    //    for (int num : nums) {
    //        pre = Swordoffer.Math.max(pre+num, num);
    //        max = Swordoffer.Math.max(pre, max);
    //    }
    //    return max;
    //}

    /**
     * 解法三：分治思想  原文链接：链接：https://leetcode.cn/problems/lian-xu-zi-shu-zu-de-zui-da-he-lcof/solution/lian-xu-zi-shu-zu-de-zui-da-he-by-leetco-tiui/
     * 思路和算法
     *   这个分治方法类似于「线段树求解最长公共上升子序列问题」的 pushUp 操作。 也许读者还没有接触过线段树，没有关系，方法二的内容假设你没有任何线段树的基础。当然，如果读者有兴趣的话，推荐阅读线段树区间合并法解决多次询问的「区间最长连续上升序列问题」和「区间最大子段和问题」，还是非常有趣的。
     *   我们定义一个操作 get(a, l, r) 表示查询 a 序列 [l,r] 区间内的最大子段和，那么最终我们要求的答案就是 get(nums, 0, nums.size() - 1)。
     *   如何分治实现这个操作呢？对于一个区间 [l,r]，我们取 m = (l+r)/2;
     *   对区间 [l,m] 和 [m+1,r] 分治求解。当递归逐层深入直到区间长度缩小为 1 的时候，递归「开始回升」。这个时候我们考虑如何通过 [l,m] 区间的信息和 [m+1,r] 区间的信息合并成区间 [l,r] 的信息。
     *   最关键的两个问题是：
     *      我们要维护区间的哪些信息呢？
     *      我们如何合并这些信息呢？
     *  对于一个区间 [l,r]，我们可以维护四个量：
     *      lSum 表示 [l,r] 内以 l 为左端点的最大子段和
     *      rSum 表示 [l,r] 内以 r 为右端点的最大子段和
     *      mSum 表示 [l,r] 内的最大子段和
     *      iSum 表示 [l,r] 的区间和
     *      以下简称 [l,m] 为 [l,r] 的「左子区间」，[m+1,r] 为 [l,r] 的「右子区间」。
     *  我们考虑如何维护这些量呢（如何通过左右子区间的信息合并得到 [l,r] 的信息）？
     *   对于长度为 1 的区间 [i, i]，四个量的值都和 nums[i] 相等。
     *   对于长度大于 1 的区间：
     *      首先最好维护的是 iSum，区间 [l,r] 的 iSum 就等于「左子区间」的 iSum 加上「右子区间」的 iSum。
     *      对于 [l,r] 的 lSum，存在两种可能，它要么等于「左子区间」的 lSum，要么等于「左子区间」的 iSum 加上「右子区间」的 lSum，二者取大。
     *      对于 [l,r] 的 rSum，同理，它要么等于「右子区间」的 rSum，要么等于「右子区间」的 iSum 加上「左子区间」的  rSum，二者取大。
     *  当计算好上面的三个量之后，就很好计算 [l,r] 的 mSum 了。
     *  我们可以考虑 [l,r] 的 mSum 对应的区间是否跨越 m——它可能不跨越 m，也就是说 [l,r] 的 mSum 可能是「左子区间」的 mSum 和 「右子区间」的 mSum 中的一个；
     *  它也可能跨越 m，可能是「左子区间」的 rSum 和 「右子区间」的 lSum 求和。三者取大。
     *
     * 复杂度分析:
     * 假设序列 a 的长度为 n。
     *   时间复杂度：假设我们把递归的过程看作是一颗二叉树的先序遍历，那么这颗二叉树的深度的渐进上界为 O(logn)，这里的总时间相当于遍历这颗二叉树的所有节点，
     *   故总时间的渐进上界是 O(∑(i=(1:logn)) 2^(i−1))=O(n)，故渐进时间复杂度为 O(n)。
     *  空间复杂度：递归会使用  O(logn) 的栈空间，故渐进空间复杂度为 O(logn)。
     */
    //public class Status{
    //    public int lSum,rSum,mSum,iSum;
    //    public Status(int lSum, int rSum, int mSum, int iSum) {
    //        this.lSum = lSum;
    //        this.rSum = rSum;
    //        this.mSum = mSum;
    //        this.iSum = iSum;
    //    }
    //}
    //
    //public int maxSubArray(int[] nums) {
    //    return getInfo(nums,0,nums.length-1).mSum;
    //}
    //
    //public Status getInfo(int[] a,int l, int r){
    //    if (l==r){
    //        return new Status(a[l],a[l],a[l],a[l]);
    //    }
    //    //数学意义：右移一位相当于除2，右移n位相当于除以2的n次方。
    //    int m = (l+r) >>1;
    //    Status lSub = getInfo(a, l, m);
    //    Status rSub = getInfo(a, m + 1, r);
    //    return pushUp(lSub, rSub);
    //}
    //
    //public Status pushUp(Status l,Status r){
    //    int iSum = l.iSum + r.iSum;
    //    int lSum = Swordoffer.Math.max(l.lSum,l.iSum + r.lSum);
    //    int rSum = Swordoffer.Math.max(r.rSum, r.iSum + l.rSum);
    //    int mSum = Swordoffer.Math.max(Swordoffer.Math.max(l.mSum, r.mSum), l.rSum + r.lSum);
    //    return new Status(lSum, rSum, mSum, iSum);
    //}


    /**
     * 题外话
     * 「方法二」相较于「方法一」来说，时间复杂度相同，但是因为使用了递归，并且维护了四个信息的结构体，运行的时间略长，
     *  空间复杂度也不如方法一优秀，而且难以理解。那么这种方法存在的意义是什么呢？
     *  对于这道题而言，确实是如此的。但是仔细观察「方法二」，它不仅可以解决区间 [0, n-1]，还可以用于解决任意的子区间 [l,r] 的问题。
     *  如果我们把 [0, n-1] 分治下去出现的所有子区间的信息都用堆式存储的方式记忆化下来，即建成一颗真正的树之后，
     *  我们就可以在 O(logn) 的时间内求到任意区间内的答案，我们甚至可以修改序列中的值，做一些简单的维护，之后仍然可以在 O(logn) 的时间内求到任意区间内的答案，
     *  对于大规模查询的情况下，这种方法的优势便体现了出来。这棵树就是上文提及的一种神奇的数据结构——线段树。
     *
     */

}











































