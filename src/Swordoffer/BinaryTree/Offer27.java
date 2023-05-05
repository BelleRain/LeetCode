package Swordoffer.BinaryTree;

/**
 * @author mxy
 * @create 2022-09-22 17:30
 */

import java.util.Stack;

/**
 * 二叉树的镜像
 * 请完成一个函数，输入一个二叉树，该函数输出它的镜像。
 * 例如输入：
 *      4
 *    /   \
 *   2     7
 *  / \   / \
 * 1   3 6   9
 *
 * 镜像输出：
 *      4
 *    /   \
 *   7     2
 *  / \   / \
 * 9   6 3   1
 *
 *示例 1：
 *输入：root = [4,2,7,1,3,6,9]
 *输出：[4,7,2,9,6,3,1]
 */
public class Offer27 {
    public static void main(String[] args) {
        TreeNode node1 = new TreeNode(4);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(7);
        TreeNode node4 = new TreeNode(1);
        TreeNode node5 = new TreeNode(3);
        TreeNode node6 = new TreeNode(6);
        TreeNode node7 = new TreeNode(9);

        node1.left = node2;
        node1.right = node3;
        node2.left = node4;
        node2.right = node5;
        node3.left = node6;
        node3.right = node7;

        Offer27 offer = new Offer27();
        System.out.println("镜像之前：");
        offer.preOrder(node1);
        System.out.println();
        System.out.println("镜像之后");
        offer.mirrorTree(node1);
        offer.preOrder(node1);

    }

    /**
     * 解法一： 递归法
     * 算法分析：
     *     1.终止条件：当节点 root 为空时（即越过叶节点），则返回 null；
     *     2.递推工作：
     *      初始化节点 tmp ，用于暂存 root 的左子节点；
     *      开启递归 右子节点 mirrorTree(root.right)，并将返回值作为 root 的 左子节点 。
     *      开启递归 左子节点 mirrorTree(tmp) ，并将返回值作为 rootroot 的 右子节点 。
     *     3.返回值： 返回当前节点 root；
     *
     * Q： 为何需要暂存 rootroot 的左子节点？
     * A： 在递归右子节点 “root.left = mirrorTree(root.right);root.left=mirrorTree(root.right);”
     *     执行完毕后， root.left 的值已经发生改变，此时递归左子节点 mirrorTree(root.left)则会出问题。
     *
     * 复杂度分析：
     * 时间复杂度 O(N) ： 其中 N 为二叉树的节点数量，建立二叉树镜像需要遍历树的所有节点，占用 O(N) 时间。
     * 空间复杂度 O(N) ： 最差情况下（当二叉树退化为链表），递归时系统需使用 O(N) 大小的栈空间。
     *
     * @param root
     * @return
     */
    //自下而上，后序翻转
    //public TreeNode mirrorTree(TreeNode root) {
    //    if (root == null) return null;
    //    TreeNode tmp = root.left;
    //    root.left = mirrorTree(root.right);
    //    root.right = mirrorTree(tmp);
    //    return root;
    //}

    //自上而下,前序翻转
    //public TreeNode mirrorTree(TreeNode root){
    //    if (root == null) return null;
    //    //交换左右节点
    //    TreeNode tmp = root.left;
    //    root.left = root.right;
    //    root.right = tmp;
    //    //递归翻转左子树
    //    mirrorTree(root.left);
    //    //递归翻转右子树
    //    mirrorTree(root.right);
    //    return root;
    //
    //}

    /**
     * 易理解的递归写法：自下而上
     */
    //public TreeNode mirrorTree(TreeNode root) {
    //    //后序遍历翻转
    //    if(root == null){
    //        return null;
    //    }
    //    //递归翻转左子树
    //    TreeNode left = mirrorTree(root.left);
    //    //递归翻转右子树
    //    TreeNode right = mirrorTree(root.right);
    //    //翻转整棵树
    //    root.left = right;
    //    root.right = left;
    //    return root;
    //}

    /**
     * 方法二：辅助栈（或队列）
     *      利用栈（或队列）遍历树的所有节点 node，并交换每个 node的左 / 右子节点。
     * 算法流程：
     *      1、特例处理： 当 root为空时，直接返回 null；2
     *      2、初始化： 栈（或队列），本文用栈，并加入根节点 root。
     *      3、循环交换： 当栈 stack 为空时跳出；
     *          出栈： 记为 node；
     *          添加子节点： 将 node 左和右子节点入栈；
     *          交换： 交换 node 的左 / 右子节点。
     *      4、返回值： 返回根节点 root 。
     *复杂度分析：
     * 时间复杂度 O(N)： 其中 N 为二叉树的节点数量，建立二叉树镜像需要遍历树的所有节点，占用 O(N)时间。
     * 空间复杂度 O(N)： 如下图所示，最差情况下，栈 stack最多同时存储 2/(N+1)个节点，占用 O(N)额外空间。
     * @param root
     * @return
     */
    public TreeNode mirrorTree(TreeNode root) {
        if (root == null) return null;
        Stack<TreeNode> stack = new Stack<>();
        stack.add(root);
        while (!stack.isEmpty()){
            //出栈： 记为 node；
            TreeNode node = stack.pop();
            //System.out.println(node.val);
            //添加子节点： 将 node 左和右子节点入栈；
            if (node.left != null) stack.add(node.left);
            if (node.right != null) stack.add(node.right);
            //交换： 交换 node 的左 / 右子节点。
            TreeNode tmp = node.left;
            node.left = node.right;
            node.right = tmp;
        }
        return root;
    }


    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode(int x) {
            val = x;
        }

        public int show(){
            return this.val;
        }
    }

    //先序遍历
    public void preOrder(TreeNode root){
        System.out.print(root.show() + " ");
        if (root.left != null){
            preOrder(root.left);
        }
        if (root.right != null){
            preOrder(root.right);
        }
    }

}



























