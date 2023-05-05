package Swordoffer.SearchAndRecall;

/**
 * @author mxy
 * @create 2022-10-01 11:02
 */

/**
 * 二叉搜索树的第k大节点
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
 */
public class Offer54 {

    public static void main(String[] args) {
        TreeNode node1 = new TreeNode(5);
        TreeNode node2 = new TreeNode(3);
        TreeNode node3 = new TreeNode(6);
        TreeNode node4 = new TreeNode(2);
        TreeNode node5 = new TreeNode(4);
        TreeNode node6 = new TreeNode(1);

        node1.left = node2;
        node1.right = node3;
        node2.left = node4;
        node2.right = node5;
        node4.left = node6;

        Offer54 offer = new Offer54();
        System.out.println(offer.kthLargest(node1, 3));
    }
    /**
     * 解法一： 二叉搜索树，逆向的中序遍历，哈希表用于记录顺序
     * 执行用时： 1 ms , 在所有 Java 提交中击败了 31.13% 的用户
     * 内存消耗： 41.8 MB , 在所有 Java 提交中击败了 17.58% 的用户
     * @param root
     * @param k
     * @return
     */
    //Map<Integer,Integer> map = new HashMap<>();
    //static int count = 0;
    //public int kthLargest(TreeNode root, int k) {
    //    infixOrderReverse(root);
    //    return map.get(count-k+1);
    //}
    //
    //public void infixOrderReverse(TreeNode root){
    //    if (root == null) return;
    //    infixOrderReverse(root.left);
    //    map.put(++count, root.val);
    //    infixOrderReverse(root.right);
    //}

    /**
     * 解法二： 中序遍历 + 提前返回 原文链接：https://leetcode.cn/problems/er-cha-sou-suo-shu-de-di-kda-jie-dian-lcof/solution/mian-shi-ti-54-er-cha-sou-suo-shu-de-di-k-da-jie-d/
     * 执行用时： 0 ms , 在所有 Java 提交中击败了 100.00% 的用户
     * 内存消耗： 41.1 MB , 在所有 Java 提交中击败了 81.70% 的用户
     * 解题思路：
     *  1、本文解法基于此性质：二叉搜索树的中序遍历为 递增序列 。
     *   根据以上性质，易得二叉搜索树的 中序遍历倒序 为 递减序列 。
     *   因此，求 “二叉搜索树第 k 大的节点” 可转化为求 “此树的中序遍历倒序的第 k 个节点”。
     * 2、中序遍历的倒序 为 “右、根、左” 顺序
     * 3、为求第 k 个节点，需要实现以下 三项工作 ：
     *      1、递归遍历时计数，统计当前节点的序号；
     *      2、递归到第 k 个节点时，应记录结果 res ；
     *      3、记录结果后，后续的遍历即失去意义，应提前终止（即返回）。
     * 4、递归解析：
     *     1、终止条件： 当节点 root 为空（越过叶节点），则直接返回；
     *     2、递归右子树： 即 dfs(root.right)；
     *     3、三项工作：
     *          1、提前返回： 若 k = 0 ，代表已找到目标节点，无需继续遍历，因此直接返回；
     *          2、统计序号： 执行 k = k - 1 （即从 k 减至 0 ）；
     *          3、记录结果： 若 k = 0 ，代表当前节点为第 k 大的节点，因此记录 res = root.val ；
     *     4、递归左子树： 即 dfs(root.left) ；
     * 复杂度分析：
     *    时间复杂度 O(N) ： 当树退化为链表时（全部为右子节点），无论 k 的值大小，递归深度都为 N ，占用 O(N) 时间。
     *    空间复杂度 O(N) ： 当树退化为链表时（全部为右子节点），系统使用 O(N) 大小的栈空间。
     *
     * 代码：
     * 题目指出：1≤k≤N （二叉搜索树节点个数）；因此无需考虑 k > N 的情况。
     * 若考虑，可以在中序遍历完成后判断 k > 0 是否成立，若成立则说明 k > N 。
     *
     */
    int res,k;
    public int kthLargest(TreeNode root, int k) {
        this.k = k;
        dfs(root);
        return res;
    }
    public void dfs(TreeNode root){
        if (root == null) return;
        dfs(root.right);
        if (k == 0) return;  //提前返回： 若 k = 0 ，代表已找到目标节点，无需继续遍历，因此直接返回；
        if (--k == 0)        //统计序号： 执行 k = k - 1 （即从 k 减至 0 ）；
            res = root.val;  //记录结果： 若 k = 0 ，代表当前节点为第 k 大的节点，因此记录 res = root.val ；
        //if (k-- == 0) res = root.val;
        dfs(root.left);
    }

    /* 明确 --k 和 k-- 的区别
    此处不能用 if (k-- == 0) 的原因：
    1、 if (--k == 0) 比较的是 --k之后的值，也就意味着 k=k-1之后的k值。当此处为0时，进行res = root.val的赋值操作，再一次循环 k==0时，直接return
    2、 if (k-- == 0) 比较的是 k-1之前的值，也就是说 k值减一，但（k--）没有。所以，当（k--）== 1 时，k已经为0，这样再一次循环 k==0时，直接return，而不会进行res = root.val的赋值操作，所以返回值为0.
     */

}

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode(int x) {
        val = x;
    }
}




































