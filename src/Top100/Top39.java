package Top100;

/**
 * @author mxy
 * @create 2022-11-18 15:40
 */

import java.lang.reflect.Array;
import java.util.*;

/**
 * 39. 组合总和   链接：https://leetcode.cn/problems/combination-sum
 * 给你一个 无重复元素 的整数数组 candidates 和一个目标整数 target ，
 * 找出 candidates 中可以使数字和为目标数 target 的 所有 不同组合 ，并以列表形式返回。你可以按 任意顺序 返回这些组合。
 * candidates 中的 同一个 数字可以 无限制重复被选取 。如果至少一个数字的被选数量不同，则两种组合是不同的。 
 * 对于给定的输入，保证和为 target 的不同组合数少于 150 个。
 *
 * 示例 1：
 * 输入：candidates = [2,3,6,7], target = 7
 * 输出：[[2,2,3],[7]]
 * 解释：
 * 2 和 3 可以形成一组候选，2 + 2 + 3 = 7 。注意 2 可以使用多次。
 * 7 也是一个候选， 7 = 7 。
 * 仅有这两种组合。
 *
 * 示例 2：
 * 输入: candidates = [2,3,5], target = 8
 * 输出: [[2,2,2,2],[2,3,3],[3,5]]
 *
 * 示例 3：
 * 输入: candidates = [2], target = 1
 * 输出: []
 *  
 * 提示：
 * 1 <= candidates.length <= 30
 * 2 <= candidates[i] <= 40
 * candidates 的所有元素 互不相同
 * 1 <= target <= 40
 */
public class Top39 {

    public static void main(String[] args) {
        Top39 top39 = new Top39();
        int[] nums = {2,3,6,7};
        System.out.println(top39.combinationSum(nums, 7));
    }

    /**
     * 回溯算法总结 + 例题 链接：https://leetcode-cn.com/problems/permutations/solution/hui-su-suan-fa-python-dai-ma-java-dai-ma-by-liweiw/
     * 画图 ！！！
     */
    /**
     * 题解链接：https://leetcode.cn/problems/combination-sum/solution/hui-su-suan-fa-jian-zhi-python-dai-ma-java-dai-m-2/
     * 什么时候使用 used 数组，什么时候使用 begin 变量
     * 有些朋友可能会疑惑什么时候使用 used 数组，什么时候使用 begin 变量。这里为大家简单总结一下：
     *  1、 排列问题，讲究顺序（即 [2, 2, 3] 与 [2, 3, 2] 视为不同列表时），需要记录哪些数字已经使用过，此时用 used 数组；
     *  2、 组合问题，不讲究顺序（即 [2, 2, 3] 与 [2, 3, 2] 视为相同列表时），需要按照某种顺序搜索，此时使用 begin 变量。
     */
    /**
     *
     * 方法一：未剪枝
     * @param candidates
     * @param target
     * @return
     */
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        int len = candidates.length;
        List<List<Integer>> res = new ArrayList<>();
        if (len == 0) return res;
        Deque<Integer> path = new ArrayDeque<>();
        dfs(candidates, 0, len, target, path, res);
        return res;
    }

    /**
     * 深度优先遍历
     * @param candidates 候选数组
     * @param begin      搜索起点 (设置起点，去除冗余结果)
     * @param len        冗余变量，是candidates里的属性，可以不传
     * @param target     每减去一个元素，目标值变小
     * @param path       从根结点到叶子结点的路径，是一个栈
     * @param res        结果集列表
     */
    public void dfs(int[] candidates,int begin,int len,int target,Deque<Integer> path,List<List<Integer>> res){
        //target为负数和0的时候，不再产生新的孩子结点
        if (target < 0) return;
        if (target == 0){
            res.add(new ArrayList<>(path));
            return;
        }
        //重点理解这里从begin开始搜索的语意
        for (int i = begin; i < len; i++) {
            path.addLast(candidates[i]);
            //注意：由于每一个元素可以重复使用，下一轮搜索的起点依然是i，这里非常容易弄错
            dfs(candidates, i, len, target - candidates[i], path, res);
            //状态重置，回溯
            path.removeLast();
        }
    }

    /**
     * 剪枝提速
     * @param candidates
     * @param target
     * @return
     */
    public List<List<Integer>> combinationSum1(int[] candidates, int target) {
        int len = candidates.length;
        List<List<Integer>> res = new ArrayList<>();
        if (len == 0) return res;
        //排序是剪枝的前提
        Arrays.sort(candidates);
        Deque<Integer> path = new ArrayDeque<>();
        dfs1(candidates, 0, len, target, path, res);
        return res;
    }

    public void dfs1(int[] candidates,int begin,int len,int target,Deque<Integer> path,List<List<Integer>> res){
        //由于进入更深层的时候，小于0的部分被剪枝，因此递归终止条件值只判断等于0的情况
        if (target == 0){
            res.add(new ArrayList<>(path));
            return;
        }
        for (int i = begin; i < len; i++) {
            //重点理解这里剪枝，前提是候选数组已经有序
            if (target - candidates[i] < 0) break;
            path.addLast(candidates[i]);
            dfs1(candidates, i, len, target - candidates[i], path, res);
            path.removeLast();
        }
    }
}
