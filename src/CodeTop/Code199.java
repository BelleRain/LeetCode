package CodeTop;

/**
 * @author mxy
 * @create 2023-04-20 9:25
 */

import com.sun.jmx.remote.internal.ArrayQueue;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

/**
 * 199. 二叉树的右视图     链接：https://leetcode.cn/problems/binary-tree-right-side-view
 * 给定一个二叉树的 根节点 root，想象自己站在它的右侧，按照从顶部到底部的顺序，返回从右侧所能看到的节点值。
 *
 * 示例 1:
 * 输入: [1,2,3,null,5,null,4]
 * 输出: [1,3,4]
 *
 * 示例 2:
 * 输入: [1,null,3]
 * 输出: [1,3]
 *
 * 示例 3:
 * 输入: []
 * 输出: []
 *  
 * 提示:
 * 二叉树的节点个数的范围是 [0,100]
 * -100 <= Node.val <= 100 
 *
 */
public class Code199 {

    public static void main(String[] args) {
        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(5);
        TreeNode node5 = new TreeNode(4);
        node1.left = node2;
        node1.right = node3;
        node2.left = node4;
        //node2.right = node4;
        //node3.right = node5;

        //List<Integer> res = rightSideView1(node1);
        List<Integer> res = rightSideView2(node1);
        System.out.println(res);


    }


    /**
     * dfs
     * @param root
     * @return
     */
    public static List<Integer> rightSideView1(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        dfs(res,root,0);
        return res;
    }

    private static void dfs(List<Integer> res, TreeNode root, int depth) {
        if (root == null) return;
        // 如果当前节点所在深度还没有出现在res里，说明在该深度下当前节点是第一个被访问的节点，因此将当前节点加入res中。
        if (depth == res.size()){
            res.add(root.val);
        }
        depth++;
        dfs(res, root.right, depth);
        dfs(res, root.left, depth);
    }

    /**
     * bfs
     * @param root
     * @return
     */
    public static List<Integer> rightSideView2(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if (root == null) return res;
        Deque<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);
        while (!queue.isEmpty()){
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null){
                    queue.offer(node.right);
                }
                //将该层最后一个节点的值放入res中
                if (i == size - 1) res.add(node.val);
            }
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
