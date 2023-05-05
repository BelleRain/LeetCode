package CodeTop;

/**
 * @author mxy
 * @create 2023-04-10 13:52
 */

/**
 * 110. 平衡二叉树   链接：https://leetcode.cn/problems/balanced-binary-tree
 * 给定一个二叉树，判断它是否是高度平衡的二叉树。
 * 本题中，一棵高度平衡二叉树定义为：
 * 一个二叉树每个节点 的左右两个子树的高度差的绝对值不超过 1 。
 *
 * 示例 1：
 * 输入：root = [3,9,20,null,null,15,7]
 * 输出：true
 *
 * 示例 2：
 * 输入：root = [1,2,2,3,3,null,null,4,4]
 * 输出：false
 *
 * 示例 3：
 * 输入：root = []
 * 输出：true
 *  
 * 提示：
 * 树中的节点数在范围 [0, 5000] 内
 * -10^4 <= Node.val <= 10^4
 *
 */
public class Code110 {

    public static void main(String[] args) {
        Code110 code = new Code110();
        TreeNode node1 = new TreeNode(3);
        TreeNode node2 = new TreeNode(9);
        TreeNode node3 = new TreeNode(20);
        TreeNode node4 = new TreeNode(15);
        TreeNode node5 = new TreeNode(7);

        node1.left = node2;
        node1.right = node3;
        node3.left = node4;
        node3.right = node5;

        System.out.println(code.isBalanced(node1));
    }

    /**
     * 题解链接：https://leetcode.cn/problems/balanced-binary-tree/solution/balanced-binary-tree-di-gui-fang-fa-by-jin40789108/
     */

    /**
     * 从底至顶，提前阻断 （最优解法）
     * 思路是对二叉树做先序遍历，从底至顶返回子树最大高度，若判定某子树不是平衡树则 “剪枝” ，直接向上返回。
     * 算法流程：
     * recur(root):
     *
     * 递归返回值：
     *      当节点 root 左 / 右子树的高度差 < 2 ：则返回以节点 root 为根节点的子树的最大高度，
     *            即节点 root 的左右子树中最大高度加 1 （ max(left, right) + 1 ）；
     *      当节点 root 左 / 右子树的高度差 ≥2 ：则返回 -1 ，代表 此子树不是平衡树 。
     * 递归终止条件：
     *      当越过叶子节点时，返回高度 0 ；
     *      当左（右）子树高度 left== -1 时，代表此子树的 左（右）子树 不是平衡树，因此直接返回 -1 ；
     * isBalanced(root) ：
     *      返回值： 若 recur(root) != -1 ，则说明此树平衡，返回 true ； 否则返回 false 。
     * 复杂度分析：
     * 时间复杂度 O(N)： N 为树的节点数；最差情况下，需要递归遍历树的所有节点。
     * 空间复杂度 O(N)： 最差情况下（树退化为链表时），系统递归需要使用 O(N) 的栈空间。
     *
     * @param root
     * @return
     */
    public boolean isBalanced(TreeNode root) {
        return recur(root) != -1;
    }

    public int recur(TreeNode root){
        if (root == null) return 0;
        int left = recur(root.left);
        if (left == -1) return -1;
        int right = recur(root.right);
        if (right == -1) return -1;
        return Math.abs(left - right) < 2 ? Math.max(left, right) + 1 : -1;
    }

    /**
     * 从顶至底，效率低
     * @param root
     * @return
     */
    public boolean isBalanced1(TreeNode root) {
        if (root == null || (root.left == null && root.right == null)) return true;
        return Math.abs(depth(root.left) - depth(root.right)) <= 1 &&
                isBalanced1(root.left) && isBalanced1(root.right);
    }

    public int depth(TreeNode root){
        if (root == null) return 0;
        int left = depth(root.left);
        int right = depth(root.right);
        return Math.max(left, right) + 1;
    }




    public static class TreeNode {
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
