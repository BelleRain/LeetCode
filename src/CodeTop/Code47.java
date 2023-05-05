package CodeTop;

/**
 * @author mxy
 * @create 2023-04-14 15:16
 */

import java.util.*;

/**
 * 47. 全排列 II   链接：https://leetcode.cn/problems/permutations-ii
 * 给定一个可包含重复数字的序列 nums ，按任意顺序 返回所有不重复的全排列。
 *
 * 示例 1：
 * 输入：nums = [1,1,2]
 * 输出：
 * [[1,1,2],
 *  [1,2,1],
 *  [2,1,1]]
 *
 * 示例 2：
 * 输入：nums = [1,2,3]
 * 输出：[[1,2,3],[1,3,2],[2,1,3],[2,3,1],[3,1,2],[3,2,1]]
 *
 * 提示：
 * 1 <= nums.length <= 8
 * -10 <= nums[i] <= 10
 *
 */
public class Code47 {

    public static void main(String[] args) {
        int[] nums = {1,1,2};
        System.out.println(permuteUnique(nums));
    }


    /**
     * 题解链接： https://leetcode.cn/problems/permutations-ii/solution/hui-su-suan-fa-python-dai-ma-java-dai-ma-by-liwe-2/
     * @param nums
     * @return
     */
    public static List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        boolean[] visited = new boolean[nums.length];
        Deque<Integer> path = new LinkedList<>();
        if (nums.length == 0) return res;
        //排序是剪枝的前提
        Arrays.sort(nums);
        dfs(res,nums,visited,path,0);
        return res;
    }

    private static void dfs(List<List<Integer>> res, int[] nums, boolean[] visited, Deque<Integer> path, int level) {
        if (level == nums.length){
            res.add(new ArrayList<>(path));
        }
        for (int i = 0; i < nums.length; i++) {
            if (visited[i]) continue;
            // 剪枝条件：i > 0 是为了保证 nums[i - 1] 有意义
            // 写 !used[i - 1] 是因为 nums[i - 1] 在深度优先遍历的过程中刚刚被撤销选择
            if (i > 0 && nums[i] == nums[i - 1] && !visited[i - 1]) continue;
            path.addLast(nums[i]);
            visited[i] = true;
            dfs(res, nums, visited, path, level + 1);
            // 回溯部分的代码，和 dfs 之前的代码是对称的
            visited[i] = false;
            path.removeLast();
        }
    }
}






































