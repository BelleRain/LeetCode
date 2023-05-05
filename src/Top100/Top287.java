package Top100;

/**
 * @author mxy
 * @create 2023-01-09 9:42
 */

import java.util.Arrays;

/**
 * 287. 寻找重复数   链接：https://leetcode.cn/problems/find-the-duplicate-number
 * 给定一个包含 n + 1 个整数的数组 nums ，其数字都在 [1, n] 范围内（包括 1 和 n），可知至少存在一个重复的整数。
 * 假设 nums 只有 一个重复的整数 ，返回 这个重复的数 。
 * 你设计的解决方案必须 不修改 数组 nums 且只用常量级 O(1) 的额外空间。
 *
 * 示例 1：
 * 输入：nums = [1,3,4,2,2]
 * 输出：2
 *
 * 示例 2：
 * 输入：nums = [3,1,3,4,2]
 * 输出：3
 *  
 * 提示：
 * 1 <= n <= 10^5
 * nums.length == n + 1
 * 1 <= nums[i] <= n
 * nums 中 只有一个整数 出现 两次或多次 ，其余整数均只出现 一次
 *  
 * 进阶：
 * 如何证明 nums 中至少存在一个重复的数字?
 * 你可以设计一个线性级时间复杂度 O(n) 的解决方案吗？
 *
 */
public class Top287 {

    public static void main(String[] args) {
        Top287 top287 = new Top287();
        int[] nums = {3,1,3,4,2};
        System.out.println(top287.findDuplicate(nums));
    }

    /*
    重复问题：
        1、暴力解法
        2、哈希表 set 去重
        3、二分查找
        4、快慢指针，判断环的存在
        5、位运算
     */

    /**
     * 题解链接：https://leetcode.cn/problems/find-the-duplicate-number/solution/er-fen-fa-si-lu-ji-dai-ma-python-by-liweiwei1419/
     * 方法一：二分查找 （二分答案）
     * 复杂度分析：
     *      时间复杂度：O(NlogN)，二分法的时间复杂度为 O(logN)，在二分法的内部，执行了一次 for 循环，时间复杂度为 O(N)，故时间复杂度为 O(NlogN)。
     *      空间复杂度：O(1)，使用了一个 cnt 变量，因此空间复杂度为 O(1)。
     */
    /*public int findDuplicate(int[] nums) {
        int len = nums.length; // n+1 = len, n = len - 1
        //在 [1...n] 查找 nums 中 重复的元素
        int left = 1;
        int right = len - 1;
        while (left < right) {
            int mid = (left + right) / 2;
            //nums 中小于等于mid的元素的个数
            int count = 0;
            for (int num : nums) {
                if (num <= mid) count++;
            }
            if (count > mid) {
                //下一轮的搜索区间[left...mid]
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }*/

    /**
     * 题解链接：https://leetcode.cn/problems/find-the-duplicate-number/solution/xun-zhao-zhong-fu-shu-by-leetcode-solution/
     */

    /**
     * 题解二： 快慢指针 （Floyd判圈算法（龟兔赛跑算法）类比题目： 141.环形链表、 142.环形链表II）
     *  相遇问题：
     *      1、寻找相遇点，慢指针每次走一步，快指针每次走两步。
     *         当 slow = fast 时，即为相遇点
     *      2、慢指针从起点出发，快指针从相遇出发，每次两个指针都移动一步，
     *         则，相遇点即为环的入口。
     *      3、相遇点即为重复的数字。
     *  复杂度分析:
     *      时间复杂度：O(n)。「Floyd 判圈算法」时间复杂度为线性的时间复杂度。
     *      空间复杂度：O(1)。我们只需要常数空间存放若干变量。
     * @param nums
     * @return
     */
    /*public int findDuplicate(int[] nums) {
        int slow = 0, fast = 0;
        slow = nums[slow];
        fast = nums[nums[fast]];
        //寻找相遇点
        while (slow != fast){
            slow = nums[slow];
            fast = nums[nums[fast]];
        }
        //寻找环入口
        slow = 0;
        while (slow != fast){
            slow = nums[slow];
            fast = nums[fast];
        }
        return slow;
    }*/


    /**
     * 题解三：位运算
     * 复杂度分析：
     *      时间复杂度：O(nlogn)，其中 n 为 nums 数组的长度。O(logn) 代表了我们枚举二进制数的位数个数，枚举第 i 位的时候需要遍历数组统计 x 和 y 的答案，
     *              因此总时间复杂度为 O(nlogn)。
     *      空间复杂度：O(1)。我们只需要常数空间存放若干变量。
     * @param nums
     * @return
     */
    public int findDuplicate(int[] nums) {
        int n = nums.length,ans = 0;
        int bit_max = 31;
        while (((n - 1) >> bit_max) == 0){
            bit_max -= 1;
        }
        for (int bit = 0; bit <= bit_max; ++bit) {
            int x = 0, y = 0;
            for (int i = 0; i < n; ++i) {
                if ((nums[i] & (1 << bit)) != 0){
                    x += 1;
                }
                if (i >= 1 && ((i & (1 << bit)) != 0)){
                    y += 1;
                }
            }
            if (x > y){
                ans |= 1 << bit ;
            }
        }
        return ans;
    }

    /**
     * 执行用时： 35 ms , 在所有 Java 提交中击败了 11.61% 的用户
     * 内存消耗： 58.8 MB , 在所有 Java 提交中击败了 43.94% 的用户
     * @param nums
     * @return
     */
    /*public int findDuplicate(int[] nums) {
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i++) {
            //if (nums[i] - i == 0) return nums[i];
            if ((nums[i] ^ i)== 0) return nums[i];
        }
        return 0;
    }*/
}
