package Top100;

/**
 * @author mxy
 * @create 2022-11-23 12:11
 */

import java.util.*;

/**
 * 78. 子集    链接：https://leetcode.cn/problems/subsets
 * 给你一个整数数组 nums ，数组中的元素 互不相同 。返回该数组所有可能的子集（幂集）。
 * 解集 不能 包含重复的子集。你可以按 任意顺序 返回解集。
 *
 * 示例 1：
 * 输入：nums = [1,2,3]
 * 输出：[[],[1],[2],[1,2],[3],[1,3],[2,3],[1,2,3]]
 *
 * 示例 2：
 * 输入：nums = [0]
 * 输出：[[],[0]]
 *  
 * 提示：
 * 1 <= nums.length <= 10
 * -10 <= nums[i] <= 10
 * nums 中的所有元素 互不相同
 */
public class Top78 {

    public static void main(String[] args) {
        Top78 top78 = new Top78();
        int[] nums = {1,2,3};
        //int[] nums = {0};
        System.out.println(top78.subsets(nums));
    }

    /**
     * 回溯 ： 由提示得，1 <= nums.length <= 10
     * 执行用时： 0 ms , 在所有 Java 提交中击败了 100.00% 的用户
     * 内存消耗： 41.4 MB , 在所有 Java 提交中击败了 66.76% 的用户
     * @param nums
     * @return
     */
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        res.add(new ArrayList<>());
        int len = nums.length;
        Deque<Integer> path = new ArrayDeque<>();
        dfs(nums,len,0,path,res);
        return res;
    }

    /**
     * 深度优先遍历
     * @param nums 原数组
     * @param len  原数组的长度
     * @param begin  每层递归的起始点
     * @param path   每个子集，从根结点到该节点的路径
     * @param res    结果集，对于该题来说，到达一个节点，则将其加入至res中，而不是等到最后一层加入
     */
    private void dfs(int[] nums, int len, int begin, Deque<Integer> path,List<List<Integer>> res) {
        if (begin == len) return;
        for (int i = begin; i < len; i++) {
            path.addLast(nums[i]);
            res.add(new ArrayList<>(path));
            dfs(nums, len, i + 1, path,res); //每次的起始点，为该节点的后一个节点
            path.removeLast(); //回溯
        }
    }


    /**
     * 题解链接：https://leetcode.cn/problems/subsets/solution/zi-ji-by-leetcode-solution/
     * 方法一： 迭代法实现子集枚举
     *         利用二进制位运算
     *         （很巧妙的一种方法，详细题解见原文链接）
     */
    List<Integer> t = new ArrayList<Integer>();
    List<List<Integer>> ans = new ArrayList<List<Integer>>();
    public List<List<Integer>> subsets1(int[] nums) {
        int n = nums.length;
        for (int mask = 0; mask < (1 << n); ++mask) {
            t.clear();
            for (int i = 0; i < n; ++i) {
                if ((mask & (1 << i)) != 0) {
                    t.add(nums[i]);
                }
            }
            ans.add(new ArrayList<Integer>(t));
        }
        return ans;
    }
}
