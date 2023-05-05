package Top100;

/**
 * @author mxy
 * @create 2022-11-25 10:50
 */

import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * 105. 从前序与中序遍历序列构造二叉树  (同剑指 Offer07 题)  链接：https://leetcode.cn/problems/construct-binary-tree-from-preorder-and-inorder-traversal
 * 给定两个整数数组 preorder 和 inorder ，其中 preorder 是二叉树的先序遍历， inorder 是同一棵树的中序遍历，
 * 请构造二叉树并返回其根节点。
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
 */
public class Top105 {

    public static void main(String[] args) {
        Top105 top105 = new Top105();
        int[] preorder = {3,9,20,15,7};
        int[] inorder = {9,3,15,20,7};
        System.out.println(top105.buildTree(preorder, inorder));
    }

    /*
    为什么要强调 preorder 和 inorder 均 无重复 元素？
    根据题解，为防止重复遍历 inorder 列表，我们将其 放置 HashMap 中。
    若 无重复元素，可知 <元素,下标> 是一一对应的，这样构造出的二叉树也是唯一的，
    若有重复元素，则构造的二叉树不再是唯一的
     */

    /**
     * 题解一：同 Offer07  具体题解可以见 Offer07
     * 复杂度分析：
     *     时间复杂度 O(N) ： 其中 N 为树的节点数量。初始化 HashMap 需遍历 inorder ，占用 O(N) 。递归共建立 N 个节点，每层递归中的节点建立、搜索操作占用 O(1) ，因此使用 O(N) 时间。
     *     空间复杂度 O(N) ： HashMap 使用 O(N) 额外空间；最差情况下（输入二叉树为链表时），递归深度达到 N ，占用 O(N) 的栈帧空间；因此总共使用 O(N) 空间。
     *     注意：本文方法只适用于 “无重复节点值” 的二叉树。
     * @param preorder
     * @param inorder
     * @return
     */
    int[] preorder;
    Map<Integer, Integer> map = new HashMap<>();
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        this.preorder = preorder;
        for (int i = 0; i < inorder.length; i++) {
            map.put(inorder[i], i);
        }
        return recur(0,0,inorder.length - 1);
    }

    /**
     * 递归创建二叉树
     * @param root  前序遍历中 根结点 的索引
     * @param left  中序遍历中 子树 的 左边界
     * @param right 中序遍历中 子树 的 右边界
     * @return
     */
    private TreeNode recur(int root, int left, int right) {
        if (left > right) return null; //left == right 时，遍历到自己，left > right 时，越界，证明已经遍历完毕
        int i = map.get(preorder[root]); //获取中序中 根结点 的索引
        //构建根结点
        TreeNode node = new TreeNode(preorder[root]);
        //递归构建左子树
        node.left = recur(root + 1, left, i - 1);
        //递归构建右子树
        node.right = recur(i - left + root + 1, i + 1, right);
        return node;
    }


    /**
     * 题解二： 与题解一思路相同，递归传入参数的不同 ，题解链接：链接：https://leetcode.cn/problems/construct-binary-tree-from-preorder-and-inorder-traversal/solution/qian-xu-bian-li-python-dai-ma-java-dai-ma-by-liwei/
     * 复杂度分析：
     * 时间复杂度：O(N)，这里 N 是二叉树的结点个数，每调用一次递归方法创建一个结点，一共创建 N 个结点，这里不计算递归方法占用的时间。
     * 空间复杂度：O(N)，这里忽略递归方法占用的空间，因为是对数级别的，比 N 小。
     * @param preorder
     * @param inorder
     * @return
     */
    /*int[] preorder;
    Map<Integer,Integer> map = new HashMap<>();
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        this.preorder = preorder;
        for (int i = 0; i < inorder.length; i++) {
            map.put(inorder[i], i);
        }
        return buildTree(0, preorder.length - 1,0,inorder.length - 1);
    }*/

    /**
     * @param preleft   子树 前序遍历 的 左边界
     * @param preright  子树 前序遍历 的 右边界
     * @param inleft    子树 中序遍历 的 左边界
     * @param inright   子树 中序遍历 的 右边界
     * @return
     */
    /*private TreeNode buildTree(int preleft, int preright, int inleft, int inright) {
        if (preleft > preright || inleft > inright) return null;
        TreeNode node = new TreeNode(preorder[preleft]);
        int pivot = map.get(preorder[preleft]); //中序遍历中 根结点的索引
        node.left = buildTree(preleft + 1, pivot - inleft + preleft,
                                     inleft, pivot - 1);
        node.right = buildTree(pivot - inleft + preleft + 1, preright,
                                pivot + 1, inright);
        return node;
    }*/

    /**
     * 方法二：迭代 ，详解见原文链接：https://leetcode.cn/problems/zhong-jian-er-cha-shu-lcof/solution/mian-shi-ti-07-zhong-jian-er-cha-shu-by-leetcode-s/
     * @param preorder
     * @param inorder
     * @return
     */
    /*public TreeNode buildTree(int[] preorder, int[] inorder) {
        if (preorder == null || preorder.length == 0) {
            return null;
        }
        TreeNode root = new TreeNode(preorder[0]);
        Deque<TreeNode> stack = new LinkedList<TreeNode>();
        stack.push(root);
        int inorderIndex = 0;
        for (int i = 1; i < preorder.length; i++) {
            int preorderVal = preorder[i];
            TreeNode node = stack.peek();
            if (node.val != inorder[inorderIndex]) {
                node.left = new TreeNode(preorderVal);
                stack.push(node.left);
            } else {
                while (!stack.isEmpty() && stack.peek().val == inorder[inorderIndex]) {
                    node = stack.pop();
                    inorderIndex++;
                }
                node.right = new TreeNode(preorderVal);
                stack.push(node.right);
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
