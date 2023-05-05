package Top100;

/**
 * @author mxy
 * @create 2022-11-24 10:02
 */

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * 98. 验证二叉搜索树  链接：https://leetcode.cn/problems/validate-binary-search-tree
 * 给你一个二叉树的根节点 root ，判断其是否是一个有效的二叉搜索树。
 * 有效 二叉搜索树定义如下：
 *    节点的左子树只包含 小于 当前节点的数。
 *    节点的右子树只包含 大于 当前节点的数。
 *    所有左子树和右子树自身必须也是二叉搜索树。
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
 */
public class Top98 {

    public static void main(String[] args) {
        Top98 top98 = new Top98();
        TreeNode node1 = new TreeNode(5);
        TreeNode node2 = new TreeNode(1);
        TreeNode node3 = new TreeNode(4);
        TreeNode node4 = new TreeNode(3);
        TreeNode node5 = new TreeNode(6);

        node1.left = node2;
        node1.right = node3;
        node3.left = node4;
        node3.right = node5;

        System.out.println(top98.isValidBST(node1));
    }

    /**
     * 题解一：题解链接：https://leetcode.cn/problems/validate-binary-search-tree/solution/yan-zheng-er-cha-sou-suo-shu-by-leetcode-solution/
     * 方法一: 递归
     * 复杂度分析：
     *      时间复杂度：O(n)，其中 n 为二叉树的节点个数。在递归调用的时候二叉树的每个节点最多被访问一次，因此时间复杂度为 O(n)。
     *      空间复杂度：O(n)，其中 n 为二叉树的节点个数。递归函数在递归过程中需要为每一层递归函数分配栈空间，
     *      所以这里需要额外的空间且该空间取决于递归的深度，即二叉树的高度。最坏情况下二叉树为一条链，
     *      树的高度为 n ，递归最深达到 n 层，故最坏情况下空间复杂度为 O(n) 。
     * @param root
     * @return
     */
    public boolean isValidBST(TreeNode root) {
        //-2^31 <= Node.val <= 2^31 - 1
        return isValidBST(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }

    /**
     * 深度优先遍历：当前节点值的上界为 root.right, 当前节点值的下界 为 root.left
     * @param root 当前节点
     * @param lower 当前节点值的下界
     * @param upper 当前节点值的上界
     * @return
     */
    public boolean isValidBST(TreeNode root,long lower,long upper){
        if (root == null) return true;
        if (root.val <= lower || root.val >= upper) return false;
        return isValidBST(root.left, lower, root.val) && isValidBST(root.right,root.val, upper);
    }

    /**
     * 方法二：中序遍历 （迭代实现）
     * 复杂度分析
     *      时间复杂度：O(n)，其中 n 为二叉树的节点个数。二叉树的每个节点最多被访问一次，因此时间复杂度为 O(n)。
     *      空间复杂度：O(n)，其中 n 为二叉树的节点个数。栈最多存储 n 个节点，因此需要额外的 O(n) 的空间。
     * @param root
     * @return
     */
    public boolean isValidBSTInorder1(TreeNode root) {
        Deque<TreeNode> stack = new ArrayDeque<>();
        double inorder = -Double.MAX_VALUE;
        while (!stack.isEmpty() || root != null){
            //用栈模拟递归向下寻找 最左子节点的过程
            while (root != null){
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            //如果中序遍历得到的第一个节点的值小于等于前一个inorder，说明不是二叉搜索树
            if (root.val <= inorder) return false;
            inorder = root.val;
            root = root.right;
        }
        return true;
    }

    /**
     * 题解三 ： 题解链接：https://leetcode.cn/problems/validate-binary-search-tree/solution/zhong-xu-bian-li-qing-song-na-xia-bi-xu-miao-dong-/
     * 方法三： 中序遍历 （递归实现）
     *      中序遍历时，判断当前节点是否大于中序遍历的前一个节点，
     *      如果大于，说明满足 BST，继续遍历；否则直接返回 false。
     * @param root
     * @return
     */
    long pre = Long.MIN_VALUE; //前一个节点的值
    public boolean isValidBSTInorder2(TreeNode root) {
        if (root == null) return true;
        //访问左子树
        if (!isValidBST(root.left)) return false;
        // 访问当前节点：如果当前节点小于等于中序遍历的前一个节点，说明不满足BST，返回 false；否则继续遍历。
        if (root.val <= pre) return false;
        pre = root.val;
        //访问右子树
        return isValidBST(root.right);
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
