package CodeTop;

/**
 * @author mxy
 * @create 2023-04-27 10:19
 */

import com.sun.jmx.remote.internal.ArrayQueue;

import javax.naming.LinkLoopException;
import java.util.*;

/**
 * 113. 路径总和 II   链接：https://leetcode.cn/problems/path-sum-ii
 * 给你二叉树的根节点 root 和一个整数目标和 targetSum ，找出所有 从根节点到叶子节点 路径总和等于给定目标和的路径。
 * 叶子节点 是指没有子节点的节点。
 *
 * 示例 1：
 * 输入：root = [5,4,8,11,null,13,4,7,2,null,null,5,1], targetSum = 22
 * 输出：[[5,4,11,2],[5,8,4,5]]
 *
 * 示例 2：
 * 输入：root = [1,2,3], targetSum = 5
 * 输出：[]
 *
 * 示例 3：
 * 输入：root = [1,2], targetSum = 0
 * 输出：[]
 *
 * 提示：
 * 树中节点总数在范围 [0, 5000] 内
 * -1000 <= Node.val <= 1000
 * -1000 <= targetSum <= 1000
 *
 */
public class Code113 {

    public static void main(String[] args) {
        int[] nums = {5,4,8,11,-1,13,4,7,2,-1,-1,-1,-1,5,1};
        TreeNode root = createTree(nums);
        printNode(root);
        System.out.println();
        System.out.println(pathSum(root, 22));
        System.out.println("============================");
        System.out.println(pathSum1(root, 22));
    }


    public static List<List<Integer>> pathSum(TreeNode root, int targetSum) {
        List<List<Integer>> res = new ArrayList<>();
        if (root == null) return res;
        Deque<Integer> path = new LinkedList<>();
        dfs(res,path,root,0,targetSum);
        return res;
    }

    private static void dfs(List<List<Integer>> res, Deque<Integer> path, TreeNode root, int pathSum, int targetSum) {
        if (root == null) return;
        path.offerLast(root.val);
        pathSum = pathSum + root.val;
        if (root.left == null && root.right == null){
            if (pathSum == targetSum){
                res.add(new ArrayList<>(path));
                path.removeLast();
                return;
            }
            path.removeLast();
            return;
        }
        System.out.println(path);
        dfs(res, path, root.left, pathSum, targetSum);
        dfs(res, path, root.right, pathSum, targetSum);
        path.removeLast();
        System.out.println("=====>" + path);
    }




    /**
     * 官方题解：
     * @param root
     * @param targetSum
     * @return
     */
    static List<List<Integer>> res = new LinkedList<>();
    static Deque<Integer> path = new LinkedList<>();
    public static List<List<Integer>> pathSum1(TreeNode root, int targetSum) {
        if (root == null)
            return res;
        dfs(root, targetSum);
        return res;
    }
    private static void dfs(TreeNode root, int targetSum) {
        if (root == null)
            return;
        path.add(root.val);
        //到达叶子节点
        if (root.left == null && root.right == null) {
            if (targetSum == root.val) {
                res.add(new LinkedList(path));
            }
        }
        System.out.println(path);
        dfs(root.left, targetSum - root.val);
        dfs(root.right, targetSum - root.val);
        path.removeLast();
        System.out.println("=====>" + path);
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

    //层序遍历
    public static void printNode(TreeNode root){
        if (root == null) return;
        Deque<TreeNode> list = new LinkedList<>();
        list.offer(root);
        while (!list.isEmpty()){
            int size = list.size();
            for (int i = 0; i < size; i++) {
                TreeNode node = list.poll();
                System.out.print(node.val + " ");
                if (node.left != null){
                    list.offer(node.left);
                }
                if (node.right != null){
                    list.offer(node.right);
                }
            }
        }
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
