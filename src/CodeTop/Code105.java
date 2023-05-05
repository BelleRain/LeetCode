package CodeTop;

/**
 * @author mxy
 * @create 2023-04-25 21:01
 */

import java.util.HashMap;

/**
 * 105. 从前序与中序遍历序列构造二叉树 链接：https://leetcode.cn/problems/construct-binary-tree-from-preorder-and-inorder-traversal
 * 给定两个整数数组 preorder 和 inorder ，其中 preorder 是二叉树的先序遍历，
 * inorder 是同一棵树的中序遍历，请构造二叉树并返回其根节点。
 *  
 * 示例 1:
 * 输入: preorder = [3,9,20,15,7], inorder = [9,3,15,20,7]
 * 输出: [3,9,20,null,null,15,7]
 *
 * 示例 2:
 * 输入: preorder = [-1], inorder = [-1]
 * 输出: [-1]
 *
 * 提示:
 * 1 <= preorder.length <= 3000
 * inorder.length == preorder.length
 * -3000 <= preorder[i], inorder[i] <= 3000
 * preorder 和 inorder 均 无重复 元素
 * inorder 均出现在 preorder
 * preorder 保证 为二叉树的前序遍历序列
 * inorder 保证 为二叉树的中序遍历序列
 *
 */
public class Code105 {

    /**
     * 详细题解见 Offer07
     * 中序遍历 ： 左子树 + 根结点 + 右子树
     * 前序遍历 ： 根结点 + 左子树 + 右子树
     *                   根结点索引           中序遍历左边界             中序遍历右边界
     *         左子树      root + 1                left                     i - 1
     *          右子树   i - left + root + 1       i + 1                     right
     *       TIPS： i - left + root + 1 含义为 根节点索引 + 左子树长度 + 1
     * @param preorder
     * @param inorder
     * @return
     */
    int[] preorder;
    HashMap<Integer,Integer> map = new HashMap<>();
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        this.preorder = preorder;
        if (preorder.length == 0 || inorder.length == 0) return null;
        for (int i = 0; i < inorder.length; i++) {
            map.put(inorder[i], i);
        }
        return recur(0,0,inorder.length - 1);
    }

    /**
     * 子树在 先序遍历中的 根结点
     * 子树在 中序遍历中的 左边界
     * 子树在 中序遍历中的 右边界
     * @param root
     * @param left
     * @param right
     * @return
     */
    private TreeNode recur(int root, int left, int right) {
        if (left > right) return null;
        TreeNode node = new TreeNode(preorder[root]);
        //根结点在中序遍历中的位置
        int i = map.get(preorder[root]);
        node.left = recur(root + 1, left, i - 1);
        node.right = recur(i - left + root + 1, i + 1, right);
        return node;
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




































