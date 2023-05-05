package Swordoffer.SearchAndRecall;

/**
 * @author mxy
 * @create 2022-10-11 11:18
 */

/**
 * 平衡二叉树
 * 输入一棵二叉树的根节点，判断该树是不是平衡二叉树。如果某二叉树中任意节点的左右子树的深度相差不超过1，那么它就是一棵平衡二叉树。
 * 示例 1:
 *
 * 给定二叉树 [3,9,20,null,null,15,7]
 *     3
 *    / \
 *   9  20
 *     /  \
 *    15   7
 * 返回 true 。
 *
 * 示例 2:
 *
 * 给定二叉树 [1,2,2,3,3,null,null,4,4]
 *        1
 *       / \
 *      2   2
 *     / \
 *    3   3
 *   / \
 *  4   4
 * 返回 false 。
 */
public class Offer55_2 {


    /**
     * 原文链接：https://leetcode.cn/problems/ping-heng-er-cha-shu-lcof/solution/mian-shi-ti-55-ii-ping-heng-er-cha-shu-cong-di-zhi/
     * 以下两种方法均基于以下性质推出： 此树的深度 等于 左子树的深度 与 右子树的深度 中的 最大值 +1 。
     */
    /**
     * 方法一：后序遍历 + 剪枝 （从底至顶）
     *    此方法为本题的最优解法，但剪枝的方法不易第一时间想到。
     * 思路是对二叉树做后序遍历，从底至顶返回子树深度，若判定某子树不是平衡树则 “剪枝” ，直接向上返回。
     * 算法流程：
     *  1、recur(root) 函数：
     *  2、返回值：
     *         当节点root 左 / 右子树的深度差  ≤1 ：则返回当前子树的深度，即节点 root 的左 / 右子树的深度最大值 +1 （ max(left, right) + 1 ）；
     *         当节点root 左 / 右子树的深度差 >=2 ：则返回 -1 ，代表 此子树不是平衡树 。
     * 3、终止条件：
     *         当 root 为空：说明越过叶节点，因此返回高度 0 ；
     *         当左（右）子树深度为 -1 ：代表此树的 左（右）子树 不是平衡树，因此剪枝，直接返回 -1 ；
     * 4、isBalanced(root) 函数：
     *         返回值： 若 recur(root) != -1 ，则说明此树平衡，返回 true ； 否则返回 false 。
     * 复杂度分析：
     * 时间复杂度 O(N)： N 为树的节点数；最差情况下，需要递归遍历树的所有节点。
     * 空间复杂度 O(N)： 最差情况下（树退化为链表时），系统递归需要使用 O(N) 的栈空间。
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
        else return Math.abs(left - right) < 2 ? Math.max(left,right) + 1 : -1;
    }



    /**
     * 方法二：先序遍历 + 判断深度 （从顶至底）
     *   此方法容易想到，但会产生大量重复计算，时间复杂度较高。
     * 思路是构造一个获取当前子树的深度的函数 depth(root) （即 面试题55 - I. 二叉树的深度 ），通过比较某子树的左右子树的深度差 abs(depth(root.left) - depth(root.right)) <= 1 是否成立，来判断某子树是否是二叉平衡树。若所有子树都平衡，则此树平衡。
     * 算法流程：
     * 1、isBalanced(root) 函数： 判断树 root 是否平衡
     * 2、特例处理： 若树根节点 root 为空，则直接返回 true ；
     * 3、返回值： 所有子树都需要满足平衡树性质，因此以下三者使用与逻辑 && 连接；
     *          abs(self.depth(root.left) - self.depth(root.right)) <= 1 ：判断 当前子树 是否是平衡树；
     *          self.isBalanced(root.left) ： 先序遍历递归，判断 当前子树的左子树 是否是平衡树；
     *          self.isBalanced(root.right) ： 先序遍历递归，判断 当前子树的右子树 是否是平衡树；
     * 4、depth(root) 函数： 计算树 root 的深度
     *       终止条件： 当 root 为空，即越过叶子节点，则返回高度 0 ；
     *       返回值： 返回左 / 右子树的深度的最大值 +1 。
     * 复杂度分析：
     * 时间复杂度 O(NlogN)： 最差情况下（为 “满二叉树” 时）， isBalanced(root) 遍历树所有节点，判断每个节点的深度 depth(root) 需要遍历 各子树的所有节点 。
     *       满二叉树高度的复杂度 O(logN) ，将满二叉树按层分为 log (N+1) 层；
     *       通过调用 depth(root) ，判断二叉树各层的节点的对应子树的深度，需遍历节点数量为
     *         Nx1,((N-1)/2)x2,((N-3)/4)x4,((N-7)/8)x8,...,((N+1)/2)x1。因此各层执行 depth(root) 的时间复杂度为 O(N) （每层开始，最多遍历 N 个节点，最少遍历 (N+1)/2 个节点）。
     *      其中，((N-3)/4)x4 代表从此层开始总共需遍历 N−3 个节点，此层共有 4 个节点，因此每个子树需遍历  (N-3)/4 个节点。
     * 因此，总体时间复杂度 == 每层执行复杂度  × 层数复杂度 = O(N×logN) 。
     * 空间复杂度 O(N)： 最差情况下（树退化为链表时），系统递归需要使用 O(N) 的栈空间。
     */
    //public boolean isBalanced(TreeNode root) {
    //    if (root == null) return true;
    //    return  Swordoffer.Math.abs(maxDepth(root.left) - maxDepth(root.right)) <= 1 && isBalanced(root.left) && isBalanced(root.right);
    //}
    //
    //public int maxDepth(TreeNode root){
    //    if (root == null) return 0;
    //    return Swordoffer.Math.max(maxDepth(root.left), maxDepth(root.right)) + 1;
    //}
    //
    //
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
}


























