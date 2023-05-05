package CodeTop;

/**
 * @author mxy
 * @create 2023-04-10 11:10
 */

import java.util.Deque;
import java.util.LinkedList;

/**
 * 111. 二叉树的最小深度    链接：https://leetcode.cn/problems/minimum-depth-of-binary-tree
 * 给定一个二叉树，找出其最小深度。
 * 最小深度是从根节点到最近叶子节点的最短路径上的节点数量。
 * 说明：叶子节点是指没有子节点的节点。
 *
 * 示例 1：
 * 输入：root = [3,9,20,null,null,15,7]
 * 输出：2
 *
 * 示例 2：
 * 输入：root = [2,null,3,null,4,null,5,null,6]
 * 输出：5
 *  
 * 提示：
 * 树中节点数的范围在 [0, 10^5] 内
 * -1000 <= Node.val <= 1000
 *
 */
public class Code111 {


    /**
     * 方法一：递归
     * @param root
     * @return
     */
    public int minDepth1(TreeNode root) {
        if (root == null) return 0;
        int m1 = minDepth1(root.left);
        int m2 = minDepth1(root.right);
        if (root.left == null) return m2 + 1;
        if (root.right == null) return m1 + 1;
        return Math.min(m1, m2) + 1;
    }


    /**
     * 方法二：迭代
     * @param root
     * @return
     */
    public int minDepth2(TreeNode root) {
        if (root == null) return 0;
        Deque<TreeNode> deque = new LinkedList<>();
        deque.offer(root);
        int min = 0;
        while (!deque.isEmpty()){
            int size = deque.size();
            min++;
            for (int i = 0; i < size; i++) {
                //到达叶子节点
                if (deque.peek().left == null && deque.peek().right == null){
                    return min;
                }
                if (deque.peek().left != null){
                    deque.offer(deque.peek().left);
                }
                if (deque.peek().right != null){
                    deque.offer(deque.peek().right);
                }
                deque.poll();
            }
        }
        return min;
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
