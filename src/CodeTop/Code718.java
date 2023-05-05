package CodeTop;

/**
 * @author mxy
 * @create 2023-05-02 11:01
 */

import java.sql.Array;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * 718. 最长重复子数组     链接：https://leetcode.cn/problems/maximum-length-of-repeated-subarray
 * 给两个整数数组 nums1 和 nums2 ，返回 两个数组中 公共的 、长度最长的子数组的长度 。
 *
 * 示例 1：
 * 输入：nums1 = [1,2,3,2,1], nums2 = [3,2,1,4,7]
 * 输出：3
 * 解释：长度最长的公共子数组是 [3,2,1] 。
 *
 * 示例 2：
 * 输入：nums1 = [0,0,0,0,0], nums2 = [0,0,0,0,0]
 * 输出：5
 *
 * 提示：
 * 1 <= nums1.length, nums2.length <= 1000
 * 0 <= nums1[i], nums2[i] <= 100
 *
 */
public class Code718 {

    public static void main(String[] args) {
        int[] nums1 = {1,2,3,2,1};
        int[] nums2 = {3,2,1,4,7};
        System.out.println(findLength11(nums1, nums2));
    }

    /**
     * 题解链接：https://leetcode.cn/problems/maximum-length-of-repeated-subarray/solution/zui-chang-zhong-fu-zi-shu-zu-by-leetcode-solution/
     */

    /**
     * 方法一：动态规划
     * 1、dp[i][j] : A[i:] 和 B[j:] 的最长公共前缀
     * 2、if A[i] == B[j], dp[i][j] = dp[i + 1][j + 1] + 1
     *    else dp[i][j] = 0
     * 3、考虑这里由 dp[i][j] 的值 由 dp[i + 1][j + 1]转移得到，故需要倒过来
     *     先计算 dp[len(A) - 1][len(B) - 1]，最后计算 dp[0][0].
     * @param nums1
     * @param nums2
     * @return
     */
    public static int findLength1(int[] nums1, int[] nums2) {
        int n = nums1.length, m = nums2.length;
        int[][] dp = new int[n + 1][m + 1];
        int ans = 0;
        for (int i = n - 1; i >= 0; i--) {
            for (int j = m - 1; j >= 0; j--) {
                dp[i][j] = nums1[i] == nums2[j] ? dp[i + 1][j + 1] + 1 : 0;
                ans = Math.max(dp[i][j], ans);
            }
        }
        for (int[] ints : dp) {
            System.out.println(Arrays.toString(ints));
        }
        return ans;
    }

    /**
     * 1、dp[i][j] ：A[:i] 与 B[:j] 的最长公共前缀
     * 2、if A[i] == B[j]，则 dp[i][j] = dp[i - 1][j - 1] + 1
     *    else dp[i][j] = 0
     * @param nums1
     * @param nums2
     * @return
     */
    public static int findLength11(int[] nums1, int[] nums2) {
        int res = 0;
        int n1 = nums1.length;
        int n2 = nums2.length;
        if(n1 == 0 || n2 == 0) {
            return res;
        }
        int[][] dp = new int[n1 + 1][n2 + 1];
        for(int i = 0; i < n1; i ++) {
            for(int j = 0; j < n2; j++) {
                if(nums1[i] == nums2[j]) {
                    dp[i + 1][j + 1] = dp[i][j] + 1;
                }
                res = Math.max(res, dp[i + 1][j + 1]);
            }
        }
        return res;
    }


    /**
     * 方法二：滑动窗口
     * nums1 = {1,2,3,2,1};  nums2 = {3,2,1,4,7};
     * 滑动两窗口，寻找 A[i] == B[j]的位置，从该位置计算子数组的位置
     * A[i] == B[j] 的位置在两数组中 的起始位置不同
     * 枚举 A 和 B 所有的对齐方式。对齐的方式有两类：
     *      第一类为 A 不变，B 的首元素与 A 中的某个元素对齐；
     *              nums1 = {1,2,3,2,1};
     *              nums2 = {3,2,1,4,7};
     *      第二类为 B 不变，A 的首元素与 B 中的某个元素对齐。
     *              nums2 = {3,2,1,4,7};
     *                nums1 = {1,2,3,2,1};
     *                          {1,2,3,2,1};
     *      对于每一种对齐方式，我们计算它们相对位置相同的重复子数组即可。
     * @param nums1
     * @param nums2
     * @return
     */
    public static int findLength2(int[] nums1, int[] nums2) {
        int n = nums1.length, m = nums2.length;
        int ret = 0;
        for (int i = 0; i < n; i++) {
            //取固定串 与 滑动串 长度的较小的一方，即该轮要比较的元素的个数
            int len = Math.min(m, n - i);
            int maxlen = maxLength(nums1,nums2,i,0,len);
            ret = Math.max(ret, maxlen);
        }
        for (int i = 0; i < m; i++) {
            int len = Math.min(n, m - i);
            int maxlen = maxLength(nums1, nums2, 0, i,len);
            ret = Math.max(ret, maxlen);
        }
        return ret;
    }

    /**
     * @param addA   A的比较 起始下标
     * @param addB   B的比较 起始下标
     * @param len   要比较的元素的个数
     * @return      公共长度最大值
     *    nums1 = {1,2,3,2,1};  nums2 = {3,2,1,4,7};
     */
    private static int maxLength(int[] nums1, int[] nums2, int addA, int addB, int len) {
        int ret = 0,k = 0;
        for (int i = 0; i < len; i++) {
            if (nums1[addA + i] == nums2[addB + i]){
                k++;
            }else {
                k = 0;
            }
            ret = Math.max(ret, k);
        }
        return ret;
    }


    /**
     * https://leetcode.cn/problems/maximum-length-of-repeated-subarray/solution/wu-li-jie-fa-by-stg-2/
     * 滑动窗口：主要思路见题解中的动图（一目了然）
     * @param nums1
     * @param nums2
     * @return
     */
    public static int findLength22(int[] nums1, int[] nums2) {
        return nums1.length <= nums2.length ? findMax(nums1,nums2) : findMax(nums2,nums1);
    }

    private static int findMax(int[] nums1, int[] nums2) {
        int max = 0;
        int m = nums1.length, n = nums2.length;
        /*
        nums1,nums2中较短的数组不动，这里默认nums1，较长的数组滑动
        初始位置：nums2右边界挨着nums1左边界，nums2从左往右滑动
         */
        // 第一阶段：nums2从左往右滑动，两数组重合部分长度不断增加，重合部分长度len从1开始增加
        // 重合部分：nums1起点下标0，nums2起点下标n-len，
        for (int len = 1; len <= m; len++) {
            max = Math.max(max, maxLength22(nums1, 0, nums2, n - len, len));
        }
        // 第二阶段：nums2从左往右滑动，两数组重合部分长度不变，重合部分长度始终为nums1长度m
        // 重合部分：nums1起点下标0，nums2起点下标n-m，然后递减
        for (int j = n - m; j >= 0; j--) {
            max = Math.max(max, maxLength22(nums1, 0, nums2, j, m));
        }
        // 第三阶段：nums2从左往右滑动，两数组重合部分长度递减，重合部分长度始终为nums1长度m-i
        // 重合部分：nums1起点下标i，递增，nums2起点下标0
        for (int i = 1; i < m; i++) {
            max = Math.max(max, maxLength22(nums1, i, nums2, 0, m - i));
        }
        return max;
    }

    private static int maxLength22(int[] nums1, int i, int[] nums2, int j, int len) {
        int count = 0, res = 0;
        for (int k = 0; k < len; k++) {
            if (nums1[i + k] == nums2[j + k]){
                count++;
            }else if (count > 0){
                res = Math.max(count, res);
                count = 0;
            }
        }
        return count > 0 ? Math.max(count, res) : res;
    }


    /**
     * 方法三：二分查找 + 哈希
     * @param nums1
     * @param nums2
     * @return
     */
    static int mod = 1000000009;
    static int base = 113;
    public static int findLength(int[] nums1, int[] nums2) {
        int left = 1, right = Math.min(nums1.length, nums2.length) + 1;
        while (left < right){
            int mid = (left + right) >> 1;
            if (check(nums1,nums2,mid)){
                left = mid + 1;
            }else {
                right = mid;
            }
        }
        return left - 1;
    }

    private static boolean check(int[] nums1, int[] nums2, int len) {
        long hashA = 0;
        for (int i = 0; i < len; i++) {
            hashA = (hashA * base + nums1[i]) % mod;
        }
        Set<Long> bucketA = new HashSet<>();
        bucketA.add(hashA);
        long mult = qPow(base,len - 1);
        for (int i = len; i < nums1.length; i++) {
            hashA = ((hashA - nums1[i - len] * mult % mod + mod) % mod * base + nums1[i]) % mod;
            bucketA.add(hashA);
        }
        long hashB = 0;
        for (int i = 0; i < len; i++) {
            hashB = (hashB * base + nums2[i]) % mod;
        }
        if (bucketA.contains(hashB)){
            return true;
        }
        for (int i = len; i < nums2.length; i++) {
            hashB = ((hashB - nums2[i - len] * mult % mod + mod) % mod * base + nums2[i]) % mod;
            if (bucketA.contains(hashB)){
                return true;
            }
        }
        return false;
    }

    //使用快速幂计算x^n % mod 的值
    private static long qPow(long x, long n) {
        long ret = 1;
        while (n != 0){
            if ((n & 1) != 0){
                ret = ret * x % mod;
            }
            x = x * x % mod;
            n >>= 1;
        }
        return ret;
    }
}


























