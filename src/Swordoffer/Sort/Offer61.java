package Swordoffer.Sort;

/**
 * @author mxy
 * @create 2022-10-02 7:45
 */

import java.util.HashSet;
import java.util.Set;

/**
 * 扑克牌中的顺子
 * 从若干副扑克牌中随机抽 5 张牌，判断是不是一个顺子，即这5张牌是不是连续的。2～10为数字本身，A为1，J为11，Q为12，K为13，而大、小王为 0 ，可以看成任意数字。A 不能视为 14。
 * 示例 1:
 * 输入: [1,2,3,4,5]
 * 输出: True
 *  
 * 示例 2:
 * 输入: [0,0,1,2,5]
 * 输出: True
 *
 * 限制：
 * 数组长度为 5 
 * 数组的数取值为 [0, 13] .
 */
public class Offer61 {
    public static void main(String[] args) {
        Offer61 offer = new Offer61();
        int[] nums = new int[]{0,0,1,3,5};
        System.out.println(offer.isStraight(nums));
    }

    /**
     * 解题思路： （图解）原文链接：https://leetcode.cn/problems/bu-ke-pai-zhong-de-shun-zi-lcof/solution/mian-shi-ti-61-bu-ke-pai-zhong-de-shun-zi-ji-he-se/
     * 根据题意，此 5 张牌是顺子的 充分条件 如下：
     *    1、 除大小王外，所有牌 无重复 ；
     *    2、 设此 5 张牌中最大的牌为 max ，最小的牌为 min （大小王除外），则需满足：
     *            max - min < 5
     *        (考虑：0个大小王，1个大小王，2个大小王，注意：大小王可以代替任何值与原数列组成顺子，这5个数必须是连续的)
     * 因而，可将问题转化为：此 5张牌是否满足以上两个条件？
     */

    /**
     *方法一： 集合 Set + 遍历
     *  1、遍历五张牌，遇到大小王（即 0 ）直接跳过。
     *  2、判别重复： 利用 Set 实现遍历判重， Set 的查找方法的时间复杂度为 O(1) ；
     *  3、获取最大 / 最小的牌： 借助辅助变量 ma和 mi ，遍历统计即可。
     * 复杂度分析：
     *      时间复杂度 O(N) = O(5) = O(1) ： 其中 N 为 nums 长度，本题中 N = 5 ；遍历数组使用 O(N) 时间。
     *      空间复杂度 O(N) = O(5) = O(1) ： 用于判重的辅助 Set 使用 O(N) 额外空间。
     *
     * @param nums
     * @return
     */
    public boolean isStraight(int[] nums) {
        int max = 0,min = 14;
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) continue;  //跳过大小王
            max = Math.max(max, nums[i]); //最大牌
            min = Math.min(min, nums[i]); //最小牌
            if (set.contains(nums[i])) return false;//若有重复，提前返回false
            set.add(nums[i]);
        }
        return max - min < 5; //最大牌-最小牌<5,可构成顺子
    }

    /**
     * 方法二：排序 + 遍历
     *  1、先对数组执行排序。
     *  2、判别重复： 排序数组中的相同元素位置相邻，因此可通过遍历数组，判断 nums[i] = nums[i + 1] 是否成立来判重。
     *  3、获取最大 / 最小的牌： 排序后，数组末位元素 nums[4] 为最大牌；元素 nums[joker] 为最小牌，其中 joker 为大小王的数量。
     *
     * 复杂度分析：
     *      时间复杂度 O(NlogN)=O(5log5)=O(1) ： 其中 N 为 nums 长度，本题中 N = 5 ；数组排序使用 O(NlogN) 时间。
     *      空间复杂度 O(1) ： 变量 joker 使用 O(1) 大小的额外空间。
     * @param nums
     * @return
     */
    //public boolean isStraight(int[] nums) {
    //    int joker = 0;
    //    Arrays.sort(nums);
    //    for (int i = 0; i < nums.length-1; i++) { //按题意，最多2个0。已排序，不用特殊考虑最后一位为0的情况
    //        if (nums[i] == 0) joker++;
    //        else if (nums[i] == nums[i+1]) return false;
    //    }
    //    return nums[4] - nums[joker] < 5;
    //}
}
























