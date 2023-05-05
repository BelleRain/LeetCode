package CodeTop;

/**
 * @author mxy
 * @create 2023-04-27 20:31
 */

import java.util.Deque;
import java.util.LinkedList;

/**
 * 98. 验证二叉搜索树      链接：https://leetcode.cn/problems/validate-binary-search-tree
 * 给你一个二叉树的根节点 root ，判断其是否是一个有效的二叉搜索树。
 * 有效 二叉搜索树定义如下：
 * 节点的左子树只包含 小于 当前节点的数。
 * 节点的右子树只包含 大于 当前节点的数。
 * 所有左子树和右子树自身必须也是二叉搜索树。
 *
 * 示例 1：
 * 输入：root = [2,1,3]
 * 输出：true
 *
 * 示例 2：
 * 输入：root = [5,1,4,null,null,3,6]
 * 输出：false
 * 解释：根节点的值是 5 ，但是右子节点的值是 4 。
 *  
 * 提示：
 * 树中节点数目范围在[1, 10^4] 内
 * -2^31 <= Node.val <= 2^31 - 1
 *
 */
public class Code98 {


    /**
     * 递归
     * @param root
     * @return
     */
    public boolean isValidBST1(TreeNode root) {
       if (root == null) return true;
       long lower = Long.MIN_VALUE;
       long upper = Long.MAX_VALUE;
       return recur(root,lower,upper);
    }

    private boolean recur(TreeNode root, long lower, long upper) {
        if (root == null) return true;
        if (root.val <= lower ||root.val >= upper) return false;
        return recur(root.left, lower, root.val) && recur(root.right, root.val, upper);
    }


    /**
     * 中序遍历：递归
     * @param root
     * @return
     */
    long pre = Long.MIN_VALUE;
    public boolean isValidBST_Recur(TreeNode root) {
        if (root == null) return true;
        if (!isValidBST_Recur(root.left)) return false;
        if (root.val <= pre) return false;
        pre = root.val;
        return isValidBST_Recur(root.right);
    }


    /**
     * 中序遍历：迭代
     * @param root
     * @return
     */
    public boolean isValidBST(TreeNode root) {
        Deque<TreeNode> stack = new LinkedList<>();
        long pre = Long.MIN_VALUE;
        if (root == null) return true;
        while (root != null || !stack.isEmpty()){
            while (root != null){
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            if (root.val <= pre) return false;
            pre = root.val;
            root = root.right;
        }
        return true;
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
