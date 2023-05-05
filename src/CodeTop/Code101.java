package CodeTop;

/**
 * @author mxy
 * @create 2023-05-05 8:42
 */

import java.util.Deque;
import java.util.LinkedList;

/**
 * 101. 对称二叉树   链接：https://leetcode.cn/problems/symmetric-tree
 * 给你一个二叉树的根节点 root ， 检查它是否轴对称。
 *
 * 示例 1：
 * 输入：root = [1,2,2,3,4,4,3]
 * 输出：true
 *
 * 示例 2：
 * 输入：root = [1,2,2,null,3,null,3]
 * 输出：false
 *
 * 提示：
 * 树中节点数目在范围 [1, 1000] 内
 * -100 <= Node.val <= 100
 *  
 * 进阶：你可以运用递归和迭代两种方法解决这个问题吗？
 *
 */
public class Code101 {

    public static void main(String[] args) {
        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(2);
        TreeNode node4 = new TreeNode(3);
        TreeNode node5 = new TreeNode(4);
        TreeNode node6 = new TreeNode(4);
        TreeNode node7 = new TreeNode(3);

        node1.left = node2;
        node1.right = node3;
        node2.left = node4;
        node2.right = node5;
        node3.left = node6;
        node3.right = node7;

        System.out.println(isSymmetric(node1));
    }


    /**
     * 递归法：
     * @param root
     * @return
     */
    public static boolean isSymmetricRecur(TreeNode root) {
        if (root == null) return true;
        return recur(root.left,root.right);
    }

    private static boolean recur(TreeNode root1, TreeNode root2) {
        if (root1 == null && root2 == null) return true;
        if (root1 == null || root2 == null || root1.val != root2.val){
            return false;
        }
        boolean left = recur(root1.left, root2.right);
        boolean right = recur(root1.right, root2.left);
        return left && right;
    }


    /**
     * 迭代法：
     * @param root
     * @return
     */
    public static boolean isSymmetric(TreeNode root) {
        if (root == null) return true;
        Deque<TreeNode> list = new LinkedList<>();
        list.offer(root.left);
        list.offer(root.right);
        while (!list.isEmpty()){
            TreeNode root1 = list.poll();
            TreeNode root2 = list.poll();
            if (root1 == null && root2 == null) continue;
            if (root1 == null || root2 == null || root1.val != root2.val) return false;
            list.offer(root1.left);
            list.offer(root2.right);
            list.offer(root1.right);
            list.offer(root2.left);
        }
        return true;
    }


    public static class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        public TreeNode(){}
        public TreeNode(int val){
            this.val = val;
        }
        public TreeNode(int val,TreeNode left,TreeNode right){
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}
