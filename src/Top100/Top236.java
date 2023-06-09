package Top100;

/**
 * @author mxy
 * @create 2022-12-13 9:11
 */

/**
 * 236. 二叉树的最近公共祖先  （ 同剑指 Offer68_2 、 Offer68_1(二叉搜索树) ）  链接：https://leetcode.cn/problems/lowest-common-ancestor-of-a-binary-tree
 * 给定一个二叉树, 找到该树中两个指定节点的最近公共祖先。
 * 百度百科中最近公共祖先的定义为：“对于有根树 T 的两个节点 p、q，最近公共祖先表示为一个节点 x，满足 x 是 p、q 的祖先且 x 的深度尽可能大（一个节点也可以是它自己的祖先）。”
 *
 * 示例 1：
 * 输入：root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 1
 * 输出：3
 * 解释：节点 5 和节点 1 的最近公共祖先是节点 3 。
 *
 * 示例 2：
 * 输入：root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 4
 * 输出：5
 * 解释：节点 5 和节点 4 的最近公共祖先是节点 5 。因为根据定义最近公共祖先节点可以为节点本身。
 *
 * 示例 3：
 * 输入：root = [1,2], p = 1, q = 2
 * 输出：1
 *
 * 提示：
 * 树中节点数目在范围 [2, 10^5] 内。
 * -10^9 <= Node.val <= 10^9
 * 所有 Node.val 互不相同 。
 * p != q
 * p 和 q 均存在于给定的二叉树中。
 *
 */
public class Top236 {

    /**
     * 题解链接：https://leetcode.cn/problems/lowest-common-ancestor-of-a-binary-tree/solution/236-er-cha-shu-de-zui-jin-gong-gong-zu-xian-hou-xu/
     * 或见 剑指 Offer68_2
     * @param root
     * @param p
     * @param q
     * @return
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if(root == null || root == p || root == q) return root;
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        if (left == null) return right;
        if (right == null) return left;
        return root;
    }

    //情况 1. , 2. , 3. , 4. 的展开写法如下。
    //public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
    //    if(root == null || root == p || root == q) return root;
    //    TreeNode left = lowestCommonAncestor(root.left, p, q);
    //    TreeNode right = lowestCommonAncestor(root.right, p, q);
    //    if(left == null && right == null) return null; // 1.
    //    if(left == null) return right; // 3.
    //    if(right == null) return left; // 4.
    //    return root; // 2. if(left != null and right != null)
    //}

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
}
