package CodeTop;

/**
 * @author mxy
 * @create 2023-04-27 20:05
 */

import java.util.LinkedList;

/**
 * 104. 二叉树的最大深度    链接：https://leetcode.cn/problems/maximum-depth-of-binary-tree
 * 给定一个二叉树，找出其最大深度。
 * 二叉树的深度为根节点到最远叶子节点的最长路径上的节点数。
 * 说明: 叶子节点是指没有子节点的节点。
 *
 * 示例：
 * 给定二叉树 [3,9,20,null,null,15,7]，
 *
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * 返回它的最大深度 3 。
 *
 */
public class Code104 {

    /**
     * 递归：
     * @param root
     * @return
     */
    public int maxDepth_Recur(TreeNode root) {
        if (root == null) return 0;
        return Math.max(maxDepth_Recur(root.left), maxDepth_Recur(root.right)) + 1;
    }


    /**
     * 迭代：
     * @param root
     * @return
     */
    public int maxDepth_Iter(TreeNode root) {
        if (root == null) return 0;
        int ans = 0;
        LinkedList<TreeNode> list = new LinkedList<>();
        list.offer(root);
        while (!list.isEmpty()){
            int size = list.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = list.poll();
                if (node.left != null){
                    list.offer(node.left);
                }
                if (node.right != null){
                    list.offer(node.right);
                }
            }
            ans++;
        }
        return ans;
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






























