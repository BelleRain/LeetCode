package CodeTop;

/**
 * @author mxy
 * @create 2023-04-10 11:49
 */

/**
 * 543. 二叉树的直径   链接：https://leetcode.cn/problems/diameter-of-binary-tree
 * 给定一棵二叉树，你需要计算它的直径长度。一棵二叉树的直径长度是任意两个结点路径长度中的最大值。
 * 这条路径可能穿过也可能不穿过根结点。
 *
 * 示例 :
 * 给定二叉树
 *           1
 *          / \
 *         2   3
 *        / \
 *       4   5
 * 返回 3, 它的长度是路径 [4,2,1,3] 或者 [5,2,1,3]。
 *
 * 注意：两结点之间的路径长度是以它们之间边的数目表示。
 *
 */
public class Code543 {


    /**
     * 二叉树上的任一“路径”上一定有一个结点是所有其他结点的祖先结点（因为“路径”是由一个个父子关系连接而成的），
     * 那么换个表述方法，对于任一结点，以此结点为根的diameter就可以表示为左子树高度 + 右子树高度，
     * 而二叉树的diameter就是所有结点为根的diameter中最大的那个。
     *
     * 即：每个节点都要记录，以该节点为根结点的直径，然后取所有直径中的最大值
     * 以某一节点为根结点的直径 = 左子树的深度 + 右子树深度
     * @param root
     * @return
     */
    int max = 0;
    public int diameterOfBinaryTree(TreeNode root) {
        depth(root);
        return max;
    }

    public int depth(TreeNode root){
        if (root == null) return 0;
        int m1 = depth(root.left);
        int m2 = depth(root.right);
        max = Math.max(max, m1 + m2);
        return Math.max(m1, m2) + 1;
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
