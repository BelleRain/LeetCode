package Top100;

/**
 * @author mxy
 * @create 2022-11-21 8:15
 */

import java.util.*;

/**
 * 46. 全排列  链接：https://leetcode.cn/problems/permutations
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
 * 提示：
 * 1 <= nums.length <= 6
 * -10 <= nums[i] <= 10
 * nums 中的所有整数 互不相同
 *
 */
public class Top46 {

    public static void main(String[] args) {
        Top46 top46 = new Top46();
        int[] nums = {1,2,3};
        System.out.println(top46.permute(nums));
    }


    /**
     * 题解参考：1、官方视频讲解： https://leetcode.cn/problems/permutations/solution/quan-pai-lie-by-leetcode-solution-2/
     *          2、详细题解：https://leetcode.cn/problems/permutations/solution/hui-su-suan-fa-python-dai-ma-java-dai-ma-by-liweiw/
     * 回溯算法的时间复杂度是 指数级别，不需要掌握，是估算出来的结果
     * 利用深度优先搜索比广度优先节约空间
     * 该题：时间复杂度　：O(n*n!)
     *       空间复杂度 ：O(n*n!)
     * （画图）
     * @param nums
     * @return
     */
    public List<List<Integer>> permute(int[] nums) {
        int len = nums.length;
        if (len == 0) return new ArrayList<>();
        List<List<Integer>> res = new ArrayList<>();
        Deque<Integer> path = new ArrayDeque<Integer>();
        boolean[] used = new boolean[len];
        dfs(nums,len,0,path,used,res);
        return res;
    }

    /**
     * 深度优先遍历
     *      1、状态：每一个结点表示了求解问题的不同阶段
     *      2、深度优先遍历在回到上一层结点时需要“状态重置”
     * 状态变量：depth、path、used
     * @param nums  原数组
     * @param len   原数组的长度（冗余变量，可以省略，为防止多次调用nums的length属性）
     * @param depth 递归到了第几层
     * @param path  已经选了哪些数path
     * @param used  布尔数组，存储是否被使用
     * @param res   结果集
     */
    private void dfs(int[] nums, int len, int depth, Deque<Integer> path, boolean[] used, List<List<Integer>> res) {
        if (depth == len) { //已经到最后一层，将path加入结果集
            res.add(new ArrayList<>(path)); // path只是引用，在递归过程中会产生空栈，所以为防止结果集中出现空列表[],为其包装一层ArrayList，不会在结果集中有[];
            return; //该分支结束
        }
        for (int i = 0; i < len; i++) {
            if (used[i]) continue; //使用过，跳过这个数，未使用过，加入栈path中
            path.addLast(nums[i]); //path作为一个栈，从末尾加入
            used[i] = true;
            dfs(nums, len, depth + 1, path, used, res);
            //递归一层结束后，要返回上一层结点，进行回溯。回溯：对之前的操作进行逆操作
            path.removeLast();
            used[i] = false;
        }
    }


}
