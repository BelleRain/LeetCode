package CodeTop;

/**
 * @author mxy
 * @create 2023-06-14 9:11
 */

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 剑指 Offer 54. 二叉搜索树的第 k大节点     链接：https://leetcode.cn/problems/er-cha-sou-suo-shu-de-di-kda-jie-dian-lcof
 * 给定一棵二叉搜索树，请找出其中第 k 大的节点的值。
 *
 * 示例 1:
 * 输入: root = [3,1,4,null,2], k = 1
 *    3
 *   / \
 *  1   4
 *   \
 *    2
 * 输出: 4
 *
 * 示例 2:
 * 输入: root = [5,3,6,2,4,null,null,1], k = 3
 *        5
 *       / \
 *      3   6
 *     / \
 *    2   4
 *   /
 *  1
 * 输出: 4
 *
 * 限制：
 * 1 ≤ k ≤ 二叉搜索树元素个数
 *
 */
public class Offer54 {

    public static void main(String[] args) {

    }

    /**
     * 执行用时： 1 ms , 在所有 Java 提交中击败了 31.99% 的用户
     * 内存消耗： 42.7 MB, , 在所有 Java 提交中击败了 28.87% 的用户
     *
     * 中序遍历二叉搜索树
     * @param root
     * @param k
     * @return
     */
    public static int kthLargest1(TreeNode root, int k) {
        List<Integer> list = new ArrayList<>();
        dfs(root,list);
        return list.get(list.size() - k);
    }

    private static void dfs(TreeNode root, List<Integer> list) {
        if (root == null) return;
        dfs(root.left, list);
        list.add(root.val);
        dfs(root.right, list);
    }


    /**
     * 执行用时：0 ms，在所有 Java 提交中击败了 100.00% 的用户
     * 内存消耗：43.1 MB，在所有 Java 提交中击败了 8.45% 的用户
     * @param root
     * @param k
     * @return
     */
    int res,k;
    public int kthLargest(TreeNode root, int k) {
        this.k = k;
        dfs(root);
        return res;
    }

    private void dfs(TreeNode root) {
        if (root == null) return;
        dfs(root.right);
        if (k == 0) return;
        if (--k == 0) res = root.val;
        dfs(root.left);
    }


    //创建二叉树
    public static TreeNode createTree(int[] nums){
        if (nums[0] == -1) return null;
        HashMap<Integer, TreeNode> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            TreeNode node = null;
            if (nums[i] == -1){
                map.put(i, null);
            }else {
                map.put(i, new TreeNode(nums[i]));
            }
        }
        for (int i = 0; (2 * i + 2) < nums.length; i++) {
            if (nums[i] == -1) continue;
            map.get(i).left = map.get(2 * i + 1);
            map.get(i).right = map.get(2 * i + 2);
        }
        return map.get(0);
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
