package CodeTop;

/**
 * @author mxy
 * @create 2023-04-19 10:13
 */

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * 94. 二叉树的中序遍历     链接：https://leetcode.cn/problems/binary-tree-inorder-traversal
 * 给定一个二叉树的根节点 root ，返回 它的 中序 遍历 。
 *
 * 示例 1：
 * 输入：root = [1,null,2,3]
 * 输出：[1,3,2]
 *
 * 示例 2：
 * 输入：root = []
 * 输出：[]
 *
 * 示例 3：
 * 输入：root = [1]
 * 输出：[1]
 *  
 * 提示：
 * 树中节点数目在范围 [0, 100] 内
 * -100 <= Node.val <= 100
 *
 * 进阶: 递归算法很简单，你可以通过迭代算法完成吗？
 */
public class Code94 {


    public List<Integer> inorderTraversal1(TreeNode root) {
        if (root == null) return new ArrayList<>();
        List<Integer> result = inorderTraversal1(root.left);
        result.add(root.val);
        result.addAll(inorderTraversal1(root.right));
        return result;
    }


    public List<Integer> inorderTraversal2(TreeNode root) {
        if (root == null) return new ArrayList<>();
        Deque<TreeNode> stack = new ArrayDeque<>();
        List<Integer> temp = new ArrayList<>();
        while (!stack.isEmpty() || root != null){
            if (root != null){
                stack.addLast(root);
                root = root.left;
            }else {
                TreeNode node = stack.removeLast();
                temp.add(node.val);
                root = node.right;
            }
        }
        return temp;
    }



    public static class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;
        public TreeNode(){}
        public TreeNode(int val) {
            this.val = val;
        }
        public TreeNode(int val,TreeNode left,TreeNode right){
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }
}
