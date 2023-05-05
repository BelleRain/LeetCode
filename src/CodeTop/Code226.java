package CodeTop;

/**
 * @author mxy
 * @create 2023-04-10 17:37
 */

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * 226. 翻转二叉树   链接：https://leetcode.cn/problems/invert-binary-tree
 * 给你一棵二叉树的根节点 root ，翻转这棵二叉树，并返回其根节点。
 *
 * 示例 1：
 * 输入：root = [4,2,7,1,3,6,9]
 * 输出：[4,7,2,9,6,3,1]
 *
 * 示例 2：
 * 输入：root = [2,1,3]
 * 输出：[2,3,1]
 *
 * 示例 3：
 * 输入：root = []
 * 输出：[]
 *  
 * 提示：
 * 树中节点数目范围在 [0, 100] 内
 * -100 <= Node.val <= 100
 *
 */
public class Code226 {


    /**
     * 递归实现：
     * @param root
     * @return
     */
    public TreeNode invertTree(TreeNode root) {
        if (root == null || (root.left == null && root.right == null)) return root;
        TreeNode left = root.left;
        TreeNode right = root.right;
        root.left = right;
        root.right = left;
        invertTree(root.left);
        invertTree(root.right);
        return root;
    }


    /**
     * 迭代实现
     * @param root
     * @return
     */
    public TreeNode invertTree1(TreeNode root) {
        if (root == null) return root;
        //将二叉树的元素放入队列中，一次处理队列元素
        Deque<TreeNode> list = new LinkedList<>();
        list.add(root);
        while (!list.isEmpty()){
            int size = list.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = list.poll();
                TreeNode tmp = node.left;
                node.left = node.right;
                node.right = tmp;
                if (node.left != null) {
                    list.offer(node.left);
                }
                if (node.right != null){
                    list.offer(node.right);
                }
            }
        }
        return root;
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
