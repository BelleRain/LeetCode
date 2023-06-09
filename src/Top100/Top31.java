package Top100;

/**
 * @author mxy
 * @create 2022-11-18 8:10
 */

import java.util.Arrays;

/**
 * 31. 下一个排列  链接：https://leetcode.cn/problems/next-permutation
 * 整数数组的一个 排列  就是将其所有成员以序列或线性顺序排列。
 * 例如，arr = [1,2,3] ，以下这些都可以视作 arr 的排列：[1,2,3]、[1,3,2]、[3,1,2]、[2,3,1] 。
 * 整数数组的 下一个排列 是指其整数的下一个字典序更大的排列。
 * 更正式地，如果数组的所有排列根据其字典顺序从小到大排列在一个容器中，那么数组的 下一个排列 就是在这个有序容器中排在它后面的那个排列。
 * 如果不存在下一个更大的排列，那么这个数组必须重排为字典序最小的排列（即，其元素按升序排列）。
 *
 * 例如，arr = [1,2,3] 的下一个排列是 [1,3,2] 。
 * 类似地，arr = [2,3,1] 的下一个排列是 [3,1,2] 。
 * 而 arr = [3,2,1] 的下一个排列是 [1,2,3] ，因为 [3,2,1] 不存在一个字典序更大的排列。
 * 给你一个整数数组 nums ，找出 nums 的下一个排列。
 *
 * 必须 原地 修改，只允许使用额外常数空间。
 *
 * 示例 1：
 * 输入：nums = [1,2,3]
 * 输出：[1,3,2]
 *
 * 示例 2：
 * 输入：nums = [3,2,1]
 * 输出：[1,2,3]
 *
 * 示例 3：
 * 输入：nums = [1,1,5]
 * 输出：[1,5,1]
 *  
 * 提示：
 * 1 <= nums.length <= 100
 * 0 <= nums[i] <= 100
 *
 */
public class Top31 {

    public static void main(String[] args) {
        Top31 top31 = new Top31();
        int[] nums = {1,2,1};
        top31.nextPermutation(nums);
        System.out.println(Arrays.toString(nums));
    }

    /**
     * 官方题解链接：https://leetcode.cn/problems/next-permutation/solution/xia-yi-ge-pai-lie-by-leetcode-solution/
     * 详细图文题解链接：https://leetcode.cn/problems/next-permutation/solution/xia-yi-ge-pai-lie-suan-fa-xiang-jie-si-lu-tui-dao-/
     */

    /**
     * 思路分析：
     *  1、我们希望下一个数比当前数大，这样才满足“下一个排列”的定义。因此只需要将后面的「大数」与前面的「小数」交换，就能得到一个更大的数。
     *     比如 123456，将 5 和 6 交换就能得到一个更大的数 123465。
     *  2、我们还希望下一个数增加的幅度尽可能的小，这样才满足“下一个排列与当前排列紧邻“的要求。为了满足这个要求，我们需要：
     *      1、在尽可能靠右的低位进行交换，需要从后向前查找
     *      2、将一个 尽可能小的「大数」 与前面的「小数」交换。比如 123465，下一个排列应该把 5 和 4 交换而不是把 6 和 4 交换
     *      3、将「大数」换到前面后，需要将「大数」后面的所有数重置为升序，升序排列就是最小的排列。
     *       以 123465 为例：首先按照上一步，交换 5 和 4，得到 123564；然后需要将 5 之后的数重置为升序，得到 123546。显然 123546 比 123564 更小，123546 就是 123465 的下一个排列
     * 算法流程：
     *  标准的“下一个排列”算法可以描述为：
     *  1、从后向前查找第一个相邻升序的元素对 (i,j)，满足 A[i] < A[j]。此时 [j,end) 必然是降序
     *  2、在 [j,end) 从后向前查找第一个满足 A[i] < A[k] 的 k。A[i]、A[k] 分别就是上文所说的「小数」（修改位置尽可能靠右）、「大数」（尽可能小）
     *  3、将 A[i] 与 A[k] 交换
     *  4、可以断定这时 [j,end) 必然是降序，逆置 [j,end)，使其升序
     *  5、如果在步骤 1 找不到符合的相邻元素对，说明当前 [begin,end) 为一个降序顺序，则直接跳到步骤 4
     * 复杂度分析
     *      时间复杂度：O(N)，其中 N 为给定序列的长度。我们至多只需要扫描两次序列，以及进行一次反转操作。
     *      空间复杂度：O(1)，只需要常数的空间存放若干变量。
     * @param nums
     */
    public void nextPermutation(int[] nums) {
        int i = nums.length - 2;
        while (i >= 0 && nums[i] >= nums[i+1]) i--; //只有在nums[i] < nums[i+1]时,取小数 nums[i]
        if (i >= 0){
            int j = nums.length - 1;
            while (j >=0 && nums[i] >= nums[j]) j--; //只有在nums[i] < nums[j] 时，取大数 nums[j]
            swap(nums,i,j);
        }
        reverse(nums, i + 1); //若i<0，则证明该nums全部为降序排列，此时nums为最大值；则直接反转nums，使其升序排列，取最小值
    }

    public void swap(int[] nums,int i,int j){
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public void reverse(int[] nums,int start){
        int left = start,right = nums.length - 1;
        while (left < right){
            swap(nums, left, right);
            left++;
            right--;
        }
    }
}
