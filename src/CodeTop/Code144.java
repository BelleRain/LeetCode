package CodeTop;

/**
 * @author mxy
 * @create 2023-04-27 19:34
 */

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * 144. 二叉树的前序遍历    链接：https://leetcode.cn/problems/binary-tree-preorder-traversal
 * 给你二叉树的根节点 root ，返回它节点值的 前序 遍历。
 *
 * 示例 1：
 * 输入：root = [1,null,2,3]
 * 输出：[1,2,3]
 *
 * 示例 2：
 * 输入：root = []
 * 输出：[]
 *
 * 示例 3：
 * 输入：root = [1]
 * 输出：[1]
 *
 * 示例 4：
 * 输入：root = [1,2]
 * 输出：[1,2]
 *
 * 示例 5：
 * 输入：root = [1,null,2]
 * 输出：[1,2]
 *
 * 提示：
 * 树中节点数目在范围 [0, 100] 内
 * -100 <= Node.val <= 100
 *  
 * 进阶：递归算法很简单，你可以通过迭代算法完成吗？
 *
 */
public class Code144 {

    /**
     * 递归：
     * @param root
     * @return
     */
    List<Integer> res = new ArrayList<>();
    public List<Integer> preorderTraversal_Recur(TreeNode root) {
        if (root == null) return res;
        res.add(root.val);
        preorderTraversal_Recur(root.left);
        preorderTraversal_Recur(root.right);
        return res;
    }


    /**
     * 迭代：
     * @param root
     * @return
     */
    public List<Integer> preorderTraversal_Iter(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) return res;
        Deque<TreeNode> stack = new LinkedList<>();
        while (root != null || !stack.isEmpty()){
           while (root != null){
               res.add(root.val);
               stack.push(root);
               root = root.left;
           }
           root = stack.pop().right;
        }
        return res;
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







































