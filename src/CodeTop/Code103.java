package CodeTop;

/**
 * @author mxy
 * @create 2023-04-14 11:41
 */

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * 103. 二叉树的锯齿形层序遍历     链接：https://leetcode.cn/problems/binary-tree-zigzag-level-order-traversal
 * 给你二叉树的根节点 root ，返回其节点值的 锯齿形层序遍历 。
 * （即先从左往右，再从右往左进行下一层遍历，以此类推，层与层之间交替进行）。
 *
 *       3
 *      / \
 *     9  20
 *       /  \
 *      15   7
 * 示例 1：
 * 输入：root = [3,9,20,null,null,15,7]
 * 输出：[[3],[20,9],[15,7]]
 *
 * 示例 2：
 * 输入：root = [1]
 * 输出：[[1]]
 *
 * 示例 3：
 * 输入：root = []
 * 输出：[]
 *  
 *
 * 提示：
 *
 * 树中节点数目在范围 [0, 2000] 内
 * -100 <= Node.val <= 100
 *
 */
public class Code103 {

    public static void main(String[] args) {
        TreeNode node1 = new TreeNode(3);
        TreeNode node2 = new TreeNode(9);
        TreeNode node3 = new TreeNode(20);
        TreeNode node4 = new TreeNode(15);
        TreeNode node5 = new TreeNode(7);

        node1.left = node2;
        node1.right = node3;
        node3.left = node4;
        node3.right = node5;

        System.out.println(zigzagLevelOrder(node1));
    }

    public static List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        Deque<TreeNode> list = new LinkedList<>();
        int level = 0;
        if (root == null) return res;
        list.add(root);
        while (!list.isEmpty()){
            int size = list.size();
            level++;
            Deque<Integer> list1 = new LinkedList<>();
            for (int i = 0; i < size; i++) {
                TreeNode node = list.poll();
                if ((level & 1) == 1){
                    list1.addLast(node.val);
                }else {
                    list1.addFirst(node.val);
                }
                if (node.left != null) list.add(node.left);
                if (node.right != null) list.add(node.right);
            }
            res.add((List<Integer>) list1);
        }
        return res;
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
