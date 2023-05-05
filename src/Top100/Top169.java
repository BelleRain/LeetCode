package Top100;

/**
 * @author mxy
 * @create 2022-12-01 15:23
 */

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 169. 多数元素    链接：https://leetcode.cn/problems/majority-element
 * 给定一个大小为 n 的数组 nums ，返回其中的多数元素。多数元素是指在数组中出现次数 大于 ⌊ n/2 ⌋ 的元素。
 * 你可以假设数组是非空的，并且给定的数组总是存在多数元素。
 *
 * 示例 1：
 * 输入：nums = [3,2,3]
 * 输出：3
 *
 * 示例 2：
 * 输入：nums = [2,2,1,1,1,2,2]
 * 输出：2
 *  
 * 提示：
 * n == nums.length
 * 1 <= n <= 5 * 10^4
 * -10^9 <= nums[i] <= 10^9
 *  
 * 进阶：尝试设计时间复杂度为 O(n)、空间复杂度为 O(1) 的算法解决此问题。
 */
public class Top169 {

    public static void main(String[] args) {
        Top169 top169 = new Top169();
        int[] nums = {2,2,1,1,1,2,2};
        System.out.println(top169.majorityElement(nums));
    }


    /**
     * 方法一：哈希表
     */
    public int majorityElement1(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        for(int i = 0; i < nums.length; i++){
            map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
            if (map.get(nums[i]) > nums.length / 2){
                return nums[i];
            }
        }
        return 0;
    }

    /**
     * 方法二： 排序
     *  既然数组中有出现次数 > ⌊ n/2 ⌋的元素，那排好序之后的数组中，相同元素总是相邻的。
     *  即存在长度 > ⌊ n/2 ⌋ 的一长串 由相同元素构成的连续子数组。
     *  举个例子： 无论是 1 1 1 2 3，0 1 1 1 2还是-1 0 1 1 1，数组中间的元素总是“多数元素”，
     *      毕竟它长度> ⌊ n/2 ⌋。
     * 复杂度分析
     *      时间复杂度：O(nlogn)。将数组排序的时间复杂度为 O(nlogn)。
     *      空间复杂度：O(logn)。如果使用语言自带的排序算法，需要使用 O(logn) 的栈空间。
     *              如果自己编写堆排序，则只需要使用 O(1) 的额外空间。
     * @param nums
     * @return
     */
    public int majorityElement2(int[] nums) {
        Arrays.sort(nums);
        return nums[nums.length >> 1];
    }


    /**
     * 方法三：（优化） 摩尔投票法
     *
     *  1、 候选人(cand_num)初始化为 nums[0]，票数count初始化为1。
     *  2、 当遇到与 cand_num相同的数，则票数 count = count + 1，否则票数count = count - 1。
     *  3、 当票数 count 为 0 时，更换候选人，并将票数count重置为1。 遍历完数组后，cand_num 即为最终答案。
     *  4、 为何这行得通呢？
     *  5、 投票法是遇到相同的则票数 + 1，遇到不同的则票数 - 1。
     *       且“多数元素”的个数 > ⌊ n/2 ⌋，其余元素的个数总和 <= ⌊ n/2 ⌋。
     *     因此 “多数元素” 的个数 - 其余元素的个数总和 的结果 肯定 >= 1。
     *      这就相当于每个 “多数元素” 和 其他元素 两两相互抵消，抵消到最后肯定还剩余至少 1个 “多数元素”。
     *      无论数组是1 2 1 2 1，亦或是1 2 2 1 1，总能得到正确的候选人。
     *
     * 复杂度分析：
     *      时间复杂度： O(n)
     *      空间复杂度： O(1)
     * @param nums
     * @return
     */
    public int majorityElement(int[] nums) {
        int res = nums[0],count = 1;
        for(int num : nums){
            if (num == res) count++;
            else count--;
            if (count == 0){
                res = num;
                count = 1;
            }
        }
        return res;
    }

}
