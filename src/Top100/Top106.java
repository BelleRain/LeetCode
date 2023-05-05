package Top100;

/**
 * @author mxy
 * @create 2022-11-25 15:28
 */

import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * 106. 从中序与后序遍历序列构造二叉树  链接：https://leetcode.cn/problems/construct-binary-tree-from-inorder-and-postorder-traversal
 * 给定两个整数数组 inorder 和 postorder ，其中 inorder 是二叉树的中序遍历，
 *  postorder 是同一棵树的后序遍历，请你构造并返回这颗 二叉树 。
 *
 * 示例 1:
 * 输入：inorder = [9,3,15,20,7], postorder = [9,15,7,20,3]
 * 输出：[3,9,20,null,null,15,7]
 *
 * 示例 2:
 * 输入：inorder = [-1], postorder = [-1]
 * 输出：[-1]
 *  
 * 提示:
 * 1 <= inorder.length <= 3000
 * postorder.length == inorder.length
 * -3000 <= inorder[i], postorder[i] <= 3000
 * inorder 和 postorder 都由 不同 的值组成
 * postorder 中每一个值都在 inorder 中
 * inorder 保证是树的中序遍历
 * postorder 保证是树的后序遍历
 */
public class Top106 {

    public static void main(String[] args) {
        Top106 top106 = new Top106();
        int[] inorder = {9,3,15,20,7};
        int[] postorder = {9,15,7,20,3};
        System.out.println(top106.buildTree(inorder, postorder));
    }

    /**
     * 题解一：
     * 执行用时： 1 ms , 在所有 Java 提交中击败了 99.68% 的用户
     * 内存消耗： 41.5 MB , 在所有 Java 提交中击败了 17.88% 的用户
     * @param inorder
     * @param postorder
     * @return
     */
    int[] postorder;
    Map<Integer,Integer> map = new HashMap<>();
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        this.postorder = postorder;
        for (int i = 0; i < inorder.length; i++) {
            map.put(inorder[i],i);
        }
        return recur(postorder.length - 1,0,inorder.length - 1);
    }

    /**
     * @param root  子树的 根结点 在 后序遍历中的 索引
     * @param left  子树 在中序遍历中的 左边界
     * @param right 子树 在中序遍历中的 右边界
     * @return
     */
    private TreeNode recur(int root, int left, int right) {
        if (left > right) return null;
        TreeNode node = new TreeNode(postorder[root]);
        int i = map.get(postorder[root]);
        node.left = recur(root - right + i - 1, left, i - 1);
        node.right = recur(root - 1, i + 1, right);
        return node;
    }

    /**
     * 题解二： 递归参数不同，总结 ，无论如何传参，最终都是要获得根结点的坐标并创建结点
     * @param inorder
     * @param postorder
     * @return
     */
    /*int[] postorder;
    Map<Integer,Integer> map = new HashMap<>();
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        this.postorder = postorder;
        for (int i = 0; i < inorder.length; i++) {
            map.put(inorder[i],i);
        }
        return recur(0,postorder.length - 1,0,inorder.length - 1);
    }*/

    /**
     * @param postleft    子树 在 后序遍历中的 左边界
     * @param postright   子树 在 后序遍历中的 右边界
     * @param inleft      子树 在 中序遍历中的 左边界
     * @param inright     子树 在 中序遍历中的 右边界
     * @return
     */
    /*private TreeNode recur(int postleft, int postright, int inleft, int inright) {
        if (postleft > postright || inleft > inright) return null;
        TreeNode node = new TreeNode(postorder[postright]);
        int i = map.get(postorder[postright]);
        node.left = recur(postleft, postright - inright + i - 1,
                          inleft, i - 1);
        node.right = recur(postright - inright + i, postright - 1,
                             i + 1, inright);
        return node;
    }*/

    /**
     * 题解三： 迭代  题解链接：https://leetcode.cn/problems/construct-binary-tree-from-inorder-and-postorder-traversal/solution/cong-zhong-xu-yu-hou-xu-bian-li-xu-lie-gou-zao-14/
     * 复杂度分析
     *      时间复杂度：O(n)，其中 n 是树中的节点个数。
     *      空间复杂度：O(n)，我们需要使用 O(h（其中 h 是树的高度）的空间存储栈。这里 h < n，所以（在最坏情况下）总空间复杂度为 O(n)。
     * @param inorder
     * @param postorder
     * @return
     */
    /*public TreeNode buildTree(int[] inorder, int[] postorder) {
        if (postorder == null || postorder.length == 0) {
            return null;
        }
        TreeNode root = new TreeNode(postorder[postorder.length - 1]);
        Deque<TreeNode> stack = new LinkedList<TreeNode>();
        stack.push(root);
        int inorderIndex = inorder.length - 1;
        for (int i = postorder.length - 2; i >= 0; i--) {
            int postorderVal = postorder[i];
            TreeNode node = stack.peek();
            if (node.val != inorder[inorderIndex]) {
                node.right = new TreeNode(postorderVal);
                stack.push(node.right);
            } else {
                while (!stack.isEmpty() && stack.peek().val == inorder[inorderIndex]) {
                    node = stack.pop();
                    inorderIndex--;
                }
                node.left = new TreeNode(postorderVal);
                stack.push(node.left);
            }
        }
        return root;
    }*/

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
