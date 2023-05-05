package Swordoffer.BinaryTree;

/**
 * @author mxy
 * @create 2022-09-22 22:13
 */

/**
 * 对称的二叉树
 * 请实现一个函数，用来判断一棵二叉树是不是对称的。如果一棵二叉树和它的镜像一样，那么它是对称的。
 *
 * 例如，二叉树 [1,2,2,3,4,4,3] 是对称的。
 *     1
 *    / \
 *   2   2
 *  / \ / \
 * 3  4 4  3
 * 但是下面这个 [1,2,2,null,3,null,3] 则不是镜像对称的:
 *
 *     1
 *    / \
 *   2   2
 *    \   \
 *    3    3
 *
 * 示例 1：
 * 输入：root = [1,2,2,3,4,4,3]
 * 输出：true
 *
 * 示例 2：
 * 输入：root = [1,2,2,null,3,null,3]
 * 输出：false
 *
 */
public class Offer28 {

    public static void main(String[] args) {
        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(2);
        TreeNode node4 = new TreeNode(3);
        TreeNode node5 = new TreeNode(4);
        TreeNode node6 = new TreeNode(4);
        TreeNode node7 = new TreeNode(3);

        node1.left = node2;
        node1.right = node3;
        node2.left = node4;
        node2.right = node5;
        node3.left = node6;
        node3.right = node7;

        Offer28 offer = new Offer28();
        boolean res = offer.isSymmetric(node1);
        System.out.println(res);

    }

    /**
     * 解法一：先镜像，再比较
     * 执行用时： 0 ms , 在所有 Java 提交中击败了 100.00% 的用户
     * 内存消耗： 39.2 MB , 在所有 Java 提交中击败了 98.54% 的用户
     * @param root
     * @return
     */
    //二叉树对称的条件：左子树镜像翻转后与右子树相同
    //1.镜像翻转左子树  2、比较镜像后的左右子树是否相同
    //public boolean isSymmetric(TreeNode root) {
    //    if (root == null) return true;
    //    //if(root == null || (root.left == null && root.right == null)) return true;
    //    //if (root.left == null || root.right == null ) return false;
    //    //镜像翻转左子树
    //    TreeNode node = mirrorTree(root.left);
    //    return recur(node, root.right);
    //}
    //
    ////镜像翻转函数
    //public TreeNode mirrorTree(TreeNode root){
    //    if (root == null) return null;
    //    TreeNode tmp = root.left;
    //    root.left = root.right;
    //    root.right = tmp;
    //    mirrorTree(root.left);
    //    mirrorTree(root.right);
    //    return root;
    //}
    //
    ////判断两棵树是否相同 树2是否等于树1
    //public boolean recur(TreeNode root1,TreeNode root2){
    //    if (root2 == null) {
    //        if (root1 == null) return true;  //如果root1遍历完成，则root1和root2节点数相等，且值都相等
    //        if (root1 != null) return false; //说明root1的节点数 大于 root2的节点数，root2是root1的子树，而非完全相同
    //    }
    //    if (root1 == null || root1.val != root2.val) return false;
    //    return recur(root1.left, root2.left) && recur(root1.right, root2.right);
    //}


    /**
     * 解法二：
     * 执行用时： 0 ms , 在所有 Java 提交中击败了 100.00% 的用户
     * 内存消耗： 39.3 MB , 在所有 Java 提交中击败了 97.06% 的用户
     *
     * 解题思路： 链接：https://leetcode.cn/problems/dui-cheng-de-er-cha-shu-lcof/solution/mian-shi-ti-28-dui-cheng-de-er-cha-shu-di-gui-qing/
     *      对称二叉树定义： 对于树中 任意两个对称节点 L 和 R ，一定有：
     *          L.val = R.val ：即此两对称节点值相等。
     *          L.left.val = R.right.val ：即 L 的 左子节点 和 R 的 右子节点 对称；
     *          L.right.val = R.left.val ：即 L 的 右子节点 和 R 的 左子节点 对称。
     *      根据以上规律，考虑从顶至底递归，判断每对节点是否对称，从而判断树是否为对称二叉树。
     * 算法流程：
     *      isSymmetric(root) ：
     *           特例处理： 若根节点 root 为空，则直接返回 true。
     *          返回值： 即 recur(root.left, root.right);
     *      recur(L, R) ：
     *          终止条件：
     *              当 L 和 R 同时越过叶节点： 此树从顶至底的节点都对称，因此返回 true；
     *              当 L 或 R 中只有一个越过叶节点： 此树不对称，因此返回 false；
     *              当节点 L 值 ！= 节点 R 值： 此树不对称，因此返回 false；
     *          递推工作：
     *              判断两节点 L.left 和 R.right 是否对称，即 recur(L.left, R.right)；
     *              判断两节点 L.right 和 R.left 是否对称，即 recur(L.right, R.left)；
     *         返回值： 两对节点都对称时，才是对称树，因此用与逻辑符 && 连接。
     *
     * 复杂度分析：
     *      时间复杂度 O(N) ： 其中 N 为二叉树的节点数量，每次执行 recur() 可以判断一对节点是否对称，因此最多调用 N/2 次 recur() 方法。
     *      空间复杂度 O(N) ： 最差情况下（见下图），二叉树退化为链表，系统使用 O(N) 大小的栈空间。
     * @param root
     * @return
     */
    public boolean isSymmetric(TreeNode root) {
        if (root == null) return true;
        return recur(root.left, root.right);
    }

    public boolean recur(TreeNode L,TreeNode R){
        if (L == null && R == null) return true;
        //当 L 或 R 中只有一个越过叶节点： 此树不对称，因此返回 false；
        if (L == null || R == null || L.val != R.val) return false;
        return recur(L.left, R.right) && recur(L.right, R.left);
    }

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) {
            val = x;
        }
    }
}



































