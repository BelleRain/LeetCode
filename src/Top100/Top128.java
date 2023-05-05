package Top100;

/**
 * @author mxy
 * @create 2022-11-28 9:25
 */

import java.util.HashSet;
import java.util.Set;

/**
 * 128. 最长连续序列    链接：https://leetcode.cn/problems/longest-consecutive-sequence
 * 给定一个未排序的整数数组 nums ，找出数字连续的最长序列（不要求序列元素在原数组中连续）的长度。
 * 请你设计并实现时间复杂度为 O(n) 的算法解决此问题。
 *
 * 示例 1：
 * 输入：nums = [100,4,200,1,3,2]
 * 输出：4
 * 解释：最长数字连续序列是 [1, 2, 3, 4]。它的长度为 4。
 *
 * 示例 2：
 * 输入：nums = [0,3,7,2,5,8,4,6,0,1]
 * 输出：9
 *  
 * 提示：
 * 0 <= nums.length <= 10^5
 * -10^9 <= nums[i] <= 10^9
 */
public class Top128 {

    public static void main(String[] args) {
        Top128 top128 = new Top128();
        int[] nums = {100,4,200,1,3,2};
        System.out.println(top128.longestConsecutive(nums));
    }

    /*
    并查集
     */

    /**
     * 题解一： 题解链接：https://leetcode.cn/problems/longest-consecutive-sequence/solution/zui-chang-lian-xu-xu-lie-by-leetcode-solution/
     * 方法一：哈希表
     * 复杂度分析：
     *      时间复杂度：O(n)，其中 n 为数组的长度。具体分析已在上面正文中给出。
     *      空间复杂度：O(n)。哈希表存储数组中所有的数需要 O(n) 的空间。
     * @param nums
     * @return
     */
    public int longestConsecutive(int[] nums) {
        //1、HashSet 去重
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }
        int longStreak = 0; //
        for (Integer num : set) {
            //2、如果 set 集合中不包含 num - 1，则更新 correnrtStreak的 长度;如果包含，则直接跳过，
            // 若 num-1 存在，则统计 num 时会由 num - 1 得到
            if (!set.contains(num - 1)){
                int correntNum = num;
                int correntStreak = 1;
                //3、判断 num + 1，num + 2，num + 3 ，....,是否存在 在set中，更新 correnrtStreak
                while (set.contains(correntNum + 1)){
                    correntNum = correntNum + 1;
                    correntStreak++;
                }
                //4、更新 longStreak
                longStreak = Math.max(longStreak, correntStreak);
            }
        }
        return longStreak;
    }
}
