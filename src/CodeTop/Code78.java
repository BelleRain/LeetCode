package CodeTop;

/**
 * @author mxy
 * @create 2023-04-26 11:08
 */

import java.util.ArrayList;
import java.util.List;

/**
 * 78. 子集   链接：https://leetcode.cn/problems/subsets
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
 *
 */
public class Code78 {

    public static void main(String[] args) {
        int[] nums = {1,2,3};
        System.out.println(subsets(nums));
    }

    public static List<List<Integer>> subsets1(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        if (nums.length == 0) return res;
        res.add(new ArrayList<>());
        List<Integer> path = new ArrayList<>();
        recur(res , nums, 0, nums.length,path);
        return res;
    }

    private static void recur(List<List<Integer>> res, int[] nums, int begin, int length, List<Integer> path) {
        if (begin == length) return;
        for (int i = begin; i < length; i++) {
            path.add(nums[i]);
            res.add(new ArrayList<>(path));
            //System.out.println(path);
            //System.out.println(res);
            recur(res, nums, i + 1, length, path);
            path.remove(path.size() - 1);
            //System.out.println("=======>" + path);
        }
    }

    static List<Integer> t = new ArrayList<Integer>();
    static List<List<Integer>> ans = new ArrayList<List<Integer>>();
    public static List<List<Integer>> subsets(int[] nums) {
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































