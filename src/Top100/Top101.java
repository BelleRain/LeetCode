package Top100;

/**
 * @author mxy
 * @create 2022-11-25 8:45
 */

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 101. 对称二叉树  (同剑指 Offer28 题)    链接：https://leetcode.cn/problems/symmetric-tree
 * 给你一个二叉树的根节点 root ， 检查它是否轴对称。
 *
 * 示例 1：
 * 输入：root = [1,2,2,3,4,4,3]
 * 输出：true
 *
 * 示例 2：
 * 输入：root = [1,2,2,null,3,null,3]
 * 输出：false
 *  
 *
 * 提示：
 * 树中节点数目在范围 [1, 1000] 内
 * -100 <= Node.val <= 100
 *  
 * 进阶：你可以运用递归和迭代两种方法解决这个问题吗？
 */
public class Top101 {

    public static void main(String[] args) {
        Top101 top101 = new Top101();
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

        System.out.println(top101.isSymmetric(node1));
    }

    /**
     * 链接：https://leetcode.cn/problems/symmetric-tree/solution/dui-cheng-er-cha-shu-by-leetcode-solution/
     */

    /**
     * 方法一：递归
     * 复杂度分析：
     *      假设树上一共 n 个节点。
     *      时间复杂度：这里遍历了这棵树，渐进时间复杂度为 O(n)。
     *      空间复杂度：这里的空间复杂度和递归使用的栈空间有关，这里递归层数不超过 n，故渐进空间复杂度为 O(n)。
     * @param root
     * @return
     */
    public boolean isSymmetric(TreeNode root) {
        if (root == null) return true;
        return isSymmetric(root.left, root.right);
    }

    public boolean isSymmetric(TreeNode root1,TreeNode root2){
        if (root1 == null && root2 == null) return true;
        //当 L 或 R 中只有一个越过叶节点： 此树不对称，因此返回 false；
        if (root1 == null || root2 == null || root1.val != root2.val) return false;
        //当 roo1 的 left 的值 == root2 的 right 的值 && root1 的 right 值 == root2 的 left 值 ，两树对称
        return isSymmetric(root1.left, root2.right) && isSymmetric(root1.right,root2.left);
    }

    /**
     * 方法二：迭代
     * @param root
     * @return
     */
    public boolean isSymmetricIter(TreeNode root) {
        return isSymmetricIter(root.left, root.right);
    }

    public boolean isSymmetricIter(TreeNode root1,TreeNode root2){
        Queue<TreeNode> list = new LinkedList<>();
        list.offer(root1);
        list.offer(root2);
        while (!list.isEmpty()){
            root1 = list.poll();
            root2 = list.poll();
            if (root1 == null && root2 == null) continue;
            if (root1 == null || root2 == null || root1.val != root2.val) return false;
            list.offer(root1.left);
            list.offer(root2.right);
            list.offer(root1.right);
            list.offer(root2.left);
        }
        return true;
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
