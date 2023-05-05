package CodeTop;

/**
 * @author mxy
 * @create 2023-04-10 9:22
 */

import java.util.HashMap;

/**
 * 剑指 Offer 07. 重建二叉树   链接：https://leetcode.cn/problems/zhong-jian-er-cha-shu-lcof
 * 输入某二叉树的前序遍历和中序遍历的结果，请构建该二叉树并返回其根节点。
 * 假设输入的前序遍历和中序遍历的结果中都不含重复的数字。
 *
 * 示例 1:
 * Input: preorder = [3,9,20,15,7], inorder = [9,3,15,20,7]
 * Output: [3,9,20,null,null,15,7]
 *
 * 示例 2:
 * Input: preorder = [-1], inorder = [-1]
 * Output: [-1]
 *  
 *
 * 限制：
 * 0 <= 节点个数 <= 5000
 *
 */
public class Offer07 {

    public static void main(String[] args) {

    }

    /**
     * 方法一： 递归
     * 1、先序遍历 ：根结点 + 左子树 + 右子树     root  root+1    i - left + root + 1
     * 2、中序遍历 ：左子树 + 根结点 + 右子树     left     i - 1
     * 3、确定三个节点 ： 1、树的根结点 2、左子树根结点 3、右子树根结点
     * 4、根据 【分治算法】的思想，对于树的左右子树，仍可复用上述方法确定树的左右子树
     * 分治算法：
     *  1、递推参数：根结点在前序遍历的索引 root、子树在中序遍历的左边界 left、子树在中序遍历的右边界 right
     *  2、终止条件：当 left > right ,代表已经越过 叶节点，此时返回 null；
     *  3、递推工作：
     *      1、创建根结点 node：节点值为 preorder[root];
     *      2、划分左右子树： 查找根节点在中序遍历 inorder 中的索引 i；
     *      使用哈希表存储中序遍历的值与索引的映射表
     *      3、构建左右子树：开启左右子树递归
     *               根结点索引           中序遍历左边界             中序遍历右边界
     *    左子树      root + 1                left                     i - 1
     *    右子树   i - left + root + 1       i + 1                     right
     *  TIPS： i - left + root + 1 含义为 根节点索引 + 左子树长度 + 1
     *  4、返回值： 回溯返回 node ，作为上一层递归中根节点的左 / 右子节点；
     *  注意：本文方法只适用于 “无重复节点值” 的二叉树。
     * @param preorder
     * @param inorder
     * @return
     */
    int[] preorder;
    HashMap<Integer, Integer> map = new HashMap<>();
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
        int i = map.get(preorder[root]);
        node.left = recur(root + 1, left, i - 1);
        node.right = recur(i - left + root + 1, i + 1, right);
        return node;
    }


    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
}
