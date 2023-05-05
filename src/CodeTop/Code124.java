package CodeTop;

/**
 * @author mxy
 * @create 2023-04-19 9:21
 */

import jdk.nashorn.internal.ir.IdentNode;

/**
 * 124. 二叉树中的最大路径和     链接：https://leetcode.cn/problems/binary-tree-maximum-path-sum
 * 二叉树中的 路径 被定义为一条节点序列，序列中每对相邻节点之间都存在一条边。
 * 同一个节点在一条路径序列中 至多出现一次 。
 * 该路径 至少包含一个 节点，且不一定经过根节点。
 * 路径和 是路径中各节点值的总和。
 *
 * 给你一个二叉树的根节点 root ，返回其 最大路径和 。
 *
 * 示例 1：
 * 输入：root = [1,2,3]
 * 输出：6
 * 解释：最优路径是 2 -> 1 -> 3 ，路径和为 2 + 1 + 3 = 6
 *
 * 示例 2：
 * 输入：root = [-10,9,20,null,null,15,7]
 * 输出：42
 * 解释：最优路径是 15 -> 20 -> 7 ，路径和为 15 + 20 + 7 = 42
 *  
 *
 * 提示：
 * 树中节点数目范围是 [1, 3 * 10^4]
 * -1000 <= Node.val <= 1000
 *
 */
public class Code124 {

    /**
     * 题解链接：https://leetcode.cn/problems/binary-tree-maximum-path-sum/solution/shou-hui-tu-jie-hen-you-ya-de-yi-dao-dfsti-by-hyj8/
     * 详细看题解中的解题思路
     * @param root
     * @return
     */
    int result = Integer.MIN_VALUE;
    public int maxPathSum(TreeNode root) {
        dfs(root);
        return result;
    }

    /**
     * 返回当前节点能够为父亲提供的贡献，需要结合题解中的图来看
     * @param root
     * @return
     */
    private int dfs(TreeNode root) {
        //如果当前节点为叶子节点，那么对父亲的贡献为0
        if (root == null) return 0;
        //如果不是叶子节点，计算当前节点的左右孩子对自身的贡献 left 和 right
        int left = dfs(root.left); //左子树提供的最大路径和
        int right = dfs(root.right); //右子树提供的最大路径和
        //更新最大值，就是当前节点的val加上左右节点的贡献
        //当前子树内部的最大路径和
        result = Math.max(result, root.val + left + right);
        //计算当前节点能为父亲提供的最大贡献，必须是把val加上
        //当前子树对外提供的最大路径和
        int max = Math.max(root.val + left, root.val + right);
        //如果贡献小于0的话，直接返回0即可
        //如果对外提供的路径和为负，直接返回0,。否则正常返回。
        return max < 0 ? 0 : max;
    }


    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}
