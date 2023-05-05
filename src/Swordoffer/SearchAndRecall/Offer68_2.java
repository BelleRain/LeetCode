package Swordoffer.SearchAndRecall;

/**
 * @author mxy
 * @create 2022-10-12 10:53
 */

/**
 * 二叉树的最近公共祖先   链接：https://leetcode.cn/problems/er-cha-shu-de-zui-jin-gong-gong-zu-xian-lcof/
 * 给定一个二叉树, 找到该树中两个指定节点的最近公共祖先。
 * 百度百科中最近公共祖先的定义为：“对于有根树 T 的两个结点 p、q，最近公共祖先表示为一个结点 x，满足 x 是 p、q 的祖先且 x 的深度尽可能大（一个节点也可以是它自己的祖先）。”
 * 例如，给定如下二叉树:  root = [3,5,1,6,2,0,8,null,null,7,4]
 * 示例 1:
 * 输入: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 1
 * 输出: 3
 * 解释: 节点 5 和节点 1 的最近公共祖先是节点 3。
 *
 * 示例 2:
 * 输入: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 4
 * 输出: 5
 * 解释: 节点 5 和节点 4 的最近公共祖先是节点 5。因为根据定义最近公共祖先节点可以为节点本身。
 *
 * 说明:
 * 所有节点的值都是唯一的。
 * p、q 为不同节点且均存在于给定的二叉树中。
 */
public class Offer68_2 {

    /**
     * 题解一： 原文链接：https://leetcode.cn/problems/er-cha-shu-de-zui-jin-gong-gong-zu-xian-lcof/solution/mian-shi-ti-68-ii-er-cha-shu-de-zui-jin-gong-gon-7/
     * 根据以上定义，若 root 是 p, q 的 最近公共祖先 ，则只可能为以下情况之一：
     *      p 和 q 在 root 的子树中，且分列 root 的 异侧（即分别在左、右子树中）；
     *      p = root ，且 q 在 root 的左或右子树中；
     *      q = root ，且 p 在 root 的左或右子树中；
     * 考虑通过递归对二叉树进行先序遍历，当遇到节点 p 或 q 时返回。从底至顶回溯，当节点 p, q 在节点 root 的异侧时，节点 root 即为最近公共祖先，则向上返回 root 。
     * 递归解析：
     * 1、终止条件：
     *      1、当越过叶节点，则直接返回 null ；
     *      2、当 root 等于 p, q ，则直接返回 root ；
     * 2、递推工作：
     *      1、开启递归左子节点，返回值记为 left ；
     *      2、开启递归右子节点，返回值记为 right ；
     * 3、返回值： 根据 left 和 right ，可展开为四种情况；
     *      1、当 left 和 right 同时为空 ：说明 root 的左 / 右子树中都不包含 p,q ，返回 null ；
     *      2、当 left 和 right 同时不为空 ：说明 p, q 分列在 root 的 异侧 （分别在 左 / 右子树），因此 root 为最近公共祖先，返回 root ；
     *      3、当 left= 为空 ，right= 不为空 ：p,q= 都不在 root= 的左子树中，直接返回 right= 。具体可分为两种情况：
     *          1、p,q 其中一个在 root 的 右子树 中，此时 right 指向 p（假设为 p ）；
     *          2、p,q 两节点都在 root 的 右子树 中，此时的 right 指向 最近公共祖先节点 ；
     *      4、当 left 不为空 ， right 为空 ：与情况 3. 同理；
     *  观察发现， 情况 1. 可合并至 3. 和 4. 内，详见文章末尾代码。
     *复杂度分析：
     * 时间复杂度 O(N) ： 其中 N 为二叉树节点数；最差情况下，需要递归遍历树的所有节点。
     * 空间复杂度 O(N) ： 最差情况下，递归深度达到 N ，系统使用 O(N) 大小的额外空间。
     * @param root
     * @param p
     * @param q
     * @return
     */
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root == p || root == q) return root;
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        if (left == null) return right;
        if (right == null) return left;
        return root;
    }

    //情况 1. , 2. , 3. , 4. 的展开写法如下。
    //public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
    //    if(root == null || root == p || root == q) return root;
    //    TreeNode left = lowestCommonAncestor(root.left, p, q);
    //    TreeNode right = lowestCommonAncestor(root.right, p, q);
    //    if(left == null && right == null) return null; // 1.
    //    if(left == null) return right; // 3.
    //    if(right == null) return left; // 4.
    //    return root; // 2. if(left != null and right != null)
    //}

    /**
     * 题解链接：https://leetcode.cn/problems/er-cha-shu-de-zui-jin-gong-gong-zu-xian-lcof/solution/er-cha-shu-de-zui-jin-gong-gong-zu-xian-6fdt7/
     */
    /**
     * 方法二：递归
     * 思路和算法：
     * 我们递归遍历整棵二叉树，定义 fx表示 x 节点的子树中是否包含 p 节点或 q 节点，如果包含为 true，否则为 false。那么符合条件的最近公共祖先 x 一定满足如下条件：
     *          (flson && frson) || ((x=p || x=q) && (flson || frson))
     * 其中 lson 和 rson 分别代表 x 节点的左孩子和右孩子。初看可能会感觉条件判断有点复杂，我们来一条条看，
     *   flson && frson 说明左子树和右子树均包含 p 节点或 q 节点，如果左子树包含的是 p 节点，那么右子树只能包含 q 节点，反之亦然，因为 p 节点和 q 节点都是不同且唯一的节点，因此如果满足这个判断条件即可说明 x 就是我们要找的最近公共祖先。
     *   再来看第二条判断条件，这个判断条件即是考虑了 x 恰好是 p 节点或 q 节点且它的左子树或右子树有一个包含了另一个节点的情况，因此如果满足这个判断条件亦可说明 x 就是我们要找的最近公共祖先。
     *  你可能会疑惑这样找出来的公共祖先深度是否是最大的。其实是最大的，因为我们是自底向上从叶子节点开始更新的，所以在所有满足条件的公共祖先中一定是深度最大的祖先先被访问到，
     *  且由于 fx 本身的定义很巧妙，在找到最近公共祖先 x 以后，fx按定义被设置为 true ，即假定了这个子树中只有一个 p 节点或 q 节点，因此其他公共祖先不会再被判断为符合条件。
     * 复杂度分析
     *   时间复杂度：O(N)，其中 N 是二叉树的节点数。二叉树的所有节点有且只会被访问一次，因此时间复杂度为 O(N)。
     *   空间复杂度：O(N) ，其中 N 是二叉树的节点数。递归调用的栈深度取决于二叉树的高度，二叉树最坏情况下为一条链，此时高度为 N，因此空间复杂度为 O(N)。
     * @param root
     * @param p
     * @param q
     * @return
     */
    //private TreeNode ans;
    //public Offer68_2() {
    //    this.ans = null;
    //}
    //
    //private boolean dfs(TreeNode root, TreeNode p, TreeNode q) {
    //    if (root == null) return false;
    //    boolean lson = dfs(root.left, p, q);
    //    boolean rson = dfs(root.right, p, q);
    //    if ((lson && rson) || ((root.val == p.val || root.val == q.val) && (lson || rson))) {
    //        ans = root;
    //    }
    //    return lson || rson || (root.val == p.val || root.val == q.val);
    //}
    //
    //public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
    //    this.dfs(root, p, q);
    //    return this.ans;
    //}

    /**
     *方法三：存储父节点
     * 思路:
     * 我们可以用哈希表存储所有节点的父节点，然后我们就可以利用节点的父节点信息从 p 结点开始不断往上跳，并记录已经访问过的节点，再从 q 节点开始不断往上跳，如果碰到已经访问过的节点，那么这个节点就是我们要找的最近公共祖先。
     * 算法
     *  1、 从根节点开始遍历整棵二叉树，用哈希表记录每个节点的父节点指针。
     *  2、从 p 节点开始不断往它的祖先移动，并用数据结构记录已经访问过的祖先节点。
     *  3、同样，我们再从 q 节点开始不断往它的祖先移动，如果有祖先已经被访问过，即意味着这是 p 和 q 的深度最深的公共祖先，即 LCA 节点。
     * 复杂度分析
     * 时间复杂度：O(N)，其中 N 是二叉树的节点数。二叉树的所有节点有且只会被访问一次，从 p 和 q 节点往上跳经过的祖先节点个数不会超过 N，因此总的时间复杂度为 O(N)。
     * 空间复杂度：O(N) ，其中 N 是二叉树的节点数。递归调用的栈深度取决于二叉树的高度，二叉树最坏情况下为一条链，此时高度为 N，因此空间复杂度为 O(N)，哈希表存储每个节点的父节点也需要 O(N) 的空间复杂度，因此最后总的空间复杂度为 O(N)。
     * @param root
     * @param p
     * @param q
     * @return
     */
    //Map<Integer, TreeNode> parent = new HashMap<Integer, TreeNode>();
    //Set<Integer> visited = new HashSet<Integer>();
    //
    //public void dfs(TreeNode root) {
    //    if (root.left != null) {
    //        parent.put(root.left.val, root);
    //        dfs(root.left);
    //    }
    //    if (root.right != null) {
    //        parent.put(root.right.val, root);
    //        dfs(root.right);
    //    }
    //}
    //
    //public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
    //    dfs(root);
    //    while (p != null) {
    //        visited.add(p.val);
    //        p = parent.get(p.val);
    //    }
    //    while (q != null) {
    //        if (visited.contains(q.val)) {
    //            return q;
    //        }
    //        q = parent.get(q.val);
    //    }
    //    return null;
    //}



    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }
}

































