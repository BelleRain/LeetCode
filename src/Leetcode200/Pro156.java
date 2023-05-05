package Leetcode200;

/**
 * @author mxy
 * @create 2022-11-08 8:52
 */

/**
 * 上下翻转二叉树
 * 给你一个二叉树的根节点 root ，请你将此二叉树上下翻转，并返回新的根节点。
 * 你可以按下面的步骤翻转一棵二叉树：
 *      1、原来的左子节点变成新的根节点
 *      2、原来的根节点变成新的右子节点
 *      3、原来的右子节点变成新的左子节点
 * 上面的步骤逐层进行。题目数据保证每个右节点都有一个同级节点（即共享同一父节点的左节点）且不存在子节点。
 *
 * 示例 1：
 * 输入：root = [1,2,3,4,5]
 * 输出：[4,5,2,null,null,3,1]
 *
 * 示例 2：
 * 输入：root = []
 * 输出：[]
 *
 * 示例 3：
 * 输入：root = [1]
 * 输出：[1]
 *
 * 提示：
 * 树中节点数目在范围 [0, 10] 内
 * 1 <= Node.val <= 10
 * 树中的每个右节点都有一个同级节点（即共享同一父节点的左节点）
 * 树中的每个右节点都没有子节点
 *
 */
public class Pro156 {

    public static void main(String[] args) {
        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(4);
        TreeNode node5 = new TreeNode(5);

        node1.left = node2;
        node1.right = node3;
        node2.left = node4;
        node2.right = node5;

        Pro156 pro156 = new Pro156();
        pro156.upsideDownBinaryTree(node1);
    }

    /**
     * 题解一：https://leetcode.cn/problems/binary-tree-upside-down/solution/binary-tree-upside-down-top-downdie-dai-fa-by-jin4/
     * 迭代：
     *   树中任何节点的右子节点存在一定有左子节点，因此思路是向左遍历树进行转化
     *   注意：此题画图即可解
     * 复杂度分析：
     *      时间复杂度：O(n)
     *      空间复杂度：O(1) 只用额外开辟三个变量
     * @param root
     * @return
     */
    public TreeNode upsideDownBinaryTree(TreeNode root) {
        TreeNode parent = null, parent_right = null;
        //左子节点变父节点，父节点变右子节点，右子节点变左子节点
        //对于某节点root，修改root.left,root.right之前，需要将三者保存下来
        //每轮循环，将原来的左子节点，右子节点，父节点保存在 root_left，parent_right，parent中，
        //而后改变当前根节点root的指向，即root.left,root.right的值，最后令root指向 root_left，即原根节点的左子节点
        //重复循环，直至 root == null时，证明 最后一个左子节点已经遍历完毕，返回parent，parent即指向最后一个左子节点，即最终的根节点
        //该过程通过画图更易理解
        while(root != null){
            TreeNode root_left = root.left; //从左子节点遍历，保存左子节点
            root.left = parent_right;  // 改变当前root.left的指向，原来的（上一次）右子节点变左子节点，只是第一轮为null
            parent_right = root.right; //保存右子节点
            root.right = parent;  //改变当前root.right的指向，原来的（上一次）父节点变右子节点
            parent = root; //保存当前的父节点
            root = root_left; //将root指向 当前节点的 原来的 左子节点，即指向原树中下一个左子节点
        }
        return parent;
    }

    /**
     * 题解二：递归
     *  主要思路：
     * （1）递归；
     * （2）将左子树进行递归，获得左子树的最终结果；
     * （3）将左子树的最右边的叶子结点连接上根节点和右子树；
     * （4）将根节点的左右子树置为空；
     * @param root
     * @return
     */
    /*public TreeNode upsideDownBinaryTree(TreeNode root) {
        if (root == null || (root.left == null && root.right == null)) { // root为空或是叶子节点返回
            return root;
        }

        TreeNode newRoot = upsideDownBinaryTree(root.left); // 只需递归处理左子树，不需要递归右子树，右子树都是叶子节点

        root.left.left  = root.right; // 三角关系翻转
        root.left.right = root;

        // 根或子树的root变为右叶子节点
        root.left = null;
        // 注意，题解说：所有右节点，都是叶子节点，且有兄弟节点。
        // 所以，root旋转后，都会变为右叶子节点，所以left和right设为null。
        // 估计很多人看漏了这个条件，感觉莫名其妙。我自己还模拟右子节点还有两个子节点的情况，想了半天。。
        root.right = null;
        return newRoot; // 同链表翻转，返回整颗树最左的叶子节点
    }*/


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
