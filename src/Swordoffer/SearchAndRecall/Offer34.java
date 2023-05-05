package Swordoffer.SearchAndRecall;

/**
 * @author mxy
 * @create 2022-09-30 10:13
 */

import java.util.*;

/**
 * 二叉树中和为某一值的路径
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
 */
public class Offer34 {

    /*
    先序遍历 + 路径记录
     */

    /**
     * 解法一：  原文链接：https://leetcode.cn/problems/er-cha-shu-zhong-he-wei-mou-yi-zhi-de-lu-jing-lcof/solution/mian-shi-ti-34-er-cha-shu-zhong-he-wei-mou-yi-zh-5/
     * 算法流程：
     *   1、pathSum(root, sum) 函数：
     *       初始化： 结果列表 res ，路径列表 path 。
     *       返回值： 返回 res 即可。
     *   2、recur(root, tar) 函数：
     *      递推参数： 当前节点 root ，当前目标值 tar 。
     *      终止条件： 若节点 root 为空，则直接返回。
     *      递推工作：
     *          1、路径更新： 将当前节点值 root.val 加入路径 path ；
     *          2、目标值更新： tar = tar - root.val（即目标值 tar 从 sum 减至 0 ）；
     *          3、路径记录： 当 ① root 为叶节点 且 ② 路径和等于目标值 ，则将此路径 path 加入 res 。
     *          4、先序遍历： 递归左 / 右子节点。
     *          5、路径恢复： 向上回溯前，需要将当前节点从路径 path 中删除，即执行 path.pop() 。
     * 复杂度分析：
     *    时间复杂度 O(N) ： N 为二叉树的节点数，先序遍历需要遍历所有节点。
     *    空间复杂度 O(N) ： 最差情况下，即树退化为链表时，path 存储所有树节点，使用 O(N) 额外空间。
     * @param root
     * @param target
     * @return
     */
    /* res.add(new LinkedList<>(path));
    值得注意的是，记录路径时若直接执行 res.append(path) ，则是将 path 对象加入了 res ；后续 path 改变时， res 中的 path 对象也会随之改变。
    正确做法：res.append(list(path)) ，相当于复制了一个 path 并加入到 res 。
     */
    LinkedList<List<Integer>> res = new LinkedList<>();
    LinkedList<Integer> path = new LinkedList<>();
    public List<List<Integer>> pathSum(TreeNode root, int target) {
        recur(root, target);
        return res;
    }

    public void recur(TreeNode root,int tar){
        if (root == null) return;
        path.add(root.val);
        tar = tar - root.val;
        if (tar == 0 && root.left == null && root.right == null)
            res.add(new LinkedList<>(path));  //将path集合中的元素加入到 新的LinkedList 中
        recur(root.left, tar);
        recur(root.right, tar);
        //路径恢复： 向上回溯前，需要将当前节点从路径 path 中删除，即执行 path.pop() 。
        path.removeLast();
    }

    /**
     * 方法二：广度优先搜索 原文链接：https://leetcode.cn/problems/er-cha-shu-zhong-he-wei-mou-yi-zhi-de-lu-jing-lcof/solution/er-cha-shu-zhong-he-wei-mou-yi-zhi-de-lu-68dg/
     * 思路及算法:
     *    我们也可以采用广度优先搜索的方式，遍历这棵树。当我们遍历到叶子节点，且此时路径和恰为目标和时，我们就找到了一条满足条件的路径。
     *   为了节省空间，我们使用哈希表记录树中的每一个节点的父节点。每次找到一个满足条件的节点，我们就从该节点出发不断向父节点迭代，即可还原出从根节点到当前节点的路径。
     */
    //List<List<Integer>> ret = new LinkedList<List<Integer>>();
    //Map<TreeNode, TreeNode> map = new HashMap<TreeNode, TreeNode>();
    //public List<List<Integer>> pathSum(TreeNode root, int target) {
    //    if (root == null) {
    //        return ret;
    //    }
    //
    //    Queue<TreeNode> queueNode = new LinkedList<TreeNode>();
    //    Queue<Integer> queueSum = new LinkedList<Integer>();
    //    queueNode.offer(root);
    //    queueSum.offer(0);
    //
    //    while (!queueNode.isEmpty()) {
    //        TreeNode node = queueNode.poll();
    //        int rec = queueSum.poll() + node.val;
    //
    //        if (node.left == null && node.right == null) {
    //            if (rec == target) {
    //                getPath(node);
    //            }
    //        } else {
    //            if (node.left != null) {
    //                map.put(node.left, node);
    //                queueNode.offer(node.left);
    //                queueSum.offer(rec);
    //            }
    //            if (node.right != null) {
    //                map.put(node.right, node);
    //                queueNode.offer(node.right);
    //                queueSum.offer(rec);
    //            }
    //        }
    //    }
    //
    //    return ret;
    //}
    ////map当中 （子节点，父节点）
    //public void getPath(TreeNode node) {
    //    List<Integer> temp = new LinkedList<Integer>();
    //    while (node != null) {
    //        temp.add(node.val); //加入当前节点
    //        node = map.get(node);  //取出当前节点的父节点
    //    }
    //    Collections.reverse(temp); //反转链表
    //    ret.add(new LinkedList<Integer>(temp));
    //    //ret.add(temp); //也可，因为 temp 为局部变量，而非全局变量，所以每次调用都会生成一个新的对象，无需复制
    //}

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
    //先序遍历
    //public void preOrder(TreeNode root){
    //    if (root == null) return;
    //    System.out.println(root);
    //    preOrder(root.left);
    //    preOrder(root.right);
    //}
}


































