package Top100;

/**
 * @author mxy
 * @create 2022-11-25 16:33
 */

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * 114. 二叉树展开为链表    链接：https://leetcode.cn/problems/flatten-binary-tree-to-linked-list
 * 给你二叉树的根结点 root ，请你将它展开为一个单链表：
 * 展开后的单链表应该同样使用 TreeNode ，其中 right 子指针指向链表中下一个结点，而左子指针始终为 null 。
 * 展开后的单链表应该与二叉树 先序遍历 顺序相同。
 *  
 * 示例 1：
 * 输入：root = [1,2,5,3,4,null,6]
 * 输出：[1,null,2,null,3,null,4,null,5,null,6]
 *
 * 示例 2：
 * 输入：root = []
 * 输出：[]
 *
 * 示例 3：
 * 输入：root = [0]
 * 输出：[0]
 *  
 *
 * 提示：
 * 树中结点数在范围 [0, 2000] 内
 * -100 <= Node.val <= 100
 *  
 * 进阶：你可以使用原地算法（O(1) 额外空间）展开这棵树吗？
 *
 */
public class Top114 {

    /**
     * 题解链接：https://leetcode.cn/problems/flatten-binary-tree-to-linked-list/solution/er-cha-shu-zhan-kai-wei-lian-biao-by-leetcode-solu/
     */

    /**
     * 方法一：前序遍历
     * 利用 前序遍历 构建二叉树
     * @param root
     */
    /*public void flatten(TreeNode root) {
        List<TreeNode> list = new ArrayList<>();
        preorderTraversal(root, list);
        int size = list.size();
        for (int i = 1; i < size; i++) {
            TreeNode prev = list.get(i - 1);
            TreeNode curr = list.get(i);
            prev.left = null;
            prev.right = curr;
        }
    }

    public void preorderTraversal(TreeNode root, List<TreeNode> list){
        if (root != null){
            list.add(root);
            preorderTraversal(root.left, list);
            preorderTraversal(root.right, list);
        }
    }*/

    /**
     * 迭代实现前序遍历
     * @param root
     */
    /*public void flatten(TreeNode root) {
        List<TreeNode> list = new ArrayList<>();
        Deque<TreeNode> stack = new LinkedList<>();
        TreeNode node = root;
        while (node != null || !stack.isEmpty()){
            while (node != null){
                list.add(node);
                stack.push(node);
                node = node.left;
            }
            node = stack.pop();
            node = node.right;
        }
        int size = list.size();
        for (int i = 1; i < size; i++) {
            TreeNode prev = list.get(i - 1);
            TreeNode curr = list.get(i);
            prev.left = null;
            prev.right = curr;
        }
    }*/

    /**
     * 方法二：前序遍历和展开同步进行
     * @param root
     */
    /*public void flatten(TreeNode root) {
        if (root == null) return;
        Deque<TreeNode> stack = new LinkedList<>();
        stack.push(root);
        TreeNode prev = null;
        while (!stack.isEmpty()){
            TreeNode curr = stack.pop();
            if (prev != null){
                prev.left = null;
                prev.right = curr;
            }
            //提前存储左右节点，先打印左节点，故左节点后入栈
            TreeNode left = curr.left,right = curr.right;
            if (right != null) stack.push(right);
            if (left != null) stack.push(left);
            prev = curr;
        }
    }*/

    /**
     * 方法三： 寻找前驱节点
     * 复杂度分析
     *      时间复杂度：O(n)，其中 n 是二叉树的节点数。展开为单链表的过程中，需要对每个节点访问一次，在寻找前驱节点的过程中，每个节点最多被额外访问一次。
     *      空间复杂度：O(1)。
     * @param root
     */
    public void flatten(TreeNode root) {
        TreeNode curr = root;
        while (curr != null){
            if (curr.left != null){
                TreeNode next = curr.left;
                TreeNode predecessor = next;
                while (predecessor.right != null){
                    predecessor = predecessor.right;
                }
                predecessor.right = curr.right;
                curr.left = null;
                curr.right = next;
            }
            curr = curr.right;
        }
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
