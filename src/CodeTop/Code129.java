package CodeTop;

/**
 * @author mxy
 * @create 2023-04-27 8:09
 */

import java.util.LinkedList;
import java.util.Queue;

/**
 * 129. 求根节点到叶节点数字之和    链接：https://leetcode.cn/problems/sum-root-to-leaf-numbers
 * 给你一个二叉树的根节点 root ，树中每个节点都存放有一个 0 到 9 之间的数字。
 * 每条从根节点到叶节点的路径都代表一个数字：
 *
 * 例如，从根节点到叶节点的路径 1 -> 2 -> 3 表示数字 123 。
 * 计算从根节点到叶节点生成的 所有数字之和 。
 * 叶节点 是指没有子节点的节点。
 *
 * 示例 1：
 * 输入：root = [1,2,3]
 * 输出：25
 * 解释：
 * 从根到叶子节点路径 1->2 代表数字 12
 * 从根到叶子节点路径 1->3 代表数字 13
 * 因此，数字总和 = 12 + 13 = 25
 *
 * 示例 2：
 * 输入：root = [4,9,0,5,1]
 * 输出：1026
 * 解释：
 * 从根到叶子节点路径 4->9->5 代表数字 495
 * 从根到叶子节点路径 4->9->1 代表数字 491
 * 从根到叶子节点路径 4->0 代表数字 40
 * 因此，数字总和 = 495 + 491 + 40 = 1026
 *
 * 提示：
 * 树中节点的数目在范围 [1, 1000] 内
 * 0 <= Node.val <= 9
 * 树的深度不超过 10
 *
 */
public class Code129 {

    public static void main(String[] args) {
        TreeNode node1 = new TreeNode(4);
        TreeNode node2 = new TreeNode(9);
        TreeNode node3 = new TreeNode(0);
        TreeNode node4 = new TreeNode(5);
        TreeNode node5 = new TreeNode(1);

        node1.left = node2;
        node1.right = node3;
        node2.left = node4;
        node2.right = node5;

        System.out.println(sumNumbers(node1));
    }

    /**
     * dfs：
     * @param root
     * @return
     */
    public static int sumNumbers1(TreeNode root) {
        if (root == null || (root.left == null && root.right == null)) return root.val;
        return recur(root,0);
    }

    private static int recur(TreeNode root, int path) {
        if (root == null) return 0;
        path = path * 10 + root.val;
        if (root.left == null && root.right == null){
            return path;
        }else {
            return recur(root.left, path) + recur(root.right, path);
        }
    }

    /**
     * bfs：
     * @param root
     * @return
     */
    public static int sumNumbers(TreeNode root) {
        if (root == null) return 0;
        int sum = 0;
        Queue<TreeNode> nodeQueue = new LinkedList<>();
        Queue<Integer> numQueue = new LinkedList<>();
        nodeQueue.offer(root);
        numQueue.offer(root.val);
        while (!nodeQueue.isEmpty()){
            TreeNode node = nodeQueue.poll();
            Integer nodeVal = numQueue.poll();
            int left = 0;
            int right = 0;
            if (node.left != null){
                nodeQueue.offer(node.left);
                left = nodeVal * 10 + node.left.val;
                numQueue.offer(left);
            }
            if (node.right != null){
                nodeQueue.offer(node.right);
                right = nodeVal * 10 + node.right.val;
                numQueue.offer(right);
            }
            if (node.left == null && node.right == null){
                sum = sum + nodeVal;
            }

        }
        return sum;
    }



    public static class TreeNode {
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











































