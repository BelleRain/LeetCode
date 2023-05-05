package CodeTop;

/**
 * @author mxy
 * @create 2023-04-14 14:34
 */

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * 46. 全排列    链接：https://leetcode.cn/problems/permutations
 * 给定一个不含重复数字的数组 nums ，返回其 所有可能的全排列 。你可以 按任意顺序 返回答案。
 *
 * 示例 1：
 * 输入：nums = [1,2,3]
 * 输出：[[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
 *
 * 示例 2：
 * 输入：nums = [0,1]
 * 输出：[[0,1],[1,0]]
 *
 * 示例 3：
 * 输入：nums = [1]
 * 输出：[[1]]
 *  
 *
 * 提示：
 * 1 <= nums.length <= 6
 * -10 <= nums[i] <= 10
 * nums 中的所有整数 互不相同
 *
 */
public class Code46 {

    public static void main(String[] args) {
        int[] nums = {1,2,3};
        System.out.println(permute(nums));
    }


    public static List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        boolean[] visited = new boolean[nums.length];
        Deque <Integer> temp = new LinkedList<>();
        if (nums.length == 0) return res;
        backTrack(res,visited,nums,temp,0);
        return res;
    }

    private static void backTrack(List<List<Integer>> res, boolean[] visited, int[] nums,Deque <Integer> temp,int level) {
        if (level == nums.length) {
            res.add(new ArrayList<>(temp));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            if (visited[i]) continue;
            temp.addLast(nums[i]);
            visited[i] = true;
            backTrack(res, visited, nums, temp,level + 1);
            temp.removeLast();
            visited[i] = false;
        }
    }
}




































